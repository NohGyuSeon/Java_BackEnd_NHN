package day05.first.recipe;

public class Barbeque implements Vistor {

    @Override
    public void visit(Acceptor acceptor) {
        System.out.println("Bar배Queue");
        System.out.println("달궈진 숯 준비~~");
        System.out.println("그릴 올려~~~");
        System.out.println(acceptor.getName() + "그릴에 올려" + acceptor.getQuantity() + acceptor.getUnit() + "만큼 올려~");
        System.out.println("자자 뒤집어~");
        System.out.println("...");
        System.out.println("자자 뒤집어~");
    }
    
}
