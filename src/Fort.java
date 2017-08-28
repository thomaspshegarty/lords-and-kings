
public class Fort extends Building {
	Fort(){
		name = "FORT";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		defenceModifier = 1;
	}
	public void setTurns() {
		numberOfTurns = 7;
	}
}
