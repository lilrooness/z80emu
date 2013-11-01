package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDAnn extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		byte n2 = registerState.fetchWord8();
		byte n1 = registerState.fetchWord8();
        String nn = Integer.toBinaryString(n2) + RadixOperations.prependZeros(Integer.toBinaryString(n1));
		registerState.setA(new byte[]{Memory.memory[RadixOperations.toShort(nn)]});
	}
}
