package com.autumncode.automata.life2d;

/**
 * This class represents a generation of a 2D cellular automaton.
 */
public interface Generation {
    /**
     * Returns the iteration of this particular generation.
     *
     * @return an integer representing the iteration of this particular generation
     */
    int getIteration();

    /**
     * Mutates the iteration number forthis generation.
     *
     * @param iteration new iteration number
     */
    void setIteration(int iteration);

    /**
     * Returns the pattern used to create this generation.
     *
     * @return an integer representing the pattern used to create this generation
     */
    int getPattern();

    /**
     * Mutates the pattern for this generation.
     *
     * @param pattern the new pattern for this generation
     */
    void setPattern(int pattern);

    /**
     * References the width of the cellular field. Should never be set to be less than one.
     *
     * @return width of the cellular field
     */
    int getWidth();

    /**
     * Gets the value of an octet with the offset representing the center bit of the octet. For example,
     * given a cellular field of <code>01110</code> (with a width of 5), <code>getOctet(1)</code> should return the binary
     * value <code>011</code>; <code>getOctet(2)</code> should return <code>111</code>. Offsets that would
     * overflow or underflow the cellular field width will use <code>0</code> in those values (thus,
     * <code>getOctet(0)</code> for <code>01110</code> would return <code>001</code>).
     *
     * @param offset the center cell in the cellular field
     * @return an integer representing the decimal value of the octet
     * @throws IllegalArgumentException This is thrown if the offset is out of range for the cellular field width
     *                                  in the default implementation.
     */
    default int getOctet(int offset) {
        validateOffset(offset);
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

    /**
     * Returns the value of the next cellular generation given a pattern and an octet.
     *
     * @param pattern The cellular pattern (where each bit represents an octet value's result)
     * @param value   the bit to examine
     * @return the status of the cell in the next generation
     */
    default boolean getBit(int pattern, int value) {
        return ((pattern >> value) & 1) == 1;
    }

    /**
     * Returns a clone of the current Generation, with the pattern applied.
     *
     * @param pattern The 2D automaton pattern for the next generation
     * @return the next generation in the cellular automaton
     */
    Generation copy(int pattern);

    /**
     * Should set the cell in the current generation be alive or dead.
     *
     * @param offset the cell to set in the field
     * @param alive  the cell status
     */
    void setCell(int offset, boolean alive);

    /**
     * Returns the cell's status in the current generation
     *
     * @param offset the cell to query
     * @return the cell's status
     */
    boolean getCell(int offset);

    /**
     * Validates the offset parameter. Used primarily as an internal method for implementations.
     *
     * @param offset the offset to validate
     */
    default void validateOffset(int offset) {
        if (offset >= getWidth() || offset < 0) {
            throw new IllegalArgumentException("invalid offset " + offset + " for structure of length " + getWidth());
        }
    }
}
