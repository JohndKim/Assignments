import java.lang.Math;

public class RoundAndRound {
    public static void main(String[] args){
        // you create an instance of the object (just like with String name, name is an instance of the String I think)
        Circle circle1 = new Circle();
        circle1.radius = 5.1;
        circle1.circumference = circle1.radius * circle1.pi;
        circle1.area = Math.pow(circle1.radius, 2) * circle1.pi;

        // additionally finding circumference and area
        Circle circle2 = new Circle();
        circle2.radius = 17.5;
        circle2.circumference = circle2.radius * circle2.pi;
        circle2.area = Math.pow(circle2.radius, 2) * circle2.pi;

        System.out.println("circle1.radius = " + circle1.radius);
        System.out.println("circle1.circumference = " + circle1.circumference);
        System.out.println("circle1.area = " + circle1.area);
        System.out.println("circle2.radius = " + circle2.radius);
        System.out.println("circle2.circumference = " + circle2.circumference);
        System.out.println("circle2.area = " + circle2.area);
        // add more output-commands here

        Cone cone1 = new Cone();
        cone1.area = circle1.area;
        cone1.height = 10.3;
        cone1.volume = 0.333 * circle1.area * cone1.height;
        cone1.surface_area = circle1.area + circle1.pi * circle1.radius * (Math.sqrt(Math.pow(cone1.height, 2) + Math.pow(circle1.radius, 2)));

        Cone cone2 = new Cone();
        cone2.area = circle2.area;
        cone2.height = 10.3;
        cone2.volume = 0.333 * circle2.area * cone2.height;
        cone2.surface_area = circle2.area + circle2.pi * circle2.radius * (Math.sqrt(Math.pow(cone2.height, 2) + Math.pow(circle2.radius, 2)));

        System.out.println("cone1.volume = " + cone1.volume);
        System.out.println("cone1.surface_area = " + cone1.surface_area);
        System.out.println("cone2.volume = " + cone2.volume);
        System.out.println("cone2.surface_area = " + cone2.surface_area);

    }
}
