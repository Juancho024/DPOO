package Visual;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

public class JPanelRedondeado extends JPanel {

    private int radio;

    public JPanelRedondeado(int radio) {
        this.radio = radio;
        setOpaque(false);  // Muy importante para que no pinte el fondo rectangular normal
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Mejorar calidad de dibujo
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color de fondo del panel
        g2.setColor(getBackground());

        // Dibujar rectángulo con esquinas redondeadas que ocupa todo el panel
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radio, radio));

        g2.dispose();
    }
}
