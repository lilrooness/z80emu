package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

public class LDHLr extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
        String HL;
        HL = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xFF)) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1]));
        short index = RadixOperations.toShort(HL);
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
		byte value = getRegisterValueByCode(opcode.substring(5), registerState)[0];
		Memory.memory[index] = value;
	}
}
