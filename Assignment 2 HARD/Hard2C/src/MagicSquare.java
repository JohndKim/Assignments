public class MagicSquare {
    static int[][] mSquare;
    static int magicNum = 0;

    // hmm wait i kinda forget fack should i have done this.square EGH I FORGET
    // bro i forgot everything about constructors wtf, isnt it like this.square = square T-T
    public MagicSquare(int[][] square){
        mSquare = square;
    }

    public String showMagicNumbers(int k) {
        // returns a string with all magic numbers from n = 1 to n = k in a row,
        // each separated by a comma
        String magicNums = "";
        for (int i = 1; i <= k; i++){
            // too lazy for pow
            int num = (i*i*i + i)/2;
            magicNums += String.valueOf(num) + ", ";
        }
        return magicNums;
    }

    public boolean isMagicSquare(){
        boolean isMagic = true;
        boolean isSemi = false;
        int cycle = mSquare.length - 1;
        int rows = mSquare.length;
        int columns = mSquare[0].length;

        // ROWS
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            // for each column
            for (int j = 0; j < columns; j++) {
                sum += mSquare[i][j];
            }

            // sets value for the magic number
            if (magicNum == 0) {
                magicNum = sum;
            }
//             if a sum of a row != magicNum, then it is not a magic square
            if (sum != magicNum) {
                isMagic = false;
                return isMagic;
            }
        }

        // COLUMNS
        for (int i = 0; i < columns; i++){
            int sum = 0;
            // for each row
            for (int j = 0; j < rows; j++){
                sum += mSquare[i][j];
            }

            // if a sum of a column != magicNum, then it is not a magic square
            if (sum != magicNum){
                isMagic = false;
                return isMagic;
            }
        }

        // DIAGONALS
        int dSum = 0;
        int dOtherSum = 0;
        for (int i = 0; i < rows; i++){
            // diagonal left to right \
            dSum += mSquare[i][i];
            // diagonal right to left  /
            dOtherSum += mSquare[i][cycle-i];

            // if a sum of a row != magicNum, then it is not a magic square
        }
        if (dSum != magicNum || dOtherSum != magicNum){
            isMagic = false;
            return isMagic;
        }
        return isMagic;
    }

    public boolean isSemimagicSquare(){
        boolean isMagic = true;
        boolean isSemi = false;
        int cycle = mSquare.length - 1;
        int rows = mSquare.length;
        int columns = mSquare[0].length;

        // ROWS = MAGICAL
        for (int i = 0; i < rows; i++){
            int sum = 0;
            // for each column
            for (int j = 0; j < columns; j++){
                sum += mSquare[i][j];
            }

            // if a sum of a row != magicNum, then it is not a magic square
            if (sum != magicNum){
                isMagic = false;
            }
        }

        // COLUMNS
        for (int i = 0; i < columns; i++){
            int sum = 0;
            // for each row
            for (int j = 0; j < rows; j++){
                sum += mSquare[i][j];
            }

            // if a sum of a column != magicNum, then it is not a magic square
            if (sum != magicNum){
                isMagic = false;
            }
        }

        // DIAGONALS
        int dSum = 0;
        int dOtherSum = 0;
        for (int i = 0; i < rows; i++){
            // diagonal left to right \
            dSum += mSquare[i][i];
            // diagonal right to left  /
            dOtherSum += mSquare[i][cycle-i];

            // if a sum of a row != magicNum, then it is not a magic square
        }
        if (dSum != magicNum || dOtherSum != magicNum && isMagic == true){
            isSemi = true;
            return isMagic;
        }
        return isSemi;
        // is semi = true
        // is not = false
    }

    public int[][] complement(){
        // calculates the complementary magic square (???)
        // oh reverses the magic square :skull:
        int temp;
        int rows = mSquare.length;
        int columns = mSquare[0].length;
        // swaps rows
        for (int i = 0; i < rows/2; i++){
            for (int j = 0; j < columns; j++){
                temp = mSquare[i][j];
                mSquare[i][j] = mSquare[rows-1-i][j];
                mSquare[rows-1-i][j] = temp;
            }
        }
        // swaps columns
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns/2; j++){
                temp = mSquare[i][j];
                mSquare[i][j] = mSquare[i][columns-1-j];
                mSquare[i][columns-1-j] = temp;
            }
        }
        return mSquare;
    }

    @Override
    public String toString(){
        String intRow = "";
        int rows = mSquare.length;
        int columns = mSquare[0].length;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                String num = Integer.toString(mSquare[i][j]);
                intRow = intRow + " " + num + " ";
            }
            intRow += "\r\n";
        }
        return intRow;
    }
}
