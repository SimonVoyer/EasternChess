package xiangqi;

public class Elephant extends Piece
{
	public Elephant(String nom, String couleur)
	{
		super(nom, couleur);
	}
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		
		boolean testLigne = depart.getLigne() + 2 == arrivee.getLigne() || depart.getLigne() - 2 == arrivee.getLigne();
		
		boolean testColonne = depart.getColonne() + 2 == arrivee.getColonne() || depart.getColonne() - 2 == arrivee.getColonne();
		
		boolean testCouleur = (getCouleur() == "rouge" && ( arrivee.getLigne() > 4 ) ) || ( getCouleur() == "noir" && (arrivee.getLigne() < 5) );
		
		boolean testImmobilite = depart.getLigne()  == arrivee.getLigne() && depart.getColonne()  == arrivee.getColonne();
		
		if ( ( testLigne && testColonne && testCouleur ) || testImmobilite)
			validite = true;
				

		return validite;
		
	}
	
}
