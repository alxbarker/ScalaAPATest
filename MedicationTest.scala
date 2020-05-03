package alx.nursing;

/* Based on doi:10.1007/s11357-013-9510-6 */

private[nursing] class AgeRange (minAge: Integer, maxAge: Integer);

object AgeRange {
  def apply (minAge: Integer, maxAge: Integer): AgeRange =    
    new AgeRange (Math.min(minAge, maxAge), Math.max(minAge, maxAge));
}

abstract class PatientAgeContext (ageRange: AgeRange);

case object Neonate         extends PatientAgeContext (AgeRange ( 0,  1));
case object Infant          extends PatientAgeContext (AgeRange ( 0,  2));
case object Preschooler     extends PatientAgeContext (AgeRange ( 3,  5));
case object Child           extends PatientAgeContext (AgeRange ( 6, 13));
case object Adolescent      extends PatientAgeContext (AgeRange (14, 18));
case object YoungAdult      extends PatientAgeContext (AgeRange (19, 24));
case object Adult           extends PatientAgeContext (AgeRange (19, 65));
case object MiddleAgedAdult extends PatientAgeContext (AgeRange (44, 65));
case object AgedAdult       extends PatientAgeContext (AgeRange (65, 80));
case object GeriatricAdult  extends PatientAgeContext (AgeRange (80,125));

/*
abstract class TemporalContext;

case class BeforeSurgery (d: Duration);
case class AfterSurgery (d: Duration);
case class BeforeSecondDose (d: Duration);
case class PerDiem;
case class AtBedtime;
case class WithFood;
case class WithLiquid; // *

abstract class MedicationRoute;

case class Intravenous   extends MedicationRoute;
case class Intramuscular extends MedicationRoute;
case class Oral          extends MedicationRoute;
case class Buccal        extends MedicationRoute;
case class Inhaled       extends MedicationRoute;
//case class _ extends MedicationRoute;
//case class _ extends MedicationRoute;
*/

// extension methods in Dotty for Durations
/*
private val secondsPerMinute: Integer = 60;
private val secondsPerHour:   Integer = 60 * secondsPerMinute;
private val secondsPerDay:    Integer = 24 * secondsPerHour;

def (s: Integer) seconds: Duration = new Duration (s);
def (m: Integer) minutes: Duration = new Duration (m, 0);
def (h: Integer) hours: Duration = new Duration (h, 0, 0);
def (d: Integer) days: Duration = new Duration (d, 0, 0, 0);
*/

private[nursing] abstract class SanitizedDuration (
                                  var weeks: Integer = 0,
                                  var days: Integer = 0,
                                  var hours: Integer = 0,
                                  var minutes: Integer = 0,
                                  var seconds: Integer = 0) {

  // sanitize input
  while (seconds >= 60) {
    seconds = seconds - 60;
    minutes = minutes + 1;
  }

  while (minutes >= 60) {
    minutes = minutes - 60;
    hours = hours + 1;
  }

  while (hours >= 24) {
    hours = hours - 24;
    days = days + 1;
  }

  while (days >= 7) {
    days = days - 7;
    weeks = weeks + 1;
  }

} // SanitizedDuration

class Duration (weeks: Integer = 0,
                days: Integer = 0,
                hours: Integer = 0,
                minutes: Integer = 0,
                seconds: Integer = 0)
      extends SanitizedDuration (weeks, days, hours, minutes, seconds) {

  override def toString: String =
    "Duration: " + (if (days    != 0) s"$days days ") +
                   (if (hours   != 0) s"$hours hours ") +
                   (if (minutes != 0) s"$minutes minutes ") +
                   (if (seconds != 0) s"$seconds seconds "); // +
                   //(super.seconds) + " super seconds";
                 
  def + (that: Duration): Duration =
    new Duration (this.weeks   + that.weeks,
                  this.days    + that.days,
                  this.hours   + that.hours,
                  this.minutes + that.minutes,
                  this.seconds + that.seconds);

} // Duration

/*
 * Dosage (MedicationPurpose,
 *         MedicationRoute,
 *         PatientAgeContext,
 *         DosageWithTemporalContext,
 *         MaximumDosageWithTemporalContext)
 */

object DurationTest {
  
  def main (args: Array[String]): Unit = {
    val myDuration1: Duration =
          new Duration (days = 6, hours = 13, minutes = 50, seconds = 45); 
    val myDuration2: Duration =
          new Duration (days = 2, hours = 20, minutes = 12, seconds = 45); 

    System.out.println (myDuration1 + myDuration2);
  }
  
} // DurationTest
