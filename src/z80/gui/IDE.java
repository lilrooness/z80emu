package z80.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 04/11/2013
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class IDE extends JPanel {

    private JTextArea input;

    public IDE(int parentWidth, int parentHeight) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(parentWidth, parentHeight / 2));
        input = new JTextArea();
        add(new JScrollPane(input));
    }
}
