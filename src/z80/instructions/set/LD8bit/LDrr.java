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

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class LDrr extends AbstractRegisterInstruction {

	public static int cycles = 1;
	
	@Override
	public void execute(RegisterState registerState) {
		BitSet opcode = BitSet.valueOf(new byte[] {registerState.getOpcode()});
		byte rFromValue = getRegisterValueByCode(opcode.get(5, 7), registerState)[0];
		setRegisterValue(registerState, RegisterCodes.getByCode(opcode.get(2, 4)), new byte[]{rFromValue});
	}

}
