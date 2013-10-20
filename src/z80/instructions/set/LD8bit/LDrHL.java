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
		BitSet registerCode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()}).get(2, 4);
		byte value = Memory.memory[RadixOperations.toShort(BitSet.valueOf(registerState.getHl()))];//use value of HL to index into memory
		setRegisterValue(registerState, RegisterCodes.getByCode(registerCode), new byte[]{value});
	}
}
