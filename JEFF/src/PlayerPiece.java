/*
* Represents the "player" Game Piece. Type of "Player" and show is "P" 
*/
public class PlayerPiece extends GamePiece {

	public PlayerPiece(int life) {
		super(life);
	}

	public String getType() {
		return "Player";
	}

	public String show() {
		return "P";
	}
}