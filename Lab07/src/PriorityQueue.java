public class PriorityQueue {
    MyQueue q;

    public PriorityQueue(MyQueue q) {
        this.q = q;
    }

    public void push(int x) throws Exception {
        int size = q.size();
        boolean inserted = false;

        // Iterate through the queue until the correct position to insert x is found
        for (int i = 0; i < size; i++) {
            int data = q.removeFirst();
            if (!inserted && data > x) {
                q.insertLast(x);
                inserted = true;
            }
            q.insertLast(data);
        }

        // If x is the largest value, insert it at the end of the queue
        if (!inserted) {
            q.insertLast(x);
        }
    }

    public void pop() throws EmptyQueueException {
        if (q.isEmpty()) {
            throw new EmptyQueueException();
        }
        q.removeFirst();
    }

    public int top() throws EmptyQueueException {
        if (q.isEmpty()) {
            throw new EmptyQueueException();
        }
        return q.front();
    }
}