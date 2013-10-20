package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;

public class LDIYdn extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		byte d = registerState.fetchWord8();
		byte n = registerState.fetchWord8();
		Memory.memory[registerState.getIY()+d] = n;
	}

}
