package z80.gui;

import z80.memory.Memory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 04/12/2013
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class MemoryView extends JFrame {

    JTable memoryTable;
    Integer[][] memory;
    String[] columnNames;
    JCheckBox viewHex;
    JPanel checkBoxPanel;

    public MemoryView() {
        setSize(100, 480);
        setLayout(new BorderLayout());
        memory = new Integer[Memory.getMemoryLength()][2];
        viewHex = new JCheckBox();
        checkBoxPanel = new JPanel();
        columnNames = new String[] {"Address", "Value"};
        updateTable();
        memoryTable.setModel(new MemoryTableModel(columnNames, memory));
        add(new JScrollPane(memoryTable), BorderLayout.SOUTH);
        checkBoxPanel.setLayout(new FlowLayout());
        checkBoxPanel.add(viewHex, BorderLayout.NORTH);
        checkBoxPanel.add(new JLabel("View Hex"));
        add(checkBoxPanel);

        viewHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MemoryTableModel.hexView = viewHex.isSelected();
            }
        });
        setVisible(true);
    }

    public void updateTable() {
        for(int i=0; i<Memory.getMemoryLength(); i++) {
            memory[i][0] = i;
            memory[i][1] = (int)Memory.getMemoryAt(i) & 0xff;
        }
        memoryTable = new JTable(memory, columnNames);
    }
}
