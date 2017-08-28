
public class Event {
	private int turns = 0;
	private int maxTurns;

	public void increment() {
		turns++;
		if (turns == getMaxTurns()) {
			fireEvent();
		}
	}

	public void fireEvent() {
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getTurns() {
		return turns;
	}

	public int getMaxTurns() {
		return maxTurns;
	}

	public void setMaxTurns(int maxTurns) {
		this.maxTurns = maxTurns;
	}
}
