package xiangqi;

public class Intersection 
{

	private Piece piece;

	public Intersection(){
		piece = null;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public boolean estOccupee(){
		if ( getPiece() == null )
			return false;
		else
			return true; }
	
}
