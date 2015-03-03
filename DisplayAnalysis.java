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
 * This DisplayAnalaysis class is essentially a text-formatting class that allows us to display the statistical
 * information in a nice and neat way. It can pass data to the Analysis class for computation.
 * 
 * 
 * 
 */

public class DisplayAnalysis extends Analysis {

	// constructor for x-only data
	public DisplayAnalysis(double[] data) {
		super(data);

	}

	// constructor for x and y data
	public DisplayAnalysis(double[] data, double[] data2) {
		super(data, data2);

	}

	// toString for x-only data
	// to be displayed in text area
	public String toStringx() {

		return "-------------------X variable statistics-------------------"
				+ "\n" + "\n" + "Observations (n): "
				+ xn
				+ "\n"
				+ "Min: "
				+ xmin
				+ "\n"
				+ "Max: "
				+ xmax
				+ "\n"
				+ "Mean: "
				+ mean
				+ "\n"
				+ "Median: "
				+ median
				+ "\n"
				+ "First Quartile: "
				+ quartile1
				+ "\n"
				+ "Third Quartile: "
				+ quartile2
				+ "\n"
				+ "Standard Deviation: "
				+ std
				+ "\n"
				+ "Sample Variance: "
				+ var
				+ "\n"
				+ "Sum of Squares: "
				+ xss
				+ "\n" + "Kurtosis: " + xkurt + "\n";
	}

	// toString for y-only data
	// to be displayed in text area
	public String toStringy() {

		return "\n"
				+ "-------------------Y variable statistics-------------------"
				+ "\n" + "\n" + "Observations (n): "
				+ yn
				+ "\n"
				+ "Min: "
				+ ymin
				+ "\n"
				+ "Max: "
				+ ymax
				+ "\n"
				+ "Mean: "
				+ ymean
				+ "\n"
				+ "Median: "
				+ ymedian
				+ "\n"
				+ "First Quartile: "
				+ yquartile1
				+ "\n"
				+ "Third Quartile: "
				+ yquartile2
				+ "\n"
				+ "Standard Deviartion: "
				+ ystd
				+ "\n"
				+ "Variance: "
				+ yvar
				+ "\n"
				+ "Sum of Squares: "
				+ yss
				+ "\n"
				+ "Kurtosis: " + ykurt + "\n";
	}

	// toString for x and y data
	// to be displayed in text area
	public String toStringxy() {

		return toStringx() + toStringy() + "\n"
				+ "------------------X/Y variable statistics------------------"
				+ "\n" + "\n" + "Intercept (b0): " + beta0 + "\n"
				+ "Slope (b1): " + beta1 + "\n" + "Estimated Function: "
				+ "Y = " + beta0 + " (+) " + beta1 + "X" + "\n"
				+ "Pearson Corr (r): " + corr + "\n" + "R-Sqr: " + corr2 + "\n"
				+ "SSTO: " + ssto + "\n" + "SSE: " + sse + "\n" + "SSR: " + ssr
				+ "\n" + "MSE: " + mse + "\n" + "Slope SE (s{b1}): " + sb1
				+ "\n" + "Intercept SE (s{b0}): " + sb0 + "\n"
				+ "95% CI for Slope: +/-" + b1confid + "\n"
				+ "Prob(|t|>0) for Slope: " + probb1 + "\n" + "Total Obs (n): "
				+ n;
	}
}
