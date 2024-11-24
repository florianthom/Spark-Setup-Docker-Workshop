package wikiplag

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.broadcast.Broadcast

object WikiPlagFuns {
  
  // Write a function that finds the top X words .
  def getTopXWords(data:RDD[(Long,String,List[String])],x:Int):List[(String,Int)]= ???

  /*
   *  Write a function that extracts all word occurrences in a document by
   *      - deleting all stopwords and
   *      - extracting all word indexes
 */ 
  def extractIndexes(doc:(Long,String,List[String]),stopWords:List[String]):
      List[(String,(Long,Int))]= ???
  
  /*
   *  Write a function that creates an Inverse Index.
   *  The Inverse Index should contain all words as the key of the map
   *  and all occurrences (document id, index) that word
 */
  
  def createIndex(data:RDD[(Long,String,List[String])],stopWords:List[String]):
            Map[String,List[(Long,Int)]]= ???
    
  /*
   *  Write a function that creates an Inverse Index.
   *  Use an accumulator for counting all elements that are deleted from the document.
   *  Send a tuple (String, Long) for each delete.
 */
  def createIndexWithAccumulator(data:RDD[(Long,String,List[String])],stopWords:List[String],
      swa:StopWordAccumulator):
            Map[String,List[(Long,Int)]]= ???
  
  def extractIndexesWithAccumulator(doc:(Long,String,List[String]),stopWords:List[String],
      swa:StopWordAccumulator):
      List[(String,(Long,Int))]= ???
  
   /*
   *  Write a function that creates an Inverse Index.
   *  Use a broadcast variable for the distribution of the stop words.
 */
  def createIndexWithBroadcast(data:RDD[(Long,String,List[String])],stopWords:Broadcast[List[String]]):
            Map[String,List[(Long,Int)]]= ???
            
  def extractIndexesWithBroadcast(doc:(Long,String,List[String]),stopWords:Broadcast[List[String]]):
      List[(String,(Long,Int))]= ???  
  
  /*
   *  Write a function that creates an Inverse Index.
   *  Use an accumulator for counting all elements that are deleted from the document.
   *  Send a tuple (String, Long) for each delete.
   *  Use the accumulator only in an action.
 */
  
  def createIndexWithAccumulatorAction(data:RDD[(Long,String,List[String])],stopWords:List[String],
      swa:StopWordAccumulator): Map[String,List[(Long,Int)]]= ???
}
