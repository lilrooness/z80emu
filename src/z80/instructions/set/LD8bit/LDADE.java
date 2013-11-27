package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

@Deprecated
public class LDADE extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
        String DE = Integer.toBinaryString(registerState.getDe()[0]) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1]));
		registerState.setA(new byte[] {Memory.memory[RadixOperations.toShort(DE)]});
	}

}
