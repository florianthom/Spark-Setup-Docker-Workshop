object tests {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet")
 
  import mapreduce.BasicOperations;$skip(452); 
  
  def findAnagrams(l:List[String]):List[(String,String)] = {
    val start= l map ((-1,_))
    BasicOperations.mapReduce[Int, String, String, String,String, String](
(X)=>{
	     val t= X._2.toLowerCase.replaceAll("[^a-z]", " ")
	     t.split(" ").toList.filter(Y=>Y!="").map(Z=>(Z.sorted,Z))
	},
	 (AL) => (for (x <- AL._2; y <-AL._2 if (x != y)) yield
{if (x<y) (x,y) else (y,x)}).toSet.toList,
start
     )
};System.out.println("""findAnagrams: (l: List[String])List[(String, String)]""");$skip(60); val res$0 = 


  findAnagrams(List("Die ist ein test","tset tsi","sti"));System.out.println("""res0: List[(String, String)] = """ + $show(res$0));$skip(68); val res$1 = 
  findAnagrams(List("Die","ist", "ein","test","tset", "tsi","sti"));System.out.println("""res1: List[(String, String)] = """ + $show(res$1))}
}
