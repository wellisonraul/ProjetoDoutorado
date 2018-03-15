package br.ufpe.activiti.behaviour.drools;

public class DroolsEncoding {
	
	public static final double EXISTENCEWITHOUTPROBLEMS = 0.9;
	public static final double PROBLEMATICEXISTENCE = 0.8;
	public static final double ABSENCEWITHOUTPROBLEMS = 0.9;
	public static final double STARTWITHOUTPROBLEMS = 0.93;

	
    private double promValue;
    private String propertyType;
    private String activityName;

	public double getPromValue() {
		return promValue;
	}

	public void setPromValue(double promValue) {
		this.promValue = promValue;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
    
}