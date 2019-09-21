package xiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ModeleJUnit {

private Bombarde bombarde;
private Elephant elephantRouge;
private Elephant elephantNoir;
private Char pieceChar;
private Cavalier cavalier;
private Mandarin mandarin;
private Mandarin mandarin2;
private Pion pion1;
private Pion pion2;
private Roi roi;
private Roi roi2;


//---------------------------------------------------------------------------
//série de tests pour l'léphant
//---------------------------------------------------------------------------

@Before
public void initialisation()
{
	elephantRouge = new Elephant("e1","rouge");
}


@Test //test 1
public void testElephant1()  
{
	Position depart = new Position(8,1);
	Position arrivee = new Position(6,3);
	assertEquals(true, elephantRouge.estValide(depart, arrivee));
}

@Before
public void initialisation2()
{
	elephantNoir = new Elephant("e1","noir");
 
}


@Test //test 2
public void testElephant2()	
{
	Position depart = new Position(2,2);
	Position arrivee = new Position(4,4);
	assertEquals(true, elephantNoir.estValide(depart, arrivee));
}

//***pour tester plus l'éléphants et pour éviter de décaler tous les tests, d'autres tests ont été ajoutés à la fin pour l'éléphant (autour de ligne 520, test 48 et suivants***

//---------------------------------------------------------------------------
//série de tests pour le cavalier
//---------------------------------------------------------------------------

@Before
public void initialisation3()
{
	cavalier = new Cavalier("c1","noir");
 
}


@Test //test 3
public void testCavalier1()
{
	Position depart = new Position(5,4);
	Position arrivee = new Position(3,5);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}

@Test //test 4
public void testCavalier2()
{
	Position depart = new Position(5,4);
	Position arrivee = new Position(6,2);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}

@Test //test 5
public void testCavalier3()
{
	Position depart = new Position(5,4);
	Position arrivee = new Position(6,6);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}

@Test //test 6
public void testCavalier4()
{
	Position depart = new Position(5,4);
	Position arrivee = new Position(3,3);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}


@Test //test 7
public void testCavalier5()
{
	Position depart = new Position(5,4);
	Position arrivee = new Position(7,4);
	assertEquals(false, cavalier.estValide(depart, arrivee));
}

@Test //test 8
public void testCavalier6()
{
	Position depart = new Position(3,0);
	Position arrivee = new Position(2,2);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}

@Test //test 9
public void testCavalier7()
{
	Position depart = new Position(0,0);
	Position arrivee = new Position(2,2);
	assertEquals(false, cavalier.estValide(depart, arrivee));
}

@Test  //test 10
public void testCavalier8()
{
	Position depart = new Position(9,2);
	Position arrivee = new Position(8,3);
	assertEquals(false, cavalier.estValide(depart, arrivee));
}

@Test  //test 11
public void testCavalier9()
{
	Position depart = new Position(6,6);
	Position arrivee = new Position(9,4);
	assertEquals(false, cavalier.estValide(depart, arrivee));
}

//---------------------------------------------------------------------------
//série de tests pour le roi
//---------------------------------------------------------------------------

@Before //roi noir
public void initialisation4()
{
	roi = new Roi("r1","noir");
 
}


@Test //test 12
public void testRoi1()
{
	Position depart = new Position(1,4);
	Position arrivee = new Position(0,4);
	assertEquals(true, roi.estValide(depart, arrivee));
}

@Test //test 13
public void testRoi2()
{
	Position depart = new Position(0,5);
	Position arrivee = new Position(0,6);
	assertEquals(false, roi.estValide(depart, arrivee));
}

@Test //test 14
public void testRoi3()
{
	Position depart = new Position(2,3);
	Position arrivee = new Position(3,3);
	assertEquals(false, roi.estValide(depart, arrivee));
}

@Test //test 15
public void testRoi4()
{
	Position depart = new Position(2,4);
	Position arrivee = new Position(1,5);
	assertEquals(false, roi.estValide(depart, arrivee));
}

@Test //test 16
public void testRoi5()
{
	Position depart = new Position(1,3);
	Position arrivee = new Position(0,3);
	assertEquals(true, roi.estValide(depart, arrivee));
}

//-------------------------

@Before //roi rouge
public void initialisation5()
{
	roi2 = new Roi("r2","rouge");
 
}

@Test //test 17
public void testRoi6()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(8,3);
	assertEquals(true, roi2.estValide(depart, arrivee));
}

@Test //test 18
public void testRoi7()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(8,5);
	assertEquals(true, roi2.estValide(depart, arrivee));
}

