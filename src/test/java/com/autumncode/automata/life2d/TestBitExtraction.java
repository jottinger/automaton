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
        ArrayDataset dataset = new ArrayDataset(input);
        for (int offset = 0; offset < input.length; offset++) {
            int value = dataset.getOctet(offset);
            assertEquals(value, values[offset]);
        }
    }
}
