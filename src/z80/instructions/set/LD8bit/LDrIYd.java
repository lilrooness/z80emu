package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDrIYd extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
//		BitSet opcode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()});
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
		byte d = registerState.fetchWord8();
		setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 5)), new byte[]{Memory.memory[d+registerState.getIY()]});
	}

}
