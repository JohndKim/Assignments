public class Main {
    public static void main(String[] args){
        PasswordTools passwordTools = new PasswordTools();
        System.out.println(passwordTools.isPasswordLeaked("123456abcdHAUS123", "cdHA"));
    }
}
