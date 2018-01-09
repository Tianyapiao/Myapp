package com.example.asus.algorithm.algorithmn;

import java.util.Scanner;

/**
 * Created by Asus on 2018/1/8.
 * @author Asus
 * 冒泡排序算法
 */

public class BubbleSort {

    public static int[] bubbleSort(int[] a){
        int [] arr=null;
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length- 1; j++) {
                if (a[j + 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void display(int[] a){

        for(int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
    }


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数组长度:");
        int n = sc.nextInt();
        System.out.println("请输入数组！");
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i]=sc.nextInt();
        }
        bubbleSort(a);
        display(a);
    }
}
