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
    private int size = 0;           //number of entries in the table

    public HashMapChained()
    {
        for (int i = 0; i < data.length; i++)
            data[i] = new LinkedList<>();
    }

    public void add(K key, V value)
    {
        int index = hash(key);
        // ensure the list doesn't already contain the key we're trying to add
        if (data[index].contains(new Entry<K, V>(key, null)))
            return;
        
        //add the new entry to the table
        data[index].add(new Entry<K, V>(key, value));
        size++;
        
        //rehash the table if necessary
        if ((double)size/data.length >= 0.75)
            rehash();
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
        if (listIndex != -1) {
            data[index].remove(listIndex);
            size--;
        }
    }

    public String toString()
    {
        String r = "";
        /*
        for( LinkedList<Entry<K, V>> entryList : data) {
            for ( Entry<K, V> entry : entryList ) {
                r += entry + "\n";
            }
        }
        * */
        
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
	
    private void rehash()
    {
        //reference to the old table
        LinkedList<Entry<K, V>>[] oldData = data;
        // create a new, larger table
        data = new LinkedList[2*data.length + 1];
        for (int i = 0; i < data.length; i++)
            data[i] = new LinkedList<>();
        size = 0;
        
        for( LinkedList<Entry<K, V>> entryList : oldData) {
            for ( Entry<K, V> entry : entryList)
                add(entry.getKey(), entry.getValue());
        }
        
    }
    
    public static void main(String[] args)
    {
	
    }	
}
