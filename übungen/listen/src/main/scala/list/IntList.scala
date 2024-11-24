package list

abstract class IntList {

  /**
   * hier mit definiert man Methoden, deren Implementierung man in die Unterklassen auslagert
   */
  def isEmpty:Boolean
  def head:Int
  def tail:IntList
  def nth(index:Int):Int
  def contains(elem:Int):Boolean
  def insert(elem:Int):IntList
  def insertSorted(elem:Int):IntList
  def delete(elem:Int):IntList
  /*={
    this match {
      case Empty => Empty
      case Cons(h,t) => if (elem==h) tail else Cons(h,tail.delete(elem))
    }
  }
  */

	def insertionSortI:IntList
	
	
	def deleteAll(elem:Int):IntList =  {
    this match {
      case Empty => Empty
      case Cons(h,t) if (h==head) => tail.deleteAll(elem)
      case Cons(h,t) if (h!=head) => new Cons(h,t.deleteAll(elem))
    }
    
  }
  
	/**
	 * Mit dem Question-Mark markiert man, dass die Methoden noch innerhalb dieser Abstrakten-Klasse definiert werden sollen
	 */
  def insertSO(elem:Int):IntList= ???
  
  def insertionSort:IntList= ???
  
  def prefix(l:IntList):IntList= ??? 
  
  def flip:IntList= ???
}