
public class StudentList extends CDLinkedList {
    // you can write additional methods.

	// implement this method
	public void swapNode(DListIterator i1, DListIterator i2) {
		DListNode n1 = i1.currentNode;
		DListNode n2 = i2.currentNode;
		
		n1.previousNode.nextNode = n2;
		n1.nextNode.previousNode = n2;
		n2.previousNode.nextNode = n1;
		n2.nextNode.previousNode = n1;
		
		DListNode temp;
		temp = n1.previousNode;
		n1.previousNode = n2.previousNode;
		n2.previousNode = temp;
		
		temp = n1.nextNode;
		n1.nextNode = n2.nextNode;
		n2.nextNode = temp;
		
	}
	
	// implement this method
	public void insertList(DListIterator i1, CDLinkedList lst) {
		if(lst.isEmpty()) {
			return;
		} 
		DListNode tailNode = lst.header.previousNode;
		DListNode headNode = lst.header.nextNode;
		DListNode nextNode = i1.currentNode.nextNode;
		
		headNode.previousNode = i1.currentNode;
		tailNode.nextNode = nextNode;
		
		i1.currentNode.nextNode = headNode;
		nextNode.previousNode = tailNode;
	}

	// implement this method
	public void gender(String g) throws Exception {
			DListNode endGender = this.header.nextNode;
			DListNode cur = this. header. nextNode;
			for(; cur != this.header;) {
				if (((Student) cur.data).getSex().equals(g)) {
					DListNode next = cur.nextNode;
					if (cur == endGender) {
						endGender = endGender. nextNode;
				} else {
					cur.previousNode.nextNode = cur.nextNode;
					cur.nextNode . previousNode = cur.previousNode;
					cur.previousNode = endGender.previousNode;
					cur.nextNode = endGender;
					endGender.previousNode.nextNode = cur;
					endGender.previousNode = cur;
				}
					cur = next;
				} else {
					cur = cur.nextNode;
				}
			}

	}
}
