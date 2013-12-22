package z80.instructions.set;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 11/12/2013
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class ControlGroup {

    public static int daa() {
        //TODO implement daa instruction
        return 1;
    }

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

    public static int di() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIFF1(false);
        registerState.setIFF2(false);
        return 1;
    }

    public static int ei() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setIFF1(true);
        registerState.setIFF2(true);
        return 1;
    }

    public static int im0() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setInterruptMode((byte) 0);
        return 1;
    }

    public static int im1() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setInterruptMode((byte) 1);
        return 1;
    }

    public static int im2() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setInterruptMode((byte) 2);
        return 1;
    }
}
