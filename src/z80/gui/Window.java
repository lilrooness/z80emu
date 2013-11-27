package z80.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 03/11/2013
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Window extends JFrame {

    private IDE ide;
    private InfoPanel infoPanel;

    public Window(int width, int height) {
        setLayout(new BorderLayout());
        setSize(width, height);

        infoPanel = new InfoPanel();
        ide = new IDE(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(infoPanel, BorderLayout.NORTH);
        add(ide, BorderLayout.SOUTH);

        add(new ControlPanel(this), BorderLayout.EAST);
        pack();

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoPanel.updateGui();
            }
        });
        timer.start();

        setVisible(true);
    }

    public IDE getIde() {
        return ide;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public static void main(String[] args) {
        new Window(640, 480);
    }
}
