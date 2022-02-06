package modelChecking.model;

import java.util.ArrayList;
import java.util.Arrays;

import fileReader.model.State;
import fileReader.model.Transition;

public class CTLChecker {
	public static State[] goThroughPaths(State state, ArrayList<State> seenStates) {
		return goThroughPathsChild(state, seenStates, true);
	}
	public static State[] goThroughPathsChild(State state, ArrayList<State> seenStates, boolean firstCall) {
		/*for (Transition t : state.getTransitions())
			System.out.print(t);
		System.out.println();*/
		if (state.getTransitions() == null)
			return null;
		if (seenStates.contains(state) && !firstCall) {
			State[] seenStatesArray = {};
			seenStatesArray = seenStates.toArray(seenStatesArray);
			
			//System.out.println(seenStates);
			
			return seenStatesArray;
		}
		seenStates.add(state);
		
		ArrayList<State[]> paths = new ArrayList<State[]>();
		State[] buffer;
		
		for (Transition transitions : state.getTransitions()) {
			buffer = goThroughPathsChild(transitions.getNextState(), seenStates, false);
			/*for (State b : buffer)
				System.out.print(b);
			System.out.println();*/
			if (buffer != null)
				paths.add(buffer);
		}
		
		if (paths.size() == 0)
			return null;
		
		
		/*State[] b = unifyStatesAndPaths(paths, seenStates);
		System.out.println("**Path**");
		for (State d : b)
			System.out.println(d);*/
		
		return unifyStatesAndPaths(paths, seenStates);
	}
	private static State[] unifyStatesAndPaths(ArrayList<State[]> paths, ArrayList<State> states) {
		//System.out.println("***In***" + states);
		
		for (State[] path : paths)
			states = unionStates(path, states);
			/*for (State state : path)
				if (!states.contains(state))
					states.add(state);*/
		//System.out.println("***Out***" + states);
		State[] statesArray = {};
		statesArray = states.toArray(statesArray);
		
		return statesArray;
			
	}
	
	
	/* Check Φ = φ */
	public static State[] marking(String phi, State[] allStates) {
		ArrayList<State> validStates = new ArrayList<State>();
		
		for (State state : allStates) {
			if (state.getLabels() == null)
				break;
			for (String label : state.getLabels())
				if (label.equals(phi)) {
					validStates.add(state);
					break;
				}
		}
		
		if (validStates.size() == 0)
			return null;
		
		State[] validStatesArray = {};
		validStatesArray = validStates.toArray(validStatesArray);
		
		return validStatesArray;
	}
	
	
	/* Check Φ = E(φ) */
	public static State[] checkE(State[] phi) {
		if (phi == null)
			return null;
		
		return phi;
	}
	
	/* Check Φ = EX(φ) */
	public static State[] checkEX(State[] phi) {
		ArrayList<State> validStates = new ArrayList<State>();
		
		// Save all phi states that has a previous
		for (State state : phi)
			if (state.getPreviousStates().length != 0) {
				validStates.add(state);
				validStates = unionStates(state.getPreviousStates(), validStates);
			}
		
		// Return valid states if there were found
		if (validStates.size() != 0) {
			State[] validStatesArray = {};
			validStatesArray = validStates.toArray(validStatesArray);
			
			return validStatesArray;
		}
		
		return null;
	}
	
	/* Check Φ = EG(φ) */
	public static State[] checkEG(State[] phi) {
		ArrayList<State> result = new ArrayList<State>();
		State[] buffer;
		ArrayList<State> phiList = arrayToList(phi);
		
		for (State state : phi) {
			buffer = goThroughPaths(state, phiList);
			
			if (buffer.length != 0 && buffer != null)
				if (isPathValid(buffer, phiList))
					result = unionStates(buffer, result);
		}
		
		if (result.size() == 0)
			return null;
		
		State[] resultArray = {};
		resultArray = result.toArray(resultArray);
		
		return resultArray;
	}
	
