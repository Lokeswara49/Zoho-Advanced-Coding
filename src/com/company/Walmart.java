package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Walmart {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int[] r=new int[n];
        int[] l=new int[n];
        l[0]=a[0];
        for(int i=1;i<n;i++){
            l[i]=l[i-1]+a[i];
        }
        r[n-1]=a[n-1];
        for(int i=n-2;i>=0;i--){
            r[i]=r[i+1]+a[i];
        }
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<n-1;i++){
            ans=Math.min(ans,Math.abs(l[i]-r[i+1]));
        }
        System.out.println(ans);

    }
}
