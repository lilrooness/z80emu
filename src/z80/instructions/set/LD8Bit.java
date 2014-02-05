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
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public class LD8Bit {

    public static void LDABC(RegisterState registerState) {
        String BC = Integer.toBinaryString(registerState.getBc()[0]) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[1]));
        registerState.setA(new byte[]{Memory.getMemoryAt(RadixOperations.toShort(BC))});
    }

    public static void LDADE(RegisterState registerState) {
        String DE = Integer.toBinaryString(registerState.getDe()[0]) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1]));
        registerState.setA(new byte[] {Memory.getMemoryAt(RadixOperations.toShort(DE))});
    }

    public static void LDAI(RegisterState registerState) {

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

    public static void LDAnn(RegisterState registerState) {
        byte n2 = registerState.fetchWord8();
        byte n1 = registerState.fetchWord8();
        String nn = Integer.toBinaryString(n2 & 0xff) + RadixOperations.prependZeros(Integer.toBinaryString(n1 & 0xff));
        registerState.setA(new byte[]{Memory.getMemoryAt(RadixOperations.toShort(nn))});
    }

    public static void LDAR(RegisterState registerState) {
        byte r = registerState.getR();
        registerState.setA(new byte[]{r});

        if(r < 0) {
            RegisterState.psr.set(StatusFlags.S.getPosition());
        } else {
            RegisterState.psr.set(StatusFlags.S.getPosition(), false);
        }

        if(r == 0) {
            RegisterState.psr.set(StatusFlags.Z.getPosition());
        } else {
            RegisterState.psr.set(StatusFlags.Z.getPosition(), false);
        }

        RegisterState.psr.set(StatusFlags.H.getPosition(), false);
        RegisterState.psr.set(StatusFlags.PV.getPosition(), registerState.isIFF2());
        RegisterState.psr.set(StatusFlags.N.getPosition(), false);
    }

    public static void LDBCA(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String bc = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[0])) +
        RadixOperations.prependZeros(Integer.toBinaryString(registerState.getBc()[1]));
        Memory.setMemoryAt(RadixOperations.toShort(bc), value);
    }

    public static void LDDEA(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String D = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[0] & 0xff));
        String E = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getDe()[1] & 0xff));
//        Memory.memory[RadixOperations.toShort(D+E)] = value;
        Memory.setMemoryAt(RadixOperations.toShort(D+E), value);
    }

    public static void LDHLn(RegisterState registerState) {
        byte value = registerState.fetchWord8();
        String HL = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xFF)) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1]));
        Memory.setMemoryAt(RadixOperations.toShort(HL), value);
    }

    public static void LDHLr(RegisterState registerState) {
        String HL;
        HL = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xFF)) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1]));
        short index = RadixOperations.toShort(HL);
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte value = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        Memory.setMemoryAt(index, value);
    }

    public static void execute(RegisterState registerState) {
        String HL;
        HL = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xFF)) + RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1]));
        short index = RadixOperations.toShort(HL);
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8()));
        byte value = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        Memory.setMemoryAt(index, value);
    }

    public static void LDIA(RegisterState registerState) {
        registerState.setI(registerState.getA()[0]);
    }

    public static void LDIXdn(RegisterState registerState) {
        byte d = registerState.fetchWord8();
        byte n = registerState.fetchWord8();

//        Memory.memory[registerState.getIX()+d] = n;
        Memory.setMemoryAt((short) (registerState.getIX()+d), n);
    }

    public static void LDIXdr(RegisterState registerState) {
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
        byte value = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
//        Memory.memory[registerState.getIX()+registerState.fetchWord8()] = value;
        Memory.setMemoryAt((short) (registerState.getIX()+registerState.fetchWord8()), value);
    }

    public static void LDIYdn(RegisterState registerState) {
        byte d = registerState.fetchWord8();
        byte n = registerState.fetchWord8();
        Memory.setMemoryAt((short) (registerState.getIY()+d), n);
    }

    public static void LDIYdr(RegisterState registerState) {
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
        byte value = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5), registerState)[0];
        Memory.setMemoryAt((short) (registerState.getIY()+registerState.fetchWord8()), value);
//        Memory.memory[registerState.getIY()+registerState.fetchWord8()] = value;
    }

    public static void LDnnA(RegisterState registerState) {
        byte value = registerState.getA()[0];
        String n2 = Integer.toBinaryString(registerState.fetchWord8() & 0xFF);
        String n1 = Integer.toBinaryString(registerState.fetchWord8() & 0xFF);
        Memory.setMemoryAt(RadixOperations.toShort(RadixOperations.prependZeros(n2) + RadixOperations.prependZeros(n1)), value);
//        Memory.memory[] = value;
    }

    public static void LDRA(RegisterState registerState) {
        registerState.setR(registerState.getA()[0]);
    }

    public static void DLrHL(RegisterState registerState) {
        String registerCode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF)).substring(2, 5);
        String l = Integer.toBinaryString(registerState.getHl()[0] & 0xFF);
        String h = Integer.toBinaryString(registerState.getHl()[1] & 0xFF);
        String hl = RadixOperations.prependZeros(h) + RadixOperations.prependZeros(l);
//        byte value = Memory.memory[RadixOperations.toShort(hl)];//use value of HL to index into memory
        byte value = Memory.getMemoryAt(RadixOperations.toShort(hl));
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(registerCode), new byte[]{value});
    }

    public static void LDrIXd(RegisterState registerState) {
//		BitSet opcode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()});
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
        byte d = registerState.fetchWord8();
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 5)), new byte[] {Memory.getMemoryAt((short) (d + registerState.getIX()))});
    }

    public static void LDrIYd(RegisterState registerState) {
//		BitSet opcode = BitSet.valueOf(new byte[]{registerState.getCurrentWord8()});
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
        byte d = registerState.fetchWord8();
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 5)), new byte[]{Memory.getMemoryAt((short) (d+registerState.getIY()))});
    }

    public static void LDrn(RegisterState registerState) {
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        //BitSet opcode = RadixOperations.stringByteToBitSet(bitString);
        byte n = registerState.fetchWord8();
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(bitString.substring(2, 5)), new byte[]{n});
    }

    public static void LDrr(RegisterState registerState) {
//		BitSet opcode = BitSet.valueOf(new byte[] {registerState.getCurrentWord8()});
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xFF));
        byte rFromValue = AbstractRegisterInstruction.getRegisterValueByCode(opcode.substring(5, 8), registerState)[0];
        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 5)), new byte[]{rFromValue});
    }
}
