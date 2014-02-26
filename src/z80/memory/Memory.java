package z80.memory;

import z80.core.RegisterState;
import z80.util.RadixOperations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Memory {

    private static PropertyChangeSupport changeSupport = new PropertyChangeSupport(new Memory());
    private static byte[] memory = new byte[65000];

    private Memory(){}

    /**
     * zeros all memory
     */
    public static void zero() {
        for(int i=0; i<memory.length; i++) {
            memory[i] = 0;
        }

        changeSupport.firePropertyChange("memory", null, memory);
    }

    public static byte indexHL() {
        RegisterState registerState = RegisterState.getInstance();
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String bin = lsb + msb;
        return index16Bit(bin);
    }

    public static byte indexDe() {
        RegisterState registerState = RegisterState.getInstance();
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[0] & 0xff));
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1] & 0xff));
        String bin = lsb + msb;
        return index16Bit(bin);
    }

    public static void setIndexHL(byte value) {
        RegisterState registerState = RegisterState.getInstance();
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String bin = lsb + msb;
        setMemoryAt(RadixOperations.toShort(bin), value);
    }

    public static void setIndexDE(byte value) {
        RegisterState registerState = RegisterState.getInstance();
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[0] & 0xff));
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1] & 0xff));
        String bin = lsb + msb;
        setMemoryAt(RadixOperations.toShort(bin), value);
    }

    public static byte index16Bit(String bin) {
        return getMemoryAt(RadixOperations.toShort(bin));
    }

    public static void push(byte value) {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setSp((short) (registerState.getSp() - 1));
        setMemoryAt(registerState.getSp(), value);
    }

    public static byte pop() {
        RegisterState registerState = RegisterState.getInstance();
        byte value = getMemoryAt(registerState.getSp());
        registerState.setSp((short) (registerState.getSp() + 1));
        return value;
    }

    public static void addMemoryListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public static synchronized void setMemoryAt(short index, byte value) {
        memory[index] = value;
        changeSupport.firePropertyChange("memory", null, memory);
    }

    public static synchronized byte getMemoryAt(int index) {
        return memory[index];
    }

    public static int getMemoryLength() {
        return memory.length;
    }
}
