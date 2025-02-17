package com.rays.pro4.Bean;

import java.util.Date;

public class RouteBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String Name;
	private Date StartDate;
	private int AllowedSpeed;
	private String VehicleId;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public int getAllowedSpeed() {
		return AllowedSpeed;
	}

	public void setAllowedSpeed(int allowedSpeed) {
		AllowedSpeed = allowedSpeed;
	}

	public String getVehicleId() {
		return VehicleId;
	}

	public void setVehicleId(String vehicleId) {
		VehicleId = vehicleId;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
