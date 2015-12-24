package com.autumncode.automata.life2d;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;

import static org.testng.Assert.assertEquals;

public class TestAutomata {
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
        ArrayGeneration dataset = new ArrayGeneration(input.length);
        for (int offset = 0; offset < input.length; offset++) {
            dataset.setCell(offset, input[offset] != 0);
        }
        for (int offset = 0; offset < input.length; offset++) {
            int value = dataset.getOctet(offset);
            assertEquals(value, values[offset]);
        }
    }

    @DataProvider
    Object[][] boundsData() {
        return new Object[][]{
                {ArrayGeneration.class, 5, -1},
                {ArrayGeneration.class, 5, 5},
                {FastBitSetGeneration.class, 5, -1},
                {FastBitSetGeneration.class, 5, 5},
        };
    }

    @Test(dataProvider = "boundsData",
            expectedExceptions = {IllegalArgumentException.class})
    public void testValidationBoundsForGet(Class<Generation> generationClass,
                                           int width,
                                           int boundary)
            throws Exception {
        Constructor c = generationClass.getConstructor(int.class);
        Generation ds = (Generation) c.newInstance(width);
        ds.getCell(boundary);
    }

    @Test(dataProvider = "boundsData",
            expectedExceptions = {IllegalArgumentException.class})
    public void testValidationBoundsForSet(Class<Generation> generationClass,
                                           int width,
                                           int boundary)
            throws Exception {
        Constructor c = generationClass.getConstructor(int.class);
        Generation ds = (Generation) c.newInstance(width);
        ds.setCell(boundary, true);
    }

    @Test(dataProvider = "boundsData")
    public void testLegitimateGet(Class<Generation> generationClass,
                                  int width,
                                  int boundary)
            throws Exception {
        Constructor c = generationClass.getConstructor(int.class);
        Generation ds = (Generation) c.newInstance(width);
        ds.getCell(Math.abs(boundary - 1));
    }

    @Test(dataProvider = "boundsData")
    public void testLegitimateSet(Class<Generation> generationClass,
                                  int width,
                                  int boundary)
            throws Exception {
        Constructor c = generationClass.getConstructor(int.class);
        Generation ds = (Generation) c.newInstance(width);
        ds.setCell(Math.abs(boundary - 1), true);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testArrayGenerationWithInvalidWidth() {
        Generation gen = new ArrayGeneration(-1);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testBitSetGenerationWithInvalidWidth() {
        Generation g = new FastBitSetGeneration(-1);
    }

    @DataProvider
    Object[][] stressTestProvider() {
        return new Object[][]{
                {new ArrayGeneration(2048), 30},
                {new FastBitSetGeneration(2048), 30},
                {new BitSetGeneration(2048), 30}
        };
    }

    @Test(dataProvider = "stressTestProvider",invocationCount = 10)
    public void stress(Generation gen, int pattern) {
        for (int i = 0; i < gen.getWidth(); i++) {
            gen = gen.copy(pattern);
        }
    }
}
