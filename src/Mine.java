
public class Mine extends Building {
	Mine(){
		name = "MINE";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		steelModifier = 1;
	}
	public void setTurns() {
		numberOfTurns = 7;
	}
}