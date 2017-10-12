package traceReadWrite;

import utility.Constants;

public class Trace {
	public long time;
	public double [] values = null;
	public int dim;
	public double degree = 0.0;
	
	public int index = -1;
	
	public static String ACCELEROMETER = "accelerometer";
	public static String GYROSCOPE = "gyroscope";
	public static String ROTATION_MATRIX = "rotation_matrix";
	public static String GPS = "gps";
	

	public Trace() {
		index = 0;
		time = 0;
		dim = 3;
		values = new double [dim];
	}
	
	public Trace(int d) {
		time = 0;
		dim = d;
		values = new double [dim];
	}
	
	public void setValues(double x, double y, double z) {
		values[0] = x;
		values[1] = y;
		values[2] = z;
		dim = 3;
	}
	
	/**
	 * Set the index of the trace in the entire trace list
	 * @param index
	 */
	public void setTraceIndex(int index)
	{
		this.index = index;
	}
	
	public void setDegreesWithXY()
	{
		if (dim < 2)
		{
			throw new IllegalArgumentException("The dimension of the trace is not correct!");
		}
		this.degree = Math.asin(this.values[0] / Math.sqrt(Math.pow(this.values[0], 2) + Math.pow(this.values[1], 2)));
	}
	public void copyTrace(Trace trace) {
		this.time = trace.time;
		this.dim = trace.dim;
		this.values = new double[dim];
		this.index = trace.index;
		for(int i = 0; i < dim; ++i) {
			this.values[i] = trace.values[i];
		}
	}
	
	public void getTrace(String line) {
		
		String[] res = line.split(Constants.kInputSeperator);
		try {
			time = Long.parseLong(res[0]);
			//time = (long) (Double.parseDouble(res[0]) * 1000.0);
		} catch (NumberFormatException e) {
			//handle error time format
			time = 0;
		}
		//Log.log(dim, line);
		for(int i = 0; i < dim; ++i) {
			try {
				values[i] = Double.parseDouble(res[i + 1]);	
			} catch (NumberFormatException e) {
				values[i] = 0.0;
			}
		}
		
		//System.out.println(time + Constants.kSeperator + values[0]);
	}

	public String toString() {
		String res = new String("");
		res = res.concat(String.valueOf(time));
		for(int i = 0; i < dim; ++i) {
			res = res.concat(Constants.kOutputSeperator + String.valueOf(values[i]));
		}
		return res;
	}

}