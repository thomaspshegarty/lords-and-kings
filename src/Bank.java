
public class Bank extends Building{
	Bank(){
		name = "BANK";
		setModifiers();
		setTurns();
	}
	
	public void setModifiers() {
		goldModifier = 1;
		
	}

	public void setTurns() {
		numberOfTurns = 7;
	}

}
