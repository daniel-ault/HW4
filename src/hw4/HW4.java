/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 *
 * @author Daniel
 */
public class HW4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Testing the BSTSet class....\n");
        testSet();
        System.out.println("\n\n\nTesting the HashMapChained rehash....\n");
        testHashMapChainedRehash();
    }
    
    public static void testSet() {
        BSTSet<Integer> set1 = new BSTSet<>();
        BSTSet<Integer> set2 = new BSTSet<>();
        BSTSet<Integer> set3 = new BSTSet<>();
        BSTSet<Integer> set4 = new BSTSet<>();
        BSTSet<Integer> set5 = new BSTSet<>();
        BSTSet<Integer> set6 = new BSTSet<>();
        
        set1.add(1);
        set1.add(4);
        set1.add(5);
        
        set2.add(4);
        set2.add(2);
        set2.add(8);
        set2.add(9);
        
        set3.add(1);
        set3.add(6);
        set3.add(0);
        
        set4.add(0);
        
        set5.add(0);
        set5.add(1);
        set5.add(4);
        set5.add(5);
        set5.add(6);
        set5.add(10);
        set5.add(12);
        
        set6.add(1);
        set6.add(4);
        set6.add(9);
        
        System.out.println("Set 1: " + set1.toString());
        System.out.println("Set 2: " + set2.toString());
        System.out.println("Set 3: " + set3.toString());
        System.out.println("Set 4: " + set4.toString());
        System.out.println("Set 5: " + set5.toString());
        System.out.println("Set 6: " + set6.toString());
        
        System.out.println("Union of sets 1 and 2: " + set1.union(set2));
        System.out.println("Intersection of sets 1 and 2: " + set1.intersection(set2));
        
        //1, 3, 4 are subsets of 5
        System.out.println("Is set 1 a subset of set 5? " + set1.subsetOf(set5));
        System.out.println("Is set 3 a subset of set 5? " + set3.subsetOf(set5));
        System.out.println("Is set 4 a subset of set 5? " + set4.subsetOf(set5));
        //6 is not subset of 5
        
        System.out.println("Is set 6 a subset of set 5? " + set6.subsetOf(set5));
    }
    
    public static void testHashMapChainedRehash() {
        Map<Integer, String> tmnt = new HashMapChained<>();
	tmnt.add(13, "Shredder");
	tmnt.add(90, "Rocksteady");
	tmnt.add(18, "Krang");
	tmnt.add(18, "Krang2");
	System.out.println(tmnt);
	System.out.println(tmnt.get(18));
	tmnt.remove(18);
	System.out.println(tmnt);
	System.out.println(tmnt.get(18));
	tmnt.remove(90);
	tmnt.remove(29);
	tmnt.add(23, "Bebop");
	tmnt.add(73, "Technodrome");
	tmnt.add(29, "Foot Soldier");
	tmnt.add(32, "Baxter Stockman");
		
	System.out.println(tmnt);
		
	System.out.println(tmnt.get(13));
	System.out.println(tmnt.get(18));
	System.out.println(tmnt.get(23));
	System.out.println(tmnt.get(24));
        
    }
}
