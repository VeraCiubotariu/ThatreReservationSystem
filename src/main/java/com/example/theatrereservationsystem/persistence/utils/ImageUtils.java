package com.example.theatrereservationsystem.persistence.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageUtils {
    public static Blob getBlobFromImage(Image image) throws IOException, SQLException {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Create a BufferedImage of the same size as the Image
        java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        // Obtain the PixelReader from the Image
        PixelReader pixelReader = image.getPixelReader();

        // Write the pixels of the Image to the BufferedImage
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the color of the pixel
                Color color = pixelReader.getColor(x, y);
                // Convert the Color to a Java AWT Color
                java.awt.Color awtColor = new java.awt.Color((float) color.getRed(), (float) color.getGreen(), (float) color.getBlue(), (float) color.getOpacity());
                // Set the pixel in the BufferedImage
                bufferedImage.setRGB(x, y, awtColor.getRGB());
            }
        }

        // Write the BufferedImage to a byte array output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage, "png", outputStream); // You can also use "jpeg" for JPEG format
        byte[] imageBytes = outputStream.toByteArray();

        // Create a Blob from the byte array
        return new javax.sql.rowset.serial.SerialBlob(imageBytes);
    }
}
