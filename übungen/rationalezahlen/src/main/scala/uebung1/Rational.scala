package uebung1

class Rational (numerator:Int, denominator:Int){
  
  //Erstellung einer Member-Variable
  //val num:Int=numerator/something // now it is a public member
  
  def this (denom:Int) = this(1,denom)  
  override def toString:String = numerator + "/" + denominator
  
  require (denominator!=0,"Denominator mmuss != 0 sein")  // wirft IllegalArgumentException
  println("Eine Rationale Zahl wurde erzeugt....")  // ist Teil des Konstruktors

  def num:Int=numerator  // damit numerator von aussen zugaenglich ist
  def denom:Int=denominator // damit denominator von aussen zugaenglich ist
  def value:Double = (num.toDouble / denom) // Konvertierung in eine Fliesskommazahl
  
  def  max(x:Rational): Rational = {
    
    if (numerator/denominator<x.num/x.denom) this else x
  }
  
  def add(r:Rational) : Rational = {
    new Rational(num * r.denom + denom * r.num, denom * r.denom)
  
  }
  
  def neg: Rational = {
    new Rational(numerator * (-1), denominator)
  }
  
  def sub(s:Rational) : Rational = {
    add(s.neg)
  }
  
  def gcd(a:Int, b:Int):Int={
    if(b==0)a
    else gcd(b,a % b)
  }
  
  def trim() : Rational = {
    new Rational(this.num/gcd(this.num,this.denom),this.denom/gcd(this.num,this.denom))
  }
  
}




