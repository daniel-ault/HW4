/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 * Implementation of the Map interface using a hash table with chaining.
 * Each index in the table is an instance of java.util.LinkedList.
 */
import java.util.LinkedList;

public class HashMapChained<K, V> implements Map<K, V>
{
    private LinkedList<Entry<K, V>>[] data = new LinkedList[5];

    public HashMapChained()
    {
        for (int i = 0; i < data.length; i++)
            data[i] = new LinkedList<>();
    }

    public void add(K key, V value)
    {
        int index = hash(key);
        // ensure the list doesn't already contain the key we're trying to add
        if (!data[index].contains(new Entry<K, V>(key, null)))
            data[index].add(new Entry<K, V>(key, value));
    }

    public V get(K key)
    {
        int index = hash(key);

        // find the key in the list
        int listIndex = data[index].indexOf(new Entry<K, V>(key, null));
        if (listIndex != -1)
            return data[index].get(listIndex).getValue();
        else
            return null;
    }

    public void remove(K key)
    {
        int index = hash(key);

        // find the key in the list
        int listIndex = data[index].indexOf(new Entry<K, V>(key, null));
        if (listIndex != -1)
            data[index].remove(listIndex);
    }

    public String toString()
    {
        String r = "";
        for (int i = 0; i < data.length; i++)
            r += i + " - " + data[i] + "\n";
        return r;
    }

    // Translates the specified key into a valid array index by doing the following:
    //  1) call hashCode() (inherited from Object), which returns an int value
    //  2) mod by data.length to ensure the value is within the bounds of the array
    //  3) ensure the value is positive by adding data.length
    private int hash(K key)
    {
        int hash = key.hashCode() % data.length;
        while (hash < 0)
            hash += data.length;
        return hash;
    }
	
    public static void main(String[] args)
    {
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
