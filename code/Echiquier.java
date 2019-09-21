package xiangqi;

public class Echiquier implements MethodesEchiquier
{
	private Intersection[][] jeu;
	
	public static final int NB_LIGNES = 10;
	public static final int NB_COLONNES = 9;
	
	public Echiquier()
	{
		jeu = new Intersection[NB_LIGNES][NB_COLONNES];
		for ( int i = 0; i < NB_LIGNES; i++ )
			for ( int j = 0; j < NB_COLONNES; j++ )
				jeu[i][j] = new Intersection();
	}

	public Intersection[][] getJeu() {
		return jeu;
	}

	public void setJeu(Intersection[][] jeu) {
		this.jeu = jeu;
	}
	
	public Intersection getIntersection( int ligne, int colonne ) {
		return jeu[ligne][colonne];
	}
	
	
	public void debuter()
	{
		//Section noire
		jeu[0][0].setPiece(new Char("t1", "noir"));
		jeu[0][8].setPiece(new Char("t2", "noir"));
		jeu[0][1].setPiece(new Cavalier("c1", "noir"));
		jeu[0][7].setPiece(new Cavalier("c2", "noir"));
		jeu[0][2].setPiece(new Elephant("e1", "noir"));
		jeu[0][6].setPiece(new Elephant("e2", "noir"));
		jeu[0][3].setPiece(new Mandarin("m1", "noir"));
		jeu[0][5].setPiece(new Mandarin("m2", "noir"));
		jeu[0][4].setPiece(new Roi("r", "noir"));
		jeu[2][1].setPiece(new Bombarde("b1", "noir"));
		jeu[2][7].setPiece(new Bombarde("b2", "noir"));
		jeu[3][0].setPiece(new Pion("p1", "noir"));
		jeu[3][2].setPiece(new Pion("p2", "noir"));
		jeu[3][4].setPiece(new Pion("p3", "noir"));
		jeu[3][6].setPiece(new Pion("p4", "noir"));
		jeu[3][8].setPiece(new Pion("p5", "noir"));
//-----------------------------------------------------------\\
		// Section rouge
		jeu[9][0].setPiece(new Char("t1", "rouge"));
		jeu[9][8].setPiece(new Char("t2", "rouge"));
		jeu[9][1].setPiece(new Cavalier("c1", "rouge"));
		jeu[9][7].setPiece(new Cavalier("c2", "rouge"));
		jeu[9][2].setPiece(new Elephant("e1", "rouge"));
		jeu[9][6].setPiece(new Elephant("e2", "rouge"));
		jeu[9][3].setPiece(new Mandarin("m1", "rouge"));
		jeu[9][5].setPiece(new Mandarin("m2", "rouge"));
		jeu[9][4].setPiece(new Roi("r", "rouge"));
		jeu[7][1].setPiece(new Bombarde("b1", "rouge"));
		jeu[7][7].setPiece(new Bombarde("b2", "rouge"));
		jeu[6][0].setPiece(new Pion("p1", "rouge"));
		jeu[6][2].setPiece(new Pion("p2", "rouge"));
		jeu[6][4].setPiece(new Pion("p3", "rouge"));
		jeu[6][6].setPiece(new Pion("p4", "rouge"));
		jeu[6][8].setPiece(new Pion("p5", "rouge"));
	}
	
