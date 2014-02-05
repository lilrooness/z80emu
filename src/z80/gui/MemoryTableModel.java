package z80.gui;

import z80.memory.Memory;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Joe on 05/02/2014.
 */
public class MemoryTableModel extends AbstractTableModel implements PropertyChangeListener {

    private String[] columnNames;
    private Object[][] data;

    public static boolean hexView;

    public MemoryTableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;

        Memory.addMemoryListener(this);
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i2) {
        if(hexView) {
            return Integer.toHexString((Integer) data[i][i2]);
        } else {
            return data[i][i2];
        }
    }

    @Override
    public void setValueAt(Object object, int i, int i2) {
        data[i][i2] = object;
    }

    public boolean isHexView() {
        return hexView;
    }

    public void setHexView(boolean hexView) {
        this.hexView = hexView;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        for(int i=0; i<Memory.getMemoryLength(); i++) {
            data[i][0] = i;
            data[i][1] = (int)Memory.getMemoryAt(i) & 0xff;
        }

        fireTableDataChanged();
    }
}
