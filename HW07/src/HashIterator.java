public class HashIterator implements Iterator {

	OpenAddressing h; // the associated hash table
	int currentPos; // position in the table's array that is currently marked.

	// Create an iterator that marks the leftmost actual data in the hash table.
	// Assume actual data are not 0 and DELETED.
	// If there are no actual data in the table, set currentPos to -1.
	public HashIterator(OpenAddressing o) {
		h = o;
		int i = 0;
		for (; i < o.array.length; i++) {
			if (o.array[i] != 0 && o.array[i] != OpenAddressing.DELETED) {
				currentPos = i;
				break;
			}
		}
		if (i >= o.array.length) {
			currentPos = -1;
		}
	}

	@Override
	public boolean hasNext() {
		int i = currentPos;
		i++;
		while (i <= h.array.length - 1) {
			if (h.array[i] != 0) {
				return true;
			} else
				i++;

		}
		return false;
	}

	@Override
	public boolean hasPrevious() {
		int i = currentPos;
		i--;
		while (i > 0) {
			if (h.array[i] != 0) {
				return true;
			} else
				i--;

		}
		return false;
	}

	@Override
	public int next() throws Exception {
		currentPos++;
		while (currentPos <= h.array.length - 1) {
			if (h.array[currentPos] != 0) {
				return h.array[currentPos];
			} else
				currentPos++;
		}
		throw new Exception();
	}

	@Override
	public int previous() throws Exception {
		int result = h.array[currentPos];
		int i = currentPos;
		i--;
		while (i > 0) {
			if (h.array[i] != 0) {
				currentPos = i;
				return result;

			} else
				i--;

		}
		throw new Exception();
	}

	@Override
	public void set(int value) {
		// does not do anything,
		// because it will break hash table definition

	}

}
