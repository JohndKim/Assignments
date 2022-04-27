import java.util.Scanner;

public class Easy1B {
    public static void main(String[] args) {

        Scanner first = new Scanner(System.in);
        String w1 = first.nextLine();
        Scanner second = new Scanner(System.in);
        String w2 = second.nextLine();
        Scanner third = new Scanner(System.in);
        String w3 = third.nextLine();

        System.out.println(w1 + "-" + w2 + "-" + w3);
    }
}