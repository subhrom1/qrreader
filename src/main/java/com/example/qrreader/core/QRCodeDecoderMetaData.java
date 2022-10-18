package com.example.qrreader.core;

import com.google.zxing.ResultPoint;

/**
 * Meta-data container for QR Code decoding.
 * @see https://github.com/zxing/zxing/blob/master/core/src/main/java/com/google/zxing/qrcode/decoder/QRCodeDecoderMetaData.java
 */
public final class QRCodeDecoderMetaData {

  private final boolean mirrored;

  QRCodeDecoderMetaData(boolean mirrored) {
    this.mirrored = mirrored;
  }

  /**
   * @return true if the QR Code was mirrored.
   */
  public boolean isMirrored() {
    return mirrored;
  }

  /**
   * Apply the result points' order correction due to mirroring.
   *
   * @param points Array of points to apply mirror correction to.
   */
  public void applyMirroredCorrection(ResultPoint[] points) {
    if (!mirrored || points == null || points.length < 3) {
      return;
    }
    ResultPoint bottomLeft = points[0];
    points[0] = points[2];
    points[2] = bottomLeft;
    // No need to 'fix' top-left and alignment pattern.
  }

}
