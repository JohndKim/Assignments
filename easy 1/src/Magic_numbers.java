public class Magic_numbers {
    public static void main (final String[] args){
        System.console().format("%s%n", System.getProperty(String.valueOf(new Object() {
            public String toString() {
                StringBuffer b = new StringBuffer();
                java.util.Arrays.stream(new int[] {848, 776, 944, 776, 368, 944, 808, 880, 800, 888, 912, 368, 912, 368, 936, 912, 864})
                    .forEach(c -> b.append((char) (c >> 3)));
                return b.toString();
            }
        })));
    }
}