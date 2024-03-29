package z80.gui;

import z80.core.RegisterState;

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

    private JPanel af, bc, de, hl;
    private JPanel generalRegs, specialRegs;
    private RegisterState registerState;
    private JTextField a, f, d, e, b, c , h, l;
    private JPanel psr, pc, currentWord;
    private JTextField psrState, pcState, currentWordState;
    private JLabel psrLabel, pcLabel, currentWordLabel;

    public InfoPanel() {
        registerState = RegisterState.getInstance();
        GridLayout globalLayout = new GridLayout(2,2);
        globalLayout.setHgap(5);
        globalLayout.setVgap(5);
        setLayout(globalLayout);
        specialRegs = new JPanel(new GridLayout(2, 2));
        generalRegs = new JPanel(globalLayout);
        this.setLayout(new GridLayout(1, 2));
        generalRegs.setLayout(globalLayout);
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

        a.setEditable(false);
        f.setEditable(false);
        d.setEditable(false);
        e.setEditable(false);
        b.setEditable(false);
        c.setEditable(false);
        h.setEditable(false);
        l.setEditable(false);

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

        GridLayout specialRegLayout = new GridLayout(2, 1);
        specialRegLayout.setVgap(-5);
        pc = new JPanel(specialRegLayout);
        psr = new JPanel(specialRegLayout);
        currentWord = new JPanel(specialRegLayout);

        pcState = new JTextField();
        psrState = new JTextField();
        currentWordState = new JTextField();

        pcState.setEditable(false);
        psrState.setEditable(false);
        currentWordState.setEditable(false);

        pcLabel = new JLabel("PC", JLabel.CENTER);
        psrLabel = new JLabel("PSR", JLabel.CENTER);
        currentWordLabel = new JLabel("INS", JLabel.CENTER);

        pc.add(pcLabel);
        pc.add(pcState);

        psr.add(psrLabel);
        psr.add(psrState);

        currentWord.add(currentWordLabel);
        currentWord.add(currentWordState);

        specialRegs.add(pc);
        specialRegs.add(psr);
        specialRegs.add(currentWord);

        generalRegs.add(af);
        generalRegs.add(de);
        generalRegs.add(bc);
        generalRegs.add(hl);
        add(generalRegs);
        add(specialRegs);
    }

    public void updateGui() {
        registerState = RegisterState.getInstance();
        a.setText(Integer.toHexString(registerState.getA()[0] & 0xff));
        b.setText(Integer.toHexString(registerState.getBc()[0] & 0xff));
        c.setText(Integer.toHexString(registerState.getBc()[1] & 0xff));
        d.setText(Integer.toHexString(registerState.getDe()[0] & 0xff));
        e.setText(Integer.toHexString(registerState.getDe()[1] & 0xff));
        h.setText(Integer.toHexString(registerState.getHl()[0] & 0xff));
        l.setText(Integer.toHexString(registerState.getHl()[1] & 0xff));
        pcState.setText(Integer.toHexString(registerState.getPc() & 0xff));
        currentWordState.setText(Integer.toHexString(registerState.getCurrentWord8() & 0xff));
    }
}
