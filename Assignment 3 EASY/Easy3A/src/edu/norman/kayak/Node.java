package edu.norman.kayak;

/**
 * This class represents a node in a linked list
 * Each node has a value (int) and a pointer which has the address of the next node (node)
 */
public class Node {
    int value;
    Node next;

    /**
     * A constructor that initializes the value and the next node (value = v, next = null)
     *
     * @param v is the value
     */
    public Node(int v){
        this.value = v;
        next = null;
    }
}

