package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dto.UserDto;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserDto userDto = new UserDto();
        userDto.setPassword("ddddddddddddddddddddddddd");
        userDto.setName("konrad");
        userDto.setEmail("konrad@onet.pl");
        System.out.println(validator.validate(userDto));
    }
}
