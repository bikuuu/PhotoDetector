package com.szymonBikowski

import java.awt.Color
import java.awt.image.BufferedImage


object DarknessAnalyzer {

  val defaultLuminanceThreshold: Int = 30 //(0-255)

  def darknessPercentage(bufferedImage: BufferedImage, luminanceThreshold: Int = defaultLuminanceThreshold): Double = {
    val luminanceList = this.luminanceList(bufferedImage)
    var darkPixelCount = 0
    for (x <- 0 until luminanceThreshold) {
      darkPixelCount = darkPixelCount + luminanceList(x)
    }
    val pixelsCount = bufferedImage.getWidth() * bufferedImage.getHeight()
    darkPixelCount.toDouble / pixelsCount * 100
  }

  def luminanceList(bufferedImage: BufferedImage): Array[Int] = {
    val histogram = new Array[Int](256)
    for (x <- 0 until bufferedImage.getWidth()) {
      for (y <- 0 until bufferedImage.getHeight()) {
        var luminance = relativeLuminance(new Color(bufferedImage.getRGB(x, y)))
        histogram(luminance) = histogram(luminance) + 1
      }
    }
    histogram
  }

  def relativeLuminance(color: Color): Int = {
    (0.2126 * color.getRed + 0.7152 * color.getGreen + 0.0722 * color.getBlue).toInt
  }

}

