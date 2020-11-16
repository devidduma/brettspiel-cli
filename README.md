# Kampf-der-Geschlechter
Dies ist meine Lösung für eine interessante Aufgabe aus dem Modul "Grundlagen der Programmierung" der TU München.

Es soll ein Brettspiel für zwei Personen (im Folgenden M und W genannt) implementiert
werden, bei dem es darum geht, Tiere auf dem Brett zu bewegen. Jeder Spieler hat
die selbe Anzahl an Tieren einer bestimmten Art. Das Spiel wird auf einem Schachbrett
gespielt (Felder "a1" bis "h8" vom Datentypen String). Um zu unterscheiden, zu welchem
Spieler ein Tier jeweils gehört, treten Männchen (Spieler M) und Weibchen (Spielerin W)
gegeneinander an.

Es folgt zunächst eine Beschreibung der Spielregeln. Anschließend folgen weitere Angaben
zur Umsetzung des Spiels als Java-Programm.
Jedes Tier ist entweder ein Raubtier oder ein Vegetarier. Die Raubtiere fressen die Vegetarier.
Raubtiere verhungern, wenn sie eine bestimmte Zeit lang nicht gefressen haben. Es
können nur Vegetarier gefressen werden. Die Art des Raubtiers oder Vegetariers ist dabei
unerheblich. (D. h. ein Pinguin kann z.B. einen Elefanten verspeisen.) Die vorkommenden
Tierarten sind:
• Vegetarier: Kaninchen, Elefant, Pferd
• Raubtiere: Leopard, Schlange, Pinguin

Die Startaufstellung ist ähnlich wie beim Schachspiel: Spielerin W hat
• sechs Kaninchen auf den Feldern b2 bis g2 (b2,c2,d2,e2,f2,g2)
• zwei Pinguine auf den Feldern a2 und h2
• zwei Schlangen auf den Eckfeldern a1 und h1
• zwei Elefanten auf den Feldern b1 und g1
• zwei Pferde auf den Feldern c1 und f1
• zwei Leoparden auf den Feldern d1 und e1

Die Aufstellung für M entspricht der für W, aber gespiegelt an der Mittellinie des Bretts, d. h.
die Tiere besetzen die Felder a7 bis h8. Graphisch dargestellt (Kleinbuchstaben für Tiere
von Spielerin W, Großbuchstaben für die von M) (L/l: Leopard(in), P/p: Pinguin(in),
S/s: Schlange(rich), E/e: Elefant(in), K/k: Kaninchen(m/w), H/h: Pferd(engl. horse)):
