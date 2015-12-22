package com.autumncode.automata.life2d;

/**
 * Created by josephottinger on 12/22/15.
 */
public interface Dataset {
    int getOctet(int offset);

    default int getBit(int pattern, int value) {
        return (pattern >> value) & 1;
    }

    <T extends Dataset> T copy(int pattern);

    String toString(String chars);
}
