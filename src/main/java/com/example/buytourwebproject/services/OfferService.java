package com.example.buytourwebproject.services;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.DTOs.OfferQueueDTO;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.repositories.OfferRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import com.example.buytourwebproject.services.interfaces.RabbitMQService;
import com.example.buytourwebproject.utils.PhotoConverterUtil;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepo offerRepo;
    private final PhotoConverterUtil photoConverterUtil;
    private final RabbitMQService rabbitMQService;
    private final RequestStatusRepo requestStatusRepo;

    public OfferService(OfferRepo offerRepo, PhotoConverterUtil photoConverterUtil,
                        RabbitMQService rabbitMQService, RequestStatusRepo requestStatusRepo) {
        this.offerRepo = offerRepo;
        this.photoConverterUtil = photoConverterUtil;
        this.rabbitMQService = rabbitMQService;
        this.requestStatusRepo = requestStatusRepo;
    }

    public void createOffer(Offer offer) throws IOException {
//        System.out.println("***> " + offer.getId());
//        System.out.println("***> " + offer);
        offerRepo.save(offer);
        System.out.println("before");
        BufferedImage image = convertTextToImage(Arrays.asList(offer.getPrice().toString(),
                offer.getTravelStartDate().toString(), offer.getTravelEndDate().toString(), offer.getNotes()));
        System.out.println("here");

        System.out.println(image.toString());
        OfferQueueDTO offerQueueDTO = new OfferQueueDTO();
        offerQueueDTO.setAgentId(offer.getAgent().getId());
        offerQueueDTO.setImage(convertToByteArray(image));
        offerQueueDTO.setUuid(offer.getRequest().getUuid());
        requestStatusRepo.changeRequestStatusTypeByAgentAndRequestId(RequestType.OFFERED,
                offer.getAgent(), offer.getRequest().getId());
        rabbitMQService.send(offerQueueDTO);
        System.out.println("here 222");

    }


    public OfferDTO convertModelToDTO(Offer offer) {
        OfferDTO offerDTO = OfferDTO.builder()
                .id(offer.getId())
                .price(offer.getPrice())
                .travelStartDate(offer.getTravelStartDate())
                .travelEndDate(offer.getTravelEndDate())
                .notes(offer.getNotes())
                .build();
        return offerDTO;
    }

    public byte[] convertToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public BufferedImage convertTextToImage(List<String> text) throws IOException {
        int xCoordinate = 180;
        int yCoordinate = 35;
        BufferedImage bufferedImage = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 300);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
        graphics.drawString("Price:", 10, 35);
        graphics.drawString("Start Date:", 10, 70);
        graphics.drawString("End Date:", 10, 105);
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
//        File file = new File("C:\\Users\\HP\\Desktop\\images\\image.png");
//        try {
//            ImageIO.write(bufferedImage, "png", file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
    }
}
