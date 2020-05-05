package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import model.*;

public class PrintResult {

    public void printToFile(RungeKutta rk, Optimize opt, SystemModel sm) throws IOException {
        System.out.println("Results in file output.txt");
        double[] point = new double[3];
        for (int i = 0; i < point.length; i++) {
            point[i] = opt.getResult()[i + 1];
        }
        rk.solve(point, sm.getParamsOfModel()[6], sm.getParamsOfModel()[7]);
        try (FileWriter file = new FileWriter("output.txt"); Formatter formatter = new Formatter(file)) {
            formatter.format("%s\t", "t");
            formatter.format("%s\t", "x1");
            formatter.format("%s\t", "x2");
            formatter.format("%s\n", "x3");
            for (int i = 0; i < rk.getTime().length; i++) {
                formatter.format("%.4f\t", rk.getTime()[i]);
                formatter.format("%.4f\t", rk.getX1()[i]);
                formatter.format("%.4f\t", rk.getX2()[i]);
                formatter.format("%.4f\n", rk.getX3()[i]);
                if (rk.getX1()[i] < 0) {
                    break;
                }
            }
        }
    }
}
