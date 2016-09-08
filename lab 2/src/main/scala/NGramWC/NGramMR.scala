package main.scala.NGramWC

/**
  * Created by digvijayky on 9/7/2016.
  */

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object NGramMR {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir","C:\\winutils")
    System.setProperty("spark.executor.memory","8G")
    val conf = new SparkConf().setAppName("SparkSentenceCount").setMaster("local[*]").set("spark.executor.memory","8g").set("spark.hadoop.validateOutputSpecs", "false") //Overwrite output files

    val sc = new SparkContext(conf)
    val textFile = sc.textFile("lab 2/data/input/sentencesFile.txt").toLocalIterator;

    val ngrams = textFile
      .map(_.split("\\s").toList)
      .flatMap(_.sliding(3))
      .filter(_.size==3)
      .foreach(println)
  }
}

