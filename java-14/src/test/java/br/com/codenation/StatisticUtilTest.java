package br.com.codenation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticUtilTest {

    @Test
    public void testAverage() {
        assertEquals(3, StatisticUtil.average(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMode() {
        assertEquals(3, StatisticUtil.mode(new int[]{1, 2, 3, 3}));
    }

    @Test
    public void testMedian() {
        assertEquals(3, StatisticUtil.median(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMedianWithOddUnorderedArray() {
        assertEquals(3, StatisticUtil.median(new int[]{3, 4, 5, 1, 2}));
    }

    @Test
    public void testMedianWithOddUnorderedNegativeArray() {
        assertEquals(-3, StatisticUtil.median(new int[]{-3, -4, -5, -1, -2}));
    }

    @Test
    public void testMedianWithEvenArrayUnOrderedAndNegativeNumbers() {
        assertEquals(-4, StatisticUtil.median(new int[]{-4, -6, -2}));
    }

    @Test
    public void testMedianWithOddArrayUnOrderedAndNegativeAndPositiveNumbers() {
        assertEquals(-1, StatisticUtil.median(new int[]{-3, 5, -1}));
    }

    @Test
    public void testMedianWithEvenArrayUnOrdered() {
        assertEquals(2, StatisticUtil.median(new int[]{1, 2, 3, 4}));
    }
}
