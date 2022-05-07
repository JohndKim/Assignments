package edu.norman.kayak;

public class Main {
    public static void main(String[] args){
        var list = new UnrolledLinkedList(3);

        for (int i = 0; i < 4; i++){
            System.out.println(list.push(i)); // true, true, true, false
        }

        System.out.println(list.size()); // 3
        System.out.println(list.toString(" ")); //  0 1 2
        System.out.println(list.get(2)); // 2

        System.out.println(list.pop()); // true
        System.out.println(list.size()); // 2
        System.out.println(list.toString(" ")); // 0 1
    }
}
