package veridis.biometric.samples.applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import veridis.biometric.BiometricException;
import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricSDK;
import veridis.biometric.BiometricScanner;
import veridis.biometric.BiometricTemplate;
import veridis.biometric.CaptureEventListener;
import veridis.biometric.JBiometricPanel;

/**
 * <p>
 * Essa classe &eacute; usada para fornecer acesso a m&eacute;todos de captura
 * /extra&ccedil;&atilde;o / verifica&ccedil;&atilde;o.
 * </p>
 * 
 * 
 * Parâmetros:
 * 
 * <ul>
 * <li><i>Callbacks:</i></li>
 * <li><b>onLoaded</b> - Esse callback é chamado assim que o applet é totalmente carregado.</li>
 * <li><b>onInitialization</b> - Esse callback é chamado após o carregamento do applet, após o licenciamento ser feito com sucesso.</li>
 * <li><b>onCapture</b> - Esse callback é chamado a cada captura realizada pelo equipamento biom&eacute;trico. Se o parâmetro
 *     <b>verifyTemplates</b> for verdadeiro, a imagem capturada é verificada com os templates armazenados internamente. Se a imagem capturada obtiver
 *     <i>matching</i>, o callback é chamado. Caso contrário, é chamado o callback <b>onBadVerify</b></li>
 * <li><b>onBadVerify</b> - Esse <i>callback</i> é chamado quando uma captura realizada pelo equipamento biométrico não obtiver matching com os templates armazenados internamente. 
 * Só chamado se o parâmetro <b>verifyTemplates</b> for verdadeiro.</li>
 * <li><b>onPlaced</b>  - Esse <i>callback</i> é chamado quando a biometria é colocada no equipamento de captura.</li>
 * <li><b>onRemoved</b> - Esse <i>callback</i> é chamado quando a biometria é removida do equipamento de captura.</li>
 * 
 * <li><i>Outros:</i></li>
 * <li><b>license</b> - A chave da licença do Veridis Biometric SDK.</li>
 * <li><b>verifyTemplates</b> - Esse parâmetro define se, para cada captura, será verificada se o template é compatível com os templates armazenados internamente.</li>
 * <li><b>verifyMinThreshold</b> - Esse parâmetroc define o limiar que determina quando ocorre uma verifica&ccção com sucesso entre dois templates. Por padrão é <b>20</b></li>
 * </ul>
 * 
 * @author <a href="mailto:moacyr.ricardo@veridistec.com.br">Moacyr Ricardo Pereira Neto</a>
 * 
 */
public class AppletSample extends JApplet implements CaptureEventListener {

	private static final long serialVersionUID = 7349792354643528033L;

	private JBiometricPanel fingerprintViewPanel = new JBiometricPanel();
	private BufferedImage logo = null;

	private List<BiometricTemplate> allTemplates = new ArrayList<BiometricTemplate>();
	private BiometricImage    lastImage;
	private BiometricTemplate lastTemplate;
	private BiometricScanner  lastScanner; 
	
	
	
	
	
