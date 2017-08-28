
public class Church extends Building{
	Church(){
		name = "CHURCH";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		unrestModifier = -1;
	}
	public void setTurns() {
		numberOfTurns = 7;
	}

}
