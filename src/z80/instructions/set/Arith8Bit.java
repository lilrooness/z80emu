package z80.instructions.set;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 27/11/2013
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class Arith8Bit {

    public static void AddAn() {
        RegisterState registerState = RegisterState.getInstance();
        byte n = registerState.fetchWord8();
        int result = registerState.getA()[0] + n;
        registerState.setA(new byte[] {(byte)result, registerState.getA()[1]});
        RegisterState.psr.set(StatusFlags.S.getPosition(), (result < 0));
        RegisterState.psr.set(StatusFlags.Z.getPosition(), (result == 0));
        RegisterState.psr.set(StatusFlags.UN.getPosition(), false);
        RegisterState.psr.set(StatusFlags.PV.getPosition(), (result > 127 || result < -128));
    }

    public static void AddAr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        registerState.setA(new byte[]{(byte) (registerState.getA()[0]+r), registerState.getA()[1]});
    }

    public static void addAHL() {
        RegisterState registerState = RegisterState.getInstance();
        String nHigh = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String nLow = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String index = nHigh + nLow;
        byte n = Memory.memory[RadixOperations.toShort(index)];
        registerState.setA(new byte[] {(byte) (registerState.getA()[0] +n),registerState.getA()[1]});
    }

    public static int addIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] + Memory.memory[a]), registerState.getA()[1]});
        return 5;
    }

    public static int addIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] + Memory.memory[a]), registerState.getA()[1]});
        return 5;
    }

    public static int subr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        registerState.setA(new byte[]{(byte) (registerState.getA()[0]-r), registerState.getA()[1]});
        return 1;
    }

    public static int subn() {
        RegisterState registerState = RegisterState.getInstance();
        byte n = registerState.fetchWord8();
        int result = registerState.getA()[0] - n;
        registerState.setA(new byte[] {(byte)result, registerState.getA()[1]});
        return 2;
    }

    public static int subHL() {
        RegisterState registerState = RegisterState.getInstance();
        String nHigh = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String nLow = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String index = nHigh + nLow;
        byte n = Memory.memory[RadixOperations.toShort(index)];
        registerState.setA(new byte[] {(byte) (registerState.getA()[0] - n),registerState.getA()[1]});
        return 2;
    }

    public static int subIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] - Memory.memory[a]), registerState.getA()[1]});
        return 5;
    }

    public static int subIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] - Memory.memory[a]), registerState.getA()[1]});
        return 5;
    }
}
