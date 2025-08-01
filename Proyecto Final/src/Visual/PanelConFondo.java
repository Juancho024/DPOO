package Visual;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PanelConFondo extends JPanel {
	private static final long serialVersionUID = 3058500999380909674L;
	private Image imagenFondo;

    public PanelConFondo(String rutaImagen) {
        this.imagenFondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen para que ocupe todo el tamaño del panel
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}
