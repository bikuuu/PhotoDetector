package com.szymonBikowski

import java.awt.image.BufferedImage
import java.io.{File, FileNotFoundException}
import java.nio.file.{Files, Path, Paths, StandardCopyOption}

import javax.imageio.ImageIO

object FileManager {

  def image(path: String): BufferedImage = {
    val file = this.file(path)
    if (file.isDirectory) {
      throw new FileNotFoundException("Image file must be file.")
    }
    ImageIO.read(file)
  }

  def filesPaths(path: String): List[File] = {

    val file = this.file(path)
    if (!file.isDirectory) {
      throw new FileNotFoundException("File must be directory.")
    }
    file.listFiles().filter(_.isFile).toList
  }

  def deleteRecursively(file: File): Unit = {
    file.createNewFile()
    if (file.isDirectory) {
      file.listFiles.filter(_ == DarknessImageCopy.outDirPath) foreach (deleteRecursively)
    }
    if (file.exists && !file.delete && file.isFile) {
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
    }
  }

  def file(path: String): File = {
    val file = new File(path)
    if (!file.exists()) {
      throw new FileNotFoundException()
    }
    file
  }

  def currentDirectory(): String = {
    val currentDirectory = new java.io.File(".").getCanonicalPath
    currentDirectory
  }

  def setOutDirPath(): Path = {
    Paths.get(FileManager.currentDirectory(), "Out")
  }

  def setInDirPath(): Path = {
    Paths.get(FileManager.currentDirectory(), "In")
  }

  def move(file: Path, outputPath: String): Unit = {
    Files.move(Paths.get(file.toString),
      Paths.get(outputPath),
      StandardCopyOption.REPLACE_EXISTING)
  }

}
