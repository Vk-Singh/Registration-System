// Import awt.Event classes
import java.awt.event.*;

/** This class extends WindowAdapter class to define steps to perform while closing JFrame Window
 * Reference: https://docs.oracle.com/javase/7/docs/api/java/awt/event/WindowAdapter.html
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class JFrameClose extends WindowAdapter {
	private CompetitorList compList;
	
	// Constructor
	public JFrameClose(CompetitorList compList) {
		this.compList =compList;
	}
	
	/**
	 * Method to define steps which are performed while closing JFrame window
	 * Generates report
	 * @param e: window event
	 * @return void
	 */
	@Override
    public void windowClosing(WindowEvent e) {
    	compList.generateReport();
        System.exit(0);
    }
}