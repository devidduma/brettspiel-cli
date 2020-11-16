/**
 * Klasse der Vegetarier.
 */
public class Vegetarian extends Animal {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Vegetarian(boolean female) {
        super(female);
    }
    
    
    //Hilfsmethode	=>	keine Codeduplizierung	=>	Minimale Wartung
    protected static boolean CheckInstanzV(Animal[][] board, int x, int y, Hilfe.List<String> tempResult) {
    	if(x>='a' && x<='h' && y>='1' && y<='8') if(board[x-'a'][y-'1'] == null) {
    		tempResult.insert("" + (char)(x) + (char)(y));
    		return true;
    	}
    	return false;
    }

}
