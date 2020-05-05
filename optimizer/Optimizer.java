package optimizer;

import controller.*;
import java.io.*;

public class Optimizer {

    public static void main(String[] args) throws IOException {
        Controller ctrl = new Controller();
        ctrl.setSystemModel(0.1, 0.1, 0.1, 0.1, 0.1, 1.0, 0.0625, 1.0);
        ctrl.setRungeKutta(0.0, 1.0, 0.001, 20.0, 5.0, 0.0);
        ctrl.setOptimize(300000);
        ctrl.startTest(0.01);
        ctrl.viewResult();
        ctrl.printResult();
    }
}
