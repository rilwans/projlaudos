package veridis.biometric.samples.capture;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import veridis.biometric.BiometricException;
import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricSDK;
import veridis.biometric.BiometricScanner;
import veridis.biometric.CaptureEventListener;
import veridis.biometric.JBiometricPanel;
import veridis.biometric.samples.util.LicenseHelper;

/**
 * This sample shows how to use the {@link CaptureEventListener} to receive events from capture devices.
 * <p>
 * All events are logged, and the last image acquired is displayed in a {@link JBiometricPanel}.
 */
public class SimpleCaptureSample extends JFrame implements CaptureEventListener {
	private static final long serialVersionUID = 4938942000824283513L;
	
	/** Text area where messages will be displayed */
	JTextPane logPanel = new JTextPane();
	/** Scroll pane where the text area is*/
	JScrollPane scrollPane = new JScrollPane(logPanel);
	/** Component where the last frame will be displayed */
	JBiometricPanel framePanel = new JBiometricPanel();
	/** Component where the last image acquired will be displayed */
	JBiometricPanel imagePanel = new JBiometricPanel();
	
	CaptureEventListener listener = this;
	
	public SimpleCaptureSample() {
		super("Simple Capture Sample - Veridis Biometric SDK");
		
		//Initialize the component: ========================================== 
		//A text box with the messages in the center, and the last image acquired in the right
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(2, 1, 2, 2));
		imagePanel.setPreferredSize(new Dimension(350, 350));
		framePanel.setPreferredSize(new Dimension(350, 350));
		right.add(imagePanel);
		right.add(framePanel);
		imagePanel.setBackground(Color.WHITE);
		framePanel.setBackground(Color.WHITE);
		getContentPane().add(right, BorderLayout.EAST);
		scrollPane.setPreferredSize(new Dimension(640, 480));
		pack();
		setResizable(false);
		setVisible(true);
		
		LicenseHelper.installLicense(this);
		
		//When closing, stops capturing and then disposes the window===========
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				BiometricSDK.removeCaptureEventListener(listener);
				dispose();
			}
		});
		//Starts capturing ====================================================
		BiometricSDK.addCaptureEventListener(this);
	}
	
	/** Appends the specified message to the log */
	void log(String message) {
		System.out.println(message);
		try {
			logPanel.getDocument().insertString(logPanel.getDocument().getLength(), message+"\n", null);
		} catch (BadLocationException e) {}
	}
	
	public void onCaptureEvent(CaptureEventType eventType, BiometricScanner reader, BiometricImage image) {
		//Prints the event, including the image size and resolution, if a image is available
		if (image != null)
			log(reader + ": " + eventType + "  => " + image);
		else
			log(reader + ": " + eventType);
		
		
		switch (eventType) {
			//Device plugged. I want to receive images from it.
			case PLUG: {
				try {
					reader.addCaptureEventListener(this);
				} catch (BiometricException e) {
					log("Cannot start device " + reader + ": " + e);
				}
				break;
			}
			//Device unplugged.
			//It might be nice to display it on your UI, but no action is required.
			case UNPLUG: break;
			
			
			//The biometric feature has been placed on the scanner. 
			//It might be nice to display it on your UI, but no action is required.
			case PLACED: break;
			//The biometric feature has been removed from the scanner.
			//It might be nice to display it on your UI, but no action is required.
			case REMOVED: break;
			
			
			//A image frame has been received. 
			//It might be nice to display it on your UI, but no action is required.
			case IMAGE_FRAME: 
				framePanel.setImage(image);
				pack(); //Refresh layout
				break;
			//A 'final' image has been captured. THIS is the image that we must handle! 
			case IMAGE_CAPTURED: {
				imagePanel.setImage(image);
				pack(); //Refresh layout
				break;
			}
		}
	}
	
	
	
	
	
	/** Main method: run a new {@link SimpleCaptureSample}. */
	public static void main(String[] args) {
		new SimpleCaptureSample();
	}
}
