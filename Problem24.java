/*
Nhập một mảng số nguyên. Liệt kê các phần tử xuất hiện trong mảng đúng 1 lần.
ví dụ:
[1, 2, 3, 1, 2, 5] => 3, 5 chỉ xuất hiện 1 lần
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.TreeMap;

public class Problem24 {

    public static List<Integer> filterDistinctElements(Integer[] arr) {
        TreeMap<Integer, Integer> distinctIntegers = new TreeMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!distinctIntegers.containsKey(arr[i])) {
                distinctIntegers.put(arr[i], 1);
            } else {
                distinctIntegers.put(arr[i], distinctIntegers.get(arr[i]) + 1);
            }
        }      
        List<Integer> distinctList = new ArrayList<Integer>();

        for (Integer key : distinctIntegers.keySet()) {
            if (distinctIntegers.get(key) == 1) {
                distinctList.add(key);
            }    
        }
        return distinctList;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input number of elements: ");
        Integer n = scan.nextInt();
        scan.nextLine();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Input element number " + i + " :");
            arr[i] = scan.nextInt();
            scan.nextLine();
        }

        List<Integer> distinctList = filterDistinctElements(arr);

        System.out.print("Distinct Elements: ");
        for (Integer item : distinctList) {
            System.out.print(item + " ");
        }
        System.out.println();

        scan.close();

    }
}
