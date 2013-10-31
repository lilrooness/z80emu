package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDIXdr extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
		byte value = getRegisterValueByCode(opcode.substring(5), registerState)[0];
		Memory.memory[registerState.getIX()+registerState.fetchWord8()] = value;
	}
}
