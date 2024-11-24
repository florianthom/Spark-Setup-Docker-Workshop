package wikiplag

import org.apache.spark.util.AccumulatorV2

class StopWordAccumulator extends AccumulatorV2[(String,Long), Map[String,List[Long]]]{
  
  def reset:Unit= ???
  def add(el:(String,Long))= ???
  def merge(other:AccumulatorV2[(String,Long), Map[String,List[Long]]]):Unit= ???
  def value:Map[String,List[Long]]= ???
  def copy:AccumulatorV2[(String,Long), Map[String,List[Long]]]= ???
  def isZero:Boolean= ???
}