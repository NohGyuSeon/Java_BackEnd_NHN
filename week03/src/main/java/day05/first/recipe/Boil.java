package day05.first.recipe;

import ingredient.Acceptor;

public class Boil implements Vistor {

    @Override
    public void visit(Acceptor acceptor) {
        System.out.println("Boil락 말락~ 아일락~");
        System.out.println("물 끓여~");
        System.out.println(acceptor.getName() + "넣어~" + acceptor.getQuantity() + acceptor.getUnit() + "만큼 넣어");
        System.out.println("자자 이제 건져내~");
    }
    
}
