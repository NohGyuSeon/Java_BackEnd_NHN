package day03.sixth;

public class CellphoneFactory extends Factory {

    @Override
    protected Product create(int modelNumber, String modelName) {
        Product product = new Cellphone(modelNumber, modelName);
        return product;
    }

    @Override
    protected void setCategory(Product product) {
        product.setCategory("Mobile Device");
    }

}
