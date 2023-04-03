package Practice;

public class teest {
    public static void main(String[] args) {
        int i=0;
        int j=800;
        String string = "hello";
        while (true){
            System.out.println(string);
            if (i == j){
                clearScreen();
                System.out.println("Screen Cleared");
                string = i+"hello";
                j+=j;
            }
//            if (i == 1000)
//                break;
            i++;
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
