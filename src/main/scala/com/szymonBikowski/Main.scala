package com.szymonBikowski


object Main {

  def main(args: Array[String]): Unit = {

    val darknessCopy = DarknessImageCopy

    DarknessImageCopy.inDirPath = FileManager.setInDirPath().toString
    DarknessImageCopy.outDirPath = FileManager.setOutDirPath().toString

    if (args.length > 0) {
      if (args(0).length > 0) {
        darknessCopy.luminanceThreshold = args(0).toInt
      }
      if (args(1).length > 0) {
        darknessCopy.inDirPath = args(1)
      }
      if (args(2).length > 0) {
        darknessCopy.outDirPath = args(2)
      }
    }
    darknessCopy.analyzeAndMove()
  }

}


