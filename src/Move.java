/**
 * Die Klasse Move repraesentiert einen einzelnen Zug.
 *
 * Es gibt zwei Konstruktoren. Einer bekommt
 * Ausgangsfeld und Zielfeld uebergeben, der andere
 * bekommt nur den eingegebenen Zug in der Form
 * <Ausgangsfeld><Zielfeld> als String uebergeben,
 * also z. B. "a7c5" fuer den Zug von "a7" nach "c5".
 */
public class Move {
	
	public String from;
	public String to;
	
    public Move(String from, String to){
        //TODO
    	this.from = from;
    	this.to = to;
    }

    public Move(String move){
        //TODO
    	this.from = ""+move.charAt(0)+move.charAt(1);
    	this.to = ""+move.charAt(2)+move.charAt(3);
    }

    @Override
    public String toString(){
        //TODO
        // Rueckgabe exakt in der Form <Ausgangsfeld><Zielfeld> als String,
        // also z. B. "b2b3" fuer den Zug eines Tiers von "b2" nach "b3".
    	return this.from + this.to;
    }

    public boolean equals(Object other) {
        //TODO
    	return this.from == ((Move)other).from && this.to == ((Move)other).to;
    }

}
