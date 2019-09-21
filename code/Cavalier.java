package xiangqi;

public class Cavalier extends Piece
{
	public Cavalier(String nom, String couleur)
	{
		super(nom, couleur);
	}
	
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		boolean testImmobilite = depart.getLigne()  == arrivee.getLigne() && depart.getColonne()  == arrivee.getColonne();
		
		//série de tests pour vérifier que le mouvement constitue un mouvement droit + oblique || 2 combos légaux
		//combo 1:
		boolean testMouvement2Colonnes = arrivee.getColonne() == depart.getColonne() + 2 || arrivee.getColonne() == depart.getColonne() - 2;
		boolean testMouvement1Ligne = arrivee.getLigne() == depart.getLigne() + 1 || arrivee.getLigne() == depart.getLigne() - 1;
		
		//combo 2:
		boolean testMouvement1Colonne = arrivee.getColonne() == depart.getColonne() + 1 || arrivee.getColonne() == depart.getColonne() - 1;
		boolean testMouvement2Lignes = arrivee.getLigne() == depart.getLigne() + 2 || arrivee.getLigne() == depart.getLigne() - 2;
		
		boolean testMouvement = ( testMouvement2Colonnes && testMouvement1Ligne ) || ( testMouvement1Colonne && testMouvement2Lignes );
		
		if ( testMouvement || testImmobilite )
			validite = true;
		
		return validite;
	}
}
