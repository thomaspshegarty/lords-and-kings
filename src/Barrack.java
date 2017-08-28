
public class Barrack extends Building {
	Barrack(){
		name = "BARRACK";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		strengthModifier = 1;
	}
	public void setTurns() {
		numberOfTurns = 7;
	}
}