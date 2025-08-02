package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class RoundedImagePanel extends JPanel {

    private BufferedImage image;
    private int cornerRadius;

    public RoundedImagePanel(String imagePath, int cornerRadius) {
        this.cornerRadius = cornerRadius;
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                System.err.println("Imagen no encontrada: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error cargando la imagen: " + e.getMessage());
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setClip(clip);

            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        } else {
            super.paintComponent(g);
        }
    }
}
