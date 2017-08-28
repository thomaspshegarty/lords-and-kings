import java.util.ArrayList;


public class JetFuelCantMeltSteelBeams extends ComputerPlayer {

	JetFuelCantMeltSteelBeams(String c, double starting, String d) {
		super(c, starting, "Computer");
		difficulty = d;
	}
	public void takeTurn(Board daBoard){
		Tile[][] m = daBoard.getMap();
		
		ArrayList<Army> maUnits = getAllArmies();
		double datIncome =getIncome();
		double daSteel =getSteel();
		double daWood = getWood();
		double daMoula = getBalance();
		double daSteelProduction = getSteelProduction();
		double daWoodProduction = getWoodProduction();
		ArrayList<Tile> maTerritoryBitch = getControlledTiles();
		
		double troopGold = .4*daMoula;
		double buildingGold = .2*daMoula;
		double expandGold = .2*daMoula;
		
		double infantryGold = .5*troopGold;
		double cavalryGold = .25*troopGold;
		double artilleryGold = .25*troopGold;
		double newBuildingGold = .7*buildingGold;
		double upgradeGold =.3*buildingGold;
		
		ArrayList<Tile> seenWorld = new ArrayList<Tile>();
		
		for (int i=0; i< Math.sqrt(m.length); i++){
			for (int j=0; j< Math.sqrt(m.length); j++){
				if (m[i][j].getVisibility()==true)
					seenWorld.add(m[i][j]);
			}
		}
		
		//Build
			
		
		
		
		
	}
}
