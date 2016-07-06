package com.kaishengit.test;

import org.junit.Test;

public class MyTestCase {

    @Test
    public void testTel() {
        int[] arr = new int[]{5, 9, 1, 0, 3, 6};
        int[] index = new int[]{2, 4, 2, 5, 5, 3, 5, 0, 4, 1, 5};
        String tel = "";
        for (int i : index) {
            tel += arr[i];
        }
        System.out.println("联系方式:" + tel);
    }
}
