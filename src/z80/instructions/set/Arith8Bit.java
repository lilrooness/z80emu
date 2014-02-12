package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.core.StatusFlags;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 27/11/2013
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class Arith8Bit {

    public static void AddAn() {
        RegisterState registerState = RegisterState.getInstance();
        byte n = registerState.fetchWord8();
        int result = registerState.getA()[0] + n;
        registerState.setA(new byte[] {(byte)result, registerState.getA()[1]});
        RegisterState.psr.set(StatusFlags.S.getPosition(), (result < 0));
        RegisterState.psr.set(StatusFlags.Z.getPosition(), (result == 0));
        RegisterState.psr.set(StatusFlags.UN.getPosition(), false);
        RegisterState.psr.set(StatusFlags.PV.getPosition(), (result > 127 || result < -128));
    }

    public static void AddAr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        registerState.setA(new byte[]{(byte) (registerState.getA()[0]+r), registerState.getA()[1]});
    }

    public static void addAHL() {
        RegisterState registerState = RegisterState.getInstance();
        String nHigh = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String nLow = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String index = nHigh + nLow;
//        byte n = Memory.memory[RadixOperations.toShort(index)];
        byte n = Memory.getMemoryAt(RadixOperations.toShort(index));
        registerState.setA(new byte[] {(byte) (registerState.getA()[0] +n),registerState.getA()[1]});
    }

    public static int addIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] + Memory.getMemoryAt(a)), registerState.getA()[1]});
        return 5;
    }

    public static int addIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] + Memory.getMemoryAt(a)), registerState.getA()[1]});
        return 5;
    }

    public static int subr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        registerState.setA(new byte[]{(byte) (registerState.getA()[0]-r), registerState.getA()[1]});
        return 1;
    }

    public static int subn() {
        RegisterState registerState = RegisterState.getInstance();
        byte n = registerState.fetchWord8();
        int result = registerState.getA()[0] - n;
        registerState.setA(new byte[] {(byte)result, registerState.getA()[1]});
        return 2;
    }

    public static int subHL() {
        RegisterState registerState = RegisterState.getInstance();
        String nHigh = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String nLow = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));
        String index = nHigh + nLow;
//        byte n = Memory.memory[RadixOperations.toShort(index)];
        byte n = Memory.getMemoryAt(RadixOperations.toShort(index));
        registerState.setA(new byte[] {(byte) (registerState.getA()[0] - n),registerState.getA()[1]});
        return 2;
    }

    public static int subIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] - Memory.getMemoryAt(a)), registerState.getA()[1]});
        return 5;
    }

    public static int subIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);
        registerState.setA(new byte[]{(byte) (registerState.getA()[0] - Memory.getMemoryAt(a)), registerState.getA()[1]});
        return 5;
    }

    public static int andr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        String rv = RadixOperations.prependZeros(Integer.toBinaryString(r & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.and(rv, acc);
        registerState.setA(new byte[]{RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int andn() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.and(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int andHL() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getHl()[0]) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.and(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int andIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.and(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int andIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.and(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int orr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        String rv = RadixOperations.prependZeros(Integer.toBinaryString(r & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.or(rv, acc);
        registerState.setA(new byte[]{RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int orn() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.or(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int orHL() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getHl()[0]) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.or(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int orIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.or(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int orIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.or(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int xorr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte r = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        String rv = RadixOperations.prependZeros(Integer.toBinaryString(r & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.xor(rv, acc);
        registerState.setA(new byte[]{RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int xorn() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.xor(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int xorHL() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getHl()[0]) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0] & 0xff));
        String result = RadixOperations.xor(n, acc);
        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return  1;
    }

    public static int xorIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.xor(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int xorIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);

        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a + d)));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String result = RadixOperations.xor(n, acc);

        registerState.setA(new byte[] {RadixOperations.toByteArray(result)[0], registerState.getA()[1]});
        return 1;
    }

    public static int cpr() {
        RegisterState registerState = RegisterState.getInstance();
        String code = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        String r = RadixOperations.prependZeros(Integer.toBinaryString(AbstractRegisterInstruction.getRegisterValueByCode(code.substring(5), registerState)[0]));
        if(acc.equals(r)) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        }
        return 1;
    }

    public static int cpn() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        if(acc.equals(n)) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        }
        return 2;
    }

    public static int cpHL() {
        RegisterState registerState = RegisterState.getInstance();
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getHl()[0]) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        if(acc.equals(n)) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        }
        return 2;
    }

    public static int cpIXd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIX() + d);
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        if(acc.equals(n)) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        }
        return 5;
    }

    public static int cpIYd() {
        RegisterState registerState = RegisterState.getInstance();
        byte d = registerState.fetchWord8();
        short a = (short) (registerState.getIY() + d);
        String n = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(a) & 0xff));
        String acc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getA()[0]));
        if(acc.equals(n)) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        }
        return 5;
    }

    public static int incr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String code = opcode.substring(2, 5);
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(code),
                new byte[]{
                        (byte) (AbstractRegisterInstruction.getRegisterValueByCode(code, registerState)[0] + 1),
                        AbstractRegisterInstruction.getRegisterValueByCode(code, registerState)[1]
                });
        return 1;
    }

    public static int incHL() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.getMemoryAt()[] += 1;
        Memory.setMemoryAt(registerState.getHl()[0], (byte) (Memory.getMemoryAt(registerState.getHl()[0]) + 1));
        return 3;
    }

    public static int incIXd() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.memory[registerState.getIX()] += 1;
        Memory.setMemoryAt(registerState.getIX(), (byte) (Memory.getMemoryAt(registerState.getIX()) + 1));
        return 6;
    }

    public static int incIYd() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.memory[] += 1;
        Memory.setMemoryAt(registerState.getIY(), (byte) (Memory.getMemoryAt(registerState.getIY()) + 1));
        return 6;
    }

    public static int decr() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String code = opcode.substring(2, 5);
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(code),
                new byte[]{
                        (byte) (AbstractRegisterInstruction.getRegisterValueByCode(code, registerState)[0] + 1),
                        AbstractRegisterInstruction.getRegisterValueByCode(code, registerState)[1]
                });
        return 1;
    }

    public static int decHL() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.memory[] += 1;
        Memory.setMemoryAt(registerState.getHl()[0], (byte) (Memory.getMemoryAt(registerState.getHl()[0]) + 1));
        return 3;
    }

    public static int decIXd() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.memory[registerState.getIX()] += 1;
        Memory.setMemoryAt(registerState.getIX(), (byte) (Memory.getMemoryAt(registerState.getIX()) + 1));
        return 6;
    }

    public static int decIYd() {
        RegisterState registerState = RegisterState.getInstance();
//        Memory.memory[registerState.getIY()] += 1;
        Memory.setMemoryAt(registerState.getIY(), (byte) (Memory.getMemoryAt(registerState.getIY()) + 1));
        return 6;
    }
}