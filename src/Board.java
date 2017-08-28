import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class Board extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int EASY = 1;
	private final static int MEDIUM = 2;
	private final static int HARD = 3;

	private Tile[][] map;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Tile> playerTiles = new ArrayList<Tile>();

	private int rows;
	private int cols;
	private Tile t;

	private GameFrame gameFrame;

	private JLabel cavalryIcon;
	private JLabel infantryIcon;
	private JLabel artilleryIcon;

	private JFrame armyFrame;
	private JFrame buildingFrame;
	private JFrame selectFrame;


	private HumanPlayer player;

	private Army thisArmy;

	private boolean moveMode = false;


	Board(int difficulty, HumanPlayer p, GameFrame g) {
		gameFrame = g;
		players.add(p);
		player = p;

		if (p.getColor().equals("red")) {
			players.add(new ComputerPlayer("blue", 40.0, "easy"));
			players.add(new ComputerPlayer("purple", 40.0, "easy"));
			players.add(new ComputerPlayer("yellow", 40.0, "easy"));
		} else if (p.getColor().equals("blue")) {
			players.add(new ComputerPlayer("red", 40.0, "easy"));
			players.add(new ComputerPlayer("purple", 40.0, "easy"));
			players.add(new ComputerPlayer("yellow", 40.0, "easy"));
		} else if (p.getColor().equals("purple")) {
			players.add(new ComputerPlayer("blue", 40.0, "easy"));
			players.add(new ComputerPlayer("red", 40.0, "easy"));
			players.add(new ComputerPlayer("yellow", 40.0, "easy"));
		} else {
			players.add(new ComputerPlayer("blue", 40.0, "easy"));
			players.add(new ComputerPlayer("purple", 40.0, "easy"));
			players.add(new ComputerPlayer("red", 0.0, "easy"));
		}

		if (difficulty == EASY) {
			rows = 12;
			cols = 12;
			Tile[][] newM = new Tile[12][12];
			map = newM;
			setLayout(new GridLayout(12, 12));
			setBoard();
		} else if (difficulty == MEDIUM) {
			rows = 13;
			cols = 13;
			Tile[][] newM = new Tile[13][13];
			map = newM;
			setLayout(new GridLayout(13, 13));
			setBoard();
		} else if (difficulty == HARD) {
			rows = 15;
			cols = 15;
			Tile[][] newM = new Tile[15][15];
			map = newM;
			setLayout(new GridLayout(15, 15));
			setBoard();
		}
		setPreferredSize(new Dimension(cols * 50, rows * 50));

	}

	public Tile returnSelected() {
		return t;
	}

	public Tile[][] getMap(){return map;}

	public void buildingCreationScreen() {
		buildingFrame = new JFrame("Create Buildings");

		JButton bank = new JButton("125 g");
		JButton barrack = new JButton("50 g");
		JButton church = new JButton("50 g");
		JButton fort = new JButton("100 g");
		JButton mill = new JButton("40 g");
		JButton mine = new JButton("40 g");

		bank.addActionListener(this);
		bank.setActionCommand("create bank");

		barrack.addActionListener(this);
		barrack.setActionCommand("create barrack");

		church.addActionListener(this);
		church.setActionCommand("create church");

		fort.addActionListener(this);
		fort.setActionCommand("create fort");

		mill.addActionListener(this);
		mill.setActionCommand("create mill");

		mine.addActionListener(this);
		mine.setActionCommand("create mine");

		JLabel bankLabel = new JLabel("Build Bank: 7 turns");
		JLabel barrackLabel = new JLabel("Build Barrack: 7 turns");
		JLabel churchLabel = new JLabel("Build Church: 7 turns");
		JLabel fortLabel = new JLabel("Build Fort: 7 turns");
		JLabel millLabel = new JLabel("Build Lumber Mill: 7 turns");
		JLabel mineLabel = new JLabel("Build Mine: 7 turns");

		//setIcons();
		buildingFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridwidth = 2;
		c.gridx = 1;
		buildingFrame.add(millLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(mill, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridx = 1;
		buildingFrame.add(mineLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(mine, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridx = 1;
		buildingFrame.add(barrackLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(barrack, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridx = 1;
		buildingFrame.add(churchLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(church, c);

		c.gridx = 0;
		c.gridy= 4;
		c.gridx = 1;
		c.gridwidth = 2;
		buildingFrame.add(fortLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(fort, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridx = 1;
		c.gridwidth = 2;
		buildingFrame.add(bankLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		buildingFrame.add(bank, c);



		buildingFrame.setPreferredSize(new Dimension(400, 200));
		buildingFrame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		buildingFrame.setLocation(dim.width / 2 - buildingFrame.getSize().width / 2,
				dim.height / 2 - ((buildingFrame.getSize().height) / 2) - 30);
		buildingFrame.setVisible(true);

	}

	private void setBoard() {

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {

				int determinant = (int) (9 * Math.random());
				Tile t;
				if (determinant < 3) {
					t = new NormalTile(x * 50, y * 50, "forest");
				} else if (determinant < 6) {
					t = new NormalTile(x * 50, y * 50, "plain");
				} else {
					t = new NormalTile(x * 50, y * 50, "rocks");
				}
				if (x == 0 || x == 1 || x == 2) {
					if (y == 0 || y == 1 || y == 2) {
						if(x == 0 && y == 0){
							t = new NormalTile(x*50, y*50, "capital");
						}
						t.setPlayer(player);
						playerTiles.add(t);
						t.refresh();
					}
				}
				if (x == rows - 3 || x == rows - 2 || x == rows - 1) {
					if (y == 0 || y == 1 || y == 2) {
						if(x == rows - 1 && y == 0){
							t = new NormalTile(x*50,y*50,"capital");
						}
						t.setPlayer(players.get(1));
						players.get(1).addControlledTile(t);
						t.refresh();
					}
				}
				if (x == 0 || x == 1 || x == 2) {
					if (y == cols - 3 || y == cols - 2 || y == cols - 1) {
						if(x == 0 && y == cols-1){
							t = new NormalTile(x*50,y*50,"capital");
						}
						t.setPlayer(players.get(2));
						players.get(2).addControlledTile(t);
						t.refresh();
					}
				}
				if (x == rows - 3 || x == rows - 2 || x == rows - 1) {
					if (y == cols - 3 || y == cols - 2 || y == cols - 1) {
						if(x == rows - 1 && y == cols-1){
							t = new NormalTile(x*50,y*50,"capital");
						}
						t.setPlayer(players.get(3));
						players.get(3).addControlledTile(t);
						t.refresh();
					}
				}
				t.addMouseListener(this);
				t.addMouseListener(gameFrame);
				map[x][y] = t;
				add(t);
			}
		}
		player.setControlledTiles(playerTiles);
		player.update();
		setVisibility();
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1){
			if (!moveMode) {
				try {
					if (t.getState()) {
						t.deselect();
					}
				} catch (Exception ex) {
				}
				t = (Tile) e.getComponent();
				t.select();
				repaint();
			} else {
				if(areBordering((Tile)e.getComponent(),t,thisArmy.getDistance())){
					if(thisArmy.getAction() != null){
						player.removeEvent(thisArmy.getAction());
					}
					thisArmy.setAction(new MoveEvent(t, (Tile) e.getComponent(), thisArmy));
					player.addEvent(thisArmy.getAction());
					moveMode = false;

					int x = t.getX() / 50;
					int y = t.getY() / 50;
					int dis = thisArmy.getDistance();

					for (int xIter = x - dis; xIter <= x + dis; xIter++) {
						for (int yIter = y - dis; yIter <= y + dis; yIter++) {
							try {
								map[xIter][yIter].setState(false);
								map[xIter][yIter].repaint();
							} catch (Exception ex) {
							}
						}
					}
					t = (Tile) e.getComponent();
					t.setState(true);
					t.repaint();
				}else{
					moveMode = false;
					int x = t.getX()/50;
					int y = t.getY()/50;
					int d = thisArmy.getDistance();
					for (int xIter = x - d; xIter <= x + d; xIter++) {
						for (int yIter = y - d; yIter <= y + d; yIter++) {
							try {
								map[xIter][yIter].setState(false);
								map[xIter][yIter].repaint();
							} catch (Exception ex) {
							}
						}
					}
				}
			}
		}else{
			try{
				t.deselect();
			}catch(Exception ez){}
			t = (Tile) e.getComponent();
			t.select();
			if(t.isOccupied()){
				thisArmy = t.getArmy();
				moveScreen();
			}

		}
	}

	public boolean areBordering(Tile t1, Tile t2, int d){
		boolean toReturn = false;
		int x = t1.getX()/50;
		int y = t1.getY()/50;
		for (int checkX = x - d; checkX <= x + d; checkX++) {
			for (int checkY = y - d; checkY <= y + d; checkY++) {
				try {
					Tile toCheck = map[checkX][checkY];
					if (toCheck.equals(t2))
						toReturn = true;
				} catch (Exception e) {
				}
			}
		}
		return toReturn;
	}

	public void mousePressed(MouseEvent e) {/*doesn't matter*/}

	public void mouseReleased(MouseEvent e) {/*doesn't matter*/}

	public void mouseEntered(MouseEvent e) {/*doesn't matter*/}

	public void mouseExited(MouseEvent e) {/*doesn't matter*/}

	public void refreshStats() {
		for (Player p : players) {
			p.update();
			p.increment();
		}
	}

	public double getHumanBalance() {
		return player.getBalance();
	}

	public double getHumanIncome() {
		return player.getIncome();
	}

	public double getHumanWood() {
		return player.getWood();
	}

	public double getHumanSteel() {
		return player.getSteel();
	}

	public HumanPlayer getPlayer() {
		return player;
	}

	public void setVisibility() {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				if (checkAround(x, y) || checkArmiesAround(x,y)) {
					map[x][y].setVisibility(true);
				} else {
					map[x][y].setVisibility(false);
				}
				map[x][y].refresh();
			}
		}
		repaint();

	}

	public boolean checkArmiesAround(int x, int y){

		boolean toReturn = false;
		for (int checkX = x - 1; checkX < x + 2; checkX++) {
			for (int checkY = y - 1; checkY < y + 2; checkY++) {
				try {
					Tile toCheck = map[checkX][checkY];
					try {
						Army arm = toCheck.getArmy();
						if(arm != null && arm.getColor().equals(player.getColor())){
							toReturn = true;
						}
					} catch (Exception e) {
					}
				} catch (Exception e) {
				}
			}
		}
		return toReturn;
	}

	public boolean checkAround(int x, int y) {
		boolean toReturn = false;

		for (int checkX = x - 2; checkX <= x + 2; checkX++) {
			for (int checkY = y - 2; checkY <= y + 2; checkY++) {
				try {
					Tile toCheck = map[checkX][checkY];
					try {
						String name = toCheck.getController().getName();
						if (!name.equals("Computer")) {
							toReturn = true;
						}
					} catch (Exception e) {
					}
				} catch (Exception e) {
				}
			}
		}
		return toReturn;
	}

	public boolean checkAroundOne(int x, int y){
		boolean toReturn = false;

		for (int checkX = x - 1; checkX < x + 1; checkX++) {
			for (int checkY = y - 1; checkY < y + 1; checkY++) {
				try {
					Tile toCheck = map[checkX][checkY];
					try {
						String name = toCheck.getController().getName();
						name.concat("");
						toReturn = true;
					} catch (Exception e) {
					}
				} catch (Exception e) {
				}
			}
		}
		return toReturn;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("select army")) {
			try {
				armySelectionScreen();
			} catch (Exception ex) {
			}
		} else if (command.equals("create buildings")) {
			buildingCreationScreen();
		} else if (command.equals("create armies")) {
			armyCreationScreen();
		} else if (command.equals("create infantry")) {
			if (player.getBalance() >= 25 && player.getSteel()>=5&& !t.isOccupied()) {
				player.purchase(this,"infantry",t);
				armyFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create cavalry")) {
			if (player.getBalance() >= 40 && player.getSteel()>=5 && !t.isOccupied()) {
				player.purchase(this,"cavalry",t);
				armyFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create artillery")) {
			if (player.getBalance() >= 55 && player.getWood()>=5 && !t.isOccupied()) {
				player.purchase(this,"artillery",t);
				armyFrame.dispose();
				gameFrame.repaintElements();
			}
		}  else if (command.equals("create bank")) {
			if (player.getBalance() >= 125 && !t.isOccupied()) {
				player.purchase(this,"bank",t);
				buildingFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create barrack")) {
			if (player.getBalance() >= 50 && !t.isOccupied()) {
				player.purchase(this,"barrack",t);
				buildingFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create church")) {
			if (player.getBalance() >= 50 && !t.isOccupied()) {
				player.purchase(this,"church",t);
				armyFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create fort")) {
			if (player.getBalance() >= 100 && !t.isOccupied()) {
				player.purchase(this,"fort",t);
				buildingFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create mill")) {
			if (player.getBalance() >= 40 && !t.isOccupied()) {
				player.purchase(this,"mill",t);
				buildingFrame.dispose();
				gameFrame.repaintElements();
			}
		} else if (command.equals("create mine")) {
			if (player.getBalance() >= 40 && !t.isOccupied()) {
				player.purchase(this,"mine",t);
				buildingFrame.dispose();
				gameFrame.repaintElements();
			}
		}else if (command.equals("move")) {
			moveScreen();
		} else if (command.equals("convert")){
			if(checkAroundOne(t.getX()/50,t.getY()/50) || t.getArmy() != null){
				addToQueue(player,new ConvertEvent(t,player,this));
			}
		}
	}

	public void setIcons() {
		try {
			ImageIcon ii = new ImageIcon();
			File f = new File(getClass().getResource("images/" + player.getColor() + " cavalry.png").toURI());
			Image i = ImageIO.read(f);
			ii.setImage(i);
			cavalryIcon = new JLabel(ii);

			armyFrame.setLayout(new GridBagLayout());
		} catch (Exception e) {
			cavalryIcon = new JLabel("C");
		}
		try {

			ImageIcon ii = new ImageIcon();
			File f = new File(getClass().getResource("images/" + player.getColor() + " infantry.png").toURI());
			Image i = ImageIO.read(f);
			ii.setImage(i);

			infantryIcon = new JLabel(ii);
		} catch (Exception e) {
			infantryIcon = new JLabel("I");
		}
		try {

			ImageIcon ii = new ImageIcon();
			File f = new File(getClass().getResource("images/" + player.getColor() + " artillery.png").toURI());
			Image i = ImageIO.read(f);
			ii.setImage(i);

			artilleryIcon = new JLabel(ii);
		} catch (Exception e) {
			artilleryIcon = new JLabel("A");
		}
	}

	public void armyCreationScreen() {
		armyFrame = new JFrame("Create Armies");

		JButton infantry = new JButton("25 g & 5 s");
		JButton cavalry = new JButton("40 g & 5 s");
		JButton artillery = new JButton("55 g & 5 w");

		infantry.addActionListener(this);
		infantry.setActionCommand("create infantry");

		cavalry.addActionListener(this);
		cavalry.setActionCommand("create cavalry");

		artillery.addActionListener(this);
		artillery.setActionCommand("create artillery");

		JLabel cavalryLabel = new JLabel("Recruit Cavalry: 3 turns");
		JLabel infantryLabel = new JLabel("Recruit Infantry: 2 turns");
		JLabel artilleryLabel = new JLabel("Recruit Artillery: 4 turns");

		setIcons();

		GridBagConstraints c = new GridBagConstraints();

		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;


		c.gridx = 0;
		armyFrame.add(infantryIcon, c);
		c.gridx = 1;
		c.gridwidth = 2;
		armyFrame.add(infantryLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		armyFrame.add(infantry, c);


		c.gridx = 0;
		c.gridy = 1;
		armyFrame.add(cavalryIcon, c);
		c.gridwidth = 2;
		c.gridx = 1;
		armyFrame.add(cavalryLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		armyFrame.add(cavalry, c);

		c.gridx = 0;
		c.gridy = 2;
		armyFrame.add(artilleryIcon, c);
		c.gridwidth = 2;
		c.gridx = 1;
		armyFrame.add(artilleryLabel, c);
		c.gridwidth = 1;
		c.gridx = 3;
		armyFrame.add(artillery, c);


		armyFrame.setPreferredSize(new Dimension(400, 200));
		armyFrame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		armyFrame.setLocation(dim.width / 2 - armyFrame.getSize().width / 2,
				dim.height / 2 - ((armyFrame.getSize().height) / 2) - 30);
		armyFrame.setVisible(true);

	}

	public void armySelectionScreen() {
		selectFrame = new JFrame("Army Selection");
		thisArmy = t.getArmy();
		int infantry = thisArmy.getInf();
		int cavalry = thisArmy.getCav();
		int artillery = thisArmy.getArt();

		setIcons();

		JButton move = new JButton("Move");
		move.addActionListener(this);
		move.setActionCommand("move");

		selectFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weighty = 1;
		c.weightx = 1;

		JLabel title = new JLabel("Unit Totals");
		JLabel infantryTotal = new JLabel(infantry + " units");
		JLabel cavalryTotal = new JLabel(cavalry + " units");
		JLabel artilleryTotal = new JLabel(artillery + " units");

		c.gridx = 0;
		c.gridwidth = 2;
		selectFrame.add(title, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		selectFrame.add(infantryIcon, c);

		c.gridx = 1;
		selectFrame.add(infantryTotal, c);

		c.gridx = 0;
		c.gridy = 2;
		selectFrame.add(cavalryIcon, c);

		c.gridx = 1;
		selectFrame.add(cavalryTotal, c);

		c.gridx = 0;
		c.gridy = 3;
		selectFrame.add(artilleryIcon, c);

		c.gridx = 1;
		selectFrame.add(artilleryTotal, c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		selectFrame.add(move, c);

		selectFrame.setPreferredSize(new Dimension(200, 400));
		selectFrame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		selectFrame.setLocation(dim.width / 2 - selectFrame.getSize().width / 2,
				dim.height / 2 - ((selectFrame.getSize().height) / 2) - 30);
		selectFrame.setVisible(true);


	}

	public void addToQueue(Player p, Event e){
		p.addEvent(e);
	}

	public void removeFromQueue(Player p, Event e){
		p.removeEvent(e);
	}

	public void takeTurn() {

		for(Player p : players){
			if(p.getName().equals("Computer")){
				ComputerPlayer pl = (ComputerPlayer)p;
				pl.takeTurn(this);
			}
			ArrayList<Event> queue = p.getQueue();
			for (Event e : queue) {
				e.increment();
			}
			for (int index = 0; index < queue.size(); index++) {
				Event e = queue.get(index);
				if (e.getTurns() == e.getMaxTurns()) {
					p.removeEvent(e);
				}
			}

		}
		setVisibility();
		gameFrame.repaintElements();
	}

	public void moveScreen() {
		try{
			selectFrame.dispose();
		}catch(Exception e){}
		int x = t.getX() / 50;
		int y = t.getY() / 50;

		int distance = thisArmy.getDistance();


		for (int xIter = x - distance; xIter <= x + distance; xIter++) {
			for (int yIter = y - distance; yIter <= y + distance; yIter++) {
				try {
					map[xIter][yIter].setState(true);
					map[xIter][yIter].repaint();
					moveMode = true;
				} catch (Exception e) {
				}
			}
		}


	}

}
