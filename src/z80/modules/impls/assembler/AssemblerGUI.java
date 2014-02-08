package z80.modules.impls.assembler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joe on 08/02/2014.
 */
public class AssemblerGUI extends JFrame {

    private JTextArea jTextArea;
    private JButton compile;

    public AssemblerGUI() {
        setTitle("Z80 Assembler Module");

        setSize(300, 300);
        setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        compile = new JButton("Compile and Load");

        add(jTextArea, BorderLayout.CENTER);
        add(compile, BorderLayout.SOUTH);

//        pack();
    }

    public void activate() {
        setVisible(true);
    }

    public void deactivate() {
        setVisible(false);
    }
}