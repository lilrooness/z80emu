package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;


/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 20/10/2013
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class LDBCA extends AbstractRegisterInstruction {
    @Override
    public void execute(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String bc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[0])) +
                RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[1]));
        Memory.memory[RadixOperations.toShort(bc)] = value;
    }
}
