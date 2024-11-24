val data : List[(String,String,Double)] = List(("Peter", "DB", 2.0), ("Marie", "Betriebssysteme", 1.3), 
            ("Werner", "OS", 1.7),("Nami", "Algo", 1.7),("Nami", "gucken", 1.7),("Hofer", "gucken", 2.0))


val predefinedList : List[Int] = List(1,1,2,3,3,3,4,4,5,5,5)
def convertIntoString(inputList : List[Int]) : String = {
  inputList match {
    case x :: Nil => ""
    case x :: xs if (x==xs.head) => x + convertIntoString(xs)
    case x :: xs => convertIntoString(xs)
  }
}
convertIntoString(predefinedList)



//2aa - Liste[(Name,Fach,Note)] into Liste[(Fach,Note)]
def convertToFachNote(list : List[(String,String,Double)]) = {
  for(element <- list) yield (element._2,element._3)
}
convertToFachNote(data)


//Map mit Person als Schlüssel und Fächer und Note als value
// map über Map returned wieder eine Map
def convertToGroupedByPersonInList(list : List[(String,String,Double)]) = list
	.groupBy(a=>a._1)
	.map(b=>(b._1,b._2.map(c=>(c._2,c._3))))
	.toList
convertToGroupedByPersonInList(data)


//gleiches wie 2c nur mit einer Map
def convertToGroupedByPersonInMap(list : List[(String,String,Double)]) = list
	.groupBy(a=>a._1)
	.map(b=>(b._1,b._2.map(c=>(c._2,c._3))))
convertToGroupedByPersonInMap(data)


//2c - Durchschnitt jedes Fachen in einer Map Speichert: Map("OS" -> 1,7)
def mapFromFachToAverage(list : List[(String,String,Double)]) = list
	.groupBy(a=>a._2)
	.map(b=>(b._1,
           1.toDouble/b._2.length * b._2.foldLeft(0.0)((c:Double,d:(String,String,Double))=>c+d._3))
      )
mapFromFachToAverage(data)


//with reduce -> geht nicht, da wir Datentypen verändern müssten von Tupel to Int
//def mapFromFachToAverageWithReduce(list : List[(String,String,Double)]) = list
//.groupBy(a=>a._2)
//.map(b=>(b._1,1.toDouble/b._2.length * b._2.reduce((c:(String,String,Double),d:(String,String,Double))=>c._3+d._3)))
//mapFromFachToAverageWithReduce(data)


//ermitteln von: wie oft war Person über oder unter dem Durchschnitt in Form: (Person, (Anzahl_drüber, Anzahl_drunter))
def OverOrUnderDurchschnitt(list : List[(String,String,Double)]) = {
	val avg = mapFromFachToAverage(list)
	list
  	.groupBy(a=>a._1)
  	.map(b=>(b._1,b._2
            .foldLeft((0,0))((c:(Int,Int),d:(String,String,Double))=>if(d._3<=(avg
                                                                               .get(d._2) match {case Some(x) => x})) (c._1+1,c._2) else (c._1,c._2+1))
            ))
        
}
OverOrUnderDurchschnitt(data)


// Stream gegeben
def nat(n: Int): Stream[Int] = {println(n); n} #:: nat(n+1) 
val s = nat(1)
s.take(4)

// a was wird ausgegeben? -> Stream(1,?) aus

// Aufgabe Stream erzeugen: (1,1) (2, 3) (3, 6) (4, 10) (5, 15)

def createStream(): Stream[(Int, Int)] = {
  // Bei der Erstellug eines Streams braucht man immer eine Bildungsvorschrift, die eigentlich
  // immer eine Funktion ist. D.h. wenn unsere Hauptfunktion keine Argumente
  // bekommen darf, brauchen wir eine HelperFunktion(mit Parameter), die unsere
  // Bildungsvorschrift ist
	def helper(a: Int, b: Int): Stream[(Int, Int)] = (a,b) #:: helper(a+1,a+1+b)
	helper(1, 1)
}
createStream().take(4).toList


val data : List[(String,String,Double)] = List(("Peter", "DB", 2.0), ("Marie", "Betriebssysteme", 1.3), 
            ("Werner", "OS", 1.7),("Nami", "Algo", 1.7),("Nami", "gucken", 1.7),("Hofer", "gucken", 2.0))


// Länge eines Vektors bestimmen
//a ja gibt es -> fold ist nicht assoziativ d.h. unser Aggregationselement (hier 0.0) wird nicht immer in das bestehende x rein kopiert, das heißt
	// die Bildungsvorschrift x+ y*y ist generell richtig, aber manchmal kann auch unser Bas
def calculateVectorlength(list: List[Double]) =
	math.sqrt(list.fold(0.0)((x,y) => x + y*y))
calculateVectorlength(List(10,20,30))

def calculateVectorlengthWithAggregate(list: List[Double]) =
	math.sqrt(list.aggregate(0.0)((a,b) => a+b*b,(c,d)=>c+d))
calculateVectorlengthWithAggregate(List(10,20,30))
