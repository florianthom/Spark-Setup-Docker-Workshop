package matrix

class SparseMatrix (data_list:List[((Int,Int),Double)], val rows:Int, val columns:Int) {
  
  type Vector = Array[Double]
  
  val data= data_list.toMap
  def toList= data.toList

  def *(v:Vector):Vector = ???

  def *(matrix:SparseMatrix):SparseMatrix = ???
  
  def equals(matrix:SparseMatrix):Boolean = ???
  
  def toMatrix:Matrix = Matrix.createFromSparse(data.toList, rows, columns)
  
  def getRow(row_nr:Int):Vector = ???
  
  def getColumn(col_nr:Int):Vector = ???  
  
  // see Basic Operations.scala (he implemented this)
  def matrixMultiplicationMR( data: SparseMatrix):SparseMatrix= ???
}