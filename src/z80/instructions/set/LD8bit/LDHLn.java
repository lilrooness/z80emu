package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDHLn extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		byte value = registerState.fetchWord8();
		Memory.memory[RadixOperations.toShort(BitSet.valueOf(registerState.getHl()))] = value;
	}
}
