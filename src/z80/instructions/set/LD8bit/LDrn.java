/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.instructions.set.LD8bit;

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
public class LDrn extends AbstractRegisterInstruction {

	public static int cycles = 2;
	
	@Override
	public void execute(RegisterState registerState) {
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        //BitSet opcode = RadixOperations.stringByteToBitSet(bitString);
		byte n = registerState.fetchWord8();
		setRegisterValue(registerState, RegisterCodes.getByCode(bitString.substring(2, 5)), new byte[]{n});
	}

}
