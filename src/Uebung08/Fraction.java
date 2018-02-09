public class Fraction {

  private long numerator;
  private long denominator;

  private static long ggT(long a, long b) {
    if (b == 0)
      return a;
    return ggT(b, a % b);
  }

  public Fraction(long numerator, long denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public long getNumerator() {
    return numerator;
  }

  public long getDenominator() {
    return denominator;
  }

  @Override
  public String toString() {
    return "[ " + numerator + " / " + denominator + " ]";
  }

  public void simplify() {
    // TODO
    if((this.numerator<0 && this.denominator<0) || (this.numerator>0 && this.denominator<0)){
      this.numerator = this.numerator * -1;
      this.denominator = this.denominator * -1;
    }
    ggT(this.numerator, this.denominator);
  }

  public Fraction mul(Fraction other) {
    // TODO
   
    long zaehler = (this.numerator*other.numerator);
    long nenner = (this.denominator*other.denominator);
    other = new Fraction(zaehler, nenner);
    simplify();
    return other;
  }

  public Fraction div(Fraction other) {
    // TODO
    long zaehler = this.numerator * other.denominator;
    long nenner =  this.denominator * other.numerator;
    Fraction other = new Fraction(zaehler, nenner);
    other.simplify();
    return other;
  }

  public Fraction add(Fraction other) {
    // TODO
    long zaehler = (this.numerator*denominator + this.denominator*numerator);
    long nenner = this.denominator*denominator;
    other = new Fraction(zaehler, nenner);
    return other;
  }

  public Fraction sub(Fraction other) {
    // TODO
    long zaehler = (this.numerator*denominator - this.denominator*numerator);
    long nenner = this.denominator*denominator;
    other = new Fraction(zaehler, nenner);
    return other;
  }

  public int compareTo(Fraction other) {
    // TODO
    return -1;
  }

  public boolean isNonNegative() {
    // TODO
    if((numerator<0 && denominator>0) || (numerator>0 && denominator<0)){
      return false;
    }else {
      return true;
    }
  }
}