package view;

import model.*;

public class ViewResult {

    public void printResult(Optimize opt) {
        System.out.format("Optimal time = %.3f seconds%n", opt.getResult()[0]);
        System.out.format("Optimal T1 = %.4f%n", opt.getResult()[1]);
        System.out.format("Optimal T2 = %.4f%n", opt.getResult()[2]);
        System.out.format("Optimal K1 = %.4f%n", opt.getResult()[3]);
    }
}
