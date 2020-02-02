package com.konrad.garagev3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ToRemove {
    public static void main(String[] args) {
        Random random = new Random();
        List<List<Integer>> listsOfIntegers = new ArrayList<>();
        List<List<Integer>> myListsOfIntegers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listsOfIntegers.add(random
                    .ints(10, 3, 8)
                    .boxed().collect(Collectors.toList()));
        }
        System.out.println(listsOfIntegers);

        listsOfIntegers.forEach(list -> myListsOfIntegers.add(list.stream().filter(e -> e != 5).collect(Collectors.toList())));
        System.out.println(myListsOfIntegers);

    }


}
