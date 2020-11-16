/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 *
 */
public class Position {

    /**
     * Die Tiere werden intern in einem Array gespeichert.
     * nrAnimals gibt an, wie viele Tiere auf dem Brett sind.
     * Diese sind in myAnimals an den Positionen 0 bis nrAnimals-1 enthalten.
     *
     * Es ist empfohlen, aber nicht vorgeschrieben, diese Attribute zu verwenden.
     *
     * Falls die beiden Attribute NICHT verwendet werden, muss die Ausgabe
     * der Spielposition unten entsprechend auf die verwendete Datenstruktur
     * angepasst werden. Die toString-Methode darf dabei nicht veraendert werden,
     * muss jedoch die selbe Rueckgabe liefern. D.h. es ist dann notwendig,
     * die Hilfsmethode boardRepresentation auf die verwendete Datenstruktur anzupassen.
     */
    private Animal[] myAnimals;
    private int nrAnimals;

    /**
     * Spieler, der als naechstes ziehen darf ('M' oder 'W').
     * Wird jedes Mal aktualisiert, wenn eine Seite ihre Zuege ausfuehrt.
     */
    private char next = 'W';
    
    public char getNext() {
    	return this.next;
    }
    
    public void switchNext() {
    	this.next = (this.next == 'W') ? 'M' : 'W';
    }


    /**
     * Stellt die Anfangsposition des Spiels her.
     * Der Parameter gibt an, welche Seite beginnt ('M' oder 'W').
     */
    public void reset(char movesNext) {
        //TODO
    	
    	next = movesNext;
    	nrAnimals = 8*4;
    	myAnimals = new Animal[nrAnimals];

    	resetGeschlecht(true);
    	resetGeschlecht(false);
    	
    	for(int i = 0; i < nrAnimals; i++) {
    		myAnimals[i].position = this;
    	}
    }
    //Hilfsmethode
    private void resetGeschlecht (boolean female) {
    	int counter = female ? 0 : 8*2;
    	
    	myAnimals[counter] = new Snake(female);
    	myAnimals[counter++].square =  "a" + (female ? "1" : "8");
    	myAnimals[counter] = new Snake(female);
    	myAnimals[counter++].square =  "h" + (female ? "1" : "8");
    	
    	myAnimals[counter] = new Elephant(female);
    	myAnimals[counter++].square =  "b" + (female ? "1" : "8");
    	myAnimals[counter] = new Elephant(female);
    	myAnimals[counter++].square =  "g" + (female ? "1" : "8");
    	
    	myAnimals[counter] = new Horse(female);
    	myAnimals[counter++].square =  "c" + (female ? "1" : "8");
    	myAnimals[counter] = new Horse(female);
    	myAnimals[counter++].square =  "f" + (female ? "1" : "8");
    	
    	myAnimals[counter] = new Leopard(female);
    	myAnimals[counter++].square =  "d" + (female ? "1" : "8");
    	myAnimals[counter] = new Leopard(female);
    	myAnimals[counter++].square =  "e" + (female ? "1" : "8");
    	
    	myAnimals[counter] = new Penguin(female);
    	myAnimals[counter++].square =  "a" + (female ? "2" : "7");
    	myAnimals[counter] = new Penguin(female);
    	myAnimals[counter++].square =  "h" + (female ? "2" : "7");
    	
    	for(int i = 0; i < 6; i++) {
	    	myAnimals[counter] = new Rabbit(female);
	    	myAnimals[counter++].square =  (char)(i+(int)'b') + (female ? "2" : "7");
    	}
    }
    


    /**
     * Fuehrt die uebergebenen Zuege fuer einen der Spieler aus.
     * Die Reihenfolge soll keinen Unterschied machen.
     * Diese Methode geht davon aus, dass dies bereits ueberprueft wurde.
     *
     * Der Zustand des Spiels wird entsprechend angepasst, d. h. ein Spiel
     * kann von der Anfangsposition aus allein mittels Aufrufen dieser Methode
     * gespielt werden. Insbesondere wechselt durch den Aufruf das Zugrecht,
     * da M und W abwechselnd ziehen.
     *
     * @param move Array mit den Zuegen, die ausgefuehrt werden sollen.
     *
     */
    public void applyMoves(Move[] move){
        //TODO
    	
		Animal[][] board = boardRepresentation();
		
    	for(int i = 0; i < move.length; i++) {
    		if(move[i] == null)
    			continue;
    		
    		int xfrom = move[i].from.charAt(0) - 'a';
    		int yfrom = move[i].from.charAt(1) - '1';
    		
    		board[xfrom][yfrom].square = move[i].to;	//move Animal
    		
    		int xto = move[i].to.charAt(0) - 'a';
    		int yto = move[i].to.charAt(1) - '1';

    		if(board[xto][yto] instanceof Vegetarian) {	//d.h. Raubtier frisst Vegetarier
				((Predator)board[xfrom][yfrom]).resetLifePoints();
						
    			for(int index = 0; index < myAnimals.length; index++) {
    				if(myAnimals[index] == board[xto][yto]) {
    					myAnimals[index] = null;
    					nrAnimals--;
    				}
    			}
    		}
    	}
    	clearNulls();
    }


