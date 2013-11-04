package z80.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 03/11/2013
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class InfoPanel extends JPanel {

    GridBagConstraints c;
    JPanel af, bc, de, hl;

    public InfoPanel(int width, int height) {
        GridLayout globalLayout = new GridLayout(2,2);
        globalLayout.setHgap(5);
        globalLayout.setVgap(5);
        setLayout(globalLayout);
        GridLayout gl = new GridLayout(2,2);
        gl.setVgap(-5);
        gl.setHgap(-5);

        af = new JPanel(gl);
        de = new JPanel(gl);
        bc = new JPanel(gl);
        hl = new JPanel(gl);

        af.add(new JLabel("A", JLabel.CENTER));
        af.add(new JLabel("F", JLabel.CENTER));
        af.add(new JTextField("0xff"));
        af.add(new JTextField("0xff"));

        de.add(new JLabel("D", JLabel.CENTER));
        de.add(new JLabel("E", JLabel.CENTER));
        de.add(new JTextField("0xff"));
        de.add(new JTextField("0xff"));

        bc.add(new JLabel("B", JLabel.CENTER));
        bc.add(new JLabel("C", JLabel.CENTER));
        bc.add(new JTextField("0xff"));
        bc.add(new JTextField("0xff"));

        hl.add(new JLabel("H", JLabel.CENTER));
        hl.add(new JLabel("L", JLabel.CENTER));
        hl.add(new JTextField("0xff"));
        hl.add(new JTextField("0xff"));

        add(af);
        add(de);
        add(bc);
        add(hl);
    }
}
