package z80.gui;

import z80.core.Control;
import z80.core.RegisterState;
import z80.memory.Memory;
import z80.util.RadixOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.BitSet;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 04/11/2013
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class ControlPanel extends JPanel{

    private JButton step, pause, run, stop;
    private JPanel top, tmiddle, bottom;
    private Window parent;

    public ControlPanel(Window parent) {
        this.parent = parent;
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
        run.addActionListener(new EventListener());
        step.addActionListener(new EventListener());
        stop.addActionListener(new EventListener());

        add(top);
        add(tmiddle);
        add(bottom);
    }


    private class EventListener implements ActionListener {
        Control control;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(control == null) {
                control = new Control();
            }
            if(e.getSource() == run) {
                loadCode(parent.getIde().getInput().getText());
                control.runProgram((short)0);
            } else if(e.getSource() == step) {
                if(!control.isSetup()) {
                    loadCode(parent.getIde().getInput().getText());
                }
                control.stepProgram((short)0);
            } else if(e.getSource() == stop) {
                RegisterState.getInstance().dump();
                Memory.zero();
                control = new Control();
            }
        }

        /**
         * parses text from the IDE into opcodes and loads them into
         * a code segment in memory starting at 0
         * @param code
         */
        public void loadCode(String code) {
            code = code.replaceAll("\\r?\\n", "");
            BitSet processedCode = new BitSet(code.length());
            for(int i=0; i<code.length(); i++) {
                if(code.charAt(i) == '1') {
                    processedCode.set(code.length() - i, true); // read in the bits reversed, because bitset uses little endian
                } else if(code.charAt(i) == '0') {
                    processedCode.set(code.length() - i, false);
                } else {
                    throw new IllegalArgumentException("An illegal character "+code.charAt(i)+ "was found at position: "+i);
                }
            }
            System.out.println(processedCode.toString());
            byte[] program = RadixOperations.toByteArray(code);
            short codeSegmentOffset = 0;
            for(int i=0; i<program.length; i++) {
                Memory.memory[codeSegmentOffset+i] = program[i];
            }
        }
    }
}
