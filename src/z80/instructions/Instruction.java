package z80.instructions;

import z80.core.RegisterState;

public interface Instruction {
	public void execute(RegisterState registerState);
}
