package day05.first.recipe;

import day05.first.ingredient.Acceptor;

public class Roast implements Vistor {

    @Override
    public void visit(Acceptor acceptor) {
        System.out.println("roast my mind~!");
        System.out.println("후라이팬에 불 올려~");
        System.out.println("예열 다 되면~~");
        System.out.println(acceptor.getName() + "올려~" + acceptor.getQuantity() + acceptor.getUnit() + "만큼 올려");
        System.out.println("자자 뒤집어~");
        System.out.println("...");
        System.out.println("자자 뒤집어~");
    }
    
}
