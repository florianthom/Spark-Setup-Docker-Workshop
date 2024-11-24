import scala.collection.mutable.HashMap

object Uebung3 {
  println("Welcome to the Scala worksheet")
  
  def quersumme(zahl:Int):Int = {
  	if(zahl<9) zahl;
  	else zahl % 10 + quersumme(zahl/10)
  }
  
  quersumme(492);
  
  def fibo(x:Int):Int = {
  	if(x <= 0) 0
  	else if(x==1) 1
  	else fibo(x-1) + fibo(x-2)
  }
  fibo(50)
  

  
  def fibo100(x:Int):Int={
  	val hashMap1: HashMap[BigInt,BigInt] = HashMap()
  	x match {
  	case 0 => 0//hashMap1+=(0 -> 0)
  	case 1 => 1//hashMap1+=(1 -> 1)
  	case y => fibo100Hidden(y)
  	}
  	
  	
  	
  }
  
  def fibo100Hidden(x:Int):Int{
  	hashMap1+=(1 -> 1)
  	
  }
  
}