package z80.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 04/11/2013
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class Controls extends JPanel{
    private JButton step, pause, run, stop;
    private JPanel top, tmiddle, bottom;

    public Controls() {
        new GridLayout(1, 3);
        top = new JPanel();
        tmiddle = new JPanel();
        bottom = new JPanel();

        step = new JButton("step");
        pause = new JButton("pause");
        run = new JButton("run");
        stop = new JButton("stop");

        top.add(step);
        top.add(pause);
        tmiddle.add(run);
        bottom.add(stop);

        add(top);
        add(tmiddle);
        add(bottom);
    }
}
