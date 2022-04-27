import java.lang.Math;

public class Main {
    public static void main(String[] args){
        Car golf = new Car();

        // VW Golf with 75 HP, 200kg engine,
        // 5 seats, pw = 0.35, body weight = 1.8 tons

        golf.motor.setHP(75);
        golf.motor.setWeight(200);
        golf.body.setNum_of_seats(5);
        golf.body.setWeight(1.8);
        golf.body.setCw_value(0.35);
        golf.manufacturer_name.setManufacturer_name("VW");
        golf.type_name.setType_name("Golf");

        Car porsche = new Car();

        // Porsche 911, 420 HP 250kg, 4 seats
        // 0.32 pw, 1.25 tonnes

        porsche.motor.setHP(420);
        porsche.motor.setWeight(250);
        porsche.body.setNum_of_seats(4);
        porsche.body.setCw_value(0.32);
        porsche.body.setWeight(1.25);
        porsche.manufacturer_name.setManufacturer_name("VW");
        porsche.type_name.setType_name("Porsche 911");

        // max speed = 6 * (sqrt(HP))/cw
        // feel like i could've done this in the class T-T
        golf.max_speed = 6*(Math.sqrt(golf.motor.getHP())) / golf.body.getCw_value();

        // fuel consumption = 1/25 * sqrtHP * sqrtkg *  cw
        golf.fuel_consumption = (0.04) * Math.sqrt(golf.motor.getHP()) * Math.sqrt(golf.motor.getWeight()) * golf.body.getCw_value();

        // testing to see if fuel consumption is good for the environment
        boolean ecologically_good;
        double good_for_env = 1.21;

        // simplified: ecologically_good = golf_fuel_consumption < good_for_env;

        if (golf.fuel_consumption < good_for_env) {
            ecologically_good = true;
        } else {
            ecologically_good = false;
        }

        // HP becomes 100
        golf.motor.setHP(100);
        // golf engine swapped with porsche's
        golf.motor = porsche.motor;

    }
}
