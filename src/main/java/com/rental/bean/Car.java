/**
 * 
 */
package com.rental.bean;

/**
 * @author neerupa
 *
 */
public class Car {
	private String make;
	private String model;
	private String vin;
	private Metadata metadata;
	private RentperDay perdayrent;
	private Metrics metrics;
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public RentperDay getPerdayrent() {
		return perdayrent;
	}
	public void setPerdayrent(RentperDay perdayrent) {
		this.perdayrent = perdayrent;
	}
	public Metrics getMetrics() {
		return metrics;
	}
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
	

}
