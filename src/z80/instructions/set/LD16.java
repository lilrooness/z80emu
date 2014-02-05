package z80.instructions.set;

import z80.core.RegisterCodes;
import z80.core.RegisterState;
import z80.instructions.AbstractRegisterInstruction;
import z80.memory.Memory;
import z80.util.RadixOperations;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 10/11/2013
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
public class LD16 {

    public static int LDddnn() {
        RegisterState registerState = RegisterState.getInstance();

        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        byte n1s =(byte) RadixOperations.toShort(n1);
        byte n2s =(byte) RadixOperations.toShort(n2);

        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), new byte[] {n2s, n1s});
        return 2;
    }

    public static int LDIXnn() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        registerState.setIX(RadixOperations.toShort(nn));
        return 4;
    }

    public static int LDIYnn() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        registerState.setIY(RadixOperations.toShort(nn));
        return 4;
    }

    /**
     * contents of memeory address nn are loaded into the low order byte
     * of index register HL, the contents of memory address nn + 1 are
     * loaded into the high order byte of register HL
     */
    public static int HL_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;
        short nns = RadixOperations.toShort(nn);

        byte[] set = new byte[2];

        set[1] = Memory.getMemoryAt(nns);
        set[0] = Memory.getMemoryAt((short) (nns + 1));
        registerState.setHl(set);
        return 5;
    }


    /**
     * the contents of memory address nn are loaded into the low order
     * byte of register pair dd, the contents of memory address nn + 1
     * are loaded into the high order byte of register pair dd
     * @return
     */
    public static int LDdd_nn_() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString((registerState.getCurrentWord8() & 0xff)));

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;
        short nns = RadixOperations.toShort(nn);

        byte[] set = new byte[2];

        set[1] = Memory.getMemoryAt(nns);

        set[0] = Memory.getMemoryAt((short) (nns + 1));

        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), set);
        return 5;
    }

    /**
     * the value of memory address nn is loaded into the low order byte
     * of register pair IX. The value of memory address nn + 1 is loaded
     * into the high order byte of the register pair IX
     * @return
     */
    public static int LDIX_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        short nns = RadixOperations.toShort(nn);
        String[] value = new String[2];
        value[1] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(nns) & 0xff));
        value[0] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (nns+1)) & 0xff));
        String valueTogether = value[0] + value[1];

        registerState.setIX(RadixOperations.toShort(valueTogether));
        return 4;
    }

    /**
     * The contents of memory location nn are loaded into the high order byte
     * of register pair IY, the contents of memory location nn + 1 are loaded
     * into the low order byte of register pair IY
     * @return
     */
    public static int LDIY_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        short nns = RadixOperations.toShort(nn);
        String[] value = new String[2];
        value[1] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(nns) & 0xff));
        value[0] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt((short) (nns+1)) & 0xff));
        String valueTogether = value[0] + value[1];

        registerState.setIY(RadixOperations.toShort(valueTogether));
        return 6;
    }

    /**
     * The high order byte of register pair hl is loaded into memory
     * address nn. The low order byte of register pair hl is loaded
     * into memory address nn+1
     *
     * @return
     */
    public static int LD_nn_hl() {
        RegisterState registerState = RegisterState.getInstance();
        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        Memory.setMemoryAt(nn, registerState.getHl()[0]);
        Memory.setMemoryAt((short) (nn+1), registerState.getHl()[1]);
        return 5;
    }

    /**
     * The high order byte of register pair dd is loaded into
     * the memory address nn, the low order byte of register pair dd
     * is loaded into memory address nn+1
     * @return
     */
    public static int LD_nn_dd() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        byte[] set;

        set = AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)));
        Memory.setMemoryAt(nn, set[0]);
        Memory.setMemoryAt((short) (nn + 1), set[1]);
        return 6;
    }

    public static int LD_nn_IX() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        String ix = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIX() & 0xff));
        String ixl = ix.substring(4);
        String ixh = ix.substring(0, 4);

        short ixls = RadixOperations.toShort(ixl);
        short ixhs = RadixOperations.toShort(ixh);

        Memory.setMemoryAt(nn, (byte)ixhs);
        Memory.setMemoryAt((short) (nn + 1), (byte)ixls);

        return 6;
    }

    public static int LD_nn_IY() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        String ix = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIY() & 0xff));
        String ixl = ix.substring(4);
        String ixh = ix.substring(0, 4);

        short ixls = RadixOperations.toShort(ixl);
        short ixhs = RadixOperations.toShort(ixh);

        Memory.setMemoryAt(nn, (byte)ixhs);
        Memory.setMemoryAt((short) (nn + 1), (byte)ixls);

        return 6;
    }

    public static int LDSPHL() {
        RegisterState registerState = RegisterState.getInstance();
        String h = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[0] & 0xff));
        String l = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getHl()[1] & 0xff));

        String hl = h + l;
        registerState.setSp(RadixOperations.toShort(hl));
        return 1;
    };

    public static int LDSPIX() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setSp(registerState.getIX());
        return 2;
    }

    public static int LDSPIY() {
        RegisterState registerState = RegisterState.getInstance();
        registerState.setSp(registerState.getIY());
        return 2;
    }

    public static int pushqq() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        RegisterCodes.getByCode(opcode.substring(2, 4));

        byte[] value = AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)));
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), value[0]);
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), value[1]);

        return 3;
    }

    public static int pushIX() {
        RegisterState registerState = RegisterState.getInstance();
        String IX = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIX() & 0xff));

        String I = IX.substring(0,5);
        String X = IX.substring(5);
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), (byte)RadixOperations.toShort(I));
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), (byte)RadixOperations.toShort(X));

        return 4;
    }

    public static int pushIY() {
        RegisterState registerState = RegisterState.getInstance();
        String IY = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIY() & 0xff));

        String I = IY.substring(0,5);
        String Y = IY.substring(5);
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), (byte)RadixOperations.toShort(I));
        registerState.setSp((short) (registerState.getSp() - 1));
        Memory.setMemoryAt(registerState.getSp(), (byte)RadixOperations.toShort(Y));

        return 4;
    }

    public static int popqq() {
        RegisterState registerState = RegisterState.getInstance();

        String low = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));
        String high = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));

        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        byte[] set = new byte[] {(byte)RadixOperations.toShort(high), (byte)RadixOperations.toShort(low)};

        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), set);
        return 3;
    }

    public static int popIX() {
        RegisterState registerState = RegisterState.getInstance();

        String low = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));
        String high = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));

        short value = RadixOperations.toShort(high + low);
        registerState.setIX(value);
        return 4;
    }

    public static int popIY() {
        RegisterState registerState = RegisterState.getInstance();

        String low = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));
        String high = RadixOperations.prependZeros(Integer.toBinaryString(Memory.getMemoryAt(registerState.getSp()) & 0xff));
        registerState.setSp((short) (registerState.getSp() + 1));

        short value = RadixOperations.toShort(high + low);
        registerState.setIY(value);
        return 4;
    }
}
