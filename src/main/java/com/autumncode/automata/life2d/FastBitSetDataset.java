package com.autumncode.automata.life2d;

import javolution.util.FastBitSet;

public class FastBitSetDataset implements Dataset {
    final FastBitSet data = new FastBitSet();
    int length = 0;

    public FastBitSetDataset(int[] input) {
        data.clear();
        length = input.length;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                this.data.set(i, true);
            }
        }
    }

    FastBitSetDataset() {
    }

    @Override
    public int getOctet(int offset) {
        int value = 0;
        for (int i = -1; i < 2; i++) {
            value = value * 2;
            if (offset + i >= 0) {
                value += (data.get(offset + i) ? 1 : 0);
            }
        }
        return value;
    }

    @Override
    public <T extends Dataset> T copy(int pattern) {
        FastBitSetDataset ds = new FastBitSetDataset();
        ds.length = length;
        for (int offset = 0; offset < length; offset++) {
            int octet = getOctet(offset);
            if (octet > 0) {
                if (offset >= 0) {
                    if (getBit(pattern, octet - 1) == 1) {
                        ds.data.set(offset, true);
                    }
                }
            }
        }
        return (T)ds;
    }

    public String toString() {
        return toString("10");

    }

    public String toString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(data.get(i) ? s.charAt(0) : s.charAt(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] data = new int[200];
        data[data.length / 2] = 1;
        FastBitSetDataset ds = new FastBitSetDataset(data);
        int pattern = (int) (Math.random() * 256);
        System.out.println(pattern);
        for (int i = 0; i < data.length / 2; i++) {
            System.out.println(ds.toString("* "));
            ds = ds.copy(pattern);
        }
    }
}
