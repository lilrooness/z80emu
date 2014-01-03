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

    public static short toShort(String bitSet) {
        short sum = 0;

        String reversed = new StringBuilder(bitSet).reverse().toString();

        for(int i=0; i < reversed.length(); i++) {
            if(reversed.charAt(i) == '1') {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

//    public static int toInt(String bin ) {
//        String reversed = new StringBuilder(bin).reverse().toString();
//    }

    public static byte[] toByteArray(String binary) {
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

    public static short byteArrayToShort(byte[] array) {
        String msb = RadixOperations.prependZeros(Integer.toBinaryString(array[0] & 0xff));
        String lsb = RadixOperations.prependZeros(Integer.toBinaryString(array[1] & 0xff));
        return RadixOperations.toShort(msb + lsb);
    }

    public static byte[] shortToByteArray(short value) {
        String bitString = RadixOperations.prependZeros(Integer.toBinaryString(value & 0xff));
        String msb = bitString.substring(0, 8);
        String lsb = bitString.substring(8);
        return new byte[] {(byte) RadixOperations.toShort(msb), (byte) RadixOperations.toShort(lsb)};
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

    public static boolean checkEquals(String bitSet1, String bitSet2) {
        for(int i=0; i<bitSet1.length(); i++) {

            if(bitSet1.charAt(i) != bitSet2.charAt(i)) {
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

    public static String prependZeros(String binString) {
        if(binString.length() < 8) {
            int diff = 8 - binString.length();
            String prepend = "";
            for(int i=0; i<diff; i++) {
                prepend = prepend + "0";
            }
            return prepend+binString;
        }
        return binString;
    }

    public static boolean getBit(String binString, int index) {
        if(binString.charAt(index) == '1') {
            return true;
        }
        return false;
    }

    public static String and(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0 ;i < a.length(); i++) {
            if(a.charAt(i) == '1' && b.charAt(i) == '1') {
                stringBuilder.append('1');
            } else {
                stringBuilder.append('0');
            }
        }
        return stringBuilder.toString();
    }

    public static String or(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i < a.length(); i++) {
            if(a.charAt(i) == '1' || b.charAt(i) == '1') {
                stringBuilder.append('1');
            } else{
                stringBuilder.append('0');
            }
        }
        return stringBuilder.toString();
    }

    public static String xor(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<a.length(); i++) {
            if((a.charAt(i) == '1' && b.charAt(i) == '0') || (a.charAt(i) == '0' && b.charAt(i) == '1')) {
                stringBuilder.append('1');
            } else {
                stringBuilder.append('0');
            }
        }
        return stringBuilder.toString();
    }

    public static String invert(String a) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0 ;i<a.length(); i++) {
            if(a.charAt(i) == '1') {
                stringBuilder.append('0');
            } else {
                stringBuilder.append('1');
            }
        }
        return stringBuilder.toString();
    }
}