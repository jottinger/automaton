package com.autumncode.automata.life2d.renderer;

import com.autumncode.automata.life2d.FastBitSetGeneration;
import com.autumncode.automata.life2d.Generation;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestSimpleImageRenderer {
    @Test
    public void testRenderer() throws IOException {
        Generation generation = new FastBitSetGeneration(1024);
        SimpleImageRenderer renderer = new SimpleImageRenderer(1024, 512);
        for (int i = 0; i < generation.getWidth() / 2; i++) {
            renderer.render(generation);
            generation = generation.copy(30);
        }
        renderer.write(new FileOutputStream("foo.png"));
    }
}
