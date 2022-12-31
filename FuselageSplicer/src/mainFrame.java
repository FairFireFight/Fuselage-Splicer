/*
 *Made by FairFireFight in a basement at 11pm dec 31st 2022
 *all rights are reserved or smth idk legal stuff lol
 * */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;


public class mainFrame extends JFrame implements ActionListener{

	//i dont fucking know what this is for but the compiler kept throwing a warning at me when i removed it
	private static final long serialVersionUID = 1L;

	//color constants
	final Color FRAME_COLOR = new Color(0x2f2f2f);
	final Color PANEL_COLOR = new Color(0x333333);
	
	JPanel inputPanel;
	
	// input fields
	JSpinner lengthField;
	JSpinner riseField;
	JSpinner runField;
	
	JSpinner frontWidthField;
	JSpinner frontHeightField;
	JSpinner rearWidthField;
	JSpinner rearHeightField;
	JSpinner sliceCount;
	
	JCheckBox preventZFighting;
	
	JButton calculate;
	JButton help;
	//output
	JScrollPane scrollingPane;
	JTextArea outputField;
	
	mainFrame(){
		// --------- setting up frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Fuselage Splicer");
		this.setSize(640, 450);
		this.getContentPane().setBackground(FRAME_COLOR);
		// ----------- input panel setup
		inputPanel = new JPanel();
		inputPanel.setBounds(25, 25, 250, 350);
		inputPanel.setBackground(PANEL_COLOR);
		
		//--------- calculate button
		calculate = new JButton("Calculate!");
		calculate.setPreferredSize(new Dimension(125, 50));
		calculate.addActionListener(this);
		calculate.setFocusable(false);
		calculate.setBackground(FRAME_COLOR);
		calculate.setForeground(Color.white);
		calculate.setFont(new Font("Arial", Font.BOLD, 16));
		//help button
		help = new JButton("Help");
		help.setPreferredSize(new Dimension(75, 50));
		help.addActionListener(this);
		help.setFocusable(false);
		help.setBackground(FRAME_COLOR);
		help.setForeground(Color.white);
		help.setFont(new Font("Arial", Font.BOLD, 16));
		
		//--------- camo checkbox
		preventZFighting = new JCheckBox();
		preventZFighting.setPreferredSize(new Dimension(200, 20));
		preventZFighting.setFont(new Font("Arial", Font.BOLD, 14));
		preventZFighting.setText("Prevent Z-Fighting");
		preventZFighting.setBackground(PANEL_COLOR);
		preventZFighting.setFocusable(false);
		preventZFighting.setForeground(new Color(0xFFFFFF));
		
		//--------- input fields
		int width = 100;
		int height = 28;
			//--------- input labels
			JLabel[] labels = new JLabel[8];  //0 = length ... 7 = sliceCount, in order
			
			for(int i = 0; i < 8; i++) {
				labels[i] = new JLabel();
				labels[i].setForeground(Color.white);
				labels[i].setPreferredSize(new Dimension(width, height));
				labels[i].setFont(new Font("Arial", Font.BOLD, 15));
			}
			
			labels[0].setText("Length");
			labels[1].setText("Rise");
			labels[2].setText("Run");
			labels[3].setText("Front Width");
			labels[4].setText("Front Height");
			labels[5].setText("Rear Width");
			labels[6].setText("Rear Height");
			labels[7].setText("Splice Count");
			// :ded_vibe:

		lengthField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		lengthField.setPreferredSize(new Dimension(width, height));
		
		riseField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		riseField.setPreferredSize(new Dimension(width, height));
		
		runField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		runField.setPreferredSize(new Dimension(width, height));
		
		frontWidthField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		frontWidthField.setPreferredSize(new Dimension(width, height));
		
		frontHeightField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		frontHeightField.setPreferredSize(new Dimension(width, height));
		
		rearWidthField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		rearWidthField.setPreferredSize(new Dimension(width, height));
		
		rearHeightField = new JSpinner(new SpinnerNumberModel(0.0, -1000.0 ,1000.0, 0.25));
		rearHeightField.setPreferredSize(new Dimension(width, height));
		
		sliceCount = new JSpinner(new SpinnerNumberModel(2, 2, 50, 1));
		sliceCount.setPreferredSize(new Dimension(width, height));
		
		
		//--------- output pane
		outputField = new JTextArea("Output goes here...");
		outputField.setEditable(false);
		outputField.setBackground(PANEL_COLOR);
		outputField.setBorder(null);
		outputField.setForeground(Color.white);
		outputField.setFont(new Font("Arial", Font.PLAIN, 16));
		
		scrollingPane = new JScrollPane(outputField);
		scrollingPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollingPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollingPane.setBounds(300, 25, 300, 350);
		scrollingPane.setBorder(null);
		
		// --------- input panel additions
		inputPanel.add(labels[0]);
		inputPanel.add(lengthField);
		
		inputPanel.add(labels[1]);
		inputPanel.add(riseField);
		
		inputPanel.add(labels[2]);
		inputPanel.add(runField);
		
		inputPanel.add(labels[3]);
		inputPanel.add(frontWidthField);
		
		inputPanel.add(labels[4]);
		inputPanel.add(frontHeightField);
		
		inputPanel.add(labels[5]);
		inputPanel.add(rearWidthField);
		
		inputPanel.add(labels[6]);
		inputPanel.add(rearHeightField);
		
		inputPanel.add(labels[7]);
		inputPanel.add(sliceCount);
		
		inputPanel.add(preventZFighting);
		inputPanel.add(calculate);
		inputPanel.add(help);
		// --------- frame additions
		this.add(inputPanel);
		this.add(scrollingPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == calculate) {
			
			try { //commit to edits
				lengthField.commitEdit();
				riseField.commitEdit();
				runField.commitEdit();
				 
				frontWidthField.commitEdit();
				frontHeightField.commitEdit();
				rearWidthField.commitEdit();
				rearHeightField.commitEdit();
				
				sliceCount.commitEdit();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "INVALID INPUTS\n PLEASE CHECK YOUR INPUTS AND TRY AGAIN", 
						"INPUT ERROR", 
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//required variables
			int slices = (int) sliceCount.getValue();
			
			double length = (double) lengthField.getValue();
			double rise = (double) riseField.getValue();
			double run = (double) runField.getValue();
			double offset = 0.0;
			double percent = 0;
					
			double widthFront = (double) frontWidthField.getValue();
			double heightFront = (double) frontHeightField.getValue();
			double widthRear = (double) rearWidthField.getValue();
			double heightRear = (double) rearHeightField.getValue();
			
			//calculation variables
			double slicePercent;
			String output;
			DecimalFormat parseDecimal = new DecimalFormat("0.000");
			
			//z fighting prevention
			if (preventZFighting.isSelected()) {
				offset = 0.004;
			}
			
			output = "Length Per Splice: " + parseDecimal.format(length / slices) +
					"\nRise per Splice: " + parseDecimal.format(rise / slices) +
					"\nRun per Splice: " + parseDecimal.format(run/slices) + "\n\n";
			
			slicePercent = 1.0 / slices;
			for (int sliceCycle = 1; sliceCycle <= slices; sliceCycle++) {
				output +=  "---------Fuselage #" + sliceCycle + "---------\n";
				output += "Splice Front Width : " + parseDecimal.format((widthFront * (1 - percent) + widthRear * percent) + offset ) + "\n";
				output += "Splice Front Height : " + parseDecimal.format((heightFront * (1 - percent) + heightRear * percent) + offset ) + "\n";
				percent += slicePercent;
				output += "Splice Rear Width : " + parseDecimal.format(((widthFront * (1 - percent)) + widthRear * percent) + offset ) + "\n";
				output += "Splice Rear Height : " + parseDecimal.format(((heightFront * (1 - percent)) + heightRear * percent) + offset ) + "\n\n";
			}
			outputField.setText(output);
		}
		else if (event.getSource() == help) {
			String helpText = 
					"Slice Count: the number of slices the fuselage will become.\n\nPrevent Z-Fighting: allows you to put the generated slices over the original\nfuselage without flickering textures, useful for making camos.\n\nMade by FairFireFight, Version 1.0";
					
			JOptionPane.showMessageDialog(null, helpText, "How to Use", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "If you see this message please contact FairFireFight#0222 on discord", "FATAL EXCEPTION", JOptionPane.ERROR_MESSAGE);
			System.exit(ABORT);
		}
	}
}
