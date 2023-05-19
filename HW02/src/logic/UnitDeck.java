package logic;

import java.util.ArrayList;

public class UnitDeck {
	private ArrayList <CardCounter> cardsInDeck;
	private String deckName;
	
	public UnitDeck(String deckName) {
		setDeckName(deckName);
		cardsInDeck = new ArrayList <CardCounter>();
	}
	public void setDeckName(String deckName) {
		if(deckName.isBlank()) {
			this.deckName = "Untitled Deck";
		}else {this.deckName = deckName;}
	}
	public void addCard(UnitCard newCard,int count) {
		if(count<=0 || count%1 != 0) {
			return;
		}else {
			for (CardCounter cc : cardsInDeck) {
		        if (cc.getCard().equals(newCard)) {
		            cc.setCount(cc.getCount() + count);
		            return;
		        }
			    
		    }
	        CardCounter cc1 = new CardCounter(newCard, count);
		    cardsInDeck.add(cc1);
		}
	}
	public void removeCard(UnitCard toRemove, int count) {
		if(count<=0 || count%1 != 0) {
			return;
		} else {
			boolean cardExists = false;
			for (CardCounter cc : cardsInDeck) {
				if (cc.getCard().equals(toRemove)) {
					cardExists = true;
					if(cc.getCount() > count) {
						cc.setCount(cc.getCount() - count);
					} else {
						cardsInDeck.remove(cc);
					}
					break;
				}
			}
			if (!cardExists) {
				return;
			}
		}
	}

	public int cardCount() {
		int total = 0;
		for (CardCounter cc : cardsInDeck) {
	        total += cc.getCount(); 
	    }
        return total;
	}
	public boolean existsInDeck(UnitCard card) {
		for (CardCounter cc : cardsInDeck) {
			if(cc.getCard().equals(card) && cc.getCount()>=1) {
				return true;
			}
		}
		return false;
	}
	public boolean equals(UnitDeck other) {
			return this.deckName.equals(other.deckName);
		
	}
	
	
	public String getDeckName() {
		return this.deckName;
	}
	public ArrayList <CardCounter> getCardsInDeck(){
		return this.cardsInDeck;
	}


}