	//============================================================ 
	//===================== APPLET LIFECICLE =====================
	//============================================================					debug("Failed to install the license \"" + licenseKey + "\":" + e.toString())
	@Override
	public void init() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace(System.err);
				CharArrayWriter exceptionString = new CharArrayWriter();
				PrintWriter printWriter = new PrintWriter(exceptionString);
				e.printStackTrace(printWriter);
				JOptionPane.showMessageDialog(getContentPane(), exceptionString.toString(), "Uncaught Exception", JOptionPane.ERROR_MESSAGE);
			}
		});
		debug("Applet.init()");
		
		super.init();
		setPreferredSize(new Dimension(200, 250));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(fingerprintViewPanel, BorderLayout.CENTER);
		
		try {
			InputStream in = AppletSample.class.getResourceAsStream("veridislogo.png");
			if (in == null) 
				debug("Can't find resource veridislogo.png");
			else 
				logo = ImageIO.read(in);
		} catch (Exception e) {
			debug("Failed to load veridislogo.png", e);
		}
		
		try {
			this.getClass().getClassLoader().loadClass("netscape.javascript.JSObject");
		} catch (ClassNotFoundException e) {
			debug("ClassNotFoundException: " + e.getMessage());
		}
		callback("onInit");
	}

	@Override
	public void start() {
		debug("Applet.start()");
		
		//Reset the UI
		resetTemplateData();
		
		//License the library. 
		//Will do it later, otherwise the applet won't be displayed until it has finished =/
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				boolean firstLoop = true;
				String licenseKey = null;
				while (BiometricSDK.getLicense() == null) { //Retries until OK
					if (firstLoop) {
						licenseKey = getParameter("license");
						firstLoop = false;
						if (licenseKey==null) continue;
					} else {
						licenseKey = JOptionPane.showInputDialog(getContentPane(), "Type the license key:", licenseKey);
						if (licenseKey==null) {
							JOptionPane.showMessageDialog(getContentPane(), "Applet disabled");
							return;
						}
					}
					
					if (licenseKey != null) {
						try {
							BiometricSDK.installLicense(licenseKey);
						} catch (BiometricException e) {
							debug("Failed to install the license \"" + licenseKey + "\":" + e.toString());
							JOptionPane.showMessageDialog(getContentPane(), "Failed to install the license \"" + licenseKey + "\":" + e.toString(), "Failed to install license", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				debug("License installed: " + BiometricSDK.getLicense().getKey());
				callback("onStart");
			}
		});
	}

	@Override
	public void stop() {
		debug("Applet.stop()");
		
		stopCapture();
		callback("onStop");
		super.stop();
	};

	@Override
	public void destroy() {
		debug("Applet.destroy()");
		
		stopCapture();
		callback("onDestroy");
		super.destroy();
	}


	public void persistTemplate() {
 		allTemplates.add(lastTemplate);
 	}
	public void persistTemplate(String template64Base) {
		try {
			allTemplates.add(new BiometricTemplate( Base64.decode(template64Base) ));
		} catch (Exception e) {
			debug("Failed to decode Base64 template", e);
		}
 	}
	public String getTemplate() {
		if (lastTemplate == null) return null;
		return Base64.encodeBytes(this.lastTemplate.getData());
	}
	public String getImage() {
		return getImage(lastImage.getWidth(), lastImage.getHeight());
	}
	public String getImage(final int _width, final int _height) {
		return AccessController.doPrivileged(new PrivilegedAction<String>() {
			@Override
			public String run() {
				if (lastImage == null) return null;
				try {
					int width = _width;
					int height = _height;
					double scale = 1;
					BufferedImage img = lastImage;
					if (width  >= 1)
						scale = Math.min(scale, width  / (double)img.getWidth() );
					if (height >= 1)
						scale = Math.min(scale, height / (double)img.getHeight() );
					
					int destWidth  = (int)(img.getWidth (null)*scale + 0.5);
					int destHeight = (int)(img.getHeight(null)*scale + 0.5);
					
					if (width <= 0)  width = destWidth;
					if (height <= 0) height = destHeight;
					
					if (destWidth!=img.getWidth() || destHeight!=img.getHeight()) {
						BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = dest.createGraphics();
						g.drawImage(img, (width-destWidth)/2, (height-destHeight)/2, destWidth, destHeight, null);
						g.dispose();
						img = dest;
					}
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(img, "png", baos);
					return Base64.encodeBytes(baos.toByteArray());
				} catch (Exception e) {
					debug("Failed to get Base64 PNG image", e);
					return null;
				}
			}
		});
	}
	public String getScannerName() {
		System.out.println("lastScanner = " + lastScanner);
		if (lastScanner == null) return null;
		return lastScanner.toString();
	}
	/**
	 * Clears the applet's inner template list.<br/>
	 * <br/>
	 * Limpa a lista de templates interna do applet.
	 * 
	 * @see AppletSample#persistTemplateDataOnTemplates()
	 */
	public void resetTemplateData() {
		allTemplates.clear();
		for (int i = 1; getParameter("template" + i) != null; i++) {
			String strTemp = getParameter("template" + i, "").trim();
			if (strTemp.length() == 0) break;
			try {
				byte[] tempData = Base64.decode(strTemp);
				allTemplates.add(new BiometricTemplate(tempData));
			} catch (Exception e) {
				debug("Invalid Template #" + i, e);
			}
		}
		
		lastTemplate = null;
		lastImage = null;
		lastScanner = null;
		fingerprintViewPanel.setImage(logo);
		
		debug("Applet.resetTemplateData()");
	}

	/**
	 * Returns a {@link String} containing debug messages.<br/>
	 * <br/>
	 * Retorna uma {@link String} contendo mensagens de debug.
	 * 
	 * @return The debug messages
	 */
	public String getDebug() {
		return this.logBuffer.toString();
	}

	
	

	
	//========================================================== 
	//===================== DEBUG MESSAGES =====================
	//==========================================================
	private StringBuffer logBuffer = new StringBuffer();
	
	private void debug(String m) {
		debug(m, null);
	}
	
	private void debug(String m, Throwable t) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		this.logBuffer
			.append(f.format(Calendar.getInstance().getTime()))
			.append(" : ")
			.append(m)
			.append("\n");		
		System.err.println(m);
		
		if (t != null) {
			CharArrayWriter exceptionString = new CharArrayWriter();
			PrintWriter printWriter = new PrintWriter(exceptionString);
			t.printStackTrace(printWriter);
			this.logBuffer.append(exceptionString.toString());
			t.printStackTrace(System.err);
		}
		
		callback("onDebug");
	}
	
	
	
	
	
	//================================================================ 
	//===================== START / STOP CAPTURE =====================
	//================================================================
	
 	/**
	 * This method causes the applet to respond to the events of the capture devices.<br/>
	 * <br/>
	 * Esse método faz com que o applet comece a responder pelos eventos dos equipamentos de captura.
	 */
	public void startCapture() {
		try {
			BiometricSDK.addCaptureEventListener(this);
			debug("Capture started.");
		} catch (BiometricException e) {
			debug("Failed to start capture: " + e.toString());
		}
	}
	/**
	 * This method is the counterpart of the method <i>{@link AppletSample} .startCapture()</i> 
	 * and causes the applet to stop responding to events of the capture device.<br/>
	 * <br/>
	 * Esse método é a contrapartida do método <i>{@link AppletSample} .startCapture()</i> 
	 * e faz com que o applet pare de responder aos eventos dos equipamentos de captura.
	 */
	public void stopCapture() {
		try {
			BiometricSDK.removeCaptureEventListener(this);
			debug("Capture stopped.");
		} catch (BiometricException e) {
			debug("Failed to stop capture: " + e.toString());
		}
	}




	@Override
	public void onCaptureEvent(CaptureEventType evt, BiometricScanner bioScanner, BiometricImage img) {
		lastScanner = bioScanner;
		if (evt == CaptureEventType.PLUG) {
			debug(bioScanner + ": Plugged");
			callback("onPlug");
			try {
				bioScanner.addCaptureEventListener(this);
			} catch (BiometricException e) {}
		}
		if (evt == CaptureEventType.UNPLUG) {
			debug(bioScanner + ": Unplugged");
			callback("onUnplug");
		}
		if (evt == CaptureEventType.PLACED) {
			debug(bioScanner + ": Finger placed");
			callback("onPlaced");
		}
		if (evt == CaptureEventType.REMOVED) {
			debug(bioScanner + ": Finger removed");
			callback("onRemoved");
		}
		if (evt == CaptureEventType.IMAGE_CAPTURED) {
			debug(bioScanner + ": Image captured");
			BiometricTemplate template = new BiometricTemplate(img);
	
			boolean verifyTemplates    = Boolean.parseBoolean( getParameter("verifyTemplates"   , "false") );
			int     verifyMinThreshold = Integer.parseInt    ( getParameter("verifyMinThreshold", "20") );
			boolean captureFailed = false;
			if (verifyTemplates) {
				for (BiometricTemplate tpt : allTemplates) {
					int score = tpt.match(template);
					debug("Verifing templates: score = " + score + "; min = " + verifyMinThreshold);
					if (score < verifyMinThreshold) 
						captureFailed = true;
				}
			}
			
			fingerprintViewPanel.setImage(img);
			lastImage = img;
			lastTemplate = new BiometricTemplate(img);
			if (!captureFailed) {
				callback("onCapture");
			} else {
				debug(bioScanner + ": Image verification failed");
				callback("onBadVerify");
			}
		}
	}





	//================================================================ 
	//===================== JAVASCRIPT CALLBACKS =====================
	//================================================================
	
	/**Avoids MalformedURLExceptionbug*/
	private static final URLStreamHandler streamHandler = new URLStreamHandler() {
	    protected URLConnection openConnection(URL u) {
	        return null;
	    }
	};
	/**Calls a javascript function[s]*/
	private synchronized void callback(String callbackname) {
		if (callbackname == null) return;
		final String callback = getParameter(callbackname);
		if (callback == null) return;
		
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			@Override
			public Void run() {
				java.applet.AppletContext context = getAppletContext();
				if (context != null) {
					for (String cb : callback.split(";")) {
						try {
							URL url = new java.net.URL(null, "javascript:" + cb, streamHandler);
							System.err.println(url);
							context.showDocument(url, "_self");
						} catch (Exception e) {
							e.printStackTrace();
							//Can't use debug(): If the onDebug event is buggy, it will loop.
						}
					}
				}
				return null;
			}
		});
	}
	/**
	 * Returns an Applet parameter. If the parameter is not defines, 
	 * a default value will be used.
	 * @param name The parameter name.
	 * @param def The parameter's default value.
	 */
	private String getParameter(String name, String def) {
		String r = getParameter(name);
		return r!=null ? r : def;
	}
}
