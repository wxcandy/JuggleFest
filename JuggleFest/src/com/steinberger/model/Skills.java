package com.steinberger.model;

public class Skills {

	private int handEye;
	private int endurance;
	private int pizzazz;

	public Skills(int handEye, int endurance, int pizzazz) {
		this.handEye = handEye;
		this.endurance = endurance;
		this.pizzazz = pizzazz;
	}

	public int dotProduct(Skills qualification) {
		int handEye = qualification.getHandEye();
		int endurance = qualification.getEndurance();
		int pizzazz = qualification.getPizzazz();
		int hecDot = this.handEye * handEye;
		int endDot = this.endurance * endurance;
		int pizDot = this.pizzazz * pizzazz;
		return hecDot + endDot + pizDot;
	}

	public int getHandEye() {
		return this.handEye;
	}

	public int getEndurance() {
		return this.endurance;
	}

	public int getPizzazz() {
		return this.pizzazz;
	}

	@Override
	public String toString() {
		return "[h=" + this.handEye + ", e=" + this.endurance + ", p="
				+ this.pizzazz + "]";
	}

}
