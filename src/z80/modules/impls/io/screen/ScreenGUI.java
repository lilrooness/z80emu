package z80.modules.impls.io.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joe on 18/02/2014.
 */
public class ScreenGUI extends JFrame {

    private JPanel display;
    byte[] pixels;
    int hres, vres;

    public ScreenGUI(int hres, int vres) {
        setTitle("Z80 Virtual Screen");
        setSize(300, 300);
        setLayout(new BorderLayout());

        display = new JPanel();

        add(display, BorderLayout.CENTER);

        Timer timer = new Timer(50,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateDisplay();
            }
        });
    }

    private void updateDisplay() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        for(int y = 0; y < vres; vres ++) {
            for(int x = 0; x < hres; hres ++) {
                g.setColor(new Color(pixels[x * y], pixels[x * y], pixels[x * y]));
                g.fillRect(x, y, x + (display.getWidth() / hres), y + (display.getHeight() / vres));
            }
        }
    }

    public void activate() {
        setVisible(true);
    }

    public int getVres() {
        return vres;
    }

    public void setVres(int vres) {
        this.vres = vres;
    }

    public int getHres() {
        return hres;
    }

    public void setHres(int hres) {
        this.hres = hres;
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }
}
