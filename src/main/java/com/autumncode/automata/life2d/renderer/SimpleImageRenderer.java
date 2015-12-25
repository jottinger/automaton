package com.autumncode.automata.life2d.renderer;

import com.autumncode.automata.life2d.Generation;
import com.autumncode.automata.life2d.Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleImageRenderer implements Renderer {
    BufferedImage image = null;
    int width;
    int height;

    public SimpleImageRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }

    @Override
    public void render(Generation generation) {
        Graphics2D g = image.createGraphics();
        for (int i = 0; i < generation.getWidth(); i++) {
            g.setColor(generation.getCell(i) ? Color.BLACK : Color.WHITE);
            g.drawLine(i, generation.getIteration(), i, generation.getIteration());
        }
    }

    public void write(OutputStream out) throws IOException {
        ImageIO.write(image, "PNG", out);
    }
}
