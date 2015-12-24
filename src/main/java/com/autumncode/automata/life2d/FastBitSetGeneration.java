package com.autumncode.automata.life2d;

import javolution.util.FastBitSet;

/**
 * This is a 2D cellular automaton using a FastBitSet as an internal data structure.
 */
public class FastBitSetGeneration extends BaseGeneration {
    final FastBitSet generation = new FastBitSet();

    public FastBitSetGeneration(int length) {
        super(length);
        setCell(width / 2, true);
    }

    @Override
    public Generation copy(int pattern) {
        FastBitSetGeneration ds = new FastBitSetGeneration(getWidth());
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
