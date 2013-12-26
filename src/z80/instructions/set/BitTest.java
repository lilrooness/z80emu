package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 23/12/2013
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class BitTest {

    public static int bitbr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        String value = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(5))) & 0xff));
        if(value.charAt(bit) == '1') {
            registerState.psr.set(StatusFlags.Z.getPosition());
        } else {
            registerState.psr.set(StatusFlags.Z.getPosition(), false);
        }
        return 2;
    }

    public static int bitbHL() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        String bin = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL()));
        if(bin.charAt(bit) == '1') {
            registerState.psr.set(StatusFlags.Z.getPosition());
        } else {
            registerState.psr.set(StatusFlags.Z.getPosition(), false);
        }
        return 3;
    }

    public static int bitbIXd() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        int d = registerState.fetchWord8();
        int ixd = d + registerState.getIX();
        String bin = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[ixd] & 0xff));
        if(bin.charAt(bit) == '1') {
            registerState.psr.set(StatusFlags.Z.getPosition());
        } else {
            registerState.psr.set(StatusFlags.Z.getPosition(), false);
        }
        return 3;
    }

    public static int bitbIYd() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        int d = registerState.fetchWord8();
        int iyd = d + registerState.getIY();
        String bin = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[iyd] & 0xff));
        if(bin.charAt(bit) == '1') {
            registerState.psr.set(StatusFlags.Z.getPosition());
        } else {
            registerState.psr.set(StatusFlags.Z.getPosition(), false);
        }
        return 3;
    }

    public static int setbr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        String registerValue = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(5))) & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(registerValue);
        stringBuilder.setCharAt(bit, '1');
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(5)), RadixOperations.toByteArray(stringBuilder.toString()));
        return 2;
    }

    public static int setbHL() {
        RegisterState registerState = RegisterState.getInstance();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '1');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.setIndexHL(newValue);
        return 2;
    }

    public static int setbIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[d + registerState.getIX()] & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '1');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.memory[d + registerState.getIX()] = newValue;
        return 1;
    }

    public static int setbIY() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[d + registerState.getIY()] & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '1');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.memory[d + registerState.getIY()] = newValue;
        return 1;
    }

    public static int resbr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        String registerValue = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(5))) & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(registerValue);
        stringBuilder.setCharAt(bit, '0');
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(5)), RadixOperations.toByteArray(stringBuilder.toString()));
        return 2;
    }

    public static int resbHL() {
        RegisterState registerState = RegisterState.getInstance();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '0');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.setIndexHL(newValue);
        return 2;
    }

    public static int resbIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[d + registerState.getIX()] & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '0');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.memory[d + registerState.getIX()] = newValue;
        return 1;
    }

    public static int resbIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        String data = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[d + registerState.getIY()] & 0xff));
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        int bit = RadixOperations.toShort(opcode.substring(2, 5));
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.setCharAt(bit, '0');
        byte newValue = RadixOperations.toByteArray(stringBuilder.toString())[0];
        Memory.memory[d + registerState.getIY()] = newValue;
        return 1;
    }
}
