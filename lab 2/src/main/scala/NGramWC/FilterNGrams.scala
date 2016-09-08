package main.scala.NGramWC

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by digvijayky on 9/7/2016.
  */
object FilterNGrams {
  def main(args: Array[String]): Unit = {
    val n = 4
    val filterWord = "MIT"

    System.setProperty("hadoop.home.dir","C:\\winutils")
    System.setProperty("spark.executor.memory","8G")
    val conf = new SparkConf().setAppName("SparkSentenceCount").setMaster("local[*]").set("spark.executor.memory","8g").set("spark.hadoop.validateOutputSpecs", "false") //Overwrite output files
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sc = new SparkContext(conf)
    val textFile = sc.textFile("lab 2/data/input/sentencesFile.txt").toLocalIterator;

    println("%" * 100 + "\n" + s"Generating ${n}-grams from the input file" + "\n" + "%" * 100 + "\n")
    val ngrams = textFile
      .map(_.split("\\s").toList)
      .flatMap(_.sliding(n))
      .filter(_.size == n)
      .foreach(println)
  }
}
