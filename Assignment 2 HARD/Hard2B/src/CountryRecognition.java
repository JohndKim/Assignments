public class CountryRecognition {
    private String countryRec;

    // gets the six digit country recognition number
    public String changeToInt(String countryRec){
        int one = countryRec.charAt(0) - 55;
        int two = countryRec.charAt(1) - 55;
        // gets ascii value, subtracts 55 as it's capitalized, and gets the int
        String int1 = String.valueOf(one);
        String int2 = String.valueOf(two);
        // this is the countryrecognition converted to its int (string)
        return int1 + int2 + "00";
    }

    public String getCountryRec() {
        return countryRec;
    }

    public void setCountryRec(String countryRec) {
        this.countryRec = countryRec;
    }
}
