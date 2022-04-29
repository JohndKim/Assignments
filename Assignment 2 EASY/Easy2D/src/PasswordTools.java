public class PasswordTools {

    public static String generateFromSentence(String sentence, int minLength){
        String password = "";
        int cycle = 0;

        // splits based on white space
        String[] split = sentence.split("\\s+");
        // for each word, get the first char, and add to String password
        for (int i = 0; i < minLength; i++)
        {
            // enables us to loop through the sentence
            // i spent 30 minutes on one if statement because i forgot when ints divide, and the product is an int, it rounds (e.g. 6/4 = 1)
            if ((split.length) - (i) == split.length * (-cycle))
            {
                // number of times it loops the string
                cycle++;
            }
            int index = i - (split.length * cycle);
            char pwChar = split[index].charAt(0);
            password += pwChar;
        }
        return password;
    }

    public static boolean checkPassword(String password){
        boolean condition0 = false;
        boolean condition1 = false;
        boolean condition2 = false;
        boolean condition3 = false;
        boolean condition4 = false;

        for (int i = 0; i < password.length(); i++){
            int charInt = password.charAt(i);
            // checks for capital letters
            if (charInt >= 65 && charInt <= 90){
                condition0 = true;
            }
            // lowercase
            if (charInt >= 97 && charInt <= 122){
                condition1 = true;
            }
            // num after capital letter
            if (i > 0 && password.charAt(i-1) >= 65 && password.charAt(i-1) <= 90 && charInt >= 48 && charInt <= 57){
                condition2 = true;
            }
            // checks for $#?!_-=%
            if (charInt == 33 || charInt == 35 || charInt == 36 || charInt == 37 || charInt == 45 || charInt == 61 || charInt == 63 || charInt == 95 ){
                condition3 = true;
            }
            if (password.length() >= 8){
                condition4 = true;
            }
            if (condition0 && condition1 && condition2 && condition3 && condition4){
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordLeaked(String leakedDataset, String password){
        // loops for each character in the leaked password
        for (int i = 0; i < leakedDataset.length(); i++)
        {
            int numOfMatches = 0;
            // if the character of the leaked password = first character of password
            if (leakedDataset.charAt(i) == password.charAt(0))
            {
                // loops for the length of password
                for (int j = 0; j < password.length(); j++)
                {
                    // if leaked password char = password char, + 1 matches
                    // hence if the leaked password is the same as password, num of matches should be the same as pw length
                    if (leakedDataset.charAt(i+j) == password.charAt(j))
                    {
                        numOfMatches++;
                    }
                }
            }
            if (numOfMatches == password.length())
            {
                return true;
            }
        }
        return false;
    }
}
