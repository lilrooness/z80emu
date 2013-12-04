package z80.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

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
    private JPanel globalContainer;
    private JMenuBar menuBar;
    private JMenu memory;
    private JMenuItem showMemory;
    private MemoryView memoryView;

    public Window(int width, int height) {
        globalContainer = new JPanel();
        globalContainer.setLayout(new BorderLayout());
        setSize(width, height);
        infoPanel = new InfoPanel();
        ide = new IDE(width, height);

        menuBar = new JMenuBar();
        memory = new JMenu("Memory");
        showMemory = new JMenuItem("Show");
        memory.add(showMemory);

        menuBar.add(memory);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        globalContainer.add(infoPanel, BorderLayout.NORTH);
        globalContainer.add(ide, BorderLayout.SOUTH);

        globalContainer.add(new ControlPanel(this), BorderLayout.EAST);
        add(globalContainer, BorderLayout.CENTER);
        setJMenuBar(menuBar);

        showMemory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                memoryView = new MemoryView();
            }
        });

        pack();
        setVisible(true);
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoPanel.updateGui();
                if(memoryView != null) {
                    memoryView.updateTable();
                }
            }
        });
        timer.start();
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