	/* Check Φ = EF(φ) */
	public static State[] checkEF(State[] phi) {
		ArrayList<State> validStates = new ArrayList<State>();
		ArrayList<State> phiList = arrayToList(phi);
		
		for (State state : phi)
			if (goThroughPaths(state, phiList) != null)
				validStates.add(state);
		
		if (validStates.size() == 0)
			return null;
		
		State[] validStatesArray = {};
		validStatesArray = validStates.toArray(validStatesArray);
		
		return validStatesArray;
	}

	
	/* Check Φ = A(φ) */
	public static State[] checkA(State[] phi, State[] allStates) {
		/*for (State s : phi)
			System.out.println(s);*/
		if (phi == null || allStates == null)
			return null;
		if (phi.length == 0 || allStates.length == 0)
			return null;
		if (phi.length != allStates.length)
			return null;
		
		return phi;
	}
	
	/* Check Φ = AX(φ) */
	public static State[] checkAX(State[] phi) {
		ArrayList<State> validStates = new ArrayList<State>();
		ArrayList<State> validPreviousStates = new ArrayList<State>();
		ArrayList<State> buffer;
		ArrayList<State> phiList = arrayToList(phi);
		
		for (State state : phi) {
			buffer = new ArrayList<State>();
			
			if (state.getPreviousStates().length != 0)
				for (State previous : state.getPreviousStates())
					if (isPreviousValid(previous.getTransitions(), phiList))
						buffer.add(previous);
			if (state.getPreviousStates().length == buffer.size()) {
				validStates.add(state);
				validPreviousStates = unionStates(state.getPreviousStates(), buffer);
			}
		}
		
		if (validStates.size() == 0)
			return null;
		
		State[] validPreviousStatesArray = {}, result = {};
		validPreviousStatesArray = validPreviousStates.toArray(validPreviousStatesArray);
		
		result = unionStates(validPreviousStatesArray, validStates).toArray(result);
		
		return result;
	}

	
	private static boolean isPreviousValid(Transition[] previousTransitions, ArrayList<State> phi) {
		for (Transition transition : previousTransitions)
			if (!phi.contains(transition.getNextState()))
				return false;
		
		return true;
	}
	
	/* Check Φ = AG(φ) */
	public static State[] checkAG(State[] phi) {
		ArrayList<State> validStates = new ArrayList<State>();
		ArrayList<State> validPaths = new ArrayList<State>();
		State[] buffer;
		
		for (State state : phi) {
			buffer = goThroughPaths(state, (ArrayList<State>) Arrays.asList(phi));
			if (buffer.length != 0 && buffer != null)
				if (isPathValid(buffer, (ArrayList<State>) Arrays.asList(phi))) {
					validStates.add(state);
					validPaths = unionStates(buffer, validPaths);
				}
		}
		
		if (validStates.size() == 0 || validStates.size() != phi.length)
			return null;
		
		State[] validStatesArray = {}, result = {};
		validStatesArray = validStates.toArray(validStatesArray);
		
		result = unionStates(validStatesArray, validPaths).toArray(result);
		
		return validStatesArray;
	}
	
	/* Check Φ = AF(φ) */
	public static State[] checkAF(State[] phi) {
		ArrayList<State> validStates = new ArrayList<State>();
		State[] buffer;
		
		for (State state : phi) {
			buffer = goThroughPaths(state, (ArrayList<State>) Arrays.asList(phi));
			if (buffer.length != 0 && buffer != null)
				if (isPathValid(buffer, (ArrayList<State>) Arrays.asList(phi)))
					validStates.add(state);
		}
		
		if (validStates.size() == 0 || validStates.size() != phi.length)
			return null;
		
		State[] validStatesArray = {};
		validStatesArray = validStates.toArray(validStatesArray);
		
		return validStatesArray;
	}
	
	private static boolean isPathValid(State[] path, ArrayList<State> phi) {
		for (State pathState : path) 
			if (!phi.contains(pathState))
				return false;
		
		return true;
	}
	
	private static ArrayList<State> unionStates(State[] s1, ArrayList<State> s2) {
		ArrayList<State> unionWithDuplicates = new ArrayList<State>();
		ArrayList<State> union = new ArrayList<State>();
		
		for (State state : s1)
			unionWithDuplicates.add(state);
		for (State state : s2)
			unionWithDuplicates.add(state);
		
		for (State state : unionWithDuplicates)
			if (!union.contains(state))
				union.add(state);
		
		
		return union;
	}
	
	private static ArrayList<State> arrayToList(State[] a) {
		ArrayList<State> l = new ArrayList<State>();
		
		for (State s : a)
			l.add(s);
		
		return l;
	}
}
