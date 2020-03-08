package gioco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GiocaBlackJack {
	
	public String random() {
		int carta = 0;
		int max = 13;
		int min = 1;
		int range = max-min+1;
		String cart = null;
		
		carta = (int)(Math.random()*range) + min;
		
		if(carta >= 2 && carta <=9) {
			cart = ""+carta;
		}else if(carta == 10) {
			cart = "T";
		}else if(carta == 11) {
			cart = "J";
		}else if(carta == 12) {
			cart = "Q";
		}else if(carta == 13) {
			cart = "K";
		}else if(carta == 1) {
			cart = "A";
		}
		return cart;
	}

	public static void main(String[] args) {
		
		Scanner tastiera = new Scanner(System.in);
		GiocaBlackJack gb = new GiocaBlackJack();
		BlackJack bj = new BlackJack();
		
		System.out.println("Gioca a BlackJack!");
		List<String> carte = new ArrayList<String>();
		carte.add(gb.random());
		carte.add(gb.random());
		
		System.out.print("Le tue carte sono: "); 
		for(String s : carte) {
			bj.cardValue(s);
			System.out.print(s + " ");
		}
		System.out.println();
			bj.gestioneAssi();
		
		System.out.println("Le tuoi punti attuali sono: " + bj.risultato());
		String risposta = "ciao";
		
		while(!risposta.equalsIgnoreCase("no")) {
			System.out.println("Vuoi un'altra carta? - Scrivi Si o No per continuare");
			risposta = tastiera.next();
			if(risposta.equalsIgnoreCase("si")){
				carte.add(gb.random());
				int lastCard = carte.size();
				System.out.print("Hai pescato: ");
				System.out.print(carte.get(lastCard-1) + "\n");
				bj.cardValue(carte.get(lastCard-1));
				bj.gestioneAssi();
				System.out.println("Ora i tuoi punti sono: " + bj.risultato());
			}
			if(bj.risultato().equals("Bust")){
				break;
			}
		}
		
		String giocatore = bj.risultato();
		if(!giocatore.equalsIgnoreCase("Bust")) {
			System.out.print("Il banco Pesca le Carte!!");
			bj.reset();
			carte.clear();
			carte.add(gb.random());
			carte.add(gb.random());
			
			System.out.print("Il Banco ha pescato: "); 
			for(String s : carte) {
				bj.cardValue(s);
				System.out.print(s + " ");
			}
			System.out.println();
				bj.gestioneAssi();
			
			System.out.println("I punti del banco Sono: " + bj.risultato());
			int gioc = Integer.parseInt(giocatore);
			int banc = Integer.parseInt(bj.risultato());
			while(banc < gioc || bj.risultato().equals("Bust")) {
				System.out.println("Il banco pesca una carta");
				carte.add(gb.random());
				int lastCard = carte.size();
				System.out.print("Il Banco ha pescato: ");
				System.out.print(carte.get(lastCard-1) + "\n");
				bj.cardValue(carte.get(lastCard-1));
				bj.gestioneAssi();
				System.out.println("Ora i punti del banco sono: " + bj.risultato());
				if(!bj.risultato().equals("Bust")) {
					banc = Integer.parseInt(bj.risultato());
				}else {
					break;
				}
			}
			
			if(bj.risultato().equals("Bust")) {
				System.out.print("Il banco ha fatto Bust! Il giocatore Vince!");
			}
			
			
		}else {
			System.out.print("Hai fatto: " + giocatore + "! hai perso!");
		}
		
		String banco = bj.risultato();
		int gioc;
		int banc;
		if(!(banco.equals("Bust")) && !(giocatore.equals("Bust"))) {
			gioc = Integer.parseInt(giocatore);
			banc = Integer.parseInt(banco);
		
			if(gioc > banc) {
				System.out.print("Il giocatore Vince!");
			}else {
				System.out.print("Il banco Vince!");
			}
		}
		

	}

}
