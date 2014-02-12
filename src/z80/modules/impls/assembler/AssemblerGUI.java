package z80.modules.impls.assembler;

import z80.gui.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joe on 08/02/2014.
 */
public class AssemblerGUI extends JFrame {

    private JTextArea jTextArea;
    private JButton compile;
    private Assembler assembler;

    public AssemblerGUI() {
        setTitle("Z80 Assembler Module");

        setSize(300, 300);
        setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        compile = new JButton("Compile and Load");

        add(jTextArea, BorderLayout.CENTER);
        add(compile, BorderLayout.SOUTH);

        assembler = new Assembler();

        compile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ControlPanel.loadCode(assembler.assemble(jTextArea.getText()));
            }
        });

//        pack();
    }

    public void activate() {
        setVisible(true);
    }

    public void deactivate() {
        setVisible(false);
    }
}
