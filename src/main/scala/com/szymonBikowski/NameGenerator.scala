package com.szymonBikowski

object NameGenerator {

  val defaultDarknessThreshold: Int = 50 //(0-100)

  def generate(darkness: Double, nameWithExtension: String, darknessThreshold: Int = defaultDarknessThreshold): String = {
    fileName(nameWithExtension) + generateLabel(darkness, darknessThreshold) + fileExtension(nameWithExtension)
  }

  private def fileName(fullName: String): String = {
    fullName.splitAt(1)._1
  }

  private def fileExtension(fullName: String): String = {
    fullName.splitAt(1)._2
  }

  private def generateLabel(darkness: Double, darknessThreshold: Double): String = {
    var label = "_"
    if (darkness <= darknessThreshold && darkness != 0 && darkness != 100) {
      label += "bright"
    }
    if (darkness >= darknessThreshold && darkness != 0 && darkness != 100) {
      label += "dark"
    }
    if (darkness == 0) {
      label += "perfectly_white"
    }
    if (darkness == 100) {
      label += "perfectly_black"
    }
    label += "_" + darkness.toInt
    label
  }

}
