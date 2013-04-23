package com.steinberger.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.steinberger.model.Circuit;
import com.steinberger.model.Juggler;
import com.steinberger.model.Skills;

public class FileController {

	private String fileName;
	private ArrayList<Circuit> circuits;
	private ArrayList<Juggler> jugglers;

	public FileController(String fileName) throws Exception {
		this.fileName = fileName;
		this.circuits = new ArrayList<Circuit>();
		this.jugglers = new ArrayList<Juggler>();
	}

	public void readInputFile() throws Exception {

		FileReader fileReader = new FileReader(this.fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line;

		while ((line = bufferedReader.readLine()) != null) {

			line = line.trim().replace("\ufeff", "");
			String[] words = line.split(" ");
			int numWords = words.length;

			if (numWords <= 1)
				continue;

			String code = words[0];

			int id = this.parseId(words[1]);
			int handEye = this.parseSkill(words[2]);
			int endurance = this.parseSkill(words[3]);
			int pizzazz = this.parseSkill(words[4]);
			Skills skills = new Skills(handEye, endurance, pizzazz);

			switch (code) {

			case "C":
				Circuit circuit = new Circuit(id, skills);
				this.circuits.add(circuit);
				break;

			case "J":
				String rankings = words[5];
				String[] rankedCircuits = rankings.split(",");
				int numRanks = rankedCircuits.length;
				Juggler juggler = new Juggler(id, skills, numRanks);
				for (int index = 0; index < numRanks; index++) {
					String circuitName = rankedCircuits[index];
					int circuitId = this.parseId(circuitName);
					Circuit rankedCircuit = this.circuits.get(circuitId);
					juggler.addCircuit(rankedCircuit);
				}
				this.jugglers.add(juggler);
				break;

			}

		}

		bufferedReader.close();

	}

	private int parseId(String name) {
		String nameLabel = name.substring(1);
		int id = Integer.parseInt(nameLabel);
		return id;
	}

	private int parseSkill(String skill) {
		String valueLabel = skill.substring(2);
		int value = Integer.parseInt(valueLabel);
		return value;
	}

	public void outputCircuitData() throws IOException {

		int numCircuits = this.circuits.size();

		StringBuilder stringBuilder = new StringBuilder();

		for (int index = numCircuits - 1; index >= 0; index--) {

			Circuit circuit = this.circuits.get(index);
			int circuitId = circuit.getId();
			stringBuilder.append("C" + circuitId + " ");

			ArrayList<Juggler> jugglers = circuit.getJugglers();

			for (Juggler juggler : jugglers) {

				int jugglerId = juggler.getId();
				stringBuilder.append("J" + jugglerId + " ");

				int[] preferences = juggler.getPreferences();
				int[] scores = juggler.getScores();

				for (int index2 = 0; index2 < preferences.length; index2++) {
					int circuitId2 = preferences[index2];
					int score = scores[index2];
					stringBuilder.append("C" + circuitId2 + ":" + score + " ");
				}

				int length = stringBuilder.length();
				stringBuilder.deleteCharAt(length - 1);
				stringBuilder.append(", ");

			}

			int length = stringBuilder.length();
			stringBuilder.delete(length - 2, length);
			stringBuilder.append("\n");

		}

		int length = stringBuilder.length();
		stringBuilder.deleteCharAt(length - 1);
		String output = stringBuilder.toString();
		System.out.println(output);

		File file = new File(this.fileName);
		if (!file.exists())
			file.createNewFile();
		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(output);
		bufferedWriter.close();

	}

	public ArrayList<Circuit> getCircuits() {
		return this.circuits;
	}

	public void setCircuits(ArrayList<Circuit> circuits) {
		this.circuits = circuits;
	}

	public String getFileName() {
		return this.fileName;
	}

	public ArrayList<Juggler> getJugglers() {
		return this.jugglers;
	}

}
