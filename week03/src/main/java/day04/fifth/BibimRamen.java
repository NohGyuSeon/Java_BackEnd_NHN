package day04.fifth;

abstract class BibimRamen {
    public void cook() {
        System.out.println("----------------");
        boilWater();
        putNoodleOn();
        waitEnd();
        throwWater();
        putSoupBaseOn();
        mixSoup();
        System.out.println("----------------");
    }
    
    protected void boilWater() {
        System.out.println("물이 끓습니다.");
    }
    
    protected void putSoupBaseOn() {
        System.out.println("모든 스프를 넣습니다");
    }
    
    protected void putNoodleOn() {
        System.out.println("면을 넣습니다");
    }
    
    protected void mixSoup() {
        System.out.println("스프와 면을 섞습니다.");
    }
    
    abstract void throwWater();
    abstract void waitEnd();
}

class Paldo extends BibimRamen {

    @Override
    void throwWater() {
        System.out.println("물을 다 버립니다.");
    }

    @Override
    void waitEnd() {
        System.out.println("3분을 기다립니다.");
        System.out.println("팔도 비빔면이 완성되었습니다.");
    }
}

class JJapagetti extends BibimRamen {

    @Override
    void throwWater() {
        System.out.println("물을 6숟가락 남겨놓고 버립니다.");
    }

    @Override
    void waitEnd() {
        System.out.println("4분을 기다립니다.");
        System.out.println("짜파게티가 완성되었습니다.");
    }

}

class Spaghetti extends BibimRamen {

    @Override
    protected void putSoupBaseOn() {
        System.out.println("소스를 붇습니다.");
    }

    @Override
    void throwWater() {
        System.out.println("물을 전부 버립니다.");
    }

    @Override
    void waitEnd() {
        System.out.println("10분을 기다립니다.");
    }

}

class BibimRamenTest {
    public static void main(String[] args) {
        BibimRamen paldo = new Paldo();
        BibimRamen jjapagetti = new JJapagetti();
        BibimRamen spaghetti = new Spaghetti();

        paldo.cook();
        jjapagetti.cook();
        spaghetti.cook();
    }
}