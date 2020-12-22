package ru.appline;

import java.util.*;

public class CardinalPoints {
    private static final CardinalPoints instance = new CardinalPoints();
    private final Map<String, List<Integer>> cardinalPoints;

    private CardinalPoints() {
        cardinalPoints = new HashMap<String, List<Integer>>();
    }

    public static CardinalPoints getInstance() {
        return instance;
    }

    public void addPoint(String point, int left, int right) {
        cardinalPoints.put(point, Arrays.asList(left, right));
    }

    public String getPointName(int degree) {
        for (String key: cardinalPoints.keySet()) {
            List<Integer> degrees = cardinalPoints.get(key);
            if (degrees.get(0) > degrees.get(1)) {
                if (degree >= degrees.get(0) && degree < 360 ||
                        degree >= 0 && degree < degrees.get(1)) {
                    return key;
                }
            } else if (degree >= degrees.get(0) && degree < degrees.get(1)) {
                return key;
            }
        }
        return String.format("Degree %s is not found", degree);
    }

    public Map<String, List<Integer>> getMap() {
        return cardinalPoints;
    }
}
