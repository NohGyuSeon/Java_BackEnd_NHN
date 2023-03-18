package day05.first.ingredient;

import day05.first.recipe.Vistor;

public class Pork extends Acceptor {

    public Pork(String name, int quantity, String unit) {
        super(name, quantity, unit);
    }

    public void accept(Vistor vistor) {
        vistor.visit(this);
        System.out.println("오이시꾸나레~ 오이시꾸나레~ 모에모에뀽!");
    }    
}
