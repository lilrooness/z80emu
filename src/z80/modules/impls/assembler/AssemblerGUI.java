package z80.modules.impls.assembler;

import z80.gui.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Joe on 08/02/2014.
 */
public class AssemblerGUI extends JFrame {

    private JTextArea jTextArea;
    private JButton compile;
    private Assembler assembler;
    private JMenuBar jMenuBar;
    private JMenu file;
    private JMenuItem load;
    private JScrollPane scrollPane;

    public AssemblerGUI() {
        setTitle("Z80 Assembler Module");

        setSize(300, 300);
        setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        compile = new JButton("Compile and Load");
        scrollPane = new JScrollPane(jTextArea);

        jMenuBar = new JMenuBar();
        file = new JMenu("File");
        jMenuBar.add(file);
        load = new JMenuItem("Load");
        file.add(load);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showOpenDialog(null);
                if(status == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedReader in = new BufferedReader(new FileReader(new File(fileChooser.getSelectedFile().getAbsolutePath())));
                        String line;

                        while((line = in.readLine()) != null) {
                            jTextArea.append(line+"\n");
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(compile, BorderLayout.SOUTH);
        add(jMenuBar, BorderLayout.NORTH);



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
