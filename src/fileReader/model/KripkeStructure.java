package fileReader.model;

import java.util.ArrayList;

public class KripkeStructure {
	/** Class Arguments **/
	private State[] states;
	private State initialState;
	
	
	/** Constructor **/
	public KripkeStructure (String[] fileContent) throws Exception {
		if (fileContent.length > 4 || fileContent.length == 0)
			throw new Exception("Kripke Structure is not valid");
		
		String[] states;
		String[] transitions;
		String[] labels;
		
		states = fileContent[0].replace("{", "").replace("}", "").split(",");
		
		transitions = fileContent[2].split(";");
		for(int i = 0; i < transitions.length; i++)
			transitions[i] = transitions[i].replace("{", "").replace("}", "");
		
		labels = fileContent[3].split(";");
		for(int i = 0; i < labels.length; i++)
			labels[i] = labels[i].replace("{", "").replace("}", "");
		
		this.states = formatState(states, transitions, labels);
		initStates(transitions);
		
		// Initializing transitions of each state and find initial state
		for (int i = 0; i < this.states.length; i++) {
			initTransitions(this.states[i]);
			if (this.states[i].getName().equals(fileContent[1].replace("{", "").replace("}", "")))
				this.initialState = this.states[i];
		}
		
		//this.initialState = fileContent[1].replace("{", "").replace("}", "");
	}
	
	
	/* Format each state with its corresponding transitions and labels */
	private State[] formatState(String[] states, String[] transitions, String[] labels) throws Exception {
		ArrayList<State> formatedStates = new ArrayList<State>();
		
		// Formating each state
		for (int i = 0; i < states.length; i++)
			formatedStates.add(new State(states[i], findLabels(states[i], labels), findTransitions(states[i], transitions)));
			
		
		if (formatedStates.size() == 0)
			throw new Exception("No states on the Kripke structure");
		
		// Converting ArrayList to Array
		State[] statesArray = {};
		statesArray = formatedStates.toArray(statesArray);
		
		return statesArray;
	}
	/* Initialize all the state */
	private void initStates(String[] transitions) {
		for (State state : this.states)
			state.initArgs(this.states, transitions);
	}
	
	/* Find and format the transitions related to a state name */
	private Transition[] findTransitions(String state, String[] transitions) {
		ArrayList<String> transitionsFound = new ArrayList<String>();
		ArrayList<Transition> formatedTransitions = new ArrayList<Transition>();
		
		// Finding transitions related to the state 
		for (int i = 0; i < transitions.length; i++)
			if (transitions[i].split(",")[0].equals(state))
				transitionsFound.add(transitions[i]);
		
		// If there is no transition for this state
		if(transitionsFound.size() == 0)
			return null;
		
		// Formating the transitions that has been found
		String fromState;
		String toState;
		String value;
		for (int i = 0; i < transitionsFound.size(); i++) {
			fromState = transitionsFound.get(i).split(",")[0];
			toState = transitionsFound.get(i).split(",")[1];
			
			formatedTransitions.add(new Transition(fromState, toState));
		}
		
		// Converting ArrayList to Array
		Transition[] transitionsArray = {};
		transitionsArray = formatedTransitions.toArray(transitionsArray);
		
		return transitionsArray;
	}
	/* Initialize all the transitions of a state */
	public void initTransitions(State s) {			
		Transition[] transitions = s.getTransitions();
		
		for (int i = 0; i < transitions.length; i++)
			transitions[i].initArgs(this.states);
	}
	
	/* Find the labels related to a state name */
	private String[] findLabels(String state, String[] labels) {
		ArrayList<String> labelsFound = new ArrayList<String>();
		String[] splittedLabel = {};
		
		// Finding labels corresponding to the state
		for (int i = 0; i < labels.length; i++)
			if(labels[i].split(",")[0].equals(state)) {
				splittedLabel = labels[i].split(",");
				
				for (int j = 1; j < splittedLabel.length; j++)
					labelsFound.add(splittedLabel[j]);
				
				break;
			}
		
		if (labelsFound.size() == 0)
			return null;
		
		// Converting ArrayList to Array
		String[] labelsArray = {};
		labelsArray = labelsFound.toArray(labelsArray);
		
		return labelsArray;
	}
	
	
	/** Serialize **/
	@Override
	public String toString() {
		String serialized = "Kripke Structure :\n";
		
		if (this.states == null || this.states.length == 0)
			return "Empty Kripke Structure\n";
		
		serialized += "Initial state: " + this.initialState.getName() + "\n\n";
		for (int i = 0; i < states.length; i++)
			serialized += this.states[i] + "\n";
		
		return serialized;
	}
	
	/** Getters **/
	public State[] getStates() {
		return states;
	}
	public State getInitialState() {
		return initialState;
	}
	
	
}
