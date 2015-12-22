package com.autumncode.automata.life2d;

import java.util.Arrays;

public class ArrayDataset implements Dataset {
    private final int[] data;

    public ArrayDataset(int[] input) {
        this(input, false);
    }

    public ArrayDataset(int[] input, boolean clean) {
        data = Arrays.copyOf(input, input.length);
        // if we were called externally, someone might
        // have stored data as something other than 1 or 0.
        if (!clean) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != 0) {
                    data[i] = 1;
                }
            }
        }
    }

    @Override
    public int getOctet(int offset) {
        int value = 0;
        for (int i = -1; i < 2; i++) {
            value = value * 2;
            if (offset + i >= 0) {
                if (offset + i < data.length) {
                    value += (data[offset + i] == 0 ? 0 : 1);
                }
            }
        }
        return value;
    }

    @Override
    public <T extends Dataset> T copy(int pattern) {
        int[] copy = new int[data.length];
        for (int offset = 0; offset < copy.length; offset++) {
            int octet = getOctet(offset);
            if (octet > 0) {
                if (offset >= 0) {
                    copy[offset] = copy[offset] | getBit(pattern, octet - 1);
                }
            }
        }
        return (T) new ArrayDataset(copy, true);
    }

    @Override
    public String toString() {
        return toString(" *");
    }

    public String toString(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(data).forEach(o -> sb.append(s.charAt(o)));
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] data = new int[200];
        data[data.length / 2] = 1;
        Dataset ds = new ArrayDataset(data);
        int pattern = (int) (Math.random() * 256);
        System.out.println(pattern);
        for (int i = 0; i < data.length / 2; i++) {
            System.out.println(ds);
            ds = ds.copy(pattern);
        }
    }
}
