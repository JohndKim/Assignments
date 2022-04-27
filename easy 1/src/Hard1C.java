public class Hard1C {
    public static void main(String[] args){
        int i = 10;
        int j = i++;
        int x = --j;
        i = ++x;
        System.out.println(i);
        // i = 10
        // 2. String as the months are composed of words,
        // and it is more intuitive which month is in what
        // season in its string form rather than its int
        boolean q = true;
        boolean w = false;
        boolean t = true;
        //!(q||w) && t||!w^!q
        // true or false AND true or true^false
        System.out.println(2^5);
        // 7
        double a = 200.1;
        double b = 600.1;
        double c = 300.1;
        double d = 400.1;
        System.out.println(a*b-c*d);
        // double = 9.999999999985448
        // bro float just won't work at all, idk why, double is just a longer version of float
        // but anyways these things have float imprecision thingies which make the values different from its actual values


    }
}
