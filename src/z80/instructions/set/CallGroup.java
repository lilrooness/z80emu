package z80.instructions.set;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created by Joe on 01/01/2014.
 */
public class CallGroup {

    public static int callnn() {
        RegisterState registerState = RegisterState.getInstance();
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(msb + lsb);
        String pc = Integer.toBinaryString(registerState.getPc() & 0xff);
        byte pclsb = (byte) RadixOperations.toShort(pc.substring(0, 9));
        byte pcmsb = (byte) RadixOperations.toShort(pc.substring(9));
        Memory.push(pclsb);
        Memory.push(pcmsb);
        registerState.setPc(nn);
        return 5;
    }

    public static int callccnn() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String testCode = opcode.substring(2, 5);
        if(RegisterState.psr.get(AbstractRegisterInstruction.getFlagTest(testCode).getFlag().getPosition())) {
            return CallGroup.callnn();
        }
        return 3;
    }

    public static int ret() {
        RegisterState registerState = RegisterState.getInstance();
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.pull() & 0xff));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.pull() & 0xff));
        registerState.setPc(RadixOperations.toShort(msb + lsb));
        return 3;
    }

    public static int retcc() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String testCode = opcode.substring(2, 5);
        if(RegisterState.psr.get(AbstractRegisterInstruction.getFlagTest(testCode).getFlag().getPosition())) {
            return ret();
        }
        return 1;
    }
}
