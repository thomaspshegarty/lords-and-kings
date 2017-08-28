public class ConvertEvent extends Event {
	Tile toConvert;
	Player player;

	public void increment(){
		super.increment();
		toConvert.updateProgress(getMaxTurns(), getTurns());
		toConvert.repaint();
	}

	ConvertEvent(Tile t, Player p, Board b){

		setMaxTurns(4);
		t.updateProgress(4, 0);
		t.setOperated(true);
		t.repaint();
		toConvert = t;
		player = p;

	}

	public void fireEvent(){
		toConvert.setPlayer(player);
		player.addControlledTile(toConvert);
		toConvert.refresh();
		toConvert.repaint();
		toConvert.setOperated(false);
	}
}
