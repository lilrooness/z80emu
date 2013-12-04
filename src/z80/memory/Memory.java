package z80.memory;

public class Memory {

    static public byte[] memory = new byte[65000];
	
	private Memory(){}

    /**
     * zeros all memory
     */
    public static void zero() {
        for(int i=0; i<memory.length; i++) {
            memory[i] = 0;
        }
    }
}
