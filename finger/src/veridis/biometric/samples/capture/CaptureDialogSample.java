package veridis.biometric.samples.capture;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import veridis.biometric.BiometricException;
import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricScanner;
import veridis.biometric.BiometricSDK;
import veridis.biometric.CaptureEventListener;
import veridis.biometric.JBiometricPanel;
import veridis.biometric.samples.util.LicenseHelper;

/**
 * This sample shows how to use a {@link JOptionPane}-like modal dialog to easily capture.
 * <p>
 * If you want a similar functionality, without windows, You might want to take a look at {@link SynchronousCaptureSample}.
 * @see SynchronousCaptureSample
 */
public class CaptureDialogSample extends JOptionPane {
	private static final long serialVersionUID = -3773122714998836754L;
	
	private BiometricImage biometricImage;
	private Dialog dialog;
	
	private CaptureEventListener listener = new CaptureEventListener() {
		public void onCaptureEvent(CaptureEventType eventType, BiometricScanner reader, BiometricImage image) {
			//Will receive images from ALL devices.
			if (eventType == CaptureEventType.PLUG)  
				reader.addCaptureEventListener(this);
		
			//Image has been captured! Stores the image and signals to stop.
			if (eventType == CaptureEventType.IMAGE_CAPTURED ) {
				biometricImage = image;
				dialog.setVisible(false);
			}
		}
	};
	
	private CaptureDialogSample(Component frame, String title) {
		super(title, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {"Cancel"});
		dialog = createDialog(frame, title);
		
		//starts capturing
		BiometricSDK.addCaptureEventListener(listener);
		
		//Shows dialog
        dialog.setVisible(true);
        dialog.dispose();
        
		//Stops capturing
		try {
			BiometricSDK.removeCaptureEventListener(listener);
		} catch (BiometricException e) {} //Don't care if an error happens.
	}
	
	/** Displays a dialog that waits a capture. 
	 * <p>
	 * It's possible to abort the capture with the "cancel" button.
	 * <p>
	 * The message displayed is "Waiting capture..."
	 * 
	 * @param frame The component that will own this dialog. null to have an unowned dialog.
	 */
	public static BiometricImage showCaptureDialog(Component frame) {
		return showCaptureDialog(frame, "Waiting capture...");
	}
	/** Displays a dialog that waits a capture. 
	 * <p>
	 * It's possible to abort the capture with the "cancel" button.
	 * 
	 * @param frame The component that will own this dialog. null to have an unowned dialog.
	 * @param title The message that will be displayed.
	 */
	public static BiometricImage showCaptureDialog(Component frame, String title) {
		return new CaptureDialogSample(frame, title).biometricImage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Main method: Creates a new frame, with a panel to show the last image captured and a button to open the capture dialog.
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Capture dialog demo");
		final JBiometricPanel imagePanel = new JBiometricPanel();
		final JButton btnCapture = new JButton("Capture a new image");
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
		frame.getContentPane().add(btnCapture, BorderLayout.SOUTH);
		frame.setPreferredSize(new Dimension(500, 400));
		btnCapture.setEnabled(false); //Will be enabled after licensing
		frame.pack();
		frame.setVisible(true);
		
		//When the user clicks "Capture", a CaptureDialog is shown and the returned image (if any) will be displayed.
		btnCapture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BiometricImage image = CaptureDialogSample.showCaptureDialog(btnCapture);
				if (image!=null) imagePanel.setImage(image);
			}
		});
		
		//Installs the license
		LicenseHelper.installLicense(frame);
		
		btnCapture.setEnabled(true); //Enabled the capture button
	}
}
