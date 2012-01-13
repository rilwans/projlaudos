package veridis.biometric.samples.capture;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import veridis.biometric.BiometricException;
import veridis.biometric.BiometricImage;
import veridis.biometric.BiometricSDK;
import veridis.biometric.BiometricScanner;
import veridis.biometric.CaptureEventListener;
import veridis.biometric.samples.util.LicenseHelper;


/**
 * This sample shows how to use the asynchronous capture APIs to implement synchronous capture.
 * <p>
 * The implementation of this class has a pretty complex synchronization, but using it is very simple:
 * <p>
 * {@code BiometricImage image = SynchronousCaptureSample.staticCapture();}
 * <p> 

 * You can also provide a timeout to perform the capture. If nothing is captured in the meantime, {@code null} is returned.
 * <p>
 * {@code BiometricImage image = SynchronousCaptureSample.staticCapture(30000); //Waits 30s, or 30000ms}
 *  
 * <p>
 * Even more powerful, you can cancel a capture at any time, using {@link SynchronousCaptureSample#cancelCapturing()}.
 * <pre>{@code 
 * SynchronousCaptureSample synchronousCapture = new SynchronousCaptureSample();
 * ...
 * //Starts to capture
 * BiometricImage image = synchronousCapture.capture();
 * ...
 * //Cancels current capture
 * synchronousCapture.cancelCapturing();
 * }</pre>
 * 
 * <p>
 * If you are developing a windowed application, {@link CaptureDialogSample} is simpler and might suit you better.
 * 
 * @see CaptureDialogSample
 */
public class SynchronousCaptureSample {
	/** Stores the image captured */
	private BiometricImage biometricImage = null;
	
	/** This semaphore forces that {@link #capture()} won't be called simultaneously from different threads. */
	private Semaphore isCapturing = new Semaphore(1);
	
	/** 
	 * {@link #capture()} will wait in this semaphore until the operation has been canceled or an image has been captured.
	 * When any of these happens, a resource will be released. 
	  */
	private Semaphore captureFinished = new Semaphore(0);
		
	/** Handles capture events, attaching itself to all devices and finishing when an image is captured. */
	private CaptureEventListener listener = new CaptureEventListener() {
		@Override
		public void onCaptureEvent(CaptureEventType eventType, BiometricScanner reader, BiometricImage image) {
			//Will receive images from ALL devices.
			if (eventType == CaptureEventType.PLUG)  
				reader.addCaptureEventListener(this);
			
			//Image has been captured! Stores the image and signals capture() to stop.
			if (eventType == CaptureEventType.IMAGE_CAPTURED ) {
				biometricImage = image;
				captureFinished.release();
			}
		}
	};
	

	/**
	 * Cancels the current call to {@link #capture()}.
	 * <p>  
	 * Nothing happens if no capture is going on. 
	 */
	public synchronized void cancelCapturing() {
		captureFinished.release();
	}
	
	/**
	 * Initializes capture and waits until the first image acquired. The image is returned.
	 * <p>
	 * If it takes too long (longer thar {@code timeout} milliseconds), {@code null} will be returned.
	 * <p>
	 * You can also use {@link #cancelCapturing()} to cancel this operation and return {@code null}.
	 * 
	 * @param timeout Milliseconds before this operations is automatically canceled. Use 0 to wait forever.
	 * @return The image acquired, or {@code null} if the operation was canceled or has timed-out.
	 * @throws InterruptedException 
	 */
	public BiometricImage capture(long timeout) throws InterruptedException {
		try {
			isCapturing.acquire();   //Evita que ESTE MÉTODO seja chamado simultaneamente por várias threads.
		} catch (InterruptedException e) { return null; }
		
		try {
			//Start to capture: Symmetric to stopCapturing()
			synchronized (this) {
				captureFinished.drainPermits(); // I'm not finished yet. Just started, actually.			
				BiometricSDK.addCaptureEventListener(listener); //Starts to receive events				
			}
			
			//Waits until the capture has finished: (successfully, canceled or timed-out)
			if (timeout > 0)
				captureFinished.tryAcquire(timeout, TimeUnit.MILLISECONDS);
			else
				captureFinished.acquire();
			
			return biometricImage; //will be NULL if it has been canceled or has timed-out.
			
		
		//InterruptedException: returns null, just like if stopCapture had been called.
		} finally {
			//Stops capturing
			try {
				BiometricSDK.removeCaptureEventListener(listener);
			} catch (BiometricException e) {} //Don't care if an error happens.
			
			biometricImage = null; //Won't need it anymore.
			
			isCapturing.release(); //Method finished.
		}
	}
	

	/**
	 * Initializes capture and waits until the first image acquired. The image is returned.
	 * <p>
	 * You can also use {@link #cancelCapturing()} to cancel this operation and return {@code null}.
	 * 
	 * @return The image acquired, or {@code null} if the operation was canceled.
	 * @throws InterruptedException 
	 * @see #capture(long)
	 */
	public BiometricImage capture() throws InterruptedException {
		return capture(0);
	}

	/**
	 * Initializes capture and waits until the first image acquired. The image is returned.
	 * <p>
	 * If it takes too long (longer thar {@code timeout} milliseconds), {@code null} will be returned.
	 * <p>
	 * This is the same as {@link #capture(long)}, but it's not needed explicitly instantiate a {@link SynchronousCaptureSample}.
	 * As a side-effect, it's impossible to cancel the operation.
	 * 
	 * @param timeout Milliseconds before this operations is automatically canceled. Use 0 to wait forever.
	 * @return The image acquired, or {@code null} if the operation has timed-out.
	 * @throws InterruptedException 
	 * @see #capture(long)
	 */
	public static BiometricImage staticCapture(long timeout) throws InterruptedException {
		return new SynchronousCaptureSample().capture(timeout);
	}

	/**
	 * Initializes capture and waits until the first image acquired. The image is returned.
	 * <p>
	 * This is the same as {@link #capture()}, but it's not needed explicitly instantiate a {@link SynchronousCaptureSample}.
	 * As a side-effect, it's impossible to cancel the operation.
	 * 
	 * @return The image acquired.
	 * @throws InterruptedException 
	 * @see #capture(long)
	 */
	public static BiometricImage staticCapture() throws InterruptedException {
		return new SynchronousCaptureSample().capture();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//========================  Test Application =============================
	public static void main(String[] args) throws InterruptedException {
		LicenseHelper.installLicense();
		
		//Captures synchronously ====================================================
		final SynchronousCaptureSample capture = new SynchronousCaptureSample();
		final boolean exit[] = new boolean[1]; //Exit flag 
		
		//This thread receives interrupts from System.in. 
		//Every time a new line is typed, the current capture will be canceled.
		new Thread() {
			public void run() {
				Scanner in = new Scanner(System.in);
				while (!exit[0]) {
					//Signals the main loop to exit
					if (in.nextLine().equals("exit"))
						exit[0] = true;
					capture.cancelCapturing();
				}
			};
		}.start();
		
		
		//Captures forever. 
		//Each capture timeouts after 15s. 
		//A small delay allows you to see all devices turning on and off after each call. 
		while (!exit[0]) {
			System.out.println("capture: " + capture.capture(15000) );
			try{
				Thread.sleep(500);
			} catch (Exception e) {}
		}
	}
}
