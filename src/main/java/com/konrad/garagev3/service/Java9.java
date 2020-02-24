package com.konrad.garagev3.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Java9 {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);
        Map<Integer, String> integerStringMap = Map.of(1, "jeden", 2, "dwa");
//        integerStringMap.put(3, "trzy");
        Map<Integer, String> jeden = Map.ofEntries(Map.entry(1, "jeden"));

        Stream.of(1, 2, 3, 4, 5).takeWhile(i -> i < 4).forEach(System.out::println);
    }
}
