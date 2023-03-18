package day03.second;

/**
 * Guitarist
 * 이펙티브 자바에서 제안하는 Builder! 디자인패턴에서의 빌더 패턴 x
 * 내부에서 builder 생성해서 chain을 통해 필드 생성
 */
public class Guitarist {
    private final int guitaristNo;
    private final String name;
    private final String guitarType;
    private final String guitarMaker;
    private final String playingType;
    private final String genre;
    private final String teamName;

    public Guitarist(Builder builder) {
        this.guitaristNo = builder.guitaristNo;
        this.name = builder.name;
        this.guitarType = builder.guitarType;
        this.guitarMaker = builder.guitarMaker;
        this.playingType = builder.playingType;
        this.genre = builder.genre;
        this.teamName = builder.teamName;
    }
    
    @Override
    public String toString() {
        return "Guitarist [guitaristNo=" + guitaristNo + ", name=" + name + ", guitarType=" + guitarType
                + ", guitarMaker=" + guitarMaker + ", playingType=" + playingType + ", genre=" + genre + ", teamName="
                + teamName + "]";
    }

    public static class Builder {
        private int guitaristNo;
        private String name;
        private String guitarType;
        private String guitarMaker;
        private String playingType;
        private String genre;
        private String teamName;

        public Builder(String guitarType, String guitarMaker) {
            this.guitarType = guitarType;
            this.guitarMaker = guitarMaker;
        }

        public Builder guitaristNo(int value) {
            this.guitaristNo = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder guitarType(String value) {
            this.guitarType = value;
            return this;
        }

        public Builder guitarMaker(String value) {
            this.guitarMaker = value;
            return this;
        }

        public Builder playingType(String value) {
            this.playingType = value;
            return this;
        }

        public Builder genre(String value) {
            this.genre = value;
            return this;
        }

        public Builder teamName(String value) {
            this.teamName = value;
            return this;
        }

        public Guitarist build() {
            return new Guitarist(this);
        }
    }
}

class Test {
    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist
        .Builder("레츠뽀", "텔레케스터")
        .guitaristNo(1)
        .genre("pop")
        .name("John lee")
        .teamName("NFlying")
        .build();
        System.out.println(guitarist.toString());
    }
}