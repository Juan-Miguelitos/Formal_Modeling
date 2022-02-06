package fileReader.model;

public class Transition {
	/** Class Arguments **/
	private State previousState;
	private State nextState;
	
	
	/** Constructor **/
	public Transition(String fromState, String toState) {
		this.previousState = new State(fromState);
		this.nextState = new State(toState);
	}
	
	
	/* Initialize classe's arguments */
	public void initArgs(State[] allStates) {
		for (int i = 0; i < allStates.length; i++)
			if (this.previousState.getName().equals(allStates[i].getName()))
				this.previousState = allStates[i];
			else if (this.nextState.getName().equals(allStates[i].getName()))
				this.nextState = allStates[i];
	}
	
	
	/** Serialize **/
	@Override
	public String toString() {
		String serialized = "Origin : " + this.previousState.getName() + "\n";
		serialized += "Destination : " + this.nextState.getName() + "\n";
		
		return serialized;
	}
	
	/** Getters **/
	public State getPreviousState() {
		return previousState;
	}
	public State getNextState() {
		return nextState;
	}
}