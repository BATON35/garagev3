package com.konrad.garagev3.service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Java11 {
    public static void main(String[] args) {
        String test = " test ";
        System.out.print(test.stripLeading());
        System.out.println(test.stripTrailing());

        String test2 = "jeden \n dwa \n try";
        test2.lines().forEach(System.out::println);

        Predicate<String> aa = Pattern.compile(".+a{2}").asMatchPredicate();
        System.out.println(aa.test("bbaa"));

        TimeUnit days = TimeUnit.DAYS;
        System.out.println(days.convert(Duration.ofHours(62)));

//        try {
//            Files.writeString(Path.of("link"), "string");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
