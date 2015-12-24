package com.autumncode.automata.life2d;

import org.testng.annotations.DataProvider;

public class BaseTestArrayAutomaton {
    @DataProvider
    Object[][] dataGenerator() {
        return new Object[][]{
                {30, "  ** "},
                {2, "  *  "},
                {1, " *   "},
                {4, "     "},
                {3, " **  "},
        };
    }
}
