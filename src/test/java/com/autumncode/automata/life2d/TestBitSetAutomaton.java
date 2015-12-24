package com.autumncode.automata.life2d;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestBitSetAutomaton extends BaseTestArrayAutomaton {

    @Test(dataProvider = "dataGenerator")
    public void testOutput(int pattern, String nextGeneration) {
        Generation gen = new BitSetGeneration(5);
        assertEquals(gen.copy(pattern).toString(), nextGeneration);
    }

}
