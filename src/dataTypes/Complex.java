package dataTypes;
/**
 * A class which represents a complex number
 * @author Sean Zimmerman
 * 
 * @version 3.10.2016
 */
public final class Complex {
    double r;        // distance
    double theta;    // angle

    // constructor that takes in rectangular coordinates
    public Complex(double re, double im) {
        r     = Math.sqrt(re*re + im*im);
        theta = Math.atan2(im, re);
    }

    // accessor methods
    public double re() { return r * Math.cos(theta); }

    public double im() { return r * Math.sin(theta); }

    public Complex conjugate() { return new Complex(this.re(), -this.im()); }

    // return a string representation of this complex number
    public String toString()  {
        return re() + " + " + im() + "i";
    }

    // return this Complex number plus b
    public Complex plus(Complex b) {
        Complex a = this;
        double re = a.r * Math.cos(a.theta) + b.r * Math.cos(b.theta);
        double im = a.r * Math.sin(a.theta) + b.r * Math.sin(b.theta);
        return new Complex(re, im);
    }

    // return this Complex number times b
    public Complex times(Complex b) {
        Complex a = this;
        Complex c = new Complex(0, 0);
        c.r = a.r * b.r;                // can't make r and theta final
        c.theta = a.theta + b.theta;    // because of these two statements
        return c;
    }

    // return the magnitude / absolute value of this complex number
    public double abs() { return r; }

}