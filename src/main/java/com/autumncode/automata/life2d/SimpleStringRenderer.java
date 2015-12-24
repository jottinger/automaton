package com.autumncode.automata.life2d;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * This is a simple default renderer for 2D cellular automata, using asterisks for live cells and blank spaces
 * for dead cells.
 */
public class SimpleStringRenderer implements Renderer {
    final PrintWriter out;

    /**
     * Simple constructor using a Writer.
     * @param out the destination writer
     */
    public SimpleStringRenderer(Writer out) {
        this.out = new PrintWriter(out);
    }

    /**
     * Simple constructor using an OutputStream.
     * @param out the destination OutputStream
     */
    public SimpleStringRenderer(OutputStream out) {
        this(new OutputStreamWriter(out));
    }

    /**
     * Simple constructor that uses System.out as the destination
     */
    public SimpleStringRenderer() {
        this(System.out);
    }

    /**
     * This method generates a single generation's string representation, writing it to the renderer's destination.
     * @param generation the current generation to render
     */
    @Override
    public void render(Generation generation) {
        StringBuilder sb=new StringBuilder(generation.getWidth());
        for(int i = 0; i< generation.getWidth(); i++) {
            if(generation.getCell(i)) {
                sb.append("*");
            } else {
                sb.append(" ");
            }
        }
        out.print(sb.toString());
    }
}
