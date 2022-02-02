package fileReader.model;

public class Transition {
	// Classe's Arguments
	private String fromState;
	private String toState;
	private String value;
	
	
	// Constructor
	public Transition(String fromState, String toState, String value) {
		this.fromState = fromState;
		this.toState = toState;
		this.value = value;
	}
	
	
	// Serialize
	@Override
	public String toString() {
		String serialized = "Origin : " + this.fromState + "\n";
		serialized += "Destination : " + this.toState + "\n";
		if (this.value != null)
			serialized += "Value : " + this.value + "\n";
		
		return serialized;
	}
	
	
	/* Getters */
	public String getFromState() {
		return fromState;
	}

	public String getToState() {
		return toState;
	}

	public String getValue() {
		return value;
	}
}
