package xiangqi;

public class Roi extends Piece
{
	public Roi(String nom, String couleur)
	{
		super(nom, couleur);
	}
	
	public boolean estValide(Position depart, Position arrivee)
	{
		boolean validite = false;
		boolean testImmobilite = depart.getLigne()  == arrivee.getLigne() && depart.getColonne()  == arrivee.getColonne();

		
		//s�rie de tests pour v�rifier que le roi est dans son palais
		boolean testColonnePalais = arrivee.getColonne() > 2 && arrivee.getColonne() < 6;
		boolean testLigneRouge = getCouleur() == "rouge" && arrivee.getLigne() > 6 && arrivee.getLigne() <= 9;
		boolean testLigneNoir = getCouleur() == "noir" && arrivee.getLigne() >= 0 && arrivee.getLigne() < 3;	
		boolean testPalais = testColonnePalais && ( testLigneRouge || testLigneNoir );
		
		//s�rie de tests pour v�rifier que le roi a boug� de 1 vertical ou horizontal
		boolean testHorizontal = arrivee.getLigne() == depart.getLigne() && ( arrivee.getColonne() == depart.getColonne() + 1 || arrivee.getColonne() == depart.getColonne() - 1 );
		boolean testVertical = arrivee.getColonne() == depart.getColonne() && ( arrivee.getLigne() == depart.getLigne() + 1 || arrivee.getLigne() == depart.getLigne() - 1 );
		boolean testMouvement = testHorizontal || testVertical;
		
		if ( ( testPalais && testMouvement ) || testImmobilite)
			validite = true;
			
		return validite;
	}
	
}
