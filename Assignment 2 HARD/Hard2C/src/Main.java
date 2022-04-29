public class Main {
    public static void main(String[] args){
        // first arg = operation (method); second arg = numbers
        // int k = Integer.parseInt(args[1]);
        // IM NOT DOING THIS HOW IT WAS MEANT TO BE DONE T-T
        // but it does the same thing
        // this task was >:C
    int[][] square = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
    MagicSquare magicSquare = new MagicSquare(square);
    int[][] mSquare = magicSquare.complement();
    System.out.println(magicSquare.toString());
    System.out.println(magicSquare.showMagicNumbers(6));
    System.out.println(magicSquare.isMagicSquare());
    System.out.println(magicSquare.isSemimagicSquare());
    }
}
