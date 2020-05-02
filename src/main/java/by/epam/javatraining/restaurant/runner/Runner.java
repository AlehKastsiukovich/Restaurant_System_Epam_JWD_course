package by.epam.javatraining.restaurant.runner;

import by.epam.javatraining.restaurant.pool.ConnectionPool;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private String name;
    private int age;

    public Runner(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        List<Runner> list = new ArrayList<>();
        Runner runner = new Runner("Aleh", 32);
        Runner runner1 = new Runner("Aleh", 32);
        list.add(runner);

        System.out.println(list.contains(runner1));


    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }

        Runner runner = (Runner) object;
        return runner.name.equals(this.name) && runner.age == this.age;
    }
}
