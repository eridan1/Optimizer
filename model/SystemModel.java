package model;

public class SystemModel {

    private final double[] paramsOfModel;

    public SystemModel(double minT1, double maxT1, double minT2, double maxT2,
            double minK1, double maxK1, double S, double Eta) {
        paramsOfModel = new double[8];
        paramsOfModel[0] = minT1;
        paramsOfModel[1] = maxT1;
        paramsOfModel[2] = minT2;
        paramsOfModel[3] = maxT2;
        paramsOfModel[4] = minK1;
        paramsOfModel[5] = maxK1;
        paramsOfModel[6] = S;
        paramsOfModel[7] = Eta;
    }

    public double[] getParamsOfModel() {
        return paramsOfModel;
    }
}
