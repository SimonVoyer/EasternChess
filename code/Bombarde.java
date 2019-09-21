package xiangqi;

public class Bombarde extends Piece
{
	public Bombarde(String nom, String couleur)
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
