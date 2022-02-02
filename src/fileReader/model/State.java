package fileReader.model;

public class State {
	// Classe's Arguments
	private String name;
	private String[] labels;
	private Transition[] transitions;
	
	
	// Constructor
	public State(String name, String[] labels, Transition[] transitions) {
		this.name = name;
		this.labels = labels;
		this.transitions = transitions;
	}

	
	// Serialize
	@Override
	public String toString() {
		String serialized = "State : " + this.name + "\n";
		
		serialized += "Labels : ";
		if (this.labels != null && this.labels.length != 0)
			for (int i = 0; i < labels.length; i++)
				serialized += this.labels[i] + " ";
		else
			serialized += "None";
		
		serialized += "\nTransitions : \n";
		if (this.transitions != null && this.transitions.length != 0)
			for (int i = 0; i < transitions.length; i++) {
				serialized += this.transitions[i].getToState();
				
				if (this.transitions[i].getValue() != null)
					serialized += " : " + this.transitions[i].getValue();
				
				serialized += "\n";
			}
		else
			serialized += "None\n";
		
		return serialized;
	}
	
	
	/* Getters */
	public String getName() {
		return name;
	}

	public String[] getLabels() {
		return labels;
	}

	public Transition[] getTransitions() {
		return transitions;
	}
}
