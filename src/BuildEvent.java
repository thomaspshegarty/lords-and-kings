public class BuildEvent extends Event {
	private Tile toBuild;
	private String building;			
	private Building created;
	private Player player;

	public void increment() {
		super.increment();
		toBuild.updateProgress(getMaxTurns(), getTurns());
		toBuild.repaint();
	}

	BuildEvent(Tile t, Building b, Player p) {
		player = p;
		setMaxTurns(b.getTurns());
		t.updateProgress(b.getTurns(), 0);
		t.setOperated(true);
		toBuild = t;
		created = b;
	}

	public void fireEvent() {
		toBuild.addBuilding(created);
		toBuild.setOperated(false);
		toBuild.repaint();
	}
}

