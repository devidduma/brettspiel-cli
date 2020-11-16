public class Penguin extends Predator {

    // Ein Pinguin kann 12 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.
    private static int withoutFood = 12;
    
    public int lifePoints = withoutFood;


    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Penguin(boolean female) {
        super(female);
        resetLifePoints();
    }


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_penguin_dark : Globals.ts_female_penguin_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_penguin_dark : Globals.ts_male_penguin_light);
    }
    
    @Override
    public Move[] possibleMoves(){
    	char x = this.square.charAt(0);
    	char y = this.square.charAt(1);
    	
    	Animal[][] board = this.position.boardRepresentation();
    	Hilfe.List<String> tempResult = new Hilfe.List<String>();
    	
    	CheckInstanzP(board, x-1, y, tempResult);
    	CheckInstanzP(board, x+1, y, tempResult);
    	CheckInstanzP(board, x, y-1 , tempResult);
    	CheckInstanzP(board, x, y+1, tempResult);
    	CheckInstanzP(board, x-1, y-1, tempResult);
    	CheckInstanzP(board, x+1, y+1, tempResult);
    	CheckInstanzP(board, x-1, y+1, tempResult);
    	CheckInstanzP(board, x+1, y-1, tempResult);
    	
    	return Hilfe.toMoveArray(this.square, tempResult);
    }
    
    @Override
    public void sunset(){
    	this.lifePoints = this.lifePoints - 1;
    	if(lifePoints == -1) this.alive = false;
    }
    
    @Override
    public void resetLifePoints() {
        lifePoints = withoutFood;
    }
}
