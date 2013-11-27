package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

@Deprecated
public class LDABC extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
        String BC = Integer.toBinaryString(registerState.getBc()[0]) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[1]));
		registerState.setA(new byte[]{Memory.memory[RadixOperations.toShort(BC)]});
	}
}
