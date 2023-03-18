package day03.fifth.director;

import builder.Builder;
import java.util.ArrayList;

public class Album {
    private Builder builder;

    public Album(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.albumTitle("Appetite for Destruction");
        builder.teamName("Guns N' Roses");
        ArrayList<String> songs = new ArrayList<>();
        songs.add("Welcome to the Jungle");
        songs.add("It's So Easy");
        songs.add("Nightrain");
        songs.add("Out to Get Me");
        songs.add("Mr. Brownstone");
        songs.add("Paradise City");
        songs.add("My Michelle");
        songs.add("Think About You");
        songs.add("Sweet Child o' Mine");
        songs.add("You're Crazy");
        songs.add("Anything Goes");
        songs.add("Rocket Queen");
        builder.makeItems(songs);
        builder.close();
    }
}