package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 20/10/2013
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class LDRA extends AbstractRegisterInstruction {
    @Override
    public void execute(RegisterState registerState) {
        registerState.setR(registerState.getA()[0]);
    }
}
