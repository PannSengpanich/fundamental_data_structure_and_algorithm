
public class UseStack {
	
	//implement this method.
	public static Stack sort(Stack s) throws Exception {
	    if (s.isEmpty()) {
	        return s;
	    }
	    Stack holder = new ArrayListStack();
	    Stack temp = new ArrayListStack();
	    Stack temp1 = new ArrayListStack();
	    holder.push(s.top());
	    s.pop();
	    
	    while(!s.isEmpty()) {
	    	if(s.top()<holder.top()) {
	    		temp1.push(s.top());
	    		s.pop();
	    		while(!holder.isEmpty() && temp1.top() < holder.top()) {
	    			s.push(holder.top());
	    			holder.pop();
	    		}
	    		holder.push(temp1.top());
	    		temp1.pop();
	    	}
	    	else{
	    		holder.push(s.top());
	    		s.pop();
	    		
	    		}
	    	}
	    
	    while(!holder.isEmpty()) {
	    	s.push(holder.top());
	    	holder.pop();
	    }
	    return s;
	}

}

