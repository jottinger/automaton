package com.autumncode.automata.life2d;

public class ArrayDataset extends BaseDataset {
    private int[] data;

    public ArrayDataset(int width) {
        super(width);
        data=new int[width];
        data[width/2]=1;
    }

    protected ArrayDataset(int[] data) {
        super(data.length);
        this.data=data;
    }

    @Override
    public void setCell(int offset, boolean alive) {
        if (offset > data.length || offset < 0) {
            throw new IllegalArgumentException("invalid offset " + offset + " for structure of length " + data.length);
        }
        data[offset] = (alive ? 1 : 0);
    }

    @Override
    public boolean getCell(int offset) {
        return data[offset]!=0;
    }

    @Override
    public <T extends Dataset> T copy(int pattern) {
        ArrayDataset ds = new ArrayDataset(getWidth());
        doCopy(pattern, ds);
        //noinspection unchecked
        return (T) ds;
    }
}
