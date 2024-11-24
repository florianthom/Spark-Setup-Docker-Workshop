import scala.collection.mutable.HashMap

object Uebung3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(101); 
  println("Welcome to the Scala worksheet");$skip(100); 
  
  def quersumme(zahl:Int):Int = {
  	if(zahl<9) zahl;
  	else zahl % 10 + quersumme(zahl/10)
  };System.out.println("""quersumme: (zahl: Int)Int""");$skip(21); val res$0 = 
  
  quersumme(492);System.out.println("""res0: Int = """ + $show(res$0));$skip(98); ;
  
  def fibo(x:Int):Int = {
  	if(x <= 0) 0
  	else if(x==1) 1
  	else fibo(x-1) + fibo(x-2)
  };System.out.println("""fibo: (x: Int)Int""");$skip(11); val res$1 = 
  fibo(50);System.out.println("""res1: Int = """ + $show(res$1));$skip(220); 
  

  
  def fibo100(x:Int):Int={
  	val hashMap1: HashMap[BigInt,BigInt] = HashMap()
  	x match {
  	case 0 => 0//hashMap1+=(0 -> 0)
  	case 1 => 1//hashMap1+=(1 -> 1)
  	case y => fibo100Hidden(y)
  	}
  	
  	
  	
  };System.out.println("""fibo100: (x: Int)Int""");$skip(65); 
  
  def fibo100Hidden(x:Int):Int{
  	hashMap1+=(1 -> 1)
  	
  };System.out.println("""fibo100Hidden: (x: Int)Int""")}
  
}
