public class Leopard extends Predator {

    // Ein Leopard kann nur 5 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.
    private static int withoutFood = 5;
    

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Leopard(boolean female) {
        super(female);
        resetLifePoints();
    }


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_leopard_dark : Globals.ts_female_leopard_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_leopard_dark : Globals.ts_male_leopard_light);
    }
    
    @Override
    public Move[] possibleMoves(){
    	char x = this.square.charAt(0);
    	char y = this.square.charAt(1);
    	
    	Animal[][] board = this.position.boardRepresentation();
    	Hilfe.List<String> tempResult = new Hilfe.List<String>();
    	
    	int zug;
    	
    	//Richtung rechts
    	zug = 1;
    	while(CheckInstanzP(board, x + zug, y, tempResult)) zug++;
    	
		//Richtung links
    	zug = 1;
    	while(CheckInstanzP(board, x - zug, y, tempResult)) zug++;
	
		//Richtung oben
    	zug = 1;
    	while(CheckInstanzP(board, x, y + zug, tempResult)) zug++;
    	
    	//Richtung unten
    	zug = 1;
    	while(CheckInstanzP(board, x, y - zug, tempResult)) zug++;
    	
    	//Diagonal
    	zug = 1;
    	while(CheckInstanzP(board, x - zug, y - zug, tempResult)) zug++;
    	zug = 1;
    	while(CheckInstanzP(board, x + zug, y + zug, tempResult)) zug++;
    	zug = 1;
    	while(CheckInstanzP(board, x + zug, y - zug, tempResult)) zug++;
    	zug = 1;
    	while(CheckInstanzP(board, x - zug, y + zug, tempResult)) zug++;
    	
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