@Test //test 19
public void testRoi8()
{
	Position depart = new Position(7,3);
	Position arrivee = new Position(7,2);
	assertEquals(false, roi2.estValide(depart, arrivee));
}

@Test //test 20
public void testRoi9()
{
	Position depart = new Position(7,5);
	Position arrivee = new Position(6,5);
	assertEquals(false, roi2.estValide(depart, arrivee));
}


@Test //test 21
public void testRoi10()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(9,4);
	assertEquals(true, roi2.estValide(depart, arrivee));
}
@Test //test 22
public void testRoi11()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(9,5);
	assertEquals(false, roi2.estValide(depart, arrivee));
}

//---------------------------------------------------------------------------
//série de tests pour le mandarin
//---------------------------------------------------------------------------

@Before //mandarin noir
public void initialisation6()
{
	mandarin = new Mandarin("m1","noir");
 
}


@Test //test 23
public void testMandarin1()
{
	Position depart = new Position(1,4);
	Position arrivee = new Position(2,3);
	assertEquals(true, mandarin.estValide(depart, arrivee));
}


@Test //test 24
public void testMandarin2()
{
	Position depart = new Position(0,5);
	Position arrivee = new Position(1,6);
	assertEquals(false, mandarin.estValide(depart, arrivee));
}

@Test //test 25
public void testMandarin3()
{
	Position depart = new Position(1,4);
	Position arrivee = new Position(2,3);
	assertEquals(true, mandarin.estValide(depart, arrivee));
}


@Test //test 26
public void testMandarin4()
{
	Position depart = new Position(1,3);
	Position arrivee = new Position(0,4);
	assertEquals(true, mandarin.estValide(depart, arrivee));
}


//-------------------------

@Before //mandarin rouge
public void initialisation7()
{
	mandarin2 = new Mandarin("M2","rouge");
 
}

@Test //test 27
public void testMandarin5()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(7,3);
	assertEquals(true, mandarin2.estValide(depart, arrivee));
}


@Test //test 28
public void testMandarin6()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(8,5);
	assertEquals(false, mandarin2.estValide(depart, arrivee));
}


@Test //test 29
public void testMandarin7()
{
	Position depart = new Position(7,3);
	Position arrivee = new Position(8,2);
	assertEquals(false, mandarin2.estValide(depart, arrivee));
}


@Test //test 30
public void testMandarin8()
{
	Position depart = new Position(9,4);
	Position arrivee = new Position(8,5);
	assertEquals(true, mandarin2.estValide(depart, arrivee));
}

@Test //test 31
public void testMandarin9()
{
	Position depart = new Position(7,5);
	Position arrivee = new Position(6,6);
	assertEquals(false, mandarin2.estValide(depart, arrivee));
}
//---------------------------------------------------------------------------
//série de tests pour le bombarde
//---------------------------------------------------------------------------
@Before
public void initialisation8()
{
	bombarde = new Bombarde("b1","noir");
 
}

@Test //test 32
public void testBombarde1()	
{
	Position depart = new Position(8,1);
	Position arrivee = new Position(8,8);
	assertEquals(true, bombarde.estValide(depart, arrivee));
}

@Test //test 33
public void testBombarde2()	
{
	Position depart = new Position(0,4);
	Position arrivee = new Position(9,4);
	assertEquals(true, bombarde.estValide(depart, arrivee));
}

@Test //test 34
public void testBombarde3()	
{
	Position depart = new Position(0,0);
	Position arrivee = new Position(4,4);
	assertEquals(false, bombarde.estValide(depart, arrivee));
}

@Before
public void initialisation9()
{
	pieceChar = new Char("c1","rouge");
 
}
//---------------------------------------------------------------------------
//série de tests pour le char
//---------------------------------------------------------------------------
@Test //test 35
public void testChar1()	
{
	Position depart = new Position(7,7);
	Position arrivee = new Position(7,4);
	assertEquals(true, pieceChar.estValide(depart, arrivee));
}


@Test //test 36
public void testChar2()	
{
	Position depart = new Position(0,0);
	Position arrivee = new Position(2,0);
	assertEquals(true, pieceChar.estValide(depart, arrivee));
}

@Test //test 37
public void testChar3()	
{
	Position depart = new Position(6,2);
	Position arrivee = new Position(3,5);
	assertEquals(false, pieceChar.estValide(depart, arrivee));
}

//---------------------------------------------------------------------------
//série de tests pour le pion
//---------------------------------------------------------------------------

//pion rouge

@Before public void initialisation10()
{
	pion1 = new Pion("p1","rouge");
 
}

@Test //test 38
public void testPion1()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(7,6);
	assertEquals(true, pion1.estValide(depart, arrivee));
}

