package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        System.out.println("请输入要排序的数字,录入0表示结束:");
        List<Integer> integerList = new ArrayList<>();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.next();

            if ("0".equalsIgnoreCase(s)) {
                break;
            }

            if (!scanner.hasNextInt()) {
                System.out.println("请录入数字");
                continue;
            }

            integerList.add(Integer.valueOf(s));
        }
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortAndPrint(integerList);
    }

    public void sortAndPrint(List<Integer> list) {
        for (Integer num : list) {
            System.out.println(num);
        }
    }
}
