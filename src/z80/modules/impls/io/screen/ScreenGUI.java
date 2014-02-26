package z80.modules.impls.io.screen;

import z80.memory.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joe on 18/02/2014.
 */
public class ScreenGUI extends JFrame {

    private JPanel display;
    int[] rpixels, gpixels, bpixels;
    int hres, vres;
    int memoffset;

    public ScreenGUI(int hres, int vres, int memoffset) {
        this.hres = hres;
        this.vres = vres;
        this.memoffset = memoffset;

        rpixels = new int[hres * vres];
        gpixels = new int[hres * vres];
        bpixels = new int[hres * vres];

        setTitle("Z80 Virtual Screen");
        setSize(300, 300);
        setLayout(new BorderLayout());
        display = new JPanel();
        display.setPreferredSize(new Dimension(300, 300));

        add(display, BorderLayout.CENTER);

        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateDisplay();
            }
        });

        timer.start();
        System.out.println("Width: "+this.getWidth()+"\nHeight: "+this.getHeight());
    }

    private void updateDisplay() {
        int red = Memory.getMemoryAt(memoffset) & 0xff;
        for(int i=0; i<hres * vres; i++) {
            rpixels[i] = Memory.getMemoryAt(memoffset + i) & 0xff;
            gpixels[i] = Memory.getMemoryAt(memoffset + i + (hres * vres)) & 0xff;
            bpixels[i] = Memory.getMemoryAt(memoffset + i + (hres * vres) * 2) & 0xff;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        for(int y = 1; y <= vres; y ++) {
            for(int x = 1; x <= hres; x ++) {
                g.setColor(new Color(
                        rpixels[x * y - 1],
                        gpixels[x * y - 1],
                        bpixels[x * y - 1])
                );
                g.fillRect(
                        (x-1) * (this.getWidth() / hres),
                        (y-1) * (this.getHeight() / vres) + 22,
                        x * (this.getWidth() / hres),
                        y * (this.getHeight() / vres) + 22
                );
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
}
