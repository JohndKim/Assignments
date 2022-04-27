public class Manufacturer {
    // can't access name outside the class (e.g. name = volvo)
    private String manufacturer_name;



    // allows us to get it outside the class
    public String getManufacturer_name() {
        return manufacturer_name;
    }
    // allows us to set it outside the class
    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }
}