	public boolean cheminPossible ( Position  depart , Position arrivee)
	{
		Piece piece = jeu[depart.getLigne()][depart.getColonne()].getPiece();
		
		int ligneDepart = depart.getLigne();
		int colonneDepart = depart.getColonne();
		int ligneArrivee = arrivee.getLigne();
		int colonneArrivee = arrivee.getColonne();
		int diffColonne = Math.abs(arrivee.getColonne() - colonneDepart);
		int diffLigne = Math.abs(arrivee.getLigne() - ligneDepart);
		
		boolean versHaut = ligneArrivee < ligneDepart;
		boolean versBas = ligneArrivee > ligneDepart;
		boolean versDroite = colonneArrivee > colonneDepart;
		boolean versGauche = colonneArrivee < colonneDepart;

		
		//test immobilité, si oui, on n'a pas besoin de faire plus de tests, tout devrait être correct
		if (diffColonne == 0 && diffLigne == 0)
			return true;
		
		//on vérifie si le mouvement causerait problème au roi, le cas échéant, il n'est pas nécessaire de faire les autres tests
		if (!roisNePouvantPasEtreFaceAFace( depart, arrivee ))
			return false;
		
//----------------------------------------------------------------------------------------------------------------------------------------\\
// 										Section des tests de chaque pièce																				   \\
//----------------------------------------------------------------------------------------------------------------------------------------\\
		
		//case 1,2,3: les pièces qui ne bougent que de 1 espace, on vérifie directement l'arrivee
		if (piece instanceof Pion || piece instanceof Roi || piece instanceof Mandarin )
		{
			if (!arriveeCorrect( piece.getCouleur(), arrivee))
				return false;
			else return true;
		}
		
		//case 4: char   
		else if (piece instanceof Char)
		{
			//on vérifie d'abord l'arrivée, si elle n'est pas correct il n'y a pas de raison de faire les autres tests
			if (!arriveeCorrect( piece.getCouleur(), arrivee))
					return false;
			//on vérifie ensuite les quatre directions possibles (lignes droites)
			if (versHaut && !testLigneVersHaut(ligneDepart, colonneDepart, diffLigne))
				return false;
			else if (versBas && !testLigneVersBas(ligneDepart, colonneDepart, diffLigne))
				return false;
			else if (versDroite  && !testColonneVersDroite(ligneDepart, colonneDepart, diffColonne))
				return false;
			else if (versGauche && !testColonneVersGauche(ligneDepart, colonneDepart, diffColonne))
				return false;
			else return true;

		}
		
		//case 5 : éléphant
		else if (piece instanceof Elephant )
		{
			//on vérifie d'abord l'arrivée, si elle n'est pas correcte, il n'y a pas de raison de faire les autres tests
			if (!arriveeCorrect( piece.getCouleur(), arrivee))
					return false;
			//on vérifie ensuite les quatre directions possibles (quatre obliques)
			if (versHaut && versDroite && !testObliqueDroiteHaut(ligneDepart, colonneDepart, diffColonne) )
				return false;
			else if (versHaut && versGauche && !testObliqueGaucheHaut(ligneDepart, colonneDepart, diffColonne) )
				return false;
			else if (versBas && versDroite && !testObliqueDroiteBas(ligneDepart, colonneDepart, diffColonne) )
				return false;
			else if (versBas && versGauche && !testObliqueGaucheBas(ligneDepart, colonneDepart, diffColonne) )
				return false;
			else return true;
		}
		
		//case 6: cavalier
		else if (piece instanceof Cavalier )
		{
			//on vérifie d'abord l'arrivée, si elle n'est pas correcte, il n'y a pas de raison de faire les autres tests
			if (!arriveeCorrect( piece.getCouleur(), arrivee))
					return false;
			
			//on doit maintenant tester qu'il n'y a aucune pièce qui bloque le premier mouvement en ligne droite
			//on fait le test à l'envers, c'est-à-dire qu'on part du point d'arrivé, et on regarde en diagonale "inverse" s'il y a une pièce
			//notez également que pour le cavalier, diffLigne/diffColonne  est "hard codé" à 2 parce qu'on veut que le cavalier bouge spécifiquement de 1 dans chaque cas 
			
			if (versHaut && versDroite && !testObliqueGaucheBas(ligneArrivee, colonneArrivee,2) )
				return false;
			else if (versHaut && versGauche && !testObliqueDroiteBas(ligneArrivee, colonneArrivee,2) )
				return false;
			else if (versBas && versDroite && !testObliqueGaucheHaut(ligneArrivee, colonneArrivee,2))
				return false;
			else if (versBas && versGauche && !testObliqueDroiteHaut(ligneArrivee, colonneArrivee,2))
				return false;
			else 
				return true;
		}
		
		//case 7: bombarde   
		//la bombarde ressemble au char, mais vu son critère de capture très particulier, il est pertinent de traiter son cas à part
		else if (piece instanceof Bombarde)
		{
			//on vérifie d'abord l'arrivée, si elle n'est pas correcte, il n'y a pas de raison de faire les autres tests
			if (!arriveeCorrect( piece.getCouleur(), arrivee))
				return false;
			
			Piece pieceArrivee = getIntersection(ligneArrivee, colonneArrivee).getPiece();
			
			
			//il faut vérifier qu'il y a exactement une pièce entre la bombarde et l'arrivée, on traite donc quatre cas
			int compteurPieces = 0;
			if (versHaut)
			{
				for ( int i = 1 ; i < diffLigne; i++){
					if (jeu[ligneDepart - i][colonneDepart].estOccupee())
						compteurPieces +=1; }
			}
			else if (versBas)
			{
				for ( int i = 1 ; i < diffLigne; i++){
					if (jeu[ligneDepart + i][colonneDepart].estOccupee())
						compteurPieces +=1; }
			}
			else if (versGauche)
			{
				for ( int i = 1 ; i < diffColonne; i++){
					if (jeu[ligneDepart][colonneDepart - i].estOccupee())
						compteurPieces +=1; }
			}
			else if (versDroite)
			{
				for ( int i = 1 ; i < diffColonne; i++){
					if (jeu[ligneDepart][colonneDepart + i].estOccupee())
						compteurPieces +=1; }
			}
			
			if (pieceArrivee != null && compteurPieces == 1)
				return true;
			else if (pieceArrivee == null && compteurPieces == 0)
				return true;
			else return false;
		}
		else return false; //si la pièce est en dehors du domaine, il y a un problème
		
	}
	
