package z80.instructions.set.arith8bit;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 23/10/2013
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class AddAn extends AbstractRegisterInstruction {
    @Override
    public void execute(RegisterState registerState) {
        byte n = registerState.fetchWord8();
        int result = registerState.getA()[0] + n;
        RegisterState.psr.set(StatusFlags.S.getPosition(), (result < 0));
        RegisterState.psr.set(StatusFlags.Z.getPosition(), (result == 0));
        RegisterState.psr.set(StatusFlags.UN.getPosition(), false);
        RegisterState.psr.set(StatusFlags.PV.getPosition(), (result > 127 || result < -128));
    }
}
