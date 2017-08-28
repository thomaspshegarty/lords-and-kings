public class MoveEvent extends Event {
		private Tile currentlyAt;
		private Tile movingTo;
		private Army toMove;

		MoveEvent(Tile from, Tile to, Army moved) {
			setMaxTurns(1);
			currentlyAt = from;
			movingTo = to;
			toMove = moved;
		}

		public void fireEvent() {
			toMove.setLocation(movingTo);
			currentlyAt.removeUnit();
			movingTo.setArmy(toMove);
			currentlyAt.repaint();
			movingTo.repaint();
		}
	}