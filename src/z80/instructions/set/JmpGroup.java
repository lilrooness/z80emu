package z80.instructions.set;

import sun.jvm.hotspot.asm.Register;
import z80.core.RegisterState;
import z80.core.StatusFlagTests;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;
import z80.util.RadixOperations;

/**
 * Created by Joe on 26/12/2013.
 */
public class JmpGroup {

    public static int JPnn() {
        RegisterState registerState = RegisterState.getInstance();
        String LSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String MSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String operand = MSB + LSB;
        registerState.setPc(RadixOperations.toShort(operand));
        return 3;
    }

    public static int JPccnn() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        StatusFlagTests test = AbstractRegisterInstruction.getFlagTest(opcode.substring(2, 5));
        if(RegisterState.psr.get(test.getFlag().getPosition())) {
            String LSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
            String MSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
            String operand = MSB + LSB;
            registerState.setPc(RadixOperations.toShort(operand));
        } else {
            registerState.fetchWord8();
            registerState.fetchWord8();
        }
        return 3;
    }

    public static int JRe() {
        RegisterState registerState = RegisterState.getInstance();
        short e = registerState.fetchWord8();
        registerState.setPc((short)(registerState.getPc() + e));
        return 3;
    }

    public static int JRCe() {
        RegisterState registerState = RegisterState.getInstance();
        if(RegisterState.psr.get(StatusFlags.C.getPosition())) {
            short e = registerState.fetchWord8();
            registerState.setPc((short)(registerState.getPc() + e));
            return 2;
        }
        registerState.fetchWord8();
        return 3;
    }

    public static int JRNCe() {
        RegisterState registerState = RegisterState.getInstance();
        if(RegisterState.psr.get(StatusFlagTests.NC.getFlag().getPosition())) {
            short e = registerState.fetchWord8();
            registerState.setPc((short)(registerState.getPc() + e));
            return 2;
        }
        registerState.fetchWord8();
        return 3;
    }

    public static int JRZe() {
        RegisterState registerState = RegisterState.getInstance();
        if(RegisterState.psr.get(StatusFlagTests.Z.getFlag().getPosition())) {
            short e = registerState.fetchWord8();
            registerState.setPc((short)(registerState.getPc() + e));
            return 2;
        }
        registerState.fetchWord8();
        return 3;
    }

    public static int JRNZe() {
        RegisterState registerState = RegisterState.getInstance();
        if(RegisterState.psr.get(StatusFlagTests.NZ.getFlag().getPosition())) {
            short e = registerState.fetchWord8();
            registerState.setPc((short)(registerState.getPc() + e));
            return 2;
        }
        registerState.fetchWord8();
        return 3;
    }
}
