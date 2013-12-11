package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.util.RadixOperations;

import static z80.core.StatusFlags.C;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 11/12/2013
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class ControlGroup {

    public static int cpl() {
        RegisterState registerState = RegisterState.getInstance();
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String x = RadixOperations.invert(acc);
        byte a = RadixOperations.toByteArray(x)[0];
        registerState.setA(new byte[]{a, registerState.getA()[1]});
        return 1;
    }

    public static int neg() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setA(new byte[] {(byte) (0 - registerState.getA()[0]), registerState.getA()[1]});
        return 2;
    }

    public static int ccf() {
        RegisterState registerState = RegisterState.getInstance();
        if(RegisterState.psr.get(StatusFlags.C.getPosition())) {
            RegisterState.psr.set(StatusFlags.C.getPosition(), false);
        } else {
            RegisterState.psr.set(StatusFlags.C.getPosition(), true);
        }
        return 1;
    }



    public static int scf() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.psr.set(StatusFlags.C.getPosition());
        return 1;
    }
}
