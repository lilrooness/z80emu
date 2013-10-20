package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDHLr extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		short index = RadixOperations.toShort(BitSet.valueOf(registerState.getHl()));
		byte value = getRegisterValueByCode(getSecondRegisterBits(BitSet.valueOf(new byte[]{registerState.getCurrentWord8()})), registerState)[0];
		Memory.memory[index] = value;
	}
}
