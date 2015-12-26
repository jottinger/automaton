package com.autumncode.automata.life2d.renderer;

import com.autumncode.automata.life2d.Generation;
import com.autumncode.automata.life2d.Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class FourColorImageRenderer implements Renderer {
    BufferedImage image = null;
    int width;
    int height;
    Color[] colors = {Color.WHITE,
            Color.GREEN,
            Color.BLUE,
            Color.RED,
    };

    public FourColorImageRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    @Override
    public void render(Generation generation) {
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < generation.getWidth() / 2; i++) {
            int color = (generation.getCell(i * 2) ? 2 : 0) + (generation.getCell(i * 2 + 1) ? 1 : 0);
            g.setColor(colors[color]);
            g.drawLine(i*2, generation.getIteration(), i*2+1, generation.getIteration());
        }
    }

    public void write(OutputStream out) throws IOException {
        ImageIO.write(image, "PNG", out);
    }
}