@Test //test 39
public void testPion2()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(9,6);
	assertEquals(false, pion1.estValide(depart, arrivee));
}

@Test //test 40
public void testPion3()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(8,7);
	assertEquals(false, pion1.estValide(depart, arrivee));
}

@Test //test 41
public void testPion4()	
{
	Position depart = new Position(4,5);
	Position arrivee = new Position(4,4);
	assertEquals(true, pion1.estValide(depart, arrivee));
}

@Test //test 42
public void testPion5()	
{
	Position depart = new Position(3,3);
	Position arrivee = new Position(2,2);
	assertEquals(false, pion1.estValide(depart, arrivee));
}

//tests pion noir

@Before public void initialisation11()
{
	pion2 = new Pion("p2","noir");
 
}

@Test //test 43
public void testPion6()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(7,6);
	assertEquals(false, pion2.estValide(depart, arrivee));
}

@Test //test 44
public void testPion7()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(9,6);
	assertEquals(true, pion2.estValide(depart, arrivee));
}

@Test //test 45 
public void testPion8()	
{
	Position depart = new Position(8,6);
	Position arrivee = new Position(8,7);
	assertEquals(true, pion2.estValide(depart, arrivee));
}

@Test //test 46
public void testPion9()	
{
	Position depart = new Position(4,5);
	Position arrivee = new Position(4,4);
	assertEquals(false, pion2.estValide(depart, arrivee));
}

@Test //test 47
public void testPion10()	
{
	Position depart = new Position(6,1);
	Position arrivee = new Position(7,2);
	assertEquals(false, pion2.estValide(depart, arrivee));
}

//---------------------------------------------------------------------------
//série de tests pour l'éléphant (suite)
//---------------------------------------------------------------------------

//elephant rouge

@Test //test 48
public void testElephant3()  
{
	Position depart = new Position(8,1);
	Position arrivee = new Position(8,3);
	assertEquals(false, elephantRouge.estValide(depart, arrivee));
}

@Test //test 49
public void testElephant4()  
{
	Position depart = new Position(5,5);
	Position arrivee = new Position(4,4);
	assertEquals(false, elephantRouge.estValide(depart, arrivee));
}
@Test //test 50
public void testElephant5()  
{
	Position depart = new Position(8,7);
	Position arrivee = new Position(6,5);
	assertEquals(true, elephantRouge.estValide(depart, arrivee));
}

//elephant noir

@Test //test 51
public void testElephant6()  
{
	Position depart = new Position(1,4);
	Position arrivee = new Position(3,4);
	assertEquals(false, elephantNoir.estValide(depart, arrivee));
}

@Test //test 52
public void testElephant7()  
{
	Position depart = new Position(3,1);
	Position arrivee = new Position(5,3);
	assertEquals(false, elephantNoir.estValide(depart, arrivee));
}
@Test //test 53
public void testElephant8()  
{
	Position depart = new Position(0,8);
	Position arrivee = new Position(2,6);
	assertEquals(true, elephantNoir.estValide(depart, arrivee));
}

//---------------------------------------------------------------------------
//série de tests pour l'immobilité
//---------------------------------------------------------------------------

@Test //test 54
public void testImmobiliteElephant()  
{
	Position depart = new Position(0,8);
	Position arrivee = new Position(0,8);
	assertEquals(true, elephantNoir.estValide(depart, arrivee));
}

@Test //test 55
public void testImmobilitePion()	
{
	Position depart = new Position(6,1);
	Position arrivee = new Position(6,1);
	assertEquals(true, pion2.estValide(depart, arrivee));
}

@Test //test 56
public void testImmobiliteChar()	
{
	Position depart = new Position(6,2);
	Position arrivee = new Position(6,2);
	assertEquals(true, pieceChar.estValide(depart, arrivee));
}

@Test //test 57
public void testImmobiliteBombarde()	
{
	Position depart = new Position(0,4);
	Position arrivee = new Position(0,4);
	assertEquals(true, bombarde.estValide(depart, arrivee));

}

@Test //test 58
public void testImmobiliteMandarin()
{
	Position depart = new Position(9,4);
	Position arrivee = new Position(9,4);
	assertEquals(true, mandarin2.estValide(depart, arrivee));
}

@Test //test 59
public void testImmobiliteRoi()
{
	Position depart = new Position(8,4);
	Position arrivee = new Position(8,4);
	assertEquals(true, roi2.estValide(depart, arrivee));
}

@Test //test 60
public void testImmobiliteCavalier()
{
	Position depart = new Position(3,0);
	Position arrivee = new Position(3,0);
	assertEquals(true, cavalier.estValide(depart, arrivee));
}
}