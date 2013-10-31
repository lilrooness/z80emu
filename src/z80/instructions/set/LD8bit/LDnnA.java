package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

import java.util.BitSet;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 20/10/2013
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class LDnnA extends AbstractRegisterInstruction {

    @Override
    public void execute(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String n2 = Integer.toBinaryString(registerState.fetchWord8() & 0xFF);
        String n1 = Integer.toBinaryString(registerState.fetchWord8() & 0xFF);

        Memory.memory[RadixOperations.toShort(RadixOperations.prependZeros(n2) + RadixOperations.prependZeros(n1))] = value;
    }
}
