package com.autumncode.automata.life2d;

public class BitPattern {
    private final int pattern;

    public BitPattern(int pattern) {
        this.pattern = pattern;
    }

    public int getBit(int value) {
        return (pattern >> value) & 1;
    }
}
