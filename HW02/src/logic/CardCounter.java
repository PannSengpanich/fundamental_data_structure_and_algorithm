package logic;

public class CardCounter {
	
	private UnitCard card;
	private int count;
	
	public String toString() {
		return  this.getCard() + " x " + this.getCount();
	}
	public CardCounter(UnitCard card,int count) {
		setUnitCard(card);
		setCount(count);
	}
	
	
	public void setUnitCard(UnitCard card) {
		this.card = card;
	}
	public void setCount(int count) {
		if(count <= 0) {
			this.count = 0;
		}else { this.count=count;}
	}
	
	
	public UnitCard getCard() {
		return this.card;
	}
	public int getCount() {
		return this.count;
	}
}
