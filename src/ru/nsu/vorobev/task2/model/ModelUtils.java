package ru.nsu.vorobev.task2.model;

import jdk.jshell.execution.Util;

import java.util.*;
import java.util.stream.Stream;

public class ModelUtils {
    public static final int defaultMaxScore = 0;
    public static final String defaultLeftPlayerName = "";
    public static final String defaultRightPlayerName = "";
    public static final String defaultSingleEnemyName = "Computer";
    public static final int limitOfScore = 30;
    public static final int limitOfNameLength = 20;
    static final String pathToSingleHistory = "history/single.txt";
    static final String pathToMultiPlayerHistory = "history/multi.txt";
    static final int inTopScore = 10;
    static final int countOfPeopleInTop = 10;

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
