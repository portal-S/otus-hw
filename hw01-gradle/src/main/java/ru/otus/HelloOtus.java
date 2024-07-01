package ru.otus;

import java.util.StringJoiner;

public class HelloOtus {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(",", "prefix: ", " :suffix");
        sj.add("first");
        sj.add("second");
        sj.add("third");

        System.out.println(sj);
    }
}
