package com.steinberger.controller;

import java.util.ArrayList;
import java.util.Random;

import com.steinberger.model.Circuit;
import com.steinberger.model.Juggler;
import com.steinberger.model.Skills;

public class StableMatcher {

	private int numCircuits;
	private int numJugglers;
	private int teamSize;

	private ArrayList<Circuit> circuits;
	private ArrayList<Juggler> jugglers;

	private Random random;

	public StableMatcher(ArrayList<Circuit> circuits,
			ArrayList<Juggler> jugglers) {
		this.circuits = circuits;
		this.jugglers = jugglers;
		this.numCircuits = circuits.size();
		this.numJugglers = jugglers.size();
		this.teamSize = this.numJugglers / this.numCircuits;
		this.random = new Random(System.currentTimeMillis());
	}

	public void matchJugglers() {

		while (!this.jugglers.isEmpty()) {

			for (int index = 0; index < this.jugglers.size(); index++) {

				Juggler currentJuggler = this.jugglers.get(index);
				int preferenceIndex = currentJuggler.getPreferenceIndex() + 1;
				int[] preferences = currentJuggler.getPreferences();
				int[] scores = currentJuggler.getScores();

				boolean isMatched = false;

				while (preferenceIndex < preferences.length) {

					int circuitIndex = preferences[preferenceIndex];
					Circuit currentCircuit = this.circuits.get(circuitIndex);

					int currentCircuitId = currentCircuit.getId();
					currentJuggler.setCurrentCircuit(currentCircuitId);

					int currentScore = scores[preferenceIndex];

					currentJuggler.setCurrentScore(currentScore);
					currentJuggler.setPreferenceIndex(preferenceIndex);

					ArrayList<Juggler> currentJugglers = currentCircuit
							.getJugglers();
					int currentJugglerScore = currentJuggler.getCurrentScore();
					int currentCircuitMinScore = currentCircuit.getMinScore();

					if (currentJugglers.size() < this.teamSize) {

						currentJuggler.setMatched(true);
						currentCircuit.addJuggler(currentJuggler);
						this.jugglers.remove(index);
						isMatched = true;
						break;

					} else if (currentJugglerScore > currentCircuitMinScore) {

						Juggler minJuggler = currentCircuit
								.replaceMinJuggler(currentJuggler);
						this.jugglers.add(minJuggler);
						this.jugglers.remove(index);
						isMatched = true;
						break;

					} else
						preferenceIndex++;

				}

				if (!isMatched) {

					while (true) {

						int randomCircuitIndex = this.random
								.nextInt(this.numCircuits);

						Circuit randomCircuit = this.circuits
								.get(randomCircuitIndex);

						int randomCircuitId = randomCircuit.getId();
						currentJuggler.setCurrentCircuit(randomCircuitId);

						Skills randomQualification = randomCircuit.getSkills();
						Juggler indexedJuggler = this.jugglers.get(index);
						Skills indexedQualification = indexedJuggler
								.getSkills();
						int randomScore = randomQualification
								.dotProduct(indexedQualification);
						currentJuggler.setCurrentScore(randomScore);

						ArrayList<Juggler> currentJugglers = randomCircuit
								.getJugglers();

						int currentJugglerScore = currentJuggler
								.getCurrentScore();
						int randomCircuitMinScore = randomCircuit.getMinScore();

						if (currentJugglers.size() < this.teamSize) {

							currentJuggler.setMatched(true);
							randomCircuit.addJuggler(currentJuggler);
							this.jugglers.remove(index);
							break;

						} else if (currentJugglerScore > randomCircuitMinScore) {

							Juggler minJuggler = randomCircuit
									.replaceMinJuggler(currentJuggler);
							this.jugglers.add(minJuggler);
							this.jugglers.remove(index);
							break;

						}

					}

				}

			}

		}

	}

	public int getNumCircuits() {
		return this.numCircuits;
	}

	public int getNumJugglers() {
		return this.numJugglers;
	}

	public int getTeamSize() {
		return this.teamSize;
	}

	public ArrayList<Circuit> getCircuits() {
		return this.circuits;
	}

	public ArrayList<Juggler> getJugglers() {
		return this.jugglers;
	}

	public String toString() {
		String result = "";
		for (Circuit circuit : this.circuits)
			result += circuit.toString() + "\n";
		return result;
	}

}
