package com.autumncode.automata.life2d;

import com.autumncode.automata.life2d.renderer.FourColorImageRenderer;

import java.io.FileOutputStream;

public class FourColorGenerator {
    public static void main(String[] args) throws Exception {
        for(int pattern=1;pattern<255;pattern++) {
            System.out.println("Generating pattern "+pattern);
            FourColorImageRenderer renderer=new FourColorImageRenderer(512,256);
            Generation generation=new FastBitSetGeneration(512);
            for(int i=0;i<generation.getWidth()/2;i++) {
                renderer.render(generation);
                generation=generation.copy(pattern);
            }
            renderer.write(new FileOutputStream("color-"+pattern+".png"));
        }
    }
}
