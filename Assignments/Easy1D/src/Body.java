public class Body {
    // in tons
    private double weight;
    private int num_of_seats;
    // the air resistance of the body, 0.1-0.9
    private double cw_value;



    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNum_of_seats() {
        return num_of_seats;
    }

    public void setNum_of_seats(int num_of_seats) {
        this.num_of_seats = num_of_seats;
    }

    public double getCw_value() {
        return cw_value;
    }

    public void setCw_value(double cw_value) {
        this.cw_value = cw_value;
    }
}
