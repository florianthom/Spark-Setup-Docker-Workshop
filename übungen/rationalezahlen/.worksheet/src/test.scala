// Scala Worksheets sind gut gut geeignet für Prototyping von Funktionen

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(131); 
  println("Welcome to the Scala worksheet");$skip(154); 
  
  
  def primHelper(zahl:Int, zaehler:Int):Boolean={
  	if(zaehler < 2) true
  	else if (zahl%zaehler==0) false
  	else primHelper(zahl,zaehler-1)
  };System.out.println("""primHelper: (zahl: Int, zaehler: Int)Boolean""");$skip(92); 
  
  def isPrim(x:Int):Boolean={
  	if(x <=2) false
  	else{
  		primHelper(x,x-1)
 	 }
  };System.out.println("""isPrim: (x: Int)Boolean""");$skip(241); 
  //isPrim(23)
  
  
  // erste Primzahl von 10001 finden
  
  
  
  // Funktion die 10 mal Hello World aussgibt
 
 	def helloWorldHelper(zaehler:Int): String = {
 	if(zaehler == 0) ""
 	else "Hello World " + helloWorldHelper(zaehler-1)
 	};System.out.println("""helloWorldHelper: (zaehler: Int)String""");$skip(73); 
 	
 	def ouputHelloWorld10Times(){
 		println(helloWorldHelper(10));
 	};System.out.println("""ouputHelloWorld10Times: ()Unit""");$skip(31); 
 	
 	ouputHelloWorld10Times();$skip(23); ;
  
  
  println("hi");$skip(21); ;
  
  println("sfd");$skip(19); ;
  println("sdfas")}
  
  

  
  
  
  
}
