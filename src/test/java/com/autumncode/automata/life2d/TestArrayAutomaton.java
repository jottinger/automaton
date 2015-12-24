package com.autumncode.automata.life2d;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestArrayAutomaton extends BaseTestArrayAutomaton {

    @Test(dataProvider = "dataGenerator")
    public void testOutput(int pattern, String nextGeneration) {
        Generation gen = new ArrayGeneration(5);
        assertEquals(gen.copy(pattern).toString(), nextGeneration);
    }

}
