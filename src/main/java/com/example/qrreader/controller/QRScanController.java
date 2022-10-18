package com.example.qrreader.controller;

import com.example.qrreader.core.KeyUtil;
import com.example.qrreader.core.ScQRCodeReader;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The controller class which accepts requests for QR Code
 * Reading. This class uses the 256-bit encryption key to
 * unmask secure QR codes.
 */
@RestController
public class QRScanController {

    @Autowired
    private Environment environment;

    /**
     * This method is called when the QR image is uploaded by the user.
     * It scans the QR code and sends across the QR message as response.
     *
     * @param file The QR code image
     * @return The result of scan.
     */
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            String resultText = scanQR(file);
            resultMap.put("message", resultText);
        } catch (Exception e) {
            resultMap.put("message", "QR code cannot be scanned!");
        }
        return ResponseEntity.ok(resultMap);
    }

    /*
     * Scans QR code file and returns the QR payload message.
     */
    private String scanQR(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(image);
        HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
        ScQRCodeReader reader = new ScQRCodeReader();
        String keyStr = environment.getProperty("key");
        String keyAsHex = Hex.encodeHexString(keyStr.getBytes());
        int[] keyArr = KeyUtil.hexToBinary(keyAsHex);
        try {
            Result result = reader.decode(binaryBitmap, keyArr);
            return result.getText();
        } catch (NotFoundException | ChecksumException | FormatException e) {
            return "QR Image cannot be scanned!";
        }

    }
}