/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.util;

import java.util.BitSet;
import java.util.Stack;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class RadixOperations {
	public static short toShort(BitSet bitSet) {
		Stack<Boolean> stack = new Stack<Boolean>();
		//first reverse the bit order so the LSB is on top of the stack
		for(int i=0; i<bitSet.length(); i++) {
			stack.push(bitSet.get(i));
		}
		short sum = 0;
		for(int i=0; i<bitSet.length(); i++) {
			if(stack.pop()) {
				sum += 2^i;
			}
		}
		return sum;
	}

    public static byte[] toByteArray(String binary) {
        int byteIndex = 0;

        byte[] bytes = new byte[binary.length() / 8];
        for(int i=0; i<binary.length() / 8; i++) {
            int base = 8 * i;
            for(int j=0; j<8; j++) {
                if(binary.charAt(base + j) == '1') {
                    bytes[i] += Math.pow(2, (7 - j));
                }
            }
        }
        return bytes;
    }

    public static boolean checkEquals(BitSet bitset1, BitSet bitSet2) {
        for(int i=0; i<bitset1.length(); i++) {
            boolean _1 = bitset1.get(i);
            boolean _2 = bitSet2.get(i);

            if(bitset1.get(i) != bitSet2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static BitSet stringByteToBitSet(String bitString) {
        BitSet bitSet = new BitSet(8);
        int prepend = 8 - bitString.length();
        int original_prepend = prepend;
        for(int i=0; i<8; i++) {
            if(prepend > 0) {
                bitSet.set(i, false);
                prepend --;
            } else {
                if(bitString.charAt(i - original_prepend) == '1') {
                    bitSet.set(i);
                }
            }
        }
        return bitSet;
    }

    
}