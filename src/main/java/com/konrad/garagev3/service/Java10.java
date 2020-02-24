package com.konrad.garagev3.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java10 {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);
        List<Integer> integers1 = List.copyOf(integers);
        System.out.println(integers == integers1);


        var collect = Stream.of(1, 2, 3, 5, 6).collect(Collectors.toUnmodifiableList());
        List<Integer> collect1 = Stream.of(1, 2, 3, 5, 6).collect(Collectors.toList());
        collect1.add(8);
    }
}