package xiangqi;

/** 
 * classe mandarin représentant le compertement du mandarin dans le jeu xiangqi
 * @author Simon Voyer
 * @version 1
*/

public class Mandarin extends Piece
{
	public Mandarin(String nom, String couleur)
	{
		super(nom, couleur);
	}
	
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		boolean testImmobilite = depart.getLigne()  == arrivee.getLigne() && depart.getColonne()  == arrivee.getColonne();
		
		//série de tests pour vérifier que le mandarin est dans son palais
		boolean testColonnePalais = arrivee.getColonne() > 2 && arrivee.getColonne() < 6;
		boolean testLigneRouge = getCouleur() == "rouge" && arrivee.getLigne() > 6 && arrivee.getLigne() <= 9;
		boolean testLigneNoir = getCouleur() == "noir" && arrivee.getLigne() >= 0 && arrivee.getLigne() < 3;	
		boolean testPalais = testColonnePalais && ( testLigneRouge || testLigneNoir );
		
		//série de tests pour vérifier que le mandarin a bougé de 1 oblique
		boolean testLigne = depart.getLigne() + 1 == arrivee.getLigne() || depart.getLigne() - 1 == arrivee.getLigne();
		boolean testColonne = depart.getColonne() + 1 == arrivee.getColonne() || depart.getColonne() - 1 == arrivee.getColonne();
		boolean testMouvement = testLigne && testColonne;
		
		if ( ( testPalais && testMouvement ) || testImmobilite)
			validite = true;
			
		return validite;
	}
	
}
