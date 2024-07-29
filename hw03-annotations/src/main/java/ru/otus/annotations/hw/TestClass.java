package ru.otus.annotations.hw;

import ru.otus.annotations.hw.annotation.After;
import ru.otus.annotations.hw.annotation.Before;
import ru.otus.annotations.hw.annotation.Test;

public class TestClass {
    private int counter;

    public TestClass() {
        counter = 0;
    }

    @Before
    public void before() {
        System.out.println("Before " + counter);
        counter++;
    }

    @After
    public void after() {
        System.out.println("After " + counter);
    }

    @Test
    @Before
    public void test1() {
        System.out.println("Test1 " + counter);
        counter++;
    }

    @Test
    public void test2() {
        System.out.println("Test2 " + counter);
        throw new RuntimeException("Test error");
    }

    @Test
    public void test3() {
        System.out.println("Test3 " + counter);
        counter++;
    }
}
