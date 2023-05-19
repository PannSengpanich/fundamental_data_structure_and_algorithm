
public class BankQueue { // must work for any implementation of DeQ
	DeQ[] counters;
	DeQ special;

	public BankQueue(DeQ[] counters, DeQ special) {
		super();
		this.counters = counters;
		this.special = special;
	}

	//Write this method
	public void distribute() throws Exception {
		float total = 0;
		for(int i = 0;i < counters.length;i++) {
			total += counters[i].size();
		}
		float ct = counters.length + 1;
		
		int nql = Math.round(total/ct);
		
		for(int i = 0;i < nql;i++) {
			if(nql == counters[i].size()-1) {
				int element = counters[i].removeLast();
				special.insertLast(element);
			}
			else if(counters[i].size()-nql <= nql-special.size()) {
				DeQArray temp = new DeQArray();
				int remove = counters[i].size()-nql;
				for (int j = 0;j< remove;j++) {
				int element = counters[i].removeLast();
				temp.insertLast(element);
				}
				while(temp.size()>0) {
				int element = temp.removeLast();
				special.insertLast(element);
				}
			}
			else if (counters[i].size()-nql > nql-special.size()) {
				DeQArray temp = new DeQArray();
				for(int k = 0;k<(counters[i].size()-nql)-(nql-special.size());k++){
					int element = counters[i].removeLast();
					temp.insertLast(element);
				}
				for (int j = 0;j<nql-special.size();j++) {
					int element = counters[i].removeLast();
					special.insertLast(element);
				}
				while(temp.size()>0) {
					int element = temp.removeLast();
					counters[i].insertLast(element);
				}
			}
			if(special.size() == nql) {
				break;
			}
		}
		if(special.size() == 0) {
			int element = counters[counters.length-1].removeLast();
			special.insertLast(element);
		}
	}

}
