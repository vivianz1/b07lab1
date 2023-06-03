public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] numbers) {
        coefficients = numbers;
    }

    public Polynomial add(Polynomial p) {
        int big = 0;
        if (coefficients.length > p.coefficients.length) big = coefficients.length;
        else big = p.coefficients.length;
        double[] newco = new double[big];
        for (int i = 0; i < big; i++) {
            if (i >= coefficients.length) newco[i] = p.coefficients[i];
            else if (i >= p.coefficients.length) newco[i] = coefficients[i];
            else newco[i] = coefficients[i] + p.coefficients[i];
        }
        return new Polynomial(newco);
    }

    public double evaluate(double x) {
        double sum = coefficients[0];
        for (int i = 1; i < coefficients.length; i++) {
            sum += coefficients[i]*Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double y) {
        return evaluate(y) == 0;
    }
}
