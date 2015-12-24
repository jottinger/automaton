package com.autumncode.automata.life2d;

import java.io.PrintStream;

public class SimpleStringRenderer implements Renderer {
    final PrintStream out;

    public SimpleStringRenderer(PrintStream out) {
        this.out = out;
    }

    public SimpleStringRenderer() {
        this(System.out);
    }

    @Override
    public void render(Dataset data) {
        StringBuilder sb=new StringBuilder(data.getWidth());
        for(int i=0;i<data.getWidth();i++) {
            if(data.getCell(i)) {
                sb.append("*");
            } else {
                sb.append(" ");
            }
        }
        out.println(sb.toString());
    }
}
