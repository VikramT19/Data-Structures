
public class Main {
	 public static void main(String[] args) {
	        SkipList<Integer> sl = new SkipList<>();
	        int[] data = {1,2,3,4,5,6,7,9};
	        for (int i : data) {
	            sl.add(i);
	        }
	        
	        sl.add(12);
	        System.out.println(sl);
	        
	        sl.add(8,15);
	        System.out.println(sl);
	        
	        System.out.println(sl.get(6));
	        System.out.println(sl);
	        
	        System.out.println(sl.size());
	        
	        sl.remove(5);
	        System.out.println(sl);
	        
	        sl.clear();
	        System.out.println(sl);
	        
  
	    }
	}


