package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;

public class LDIXdr extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		BitSet opcode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()});
		byte value = getRegisterValueByCode(AbstractRegisterInstruction.getSecondRegisterBits(opcode), registerState)[0];
		Memory.memory[registerState.getIX()+registerState.fetchWord8()] = value;
	}

}
