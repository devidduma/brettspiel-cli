/**
 * Die Klasse Main enthaelt das Hauptprogramm.
 *
 * Im Hauptprogramm wird zuerst der Benutzer gefragt,
 * wer das Spiel beginnen soll.
 *
 * Dann wird das Spiel gestartet.
 *
 */
public class Main {

    public static void main(String args[]) {
        //TODO
    	boolean ladiesFirst = false;
    	
    	String entsch = IO.readString("\"Kampf der Geschlechter\"-$ " +
    			" : Wer soll das Spiel beginnen? [W / M]");
    	
    	boolean gueltigkeit = false;
    	do {
	    	if(entsch.equals("W") || entsch.equals("w")) {
	    		ladiesFirst = true;
	    		gueltigkeit = true;
	    	} else if(entsch.equals("M") || entsch.equals("m")) {
	    		ladiesFirst = false;
    			gueltigkeit = true;
	    	} else {
	    		entsch = IO.readString("Kampf der Geschlechter/n" +
	        		":: Falsche Eingabe! Bitte mit [W / M] antworten: ");
	    	}
    	}
    	while(!gueltigkeit);
    	
    	Game game = new Game();
    	game.startGame(ladiesFirst);
    }

}
