package com.autumncode.automata.life2d;

public interface Dataset {
    int getWidth();

    default int getOctet(int offset) {
        int value = 0;
        for (int i = -1; i < 2; i++) {
            value = value * 2;
            try {
                value += getCell(offset + i) ? 1 : 0;
            } catch (Exception ignored) {

            }
        }
        return value;
    }

    default int getBit(int pattern, int value) {
        return (pattern >> value) & 1;
    }

    <T extends Dataset> T copy(int pattern);

    void setCell(int offset, boolean alive);

    boolean getCell(int offset);

    default void validateOffset(int offset) {
        if (offset > getWidth() || offset < 0) {
            throw new IllegalArgumentException("invalid offset " + offset + " for structure of length " + getWidth());
        }
    }

    Renderer getRenderer();

    void setRenderer(Renderer renderer);
}
