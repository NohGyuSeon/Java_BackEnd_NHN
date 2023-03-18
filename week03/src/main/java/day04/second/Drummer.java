package day04.second;

public class Drummer {
    private final int drummerNo;
    private final String name;

    private final int numberOfBass;
    private final int countOfSymbol;
    private final String teamName;

    private Drummer(Builder builder) {
        this.drummerNo = builder.drummerNo;
        this.name = builder.name;
        this.numberOfBass = builder.numberOfBass;
        this.countOfSymbol = builder.countOfSymbol;
        this.teamName = builder.teamName;
    }

    @Override
    public String toString() {
        return "Drummer [drummerNo=" + drummerNo + ", name=" + name + ", numberOfBass=" + numberOfBass
                + ", countOfSymbol=" + countOfSymbol + ", teamName=" + teamName + "]";
    }

    public static class Builder {
        private final int drummerNo;
        private final String name;

        private int numberOfBass;
        private int countOfSymbol;
        private String teamName;

        public Builder(int drummerNo, String name) {
            this.drummerNo = drummerNo;
            this.name = name;
        }

        public Builder numberOfBass(int numberOfBass) {
            this.numberOfBass = numberOfBass;
            return this;
        }

        public Builder countOfSymbol(int countOfSymbol) {
            this.countOfSymbol = countOfSymbol;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Drummer build() {
            return new Drummer(this);
        }
    }
}