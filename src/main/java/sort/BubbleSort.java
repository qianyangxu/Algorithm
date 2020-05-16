package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 冒泡排序
 * 核心:双层循环
 * 外层循环控制总次数
 * 内层循环取i,i+1两两比较,i>i+1就交换位置
 * 时间复杂度O(n*n)
 */
public class BubbleSort {

    public static void main(String[] args) {
        System.out.println("请输入要排序的数字,录入';'表示结束:");
        List<Integer> integerList = new ArrayList<Integer>();
        BubbleSort bubbleSort = new BubbleSort();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.contains(" ")) {
                boolean flag = false;
                String[] array = line.split(" ");
                for (String str : array) {
                    if (str.contains(";")) {
                        str = str.replaceAll(";", "");
                        flag = true;
                    }

                    if (!isInteger(str)) {
                        System.out.println("请录入数字");
                        break;
                    }

                    integerList.add(Integer.valueOf(str));
                    if (flag) {
                        break;
                    }
                }
                break;
            } else {
                if (";".equalsIgnoreCase(line)) {
                    break;
                }

                if (!isInteger(line)) {
                    System.out.println("请录入数字");
                    continue;
                }

                integerList.add(Integer.valueOf(line));
            }
        }
        bubbleSort.sortAndPrint(integerList);
    }

    private void sortAndPrint(List<Integer> list) {
        System.out.println("排序前结果:" + Arrays.toString(list.toArray()));

        // 写法一
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        // 写法二
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > list.get(i)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        System.out.println("排序后结果:" + Arrays.toString(list.toArray()));
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
