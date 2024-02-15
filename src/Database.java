public class Database {
    private String Word;
    private String Meaning;

    public Database(String word, String meaning) {
        Word = word;
        Meaning = meaning;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getMeaning() {
        return Meaning;
    }

    public void setMeaning(String meaning) {
        Meaning = meaning;
    }

    @Override
    public String toString() {
        return "Word= '" + Word + '\'' +
                ", Meaning = '" + Meaning + '\'';
    }

    public String toFileLine(){
        return Word +" "+ Meaning;
    }
}
