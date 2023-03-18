package day05.first;

import day05.first.ingredient.Pork;
import day05.first.recipe.Barbeque;
import day05.first.recipe.Boil;
import day05.first.recipe.Fry;
import day05.first.recipe.Roast;

public class Test {
    public static void main(String[] args) {
        Pork pork = new Pork("돼지고기", 200, "g");
        Fry fry = new Fry();
        Boil boil = new Boil();
        Roast roast = new Roast();
        Barbeque barbeque = new Barbeque();

        pork.accept(fry);
        System.out.println("--------------------");
        pork.accept(boil);
        System.out.println("--------------------");
        pork.accept(roast);
        System.out.println("--------------------");
        pork.accept(barbeque);
        
    }
}
