package wordcount
import common._
import mapreduce.BasicOperations

//import scala.collection.parallel.ParIterableLike.GroupBy
//import scala.collection.parallel.ParIterableLike.GroupBy

class Processing {
   
  /**********************************************************************************************
   *
   *                          Aufgabe 1
   *   
   *   
   * Vervollständigen   Sie   die   Funktionen   getWords,   getAllWords   und
	 * countTheWords, die das Zählen von Wörtern innerhalb eines Texts ermöglichen sollen.

   *********************************************************************************************
  */
  def getWords(line:String):List[String]={
    /*
     * Extracts all words in a line
     * 
     * 1. Removes all characters which are not letters (A-Z or a-z)
     * 2. Shifts all words to lower case
     * 3. Extracts all words and put them into a list of strings
     */
    
    val t= line.toLowerCase.replaceAll("[^a-z]", " ")
    val wl= t.split(" +").toList;
    return wl
  } 
  
  def getAllWords(l:List[(Int,String)]):List[String]={
    
    /*
     * Extracts all Words from a List containing tupels consisting
     * of a line number and a string
     * The Words should be in the same order as they occur in the source document
     * 
     * Hint: Use the flatMap function
     */
    val result = l.flatMap(x => List(x._2))
    val resultGetWords = this.getWords(result.mkString(","))
    return resultGetWords
  }
  
  def countTheWords(l:List[String]):List[(String,Int)]={
 
    /*
     *  Gets a list of words and counts the occurences of the individual words
     */
    val map = l.map(word => (word,1))
    val group = map.groupBy(word => word)
    
    //also wenn map über eine map geht ist x immer (key,value)
    val map_result = group.map(x => (x._1._1,x._2.length))
    print(map_result.toList)
    return map_result.toList;
    
  }
  
  /**********************************************************************************************
   *
   *                          Aufgabe 2
   *   
   *********************************************************************************************
  */

  // FOLDLEFT von Scala
//  override def foldLeft[B](z: B)(f: (B, A) => B): B = {
//  var acc = z
//  var these = this
//  while (!these.isEmpty) {
//    acc = f(acc, these.head)
//    these = these.tail
//  }
//  acc
//}
  
  
  
  def mapReduce[S,B,R](mapFun:(S=>B), 
      redFun:(R,B)=>R, 
      base:R, 
      l:List[S]):R = {l.map(mapFun).foldLeft(base)(redFun)}
  
  def countTheWordsMR(l:List[String]):List[(String,Int)]= {
    return this.mapReduce(mapFun, (map1 : scala.collection.immutable.Map[String,Int],elementNow: (String,Integer) ) => if(map1.getOrElse(elementNow._1, 0) == 0) map1.updated(elementNow._1,1.toInt) else map1.updated(elementNow._1, map1.get(elementNow._1) match{ case Some(x2) => x2 + 1}),Map.empty[String, Int], l).toList.sorted
// return this.mapReduce((w=>(w,1)), (map,tupel) => map-get(t._1) match {case None => map ++ Map(r._1 -> 1); case Some(e) => map ++ Map(r._1 -> (e+1))}, Map[String,Int](),l).toList
    
    // ohne toList: return this.mapReduce((x=>(x,1)), Base : List(("",0)))
    
//    (elementNow.x_1 -> 1)
//    type mismatch; found : (Map[String,Int], String) ⇒ scala.collection.immutable.Map[String,Int] required: (scala.collection.immutable.Map[String,Int], (String, Integer)) ⇒ 
//      scala.collection.immutable.Map[String,Int]
    
    
    
  }
  
  def mapFun(word : String) : (String,Integer) = {
    return (word,1)
  }
  


  
  
  /**********************************************************************************************
   *
   *                          Aufgabe 3
   *   
   *********************************************************************************************
  */      
  
    def getAllWordsWithIndex(l:List[(Int,String)]):List[(Int,String)]= {
      return l.map(x => (x._1,this.getWords(x._2))).filter(x=> x._2.length > 1).map(x => x._2.map(y => (x._1,y))).flatten.sorted
      // bei map muss am anfang immer x stehen nicht z.B. (x1,x2) weil man die Elemente eines Tupels ansprechen möchte!!!!!!!!!!!!!!!!!
      // gleiches für filter, group
    }
    
    // er: l.flatMap(X=>getWords(X._2).map (Y=>(X._1,Y)))
    
    
  
   def createInverseIndex(l:List[(Int,String)]):Map[String,List[Int]]={
     
     val group = l.groupBy(x => x._2).map(x => (x._1,(x._2).map(y => y._1)))
     println("hi")
     println("group\n " + group)
     return group
   } 
   
   //    def createInverseIndex(l:List[(Int,String)]):Map[String,List[Int]]={
//     
//      val groups = l.groupBy(x=>X._2)
   //  for (word <- groups) yield (word._1, word._2.map(X=>X._1))
//   } 
  
   def andConjunction(words:List[String], invInd:Map[String,List[Int]]):List[Int]={
     
//     val result = words.map(x => (x,invInd.getOrElse(x, Nil)))
     val result = words.groupBy(word => word).map(x => (invInd.getOrElse(x._1, Nil).sorted)).toList//.sorted,x._1))
     // result = Liste der Zeilen der einzelnen Wörter
     val tmp = result.flatten.groupBy(x => x).keySet.toList//.toList
     val result2 = tmp.filter(x=> !(result.map(y=> y.contains(x))).contains(false)).sorted
//     val result2 = tmp.filter(x => (result.foldLeft(List())(y : List[Integer] => if (y.contains(x)) true else false).contains(true)))
     //tmp = Menge aller Zeilen
     //val result2 = result.keySet//.flatten.groupBy(x=>x)
     
    //     val (key, value) = result.head
////     val checkOrigLen = result.keySet.toList.length
////     val checkTestLen = result.keySet.filter(x=> x.equals(key)).toList.length
////     if(checkorigLen == checkTestLen)
////       return result
////     else
////       return Nil
     //.map(x => (x._1,invInd.getOrElse(x, Nil)))              (contains ist 1 mögliches Wort und nicht irgendwie ne Methode)
//     output von group by (word => words) auf List ist -> Map(contains -> List(contains), this -> List(this), test -> List(test)), deshalb groupBy(word => (word,Nil)) IST FALSCH : ERGEBNIS IST DANN  -> Map((this,List()) -> List(this), (test,List()) -> List(test),
     // Ergebnis: bei group by IMMER einfach word => word lassen ... ist eig besser 
     println("result1: " + result)


     println("tmp: " + tmp)
     println("result2: " + result2)

     return result2
   }
   
   // words.flatMap(x=>invInd.getOrElse(x,List()).filter(x=>words.forall(p=> invInd.getOrElse(p,List())).contains(x))).distinct)
   
   // reduceLeft ( & ) -> bedeuted intersection d.h. muss man nicht selber programmieren
   // es geht auch list.union (zusammenfügen)
   
  
   def orConjunction(words:List[String], invInd:Map[String, List[Int]]):List[Int]={
     
     val result = words.map(x => invInd.getOrElse(x, Nil)).flatten.groupBy(x => x).map(v => v._1).toList.sorted
     println(result)
     return result
   }
   // .diff wirft alle doppelten Einträge raus
}


object Processing{
  
  def getData(filename:String):List[(Int,String)]={

    val url= getClass.getResource("/"+filename).getPath
    val src = scala.io.Source.fromFile(url)
    val iter = src.getLines()
    var c = -1
    val result= (for (row <- iter) yield {c=c+1;(c,row)}).toList
    src.close()
    result
  }
}