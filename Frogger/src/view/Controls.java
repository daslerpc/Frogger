package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Controls extends JPanel implements ItemListener, ChangeListener {
	JCheckBox svFilterDisplay;
	JCheckBox synchrosDisplay;
	JSlider stepSize;
	JButton stepB;
	JButton start;
	JButton stepF;
	
	static final int STEP_MIN = 0;
	static final int STEP_MAX = 30;
	static final int STEP_INIT = 1;    //initial step size
	
	boolean displayFilter = true;
	boolean displaySynchs = true;
	
	JLabel stepLabel;
	int timestep = 1;
	
	public Controls() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
        

        add(checkBoxes());
        add(Box.createRigidArea(new Dimension(100,200)));
        add(sliders());
        add(playControls());
        
	}

	private JPanel checkBoxes() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		svFilterDisplay = new JCheckBox("Display SV-Filter");
        synchrosDisplay = new JCheckBox("Display Synchronizers");
        
        // Default to unchecked
        svFilterDisplay.setSelected(false);
        synchrosDisplay.setSelected(false);
        
        //Register a listener for the check boxes.
        svFilterDisplay.addItemListener(this);
        synchrosDisplay.addItemListener(this);
        
        panel.add(svFilterDisplay);
        panel.add(synchrosDisplay);
		return panel;
	}

	private JPanel sliders() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		stepLabel = new JLabel("Time Step Size = 1");

		stepSize = new JSlider(JSlider.HORIZONTAL, STEP_MIN, STEP_MAX, STEP_INIT);
		stepSize.addChangeListener(this);

		//Turn on labels at major tick marks.
		stepSize.setMajorTickSpacing(10);
		stepSize.setMinorTickSpacing(1);
		stepSize.setPaintTicks(true);
		stepSize.setPaintLabels(true);
		
		
		panel.add(stepLabel);
		panel.add(stepSize);
		
		return panel;
	}
	
	private JPanel playControls() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		
        stepB = new JButton("<");
        panel.add(stepB);
        
        start = new JButton("Start Sim");
        panel.add(start);
        
        stepF = new JButton(">");
        panel.add(stepF);
        
		return panel;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getItemSelectable();
		 
        if (source == svFilterDisplay) {
            displayFilter = !displayFilter;
        }
        else if (source == synchrosDisplay) {
            displaySynchs = !displaySynchs;
        }
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		JSlider source = (JSlider) arg0.getSource();
		
	    if (!source.getValueIsAdjusting()) {
	        timestep = (int)source.getValue();   
	        stepLabel.setText("Time Step Size = " + timestep);
	    }
	}
	
}
