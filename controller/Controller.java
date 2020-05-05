package controller;

import java.io.IOException;
import model.*;
import view.*;

public class Controller {

    private SystemModel sm;
    private RungeKutta rk;
    private Optimize opt;
    private ViewResult vr;
    private PrintResult pr;

    public void setSystemModel(double a, double b, double c, double d, double e,
            double f, double g, double h) {
        sm = new SystemModel(a, b, c, d, e, f, g, h);
    }

    public void setRungeKutta(double a, double b, double c,
            double d, double e, double f) {
        rk = new RungeKutta(a, b, c, d, e, f);
    }

    public void setOptimize(int count) {
        opt = new Optimize(count, sm, rk);
    }

    public void startTest(double bound) {
        opt.test(bound);
    }

    public void viewResult() {
        vr = new ViewResult();
        vr.printResult(opt);
    }

    public void printResult() throws IOException {
        pr = new PrintResult();
        pr.printToFile(rk, opt, sm);
    }
}
