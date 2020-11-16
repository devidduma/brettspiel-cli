public class Snake extends Predator {

    // Eine Schlange kann 9 Tage bzw. Spielrunden ohne Essen auskommen.
    // Die Deklaration darf entfernt (und der Wert z. B. direkt im Code
    // verwendet) werden.
    private static int withoutFood = 9;


    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     *
     */
    public Snake(boolean female) {
        super(female);
        resetLifePoints();
    }

    @Override
    public String toString(){
        return this.female
          ? (Globals.darkSquare(this.square) ? Globals.ts_female_snake_dark : Globals.ts_female_snake_light)
          : (Globals.darkSquare(this.square) ? Globals.ts_male_snake_dark : Globals.ts_male_snake_light);
    }
    
    @Override
    public Move[] possibleMoves(){
    	char x = this.square.charAt(0);
    	char y = this.square.charAt(1);
    	
    	Animal[][] board = this.position.boardRepresentation();
    	Hilfe.List<String> tempResult = new Hilfe.List<String>();
    	
        for(int zug = 1; zug + x <= 'h'; zug++) {	//Richtung links
        	int xneu = x + zug;
        	int yneu = (zug % 2 == 0 ? y : y + 1);
        	if(!CheckInstanzP(board, xneu, yneu, tempResult))
        		break;
        }
	
		for(int zug = 1; x - zug >= 'a'; zug++) {	//Richtung rechts
			int xneu = x - zug;
        	int yneu = (zug % 2 == 0 ? y : y - 1);
        	if(!CheckInstanzP(board, xneu, yneu, tempResult))
        		break;
		}
	
		for(int zug = 1; y + zug <= '8'; zug++) {	//Richtung oben
			int xneu = (zug % 2 == 0 ? x : x - 1);
        	int yneu = y + zug;
        	if(!CheckInstanzP(board, xneu, yneu, tempResult))
        		break;
		}
	
		for(int zug = 1; y - zug >= '0'; zug++) {	//Richtung unten
			int xneu = (zug % 2 == 0 ? x : x + 1);
        	int yneu = y - zug;
        	if(!CheckInstanzP(board, xneu, yneu, tempResult))
        		break;
		}
    	
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
