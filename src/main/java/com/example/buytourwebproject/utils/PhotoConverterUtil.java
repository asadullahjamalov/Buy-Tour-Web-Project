package com.example.buytourwebproject.utils;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Component
public class PhotoConverterUtil {

    public BufferedImage convertTextToImage(List<String> text) throws IOException {
        int xCoordinate = 180;
        int yCoordinate = 35;
        BufferedImage bufferedImage = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 300);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 10));
        graphics.drawString("Price:", 10, 35);
        graphics.drawString("Travel Start Date:", 10, 70);
        graphics.drawString("Travel End Date:", 10, 105);
        graphics.drawString("Notes:", 10, 140);
        graphics.drawString("Buy Tour App", 390, 290);
        for(String line : text) {
            if(line.length()>100){
                int count = line.length()/50;
                while(count!=0){
                    graphics.drawString(line.substring(0, 50), xCoordinate, yCoordinate);
                    yCoordinate += 25;
                    line=line.substring(50);
                    if(line.length()<50){
                        graphics.drawString(line, xCoordinate, yCoordinate);
                        yCoordinate += 25;
                    }
                    count--;
                }
            }
            else {
                graphics.drawString(line, xCoordinate, yCoordinate);
                yCoordinate += 35;
            }
        }
        return bufferedImage;
    }
}
