package z80.instructions.set;

import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created by Joe on 05/01/2014.
 */
public class ExTransSearchGroup {

    private static void aCompHl() {
        RegisterState registerState = RegisterState.getInstance();
        int result = registerState.getA()[0] - Memory.indexHL();
        if(result > -1) {
            RegisterState.psr.set(StatusFlags.S.getPosition(), 0);
        }
        if(result == 0) {
            RegisterState.psr.set(StatusFlags.Z.getPosition(), 1);
        }
    }

    public static int exDeHl() {
        RegisterState registerState = RegisterState.getInstance();

        byte[] de = registerState.getDe();
        byte[] hl = registerState.getHl();
        registerState.setDe(de);
        registerState.setHl(hl);

        return 1;
    }

    public static int exAfAf() {
        RegisterState registerState = RegisterState.getInstance();

        byte[] af = registerState.getA();
        byte[] af_ = registerState.getA_();
        registerState.setA(af_);
        registerState.setA_(af);

        return 1;
    }

    public static int exx() {
        RegisterState registerState = RegisterState.getInstance();

        byte[] bc = registerState.getBc();
        byte[] de = registerState.getDe();
        byte[] hl = registerState.getHl();

        byte[] bc_ = registerState.getBc_();
        byte[] de_ = registerState.getDe_();
        byte[] hl_ = registerState.getHl_();

        registerState.setHl(hl_);
        registerState.setDe(de_);
        registerState.setBc(bc_);

        registerState.setHl_(hl);
        registerState.setDe_(de);
        registerState.setBc_(bc);

        return 1;
    }

    public static int exSpHl() {
        RegisterState registerState = RegisterState.getInstance();

        String msb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp()]));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp() + 1]));


        Memory.memory[registerState.getSp()] = registerState.getHl()[1];
        Memory.memory[registerState.getSp() + 1] = registerState.getHl()[0];

        registerState.setSp(RadixOperations.toShort(msb + lsb));
        return 5;
    }

    public static int exSpIX() {
        RegisterState registerState = RegisterState.getInstance();
        String IX = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIX() & 0xff));

        String msb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp()] & 0xff));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp() + 1] & 0xff));

        registerState.setSp(RadixOperations.toShort(msb + lsb));

        msb = IX.substring(0, 4);
        lsb = IX.substring(4);

        Memory.memory[registerState.getSp()] = (byte) RadixOperations.toShort(msb);
        Memory.memory[registerState.getSp() + 1] = (byte) RadixOperations.toShort(lsb);
        return 6;
    }

    public static int exSpIY() {
        RegisterState registerState = RegisterState.getInstance();
        String IY = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIY() & 0xff));

        String msb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp()] & 0xff));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[registerState.getSp() + 1] & 0xff));

        registerState.setIY(RadixOperations.toShort(msb + lsb));

        msb = IY.substring(0, 4);
        lsb = IY.substring(4);

        Memory.memory[registerState.getSp()] = (byte) RadixOperations.toShort(msb);
        Memory.memory[registerState.getSp() + 1] = (byte) RadixOperations.toShort(lsb);
        return 6;
    }

    public static int ldi() {
        RegisterState registerState = RegisterState.getInstance();
        Memory.setIndexDE(Memory.indexHL());
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getDe()) + 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getHl()) + 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getBc()) - 1)));
        return 4;
    }

    public static int ldir() {
        RegisterState registerState = RegisterState.getInstance();
        ldi();
        if(RadixOperations.byteArrayToShort(registerState.getBc()) != 0) {
            registerState.setPc((short) (registerState.getPc() - 2));
        }
        return 5;
    }

    public static int ldd() {
        RegisterState registerState = RegisterState.getInstance();
        Memory.setIndexDE(Memory.indexHL());
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getDe()) - 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getHl()) - 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getBc()) - 1)));
        return 4;
    }

    public static int lddr() {
        RegisterState registerState = RegisterState.getInstance();
        ldd();
        if(RadixOperations.byteArrayToShort(registerState.getBc()) != 0) {
            registerState.setPc((short) (registerState.getPc() - 2));
        }
        return 5;
    }

    public static int cpi() {
        RegisterState registerState = RegisterState.getInstance();
        ExTransSearchGroup.aCompHl();
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getHl()) + 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getBc()) - 1)));
        RegisterState.psr.set(StatusFlags.N.getPosition(), 1);
        if(RadixOperations.byteArrayToShort(registerState.getBc()) == 0) {
            RegisterState.psr.set(StatusFlags.PV.getPosition(), 0);
        } else {
            RegisterState.psr.set(StatusFlags.PV.getPosition(), 1);
        }

        return 4;
    }

    public static int cpir() {
        RegisterState registerState = RegisterState.getInstance();
        cpi();
        if(RadixOperations.byteArrayToShort(registerState.getBc()) == 0 && RegisterState.psr.get(StatusFlags.Z.getPosition())) {
            return 4;
        }
        registerState.setPc((short) (registerState.getPc() - 2));
        return 5;
    }

    public static int cpd() {
        RegisterState registerState = RegisterState.getInstance();
        ExTransSearchGroup.aCompHl();
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getHl()) - 1)));
        registerState.setDe(RadixOperations.shortToByteArray((short) (RadixOperations.byteArrayToShort(registerState.getBc()) - 1)));
        RegisterState.psr.set(StatusFlags.N.getPosition(), 1);
        if(RadixOperations.byteArrayToShort(registerState.getBc()) == 0) {
            RegisterState.psr.set(StatusFlags.PV.getPosition(), 0);
        } else {
            RegisterState.psr.set(StatusFlags.PV.getPosition(), 1);
        }

        return 4;
    }

    public static int cpdr() {
        RegisterState registerState = RegisterState.getInstance();
        cpd();
        if(RadixOperations.byteArrayToShort(registerState.getBc()) == 0 && RegisterState.psr.get(StatusFlags.Z.getPosition())) {
            return 4;
        }
        registerState.setPc((short) (registerState.getPc() - 2));
        return 5;
    }
}
