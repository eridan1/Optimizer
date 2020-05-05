package model;

import java.util.Random;

public class Optimize {

    private final SystemModel sm;
    private final RungeKutta rk;
    private final double[] vector;
    private double tTrs;
    private final Random rand;
    private final int countOfTest;
    private int currentTest;
    private final double[] result;

    public Optimize(int count, SystemModel sm_obj, RungeKutta rk_obj) {
        rk = rk_obj;
        sm = sm_obj;
        vector = new double[3];
        result = new double[4];
        rand = new Random();
        countOfTest = count;
    }

    public double[] getResult() {
        return result;
    }

    private void play() {
        double T1 = sm.getParamsOfModel()[0]
                + (sm.getParamsOfModel()[1] - sm.getParamsOfModel()[0])
                * rand.nextDouble();
        double T2 = sm.getParamsOfModel()[2]
                + (sm.getParamsOfModel()[3] - sm.getParamsOfModel()[2])
                * rand.nextDouble();
        double K1 = sm.getParamsOfModel()[4]
                + (sm.getParamsOfModel()[5] - sm.getParamsOfModel()[4])
                * rand.nextDouble();
        vector[0] = T1;
        vector[1] = T2;
        vector[2] = K1;
    }

    private void getTransientTime(double bound) {
        for (int i = rk.getX1().length - 1; i > 0; i--) {
            if (Math.abs(rk.getX1()[i]) >= bound) {
                tTrs = rk.getTime()[i];
                break;
            }
        }
    }

    public void test(double bound) {
        for (currentTest = 1; currentTest <= countOfTest; currentTest++) {
            play();
            rk.solve(vector, sm.getParamsOfModel()[6], sm.getParamsOfModel()[7]);
            getTransientTime(bound);
            if (currentTest > 1) {
                if (result[0] > tTrs) {
                    result[0] = tTrs;
                    System.arraycopy(vector, 0, getResult(), 1, 3);
                }
            } else {
                result[0] = tTrs;
                System.arraycopy(vector, 0, getResult(), 1, 3);
            }
        }
    }
}
