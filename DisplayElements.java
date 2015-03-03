/*
 * CMPT184 Final Project, Section 3 with Dr. Hill
 * Written by Mike Morley
 * Tested by No One
 * 
 * Makes use of the Apache Math Commons 3 library for mathematical number crunching
 * http://www.commons.apache.org/math/index.html
 * 
 * Also makes use of the JChart2D library to create the regression plot
 * http://www.jchart2d.sourceforge.net
 * 
 * 
 * 
 * 
 * This DisplayElements class is behind all of the GUI work of the application. It handles the GUI itself,
 * and all of the action events. It allows for the GUI to save the text-based data into a text file. Creates
 * A RegressionPlot object which will display a histogram with a regression line if two variables are used.
 * 
 * 
 * 
 * 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DisplayElements {

	public DisplayElements() {

		// creates the primary frame for the GUI
		final JFrame frame = new JFrame("Statistical Analysis");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 400));
		frame.setLayout(new BorderLayout());

		// creating text fields, text area, and menu
		final JTextField textx = new JTextField("Enter X data", 10);
		final JTextField texty = new JTextField("Enter Y data (optional)", 10);
		final JTextArea outputx = new JTextArea(5, 15);
		// scroll bar settings
		final JScrollPane scroll = new JScrollPane(outputx,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// menu bar and menu options
		final JMenuBar menubar = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");
		final JMenuItem quitItem = new JMenuItem("Quit");
		final JMenuItem save = new JMenuItem("Save");

		// adding menu things
		fileMenu.add(save);
		fileMenu.add(quitItem);
		menubar.add(fileMenu);
		frame.setJMenuBar(menubar);

		// disables editing of the text area
		outputx.setEditable(false);

		// configuring where elements will be displayed in the frame
		frame.getContentPane().add(textx, BorderLayout.NORTH);
		frame.getContentPane().add(texty, BorderLayout.SOUTH);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);

		// default text for the text area
		outputx.setText("\n \n \n \n \n \n \n \n \n \n" + "\t"
				+ "Enter X and/or Y variables and hit enter key.");

		// action listener for the x text entry field
		textx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent x) {

				// pulls the entered data from the text entry field and creates
				// a string with it, a new array is used to parse the doubles
				// from the string
				String xinfo = textx.getText();
				String[] xdata = xinfo.split("[,\\s]+");
				double xdata2[] = new double[xdata.length];
				int i = 0;
				for (String s : xdata) {
					xdata2[i++] = Double.parseDouble(s);
				}

				// creates a displayanalysis object with the array of doubles
				// then displays the relevent information to the text area
				DisplayAnalysis dis = new DisplayAnalysis(xdata2);
				outputx.setText(dis.toStringx());
			}
		});

		// action listener for the y tect entry field
		texty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent y) {

				// pulls the entered data from the text entry field and creates
				// a string with it, a new array is used to parse the doubles
				// from the string
				String xinfo = textx.getText();
				String[] xdata = xinfo.split("[,\\s]+");
				double xdata2[] = new double[xdata.length];
				int i = 0;
				for (String s : xdata) {
					xdata2[i++] = Double.parseDouble(s);
				}

				// pulls the entered data from the text entry field and creates
				// a string with it, a new array is used to parse the doubles
				// from the string
				String yinfo = texty.getText();
				String[] ydata = yinfo.split("[,\\s]+");
				double ydata2[] = new double[ydata.length];
				int j = 0;
				for (String s : ydata) {
					ydata2[j++] = Double.parseDouble(s);
				}

				// checks to make sure both arrays are of the same size
				// if not, displays an error, otherwise creates a new
				// displayanalysis object
				// with the two arrays and displays the relevent information in
				// the text area
				if (xdata2.length == ydata2.length) {
					DisplayAnalysis distwo = new DisplayAnalysis(xdata2, ydata2);
					outputx.setText(distwo.toStringxy());
					RegressionPlot regplot = new RegressionPlot(xdata2, ydata2);
				}

				else
					JOptionPane
							.showMessageDialog(
									frame,
									"Amount of X variables must match the amount of Y variables.",
									"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		// action listener for the save menu option
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent s) {

				// creates a filewriter object, asks the user what they want the
				// file name to be
				// then saves the current text in the text area to that file
				// name (in the current working directory)
				FileWriter writer = null;
				try {
					String name = JOptionPane.showInputDialog(frame,
							"Enter a name for the file: ", "Save",
							JOptionPane.PLAIN_MESSAGE);
					writer = new FileWriter(name + ".txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					writer.write(outputx.getText());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// action listener for the quit menu option
		quitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent q) {
				System.exit(0);
			}
		});

		// packing and setting visibility
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		DisplayElements GUI = new DisplayElements();
	}
}
