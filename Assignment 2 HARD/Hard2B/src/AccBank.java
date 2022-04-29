public class AccBank {
    private String bankcode;
    private String accnum;

    // ensures account number has 10 digits
    public String remakeAccnum(String accnum){
        while (accnum.length() < 10) {
            accnum = "0" + accnum;
        }
        return accnum;
    }

    // combines the bank code and account number
    public String bankaccNum(String bankcode, String new_accnum){
        return bankcode + new_accnum;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getAccnum() {
        return accnum;
    }

    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }
}
