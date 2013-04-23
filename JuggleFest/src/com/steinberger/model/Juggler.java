package com.steinberger.model;

import java.util.Arrays;

public class Juggler implements Comparable<Juggler> {

	private int id;
	private Skills skills;

	private boolean isMatched;
	private int[] preferences;
	private int[] scores;

	private int currentRank = 0;
	private int currentCircuit = -1;
	private int currentScore = -1;
	private int preferenceIndex = -1;

	public Juggler(int id, Skills skills, int numRanks) {
		this.id = id;
		this.skills = skills;
		this.preferences = new int[numRanks];
		this.scores = new int[numRanks];
	}

	public void addCircuit(Circuit circuit) {

		int preference = circuit.getId();
		this.preferences[this.currentRank] = preference;

		Skills skills = circuit.getSkills();
		int score = this.skills.dotProduct(skills);
		this.scores[this.currentRank] = score;

		this.currentRank++;

	}

	public int getId() {
		return this.id;
	}

	public Skills getSkills() {
		return this.skills;
	}

	public boolean isMatched() {
		return this.isMatched;
	}

	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

	public int[] getPreferences() {
		return this.preferences;
	}

	public int[] getScores() {
		return this.scores;
	}

	public int getCurrentCircuit() {
		return this.currentCircuit;
	}

	public void setCurrentCircuit(int currentCircuit) {
		this.currentCircuit = currentCircuit;
	}

	public int getCurrentScore() {
		return this.currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getPreferenceIndex() {
		return this.preferenceIndex;
	}

	public void setPreferenceIndex(int preferenceIndex) {
		this.preferenceIndex = preferenceIndex;
	}

	@Override
	public int compareTo(Juggler juggler) {
		if (this.currentScore < juggler.getCurrentScore())
			return 1;
		else if (this.currentScore == juggler.getCurrentScore())
			return 0;
		else
			return -1;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\tJuggler J" + this.id + "\n");
		stringBuilder.append("\t\tSkills: " + this.skills + "\n");
		stringBuilder.append("\t\tPreferences: "
				+ Arrays.toString(this.preferences) + "\n");
		stringBuilder.append("\t\tScores: " + Arrays.toString(this.scores)
				+ "\n");
		stringBuilder.append("\t\tCurrent Circuit: C" + this.currentCircuit
				+ "\n");
		stringBuilder.append("\t\tCurrent Score: " + this.currentScore + "\n");
		return stringBuilder.toString();
	}

}
