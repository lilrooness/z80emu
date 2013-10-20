package z80.instructions.set.LD8bit;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 20/10/2013
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class LDAI extends AbstractRegisterInstruction {

    @Override
    public void execute(RegisterState registerState) {

        byte i = registerState.getI();
        registerState.setA(new byte[]{i});
        if(i < 0) {
            RegisterState.psr.set(StatusFlags.S.getPosition());
        } else {
            RegisterState.psr.set(StatusFlags.S.getPosition());
            registerState.psr.set(StatusFlags.S.getPosition(), false);
        }

        if(i == 0) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        } else {
            RegisterState.psr.set(StatusFlags.Z.getPosition(), false);
        }

        RegisterState.psr.set(StatusFlags.H.getPosition(), false);
        RegisterState.psr.set(StatusFlags.PV.getPosition(), registerState.isIFF2());
        RegisterState.psr.set(StatusFlags.N.getPosition(), false);
    }
}
