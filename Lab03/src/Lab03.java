
public class Lab03 {

    private static int[] genData1(int n,int max) {
        int[] data = new int[n];
        for(int i=0;i<n;i++) {
            data[i] = (int) (Math.random()*max);
        }
        return data;
    }
    private static int[] genData2(int n,int maxStep) {
        int[] data = new int[n];
        data[0] = (int) (Math.random()*maxStep);
        for(int i=1;i<n;i++) {
        	data[i] = data[i-1] + (int) (Math.random() * maxStep);
        }
        return data;
    }
    private static boolean f1(int x, int[] d) {
    	for(int i=0;i<d.length;i++) {
    		if(x==d[i])
    			return true;
    	}
    	return false;
    }
    private static boolean f2(int x,int[] d) {
    	int start=0, end=d.length-1;
    	while(start > end) {
    		int middle=(int)(start+end)/2;
    		if(d[middle]==x)
    			return true;
    		if(x>d[middle]) 
    			start=middle+1;
    		else
    			end=middle-1;
    	}
    	return x==d[start];
    }
    private static boolean f3(int x,int start,int end,int[] d) {
    	if(start > end)
    		return false;
    	int middle=(int)(start+end)/2;
    	if(d[middle]==x)
    		return true;
    	if(x>d[middle])
    		return f3(x,middle+1,end,d);
    	return f3(x,start,middle-1,d);
    	
    }
    public static void main(String[] args) {
        for(int n=0;n<=250000;n+=10000) {
        	if(n==0) {
        		System.out.println("0,0");
        		continue;
        	}
            int[] data = genData2(n,10);
            int[] key = genData1(n,n*100);

            long startTime = System.nanoTime();
            for(int i=0;i<key.length;i++) 
            	f3(key[i],i, i, data);
            System.out.println(""+n+","+(System.nanoTime()-startTime));
        }
    }

}
