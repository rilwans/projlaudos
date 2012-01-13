package veridis.biometric.samples.util;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import veridis.biometric.BiometricSDK;
import veridis.biometric.BiometricException.LicenseException;


/**
 * Helper class to license the SDK.
 * <p>
 * If you are using the FREE version of this SDK, there is no need to worry about licensing.
 * Otherwise, licensing would usually take a single line: 
 * <p>
 * {@code BiometricSDK.installLicense("MY LICENSE KEY");}
 * <p>
 * Unfortunately, the samples don't know whenever you are using the FREE license or what is you license key, . It's needed to show a dialog, test if the license entered is OK, try again, etc. That's what this class does!
 */
public class LicenseHelper {
	/**
	 * Asks the license key to the user, and use it to start the SDK

	 * If you plan to use the FREE edition, calling this method is not required at all.
	 * Also, this method can be replaced by a single line of code: BiometricSDK.installLicense("MY LICENSE KEY");
	 */
	public static void installLicense() {
		installLicense(null);
	}

	
	/**
	 * Asks the license key to the user, and use it to start the SDK.
	 * 
	 * If you plan to use the FREE edition, calling this method is not required at all.
	 * Also, this method can be replaced by a single line of code: BiometricSDK.installLicense("MY LICENSE KEY");
	 * @param parent Parent component, which will be blocked by a modal dialog.
	 */
	public static void installLicense(Component parent) {
		System.out.println(BiometricSDK.getVersion());
		File licenseFile = new File("vrlicense");
		
		try {
			String licenseText="";
			Scanner s = new Scanner(licenseFile);
			while (s.hasNextLine())
				licenseText += s.nextLine() + "\n";
			BiometricSDK.installLicense(licenseText);
			return; //SUCESS!
		} catch (Exception e) {
			//Não conseguiu utilizar uma licença salva anteriormente.
		}

		String OK_OPTION="Use license key";
		String CANCEL_OPTION="Use FREE";
		JOptionPane freeOptionPane = new JOptionPane("Enter your " + BiometricSDK.getVersion().getLibraryName() + " license key, or use FREE", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new String[] {OK_OPTION, CANCEL_OPTION} );
		freeOptionPane.setWantsInput(true);
		JDialog dialog = freeOptionPane.createDialog(parent, "Install License Key?");
		dialog.setModal(true);
		
		//Asks the user for the license key and do online licensing.
		while (true) {
			dialog.setVisible(true);
			if (freeOptionPane.getValue() != OK_OPTION) {
				System.out.println("Licensed as " + BiometricSDK.getLicense().getKey());
				return; //Self-licensed as FREE
			}
			try {
				BiometricSDK.installLicense(freeOptionPane.getInputValue().toString());
			} catch (LicenseException e) {
				JOptionPane.showMessageDialog(parent, "Licensing failed:\n" + e, "Licensing failed", JOptionPane.ERROR_MESSAGE);
				continue;
			}	
			
			//Licenciado com sucesso
			System.out.println("Licensed as " + BiometricSDK.getLicense().getKey());
			//Salva o texto da licença para a próxima vez...
			try {
				new PrintStream(licenseFile).print(BiometricSDK.getLicense().getFullText());
			} catch (IOException e) { /*Never mind...*/ }
			//Sucesso!
			return;			
		}
	}
}
