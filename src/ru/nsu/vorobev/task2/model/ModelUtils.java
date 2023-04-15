package ru.nsu.vorobev.task2.model;

import java.util.*;

public class ModelUtils {
    public static final int defaultMaxScore = 0;
    public static final String defaultLeftPlayerName = "";
    public static final String defaultRightPlayerName = "";
    public static final String defaultSingleEnemyName = "Computer";
    public static final int limitOfScore = 30;
    public static final int limitOfNameLength = 20;
    public static final int countOfPeopleInTop = 10;
    public static final int inTopScore = 2;
    static final String pathToSingleHistory = "history/single.txt";
    static final String pathToMultiPlayerHistory = "history/multi.txt";

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> -(o1.getValue()).compareTo(o2.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
