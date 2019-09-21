package xiangqi;

public class Char extends Piece
{
	public Char(String nom, String couleur)
	{
		super(nom, couleur);
	}
	
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		
		if (depart.getLigne() == arrivee.getLigne() ||depart.getColonne() == arrivee.getColonne())
			validite = true;

		return validite;
	}
}
