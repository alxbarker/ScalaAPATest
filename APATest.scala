package alx.exp.apa;

abstract class APATextbook (val authors: Array[String],
                            val year: Integer,
                            val title: String, 
                            val edition: String = "",
                            val city: String,
                            val publisher: String) {

  override def toString: String =
    authors.take(authors.size-1).mkString ("", ", ", ", ") +
      "& " + authors.takeRight(1).mkString +
      " (" + year + ")." +
      " italic-start:::" + title +
      (if (edition != "") " (" + edition + "). " else "") +
      ":::italic-stop " + 
      city + ": " + publisher + ".";

} // APATextbook

object MedSurgCan2014
  extends APATextbook (
    authors = Array[String] ("Lewis, S. L.", "Dirksen, S. R.",
                             "Heitkemper, M. M.", "Bucher, L.", 
                             "Camera, I. M."),
    year = 2014,
    title = "Medical-surgical nursing in Canada: Assessment and management of clinical problems",
    edition = "3rd Cdn. ed.",
    city = "Toronto",
    publisher = "Elsevier Canada") {
    
} // MedSurgCan2014
 
object APATest {
  
  def main (args: Array[String]): Unit =
    System.out.println (MedSurgCan2014);

} // APATest

/* Output:
Lewis, S. L., Dirksen, S. R., Heitkemper, M. M., Bucher, L., & 
Camera, I. M.(2014). italic-start:::Medical-surgical nursing in 
Canada: Assessment and management of clinical problems (3rd Cdn. ed.). 
:::italic-stop Toronto: Elsevier Canada.
*/
