package com.autumncode.automata.life2d;

import java.util.Arrays;

public class Dataset {
    private final int[] data;

    public Dataset(int[] input) {
        this(input, false);
    }

    public Dataset(int[] input, boolean clean) {
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

    public int getBit(int pattern, int value) {
        return (pattern >> value) & 1;
    }

    public Dataset copy(int pattern) {
        int[] copy = new int[data.length];
        for (int offset = 0; offset < copy.length; offset++) {
            int octet = getOctet(offset);
            if (octet > 0) {
                if (offset >= 0) {
                    copy[offset] = copy[offset] | getBit(pattern, octet - 1);
                }
            }
        }
        return new Dataset(copy, true);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(data).forEach(o -> sb.append(Integer.toString(o)));
        return sb.toString();
    }

    public String toString(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(data).forEach(o -> sb.append(s.charAt(o)));
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] data = new int[200];
        data[data.length / 2] = 1;
        Dataset ds = new Dataset(data);
        int pattern = (int) (Math.random() * 256);
        System.out.println(pattern);
        for (int i = 0; i < data.length / 2; i++) {
            System.out.println(ds.toString(" *"));
            ds = ds.copy(pattern);
        }
    }
}
