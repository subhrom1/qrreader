package com.example.qrreader.core;

/**
 * Utility class which helps in converting 256 bit key from
 * String to Hex and binary.
 */
public class KeyUtil {

    // Private constructor for the class
    private KeyUtil() {
    }

    /**
     * Hexadecimal String to Binary Converter.
     *
     * @param hexString Hexadecimal key string
     * @return int[] the integer array of 0s and 1s representing
     * the key data.
     */
    public static int[] hexToBinary(String hexString) {
        String binaryStr = hexToBinaryString(hexString);
        int[] binArr = new int[binaryStr.length()];
        int i = 0;
        for (char binaryChar : binaryStr.toCharArray()) {
            binArr[i++] = binaryChar == '0' ? 0 : 1;
        }
        return binArr;
    }

    /*
     * Converts Hexadecimal String to Binary String.
     */
    private static String hexToBinaryString(String hexString) {
        StringBuilder binaryBuilder = new StringBuilder();
        for (char hexChar : hexString.toCharArray()) {
            switch (hexChar) {
                case '0':
                    binaryBuilder.append("0000");
                    break;
                case '1':
                    binaryBuilder.append("0001");
                    break;
                case '2':
                    binaryBuilder.append("0010");
                    break;
                case '3':
                    binaryBuilder.append("0011");
                    break;
                case '4':
                    binaryBuilder.append("0100");
                    break;
                case '5':
                    binaryBuilder.append("0101");
                    break;
                case '6':
                    binaryBuilder.append("0110");
                    break;
                case '7':
                    binaryBuilder.append("0111");
                    break;
                case '8':
                    binaryBuilder.append("1000");
                    break;
                case '9':
                    binaryBuilder.append("1001");
                    break;
                case 'a':
                case 'A':
                    binaryBuilder.append("1010");
                    break;
                case 'b':
                case 'B':
                    binaryBuilder.append("1011");
                    break;
                case 'c':
                case 'C':
                    binaryBuilder.append("1100");
                    break;
                case 'd':
                case 'D':
                    binaryBuilder.append("1101");
                    break;
                case 'e':
                case 'E':
                    binaryBuilder.append("1110");
                    break;
                case 'f':
                case 'F':
                    binaryBuilder.append("1111");
                    break;
                default:
                    //returns null if hex has any invalid character.
                    return null;
            }
        }
        return binaryBuilder.toString();
    }
}
