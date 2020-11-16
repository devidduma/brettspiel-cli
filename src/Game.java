/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */

public class Game {

    private Position pos;
    private Animal[][] board;
    private Animal[] myOwnAnimals;
    private Move[] allPossMoves;
    private Move[] zuge;


    /**
     * Startet ein neues Spiel.
     * Der Benutzer wird ueber das Spielgeschehen informiert.
     *
     * Dazu gehoert auch die Information, wie lange die
     * einzelnen Raubtiere noch ohne Essen auskommen koennen.
     * Diese Information soll auf Anfrage oder immer angezeigt werden.
     *
     * Es soll ausserdem eine Moeglichkeit geben, sich alle Zuege
     * anzeigen zu lassen, die in der Spielsituation moeglich sind.
     *
     * Bei fehlerhaften Eingaben wird die Eingabe natuerlich wiederholt.
     *
     * Der Parameter spezifiziert, wer das Spiel beginnen darf.
     */
    public void startGame(boolean ladiesFirst){
        pos = new Position();
        pos.reset(ladiesFirst ? 'W' : 'M');
        
        String winner = "X";
        
        //TODO
        do {
        	System.out.println("\n\tNEUE SPIELRUNDE\n");
        	HalbeRunde(pos);
        	pos.switchNext();
        	
    		winner = willAufgeben(winner);
    		if(!winner.equals("X"))
    			break;
    		
    		System.out.println("");
        	
        	HalbeRunde(pos);
        	pos.switchNext();
        	
        	pos.sunset();
        	
        	if(pos.theWinner() == 'M')
        		winner = "Spieler M";
        	else if(pos.theWinner() == 'W')
        		winner = "Spieler W";
        	else if(pos.theWinner() == 'N')
        		winner = "N";
        	else
    			winner = willAufgeben(winner);
        }
        while(winner.equals("X"));
        
        System.out.println("\n\tSPIEL ENDE\n");
        System.out.println(pos.toString());
        if(winner.equals("N"))
        	System.out.println("Das Spiel ist unentschieden.");
        else 
        	System.out.println("Glückwunsch " + winner + "! Sie gewinnen.");
        
    }
    
