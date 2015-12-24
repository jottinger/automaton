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
    String implementation = "com.autumncode.automata.life2d.ArrayDataset";

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        new JCommander(main, args);
        main.run();
    }

    private void run() throws Exception {
        @SuppressWarnings("unchecked")
        Class<Dataset> datasetClass = (Class<Dataset>) Class.forName(implementation);
        Constructor<Dataset> constructor = datasetClass.getConstructor(int.class);
        Dataset ds = constructor.newInstance(length);
        ds.setCell(length / 2, true);
        for (int i = 0; i < length / 2; i++) {
            ds.getRenderer().render(ds);
            ds = ds.copy(pattern);
        }

    }
}