	public boolean testLigneVersHaut(int ligneDepart, int colonneDepart, int diffLigne)
	{
		for ( int i = 1 ; i < diffLigne; i++){
			if (jeu[ligneDepart - i][colonneDepart].estOccupee())
				return false; }
		return true;
	}
	
	public boolean testLigneVersBas(int ligneDepart, int colonneDepart, int diffLigne)
	{
		for ( int i = 1 ; i < diffLigne; i++){
			if (jeu[ligneDepart + i][colonneDepart].estOccupee())
				return false; }
		return true;
	}
	
	public boolean testColonneVersDroite(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart][colonneDepart + i].estOccupee())
				return false;
		return true;
	
	}
	
	public boolean testColonneVersGauche(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart ][colonneDepart - i].estOccupee())
				return false; 
		return true;
	}
	
	public boolean testObliqueDroiteBas(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart + i][colonneDepart + i].estOccupee())
				return false;
		return true;
	}
	
	public boolean testObliqueGaucheHaut(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart - i][colonneDepart - i].estOccupee())
				return false; 
		return true;
	}
	
	public boolean testObliqueGaucheBas(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart + i][colonneDepart - i].estOccupee())
				return false;
		return true;
	}
	
	public boolean testObliqueDroiteHaut(int ligneDepart, int colonneDepart, int diffColonne)
	{
		for ( int i = 1 ; i < diffColonne; i++)
			if (jeu[ligneDepart - i][colonneDepart + i].estOccupee())
				return false;
		return true;
	}
	
	public boolean arriveeCorrect(String couleur, Position arrivee)
	{
		
		Intersection pointArrivee = jeu[arrivee.getLigne()][arrivee.getColonne()];
		
		//s'il n'y a pas de pièce à l'arrivée, pas de problème
		if (!(pointArrivee.estOccupee()))
				return true;
		//s'il y a piece mais couleur différente, pas de problème
		else if ( ! ( couleur.equals( pointArrivee.getPiece().getCouleur() ) ) )
			return true;
		//le dernier cas est celui oui il y a une pièce de même couleur, retourne false
		else
			return false;
	}
	
	public  boolean roisNePouvantPasEtreFaceAFace ( Position depart, Position arrivee ) //true == pas de prob, false == prob
	{
		int ligneDepart = depart.getLigne();
		int colonneDepart = depart.getColonne();
		Piece piece = jeu[ligneDepart][colonneDepart].getPiece();
		int colonneRoiRouge = trouverRoi("colonne", "rouge");
		int colonneRoiNoir = trouverRoi("colonne", "noir");
		
		if ( piece instanceof Roi)
		{
			if (piece.getCouleur().equals("rouge")) 
			{
				if (arrivee.getColonne() != colonneRoiNoir) //si le roi ne finit pas sur la colonne de l'autre roi, pas de problème
					return true;
				else 
				{  //quand ils finissent sur la mm colonne, il faut vérifier qu'il y ait kkch entre les deux
					boolean pieceAvantRoi = false;
					for (int i = depart.getLigne()-1; i >=0; i-- )
					{
						if(jeu[i][colonneRoiNoir].estOccupee() && i != ligneDepart )
						{
							if( !( jeu[i][colonneRoiNoir].getPiece() instanceof Roi ) ) //s'il trouve kkch avant roi, il sauvegarde l'info avec une bool
								pieceAvantRoi = true;
							else if ( jeu[i][colonneRoiNoir].getPiece() instanceof Roi && pieceAvantRoi ) //s'il trouve un roi et qu'il y a une piece avant. pas de prob
								return true;
							else return false;
						}
					} // le cas restant est de trouver un roi sans pièce intermédiaire, ce qui retourn false
					return true; //s'il n'y a aucune piece dans la colonne, on retourne true
				}		
			}
			else { //quand piece est noire, mm algo
					 if (arrivee.getColonne() != colonneRoiRouge) 
							return true;
					boolean pieceAvantRoi = false;
					for (int i = depart.getLigne()+1; i <=9; i++ )
					{
						if(jeu[i][colonneRoiRouge].estOccupee() && i != ligneDepart )
						{
							if( !( jeu[i][colonneRoiRouge].getPiece() instanceof Roi ) ) 
								pieceAvantRoi = true;
							else if ( jeu[i][colonneRoiRouge].getPiece() instanceof Roi && pieceAvantRoi ) 
								return true;
							else 
								return false; 
						}
					}
					return true;
				  } 
			}
		else 
		{
			
			if (colonneRoiNoir != colonneRoiRouge) //si les rois ne sont pas dans la même colonne, il n'y a pas de problème
				return true;
			//si la pièce ne part pas de la colonne royal ou qu'il attérit sur la colonne royal, pas de prob
			else if (depart.getColonne() != colonneRoiNoir || arrivee.getColonne() == colonneRoiNoir ) 
				return true;
			else 
			{ //dans le cas restant, il faut vérifier chaque ligne, mm algo
				boolean pieceAvantRoi = false; 
				int ligneRoiNoir = trouverRoi("ligne", "noir");
				int ligneRoiRouge = trouverRoi("ligne", "rouge"); //ajuster algo
				
				for (int i = ligneRoiNoir+1; i <=ligneRoiRouge; i++ )
					if(jeu[i][colonneRoiRouge].estOccupee() && i != ligneDepart)
					{
						if( !( jeu[i][colonneRoiRouge].getPiece() instanceof Roi ) ) 
							pieceAvantRoi = true;
						else if ( jeu[i][colonneRoiRouge].getPiece() instanceof Roi && pieceAvantRoi ) 
							return true;
						else return false;
					} 
				return true;
			}
		

		}
	}

	
	public int trouverRoi(String coordonnee, String couleur )
	{
		
		if (couleur.equals("noir")  ) // roi noir
		{
		for ( int i = 0; i <=2; i++ )
			for ( int j = 3; j <= 5; j++)
				if(jeu[i][j].getPiece() instanceof Roi)
				{
					if (coordonnee.equals("colonne")) 
						return j;
					else 			//si ligne
						return i;
				}

		}
		else //roi rouge
		{
		for (int i = 7; i <= 9; i++ )
			for ( int j = 3; j <= 5; j++)
				if(jeu[i][j].getPiece() instanceof Roi )
				{
					if (coordonnee.equals("colonne")) 
						return j;
					else 
						return i;
				}
					
					
			
		}
		return -1; //ne devrait pas se rendre ici, le cas échéant, il y a un problème == -1
	}
	


}
