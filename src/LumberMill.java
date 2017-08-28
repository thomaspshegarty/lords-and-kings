
public class LumberMill extends Building{
	LumberMill(){
		name = "LUMBERMILL";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		woodModifier = 1;
	}
	public void setTurns() {
		numberOfTurns = 7;
	}

}
