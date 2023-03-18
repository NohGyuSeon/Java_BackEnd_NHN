package day05.first.recipe;

import day05.first.ingredient.Acceptor;

public class Fry implements Vistor {

    @Override
    public void visit(Acceptor acceptor) {
        System.out.println("I believe I can fry~");
        System.out.println("기름 끓여~");
        System.out.println("보글보글");
        System.out.println(acceptor.getName() + "넣어~" + acceptor.getQuantity() + acceptor.getUnit() + "만큼 넣어");
        System.out.println("자자 이제 건져내~");
    }

    
}