/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.instructions.set.LD8bit;

import java.util.BitSet;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.util.RadixOperations;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class LDrr extends AbstractRegisterInstruction {

	public int cycles = 1;

	@Override
	public void execute(RegisterState registerState) {
//		BitSet opcode = BitSet.valueOf(new byte[] {registerState.getCurrentWord8()});
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
		byte rFromValue = getRegisterValueByCode(opcode.substring(5, 8), registerState)[0];
		setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 5)), new byte[]{rFromValue});
	}
}
