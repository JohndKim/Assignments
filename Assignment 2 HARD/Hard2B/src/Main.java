public class Main {
    public static void main(String[] args){
//        i would do the arg stuff but idk how to do it in java, i do tho in C >.>;;;
//        String countryrec = args[0];
//        String bankcode = args[1];
//        String accountnum = args[2];

    // gets country code AND gets the 6 digit country code
    CountryRecognition countryRec = new CountryRecognition();
    countryRec.setCountryRec("FR");
    String countryRecInt = countryRec.changeToInt(countryRec.getCountryRec());

    // calculates the combined bank code and acocunt number (18 digits)
    AccBank accbank = new AccBank();
    accbank.setBankcode("76543210");
    accbank.setAccnum("4321");
    String accnum = accbank.remakeAccnum(accbank.getAccnum());
    String bankacc = accbank.bankaccNum(accbank.getBankcode(), accnum);

    // calculates 2 digit checksum
    CheckSum checkSum = new CheckSum();
    String checksum = checkSum.checkSumCalculation(bankacc, countryRecInt);

    // calculates IBAN
    String IBAN = countryRec.getCountryRec() + checksum + bankacc;

    // bro idk how to stop a new line from being made...
    String[] parts = IBAN.split("(?<=\\G....)");
    for (String part : parts){
        System.out.println(part);
    }
    }
}