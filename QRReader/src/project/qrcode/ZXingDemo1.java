package project.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
 
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

public class ZXingDemo1 {
 
    private static BufferedImage image;

	public static BufferedImage getImage() {
		return image;
	}

	public static void setImage(BufferedImage image) {
		ZXingDemo1.image = image;
	}

	/**
     * @param args
     * @throws WriterException
     * @throws IOException
     */
    public static void main(String[] args) throws WriterException, IOException {
        String qrCodeText = "hello Good Day";
        String filePath = "D:\\QR\\QR_Code2.png";
        int size = 130;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrCodeText, size);
        save(qrFile,fileType);
        System.out.println(readQRImage(qrFile));
        System.out.println("DONE");
    }
 
    public static String readQRImage(File filepath) {
   
    	 
    	Result result = null;
    	BinaryBitmap binaryBitmap;
    	try{
    	 
    	binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filepath)))));
    	result = new MultiFormatReader().decode(binaryBitmap);
    	
    
    	}catch(Exception ex){
    	ex.printStackTrace();
    	}
    	if(result!=null)
    	return result.getText();
    	else
    		return "not found";
		
	}
    public static String readQRImage(BufferedImage i){
    	Result result = null;
    	BinaryBitmap binaryBitmap;
    	try{
    	 
    	binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(i)));
    	result = new MultiFormatReader().decode(binaryBitmap);
    	
    
    	}catch(Exception ex){
  
    	}
    	if(result!=null)
    	return result.getText();
    	else
    		return "not found";
    }
	public static void createQRImage(String qrCodeText, int size) throws WriterException, IOException 
	{
        // Create the ByteMatrix for the QR-Code that encodes the given String
		
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        if(qrCodeText!=null&&qrCodeText.length()>0){
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
        
        
        // Make the BufferedImage that are to hold the QRCode
        
        int matrixWidth = byteMatrix.getWidth();
        
        image = new BufferedImage(matrixWidth, matrixWidth,BufferedImage.TYPE_INT_RGB);
        
        image.createGraphics();
 
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);
 
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        //graphics.drawString(qrCodeText,20, size-10);
        }
        else
        	JOptionPane.showMessageDialog(null, "Enter QR Text Code");
        
    }
	public static void save(File qrFile, String fileType){
		
		try {
		
			ImageIO.write(image, fileType, qrFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    
 
}