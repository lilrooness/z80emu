package z80.memory;

import z80.core.RegisterState;
import z80.util.RadixOperations;

public class Memory {

    static public byte[] memory = new byte[65000];
	
	private Memory(){}

    /**
     * zeros all memory
     */
    public static void zero() {
        for(int i=0; i<memory.length; i++) {
            memory[i] = 0;
        }
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
        memory[RadixOperations.toShort(bin)] = value;
    }

    public static void setIndexDE(byte value) {
        RegisterState registerState = RegisterState.getInstance();
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[0] & 0xff));
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1] & 0xff));
        String bin = lsb + msb;
        memory[RadixOperations.toShort(bin)] = value;
    }

    public static byte index16Bit(String bin) {
        return memory[RadixOperations.toShort(bin)];
    }

    public static void push(byte value) {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.memory[registerState.getSp()] = value;
    }

    public static byte pull() {
        RegisterState registerState = RegisterState.getInstance();
        byte value = Memory.memory[registerState.getSp()];
        registerState.setSp((short) (registerState.getSp() + 1));
        return value;
    }
}
