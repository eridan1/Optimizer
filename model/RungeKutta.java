package model;

public class RungeKutta {

    private final double[] x1;
    private final double[] x2;
    private final double[] x3;
    private final double[] time;
    private final double h;
    private final int n;

    public double[] getX1() {
        return x1;
    }

    public double[] getX2() {
        return x2;
    }

    public double[] getX3() {
        return x3;
    }

    public double[] getTime() {
        return time;
    }

    public RungeKutta(double t0, double tk, double step,
            double x10, double x20, double x30) {
        h = step;
        n = (int) (1 + (tk - t0) / step);
        time = new double[n];
        x1 = new double[n];
        x2 = new double[n];
        x3 = new double[n];
        time[0] = t0;
        x1[0] = x10;
        x2[0] = x20;
        x3[0] = x30;
    }

    private double u(double a, double b, final double c1, final double c2) {
        double f1 = -(1 / c1) * (a + c2 * b);
        return f1;
    }

    private double v(double a, double b, final double c1) {
        double f2 = (1 / c1) * (a - b);
        return f2;
    }

    private double q(double a, final double c1, final double c2) {
        double f3;
        if (a <= -c1) {
            f3 = -c2;
        } else if (a >= c1) {
            f3 = c2;
        } else {
            f3 = 0.0;
        }
        return f3;
    }

    public void solve(double[] rand, double s, double eta) {
        double k1, k2, k3, k4;
        double r1, r2, r3, r4;
        double g1, g2, g3, g4;
        double t = time[0];
        double X = x1[0];
        double Y = x2[0];
        double Z = x3[0];
        double T1 = rand[0];
        double T2 = rand[1];
        double K1 = rand[2];
        double S = s;
        double Eta = eta;
        int i = 0;
        while (++i < n) {
            k1 = u(X, Z, T1, K1);
            r1 = v(X, Y, T2);
            g1 = q(Y, Eta, S);

            k2 = u(X + h * k1 / 2, Z + h * g1 / 2, T1, K1);
            r2 = v(X + h * k1 / 2, Y + h * r1 / 2, T2);
            g2 = q(Y + h * r1 / 2, Eta, S);

            k3 = u(X + h * k2 / 2, Z + h * g2 / 2, T1, K1);
            r3 = v(X + h * k2 / 2, Y + h * r2 / 2, T2);
            g3 = q(Y + h * r2 / 2, Eta, S);

            k4 = u(X + h * k3, Z + h * g3, T1, K1);
            r4 = v(X + h * k3, Y + h * r3, T2);
            g4 = q(Y + h * r3, Eta, S);

            X += (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
            Y += (h / 6) * (r1 + 2 * r2 + 2 * r3 + r4);
            Z += (h / 6) * (g1 + 2 * g2 + 2 * g3 + g4);
            t += h;
            this.time[i] = t;
            this.x1[i] = X;
            this.x2[i] = Y;
            this.x3[i] = Z;
        }
    }
}
