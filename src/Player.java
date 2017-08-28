import java.util.ArrayList;


public abstract class Player {
	private ArrayList<Army> allArmies= new ArrayList<Army>();
	private double balance = 0;
	private double wood = 0;
	private double steel = 0;
	private double income = 0;
	private double woodProduction = 0;
	private double steelProduction = 0;
	private String color;
	private String name;
	private ArrayList<Tile> controlledTiles = new ArrayList<Tile>();
	private ArrayList<Event> queue = new ArrayList<Event>();

	Player(String c, double starting, String n){
		color = c;
		balance = starting;
		name = n;
	}

	public void purchase(Board b, String s, Tile t){
		if(s.equals("infantry")){
			b.addToQueue(this,new CreateEvent(t,new Infantry(getColor()),this));
			balance = balance-25;
			steel = steel-5;
		}else if(s.equals("cavalry")){
			b.addToQueue(this,new CreateEvent(t,new Cavalry(getColor()),this));
			balance = balance - 40;
			steel = steel-5;
		}else if(s.equals("artillery")){
			b.addToQueue(this, new CreateEvent(t,new Artillery(getColor()),this));
			balance = balance - 55;
			wood = wood -5;
		}else if(s.equals("bank")){
			b.addToQueue(this,new BuildEvent(t,new Bank(),this));
			balance = balance - 125;
		}else if(s.equals("barrack")){
			b.addToQueue(this,new BuildEvent(t,new Barrack(),this));
			balance = balance - 50;
		}else if(s.equals("church")){
			b.addToQueue(this,new BuildEvent(t,new Church(),this));
			balance = balance - 50;
		}else if(s.equals("fort")){
			b.addToQueue(this,new BuildEvent(t,new Fort(),this));
			balance = balance - 100;
		}else if(s.equals("mill")){
			b.addToQueue(this,new BuildEvent(t,new LumberMill(),this));
			balance = balance - 40;
		}else if(s.equals("mine")){
			b.addToQueue(this,new BuildEvent(t,new Mine(),this));
			balance = balance - 40;
		}
	}

	public double getWoodProduction(){return woodProduction;}
	public double getSteelProduction(){return steelProduction;}
	public String getColor() {return color;}
	public double getBalance(){return balance;}
	public String getName(){return name;}
	public ArrayList<Tile> getControlledTiles(){return controlledTiles;}

	public void setControlledTiles(ArrayList<Tile> c){controlledTiles = c;}

	public void addControlledTile(Tile t){controlledTiles.add(t);}

	public void update(){
		income = 0;
		woodProduction = 0;
		steelProduction = 0;
		for(Tile t : controlledTiles){
			income = income+t.getIncome();
			woodProduction = woodProduction+t.getWoodProduction();
			steelProduction = steelProduction+t.getSteelProduction();
		}
	}

	public void increment(){

		balance = balance+income;
		wood = wood+woodProduction;
		steel = steel + steelProduction;
	}

	public double getIncome() {
		return income;
	}

	public double getWood() {
		return wood;
	}

	public double getSteel() {
		return steel;
	}

	public ArrayList<Event> getQueue(){return queue;}
	public void removeEvent(Event e){
		queue.remove(e);

	}
	public void addEvent(Event e){queue.add(e);}

	public void addArmy(Army a){
		allArmies.add(a);
	}

	public ArrayList<Army> getAllArmies (){
		return allArmies;
	}

}
