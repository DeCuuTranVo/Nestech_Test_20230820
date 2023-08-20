/*
 * Viết chương trình liệt kê n số nguyên tố đầu tiên trong java. 
 * Số nguyên dương n được nhập từ bàn phím.
 */

import java.util.Scanner;

public class Problem21 {

    public static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input an positive integer:");

        int n;
        try {
            n = scan.nextInt();
            if (n <= 0) {
                System.out.println("Please input a positive integer next time.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Please input an integer next time.");
            return;
        }
        
        if (n == 1) {
            System.out.println("No prime number found.");
            return;
        }

        for (int i = 2; i < n; i++) {
            //System.out.println(i + " is Prime: " + isPrime(i));
            if (isPrime(i)) {
                System.out.print(i + " ");
            }       
        }      
        System.out.println("");
        scan.close();
    }
}
