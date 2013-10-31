/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: Stephen Marriott
 */
package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class LDrHL extends AbstractRegisterInstruction {

	@Override
	public void execute(RegisterState registerState) {
		String registerCode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF)).substring(2, 5);
        String l = Integer.toBinaryString(registerState.getHl()[0] & 0xFF);
        String h = Integer.toBinaryString(registerState.getHl()[1] & 0xFF);
        String hl = RadixOperations.prependZeros(h) + RadixOperations.prependZeros(l);
		byte value = Memory.memory[RadixOperations.toShort(hl)];//use value of HL to index into memory
		setRegisterValue(registerState, RegisterCodes.getByCode(registerCode), new byte[]{value});
	}
}
