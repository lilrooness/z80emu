/**
 * <project>
 * <name>, ICP-3099
 * 2013/14
 * Supervisor: <name>
 */
package z80.util;

import java.util.BitSet;

/**
 * <description>
 *
 * @author Frangoudes, Joseph (eeue5c)
 * @since 01, 1976
 * @version 05, 2005
 */
public class RadixOperations {
	public static byte convertToByte(BitSet bitSet) {
		byte x = 0;
		for(int i=0; i<bitSet.length(); i++) {
			if(bitSet.get(i)) {
				x += 2^i;
			}
		}
		return x;
	}

	public static byte[] convertToByteArray(BitSet bitSet) {
		int bytes = bitSet.length()/8;
		byte[] array = null;
		if(bytes <= 1) {
			array = new byte[1];
			array[0] = convertToByte(bitSet);
		} else {
			array = new byte[bytes];
			for(int i=0; i<array.length; i++) {
				int remaining = array.length - i;
				if(remaining < 4) {
					array[i] = convertToByte(bitSet.get(i, i+remaining-1));
				}
				array[i] = convertToByte(bitSet.get(i, i+4));
			}
		}
		return array;
	}
}