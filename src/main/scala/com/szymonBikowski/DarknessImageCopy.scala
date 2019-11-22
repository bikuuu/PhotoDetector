package com.szymonBikowski

import java.nio.file.Paths

object DarknessImageCopy {

  var inDirPath: String = inDirPath
  var outDirPath: String = outDirPath
  var luminanceThreshold: Int = DarknessAnalyzer.defaultLuminanceThreshold
  var darknessFileNameThreshold: Int = NameGenerator.defaultDarknessThreshold

  def analyzeAndMove() {
    FileManager.filesPaths(inDirPath).foreach((file) => {
      val darkness = DarknessAnalyzer.darknessPercentage(FileManager.image(file.getPath), luminanceThreshold)
      val name = NameGenerator.generate(darkness, file.getName, darknessFileNameThreshold)
      val path = Paths.get(
        outDirPath,
        name
      ).toString
      FileManager.deleteRecursively(FileManager.file(outDirPath))
      FileManager.move(file.toPath, path)
    })
  }

}

