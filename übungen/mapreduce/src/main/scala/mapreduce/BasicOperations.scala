package mapreduce

object BasicOperations {

  	def mapper[KeyIn ,ValueIn, KeyMOut, ValueMOut](mapFun:((KeyIn,ValueIn))=>List[(KeyMOut,ValueMOut)],
							data:List[(KeyIn, ValueIn)]):List[(KeyMOut,ValueMOut)]={
		
		data.flatMap(mapFun(_))
	}
  	
  	def sorter[KeyMOut,ValueMOut](data:List[(KeyMOut,ValueMOut)]):List[(KeyMOut,List[ValueMOut])]= {
	
		data.groupBy(_._1) mapValues(X=> X.map(_._2)) toList

	}
  	
  	def reducer[KeyMOut,ValueMOut,KeyROut, ValueROut](redFun:((KeyMOut,List[ValueMOut]))=>List[(KeyROut,ValueROut)],
							data:List[(KeyMOut,List[ValueMOut])]):List[(KeyROut, ValueROut)]={
	
		data flatMap (redFun(_))
	}
  	
  	def mapReduce[KeyIn,ValueIn,KeyMOut,ValueMOut, KeyROut, ValueROut](mapFun:((KeyIn,ValueIn))=>List[(KeyMOut, ValueMOut)],
					redFun:(((KeyMOut,List[ValueMOut]))=>List[(KeyROut,ValueROut)]), data:List[(KeyIn,ValueIn)]): List[(KeyROut,ValueROut)] = {
					
		reducer(redFun,sorter(mapper(mapFun, data)))
	}
  	
  	/* 
  	 * Wandeln Sie das WordCount-Beispiel aus der Vorlesung in die Map-Reduce-Variante um.
  	 * Die Funktion soll wie unten aufgefuehrt aufgerufen werden koennen.
  	 * 
  	 * */
  	
	def wordCount(text:List[(Int,String)]):List[(String,Int)] = ???	
	/*
	 * Schreiben Sie eine Funktion, die fuer eine Liste von Zahlen, jeweils die Prim-Teil 
	 * berechnet und aufsummiert.
	 * 
	 * So hat bspw. die 24 folgende Prim-Teiler 2,2,2,3 die Summe davon ist 9.
	 * Wenden Sie die Funktion mit MapReduce an. Ergebnis soll eine Liste von Tupeln sein,
	 * die an erster Stelle die Zahl enthaelt und an zweiter Stelle die Summe der Primteiler.
	 * 
	 */
	
	
	def primf(x:Int, teiler:Int):List[Int]= ???
	

	
	def primTeilerSumme(l:List[Int]):List[(Int, Int)]= ???	

	
	/* Schreiben Sie eine Funktion, die für eine Liste von Wörtern alle Anagramme findet
	 * Benutzen Sie dafür die MapReduce-Funktion
	 */
	
	def findAnagrams(l:List[String]):List[(String,String)] = ???
	
	
    
	def main(args: Array[String]): Unit = {
    
  	 println(wordCount(List((0,"Dies ist ein Test"),(1,"und jetzt kommt noch ein Test!"), (2,"mal schauen, ob es funktioniert"))))
  		
  		println(primf(12,2))
  		println(primTeilerSumme(List(12,24,8,36)))
  		
  		println(findAnagrams(List("otto","toto","hans","haus","heute","geist","huete","siegt","tuehe")))
  		
	}
	
	
}
