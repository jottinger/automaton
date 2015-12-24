package com.autumncode.automata.life2d;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.lang.reflect.Constructor;

public class Main {
    @Parameter(names = "pattern")
    int pattern = 30;
    @Parameter(names = "length")
    int length = 64;
    @Parameter(names = "implementation")
    String implementation = "com.autumncode.automata.life2d.FastBitSetGeneration";

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        new JCommander(main, args);
        main.run();
    }

    private void run() throws Exception {
        @SuppressWarnings("unchecked")
        Class<Generation> datasetClass = (Class<Generation>) Class.forName(implementation);
        Constructor<Generation> constructor = datasetClass.getConstructor(int.class);
        Generation ds = constructor.newInstance(length);
        for (int i = 0; i < length / 2; i++) {
            System.out.println(ds.toString());
            ds = ds.copy(pattern);
        }
    }
}
