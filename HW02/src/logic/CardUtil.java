package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class CardUtil {
	
	public static boolean isExistsInList(UnitCard card, ArrayList<UnitCard> list) {
		for(UnitCard c : list) {
			if(c.equals(card)) {
				return true;
			}

		}
		return false;
	}
	
	public static boolean isExistsInList(UnitDeck deck, ArrayList<UnitDeck> list) {
		for(UnitDeck d : list) {
			if(d.equals(deck)) {
				return true;
			}

		}
		return false;

	}
	
	public static boolean cardExistsInDeckList(ArrayList<UnitDeck> deckList, UnitCard cardToTest) {
		for(UnitDeck d : deckList) {
			for (CardCounter cc : d.getCardsInDeck()) {
				if(cc.getCard().equals(cardToTest)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static ArrayList<UnitCard> getCardsFromFile(String filename){
		File fileToRead = new File(filename);
		ArrayList<UnitCard> cardsFromFile = new ArrayList<UnitCard>();
		
		try {
			Scanner sc = new Scanner(fileToRead);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] lineArr = line.split(",");
				
				String name = lineArr[0];
				int bloodCost = Integer.parseInt(lineArr[1]);
				int power = Integer.parseInt(lineArr[2]);
				int health = Integer.parseInt(lineArr[3]);
				String flavorText = lineArr[4];
				
				UnitCard newCard = new UnitCard(name, bloodCost, power, health, flavorText);
				cardsFromFile.add(newCard);
			}
			sc.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
			return null;
		}
		
		return cardsFromFile;
	}


	public static void printCardList(ArrayList<UnitCard> cardList, boolean verbose) {
		
		for(int i = 0; i < cardList.size(); i++) {
			System.out.println(i + ") " + cardList.get(i));
			if(verbose) {
				System.out.println("Blood Cost: " + cardList.get(i).getBloodCost());
				System.out.println(cardList.get(i).getFlavorText());
				if(i < cardList.size()-1) System.out.println("-----");
			}
		}
	}
	
	public static void printDeck(UnitDeck ud) {
		
		if(ud.getCardsInDeck().size() == 0) {
			System.out.println("EMPTY DECK");
		}else {
			for(CardCounter cc : ud.getCardsInDeck()) {
				System.out.println(cc);
			}
		}
		
		System.out.println("Total Cards: " + ud.cardCount());
	}
	
	public static void printDeckList(ArrayList<UnitDeck> deckList) {
		
		
		for(int i = 0; i < deckList.size(); i++) {
			System.out.println(i + ") " + deckList.get(i).getDeckName());
			printDeck(deckList.get(i));
			if(i < deckList.size()-1) System.out.println("-----");
		}
	}
}
