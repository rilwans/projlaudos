package veridis.biometric.samples.matching;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import veridis.biometric.BiometricIdentification;
import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricModality;
import veridis.biometric.BiometricScannerType;
import veridis.biometric.BiometricTemplate;
import veridis.biometric.samples.util.LicenseHelper;

public class Statistcs extends JFrame {
	private static final long serialVersionUID = -3076814436316853605L;

	Map<String, List<BiometricTemplate>> templates = new HashMap<String, List<BiometricTemplate>>();

	int maxThreshold;
	int[] correctAccept;
	int[] falseAccept; 
	int[] correctReject;
	int[] falseReject;

	int matchPerformed, matchTotal;
	long runningTime;
	
	public void extract(File base) throws IOException {
		System.out.println("Extracting templates....");
		File[] files = base.listFiles();
		Arrays.sort(files);
		for (File f : files) {
			String name = f.getName();
			if (!name.endsWith(".bmp")) continue;
			File tptFile = new File(f.getCanonicalPath() + ".tpt");
			String clazz = name.replaceAll("-.*", "");
			
			BiometricTemplate tpt;
			if (tptFile.exists()) {
				byte[] tptBuf = new byte[(int)tptFile.length()];
				new FileInputStream(tptFile).read(tptBuf);
				tpt = new BiometricTemplate(tptBuf);
			} else {
				BufferedImage img = ImageIO.read(f);
				tpt = new BiometricTemplate( new BiometricImage(img, 200, BiometricModality.FINGERPRINT, BiometricScannerType.UNKNOWN) );
				new FileOutputStream(tptFile).write(tpt.getData());
			}
			if (!templates.containsKey(clazz))
				templates.put(clazz, new ArrayList<BiometricTemplate>());
			templates.get(clazz).add(tpt);
		}
		System.out.println("Extraction done!");
	}
	
	public void match(int Nthreads) throws InterruptedException {
		final Queue<String> queue = new LinkedList<String>();
		int ntemplates=0;
		for (Map.Entry<String, List<BiometricTemplate>> tptGroup : templates.entrySet()) {
			queue.add(tptGroup.getKey());
			ntemplates+=tptGroup.getValue().size();
		}
		matchTotal = ntemplates*(ntemplates-1);
		
		final Statistcs _statistcs = this;
		ThreadGroup threadGroup = new ThreadGroup("Matching");
		Thread[] threads = new Thread[Nthreads];
		for (int i=0; i<Nthreads; i++) {
			threads[i] = new Thread(threadGroup, "Matching Thread #"+(i+1)) {
				public void run() {
					while (true) {
						String tptGroup;
						synchronized (queue) {
							tptGroup = queue.poll();
						}
						if (tptGroup == null) break;
						List<BiometricTemplate> tptsetA = templates.get(tptGroup);
						for (BiometricTemplate tptA : tptsetA) {
							
							BiometricIdentification identification = tptA.identify();
							for (Collection<BiometricTemplate> tptsetB : templates.values()) {
								for (BiometricTemplate tptB : tptsetB) {
									if (tptA == tptB) continue;
									int score = identification.match(tptB);
									boolean sameClass = (tptsetA == tptsetB);
									
									//Bottleneck
									synchronized (_statistcs) {
										for (int threshold=0; threshold<=maxThreshold; threshold++) {
											boolean matched = (score >= threshold);
											
											if (matched && sameClass) {
												correctAccept[threshold]++;
											} else if (matched && !sameClass) {
												falseAccept[threshold]++;
											} else if (!matched && sameClass) {
												falseReject[threshold]++;
											} else if (!matched && !sameClass) {
												correctReject[threshold]++;
											}
										}
										_statistcs.matchPerformed++;
									}
								}
							}
					}
				}
			}
		};
	}

	Timer timer = new Timer();
	timer.schedule(new TimerTask() {
		long lastTime = System.currentTimeMillis();
		long lastN=0;
		public void run() {
			long now = System.currentTimeMillis();
			int N = matchPerformed;
			if (now != lastTime)
				System.out.println(100*N/matchTotal + "% ("+N + "/" + matchTotal + ") " + 1000*(N-lastN) / (now-lastTime) + " match/s");
			lastN = N;
			lastTime=now;
		}
	}, 0, 500);	
		
	for (Thread t : threads)
		t.start();
	for (Thread t : threads)
		t.join();
	timer.cancel();
	
	/*
	

	DefaultXYDataset dataset = new DefaultXYDataset();
	JFreeChart chart = ChartFactory.createXYLineChart("FMR x FRR", "Eixo X", "Eixo Y", dataset, PlotOrientation.VERTICAL, true, true, false);
	XYPlot plot = chart.getXYPlot();
	ChartPanel chartPanel = new ChartPanel(chart);
	
	double[] axis_data;
	double[] far_data;
	double[] frr_data;
	int foofsdfds=0;
	public void updateChart() {
		if (axis_data == null) {
			axis_data = new double[maxThreshold+1];
			far_data  = new double[maxThreshold+1];
			frr_data  = new double[maxThreshold+1];
			for (int i=0; i<=maxThreshold; i++) {
				axis_data[i] = i;
			}
			dataset.addSeries("FAR", new double[][] {axis_data, far_data} );
			dataset.addSeries("FRR", new double[][] {axis_data, frr_data} );
		}
		for (int i=0; i<=maxThreshold; i++) {
			far_data[i] = falseAccept[i]==0?Double.NaN:falseAccept[i] / (double)(falseAccept[i] + correctReject[i]);
			frr_data[i] = falseReject[i]==0?Double.NaN:falseReject[i] / (double)(falseReject[i] + correctAccept[i]);
		}
		
		foofsdfds = (foofsdfds+1) % 4;
		//if (foofsdfds == 0)
			dataset.seriesChanged(new SeriesChangeEvent(this));*/
	}
	
