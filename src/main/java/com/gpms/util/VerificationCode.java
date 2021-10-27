package com.gpms.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCode {

    private int weight = 70;             //��֤��ͼƬ�ĳ��Ϳ�
    private int height = 30;
    private String text;                //����������֤����ı�����
    private Random r = new Random();      //��ȡ���������
    private String[] fontNames = {"����", "���Ŀ���", "����", "΢���ź�", "����_GB2312"};   //��������
    private String codes = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";    //��֤������

    /**
     * ����ͼƬ�ķ���
     */
    private BufferedImage createImage() {
        //����ͼƬ������
        BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
        //��ȡ����
        Graphics2D g = (Graphics2D) image.getGraphics();
        // �趨ͼ�񱳾�ɫ(��Ϊ��������������ƫ��)
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, weight, height);
        //����һ��ͼƬ
        return image;
    }

    /**
     * �������ߣ���֤�������������ֹ���������ͼƬ
     */
    private void drawLine(BufferedImage image) {
        int num = 10;
        //��������ߵ�����
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x = r.nextInt(weight);
            int y = r.nextInt(height);
            int xl = r.nextInt(weight);
            int yl = r.nextInt(height);
            g.setColor(getRandColor(0, 255));
            g.drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * ��ȡ��֤��ͼƬ�ķ���
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        //��ȡ����
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        drawLine(image);
        //���ĸ��ַ�����
        for (int i = 0; i < 4; i++) {
            //��������ַ�����Ϊֻ�л��ַ����ķ�����û�л��ַ��ķ�����������Ҫ���ַ�����ַ����ٻ�
            String s = randomChar() + "";
            //��ӵ�StringBuilder����
            sb.append(s);
            //�����ַ���x����
            float x = i * 1.0F * weight / 4;
            //�������壬���
            g.setFont(randomFont());
            //������ɫ�����
            g.setColor(randomColor());
            g.drawString(s, x, height - 5);
        }
        this.text = sb.toString();
        return image;
    }

    /**
     * ������Χ��������ɫ
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * ��ȡ��֤���ı��ķ���
     */
    public String getText() {
        return text;
    }

    /**
     * ��ȡ����ַ�
     */
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * ��ȡ�������ɫ
     */
    private Color randomColor() {
        int r = this.r.nextInt(200);
        int g = this.r.nextInt(200);
        int b = this.r.nextInt(200);
        //����һ�������ɫ
        return new Color(r, g, b);
    }

    /**
     * ��ȡ�������
     */
    private Font randomFont() {
        //��ȡ���������
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        //�����ȡ�������ʽ��0������ʽ��1�ǼӴ֣�2��б�壬3�ǼӴּ�б��
        int style = r.nextInt(4);
        //�����ȡ����Ĵ�С
        int size = r.nextInt(5) + 24;
        //����һ�����������
        return new Font(fontName, style, size);
    }
}