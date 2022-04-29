import java.math.BigInteger;

public class CheckSum {
    // returns checksum
    public String checkSumCalculation(String bankacc, String countryRec){
        BigInteger combined = new BigInteger(bankacc + countryRec);
        BigInteger ninetyseven = new BigInteger("97");
        BigInteger ninetyeight = new BigInteger("98");
        String checksum = String.valueOf(ninetyeight.subtract(combined.mod(ninetyseven)));
        if (checksum.length() < 2){
            checksum = "0" + checksum;
            return checksum;
        }
        return checksum;
    }
}
