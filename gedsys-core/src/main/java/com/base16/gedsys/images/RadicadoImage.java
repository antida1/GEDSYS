/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.images;

import com.lowagie.text.pdf.BarcodeEAN;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;

/**
 *
 * @author rober
 */
public class RadicadoImage {

    public String Generar(String nombreEntidad, String numeroRadicado, String logoPath, String destinationPath, String Usuario) {
        try {
            File path = new File(logoPath);
            BufferedImage logo = ImageIO.read(new File(path, "logoInstitucion.png"));
            BufferedImage qrCode = ImageIO.read(GenerarQRCode(numeroRadicado, 100, 100));
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            //BufferedImage imageBar = (BufferedImage) this.GenerarCodeBar(numeroRadicado);
            int Ratio = 3;
            Graphics2D g2D = img.createGraphics();
            Font font = new Font("Monospaced", Font.BOLD, 10);
            g2D.setFont(font);
            FontMetrics fm = g2D.getFontMetrics();
            int width = fm.stringWidth("------------------------------") + (logo.getWidth() / Ratio) + 78;
            //int height = Math.max(fm.getHeight() * 5, (logo.getHeight()+imageBar.getHeight() / Ratio));
            int height = Math.max(fm.getHeight() * 6, (logo.getHeight() / Ratio));
            g2D.dispose();

            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            g2D = img.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2D.setFont(font);
            fm = g2D.getFontMetrics();
            g2D.setColor(Color.black);
            g2D.drawImage(logo, 0, 0, logo.getWidth() / Ratio, logo.getHeight() / Ratio, null);
            g2D.drawImage(qrCode, width - qrCode.getWidth(), 0, qrCode.getWidth(), qrCode.getHeight(), null);
            
            g2D.drawString(nombreEntidad, logo.getWidth() / Ratio, fm.getAscent());
            font = new Font("Monospaced", Font.BOLD, 12);
            g2D.setFont(font);
            g2D.drawString(numeroRadicado, logo.getWidth() / Ratio, fm.getAscent() *2 );
            
            
            font = new Font("Monospaced", Font.BOLD, 10);
            g2D.setFont(font);
            g2D.drawString("------------------------------", logo.getWidth() / Ratio, fm.getAscent() * 3);
            g2D.drawString("GESTIONADO POR -----------------", logo.getWidth() / Ratio, fm.getAscent() * 4);
            g2D.drawString(Usuario, logo.getWidth() / Ratio, fm.getAscent() * 5);
            g2D.drawString("------------------------------", logo.getWidth() / Ratio, fm.getAscent() * 6);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date today = new Date();
            g2D.setColor(Color.black);

            g2D.drawString(sdf.format(today), logo.getWidth() / Ratio, fm.getAscent() * 7);
            //g2D.drawImage(img, 0, logo.getHeight(), imageBar.getWidth() , imageBar.getHeight()/Ratio, null);
            g2D.dispose();
            
            
            ImageIO.write(img, "png", new File(destinationPath + numeroRadicado + ".png"));
        } catch (IOException e) {
             Logger.getLogger(RadicadoImage.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return numeroRadicado + ".png";
    }

    public Image GenerarCodeBar(String texto) {
        BarcodeEAN codeEAN;
        codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(codeEAN.EAN13);
        codeEAN.setCode(texto);
        Image imageEAN = codeEAN.createAwtImage(Color.BLACK, Color.WHITE);
        return imageEAN;
    }
    
    public File GenerarQRCode(String data, int width, int height){
        return QRCode.from(data).withSize(width, height).file();
    }
    
}
