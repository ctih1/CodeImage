package xyz.ctih1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Image {
    BufferedImage img;
    Graphics2D g2d;
    Font font;
    int width;
    int height;
    FontMetrics fm;
    String[] lines;
    int index;
    Random random;
    int offset;
    double elapsedTime;
    public Image() {
        img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        font = new Font("Arial",Font.PLAIN,12);
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        random = new Random();

    }
    public BufferedImage create(String text, BufferedImage img) {
        System.out.printf("[Image] Creating image %s/%s...%n", Main.current, Main.total);
        offset = (int) (random.nextFloat()*36);
        index = 0;
        lines = text.split("\n");
        height = fm.getHeight()*lines.length;
        width = height;

        if(img==null) {
            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();

        for(String line : lines) {
            g2d.setColor(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()));
            line = line.replace("\t","    ");
            g2d.drawString(line,0,(fm.getHeight()*index)+offset);
            index+=1;
        }
        g2d.dispose();
        Main.current++;
        return img;

    }
    public void export(BufferedImage img) {
        System.out.println("[Image] Exporting image...");
        try {
            ImageIO.write(img,"png",new File("text.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            elapsedTime = new Long(((System.currentTimeMillis())-Main.startTime)/10).doubleValue();
            System.out.printf("Done in %1$,.2f seconds!\n", elapsedTime/100);
        }
    }
}
