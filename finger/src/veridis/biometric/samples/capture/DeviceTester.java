package veridis.biometric.samples.capture;

import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricSDK;
import veridis.biometric.BiometricScanner;
import veridis.biometric.CaptureEventListener;
import veridis.biometric.JBiometricPanel;
import veridis.biometric.samples.util.LicenseHelper;

@SuppressWarnings("serial")
public class DeviceTester extends JFrame implements CaptureEventListener {
	JBiometricPanel imagePanel = new JBiometricPanel();
	public DeviceTester(BiometricScanner scanner) {
		super(scanner.getName());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BiometricSDK.addCaptureEventListener(this);
		scanner.addCaptureEventListener(this);
		
		getContentPane().add(imagePanel);
		pack();
	}
	
	public void onCaptureEvent(veridis.biometric.CaptureEventListener.CaptureEventType eventType, BiometricScanner scanner, veridis.biometric.BiometricImage image) {
		if (image != null) {
			imagePanel.setImage(image);
			pack();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		LicenseHelper.installLicense();
		BiometricSDK.addCaptureEventListener(new CaptureEventListener() {
			@Override
			public void onCaptureEvent(CaptureEventType eventType, BiometricScanner scanner, BiometricImage image) {
				if (eventType == CaptureEventType.PLUG)
					new DeviceTester(scanner).setVisible(true);
			}
		});
		//Never return from Main()
		new Semaphore(0).acquire();
	}
}
