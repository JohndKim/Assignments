import java.util.Scanner;

public class Hard1E {
    public static void main(String[] args){
        Scanner ask_key = new Scanner(System.in);
        System.out.println("Enter key. ");
        int key = ask_key.nextInt();

        switch (key) {
            case 50:
                System.out.println("Key 50: LB = 00, UB = 19.5");
                break;
            case 40:
                System.out.println("Key 40: LB = 20, UB = 21.5");
                break;
            case 17:
                System.out.println("Key 17: LB = 34, UB = 35.5");
            // etc.
        }

    }
}
