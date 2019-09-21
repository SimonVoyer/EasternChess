package xiangqi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;



import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.UIManager;

public class FrameXiangQi extends JFrame {

	private JPanel contentPane;
	JPanel panelConteneur;
	JLabel labelImage, labelCouleur;
	JLabel grille[][]; //90 JLabels transparents s'apparentant aux intersections
	JLabel grilleCaptureRouge[];
	JLabel grilleCaptureNoire[];
	JPanel panelControle;
	JButton boutonDebuter, boutonRecommencer;
	Ecouteur ec;
	Echiquier echiquier; //échiquier faisant le lien avec la logique du jeu
	JPanel panelRouge;
	JPanel panelNoir;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameXiangQi frame = new FrameXiangQi();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *constructeur
	 */
	public FrameXiangQi() {
		
		echiquier = new Echiquier(); //création de l'échiquier et des 90 JLabels
		grille = new JLabel[10][9];
		grilleCaptureRouge = new JLabel[16];
		grilleCaptureNoire = new JLabel[16];
		
		
		setTitle("XiangQi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 878);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		panelConteneur= new JPanel();
		panelConteneur.setBackground(new Color(255, 228, 196));
		panelConteneur.setBounds(26, 77, 670, 751);
		panelConteneur.setLayout(new GridLayout(10, 9, 0, 0));
		panelConteneur.setOpaque(false);
		contentPane.add(panelConteneur);
		
		labelImage= new JLabel("");
		labelImage.setBounds(30, 111, 690, 700);
		contentPane.add(labelImage);
		labelImage.setIcon(( new ImageIcon( "icones/fond2.png")));
		
		panelControle = new JPanel();
		panelControle.setBackground(new Color(255, 228, 196));
		panelControle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelControle.setBounds(0, 11, 918, 58);
		contentPane.add(panelControle);
		panelControle.setLayout(null);
		
		boutonRecommencer = new JButton("Recommencer");
		boutonRecommencer.setBounds(744, 22, 152, 23);
		boutonRecommencer.setBackground(new Color(255,239,213));
		boutonRecommencer.setContentAreaFilled(false);
		boutonRecommencer.setOpaque(true);
		panelControle.add(boutonRecommencer);
		boutonRecommencer.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		boutonDebuter = new JButton("D\u00E9buter");
		boutonDebuter.setBackground(new Color(255, 239, 213));
		boutonDebuter.setBounds(610, 22, 119, 23);
		boutonDebuter.setContentAreaFilled(false);
		boutonDebuter.setOpaque(true);
		panelControle.add(boutonDebuter);
		boutonDebuter.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCouleur = new JLabel("");
		labelCouleur.setBackground(new Color(255, 239, 213));
		labelCouleur.setOpaque(true);
		labelCouleur.setBounds(53, 11, 475, 41);
		
		panelControle.add(labelCouleur);
		labelCouleur.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		panelRouge = new JPanel();
		panelRouge.setBackground(new Color(255, 69, 0));
		panelRouge.setBounds(708, 77, 99, 751);
		contentPane.add(panelRouge);
		
		panelNoir = new JPanel();
		panelNoir.setBackground(new Color(0, 0, 0));
		panelNoir.setBounds(817, 77, 101, 751);
		contentPane.add(panelNoir);
		
		
		//gestion des événements 
		ec = new Ecouteur();
		for ( int i = 0 ; i < 10 ; i ++ )
			for ( int j = 0 ; j < 9 ; j ++ )
			{
				grille[i][j] = new JLabel();
				grille[i][j].addMouseListener( ec );
				panelConteneur.add( grille[i][j]);
				grille[i][j].setHorizontalAlignment(SwingConstants.CENTER); //centré  par rapport au label plutôt qu'à gauche (gauche étant la position par défaut)
			}
		for ( int i = 0 ; i < 16 ; i ++ ) {
			grilleCaptureRouge[i] = new JLabel();
			grilleCaptureNoire[i] = new JLabel();
			panelRouge.add(grilleCaptureRouge[i]);
			panelNoir.add(grilleCaptureNoire[i]);
			grilleCaptureRouge[i].setHorizontalAlignment(SwingConstants.CENTER);
			grilleCaptureNoire[i].setHorizontalAlignment(SwingConstants.CENTER);
		}
			
		boutonDebuter.addMouseListener(ec);
		boutonRecommencer.addMouseListener(ec);
	
		
	

	}
	
	private class Ecouteur extends MouseAdapter
	{
		int ligneClic, colonneClic;
		Piece pieceTampon, pieceEnlevee; //tampon = équivalent main qui prend (départ)
		ImageIcon iconeTampon, iconeCapture;
		Position depart, arrivee;
		String couleurControle = "rouge"; //valeur rouge ou noir ; si rouge on peut pas prendre noir, vice versa | rouge semble être généralement utilisé pour commencer
		boolean debuterCliquer;
		int indiceCaptureRouge = 0, indiceCaptureNoir = 0;
		
	
		public void initInterface()	{
			//réinitialisation de l'ensemble du jeu à null
		    for ( int i = 0; i < 10 ; i++ )
			      for ( int j = 0; j< 9; j++ )
			    	  grille[i][j].setIcon(null);
		    
		    //inscription au label pour le premier tour
		    couleurControle = "rouge";
		    labelCouleur.setText(couleurControle);
		    
		    //-------------------section noire ----------------------\\
			grille[0][0].setIcon(new ImageIcon("icones/charNoir.png"));
			grille[0][8].setIcon(new ImageIcon("icones/charNoir.png"));
			grille[0][1].setIcon(new ImageIcon("icones/cavalierNoir.png"));
			grille[0][7].setIcon(new ImageIcon("icones/cavalierNoir.png"));
			grille[0][2].setIcon(new ImageIcon("icones/elephantNoir.png"));
			grille[0][6].setIcon(new ImageIcon("icones/elephantNoir.png"));
			grille[0][3].setIcon(new ImageIcon("icones/mandarinNoir.png"));
			grille[0][5].setIcon(new ImageIcon("icones/mandarinNoir.png"));
			grille[0][4].setIcon(new ImageIcon("icones/roiNoir.png"));
			grille[2][1].setIcon(new ImageIcon("icones/bombardeNoir.png"));
			grille[2][7].setIcon(new ImageIcon("icones/bombardeNoir.png"));
			grille[3][0].setIcon(new ImageIcon("icones/pionNoir.png"));
			grille[3][2].setIcon(new ImageIcon("icones/pionNoir.png"));
			grille[3][4].setIcon(new ImageIcon("icones/pionNoir.png"));
			grille[3][6].setIcon(new ImageIcon("icones/pionNoir.png"));
			grille[3][8].setIcon(new ImageIcon("icones/pionNoir.png"));
			
			//-------------------section rouge ----------------------\\
			grille[9][0].setIcon(new ImageIcon("icones/charRouge.png"));
			grille[9][8].setIcon(new ImageIcon("icones/charRouge.png"));
			grille[9][1].setIcon(new ImageIcon("icones/cavalierRouge.png"));
			grille[9][7].setIcon(new ImageIcon("icones/cavalierRouge.png"));
			grille[9][2].setIcon(new ImageIcon("icones/elephantRouge.png"));
			grille[9][6].setIcon(new ImageIcon("icones/elephantRouge.png"));
			grille[9][3].setIcon(new ImageIcon("icones/mandarinRouge.png"));
			grille[9][5].setIcon(new ImageIcon("icones/mandarinRouge.png"));
			grille[9][4].setIcon(new ImageIcon("icones/roiRouge.png"));
			grille[7][1].setIcon(new ImageIcon("icones/bombardeRouge.png"));
			grille[7][7].setIcon(new ImageIcon("icones/bombardeRouge.png"));
			grille[6][0].setIcon(new ImageIcon("icones/pionRouge.png"));
			grille[6][2].setIcon(new ImageIcon("icones/pionRouge.png"));
			grille[6][4].setIcon(new ImageIcon("icones/pionRouge.png"));
			grille[6][6].setIcon(new ImageIcon("icones/pionRouge.png"));
			grille[6][8].setIcon(new ImageIcon("icones/pionRouge.png"));
			
			//on enlève les icones des zones de capture
			for (int i = 0; i<16;i++) {
			grilleCaptureRouge[i].setIcon(null);
			grilleCaptureNoire[i].setIcon(null);
			}
		
		}
		
		public void recommencer() {
			pieceTampon = null;
			iconeTampon = null;
			echiquier = new Echiquier();
			echiquier.debuter();
			initInterface();
		}
		  
		
	

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if ( e.getSource() == boutonDebuter && !debuterCliquer)	{
				debuterCliquer = true;
				echiquier.debuter();
				initInterface();	}
			else if ( e.getSource() == boutonDebuter && debuterCliquer)
				JOptionPane.showMessageDialog(panelConteneur,"La partie est déjà commencée! Veuillez cliquer sur Recommencer pour réinitialiser la partie.");
			else if ( e.getSource() == boutonRecommencer)	{
					recommencer();
				}
			else 	{ // il s'agit d'un label / intersection
			    //trouver lequel
			    for ( int i = 0; i < 10 ; i++ )
			      for ( int j = 0; j< 9; j++ )
			        if (e.getSource() == grille[i][j]) {
			          ligneClic = i;
			          colonneClic = j;	
			          }


				//case: tampon vide, case occupée = départ
				Intersection intersectionSelectionnee = echiquier.getIntersection(ligneClic, colonneClic);
				boolean isIntersectionOccupee = intersectionSelectionnee.estOccupee();
				if (pieceTampon == null && isIntersectionOccupee && intersectionSelectionnee.getPiece().getCouleur().equals(couleurControle)) {
					depart = new Position(ligneClic, colonneClic);
					pieceTampon = intersectionSelectionnee.getPiece();
					iconeTampon = (ImageIcon) grille[ligneClic][colonneClic].getIcon();
					grille[ligneClic][colonneClic].setIcon(null);	
					
				}
				//case: tampon plein, case vise = déplacement simple
				else if (pieceTampon != null && !isIntersectionOccupee) {
					arrivee = new Position(ligneClic, colonneClic);
					if (pieceTampon.estValide(depart,arrivee)  && echiquier.cheminPossible(depart, arrivee)) {
						echiquier.getIntersection(depart.getLigne(), depart.getColonne()).setPiece(null);
						intersectionSelectionnee.setPiece(pieceTampon);
						grille[ligneClic][colonneClic].setIcon(iconeTampon);
						pieceTampon = null;
						iconeTampon = null;	
						//on ajuste la couleur de contrôle
						if (couleurControle.equals("rouge"))
							couleurControle = "noir";
						else
							couleurControle = "rouge";	
						labelCouleur.setText(couleurControle); // on ajuste le label du tour en cours
						}
					else {
						grille[depart.getLigne()][depart.getColonne()].setIcon(iconeTampon);
						pieceTampon = null;
						iconeTampon = null;		
						}	
					}
				

				//case: tampon plein, case peine, déplacement et capture
				else if (pieceTampon != null && isIntersectionOccupee) {
					arrivee = new Position(ligneClic, colonneClic);
					if (pieceTampon.estValide(depart,arrivee) && echiquier.cheminPossible(depart, arrivee)) {
						if (echiquier.getIntersection(arrivee.getLigne(), arrivee.getColonne()).getPiece() instanceof Roi) {
							JOptionPane.showMessageDialog(panelConteneur,"Partie terminée! Victoire du joueur " +  couleurControle);
							recommencer();
						}
						
						echiquier.getIntersection(depart.getLigne(), depart.getColonne()).setPiece(null);
						pieceEnlevee = intersectionSelectionnee.getPiece();
						iconeCapture = (ImageIcon) grille[ligneClic][colonneClic].getIcon(); //pê enlever le cast ****
						intersectionSelectionnee.setPiece(pieceTampon);
						grille[ligneClic][colonneClic].setIcon(iconeTampon);
						pieceTampon = null;
						iconeTampon = null;	
						//on ajuste la couleur de contrôle
						if (couleurControle.equals("rouge")) {
							couleurControle = "noir";
							grilleCaptureNoire[indiceCaptureNoir++].setIcon(iconeCapture);
							
						} else {
							couleurControle = "rouge";	
							grilleCaptureRouge[indiceCaptureRouge++].setIcon(iconeCapture);
							}
						labelCouleur.setText(couleurControle); // on ajuste le label du tour en cours
						
						}
					else {
						grille[depart.getLigne()][depart.getColonne()].setIcon(iconeTampon);
						pieceTampon = null;
						iconeTampon = null;	}
				}
				
				
				
			  } 
			} // fin de la méthode mouseReleased    
	    }// fin de la classe Ecouteur
	}


