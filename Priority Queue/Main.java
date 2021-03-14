import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		PriorityQueue2011<Integer> s = new PriorityQueue2011<>();
        int[] data = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i : data) {
            s.add(i);
        }
        
        
        System.out.println(s);
        s.add(49);
        System.out.println(s);
        s.add(23);
        System.out.println(s);
        s.poll();
        System.out.println(s);
        s.remove();
        System.out.println(s);
        System.out.println(s.peek());
        System.out.println(s.element());
        System.out.println(s.size());
        System.out.println(s.toString());
        
        System.out.println("\n");
        System.out.println("NEXT PQ: \n");
        
    
        PriorityQueue<Integer> p = new PriorityQueue<>();
        int[] data1 =  {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i : data1) {
            p.add(i);
        }
        

        System.out.println(p);
        p.add(49);
        System.out.println(p);
        p.add(23);
        System.out.println(p);
        p.poll();
        System.out.println(p);
        p.remove();
        System.out.println(p);
        System.out.println(p.peek());
        System.out.println(p.element());
        System.out.println(p.size());
        System.out.println(p.toString());
        System.out.println("Next line");
        
        Integer [] data2 =  {1,9,2,8,3,7,4,6,5};

        System.out.println("Sort results: ");
        PQSort.heapSort2011(data2);
        System.out.println(Arrays.toString(data2));
        
        System.out.println(s.toTree());
        
       
       
  

    

	}

}
