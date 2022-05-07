package edu.norman.kayak;

/**
 * This class represents a linked list, a list of nodes
 */
public class UnrolledLinkedList {
    private final int arraySize;
    private int elementsAdded;
    private Node first; // first node, next = null, until we add an element

    /**
     * Initializes the array size, num of elements, and the first node (null, empty, nada, nothing)
     * @param arraySize size of the linked list
     */
    public UnrolledLinkedList(int arraySize){
        this.arraySize = arraySize;
        this.elementsAdded = 0;
        this.first = null;
    }

    /**
     * pushes a node into the last index
     *
     * @param value node int
     * @return true = successful, false = not
     */
    public boolean push(int value){
        // inserts an element at the end of the list
        // if a new array is created, return true
        var newNode = new Node(value);
        if (elementsAdded == arraySize) return false;
        if (first == null) first = newNode; // checks if it's the first element, adds the value to the first node
        else {
            Node last = first; // copies first element (last.next should equal null if it's the 2nd item)
            while (last.next != null){
                last = last.next;
            }            last.next = newNode; // points to the new node we created
        }
        elementsAdded++;
        return true;
    }

    /**
     * removes the last node
     *
     * @return true = success, false = not
     */
    public boolean pop(){
        // remove the element at the end of the list
        // removed = true
        if (first == null) return false; // no items in the list
        else {
            Node last = first;
            while (last.next != null){
                last = last.next;
            }
            elementsAdded--;
            return true;
        }
    }

    /**
     * returns the int at the index specified
     *
     * @param index index
     * @return the int
     */
    public int get(int index){
        Node thisNode = first;
        for (int j = 0; j < index; j++){
            thisNode = thisNode.next;
        }
        return thisNode.value;
    }

    /**
     * Gets the number of nodes
     *
     * @return size
     */
    public int size(){
        // get number of currently stored elements
        return elementsAdded;
    }

    /**
     * Converts the node ints into a string from index 0 to num of nodes
     *
     * @param separator the thing that separates each int
     * @return linked list string
     */
    public String toString(String separator){
        // returns all the elements of the list in ascending order in terms of index
        // sorted as a new string (elements are in a row)
        StringBuilder list = new StringBuilder();
        Node last = first;

        for (int i = 0; i < elementsAdded; i++){
            list.append(last.value).append(separator);
            last = last.next;
        }

        return list.toString();
    }
}
