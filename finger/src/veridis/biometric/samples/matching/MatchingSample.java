package veridis.biometric.samples.matching;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricTemplate;
import veridis.biometric.JBiometricPanel;
import veridis.biometric.samples.capture.CaptureDialogSample;
import veridis.biometric.samples.util.LicenseHelper;

/**
 * This sample is very similar to {@link CaptureDialogSample} sample, except it has 2 image containers, each one with a "Capture" button.
 * <p>
 * When both images have been captured, a "matching score" indicating how similar they are will be calculated and displayed.
 * <p>
 * A threshold is defined as {@link #MATCHING_THRESHOLD}. If the similarity is above this threshold, the images are said to have MATCHED. 
 * If it's under the threshold, the images DON'T MATCH.
 * <p>
 * A good threshold value depends on your application. If security is non-critical, a lower threshold will be desirable to minimize the amount of false-negatives.
 * On the other hand, on  security-critical applications you should use a higher threshold, to minimize false-matches. 
 */
public class MatchingSample extends JFrame {	
	private static final long serialVersionUID = -8789643203463750401L;

	/** Minimum similarity between templates to consider it a MATCH.*/
	public static final int MATCHING_THRESHOLD = 40;
	
	/**Panel to capture and display the image on the left.*/
	SubImagePanel image1 = new SubImagePanel();
	/**Panel to capture and display the image on the right.*/
	SubImagePanel image2 = new SubImagePanel();
	/**Label to display the matching score.*/
	JLabel matchingResult = new JLabel(" ", JLabel.CENTER);

	
	/** 
	 * This panel captures and displays biometric images. 
	 * It must also extract templates from the images captured, and calculate the matching scores.  
	 */
	class SubImagePanel extends JPanel {
		private static final long serialVersionUID = 1352327744439560733L;
		/**Panel where the image is displayed.*/
		JBiometricPanel imagePanel = new JBiometricPanel();
		/**Button to capture a new image.*/
		JButton btnCapture = new JButton("Capture");
		/**Last image captured.*/
		BiometricImage biometricImage = null;
		/**Template extracted from the last image captured.*/
		BiometricTemplate template = null;
		
		/**Initializes this component*/
		public SubImagePanel() {
			//Lays out the sub-components
			setLayout(new BorderLayout());
			add(imagePanel, BorderLayout.CENTER);
			add(btnCapture, BorderLayout.SOUTH);
			
			//Handles btnCapture clicking. Will get an image, extract it's template and calculate the matching score.
			//It's where all the magic is!
			btnCapture.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Uses CaptureDialog to get an image
					BiometricImage img = CaptureDialogSample.showCaptureDialog(btnCapture);
					if (img==null) return; //Operation cancelled
					
					//Displays the image captured
					imagePanel.setImage(biometricImage = img);
					//Extracts a template containing with the image's features.
					template = new BiometricTemplate(biometricImage);
					
					//Experimental feature: Displays an overlay with the features.
					try {
						imagePanel.setTemplate(template);
					} catch (Exception e) { /*Don't care*/ }
					
					
					
					//If both templates have been extracted we can do a match.
					if (image1.template != null && image2.template != null) {
						BiometricTemplate template1 = image1.template;
						BiometricTemplate template2 = image2.template;
						
						//Calculates a matching score, indicating how similar the templates are.
						int matching = template1.match(template2);
						
						//Shows the score
						matchingResult.setText("Matching Score = " + matching + ". ");
						
						//If we get a "bad" score, paint it red.
						if (matching < MATCHING_THRESHOLD) {
							matchingResult.setText("Didn't match (Score=" + matching + ")");
							matchingResult.setForeground(Color.RED);
						//If we get a "good" score, paint it green.
						} else {
							matchingResult.setText("Matched (Score=" + matching + ")");
							matchingResult.setForeground(Color.GREEN);
						}
					}
				}
			});
		}
		@Override
		/**Enabling/Disabling this panel means to enable/disable it's capture button.*/
		public void setEnabled(boolean enabled) {
			btnCapture.setEnabled(enabled);
		}
	}

	/**Creates and displays the frame.*/
	public MatchingSample() {
		super("Matching Sample");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel imagesPanel = new JPanel();
		imagesPanel.setLayout(new GridLayout(1, 0));
		imagesPanel.add(image1); image1.setEnabled(false);
		imagesPanel.add(image2); image2.setEnabled(false);
		getContentPane().add(imagesPanel, BorderLayout.CENTER);
		
		matchingResult.setFont(new Font( matchingResult.getFont().getName(), Font.BOLD, 20 ));
		getContentPane().add(matchingResult, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(500, 400));
		pack();
		setVisible(true);

		//Frame is showing, install the license.
		LicenseHelper.installLicense(this);
		
		//License has been installed -> Enable the buttons.
		image1.setEnabled(true);
		image2.setEnabled(true);
	}
	/**
	 * Main method: Shows a new {@link MatchingSample}.
	 */
	public static void main(String[] args) {
		new MatchingSample();
	}
}
