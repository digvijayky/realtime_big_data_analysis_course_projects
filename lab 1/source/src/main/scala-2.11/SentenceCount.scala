/**
  * Created by digvijayky on 8/30/2016.
  */

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object SentenceCount {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir","C:\\winutils")
    System.setProperty("spark.executor.memory","8G")
    val conf = new SparkConf().setAppName("SparkSentenceCount").setMaster("local[*]").set("spark.executor.memory","12g").set("spark.hadoop.validateOutputSpecs", "false") //Overwrite output files

    val sc = new SparkContext(conf)
    val textFile: RDD[String] = sc.textFile("data/input/sentencesFile.txt")

    val sortedSentences: Unit =textFile.map(s=>(s,1)).reduceByKey((a, b)=>a+b).sortByKey().coalesce(1).saveAsTextFile("data/output/sortedSentences.txt")

    val totalNumSentences=textFile.map(line => line).count()
    println(s"There are $totalNumSentences sentences in the input file")

  }
}

