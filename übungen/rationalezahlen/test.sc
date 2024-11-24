// Scala Worksheets sind gut gut geeignet für Prototyping von Funktionen

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  def primHelper(zahl:Int, zaehler:Int):Boolean={
  	if(zaehler < 2) true
  	else if (zahl%zaehler==0) false
  	else primHelper(zahl,zaehler-1)
  }                                               //> primHelper: (zahl: Int, zaehler: Int)Boolean
  
  def isPrim(x:Int):Boolean={
  	if(x <=2) false
  	else{
  		primHelper(x,x-1)
 	 }
  }                                               //> isPrim: (x: Int)Boolean
  //isPrim(23)
  
  
  // erste Primzahl von 10001 finden
  
  
  
  // Funktion die 10 mal Hello World aussgibt
 
 	def helloWorldHelper(zaehler:Int): String = {
 	if(zaehler == 0) ""
 	else "Hello World " + helloWorldHelper(zaehler-1)
 	}                                         //> helloWorldHelper: (zaehler: Int)String
 	
 	def ouputHelloWorld10Times(){
 		println(helloWorldHelper(10));
 	}                                         //> ouputHelloWorld10Times: ()Unit
 	
 	ouputHelloWorld10Times();                 //> Hello World Hello World Hello World Hello World Hello World Hello World Hell
                                                  //| o World Hello World Hello World Hello World 
  
  
  println("hi");                                  //> hi
  
  println("sfd");                                 //> sfd
  println("sdfas")                                //> sdfas
  
  

  
  
  
  
}