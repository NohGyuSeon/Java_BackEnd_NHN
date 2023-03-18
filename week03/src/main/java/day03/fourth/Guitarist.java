package day03.fourth;

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
        private final String guitarType;
        private final String guitarMaker;
        private String playingType;
        private String genre;
        private String teamName;

        public Builder(String guitarType, String guitarMaker) {
            this.guitarType = guitarType;
            this.guitarMaker = guitarMaker;
        }

        public Builder guitaristNo(int guitaristNo) {
           this.guitaristNo = guitaristNo;
            return this;
        }

        public Builder name(String name) {
           this.name = name;
            return this;
        }

        public Builder playingType(String playingType) {
           this.playingType = playingType;
            return this;
        }

        public Builder genre(String genre) {
           this.genre = genre;
            return this;
        }

        public Builder teamnNme(String teamName) {
           this.teamName = teamName;
            return this;
        }

        public Guitarist build() {
            return new Guitarist(this);
        }
    }
}

class Test {
    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist.Builder("lespaul",
        "gibson").guitaristNo(2).build();

        System.out.println(guitarist2);
    }
}