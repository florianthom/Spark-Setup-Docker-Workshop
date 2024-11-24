package wordcount

/**
 * @author hendrik
 */
object App {


  def main(args: Array[String]) = {

        val sentiAnalyse= new Sentiments("AFINN-112.txt")
        val book= sentiAnalyse.getDocumentGroupedByCounts("GoneWithTheWind.txt", 20000)
        val data= sentiAnalyse.analyzeSentiments(book)
        sentiAnalyse.createGraph(data)
        
        
//    val proc = new Processing()
//    val data = Processing.getData("MobyDickC1.txt")
//     val s1="This 88 is! a,Test! The result !!!should be: 8 Words"
//     val words_s1= List("a", "be", "is", "result", "should", "test", "the", "this", "words")
//     val s2="This is another test. It contains a lot of words which are also in string 1."
//     val words_s2= List("a", "also", "another", "are", "contains", "in", "is", "it", "lot", "of", "string", "test", "this", "which", "words")
//     val test_list= List((0,s1),(1,""),(2,s2),(3,""))
//    
    
//
//    val data2 = sentiAnalyse.analyzeSentiments(sentiAnalyse.getDocumentGroupedByCounts("MobyDickC1.txt",200))
//    println("data2: ", data2)
//    print()
    
//           val result= proc.countTheWordsMR(proc.getAllWords(test_list))
//           println(result)
//           println()


  }

}