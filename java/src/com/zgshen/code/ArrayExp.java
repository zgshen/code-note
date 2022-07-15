package com.zgshen.code;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayExp {

    @Test
    public void output() {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        List<Integer> integers = Arrays.stream(arr).boxed().toList();
        System.out.println(integers);

        System.out.println(Arrays.toString(arr));
    }

}
