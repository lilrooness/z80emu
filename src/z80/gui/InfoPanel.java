package z80.gui;

import z80.core.RegisterState;
import z80.util.RadixOperations;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 03/11/2013
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class InfoPanel extends JPanel implements Runnable{

    private JPanel af, bc, de, hl;
    private RegisterState registerState;
    private JTextField a, f, d, e, b, c , h, l;

    public InfoPanel() {
        registerState = RegisterState.getInstance();
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

        a = new JTextField("0xff");
        f = new JTextField("0xff");

        d = new JTextField("0xff");
        e = new JTextField("0xff");

        b = new JTextField("0xff");
        c = new JTextField("0xff");

        h = new JTextField("0xff");
        l = new JTextField("0xff");

        af.add(new JLabel("A", JLabel.CENTER));
        af.add(new JLabel("F", JLabel.CENTER));
        af.add(a);
        af.add(f);

        de.add(new JLabel("D", JLabel.CENTER));
        de.add(new JLabel("E", JLabel.CENTER));
        de.add(d);
        de.add(e);

        bc.add(new JLabel("B", JLabel.CENTER));
        bc.add(new JLabel("C", JLabel.CENTER));
        bc.add(b);
        bc.add(c);

        hl.add(new JLabel("H", JLabel.CENTER));
        hl.add(new JLabel("L", JLabel.CENTER));
        hl.add(h);
        hl.add(l);

        add(af);
        add(de);
        add(bc);
        add(hl);
    }

    @Override
    public void run() {

        while(true) {
            registerState = RegisterState.getInstance();
            a.setText(Integer.toHexString(registerState.getA()[0]));
//        f.setText(Integer.toHexString(registerState.getA()[1]));
            b.setText(Integer.toHexString(registerState.getBc()[0]));
            c.setText(Integer.toHexString(registerState.getBc()[1]));
            d.setText(Integer.toHexString(registerState.getDe()[0]));
            e.setText(Integer.toHexString(registerState.getDe()[1]));
            h.setText(Integer.toHexString(registerState.getHl()[0]));
            l.setText(Integer.toHexString(registerState.getHl()[1]));
        }
    }
}
