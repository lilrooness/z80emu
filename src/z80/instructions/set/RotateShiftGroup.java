package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created by Joe on 03/01/2014.
 */

public class RotateShiftGroup {

    //HELPER METHODS

    private static String rlc(String bitString) {
        RegisterState registerState = RegisterState.getInstance();
        String newString = bitString.substring(1) + bitString.charAt(0);
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(0) == '1'?1 : 0);
        return newString;
    }

    private static String rl(String bitString) {
        RegisterState registerState = RegisterState.getInstance();
        String newString = bitString.substring(1) + (registerState.psr.get(StatusFlags.C.getPosition())?"1":"0");
        return newString;
    }

    private static String rrc(String bitString) {
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(7) == '1'?1 : 0);
        String newString = bitString.charAt(7) + bitString.substring(0, 7);
        return newString;
    }

    private static String rr(String bitString) {
        char prevC = RegisterState.psr.get(StatusFlags.C.getPosition())?'1':'0';
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(7) == '1'?1:0);
        String newString = prevC + bitString.substring(0, 7);
        return newString;
    }

    private static String sla(String bitString) {
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(0) == '1'?1:0);
        String newString = bitString.substring(1) + '0';
        return newString;
    }

    private static String sra(String bitString) {
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(7) == '1'?1:0);
        String newString = bitString.charAt(0) + bitString.substring(1, 7);
        return newString;
    }

    private static String srl(String bitString) {
        RegisterState.psr.set(StatusFlags.C.getPosition(), bitString.charAt(7) == '1'?1:0);
        String newString = '0' + bitString.substring(1, 7);
        return newString;
    }

//    ###########################################################################################
    public static int rlca() {
        RegisterState registerState = RegisterState.getInstance();
        String newString = RotateShiftGroup.rlc(RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff)));
        registerState.setA(new byte[]{(byte) RadixOperations.toShort(newString), registerState.getA()[1]});
        return 1;
    }

    public static int rla() {
        RegisterState registerState = RegisterState.getInstance();
        String newString = RotateShiftGroup.rl(RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff)));
        registerState.setA(new byte[]{(byte) RadixOperations.toShort(newString), registerState.getA()[1]});
        return 1;
    }

    public static int rrca() {
        RegisterState registerState = RegisterState.getInstance();
        String newString = RotateShiftGroup.rrc(RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff)));
        registerState.setA(new byte[]{(byte) RadixOperations.toShort(newString), registerState.getA()[1]});
        return 1;
    }

    public static int rra() {
        RegisterState registerState = RegisterState.getInstance();
        String newString = RotateShiftGroup.rr(RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff)));
        registerState.setA(new byte[]{(byte) RadixOperations.toShort(newString), registerState.getA()[1]});
        return 1;
    }

    public static int rlcHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.rlc(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int rlcIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.rlc(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rlcIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.rlc(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rlr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.rl(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int rlHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.rl(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int rlIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.rl(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rlIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.rlc(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rrc() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.rrc(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int rrcHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.rrc(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int rrcIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.rrc(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rrcIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.rrc(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.rr(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int rrHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.rr(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int rrIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.rr(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rrIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.rr(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int slar() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.sla(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int slaHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.sla(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int slaIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.sla(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int slaIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.sla(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int srar() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.sra(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int sraHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.sra(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int sraIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.sra(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int sraIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.sra(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int srlr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0] & 0xff));
        String newString = RotateShiftGroup.srl(r);
        AbstractRegisterInstruction.setRegisterValue(
                registerState,
                RegisterCodes.getByCode(opcode.substring(5)),
                new byte[] {(byte)RadixOperations.toShort(newString),
                        AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[1]
                }
        );
        return 2;
    }

    public static int srlHL() {
        RegisterState registerState = RegisterState.getInstance();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        String newString = RotateShiftGroup.srl(bitString);
        Memory.setIndexHL((byte) RadixOperations.toShort(newString));
        return 4;
    }

    public static int srlIXd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIX() + d)) & 0xff));
        String newString = RotateShiftGroup.srl(bitString);
        Memory.setMemoryAt((short) (registerState.getIX() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int srlIYd() {
        RegisterState registerState = RegisterState.getInstance();
        int d = registerState.fetchWord8();
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (registerState.getIY() + d)) & 0xff));
        String newString = RotateShiftGroup.srl(bitString);
        Memory.setMemoryAt((short) (registerState.getIY() + d), (byte) RadixOperations.toShort(newString));
        return 6;
    }

    public static int rld() {
        RegisterState registerState = RegisterState.getInstance();
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String d = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        StringBuilder accBuilder = new StringBuilder(acc);
        StringBuilder dBuilder = new StringBuilder(d);

        String loAcc = acc.substring(4);
        accBuilder.replace(4, acc.length(), d.substring(0, 4));
        dBuilder.replace(0, 4, d.substring(4));
        dBuilder.replace(4, d.length(), loAcc);
        registerState.setA(new byte[] {
                (byte) RadixOperations.toShort(accBuilder.toString()),
                registerState.getA()[1]
        });
        Memory.setIndexHL((byte) RadixOperations.toShort(dBuilder.toString()));
        return 5;
    }

    public static int rrd() {
        RegisterState registerState = RegisterState.getInstance();
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String d = RadixOperations.prependZeros(Integer.toBinaryString(Memory.indexHL() & 0xff));
        StringBuilder accBuilder = new StringBuilder(acc);
        StringBuilder dBuilder = new StringBuilder(d);

        String lowD = d.substring(4);
        dBuilder.replace(4, d.length(), d.substring(0, 4));
        dBuilder.replace(0, 4, acc.substring(4));
        accBuilder.replace(4, acc.length(), lowD);
        registerState.setA(new byte[] {
                (byte) RadixOperations.toShort(accBuilder.toString()),
                registerState.getA()[1]
        });
        Memory.setIndexHL((byte) RadixOperations.toShort(dBuilder.toString()));
        return 5;
    }
}
