package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;

public class LDrIXd extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		BitSet opcode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()});
		byte d = registerState.fetchWord8();
		setRegisterValue(registerState, RegisterCodes.getByCode(opcode.get(2, 4)), new byte[] {Memory.memory[d + registerState.getIX()]});
	}
}
