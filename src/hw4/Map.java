/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 * Interface specifying the basic map operations.
 */
public interface Map<K, V>
{
    // Adds the specified key-value pair to the map.  Does nothing if the key already
    //  exists in the map.
    void add(K key, V value);

    // Returns the value associated with the specified key, or null of that key doesn't
    //  exist in the map
    V get(K key);
	
    // Removes the key-value pair with the specified key from the map.  Does nothing if
    //  the key doesn't exist in the map.
    void remove(K key);
}
