package com.autumncode.automata.life2d;

/**
 * This is a cellular automaton represented internally by an array of integers.
 * <p/>
 * A cell is "alive" if the integer is 1, and dead if it's 0.
 */
public class ArrayGeneration extends BaseGeneration {
    private int[] generation;

    public ArrayGeneration(int width) {
        super(width);
        generation = new int[width];        setCell(width / 2, true);

    }

    @Override
    public void setCell(int offset, boolean alive) {
        validateOffset(offset);
        generation[offset] = (alive ? 1 : 0);
    }

    @Override
    public boolean getCell(int offset) {
        validateOffset(offset);
        return generation[offset] != 0;
    }

    @Override
    public Generation copy(int pattern) {
        ArrayGeneration ds = new ArrayGeneration(getWidth());
        doCopy(pattern, ds);
        return ds;
    }
}
