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
 * This Analysis class contains all the necessary variables and makes use of the math commons 3 library to handle
 * all of the the calculations. It is an abstract class so that our DisplayAnalysis class can make use of all of the variables.
 * Uses a Data object, which preps the data for calculation.
 * 
 * 
 * 
 * 
 */

public abstract class Analysis {

	protected double mean, median, quartile1, quartile2, std, var, beta0,
			beta1, corr, corr2, ssto, sse, ssr, mse, xss, n, ymean, ymedian,
			yquartile1, yquartile2, ystd, yvar, yss, sb0, sb1, b1confid,
			probb1, xkurt, xmin, xmax, xn, ykurt, ymin, ymax, yn, regstarty,
			regendy;

	protected double[] xunsorted, yunsorted;

	// constructor for x-only data
	// assigns values to the variables
	public Analysis(double[] data) {

		Data info = new Data(data);

		mean = info.getStats().getMean();

		median = info.getStats().getPercentile(50);

		quartile1 = info.getStats().getPercentile(25);

		quartile2 = info.getStats().getPercentile(75);

		std = info.getStats().getStandardDeviation();

		var = info.getStats().getVariance();

		xss = info.getStats().getSumsq();

		xkurt = info.getStats().getKurtosis();

		xmin = info.getStats().getMin();

		xmax = info.getStats().getMax();

		xn = info.getStats().getN();

	}

	// constructor for x and y data
	// assigns values to the variables
	public Analysis(double[] data, double[] data2) {

		Data info = new Data(data, data2);

		mean = info.getStats().getMean();

		median = info.getStats().getPercentile(50);

		quartile1 = info.getStats().getPercentile(25);

		quartile2 = info.getStats().getPercentile(75);

		std = info.getStats().getStandardDeviation();

		var = info.getStats().getVariance();

		xss = info.getStats().getSumsq();

		xkurt = info.getStats().getKurtosis();

		xmin = info.getStats().getMin();

		xmax = info.getStats().getMax();

		xn = info.getStats().getN();

		xunsorted = info.getStats().getValues();

		ymean = info.getStatsY().getMean();

		ymedian = info.getStatsY().getPercentile(50);

		ymin = info.getStatsY().getMin();

		ykurt = info.getStatsY().getKurtosis();

		ymax = info.getStatsY().getMax();

		yn = info.getStatsY().getN();

		yquartile1 = info.getStatsY().getPercentile(25);

		yquartile2 = info.getStatsY().getPercentile(75);

		ystd = info.getStatsY().getStandardDeviation();

		yvar = info.getStatsY().getVariance();

		yss = info.getStatsY().getSumsq();

		yunsorted = info.getStatsY().getValues();

		beta0 = info.getReg().getIntercept();

		beta1 = info.getReg().getSlope();

		corr = info.getReg().getR();

		corr2 = info.getReg().getRSquare();

		ssto = info.getReg().getTotalSumSquares();

		sse = info.getReg().getSumSquaredErrors();

		ssr = info.getReg().getRegressionSumSquares();

		mse = info.getReg().getMeanSquareError();

		n = info.getReg().getN();

		sb0 = info.getReg().getInterceptStdErr();

		sb1 = info.getReg().getSlopeStdErr();

		b1confid = info.getReg().getSlopeConfidenceInterval(.05);

		probb1 = info.getReg().getSignificance();

		regstarty = info.getReg().predict(xmin);

		regendy = info.getReg().predict(xmax);

	}
}
