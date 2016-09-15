package main.scala.videoAnalysis

import scala.io.Source
/**
  * Created by digvijayky on 9/13/2016.
  */
object exampleREST {

  def main(args:Array[String]){

  }
  def get(url:String)=scala.io.Source.fromURL(url).mkString


  get("https://www.youtube.com/watch?v=FvPsBd14OG0")
}
