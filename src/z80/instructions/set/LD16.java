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

}
