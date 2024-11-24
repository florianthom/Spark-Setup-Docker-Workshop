object test{

	/*
		Stream der Tupel ausgibt mit
		1) Counter
		2) Summe der bisherigen Zahlen bis zum Counter
	*/
	
	/* Als Stream direkt */
 	val s = Stream.from(0).map(x => (x, 0.to(x).sum))
                                                  //> s  : scala.collection.immutable.Stream[(Int, Int)] = Stream((0,0), ?)
	s.take(101).toList.takeRight(10)          //> res0: List[(Int, Int)] = List((91,4186), (92,4278), (93,4371), (94,4465), (9
                                                  //| 5,4560), (96,4656), (97,4753), (98,4851), (99,4950), (100,5050))
	
 /* Als Funktion */
	def s2: Stream[(Int, Int)] = {
		def helper(a: Int, b: Int): Stream[(Int, Int)] = Stream.cons((a, b), helper(a + 1, a + 1 + b))
		helper(0, 0)
	}                                         //> s2: => Stream[(Int, Int)]
 
 	s2.take(101).toList.takeRight(10)         //> res1: List[(Int, Int)] = List((91,4186), (92,4278), (93,4371), (94,4465), (9
                                                  //| 5,4560), (96,4656), (97,4753), (98,4851), (99,4950), (100,5050))
 
 	/* Als reine Streamfunktion */
 	def s3(n: Int = 0): Stream[(Int, Int)] = Stream.cons((n, 0.to(n).sum), s3(n + 1))
                                                  //> s3: (n: Int)Stream[(Int, Int)]
 	
 	s3().take(101).toList.takeRight(10)       //> res2: List[(Int, Int)] = List((91,4186), (92,4278), (93,4371), (94,4465), (9
                                                  //| 5,4560), (96,4656), (97,4753), (98,4851), (99,4950), (100,5050))
 
 	/*
 		Kleine Map Funktion
 	*/
	def mapSource[A, B](f: A => B, seq: Seq[A]): Seq[B] = for( x <- seq ) yield f(x)
                                                  //> mapSource: [A, B](f: A => B, seq: Seq[A])Seq[B]
	
	val ones = List(1, 1, 1, 1, 1)            //> ones  : List[Int] = List(1, 1, 1, 1, 1)
	val twos = mapSource((f: Int) => f * 2, ones)
                                                  //> twos  : Seq[Int] = List(2, 2, 2, 2, 2)
	
	twos                                      //> res3: Seq[Int] = List(2, 2, 2, 2, 2)
	
	/*
		Ähnliche Aufgabe wie im Beleg nur mit Kursbelegungen und Noten (vereinfacht)
	*/
	
	val belegungen = List(
		("Anton", "Algorithmen", 2.4),
		("Berta", "Betriebsysteme", 3.4),
		("Berta", "Algorithmen", 2.0),
		("Charlotte", "Computergrafik", 2.6),
		("Charlotte", "Betriebsysteme", 1.0),
		("Dora", "Datenbanken", 2.7),
		("Dora", "Algorithmen", 3.0)
	)                                         //> belegungen  : List[(String, String, Double)] = List((Anton,Algorithmen,2.4)
                                                  //| , (Berta,Betriebsysteme,3.4), (Berta,Algorithmen,2.0), (Charlotte,Computerg
                                                  //| rafik,2.6), (Charlotte,Betriebsysteme,1.0), (Dora,Datenbanken,2.7), (Dora,A
                                                  //| lgorithmen,3.0))
	
	/* Aus den Aufzeichnungen */
	
	/*
		All Grades of a Lecture
	*/
	
	def allGrades(grades: List[(String, String , Double)]): List[(String, List[Double])] = grades
		.map(f => (f._2, f._3))
		.groupBy(_._1)
		.mapValues(f => f.map(m => m._2))
		.toList                           //> allGrades: (grades: List[(String, String, Double)])List[(String, List[Doubl
                                                  //| e])]
	
	allGrades(belegungen)                     //> res4: List[(String, List[Double])] = List((Datenbanken,List(2.7)), (Betrieb
                                                  //| systeme,List(3.4, 1.0)), (Algorithmen,List(2.4, 2.0, 3.0)), (Computergrafik
                                                  //| ,List(2.6)))
	
	def allGradesFor(grades: List[(String, String , Double)]): List[(String, List[Double])] = {
		val grouped = for(x <- grades) yield {
			val g = for(y <- grades; if x._2 == y._2) yield { y._3 }
			(x._2, g)
		}
		
		def distinct(list: List[(String, List[Double])]): List[(String, List[Double])] = {
			if (list.isEmpty)
				return list
				
			val h = list.head
			h :: distinct(for(x <- list; if x._1 != h._1) yield { x })
		}
		
		distinct(grouped)
	}                                         //> allGradesFor: (grades: List[(String, String, Double)])List[(String, List[Do
                                                  //| uble])]
	
	allGradesFor(belegungen)                  //> res5: List[(String, List[Double])] = List((Algorithmen,List(2.4, 2.0, 3.0))
                                                  //| , (Betriebsysteme,List(3.4, 1.0)), (Computergrafik,List(2.6)), (Datenbanken
                                                  //| ,List(2.7)))
	
	/*
		Average Grades Per Lecture
	*/
	def avgGradesPerLecture(grades: List[(String, String , Double)]): List[(String, Double)] = grades
		.map(f => (f._2, f._3))
		.groupBy(_._1)
		.mapValues(f => f.map(m => m._2))
		.map(f => (f._1, f._2.sum / f._2.length))
		.toList                           //> avgGradesPerLecture: (grades: List[(String, String, Double)])List[(String, 
                                                  //| Double)]
		
	avgGradesPerLecture(belegungen)           //> res6: List[(String, Double)] = List((Datenbanken,2.7), (Betriebsysteme,2.2)
                                                  //| , (Algorithmen,2.466666666666667), (Computergrafik,2.6))
	
	def avgGradesPerLectureFor(grades: List[(String, String , Double)]): List[(String, Double)] = {
		val grouped = for(x <- grades) yield {
			val g = for(y <- grades; if x._2 == y._2) yield { y._3 }
			(x._2, g)
		}

		def distinct(list: List[(String, List[Double])]): List[(String, List[Double])] = {
			if (list.isEmpty)
				return list
				
			val h = list.head
			h :: distinct(for(x <- list; if x._1 != h._1) yield { x })
		}
		
		val d = distinct(grouped)
		
		for(x <- d) yield { (x._1, x._2.sum / x._2.length) }
	}                                         //> avgGradesPerLectureFor: (grades: List[(String, String, Double)])List[(Strin
                                                  //| g, Double)]
	
  avgGradesPerLectureFor(belegungen)              //> res7: List[(String, Double)] = List((Algorithmen,2.466666666666667), (Betri
                                                  //| ebsysteme,2.2), (Computergrafik,2.6), (Datenbanken,2.7))
                                                  
  def avgGradesPerLectureInRespectToAllGrades(grades: List[(String, String , Double)]): List[(String, Double)] = {
  	val all = allGrades(grades)
  	
  	for(x <- all) yield { (x._1, x._2.sum / x._2.length) }
  }                                               //> avgGradesPerLectureInRespectToAllGrades: (grades: List[(String, String, Dou
                                                  //| ble)])List[(String, Double)]
  
  avgGradesPerLectureInRespectToAllGrades(belegungen)
                                                  //> res8: List[(String, Double)] = List((Datenbanken,2.7), (Betriebsysteme,2.2)
                                                  //| , (Algorithmen,2.466666666666667), (Computergrafik,2.6))
  
  /*
  	Is a Person better then the Average of the Lecture
  */
  def isPersonBetterThanAvg(grades: List[(String, String , Double)]): List[(String, String, Boolean)] = grades
  	.map(f => (f._1, f._2, f._3 < avgGradesPerLecture(grades).filter(p => p._1 == f._2).head._2))
                                                  //> isPersonBetterThanAvg: (grades: List[(String, String, Double)])List[(String
                                                  //| , String, Boolean)]
                                                  
 	
  	
  isPersonBetterThanAvg(belegungen)               //> res9: List[(String, String, Boolean)] = List((Anton,Algorithmen,true), (Ber
                                                  //| ta,Betriebsysteme,false), (Berta,Algorithmen,true), (Charlotte,Computergraf
                                                  //| ik,false), (Charlotte,Betriebsysteme,true), (Dora,Datenbanken,false), (Dora
                                                  //| ,Algorithmen,false))
   
  def isPersonBetterThanAvgFor(grades: List[(String, String , Double)]): List[(String, String, Boolean)] = {
  	val avg = avgGradesPerLectureFor(grades)
  	
  	for(x <- grades) yield {
  		val avgGrade = (for(y <- avg; if x._2 == y._1) yield { x._3 }).head
  		(x._1, x._2, x._3 < avgGrade)
  	}
  }                                               //> isPersonBetterThanAvgFor: (grades: List[(String, String, Double)])List[(Str
                                                  //| ing, String, Boolean)]
  
  isPersonBetterThanAvg(belegungen)               //> res10: List[(String, String, Boolean)] = List((Anton,Algorithmen,true), (Be
                                                  //| rta,Betriebsysteme,false), (Berta,Algorithmen,true), (Charlotte,Computergra
                                                  //| fik,false), (Charlotte,Betriebsysteme,true), (Dora,Datenbanken,false), (Dor
                                                  //| a,Algorithmen,false))
     
	/* Der String soll die doppelten Elemente eines in der ... n - (nur ab 2 gleiche) Elemente hintereinander, so soll das Element genau n-1 mal im Ergebnis vorkommen */
	
	val t = "DDDaaas ist eeeiiiin    ttest"   //> t  : String = DDDaaas ist eeeiiiin    ttest
	
	def removeDuplicates(text: String): String = {
		def helper(i: Int, c: Int, l: String): String = {
			if(i == text.length - 1)
				l + text.charAt(i)
			else if(text.charAt(i) == text.charAt(i + 1))
				helper(i + 1, c + 1, l + text.charAt(i))
			else if (c > 1)
				helper(i + 1, 0 , l)
			else
				helper(i + 1, 0, l + text.charAt(i))
		}
		
		helper(0, 0, "")
	}                                         //> removeDuplicates: (text: String)String
	
	removeDuplicates(t)                       //> res11: String = DDaas ist eeiiin   ttest
  
}