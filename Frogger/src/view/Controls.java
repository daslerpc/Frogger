package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class Controls extends JTabbedPane implements ItemListener {
	JCheckBox svFilterDisplay;
	JCheckBox synchrosDisplay;
	
	Controls() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
		
		JComponent panel1 = new JPanel(false);
        panel1.setLayout(new GridLayout(3, 1));
        
        
        svFilterDisplay = new JCheckBox("Display SV-Filter");
        synchrosDisplay = new JCheckBox("Display Synchronizers");
        
        svFilterDisplay.setSelected(false);
        synchrosDisplay.setSelected(false);
        
      //Register a listener for the check boxes.
        svFilterDisplay.addItemListener(this);
        synchrosDisplay.addItemListener(this);
        
        panel1.add(svFilterDisplay);
        panel1.add(synchrosDisplay);
        
        
        JButton start = new JButton("Start Sim");
        panel1.add(start);
        
        addTab("3-SAT Reduction", null, panel1, "A reduction of 3-SAT to the traffic crossing problem.");
		
		
		
		
		
		
		
		JComponent panel2 = new JPanel(false);
        panel2.setLayout(new GridLayout(1, 1));
        addTab("Traffic Crossing", null, panel2, "Simulation of the traffic crossing problem.");
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getItemSelectable();
		 
        if (source == svFilterDisplay) {
            if (arg0.getStateChange() == ItemEvent.SELECTED) {

            } else {
            	
            }
        }
        else if (source == synchrosDisplay) {
            if (arg0.getStateChange() == ItemEvent.SELECTED) {

            } else {
            	
            }
        }
	}
	
}
