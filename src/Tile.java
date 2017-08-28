import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class Tile extends JComponent{

	private double income;
	private double strength;
	private double defense;
	private double unrest;
	private double woodProduction;
	private double steelProduction;
	private String landType;
	private boolean selectedState;

	private String filename;
	private String selectedFilename;
	private int x;
	private int y;

	private Player controller;
	private boolean controlled;

	private boolean visible = true;

	private int index;

	private Army unit;

	private boolean operated;

	private double turnsMax;
	private double turnsComplete;
	
	private ArrayList<Building> daBuildings;

	Tile(int across, int hi, String land){
		x = across;
		y = hi;
		landType = land;
		setValues(landType);
		controlled=false;
		daBuildings = new ArrayList<Building>();
	}


	public void setValues(String type){
		if(!getVisibility()){
			setFilename("images/unseen tile.png");
			setSelectedFilename("images/unseen tile.png");
		}
	}
	public void refresh(){
		setValues(landType);
	}

	public void setState(boolean s){selectedState = s;}

	public BufferedImage getImage(){
		try {
			File f = new File(getClass().getResource(getCurrentFilename()).toURI());
			BufferedImage image = ImageIO.read(f);
			return image;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public String getFilename() {return filename;}
	public void setFilename(String file) {filename = file;}
	public void setSelectedFilename(String selectedFilename) {this.selectedFilename = selectedFilename;}
	public String getSelectedFilename() {return selectedFilename;}

	public String getCurrentFilename(){
		if(getState()){
			return getSelectedFilename();
		}else{
			return getFilename();
		}
	}

	public void select() {setState(true);}
	public void deselect(){setState(false);}

	public boolean getState(){return selectedState;}

	public double getDefense() {return defense;}
	public double getIncome() {return income;}
	public double getSteelProduction() {return steelProduction;}
	public double getStrength() {return strength;}
	public double getUnrest() {return unrest;}
	public double getWoodProduction() {return woodProduction;}
	public String getLandType() {return landType;}

	public void setDefense(double defense) {this.defense = defense;}
	public void setIncome(double income) {this.income = income;}
	public void setLandType(String landType) {this.landType = landType;}
	public void setSteelProduction(double steelProduction) {this.steelProduction = steelProduction;}
	public void setStrength(double strength) {this.strength = strength;}
	public void setUnrest(double unrest) {this.unrest = unrest;}
	public void setWoodProduction(double woodProduction) {this.woodProduction = woodProduction;}

	public Player getController(){return controller;}
	public void setPlayer(Player p){controller = p;controlled=true;}
	public boolean isControlled(){return controlled;}

	public void setVisibility(boolean v){visible = v;}
	public boolean getVisibility(){return visible;}

	public int getIndex(){return index;}

	public void paintComponent(Graphics g){
		g.drawImage(getImage(),0,0, null);
		if(getVisibility()){
			if(isOccupied()){
				g.drawImage(unit.getImage(),0,0,null);
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.WHITE);
				try{
				if (unit.getColor()=="yellow")
					g2.setColor(Color.BLACK);

				}catch (Exception e){
					
				}

				g2.drawString(unit.getSize()+"",35,15);
			}
			if(getOperated()){
				g.fillRect(5, 35, 40, 10);
				g.setColor(new Color(18,132,0));
				g.fillRect(5,35,(int)(40*(turnsComplete/turnsMax)),10);
			}
		}

	}

	public void setArmy(Army u){
		if(unit == null) {
			unit = u;
		}else{
			ArrayList<Unit> currentArmy = unit.getArmy();
			ArrayList<Unit> arrivingArmy = u.getArmy();
			ArrayList<Unit> combinedArmy = new ArrayList<Unit>();
			combinedArmy.addAll(currentArmy);
			combinedArmy.addAll(arrivingArmy);
			unit = new Army(combinedArmy,unit.getColor());
		}
	}
	public boolean isOccupied(){
		try{
			return unit != null;
		}catch (Exception e){
			return false;
		}
	}
	public void removeUnit(){
		unit = null;
	}

	public Army getArmy(){return unit;}

	public boolean getOperated(){return operated;}
	public void setOperated(boolean o){operated = o;}

	public void updateProgress(int max, int current){
		turnsMax = max;
		turnsComplete = current;

	}


	public void addBuilding(Building b) {
		daBuildings.add(b);
	}
}