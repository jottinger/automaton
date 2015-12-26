package com.autumncode.automata.life2d.renderer;

import com.autumncode.automata.life2d.FastBitSetGeneration;
import com.autumncode.automata.life2d.Generation;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestSimpleImageRenderer {
    @Test
    public void testRenderer() throws IOException {
        Set<Integer> patterns = new HashSet<>();
        while (patterns.size() < 2) {
            patterns.add((int) (Math.random() * 254) + 1);
        }
        for (Integer pattern : patterns) {
            Generation generation = new FastBitSetGeneration(1024);
            SimpleImageRenderer renderer = new SimpleImageRenderer(1024, 512);
            for (int i = 0; i < generation.getWidth() / 2; i++) {
                renderer.render(generation);
                generation = generation.copy(pattern);
            }
            renderer.write(new FileOutputStream(pattern + ".png"));
        }
    }
}
