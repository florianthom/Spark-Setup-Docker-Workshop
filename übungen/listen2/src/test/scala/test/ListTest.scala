package test

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import list._

@RunWith(classOf[JUnitRunner])
class ListTest extends FunSuite {

  
	trait TestLists{ 

	  val l1= Empty.insert(4).insert(5).insert(2).insert(3).insert(9)
	  val l2= Empty.insert(4).insert(5).insert(2).insert(3).insert(9).insert(11)
	  val l3= Empty.insert(4).insert(5).insert(3).insert(9).insert(11)
 	  val l4= Empty.insert(11).insert(9).insert(5).insert(4).insert(3).insert(2)
 	  val l5= Empty.insert(11).insert(9).insert(8).insert(5).insert(4).insert(3).insert(2)
 	  val l5_rev= Empty.insert(2).insert(3).insert(4).insert(5).insert(8).insert(9).insert(11)
 	  val l6= Empty.insert(2).insert(9).insert(8).insert(4).insert(5).insert(3).insert(11)
 	  val l1_double= Empty.insert(8).insert(10).insert(4).insert(6).insert(18)
 	  val l1_even = Empty.insert(4).insert(2)
 	  val l1_me= Empty.insert(12).insert(6)
	}
	
	test("toString Method") {
	  
		new TestLists{
    	
			assert("Cons(9,Cons(3,Cons(2,Cons(5,Cons(4,Empty)))))"===l1.toString)
		}
	}

	test("insert Method"){
	  
	  new TestLists{
	    
	    assert(l2===l1.insert(11))
	  }
	}
	
	test("insertS Method"){
	  
	  new TestLists{
	    
	    assert(l5===l4.insertSorted(8))
	  }
	}
	
	test("insertSO Method"){
	  
	  new TestLists{
	    
	    assert(l5===l4.insertSO(8))
	  }
	}
	
	test("delete Method"){
	  
	  new TestLists{
	    
	    assert(l3===l2.delete(2))
	  }
	}
	
	test("insertsertionSort Method"){
	  
	  new TestLists{
	    
	    assert(l5===l6.insertionSort)
	  }
	}
	
	test("map - Method"){

	   new TestLists{
	    
	    assert(l1_double===l1.map(x=>2*x))
	  }
  }
	
  test("mapInside - Method "){

	   new TestLists{
	    
	    assert(l1_double===l1.mapInside(x=>2*x))
	  }
  }
  
  test("filter - Method"){

	   new TestLists{
	    
	    assert(l1_even===l1.filter(x=>x%2==0))
	  }
  }
	
  test("filterInside - Method "){

	   new TestLists{
	    
	    assert(l1_even===l1.filterInside(x=>x%2==0))	  }
  }
  
  test("foldLeft - Method"){

	   new TestLists{
	    
	    assert(l1.foldLeft(0)((X,Y)=>X-Y)== -23)
	  }
  }
  
  test("foldLeftInside - Method"){

	   new TestLists{
	    println(l1)
	       assert(l1.foldLeftInside(0)((X,Y)=>X-Y)== -23)
	  }  
  }
  
  test("foldRight - Method"){

	   new TestLists{
	    
	    assert(l1.foldRight(0)((X,Y)=>X-Y)==7)
	  }
  }
  
  test("foldRightInside - Method"){

	   new TestLists{
	    
	    assert(l1.foldRightInside(0)((X,Y)=>X-Y)==7)
	  }  
  }
  
  test("reduceLeft - Method"){

	   new TestLists{
	    assert(l1.reduceLeft((X,Y)=>X-Y)== -5)
	  }
  }

  test("reduceRight - Method"){

	   new TestLists{
	    
	    assert(l1.reduceRight((X,Y)=>X-Y)==7)
	  }
  }

	 test("findMin - Method"){

	   new TestLists{
	    
	    assert(Application.findMin(l1)==2)
	  }
  }

	test("sumOddNumbers - Method"){

	   new TestLists{
	    
	    assert(Application.sumOddNumbers(l1)==17)
	  }
  }
	
  test("countEvenNumbers - Method"){

	   new TestLists{
	    
	    assert(Application.countEvenNumbers(l1)==2)
	  }
  }
  
    test("multiplyFilterEven Method"){
	  
	  new TestLists{
	    
	    assert(Application.multiplyAndFilterEven(l1,3)===l1_me)
	  }
	}

	test("flip Method"){
	  
	  new TestLists{
	    
	    assert(l5_rev===l5.flip)
	  }
	}
}