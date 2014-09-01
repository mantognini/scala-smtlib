package smtlib
package common

class Hexadecimal private(val rep: String) {
  //should be normalized to upper cases
  require(rep.forall(c =>
    (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F')
  ))

  def toInt: Int = {
    rep.foldLeft(0)((acc, c) => {
      acc*16 + c.asDigit//asDigit works for 'A', 'F', ...
    })
  }

  def toBinary: List[Boolean] = {
    rep.flatMap{
      case '0' => List(false, false, false, false)
      case '1' => List(false, false, false, true )
      case '2' => List(false, false, true , false)
      case '3' => List(false, false, true , true )
      case '4' => List(false, true , false, false)
      case '5' => List(false, true , false, true )
      case '6' => List(false, true , true , false)
      case '7' => List(false, true , true , true )
      case '8' => List(true , false, false, false)
      case '9' => List(true , false, false, true )
      case 'A' => List(true , false, true , false)
      case 'B' => List(true , false, true , true )
      case 'C' => List(true , true , false, false)
      case 'D' => List(true , true , false, true )
      case 'E' => List(true , true , true , false)
      case 'F' => List(true , true , true , true )
    }.toList
  }

  override def toString: String = "#x" + rep

  override def equals(that: Any): Boolean = (that != null) && (that match {
    case (h: Hexadecimal) => rep == h.rep
    case _ => false
  })

  override def hashCode: Int = rep.hashCode

}

object Hexadecimal {

  def fromString(str: String): Option[Hexadecimal] = {
    var error = false
    val rep = str.map(c => {
      if(isDigit(c)) 
        c.toUpper
      else {
        error = true
        c
      }
    })
    if(error) None else Some(new Hexadecimal(rep))
  }

  def fromInt(n: Int): Option[Hexadecimal] = {
    if(n < 0) None else {
      ???
    }
  }

  def isDigit(c: Char): Boolean =
    c.isDigit || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')

}