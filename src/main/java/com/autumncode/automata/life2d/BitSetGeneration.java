package com.autumncode.automata.life2d;


import java.util.BitSet;

public class BitSetGeneration extends BaseGeneration {
    final BitSet generation = new BitSet();

    public BitSetGeneration(int length) {
        super(length);
        setCell(width / 2, true);
    }

    @Override
    public Generation copy(int pattern) {
        BitSetGeneration ds = new BitSetGeneration(getWidth());
        doCopy(pattern, ds);
        return ds;
    }

    @Override
    public void setCell(int offset, boolean alive) {
        validateOffset(offset);
        generation.set(offset, alive);
    }

    @Override
    public boolean getCell(int offset) {
        validateOffset(offset);
        return generation.get(offset);
    }

}
