
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameFrame implements ActionListener,MouseListener{
	private JFrame frame;
	private Board b;
	private JFrame f;
	private JPanel infoPanel = new JPanel();

	private JLabel humanBalance = new JLabel("g");
	private JLabel humanIncome = new JLabel("gpt");
	private JLabel humanWood = new JLabel("w");
	private JLabel humanSteel = new JLabel("s");
	private JLabel humanWoodProduction = new JLabel("wpt");
	private JLabel humanSteelProduction = new JLabel("spt");

	private JButton selectArmy;
	private JButton createBuildings;
	private JButton createArmies;
	private JButton endTurn;
	private JButton convertTile;

	private String color = "blue";
	private int difficulty;
	private String name;
	private JTextField country;

	private JButton r;
	private JButton bl;
	private JButton y;
	private JButton pr;
	
	private ImageIcon gold;
	private ImageIcon steel;
	private ImageIcon wood;

	private JPanel tilePanel;

	private int width;
	
	
	public void runGui(){

		frame = new JFrame("Lords And Kings v1.01");

		JLabel label1 = new JLabel("Welcome to Lords and Kings! Enter your ruler's name:");
		country = new JTextField("");

		JLabel label2 = new JLabel("Select your color:");

		r = new JButton("red");
		r.setActionCommand("red");
		r.addActionListener(this);
		r.setBackground(Color.RED);

		bl = new JButton("blue");
		bl.setActionCommand("blue");
		bl.addActionListener(this);
		bl.setBackground(Color.BLUE);

		y = new JButton("yellow");
		y.setActionCommand("yellow");
		y.addActionListener(this);
		y.setBackground(Color.YELLOW);

		pr = new JButton("purple");
		pr.setActionCommand("purple");
		pr.addActionListener(this);
		pr.setBackground(new Color(150, 0, 255));

		JButton easy = new JButton("Easy");
		easy.setActionCommand("easy");
		easy.addActionListener(this);

		JButton medium = new JButton("Medium");
		medium.setActionCommand("medium");
		medium.addActionListener(this);

		JButton hard = new JButton("Hard");
		hard.setActionCommand("hard");
		hard.addActionListener(this);

		JLabel label = new JLabel("To continue, select your difficulty.");
		label.setBackground(frame.getBackground());
		label.setHorizontalAlignment(JLabel.CENTER);

		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(5,5,5,5);
		c.gridwidth = 4;
		frame.add(label1,c);

		c.gridwidth = 4;
		c.gridy = 1;
		frame.add(country,c);

		c.gridy = 2;
		frame.add(label2,c);

		c.gridwidth=1;
		c.gridy = 3;
		frame.add(r,c);
		c.gridx = 1;
		frame.add(bl,c);
		c.gridx = 2;
		frame.add(y,c);
		c.gridx = 3;
		frame.add(pr,c);

		c.gridwidth = 4;
		c.gridy = 4;
		c.gridx = 0;
		frame.add(label,c);

		c.gridy = 5;
		c.gridwidth = 1;
		frame.add(easy,c);
		c.gridx=1;
		frame.add(medium,c);
		c.gridx=2;
		frame.add(hard,c);

		frame.pack();
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setVisible(true);
	}

	public static void main(String[] args){
		GameFrame g = new GameFrame();
		g.runGui();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("red")){
			color = "red";
			r.setSelected(true);
		}else if(e.getActionCommand().equals("blue")) {
			color = "blue";
			bl.setSelected(true);
		}else if(e.getActionCommand().equals("yellow")) {
			color = "yellow";
			y.setSelected(true);
		}else if(e.getActionCommand().equals("purple")) {
			color = "purple";
			pr.setSelected(true);
		}else if(e.getActionCommand().equals("easy")) {
			difficulty = 1;
			name = country.getText();
			runGame();
		}else if(e.getActionCommand().equals("medium")){
			difficulty = 2;
			name = country.getText();
			runGame();
		}else if(e.getActionCommand().equals("hard")){
			difficulty = 3;
			name = country.getText();
			runGame();
		}

	}

	public void setIcons(){
		try {
			File f = new File(getClass().getResource("images/gold.png").toURI());
			BufferedImage image = ImageIO.read(f);
			gold = new ImageIcon(image);
			f = new File(getClass().getResource("images/steel.png").toURI());
			image = ImageIO.read(f);
			steel = new ImageIcon(image);
			f = new File(getClass().getResource("images/wood.png").toURI());
			image = ImageIO.read(f);
			wood = new ImageIcon(image);
		}catch(Exception e){
			e.printStackTrace();
			gold = new ImageIcon();
			steel = new ImageIcon();
			wood = new ImageIcon();
		}
	}
	
	public void runGame() {
		
		setIcons();
		
		frame.dispose();
		HumanPlayer player = new HumanPlayer(color, 40.0, name);
		b = new Board(difficulty,player,this);
		JScrollPane p;
		if(difficulty==1){
			width = 820;
		}else if(difficulty == 2){
			width = 880;
		}else {
			width = 980;
		}

		f = new JFrame("Lords and Kings v1.01");
		p = new JScrollPane(b);
		p.getVerticalScrollBar().setUnitIncrement(32);
		p.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		f.setLayout(new BorderLayout());
		f.add(p, BorderLayout.CENTER);
		tilePanel = new JPanel();
		tilePanel.add(new JLabel("Tile Information:"));
		tilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
		f.add(tilePanel, BorderLayout.WEST);

		endTurn = new JButton("End Turn");

		infoPanel.setLayout(new GridLayout(1, 6));
		
		humanBalance = new JLabel(b.getHumanBalance() + " g",gold,JLabel.CENTER);
		humanIncome = new JLabel(b.getHumanIncome() + " g/turn");
		humanSteel = new JLabel(b.getHumanSteel() + " s",steel,JLabel.CENTER);
		humanWood = new JLabel(b.getHumanWood() + " w",wood,JLabel.CENTER);
		humanWoodProduction = new JLabel(b.getPlayer().getWoodProduction()+" w/turn");
		humanSteelProduction = new JLabel(b.getPlayer().getSteelProduction()+" s/turn");
		
		infoPanel.add(new JLabel("Player information:"));
		infoPanel.add(humanBalance);
		infoPanel.add(humanIncome);
		infoPanel.add(humanSteel);
		infoPanel.add(humanSteelProduction);
		infoPanel.add(humanWood);
		infoPanel.add(humanWoodProduction);
		infoPanel.add(endTurn);

		f.add(infoPanel, BorderLayout.NORTH);

		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.takeTurn();
				b.refreshStats();
				playerInfo();
			}
		});

		f.setPreferredSize(new Dimension(width, 700));
		f.pack();

		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width / 2 - f.getSize().width / 2, dim.height / 2 - ((f.getSize().height) / 2) - 30);
		f.setVisible(true);
	}

	public void playerInfo(){
		f.remove(infoPanel);

		infoPanel.removeAll();

		humanBalance = new JLabel(b.getHumanBalance() + " g",gold,JLabel.CENTER);
		humanIncome = new JLabel(b.getHumanIncome() + " g/turn");
		humanSteel = new JLabel(b.getHumanSteel() + " s",steel,JLabel.CENTER);
		humanWood = new JLabel(b.getHumanWood() + " w",wood,JLabel.CENTER);
		humanWoodProduction = new JLabel(b.getPlayer().getWoodProduction()+" w/turn");
		humanSteelProduction = new JLabel(b.getPlayer().getSteelProduction()+" s/turn");

		infoPanel.add(new JLabel("Player information:"));
		infoPanel.add(humanBalance);
		infoPanel.add(humanIncome);
		infoPanel.add(humanSteel);
		infoPanel.add(humanSteelProduction);
		infoPanel.add(humanWood);
		infoPanel.add(humanWoodProduction);
		infoPanel.add(endTurn);

		f.setPreferredSize(new Dimension(width, 700));
		f.add(infoPanel, BorderLayout.NORTH);
		f.pack();
	}

	public void tileInfo(){
		try {
			tilePanel.removeAll();
			JLabel label =new JLabel("Tile Information:");
			Tile t = b.returnSelected();

			if(t.getVisibility()) {
				JTextArea tileIncome = new JTextArea(t.getIncome() + " gold per turn");
				tileIncome.setSize(50, 10);
				tileIncome.setBackground(tilePanel.getBackground());
				tileIncome.setEditable(false);

				JTextArea tileWood = new JTextArea(t.getWoodProduction() + " wood per turn");
				tileWood.setSize(50, 10);
				tileWood.setBackground(tilePanel.getBackground());
				tileWood.setEditable(false);

				JTextArea tileSteel = new JTextArea(t.getSteelProduction() + " steel per turn");
				tileSteel.setSize(50, 10);
				tileSteel.setBackground(tilePanel.getBackground());
				tileSteel.setEditable(false);

				JTextArea tileDefense = new JTextArea(t.getDefense() + " total defense value");
				tileDefense.setSize(50, 10);
				tileDefense.setBackground(tilePanel.getBackground());
				tileDefense.setEditable(false);

				JTextArea tileStrength = new JTextArea(t.getStrength() + " total strength");
				tileStrength.setSize(50, 10);
				tileStrength.setBackground(tilePanel.getBackground());
				tileStrength.setEditable(false);

				String name1;
				try {
					name1 = t.getController().getName();
				} catch (Exception e) {
					name1 = "Uncontrolled";
				}
				JTextArea tileController = new JTextArea(name1);
				tileController.setSize(50, 10);
				tileController.setBackground(tilePanel.getBackground());
				tileController.setEditable(false);

				selectArmy = new JButton("Select Army");
				selectArmy.setActionCommand("select army");
				selectArmy.addActionListener(b);
				createBuildings = new JButton("Create Buildings");
				createBuildings.setActionCommand("create buildings");
				createBuildings.addActionListener(b);
				createArmies = new JButton("Create Armies");
				createArmies.setActionCommand("create armies");
				createArmies.addActionListener(b);
				
				convertTile = new JButton("Convert Tile");
				convertTile.setActionCommand("convert");
				convertTile.addActionListener(b);

				if (!name1.equals("Uncontrolled") && !name1.equals("Computer")) {
					createBuildings.setEnabled(true);
					createArmies.setEnabled(true);
					convertTile.setEnabled(false);
				} else {
					createBuildings.setEnabled(false);
					createArmies.setEnabled(false);

					convertTile.setEnabled(true);


				}

				if(t.getArmy() != null && t.getArmy().getColor().equals(b.getPlayer().getColor())){
					selectArmy.setEnabled(true);
				}else{
					selectArmy.setEnabled(false);
				}

				tilePanel.setLayout(new GridLayout(11, 1, 30, 30));
				tilePanel.add(label);
				tilePanel.add(tileController);
				tilePanel.add(tileIncome);
				tilePanel.add(tileSteel);
				tilePanel.add(tileWood);
				tilePanel.add(tileDefense);
				tilePanel.add(tileStrength);
				tilePanel.add(selectArmy);
				tilePanel.add(createBuildings);
				tilePanel.add(createArmies);
				tilePanel.add(convertTile);
				tilePanel.setPreferredSize(new Dimension(200, 700));

				f.setPreferredSize(new Dimension(width, 700));
				f.pack();
			}


		}catch(Exception e){}

	}

	public void mouseClicked(MouseEvent e) {tileInfo();}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void repaintElements(){
		tileInfo();
		playerInfo();
	}



}
