package com.autumncode.automata.life2d;

import java.io.StringWriter;

/**
 * This is an abstract class that contains basic cellular generation. Designed to serve as a common base class for
 * all 2D cellular automata.
 */
public abstract class BaseGeneration implements Generation {
    final int width;

    public BaseGeneration(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Width of a cellular automata cannot be less than 1. Value: " + width);
        }
        this.width = width;
    }

    /**
     * This is the designated width of the cellular field.
     *
     * @return the cellular field width
     */
    @Override
    final public int getWidth() {
        return width;
    }

    protected void doCopy(int pattern, Generation target) {
        for (int offset = 0; offset < getWidth(); offset++) {
            int octet = getOctet(offset);
            if (octet > 0) {
                target.setCell(offset, getBit(pattern, octet - 1));
            }
        }
    }

    /**
     * This generates a representation of the cellular field with blank spaces for dead cells and asterisks for
     * live cells.
     *
     * @return A string representation of the current generation
     */
    public String toString() {
        StringWriter sw = new StringWriter();
        new SimpleStringRenderer(sw).render(this);
        return sw.toString();
    }
}
