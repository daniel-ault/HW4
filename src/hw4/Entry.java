/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 * Class representing a single entry (key-value pair) in a hash table.
 */
public class Entry<K, V>
{
    private K key;
    private V value;

    public K getKey() { return key; }
    public V getValue() { return value; }

    public Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public String toString()
    {
        return "<" + key + ", " + value + ">";
    }

    public boolean equals(Object o)
    {
        return (o instanceof Entry && ((Entry)o).getKey().equals(key));
    }
}
