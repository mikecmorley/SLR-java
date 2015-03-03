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
 * This RegressionPlot class is responsible for generating the GUI for a regression plot.
 * Containers event handlers that will allow the user to save the plot as a PNG, and close
 * the application.
 * 
 * 
 * 
 * 
 * 
 */

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import info.monitorenter.gui.chart.traces.painters.TracePainterDisc;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class RegressionPlot extends Analysis {

	public RegressionPlot(double[] data, double[] data2) {
		super(data, data2);

		// creates the regression plot using the two arrays and displays it in a
		// new
		// frame, then adds a line (the regression line)
		final Chart2D chart = new Chart2D();
		Trace2DSimple trace = new Trace2DSimple();
		trace.setTracePainter(new TracePainterDisc());
		trace.setColor(Color.BLACK);
		trace.setName("");

		chart.addTrace(trace);

		Trace2DSimple trace2 = new Trace2DSimple();
		trace2.setColor(Color.RED);
		trace2.setName("");

		chart.addTrace(trace2);

		trace2.addPoint(xmin, regstarty);
		trace2.addPoint(xmax, regendy);

		for (int n = 0; n < xn; n++)
			trace.addPoint(xunsorted[n], yunsorted[n]);

		final JFrame frame2 = new JFrame("Regression Plot");

		// menu bar and menu options
		final JMenuBar menubar2 = new JMenuBar();
		final JMenu fileMenu2 = new JMenu("File");
		final JMenuItem quitItem2 = new JMenuItem("Quit");
		final JMenuItem save2 = new JMenuItem("Save");

		// adding menu things
		fileMenu2.add(save2);
		fileMenu2.add(quitItem2);
		menubar2.add(fileMenu2);
		frame2.setJMenuBar(menubar2);

		frame2.getContentPane().add(chart);
		frame2.setJMenuBar(menubar2);
		frame2.setSize(400, 300);
		frame2.setVisible(true);

		// actionlistener for the graph's save function
		// asks the user what to name the file
		save2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent c) {
				BufferedImage bi = chart.snapShot();
				try {
					String name2 = JOptionPane.showInputDialog(frame2,
							"Enter a name for the file: ", "Save",
							JOptionPane.PLAIN_MESSAGE);
					ImageIO.write(bi, "PNG", new File(name2 + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// action listener for the quit menu option
		quitItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent q) {
				System.exit(0);
			}
		});

	}
}
