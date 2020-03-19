package com.cuit.util.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RandomCodeImageBuilder {
    private char[] codeSequence = { 'A', '1','B', 'C', '2','D','3', 'E','4', 'F', '5','G','6', 'H', '7','I', '8','J',
            'K',   '9' ,'L', '1','M',  '2','N',  'P', '3', 'Q', '4', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};
    private Random random = new Random();

    public BufferedImage getBufferedImage(String strCode) {
        int width = 100;
        int height = 45;

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getColor(200, 250));
        g.setFont(new Font("宋体",Font.PLAIN,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
        //绘制字符
        for(int i = 0;i < 4;i++) {
            String rand = strCode.substring(i, i + 1);
            g.setColor(new Color(20 + random.nextInt(110),
                    20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand, 20 * i + 10, 30);
        }
        g.dispose();
        return image;
    }

    public String getCode() {
        //字符
        String strCode = "";
        for(int i = 0;i < 4;i++) {
            String rand = String.valueOf(codeSequence[random.nextInt(codeSequence. length)]);
            strCode = strCode + rand;
        }

        return strCode;
    }

    public Color getColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