	//JProgressBar progressBar = new JProgressBar();
	public Statistcs(File base, int maxThreshold) throws IOException, InterruptedException {
		super("Matching statistics");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		this.maxThreshold = maxThreshold;
		this.correctAccept = new int[maxThreshold+1];
		this.falseAccept = new int[maxThreshold+1];
		this.correctReject = new int[maxThreshold+1];
		this.falseReject = new int[maxThreshold+1];

		/*
		LogarithmicAxis Yaxis = new LogarithmicAxis("Hi5");
		Yaxis.setRange(1e-6, 1);
		plot.setRangeAxis(Yaxis);
		plot.getDomainAxis().setRange(0, maxThreshold);
		updateChart();
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(chartPanel, BorderLayout.CENTER);
		getContentPane().add(progressBar, BorderLayout.SOUTH);
		progressBar.setIndeterminate(true);
		pack();
		setVisible(true);*/
	
		extract(base);
		
		match(Runtime.getRuntime().availableProcessors());
		

		try {
			File CSVfile = new File("roc.csv");
			PrintStream CSVout = new PrintStream(CSVfile);
			CSVout.println("THR,\tTP,\tTN,\tFP,\tFN,\tFAR,\tFRR");
			for (int i=0; i<=maxThreshold; i++) {
				double far = falseAccept[i]==0?0: 100.0 * falseAccept[i] / (falseAccept[i] + correctReject[i]);
				double frr = falseReject[i]==0?0: 100.0 * falseReject[i] / (falseReject[i] + correctAccept[i]); 
				CSVout.format(Locale.ENGLISH, "%d,\t%d,\t%d,\t%d,\t%d,\t%.8f,\t%.8f\n", i, correctAccept[i], correctReject[i], falseAccept[i], falseReject[i], far, frr);
			}
			CSVout.close();
			System.out.println("Saving to roc.csv");
		} catch (IOException e) { e.printStackTrace(); }		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length==0) {
			System.out.println("Argumento: Pasta com impressões digitais");
		}
		File base=new File(args[0]);
		
		LicenseHelper.installLicense();
		new Statistcs(base, 100);
	}
}
