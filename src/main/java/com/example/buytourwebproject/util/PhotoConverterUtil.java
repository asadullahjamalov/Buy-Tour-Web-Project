package com.example.buytourwebproject.util;

import com.example.buytourwebproject.models.Offer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class PhotoConverterUtil {

    public byte[] convertToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public BufferedImage convertTextToImage(Offer offer) {
        BufferedImage bufferedImage = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 1000, 500);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
        graphics.drawString("Description: " + offer.getDescription(), 10, 30);
        graphics.drawString("Travel Locations: " + offer.getTravelLocations(), 10, 60);
        graphics.drawString("Price: " + offer.getPrice().toString(), 10, 90);
        graphics.drawString("Start Date: " + offer.getTravelStartDate().toString(), 10, 120);
        graphics.drawString("End Date: " + offer.getTravelEndDate().toString(), 10, 150);
        graphics.drawString("Notes:" + offer.getNotes(), 10, 180);
        graphics.drawString("Buy Tour App", 800, 450);
        return bufferedImage;
    }

}
