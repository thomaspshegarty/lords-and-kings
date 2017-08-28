public class CreateEvent extends Event {
		private Tile toCreate;
		private Unit created;
		private Player player;

		public void increment() {
			super.increment();
			toCreate.updateProgress(getMaxTurns(), getTurns());
			toCreate.repaint();
		}

		CreateEvent(Tile t, Unit u, Player p) {
			player = p;
			if (u.getFilename().contains("cavalry")) {
				setMaxTurns(3);
				t.updateProgress(3, 0);
			} else if (u.getFilename().contains("infantry")) {
				setMaxTurns(2);
				t.updateProgress(2, 0);
			} else {
				setMaxTurns(4);
				t.updateProgress(4, 0);
			}
			t.setOperated(true);
			toCreate = t;
			created = u;
		}

		public void fireEvent() {
			Army a = new Army(created, player.getColor(),toCreate);
			player.addArmy(a);
			toCreate.setArmy(a);
			toCreate.setOperated(false);
			toCreate.repaint();
		}
	}