package day04.fifth;

public abstract class Ramen {
    public void cook() {
        System.out.println("-----------------");
        boilWater();
        putNoodleOn();
        putSoupBaseOn();
        addTopping();
        waitEnd();
        System.out.println("-----------------");
    }

    public void boilWater() {
        System.out.println("물이 끓습니다.");
    }

    public void putNoodleOn() {
        System.out.println("면을 넣습니다.");
    }

    public void putSoupBaseOn() {
        System.out.println("스프를 넣습니다.");
    }

    public abstract void addTopping();
    public abstract void waitEnd();
}

class Sin extends Ramen {

    @Override
    public void addTopping() {
        System.out.println("계란을 추가합니다");
    }

    @Override
    public void waitEnd() {
        System.out.println("3분을 기다립니다...");
        System.out.println("신라면 조리가 완료되었습니다.");
    }

}

class Jin extends Ramen {

    @Override
    public void addTopping() {
        System.out.println("대파를 추가합니다");
    }

    @Override
    public void waitEnd() {
        System.out.println("4분을 기다립니다...");
        System.out.println("진라면 조리가 완료되었습니다.");
    }

}

class ChamKae extends Ramen {

    @Override
    public void addTopping() {
        System.out.println("유성 스프를 추가합니다.");
    }

    @Override
    public void waitEnd() {
        System.out.println("4분 30초를 기다립니다.");
        System.out.println("참깨라면 조리가 완료되었습니다.");
    }
}