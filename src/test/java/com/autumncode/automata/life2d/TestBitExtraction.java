package com.autumncode.automata.life2d;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestBitExtraction {
    @DataProvider
    Object[][] extractionData() {
        return new Object[][]{
                {new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}},
                {new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 0, 1, 2}},
                {new int[]{0, 0, 0, 1, 0}, new int[]{0, 0, 1, 2, 4}},
                {new int[]{0, 0, 1, 0, 0}, new int[]{0, 1, 2, 4, 0}},
                {new int[]{0, 1, 0, 0, 0}, new int[]{1, 2, 4, 0, 0}},
                {new int[]{1, 0, 0, 0, 0}, new int[]{2, 4, 0, 0, 0}},
        };
    }


    @Test(dataProvider = "extractionData")
    public void testSimpleExtract(int[] input, int[] values) {
        Dataset dataset = new Dataset(input);
        for (int offset = 0; offset < input.length; offset++) {
            int value = dataset.getOctet(offset);
            assertEquals(value, values[offset]);
        }
    }

    @Test
    public void testBitPattern() {
        BitPattern bitPattern = new BitPattern(27);

        assertEquals(bitPattern.getBit(0), 1);
        assertEquals(bitPattern.getBit(1), 1);
        assertEquals(bitPattern.getBit(2), 0);
        assertEquals(bitPattern.getBit(3), 1);
        assertEquals(bitPattern.getBit(4), 1);
        assertEquals(bitPattern.getBit(5), 0);
    }

    @Test
    public void testLife() {
        int[] data = new int[256];
        data[128] = 1;
        Dataset ds = new Dataset(data);
        for (int i = 0; i < 100; i++) {
            System.out.println(ds.toString(" *"));
            ds = ds.copy(30);
        }
    }
}
