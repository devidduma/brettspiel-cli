public class Rabbit extends Vegetarian {

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Rabbit(boolean female) {
        super(female);
    }


    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_rabbit_dark : Globals.ts_female_rabbit_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_rabbit_dark : Globals.ts_male_rabbit_light);
    }
    
    @Override
    public Move[] possibleMoves(){
    	char x = this.square.charAt(0);
    	char y = this.square.charAt(1);
    	
    	Animal[][] board = this.position.boardRepresentation();
    	Hilfe.List<String> tempResult = new Hilfe.List<String>();
    	
    	CheckInstanzV(board, x-1, y, tempResult);
    	CheckInstanzV(board, x+1, y, tempResult);
    	CheckInstanzV(board, x, y-1 , tempResult);
    	CheckInstanzV(board, x, y+1, tempResult);
    	CheckInstanzV(board, x-1, y-1, tempResult);
    	CheckInstanzV(board, x+1, y+1, tempResult);
    	CheckInstanzV(board, x-1, y+1, tempResult);
    	CheckInstanzV(board, x+1, y-1, tempResult);
    	
    	return Hilfe.toMoveArray(this.square, tempResult);
    }
    
    @Override
    public void sunset(){
    }
}
