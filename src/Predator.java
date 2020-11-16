/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

    public int lifePoints;
    
    public void resetLifePoints() {
    }

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Predator(boolean female) {
        super(female);
    }
    
    //Hilfsmethode	=>	keine Codeduplizierung	=>	Minimale Wartung
    protected boolean CheckInstanzP(Animal[][] board, int x, int y, Hilfe.List<String> tempResult) {
    	if(!(x>='a' && x<='h' && y>='1' && y<='8'))
    		return false;
    	
    	Animal animal = board[x-'a'][y-'1'];
    	if(animal != null && this.female == animal.female) {
    		return false;
    	} if(animal instanceof Predator) {
    		return false;
    	} else if(animal instanceof Vegetarian) {
    		tempResult.insert("" + (char)(x) + (char)(y));
    		return false;
    	} else {
    		tempResult.insert("" + (char)(x) + (char)(y));
    		return true;
    	}
    }
}
