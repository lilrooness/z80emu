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

    public int LDddnn() {
        RegisterState registerState = RegisterState.getInstance();

        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));
        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        byte n1s =(byte) RadixOperations.toShort(n1);
        byte n2s =(byte) RadixOperations.toShort(n2);

        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), new byte[] {n2s, n1s});
        return 2;
    }

    public int LDIXnn() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        registerState.setIX(RadixOperations.toShort(nn));
        return 4;
    }

    public int LDIYnn() {
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
    public int HL_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;
        short nns = RadixOperations.toShort(nn);

        byte[] set = new byte[2];

        set[1] = Memory.memory[nns];
        set[0] = Memory.memory[nns + 1];
        registerState.setHl(set);
        return 5;
    }


    /**
     * the contents of memory address nn are loaded into the low order
     * byte of register pair dd, the contents of memory address nn + 1
     * are loaded into the high order byte of register pair dd
     * @return
     */
    public int LDdd_nn_() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString((registerState.getCurrentWord8() & 0xff)));

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;
        short nns = RadixOperations.toShort(nn);

        byte[] set = new byte[2];

        set[1] = Memory.memory[nns];
        set[0] = Memory.memory[nns + 1];

        AbstractRegisterInstruction.setRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)), set);
        return 5;
    }

    /**
     * the value of memory address nn is loaded into the low order byte
     * of register pair IX. The value of memory address nn + 1 is loaded
     * into the high order byte of the register pair IX
     * @return
     */
    public int LDIX_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        short nns = RadixOperations.toShort(nn);
        String[] value = new String[2];
        value[1] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[nns] & 0xff));
        value[0] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[nns+1] & 0xff));
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
    public int LDIY_nn_() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String nn = n2 + n1;

        short nns = RadixOperations.toShort(nn);
        String[] value = new String[2];
        value[1] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[nns] & 0xff));
        value[0] =  RadixOperations.prependZeros(Integer.toBinaryString(Memory.memory[nns+1] & 0xff));
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
    public int LD_nn_hl() {
        RegisterState registerState = RegisterState.getInstance();
        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        Memory.memory[nn] = registerState.getHl()[0];
        Memory.memory[nn+1] = registerState.getHl()[1];
        return 5;
    }

    /**
     * The high order byte of register pair dd is loaded into
     * the memory address nn, the low order byte of register pair dd
     * is loaded into memory address nn+1
     * @return
     */
    public int LD_nn_dd() {
        RegisterState registerState = RegisterState.getInstance();
        String opcode = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getCurrentWord8() & 0xff));

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        byte[] set;

        set = AbstractRegisterInstruction.get16BitRegisterValue(registerState, RegisterCodes.getByCode(opcode.substring(2, 4)));
        Memory.memory[nn] = set[0];
        Memory.memory[nn + 1] = set[1];
        return 6;
    }

    public int LD_nn_IX() {
        RegisterState registerState = RegisterState.getInstance();

        String n1 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));
        String n2 = RadixOperations.prependZeros(Integer.toBinaryString(registerState.fetchWord8() & 0xff));

        short nn = RadixOperations.toShort(n2 + n1);
        String ix = RadixOperations.prependZeros(Integer.toBinaryString(registerState.getIX() & 0xff));
        String ixl = ix.substring(4);
        String ixh = ix.substring(0, 4);

        short ixls = RadixOperations.toShort(ixl);
        short ixhs = RadixOperations.toShort(ixh);

        Memory.memory[nn] = (byte)ixhs;
        Memory.memory[nn + 1] = (byte)ixls;

        return 6;
    }

}
