package z80.gui;

import z80.modules.Module;
import z80.modules.ModuleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JMenu help;
    private JMenuItem linkMenu;
    private JMenu modules;

    private ModuleController moduleController;

    public Window(int width, int height) {
        globalContainer = new JPanel();
        globalContainer.setLayout(new BorderLayout());
        setSize(width, height);
        infoPanel = new InfoPanel();
        ide = new IDE(width, height);

        menuBar = new JMenuBar();
        memory = new JMenu("Memory");
        help = new JMenu("Help");
        showMemory = new JMenuItem("Show");
        memory.add(showMemory);

        menuBar.add(memory);
        menuBar.add(help);
        modules = new JMenu("Modules");

        linkMenu = new JMenuItem("Z80 Docs");
        help.add(linkMenu);

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

        linkMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "<html><body><a href=\"http://www.zilog.com/appnotes_download.php?FromPage=DirectLink&dn=UM0080&ft=User%20Manual&f=YUhSMGNEb3ZMM2QzZHk1NmFXeHZaeTVqYjIwdlpHOWpjeTk2T0RBdmRXMHdNRGd3TG5Ca1pnPT0=\">Z80 Manual</a></body></html>", "Docs", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addModules();

        pack();
        setVisible(true);
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoPanel.updateGui();
//                if(memoryView != null) {
//                    memoryView.updateTable();
//                }
            }
        });
        timer.start();
    }

    private void addModules() {
        moduleController = new ModuleController();

        for(int i=0; i<moduleController.getKeyArray().length; i++) {
            addModule(moduleController.getModule((String)moduleController.getKeyArray()[i]));
        }

        menuBar.add(modules);
    }

    private void addModule(Module module) {
        JMenuItem menuItem = new JMenuItem(module.getName());
        menuItem.putClientProperty("name", module.getName());

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                moduleController.getModule((String)((JMenuItem)actionEvent.getSource()).getClientProperty("name"))
                        .onActivate();
            }
        });

        modules.add(menuItem);
    }

    public IDE getIde() {
        return ide;
    }

    public static void main(String[] args) {
        new Window(640, 480);
    }
}
