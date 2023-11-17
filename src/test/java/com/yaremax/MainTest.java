package com.yaremax;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MainTest {

    @Test
    void TestCountNumberOfSteps() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;

        // When
        int count = Main.countNumberOfSteps(start, end, step);

        // Then
        assertThat(count).isEqualTo(261);
    }

    @Test
    void TestGenerateXList() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;

        // When
        List<Double> xList = Main.generateXList(start, end, step);

        // Then
        assertThat(xList.get(0)).isEqualTo(start);
        assertThat(xList.get(120)).isEqualTo(start + step * 120);
        assertThat(xList.get(260)).isEqualTo(end);
        assertThat(xList.size()).isEqualTo(261);
    }

    @Test
    void TestGenerateYList() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);

        // When
        List<Double> yList = Main.generateYList(xList);

        // Then
        assertThat(yList.get(0)).isEqualTo(Main.calc4(start));
        assertThat(yList.get(120)).isEqualTo(Main.calc4(xList.get(120)));
        assertThat(yList.get(260)).isEqualTo(Main.calc4(end));
        assertThat(yList.size()).isEqualTo(xList.size()).isEqualTo(261);
    }

    @Test
    void TestFindMax() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);
        List<Double> yList = Main.generateYList(xList);
        double expectedMax = yList.stream().max(Double::compareTo).get();

        // When
        double max = Main.findMax(yList);

        // Then
        assertThat(max).isEqualTo(expectedMax);
    }

    @Test
    void TestFindMin() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);
        List<Double> yList = Main.generateYList(xList);
        double expectedMin = yList.stream().min(Double::compareTo).get();

        // When
        double min = Main.findMin(yList);

        // Then
        assertThat(min).isEqualTo(expectedMin);
    }

    @Test
    void TestFindSum() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);
        List<Double> yList = Main.generateYList(xList);
        double expectedSum = yList.stream().mapToDouble(i -> i).sum();

        // When
        double sum = Main.findSum(yList);

        // Then
        assertThat(sum).isEqualTo(expectedSum);
    }

    @Test
    void TestFindAverage() {
        // Given
        double start = 0.7;
        double end = 2.0;
        double step = 0.005;
        List<Double> xList = Main.generateXList(start, end, step);
        List<Double> yList = Main.generateYList(xList);
        double expectedAverage = yList.stream().mapToDouble(i -> i).average().getAsDouble();

        // When
        double average = Main.findAverage(yList);

        // Then
        assertThat(average).isEqualTo(expectedAverage);
    }

//    @Test
//    void generateXYMap() {
//        // Given
//        double start = 0.7;
//        double end = 2.0;
//        double step = 0.005;
//        List<Double> xList = Main.generateXList(start, end, step);
//        List<Double> yList = Main.generateYList(xList);
//
//        // When
//        Map<Double, Double> xyMap = Main.generateXYMap(xList, yList);
//
//        // Then
//        assertThat(xyMap.get(xList.get(xList.size()-1)))
//                .isEqualTo(yList.get(yList.size()-1));
//        assertThat(xyMap.get(xList.get(0)))
//                .isEqualTo(yList.get(0));
//    }

    @Nested
    class TestCalc4 {
        @Test
        void TestCalc4_xLess() {
            // Given
            double x = 1.2;

            // When
            double y = Main.calc4(x);

            // Then
            assertThat(Main.calc4(x)).isEqualTo(Math.PI * x * x - 7 / (x * x));
        }
        @Test
        void TestCalc4_xEqual() {
            // Given
            double a = 1.65;
            double x = 1.3;

            // When
            double y = Main.calc4(x);

            // Then
            assertThat(Main.calc4(x)).isEqualTo(a * x * x * x + 7 * sqrt(x));
        }
        @Test
        void TestCalc4_xGrater() {
            // Given
            double a = 1.65;
            double x = 1.4000005                                                                        ;

            // When
            double y = Main.calc4(x);

            // Then
            assertThat(Main.calc4(x)).isEqualTo(log(x + 7 * sqrt(abs(x + a))));
        }
    }
}