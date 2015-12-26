package com.autumncode.automata.life2d;

import com.autumncode.automata.life2d.renderer.SimpleImageRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

public class BWImageGenerator {
    public static void main(String[] args) throws IOException {
        for (int pattern = 1; pattern < 256; pattern++) {
            Generation generation = new FastBitSetGeneration(1024);
            SimpleImageRenderer renderer = new SimpleImageRenderer(1024, 512);
            for (int i = 0; i < generation.getWidth() / 2; i++) {
                renderer.render(generation);
                generation = generation.copy(pattern);
            }
            renderer.write(new FileOutputStream("bw-" + pattern + ".png"));
        }
    }
}