    /**
     * Ermittelt, ob/wer gewonnen hat.
     *
     * @return 'W' falls W gewonnen hat,
     *         'M' falls M gewonnen hat,
     *         'N' falls das Spiel unentschieden zu Ende ist,
     *         'X' falls das Spiel noch nicht zu Ende ist.
     *
     */
    public char theWinner() {
        //TODO
    	int anzRaubW = 0, anzRaubM = 0, anzVegW = 0, anzVegM = 0;
    	for(int i =  0; i < nrAnimals; i++) {
    		if(myAnimals[i] instanceof Predator) {
    			if(myAnimals[i].female) anzRaubW++;
    			else anzRaubM++;
    		}
    		else if(myAnimals[i] instanceof Vegetarian) {
    			if(myAnimals[i].female) anzVegW++;
    			else anzVegM++;
    		}
    	}
    	
    	if(anzVegW + anzRaubW == 0)
    		return 'M';
    	if(anzVegM + anzRaubM == 0)
    		return 'W';
    	if(anzRaubW + anzRaubM == 0) {
    		if(anzVegW == anzVegM) return 'N';
    		else if(anzVegW > anzVegM) return 'W';
    		else return 'M';
    	}
    	else return 'X';
    }
    
    
    public void sunset() {
    	for(int i = 0; i < myAnimals.length; i++) {
    		myAnimals[i].sunset();
    		if(myAnimals[i].alive == false) {
    			myAnimals[i] = null;
    			nrAnimals--;
    		}
    	}
    	clearNulls();
    }

    // Ausgabe der Spielposition

    private static final int[] I = {8,7,6,5,4,3,2,1};
    private static final String[] J = {"a","b","c","d","e","f","g","h"};
    private static int toIndex(String s){return (s.charAt(0)-'a');}

    // Erzeugt eine 2D-Repraesentation der Spielposition.
    // Darf ggf. auf neue Datenstruktur angepasst werden (s.o.)
    // Die Rueckgabe ist ein zweidimensionales Array, welches
    // jedem Feld das darauf befindliche Tier (oder null) zuordnet.
    // Dabei laeuft der erste Index von der 'a'-Linie zur 'h'-Linie,
    // der zweite von der 1. zur 8. Reihe. D.h. wenn z.B. bei a[3][7]
    // ein Tier ist, ist das zugehörige Feld "d8" (vierter Buchstabe,
    // achte Zahl).
    public Animal[][] boardRepresentation(){
        Animal[][] a = new Animal[8][8];
        for (int i : I) {
            for (String j : J) {
                for (int k = 0; k < myAnimals.length; k++) {
                    if (null == myAnimals[k]) {break;}
                    if (myAnimals[k].square.equals(j+i)) {
                        a[toIndex(j)][i-1] = myAnimals[k];
                    }
                }
            }
        }
        return a;
    }
    
    //Hilfsmethode
    private void clearNulls() {
		Animal[] tempMyAnimals = new Animal[nrAnimals];	//clear "null"s from myAnimals
		for(int index = 0, i = 0; i < myAnimals.length; i++) {
			if(myAnimals[i] != null)
				tempMyAnimals[index++] = myAnimals[i];
		}
		myAnimals = tempMyAnimals;
    }


    @Override
    public String toString(){
        String str = "   a b c d e f g h\n";
        Animal[][] ani = boardRepresentation();
        for (int i : I) {
            str += (i+" ");
            for (String j : J) {
                if (null == ani[toIndex(j)][i-1]) {
                    str += (i+toIndex(j))%2==1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
                } else {
                    str += ani[toIndex(j)][i-1].toString();
                }
            }
            str += " " + i + "\n";
        }
        str += "  a b c d e f g h\nIt is " + next + "'s turn.\n";
        return str;
    }

}
