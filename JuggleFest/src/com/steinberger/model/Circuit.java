package com.steinberger.model;

import java.util.ArrayList;
import java.util.Collections;

public class Circuit {

	private int id;
	private Skills skills;

	private ArrayList<Juggler> jugglers;
	private int minScore = Integer.MAX_VALUE;

	public Circuit(int id, Skills skills) {
		this.id = id;
		this.skills = skills;
		this.jugglers = new ArrayList<Juggler>();
	}

	public void addJuggler(Juggler juggler) {
		this.jugglers.add(juggler);
		Collections.sort(this.jugglers);
		int numJugglers = this.jugglers.size();
		this.minScore = this.jugglers.get(numJugglers - 1).getCurrentScore();
	}

	public Juggler replaceMinJuggler(Juggler juggler) {
		int numJugglers = this.jugglers.size();
		Juggler minJuggler = this.jugglers.remove(numJugglers - 1);
		minJuggler.setMatched(false);
		juggler.setMatched(true);
		this.addJuggler(juggler);
		return minJuggler;
	}

	public int getId() {
		return this.id;
	}

	public Skills getSkills() {
		return this.skills;
	}

	public ArrayList<Juggler> getJugglers() {
		return this.jugglers;
	}

	public int getMinScore() {
		return this.minScore;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Circuit C" + this.id + "\n");
		stringBuilder.append("\tSkills: " + this.skills + "\n");
		for (Juggler juggler : this.jugglers)
			stringBuilder.append(juggler);
		return stringBuilder.toString();
	}

}
