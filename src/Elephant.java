public class Elephant extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Elephant(boolean female) {
        super(female);
    }


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_elephant_dark : Globals.ts_female_elephant_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_elephant_dark : Globals.ts_male_elephant_light);
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
    	while(CheckInstanzV(board, x + zug, y, tempResult)) zug++;
    	
		//Richtung links
    	zug = 1;
    	while(CheckInstanzV(board, x - zug, y, tempResult)) zug++;
	
		//Richtung oben
    	zug = 1;
    	while(CheckInstanzV(board, x, y + zug, tempResult)) zug++;
    	
    	//Richtung unten
    	zug = 1;
    	while(CheckInstanzV(board, x, y - zug, tempResult)) zug++;
    	
        return Hilfe.toMoveArray(this.square, tempResult);
    }
    
    @Override
    public void sunset(){
    }

}
