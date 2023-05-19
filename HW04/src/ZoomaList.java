import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	public void insert(int value, Iterator p) throws Exception {
		//fill code 
		if (p == null || !(p instanceof DListIterator))
			throw new Exception();
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();

		DListIterator p3 = new DListIterator(p2.currentNode.nextNode);
		DListNode n = new DListNode(value, p3.currentNode, p2.currentNode);
		p2.currentNode.nextNode = n;
		p3.currentNode.previousNode = n;
		size++;
		while((p2.currentNode.data == p2.currentNode.nextNode.data || p3.currentNode.previousNode.data == p3.currentNode.data)) {
			if(p2.currentNode.data == p2.currentNode.nextNode.data && p3.currentNode.previousNode.data == p3.currentNode.data) {
				while(p2.currentNode.data == p2.currentNode.previousNode.data ) {
					p2.currentNode = p2.currentNode.previousNode;
				}
				p2.currentNode = p2.currentNode.previousNode;
				while(p3.currentNode.data == p3.currentNode.nextNode.data ) {
					p3.currentNode = p3.currentNode.nextNode;
				}
				p3.currentNode = p3.currentNode.nextNode;
				DListIterator count = new DListIterator(p2.currentNode);
				int inc = 0;
					while(count.currentNode != p3.currentNode) {
						count.currentNode = count.currentNode.nextNode;
						inc++;
					}
				if(inc <= 3) {
					break;
				}
				removeBetween(p2, p3,inc-1);
				score += (inc-1);
				continue;
			}
			if(p2.currentNode.data == p2.currentNode.nextNode.data) {
				while(p2.currentNode.data == p2.currentNode.previousNode.data ) {
					p2.currentNode = p2.currentNode.previousNode;
				}
				p2.currentNode = p2.currentNode.previousNode;
				DListIterator count = new DListIterator(p2.currentNode);
				int inc = 0;
					while(count.currentNode != p3.currentNode) {
						count.currentNode = count.currentNode.nextNode;
						inc++;
					}
				if(inc <= 3) {
					break;
				}
				removeBetween(p2, p3,inc-1);
				score += (inc-1);
				continue;
			}
			if(p3.currentNode.data == p3.currentNode.previousNode.data) {
				while(p3.currentNode.data == p3.currentNode.nextNode.data ) {
					p3.currentNode = p3.currentNode.nextNode;
				}
				p3.currentNode = p3.currentNode.nextNode;
				DListIterator count = new DListIterator(p2.currentNode);
				int inc = 0;
					while(count.currentNode != p3.currentNode) {
						count.currentNode = count.currentNode.nextNode;
						inc++;
					}
				if(inc <= 3) {
					break;
				}
				removeBetween(p2, p3,inc-1);
				score += (inc-1);
				continue;
			}
		}
	}

	
	public void removeBetween(DListIterator left, DListIterator right, int inc) {
		//fill code
		if(left.currentNode == right.currentNode || left.currentNode.nextNode == right.currentNode) {
			return;
		}
		left.currentNode.nextNode = right.currentNode;
		right.currentNode.previousNode = left.currentNode;
		size -= inc;
	}
	
	public static void main(String[] args) throws Exception {
		CDLinkedList l2 = new CDLinkedList();
		DListIterator d2 = new DListIterator(l2.header);
		l2.insert(3,d2); //list 4,4,4,3,3,5,5,3 -> 4,4,4,3,3,(5),5,5,3 -> 4,4,4
		l2.insert(5,d2);
		l2.insert(5,d2);
		l2.insert(3,d2);
		l2.insert(3,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		l2.insert(4,d2);
		
		ZoomaList l = new ZoomaList(l2);
		DListIterator d = new DListIterator(l.header);
		
		d.next();
		d.next();
		d.next();
		d.next();
		d.next();
		
		l.insert(5,d);
		assertEquals(3,l.size());
		assertEquals(6,l.score);
		assertEquals("4 4 4", l.printList());
	}
}

