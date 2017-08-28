
public class Building {
	double goldModifier;
	double steelModifier;
	double woodModifier;
	double defenceModifier;
	double strengthModifier;
	double unrestModifier;
	int numberOfTurns;
	String name;
	
	public int getTurns(){
		return numberOfTurns;
	}
	public double[] getModifiers(){
		return new double[]{goldModifier,steelModifier,woodModifier,
				defenceModifier,strengthModifier,unrestModifier};
	}
	
	public String getName(){
		return name;
	}
	
	

	
	

}
