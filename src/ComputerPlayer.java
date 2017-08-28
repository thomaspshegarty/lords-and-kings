import java.awt.*;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	String difficulty;

	ComputerPlayer(String c, double starting, String d) {
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



		for (int i = 0; i< maTerritoryBitch.size(); i++){
			int toDo = (int)(10*Math.random()+1);
			if (toDo==1){
				int whichUnit = (int)(4*Math.random()+1);
				if (whichUnit==1 &&daMoula>=25){
					purchase(daBoard, "infantry", maTerritoryBitch.get(i));
				}else if (whichUnit==2&&daMoula>=40){//cavalry
					purchase(daBoard, "cavalry", maTerritoryBitch.get(i));
				}else if (whichUnit==3 &&daMoula>=55){//artillery
					purchase(daBoard, "artillery", maTerritoryBitch.get(i));
				}
			}else if(toDo ==2){
				int whichBuilding = (int)(7*Math.random()+1);
				if (whichBuilding==1 &&daMoula>=125){
					purchase(daBoard, "bank", maTerritoryBitch.get(i));
				}else if (whichBuilding==2&&daMoula>=50){//cavalry
					purchase(daBoard, "barrack", maTerritoryBitch.get(i));
				}else if (whichBuilding==3 &&daMoula>=50){//artillery
					purchase(daBoard, "church", maTerritoryBitch.get(i));
				}else if (whichBuilding==4 &&daMoula>=100){
					purchase(daBoard, "fort", maTerritoryBitch.get(i));
				}else if (whichBuilding==5&&daMoula>=40){//cavalry
					purchase(daBoard, "mill", maTerritoryBitch.get(i));
				}else if (whichBuilding==6 &&daMoula>=40){//artillery
					purchase(daBoard, "mine", maTerritoryBitch.get(i));
				}


			}
		}


		//move troops
		if (maUnits.size()>=1){
			for(int i = 0; i<maUnits.size();i++){
				Tile loc = maUnits.get(i).getLocation();
				int direction = (int)(5*Math.random()+1);
				try{
					if (direction == 1){
						daBoard.addToQueue(this, new MoveEvent(loc, m[(loc.getX()/50)+1][loc.getY()/50], maUnits.get(i) ));
					}else if (direction == 2){
						daBoard.addToQueue(this, new MoveEvent(loc, m[(loc.getX()/50)-1][loc.getY()/50], maUnits.get(i) ));
					}else if (direction == 3){
						daBoard.addToQueue(this, new MoveEvent(loc, m[(loc.getX()/50)][loc.getY()/50+1], maUnits.get(i) ));
					}else if (direction == 4){
						daBoard.addToQueue(this, new MoveEvent(loc, m[(loc.getX()/50)][loc.getY()/50-1], maUnits.get(i) ));
					}
				}catch (Exception e){
				}
			}
		}
		//find nearest troops

		//expand borders
		if (maUnits.size()>=1){
			for(int i = 0; i<maUnits.size();i++){
					daBoard.addToQueue(this, new ConvertEvent(maUnits.get(i).getLocation(), this, daBoard));
			}
		}

	}
	
}
