package list

case class Cons (val head:Int, val tail:IntList) extends IntList{
  def isEmpty=false
  
  def nth(index:Int):Int= index match{
    case 0 => head
    case i => tail.nth(i-1)
  }
  
  def contains(elem:Int):Boolean= elem match{
    case y if (y==head) => true
    case _ => tail.contains(elem)
  }

  def insert(X:Int):IntList= new Cons(X,this)
  	
  def insertSorted(elem:Int):IntList=  elem match {
    
    case _ if (head>=elem) => new Cons(elem, new Cons(head,tail))
    case _ => new Cons(head, tail.insertSorted(elem))
  }

  def delete(elem:Int):IntList= 
    if (elem==head) tail else new Cons(head, tail.delete(elem))


  def deleteAll(elem:Int):IntList = 
    if (elem==head) tail.deleteAll(elem) 
    	else new Cons(head, tail.deleteAll(elem))
  
 def insertionSortI:IntList= 
     (tail.insertionSortI).insertSorted(head)
     
  /*------------------------------------------------------------------------------*/
  /* 													Exercise 5																					*/
  /*------------------------------------------------------------------------------*/

  
  override def map(mapFun:(Int)=>Int): IntList = {
    	  Cons(mapFun(head), tail.map(mapFun))
    	}

  override def filter(filterFun:Int=>Boolean):IntList= {
    if(filterFun(head)) Cons(head, tail.filter(filterFun)) else tail.filter(filterFun)
  }

  override def foldLeft(base:Int)(reduceFun:(Int,Int)=>Int):Int= ???
  
  override def foldRight(base:Int)(reduceFun:(Int,Int)=>Int):Int= ???
  
}