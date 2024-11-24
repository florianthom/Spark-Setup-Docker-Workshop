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
  	
  def insertSorted(elem:Int):IntList=  ???

  // Vorgehen: Liste neu erzeugen, bis man gesuchtes Element gefunden hat
  def delete(elem:Int):IntList= {
    elem match {
      case y if (y==head) => tail
      case y if (y!=head) => new Cons(head,tail.delete(y))
      case _ => this
    }
    
  }
  


 
    
  
  // Sinn: wir machen pattern matching: case (y) => (...).insert(y)
  def insertionSortI:IntList= ???    
}