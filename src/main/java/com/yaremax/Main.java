package com.yaremax;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);
        List<Double> yList = Main.generateYList(xList);

        printMaxAndMin(xList, yList);
    }

    public static double calc4(double x) {
        double a = 1.65;
        double y = 0;

        if (x < 1.3) {
            y = PI * x * x - 7 / (x * x);
        } else if (x == 1.3) {
            y = a * x * x * x + 7 * sqrt(x);
        } else {
            y = log(x + 7 * sqrt(abs(x + a)));
        }

        return y;
    }

    public static int countNumberOfSteps(double start, double end, double step) {
        int count = (int) Math.round((end - start) / step) + 1;
        return count;
    }

    public static List<Double> generateXList(double start, double end, double step) {
        int size = countNumberOfSteps(start, end, step);

        List<Double> xList = IntStream
                .range(0, size)
                .mapToDouble(i -> start + i * step)
                .boxed()
                .toList();

        return xList;
    }

    public static List<Double> generateYList(List<Double> xList) {
        List<Double> yList = new ArrayList<>();

        for (Double x : xList) {
            yList.add(calc4(x));
        }

        return yList;
    }

    public static double findMax(List<Double> yList) {
        return yList.stream().max(Double::compareTo).get();
    }

    public static double findMin(List<Double> yList) {
        return yList.stream().min(Double::compareTo).get();
    }

    public static double findSum(List<Double> yList) {
        return yList.stream().mapToDouble(i -> i).sum();
    }

    public static double findAverage(List<Double> yList) {
        return yList.stream().mapToDouble(i -> i).average().getAsDouble();
    }

//    public static Map<Double, Double> generateXYMap(List<Double> xList, List<Double> yList){
//        Map<Double, Double> xyMap = new HashMap<>();
//
//        for (int i = 0; i < xList.size(); i++) {
//            xyMap.put(xList.get(i), yList.get(i));
//        }
//
//        return xyMap;
//    }

    public static void printMaxAndMin(List<Double> xList, List<Double> yList) {
        double maxY = findMax(yList);
        double maxX;
        for (int i = 0; i < yList.size(); i++) {
            if (yList.get(i) == maxY) {
                maxX = xList.get(i);
                System.out.println("Max y = " + maxY + ", x = " + maxX);
                break;
            }
        }

        double minY = findMin(yList);
        double minX;
        for (int i = 0; i < yList.size(); i++) {
            if (yList.get(i) == minY) {
                minX = xList.get(i);
                System.out.println("Min y = " + minY + ", x = " + minX);
                break;
            }
        }
    }
}