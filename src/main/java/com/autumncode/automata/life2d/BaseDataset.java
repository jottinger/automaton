package com.autumncode.automata.life2d;

public abstract class BaseDataset implements Dataset {
    final int width;
    Renderer renderer = new SimpleStringRenderer();

    public BaseDataset(int width) {
        this.width = width;
    }

    @Override
    final public int getWidth() {
        return width;
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    protected void doCopy(int pattern, Dataset target) {
        for (int offset = 0; offset < getWidth(); offset++) {
            int octet = getOctet(offset);
            if (octet > 0) {
                if (offset >= 0) {
                    if (getBit(pattern, octet - 1) == 1) {
                        target.setCell(offset, true);
                    }
                }
            }
        }
    }
}
