package com.galvanize.startrekcaptaindatabase.entities;

import com.galvanize.startrekcaptaindatabase.Rank;

public class Officer {
    private long id;
    private Rank rank;
    private String first;
    private String last;

    public Officer(){}

    public Officer(Rank rank, String first, String last) {
        this.rank = rank;
        this.first = first;
        this.last = last;
    }

    public Officer(long id, Rank rank, String first, String last) {
        this.id = id;
        this.rank = rank;
        this.first = first;
        this.last = last;
    }


}

