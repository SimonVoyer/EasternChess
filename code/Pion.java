package xiangqi;

public class Pion extends Piece
{
	public Pion(String nom, String couleur)
	{
		super(nom, couleur);
	}
	
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		boolean testImmobilite = depart.getLigne()  == arrivee.getLigne() && depart.getColonne()  == arrivee.getColonne();
		
		//test de zone
		boolean zoneRouge = depart.getLigne() > 4;
		boolean zoneNoire = depart.getLigne() < 5;
		
		//on v�rifie que la ligne a chang� de 1 et que la colonne est la m�me qu'avant; test varie selon couleur
		boolean testLigneNoir = arrivee.getLigne() == depart.getLigne() + 1 && arrivee.getColonne() == depart.getColonne() ;
		boolean testLigneRouge = arrivee.getLigne() == depart.getLigne() -1 && arrivee.getColonne() == depart.getColonne() ;
		
		//on v�rifie que la colonne a chang� de 1 et que la ligne est la m�me qu'avant
		boolean testColonne = (arrivee.getColonne() == depart.getColonne() +1 || arrivee.getColonne() == depart.getColonne() - 1) && arrivee.getLigne() == depart.getLigne() ;
		
		if (getCouleur() == "noir")
		{
			if ( zoneNoire && testLigneNoir)
				validite = true;
			
			if ( zoneRouge && ( testLigneNoir || testColonne ))
				validite = true;
		}		
		
		if (getCouleur() == "rouge")
		{
			if ( zoneRouge  && testLigneRouge)
				validite = true;
			
			if ( zoneNoire && ( testLigneRouge || testColonne ))
				validite = true;
		}
		
		if ( testImmobilite )
			validite = true;
		
		
		return validite;
	}
		
}
