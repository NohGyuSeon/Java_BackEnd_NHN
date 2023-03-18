package day04.first;

public class Guitarist {
    private final String name;
    private final String team;
    private final String guitarType;
    private final String guitarMaker;
    private final String playingType;
    private final String genre;

    public Guitarist(Builder builder) {
        this.name = builder.name;
        this.team = builder.team;
        this.guitarType = builder.guitarType;
        this.guitarMaker = builder.guitarMaker;
        this.playingType = builder.playingType;
        this.genre = builder.genre;
    }

    @Override
    public String toString() {
        return "Guitarist [name=" + name + ", team=" + team + ", guitarType=" + guitarType + ", guitarMaker="
                + guitarMaker + ", playingType=" + playingType + ", genre=" + genre + "]";
    }

    public static class Builder {
        private final String name;

        private String team;
        private String guitarType;
        private String guitarMaker;
        private String playingType;
        private String genre;

        public Builder(String name) {
            this.name = name;
        }

        public Builder team(String team) {
            this.team = team;
        return this;
        }

        public Builder guitarType(String guitarType) {
            this.guitarType = guitarType;
            return this;
        }

        public Builder guitarMaker(String guitarMaker) {
            this.guitarMaker = guitarMaker;
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

        public Guitarist build() {
            return new Guitarist(this);
        }
    }
}