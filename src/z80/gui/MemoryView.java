package z80.gui;

import z80.memory.Memory;

import javax.swing.*;

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

    public MemoryView() {
        setSize(100, 480);
        memory = new Integer[Memory.getMemoryLength()][2];
        viewHex = new JCheckBox();
        columnNames = new String[] {"Address", "Value"};
        updateTable();
        memoryTable.setModel(new MemoryTableModel(columnNames, memory));
        add(new JScrollPane(memoryTable));
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
