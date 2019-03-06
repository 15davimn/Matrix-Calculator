//Due to rounding errors with dobules, a fraction class was made to avoid this.
//Another reason that it's used is that msot math classes prefer fraction answers instead of math.
//Future improvements will involve some methods returning new fractions and adding a cloning method.

package work.with.matrices;

public class Fraction {

    private int num;
    private int denom;

    public Fraction(int num, int denom) {
        this.num = num;
        this.denom = denom;
    }

    //Since all number are added as a fraction, if a whole number is entered, this converts it into a fraction.
    public Fraction(int num) {
        this.num = num;
        this.denom = 1;
    }

    public void addFraction(Fraction toAdd) {
        
        //Since you can only add fractions with the same denominator, first the program checks to see if they are the same.
        //If they are, it just adds the numerators together.
        if (this.denom == toAdd.getDenom()) {
            this.num += toAdd.getNum();
            
            //Handles if the fraction is zero.
        } else if (this.num == 0) {
            this.num = toAdd.num;
            this.denom = toAdd.denom;
        } else if (toAdd.num == 0) {
            return;
        } else {
            //Since they don't have the same denominator, converts the two fractions to fractions that do.
            int gcd = gcd(toAdd);
            int factor1 = gcd / this.denom;
            int factor2 = gcd / toAdd.getDenom();
            multiply(new Fraction(factor1, factor1));
            toAdd.multiply(new Fraction(factor2, factor2));
            this.num += toAdd.num;
        }
        
        reduce();
    }

    public int getNum() {
        return num;
    }

    public int getDenom() {
        return denom;
    }

    public int gcd(Fraction otherFraction) {
        
       
        if (denom == 0 || otherFraction.getDenom() == 0) {
            return 0;
        }
        int otherDenom = otherFraction.getDenom();
        int possibleFactor = 2;
        while (true) {
            if ((possibleFactor % this.denom == 0) && (possibleFactor % otherDenom == 0)) {
                return possibleFactor;
            } else {
                possibleFactor++;
            }
        }
    }
//prints out number in num / denom fashion.
    public String toString() {
        if (denom == 1) {
            return "" + num;
        } else if (denom == 0 || num == 0) {
            return "0";
        } else {
            return num + "/" + denom;
        }
    }

    public void multiply(Fraction toMultiplyBy) {
    //Multiples two fractions together.
        this.num *= toMultiplyBy.getNum();
        this.denom *= toMultiplyBy.getDenom();

    }

    public void divide(Fraction toDivideBy) {
        //Handles Division
        Fraction flipped = new Fraction(toDivideBy.denom, toDivideBy.num);
        multiply(flipped);
    }

    public void multiply(int toMultiplyBy) {
        
        //Multiplies a fraction by an integer.
        this.num *= toMultiplyBy;
    }

    //Simplifies the fraction. If both the top and bottom are divisible by a number, divides them both by it.
    //Also takes care of a few things like turning the fraction 0/3 to 0/0 to make handling it easier.
    
    public void reduce() {

        if (this.num == 0) {
            this.denom = 0;
            return;
        }
        if (this.denom < 0 && this.num < 0) {
            denom *= -1;
            num *= -1;
        }

        if (this.denom < 0 && this.num > 0) {
            denom *= -1;
            num *= -1;
        }

        if (this.num == this.denom) {
            this.num = 1;
            this.denom = 1;
            return;
        }

        if (this.num == (-1 * this.denom)) {
            this.num = -1;
            this.denom = 1;
        }

        int factor = numberForReducing();
        if (!(factor == -1)) {
            num /= factor;
            denom /= factor;
        }
    }

    public int numberForReducing() {
        int possibleFactor = -999;
        if (Math.abs(num) < Math.abs(denom)) {
            possibleFactor = denom;
        } else {
            possibleFactor = num;
        }
        while (Math.abs(possibleFactor) > 1) {
            if (this.num % possibleFactor == 0 && this.denom % possibleFactor == 0) {
                return possibleFactor;
            } else {
                if (possibleFactor > 0) {
                    possibleFactor--;
                } else {
                    possibleFactor++;
                }
            }
        }
        return -1;
    }
}
