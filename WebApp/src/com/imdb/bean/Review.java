package com.imdb.bean;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class Review. - Bean class to store the details of the review.
 */
@Component
public class Review implements Comparable<Review>{

	
	/** to store the review details */
	String heading, data, userInfo;
	
	/** to store the usefulness percentage of the review. */
	double usefulness;
	
	/**
	 * Gets the user info.
	 *
	 * @return the user info
	 */
	public String getUserInfo() {
		return userInfo;
	}
	
	/**
	 * Sets the user info.
	 *
	 * @param userInfo the new user info
	 */
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	
	/**
	 * Gets the heading.
	 *
	 * @return the heading
	 */
	public String getHeading() {
		return heading;
	}
	
	/**
	 * Sets the heading.
	 *
	 * @param heading the new heading
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the usefulness.
	 *
	 * @return the usefulness
	 */
	public double getUsefulness() {
		return usefulness;
	}

	/**
	 * Sets the usefulness.
	 *
	 * @param usefulness the new usefulness
	 */
	public void setUsefulness(double usefulness) {
		this.usefulness = usefulness;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Review [heading=" + heading + ", data=" + data + ", userInfo=" + userInfo + ", usefulness=" + usefulness
				+ "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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
