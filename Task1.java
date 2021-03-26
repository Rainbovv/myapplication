import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int qty = scan.nextInt();
        int maxValue = 1;
        int count = 0;

        for(int i = 0; i < qty; i++) {
            int nextInt = scan.nextInt();

            if(nextInt == maxValue) {
                count++;
            }
            if(nextInt > maxValue) {
                count = 1;
                maxValue = nextInt;
            }
        }
        scan.close();
        System.out.println(count);
    }
}
