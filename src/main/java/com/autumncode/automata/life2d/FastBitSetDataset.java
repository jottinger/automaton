package com.autumncode.automata.life2d;

import javolution.util.FastBitSet;

public class FastBitSetDataset extends BaseDataset {
    final FastBitSet data = new FastBitSet();

    public FastBitSetDataset(int[] input) {
        super(input.length);
        data.clear();
        for (int i = 0; i < getWidth(); i++) {
            if (input[i] != 0) {
                this.data.set(i, true);
            }
        }
    }

    public FastBitSetDataset(int length) {
        super(length);
    }

    @Override
    public <T extends Dataset> T copy(int pattern) {
        FastBitSetDataset ds = new FastBitSetDataset(getWidth());
        doCopy(pattern, ds);
        //noinspection unchecked
        return (T) ds;
    }

    @Override
    public void setCell(int offset, boolean alive) {
        validateOffset(offset);
        data.set(offset, alive);
    }

    @Override
    public boolean getCell(int offset) {
        validateOffset(offset);
        return data.get(offset);
    }
}
