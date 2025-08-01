package Visual;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {

    private Color backgroundColor;

    public RoundedLabel(String text, Color backgroundColor, Font font, Color ColorLetra) {
        super(text, SwingConstants.CENTER);
        this.backgroundColor = backgroundColor;
        setForeground(ColorLetra); // Puedes cambiar esto si el fondo es claro
        setFont(font);
        setOpaque(false); // Importante para transparencia
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Suavizado para bordes redondeados
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Usar color de fondo personalizado
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    // Opcional: permitir cambiar el color después
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
}
