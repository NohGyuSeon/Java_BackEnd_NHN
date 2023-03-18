package day03.sixth;

import framework.*;

public class Cellphone implements Product {
    int modelNumber;
    String modelName;
    String category;

    public Cellphone(int modelNumber, String modelName) {
        this.modelNumber = modelNumber;
        this.modelName = modelName;
    }

    @Override
    public String getCategoryName() {
        return this.category;
    }

    @Override
    public int getModelNumber() {
        return this.modelNumber;
    }


    @Override
    public void setCategory(String category) {
        this.category = category;
    }


}