    private String willAufgeben(String winner) {
    	String entsch = IO.readString(":: Will jemand aufgeben? (oder Unentschieden vorschlagen) [J für Ja / irgendwas Anderes für Nein]: ");
    	if(entsch.equals("J") || entsch.equals("j")) {
    		
    		String aufgeben = IO.readString(":: Wer will aufgeben? [W / M / N um Unentschieden vorzuschlagen / irgendwas Anderes falls Meinung geändert]: ");
    		if(aufgeben.equals("W") || aufgeben.equals("w"))
    			winner = "Spieler M";
    		else if(aufgeben.equals("M") || aufgeben.equals("m"))
    			winner = "Spieler W";
    		else if(aufgeben.equals("N") || aufgeben.equals("n")) {
    			String unentschieden = IO.readString(":: Akzeptiert der andere Spieler den Vorschlag zum Unentschieden? [J für Ja / irgendwas Anderes für Nein]: ");
    			if(unentschieden.equals("J") || unentschieden.equals("j"))
    				winner = "N";
    		}
    	}
    	return winner;
    }
    
    
    private void HalbeRunde(Position pos) {
    	board = pos.boardRepresentation();
    	myOwnAnimals = new Animal[32];
    	System.out.println(pos.toString());
    	
    	boolean female = (pos.getNext() == 'W') ? true : false;
    	
		int index = 0;
    	for(int i = 0; i < board.length; i++) {
    		for(int j = 0; j < board[i].length; j++) {
    			
    			Animal animal = board[i][j];
    			if(animal != null) if(female == animal.female) {
    				myOwnAnimals[index++] = animal;
    			}
    		}
    	}
    	
    	allPossMoves = new Move[1010];
    	int counter = 0;
    	for(int i = 0; myOwnAnimals[i] != null; i++) {
    		Move[] moves = myOwnAnimals[i].possibleMoves();
    		for(int j = 0; j < moves.length; j++) 
    			allPossMoves[counter++] = moves[j];
    	}

    	raubtiereInfo();
    	showPossMoves();
    	
        System.out.println("Es sind maximal 1 Zug für Raubtiere und maximal 3 Züge für Vegetarier erlaubt");
    	
    	zuge = new Move[4];

    	System.out.println("ACHTUNG! Wenn Sie ein Zug passen wollen, einfach leeres String eingeben (Enter drücken).");
    	zuge[0] = Zug("Erster Zug ist für das Raubtier: ",'R');
    	zuge[1] = Zug("Zweiter Zug ist für eine Vegetarier: ",'V');
    	zuge[2] = Zug("Dritter Zug ist für eine Vegetarier: ",'V');
    	zuge[3] = Zug("Vierter Zug ist für eine Vegetarier: ",'V');
    	
    	pos.applyMoves(zuge);
    }

    
    private Move Zug(String msg, char RW) {
    	String entsch = IO.readString(msg);
    	
    	while(!CheckEingabe(entsch,RW)) {
    		entsch = IO.readString("Bitte geben Sie erneut ein: ");
    	}
    	
    	if(entsch.equals(""))
    		return null;
    	
    	return new Move(entsch);
    }
    
    
    private boolean CheckEingabe(String entsch, char RW) {
    	if(entsch.equals(""))
    		return true;
    	
    	if(entsch.length() != 4) {
    		System.out.print("Falsche Eingabe! ");
    		return false;
    	}
    	
    	for (int i = 0; allPossMoves[i] != null; i++) {
    		if(entsch.equals(allPossMoves[i].toString())) {
    			int xfrom = entsch.charAt(0) - 'a';
    			int yfrom = entsch.charAt(1) - '1';
    			
    			String to = "" + entsch.charAt(2) + entsch.charAt(3);
    			for(int k = 0; k < zuge.length; k++) {
    				if(zuge[k] == null) continue;
    				
    				if(to.equals(zuge[k].to)) {
    					System.out.print("Du darfst nicht zwei mal im gleichen Feld ziehen! ");
    					return false;
    				}
    			}
    			
    			String from = "" + entsch.charAt(0) + entsch.charAt(1);
    			for(int k = 0; k < zuge.length; k++) {
    				if(zuge[k] == null) continue;
    				
    				if(from.equals(zuge[k].from)) {
    					System.out.print("Es wurde schon mit dem Tier in " + from + " gezogen! ");
    					return false;
    				}
    			}
    			
    			if(RW == 'V' && board[xfrom][yfrom] instanceof Vegetarian)
    				return true;
    			else if(RW == 'R' && board[xfrom][yfrom] instanceof Predator)
    				return true;
    			else {
    				System.out.print("Nur " + (RW == 'V' ? "Vegetarier" : "Raubtiere") + " sind erlaubt! ");
    				return false;
    			}
    		}
    	}
		System.out.print("Falsche Eingabe! ");
    	return false;
    }
    
    
    private void raubtiereInfo() {
    	System.out.println("Lifepoints der Raubtiere");
    	
    	for (int i = 0; myOwnAnimals[i] != null; i++) {
    		if(myOwnAnimals[i] instanceof Leopard)
    			System.out.println("Leopard " + myOwnAnimals[i].square + ": " +
    					((Leopard)myOwnAnimals[i]).lifePoints + " Tage noch ohne Essen.");
    		else if(myOwnAnimals[i] instanceof Snake)
    			System.out.println("Snake " + myOwnAnimals[i].square + ": " +
    					((Snake)myOwnAnimals[i]).lifePoints + " Tage noch ohne Essen.");
    		else if(myOwnAnimals[i] instanceof Penguin)
    			System.out.println("Penguin " + myOwnAnimals[i].square + ": " +
    					((Penguin)myOwnAnimals[i]).lifePoints + " Tage noch ohne Essen.");
    	}
    	System.out.println("");
    }
    
    
    private void showPossMoves() {
    	System.out.println("Alle möglichen Züge");
    	
    	if(allPossMoves.length > 0) 
    		System.out.print(allPossMoves[0]);
    	
    	for (int i = 1; allPossMoves[i] != null; i++) {
    		String from_before = "" + allPossMoves[i-1].toString().charAt(0) + allPossMoves[i-1].toString().charAt(1);
    		String from_now = "" + allPossMoves[i].toString().charAt(0) + allPossMoves[i].toString().charAt(1);
    		
    		System.out.print(from_before.equals(from_now) ? ", " : " | ");
    		System.out.print(allPossMoves[i]);
    	}
    	System.out.println("\n");
    }
}
