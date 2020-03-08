package gioco;

public class BlackJack {
	
	private int valore = 0;
	private boolean assi = false;
	private int nAssi = 0;
	
	public int getValore() {
		return valore;
	}

	public int getnAssi() {
		return nAssi;
	}

	/*
	 * T, J, Q, K = 10,jack,queen,king valgono 10 punti
	 * A = ace vale 11 o 1 punto a piaceimento
	 * da 2 a 9 valgono il loro valore
	 */
	public void cardValue(String s) {
		char c = s.charAt(0);
		String n = ""+c;
		int i = (int)c;
		if(c == 'T' || c == 'J' || c == 'Q' || c == 'K') {
			this.valore += 10;
		}else if(i>=50 && i<=57) {
			int ni = Integer.parseInt(n);
			this.valore += ni;
		}
		else if(c == 'A') {
			this.assi = true;
			this.nAssi++;
		} 
	}
	
	public void gestioneAssi() {
		while(nAssi>0) {
			if(this.assi) {
				if(this.valore + 11 <= 21) {
					this.valore += 11;
					nAssi--;
				}else {
					this.valore += 1;
					nAssi--;
				}
			}
		}
		this.assi=false;
	}
	
	public String risultato() {
		String risultato = null;
		if (getValore() > 21) {
			risultato = "Bust";
		}else {
			risultato = ""+getValore();
		}
		return risultato;
	}
	
	public void reset() {
		valore = 0;
		assi = false;
		nAssi = 0;
	}
	
	public static void main(String[] args) {
		BlackJack x = new BlackJack();
		
		String carte = "9 5 4, 6 2 J, 5 5 6, 3 J 7, A A J A 6, 7 2 A, A 4 A, 2 A 7, A A 5, A 7, J J, K T, 8 9, 2 A 9 5, 6 Q, A A T J, 7 5 J, T 7, A 3 J A 9, 7 Q, 2 A K A 6, K T";
		
		String[] sequenza = carte.trim().split(",");
		for(String s : sequenza) {
			String[] giocata = s.trim().split(" ");
			for(int i=0;i<giocata.length;i++) {
				x.cardValue(giocata[i]);
			}
			x.gestioneAssi();
			System.out.print(x.risultato());
			System.out.print(" ");
			x.reset();
		}
	}

}
