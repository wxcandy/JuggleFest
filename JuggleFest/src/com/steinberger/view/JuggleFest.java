package com.steinberger.view;

import java.util.ArrayList;
import java.util.Iterator;

import com.steinberger.controller.FileController;
import com.steinberger.controller.StableMatcher;
import com.steinberger.model.Circuit;
import com.steinberger.model.Juggler;

public class JuggleFest {

	/**
	 * Calculate the triangle sum for an integer binary tree stored in a text
	 * file.
	 * 
	 * @param args
	 *            inputFileName outputFileName jugglerSumCircuitId
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String inputFileName = args[0];
		FileController fileController = new FileController(inputFileName);
		fileController.readInputFile();

		ArrayList<Circuit> circuits = fileController.getCircuits();
		ArrayList<Juggler> jugglers = fileController.getJugglers();

		Iterator<Circuit> circuitIterator = circuits.iterator();
		while (circuitIterator.hasNext()) {
			Circuit circuit = circuitIterator.next();
			System.out.println(circuit);
		}

		System.out.println("***************");

		Iterator<Juggler> jugglerIterator = jugglers.iterator();
		while (jugglerIterator.hasNext()) {
			Juggler juggler = jugglerIterator.next();
			System.out.println(juggler);
		}

		System.out.println("***************");

		int numCircuits = circuits.size();
		System.out.println("Circuits: " + numCircuits);

		int numJugglers = jugglers.size();
		System.out.println("Jugglers: " + numJugglers);

		int numJugglersPerCircuit = jugglers.size() / circuits.size();
		System.out.println("Team size: " + numJugglersPerCircuit);

		System.out.println("***************");

		StableMatcher stableMatcher = new StableMatcher(circuits, jugglers);
		stableMatcher.matchJugglers();
		System.out.println(stableMatcher.toString());

		System.out.println("***************");

		String outputFileName = args[1];
		ArrayList<Circuit> matchedCircuits = stableMatcher.getCircuits();
		fileController = new FileController(outputFileName);
		fileController.setCircuits(matchedCircuits);
		fileController.outputCircuitData();

		System.out.println("***************");

		int circuitSumId = Integer.parseInt(args[2]);
		int sum = sumJugglerIds(matchedCircuits, circuitSumId);

		System.out.println("Circuit C" + circuitSumId);
		System.out.println("Juggler Sum: " + sum);

	}

	public static int sumJugglerIds(ArrayList<Circuit> circuits, int circuitId) {
		int sum = 0;
		Circuit circuit = circuits.get(circuitId);
		for (Juggler juggler : circuit.getJugglers())
			sum += juggler.getId();
		return sum;
	}

}
