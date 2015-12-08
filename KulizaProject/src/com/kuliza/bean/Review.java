package com.kuliza.bean;

public class Review implements Comparable<Review>{

	
	String heading, data;
	double usefulness;
	
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getUsefulness() {
		return usefulness;
	}

	public void setUsefulness(double usefulness) {
		this.usefulness = usefulness;
	}

	@Override
	public String toString() {
		return "Review [heading=" + heading + ", data=" + data + ", usefulness=" + usefulness + "]";
	}
	@Override
	public int compareTo(Review o) {
		// TODO Auto-generated method stub
		double difference= o.getUsefulness()-this.getUsefulness();
		if(difference>0){
			return 1;
		}
		if(difference<0){
			return -1;
		}
		return 0;
	}

}
