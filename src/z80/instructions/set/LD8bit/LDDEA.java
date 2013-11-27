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
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class LDDEA extends AbstractRegisterInstruction {
    @Override
    public void execute(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String D = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[0] & 0xff));
        String E = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1] & 0xff));
        Memory.memory[RadixOperations.toShort(D+E)] = value;
    }
}
