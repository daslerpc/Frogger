package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Sim;



public class Controls extends JPanel implements ItemListener, ChangeListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JCheckBox svFilterDisplay;
	JCheckBox synchrosDisplay;
	JSlider stepSize;
	JButton stepB;
	JButton start;
	JButton stepF;
	
	JButton addTVar;
	JButton addFVar;
	JButton addRandVar;
	JButton addClause;
	
	JButton zoomIn;
	JButton zoomOut;
	JButton center;
	JButton fit;
	
	static final int STEP_MIN = 0;
	static final int STEP_MAX = 30;
	static final int STEP_INIT = 1;    //initial step size
	
	boolean displayFilter = true;
	boolean displaySynchs = true;
	
	JLabel stepLabel;
	int timestep = 1;
	
	boolean play = false;
	
	public Controls() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.LIGHT_GRAY);
        

		add(problemDefinitionControls());
		add(displayControls());
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
        stepB.addActionListener(this);
        panel.add(stepB);
        
        start = new JButton(" Start Sim ");
        start.addActionListener(this);
        panel.add(start);
        
        stepF = new JButton(">");
        stepF.addActionListener(this);
        panel.add(stepF);
        
		return panel;
	}
	
	private JPanel problemDefinitionControls() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		addFVar = new JButton("Add False Variable");
		addFVar.setAlignmentX(Component.CENTER_ALIGNMENT);;
        addFVar.addActionListener(this);
        panel.add(addFVar);
        
        addRandVar = new JButton("Add Random Variable");
        addRandVar.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRandVar.addActionListener(this);
        panel.add(addRandVar);
        
        addTVar = new JButton("Add True Variable");
        addTVar.setAlignmentX(Component.CENTER_ALIGNMENT);
        addTVar.addActionListener(this);
        panel.add(addTVar);
        
        return panel;
	}

	private JPanel displayControls() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		zoomIn = new JButton("Zoom In");
        zoomIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        zoomIn.addActionListener(this);
        panel.add(zoomIn);
        
        zoomOut = new JButton("Zoom Out");
        zoomOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        zoomOut.addActionListener(this);
        panel.add(zoomOut);
        
        center = new JButton("Center View");
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.addActionListener(this);
        panel.add(center);
        
        fit = new JButton("Zoom to Fit");
        fit.setAlignmentX(Component.CENTER_ALIGNMENT);
        fit.addActionListener(this);
        panel.add(fit);
        
        return panel;
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getItemSelectable();
		 
        if (source == svFilterDisplay) {
            displayFilter = !displayFilter;
        } else if (source == synchrosDisplay) {
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source == stepB) {
			Sim.getInstance().stepSim(-timestep);
			Display.getInstance().repaint();
		} else if (source == start) {
			play = !play;
			if (play) {
				start.setText("Pause Sim");
			} else {
				start.setText(" Start Sim ");
			}
		} else if (source == stepF) {
			Sim.getInstance().stepSim(timestep);
			Display.getInstance().repaint();
		} else if (source == addFVar) {
			Sim.getInstance().addVariable(false);
		} else if (source == addRandVar) {
			Sim.getInstance().addVariable();
		} else if (source == addTVar) {
			Sim.getInstance().addVariable(true);
		} else if (source == addClause) {
			Sim.getInstance().addClause();
		} else if (source == zoomIn) {
		} else if (source == zoomOut) {
		} else if (source == center) {
			Display.getInstance().center();
		} else if (source == fit) {
		}
	}
	
}
