package fileReader.model;

import java.util.ArrayList;

public class State {
	/** Class Arguments**/
	private String name;
	private String[] labels = null;
	private Transition[] transitions = null;
	private State[] previousStates = {};
	
	
	/** Constructors **/
	public State(String name, String[] labels, Transition[] transitions) {
		this.name = name;
		this.labels = labels;
		this.transitions = transitions;
	}
	public State(String name) {
		this.name = name;
	}
	
	
	/**/
	public void initArgs(State[] states, String[] transitions) {
		ArrayList<State> previousStates = new ArrayList<State>();
		
		for (String transition : transitions)
			if (transition.split(",")[1].equals(this.name))
				for (State state : states)
					if (transition.split(",")[0].equals(state.getName())) {
						previousStates.add(state);
						break;
					}
		
		this.previousStates = previousStates.toArray(this.previousStates);
	}
	
	
	/** Serialize **/
	@Override
	public String toString() {
		String serialized = "State : " + this.name + "\n";
		
		serialized += "Labels : ";
		if (this.labels != null && this.labels.length != 0)
			for (int i = 0; i < labels.length; i++)
				serialized += this.labels[i] + " ";
		else
			serialized += "None";
		
		if (this.previousStates.length != 0) {
			serialized += "\nPrevious : ";
			for (State state : this.previousStates)
				serialized += state.getName() + " ";
		}
		
		serialized += "\nTransitions : \n";
		if (this.transitions != null && this.transitions.length != 0)
			for (int i = 0; i < transitions.length; i++)
				serialized += this.transitions[i].getNextState().getName() + "\n";
		else
			serialized += "None\n";
		
		return serialized;
	}
	
	/** Getters **/
	public String getName() {
		return name;
	}
	public String[] getLabels() {
		return labels;
	}
	public Transition[] getTransitions() {
		return transitions;
	}
	public State[] getPreviousStates() {
		return previousStates;
	}
}
