import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * asks the user what constant μ should be approximated, and then asks in turn
 * for each of the four personal numbers w, x, y, and z. The program should then
 * calculate and report the values of the exponents a, b, c, and d that bring
 * the de Jager formula as close as possible to μ, as well as the value of the
 * formula waxbyczd and the relative error of the approximation to the nearest
 * hundredth of one percent (see SimpleWriter print(double, int, boolean) for a
 * method you may find useful for this).
 *
 * @author ZHIDONG YANG
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String snm = in.nextLine();

        boolean result = FormatChecker.canParseDouble(snm);

        while (!result || Double.parseDouble(snm) <= 0) {
            out.println("Please enter a positive real number.");
            out.println("What constant μ4 do you want to be approximated?");
            snm = in.nextLine();
            result = FormatChecker.canParseDouble(snm);
        }

        return Double.parseDouble(snm);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String snm = in.nextLine();
        final double e = 0.0001;
        boolean result = FormatChecker.canParseDouble(snm);
//use a while loop to solve the case where the user input is a real number
        while (!result || Double.parseDouble(snm) <= 0
                || Math.abs(Double.parseDouble(snm) - 1) < e) {
            out.println(
                    "Please enter a positive real number not equal to one.");
            //line 40, 41 and 42 ask user enter a positive real number
            out.println("Enter a positive real number not equal to 1.0: ");
            //receive the user input
            snm = in.nextLine();
            /*
             * check if that the input is a real number by using the method of
             * canParseDouble
             */

            result = FormatChecker.canParseDouble(snm);
        }

        return Double.parseDouble(snm);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    //FIXME: question asks you to define another function here.
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * asks the user what constant μ should be approximated
         */
        out.println("What constant μ do you want to be approximated?");
        double constant = getPositiveDouble(in, out);

        /*
         * asks in turn for each of the four personal numbers w, x, y, and z
         */
        out.println("Pleanse enter the personal number w.");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("Pleanse enter the personal number x.");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("Pleanse enter the personal number y.");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("Pleanse enter the personal number z.");
        double z = getPositiveDoubleNotOne(in, out);

        /*
         * use while loop to get the value of a,b,c,d
         */
        int a = 0, b = 0, c = 0, d = 0;
        double acount = 0, bcount = 0, ccount = 0, dcount = 0;
        final double[] array = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        double difference = ((Math.pow(w, array[0])) * (Math.pow(x, array[0]))
                * (Math.pow(y, array[0])) * (Math.pow(z, array[0]))) - constant;
        for (d = 0; d < array.length; d++) {
            for (c = 0; c < array.length; c++) {
                for (b = 0; b < array.length; b++) {
                    for (a = 0; a < array.length; a++) {
                        double estimate = ((Math.pow(w, array[a]))//get the estimate value
                                * (Math.pow(x, array[b]))
                                * (Math.pow(y, array[c]))
                                * (Math.pow(z, array[d]))) - constant;
                        /*
                         * set the final value
                         */
                        if (Math.abs(estimate) < Math.abs(difference)) {
                            difference = estimate;
                            acount = array[a];
                            bcount = array[b];
                            ccount = array[c];
                            dcount = array[d];
                        }

                    }

                }

            }

        }
        /*
         * show the value of error, the formula, and the value of w, x, y, z
         */
        final int hundred = 100;
        double error = Math.abs(difference / constant) * hundred;
        out.println("value of the formula: " + (constant + difference));
        out.print("The error is: ");
        int precision = 2;
        out.print(error, precision, false);
        out.println(" %.");
        out.println("The exponent of w is " + acount);
        out.println("The exponent of x is " + bcount);
        out.println("The exponent of y is " + ccount);
        out.println("The exponent of z is " + dcount);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
