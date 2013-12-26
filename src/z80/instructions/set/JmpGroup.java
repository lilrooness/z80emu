package z80.instructions.set;

import sun.jvm.hotspot.asm.Register;
import z80.core.RegisterState;
import z80.util.RadixOperations;

/**
 * Created by Joe on 26/12/2013.
 */
public class JmpGroup {

    public static int JPnn() {
        RegisterState registerState = RegisterState.getInstance();
        String LSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String MSB = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String operand = MSB + LSB;
        registerState.setPc(RadixOperations.toShort(operand));
        return 3;
    }

    public static int JPccnn() {
        RegisterState registerState = RegisterState.getInstance();

        return 3;
    }
}
