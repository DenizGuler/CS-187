/*
* Represents the "Troll" Piece. It's type is "Troll" and show is "T"
*/
public class TrollPiece extends GamePiece {
	
	public TrollPiece() {
		super();
	}

	public String getType() {
		return "Troll";
	}

	public String show() {
		return "T";
	}

	public String toString(){
		return "Troll, T";
	}
}