package main;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import view.Controls;
import view.Display;


public class Applet extends JApplet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Called when this applet is loaded into the browser.
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	// Move window to middle-ish of screen
                	// This should probably be removed for web version
                	Container c = getParent();
                    while (c.getParent()!=null) {
                        c = c.getParent();
                    }
                    if (c instanceof Window) {
                    	c.setLocation(300, 100);
                    }
                    //end move
                    
                    // Resize window
                    // Does this need to be removed for web version?
                    setSize(1000, 600);

                	setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
                	
                    Controls controls = new Controls();
                    
                    controls.setMinimumSize(new Dimension(300, Short.MAX_VALUE));
                    controls.setPreferredSize(new Dimension(300, Short.MAX_VALUE));
                    controls.setMaximumSize(new Dimension(300, Short.MAX_VALUE));
    
                    add(Display.getInstance());
                    add(controls);
                    
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
    
    
}