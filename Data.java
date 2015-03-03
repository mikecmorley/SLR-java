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
 * This Data class parses all of the entered data from the GUI into arrays, which are then used to create
 * DescriptiveStatistics (math commons 3) objects to compute single variable statistics. To compute the regression
 * information we combined the two arrays into a single array, and pass it into a SimpleRegression (math commons 3) object
 * for computation. We have several getter methods that allows us to get the SimpleRegression and 
 * DescriptiveStatistics objects so we can later use their methods and assign them to variables (in Analysis).
 * 
 * 
 * 
 */

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Data {

	// fields
	private double[] listx;
	private double[] listy;
	private double[][] listxy;
	private DescriptiveStatistics stats;
	private DescriptiveStatistics statsy;
	private SimpleRegression reg;

	// constructor for x-only data
	// adds the x data into a descriptivestatistics object to be processed
	public Data(double[] data) {

		listx = data;
		stats = new DescriptiveStatistics();
		stats.clear();

		for (double i : listx) {
			stats.addValue(i);
		}
	}

	// constructor for x and y data
	// adds the data into two descriptivestatistics objects to be processed
	// also adds the paired data into a regression object to be processed
	public Data(double[] data, double[] data2) {

		listx = data;
		listy = data2;
		listxy = new double[data.length][2];
		stats = new DescriptiveStatistics();
		statsy = new DescriptiveStatistics();
		reg = new SimpleRegression();
		stats.clear();
		reg.clear();

		// combining arrays
		for (int m = 0; m < listx.length; m++) {
			listxy[m][0] = listx[m];
			listxy[m][1] = listy[m];
		}

		// adds x data
		for (double i : listx) {
			stats.addValue(i);
		}

		// adds y data
		for (double j : listy) {
			statsy.addValue(j);
		}

		// adds xy data
		reg.addData(listxy);

	}

	// get the regression object
	public SimpleRegression getReg() {
		return reg;
	}

	// gets the descriptive stats object for x
	public DescriptiveStatistics getStats() {
		return stats;
	}

	// gets the descriptive stats object for y
	public DescriptiveStatistics getStatsY() {
		return statsy;
	}
}
