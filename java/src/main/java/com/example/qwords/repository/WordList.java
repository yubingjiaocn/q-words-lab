package com.example.qwords.repository;
import java.util.ArrayList;

public class WordList {

    private ArrayList<String> wordlist;


    public WordList() {

        this.wordlist = new ArrayList<String>();
        this.wordlist.add("animal");
        this.wordlist.add("bakery");
        this.wordlist.add("cracks");

    }

    public String getRandomWord() {

        return this.wordlist.get(0);
    }

}

