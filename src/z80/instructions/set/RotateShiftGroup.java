package z80.instructions.set;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.util.RadixOperations;

/**
 * Created by Joe on 03/01/2014.
 */
public class RotateShiftGroup {
    public static int rlca() {
        RegisterState registerState = RegisterState.getInstance();
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String newString = acc.substring(1) + acc.charAt(0);
        RegisterState.psr.set(StatusFlags.C.getPosition(), acc.charAt(0) == 1?1 : 0);
        registerState.setA(new byte[]{(byte) RadixOperations.toShort(newString), registerState.getA()[1]});
        return 1;
    }

    public static int rla() {

        return 1;
    }
}
