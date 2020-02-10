package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.WorkerType;
import com.konrad.garagev3.model.dto.UserDto;

import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Stream<List<Integer>> listStream = myListsOfIntegers.stream().map(element -> element.stream().filter(e -> e != 5).collect(Collectors.toList()));

//        List<UserDto> workers = new ArrayList<>();
//        workers.add(UserDto.builder().id(1L).age(33).name("aaa").workerType(WorkerType.MANAGER).build());
//        workers.add(UserDto.builder().id(2L).age(22).name("bbb").workerType(WorkerType.MECHANIC).build());
//        workers.add(UserDto.builder().id(3L).age(23).name("ccc").workerType(WorkerType.MANAGER).build());
//        workers.add(UserDto.builder().id(4L).age(23).name("ddd").workerType(WorkerType.SELLER).build());
//
//
//        Map<Long, UserDto> collect = workers.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));
//        Map<Long, UserDto> collect2 = workers.stream().collect(Collectors.toMap(UserDto::getId, Function.identity()));
//        Map<WorkerType, List<UserDto>> collect1 = workers.stream().collect(Collectors.groupingBy(u -> u.getWorkerType()));
//        Map<WorkerType, List<UserDto>> collect3 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType));
//        Map<WorkerType, Set<UserDto>> collect4 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.toSet()));
//        Map<WorkerType, Long> collect5 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.counting()));
//        Map<WorkerType, Double> collect6 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.averagingDouble(UserDto::getAge)));
//        Map<WorkerType, Integer> collect7 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.summingInt(UserDto::getAge)));
//        Map<WorkerType, List<@NotEmpty(message = "*Please provide your name") String>> collect8 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.mapping(UserDto::getName, Collectors.toList())));
//        Map<WorkerType, DoubleSummaryStatistics> collect9 = workers.stream().collect(Collectors.groupingBy(UserDto::getWorkerType, Collectors.summarizingDouble(UserDto::getAge)));
//        Map<Boolean, List<UserDto>> collect10 = workers.stream().collect(Collectors.partitioningBy(u -> u.getWorkerType().equals(WorkerType.MANAGER)));
//        System.out.println(collect9);

//        collect2.merge();

    }


}
