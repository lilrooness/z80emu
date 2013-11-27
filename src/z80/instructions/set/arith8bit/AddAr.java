package z80.instructions.set.arith8bit;

import java.util.BitSet;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;

/**
 * The contents of register r are added to the contents of the Accumulator, and
 * the result is stored in the Accumulator.
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
@Deprecated
public class AddAr extends AbstractRegisterInstruction {
	public static int cycles = 1;
	@Override
	public void execute(RegisterState registerState) {
//		byte[] a = registerState.getA();
//		BitSet opcode = BitSet.valueOf(new byte[] { registerState.getCurrentWord8() });
//		byte b = getRegisterValueByCode(opcode.get(4, 7), registerState)[0];
//		registerState.setA(new byte[] { (byte) (a[0] + b), a[1] });
	}
}
