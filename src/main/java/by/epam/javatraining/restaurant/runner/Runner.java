package by.epam.javatraining.restaurant.runner;

import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        Map<Object, Integer> map = new HashMap<>();
        Object object1 = new Object();
        Object object2 = new Object();
        map.put(object1, 5);

        increaseQuantity(object1, map);

//        if (map.containsKey(object1)) {
//            Integer temp = map.get(object1);
//            temp++;
//            map.put(object1, temp);
//        }

        for (Map.Entry<Object, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static void increaseQuantity(Object object, Map map) {
        if (map.containsKey(object)) {
            Integer temp = (Integer) map.get(object);
            temp++;
            map.put(object, temp);
        } else {
            map.put(object, 1);
        }
    }
}
