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
	
}