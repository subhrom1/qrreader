package com.example.qrreader.core;

import com.google.zxing.common.BitMatrix;

/**
 * Encapsulates data masks for the data bits in a QR code.
 * @see https://github.com/zxing/zxing/blob/master/core/src/main/java/com/google/zxing/qrcode/decoder/DataMask.java
 * */
enum DataMask {

  // See ISO 18004:2006 6.8.1

  /**
   * 000: mask bits for which (x + y) mod 2 == 0
   */
  DATA_MASK_000() {
    @Override
    boolean isMasked(int i, int j) {
      return ((i + j) & 0x01) == 0;
    }
  },

  /**
   * 001: mask bits for which x mod 2 == 0
   */
  DATA_MASK_001() {
    @Override
    boolean isMasked(int i, int j) {
      return (i & 0x01) == 0;
    }
  },

  /**
   * 010: mask bits for which y mod 3 == 0
   */
  DATA_MASK_010() {
    @Override
    boolean isMasked(int i, int j) {
      return j % 3 == 0;
    }
  },

  /**
   * 011: mask bits for which (x + y) mod 3 == 0
   */
  DATA_MASK_011() {
    @Override
    boolean isMasked(int i, int j) {
      return (i + j) % 3 == 0;
    }
  },

  /**
   * 100: mask bits for which (x/2 + y/3) mod 2 == 0
   */
  DATA_MASK_100() {
    @Override
    boolean isMasked(int i, int j) {
      return (((i / 2) + (j / 3)) & 0x01) == 0;
    }
  },

  /**
   * 101: mask bits for which xy mod 2 + xy mod 3 == 0
   * equivalently, such that xy mod 6 == 0
   */
  DATA_MASK_101() {
    @Override
    boolean isMasked(int i, int j) {
      return (i * j) % 6 == 0;
    }
  },

  /**
   * 110: mask bits for which (xy mod 2 + xy mod 3) mod 2 == 0
   * equivalently, such that xy mod 6 < 3
   */
  DATA_MASK_110() {
    @Override
    boolean isMasked(int i, int j) {
      return ((i * j) % 6) < 3;
    }
  },

  /**
   * 111: mask bits for which ((x+y)mod 2 + xy mod 3) mod 2 == 0
   * equivalently, such that (x + y + xy mod 3) mod 2 == 0
   */
  DATA_MASK_111() {
    @Override
    boolean isMasked(int i, int j) {
      return ((i + j + ((i * j) % 3)) & 0x01) == 0;
    }
  };

  // End of enum constants.


  /**
   * <p>Implementations of this method reverse the data masking process applied to a QR Code and
   * make its bits ready to read.</p>
   *
   * @param bits representation of QR Code bits
   * @param dimension dimension of QR Code, represented by bits, being unmasked
   */
  final void unmaskBitMatrix(BitMatrix bits, int dimension) {
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (isMasked(i, j)) {
          bits.flip(j, i);
        }
      }
    }
  }

  abstract boolean isMasked(int i, int j);

}
