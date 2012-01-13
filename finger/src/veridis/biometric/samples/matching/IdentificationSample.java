package veridis.biometric.samples.matching;

import java.util.ArrayList;

import veridis.biometric.BiometricIdentification;
import veridis.biometric.BiometricTemplate;
import veridis.biometric.samples.capture.SynchronousCaptureSample;
import veridis.biometric.samples.util.LicenseHelper;

public class IdentificationSample {
	public static void main(String[] args) throws InterruptedException {
		LicenseHelper.installLicense();
		
		ArrayList<BiometricTemplate> templates = new ArrayList<BiometricTemplate>();
		while (true) {
			templates.add(new BiometricTemplate(SynchronousCaptureSample.staticCapture()));
			
			for (BiometricTemplate a : templates) {
				BiometricIdentification context = a.identify();
				for (BiometricTemplate b : templates) {
					System.out.print(context.match(b) + "\t");
				}
				System.out.println();
			}
		}
	}
}
