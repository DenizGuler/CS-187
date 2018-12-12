/*
*	Represents the "treasure" game piece. Type of "Treasure" and show is "T"
*/
public class TreasurePiece extends GamePiece {
	
	public TreasurePiece(int points) {
		super(points);
	}

	public String getType() {
		return "Treasure";
	}

	public String show() {
		return "$";
	}
}