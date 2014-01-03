package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 11/12/2013
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class Arith16Bit {
    public static int addHLss() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        short ss = RadixOperations.byteArrayToShort(AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4))));
        short hl = RadixOperations.byteArrayToShort(registerState.getHl());
        registerState.setHl(RadixOperations.shortToByteArray((short) (hl + ss)));
        return 3;
    }

    public static int addIXpp() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        short pp = RadixOperations.byteArrayToShort(AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4))));
        registerState.setIX((short) (registerState.getIX() + pp));
        return 4;
    }

    public static int addIYpp() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        short pp = RadixOperations.byteArrayToShort(AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4))));
        registerState.setIY((short) (registerState.getIY() + pp));
        return 4;
    }

    public static int incss() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        short ss = RadixOperations.byteArrayToShort(AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4))));
        AbstractRegisterInstruction.set16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), RadixOperations.shortToByteArray((short) (ss + 1)));
        return 1;
    }

    public static int incIX() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIX((short) (registerState.getIX() + 1));
        return 2;
    }

    public static int incIY() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIY((short) (registerState.getIY() + 1));
        return 2;
    }

    public static int decss() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        short ss = RadixOperations.byteArrayToShort(AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4))));
        AbstractRegisterInstruction.set16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), RadixOperations.shortToByteArray((short) (ss - 1)));
        return 1;
    }

    public static int decIX() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIX((short) (registerState.getIX() - 1));
        return 2;
    }

    public static int decIY() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIY((short) (registerState.getIY() - 1));
        return 2;
    }

}
