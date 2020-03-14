package com.galvanize.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="officers")
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="officer_rank")
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Column(name="first_name")
    private String first;
    @Column(name="last_name")
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

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Officer{" +
                "id=" + id +
                ", rank=" + rank +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Officer officer = (Officer) o;
        return getId() == officer.getId() &&
                getRank() == officer.getRank() &&
                getFirst().equals(officer.getFirst()) &&
                getLast().equals(officer.getLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRank(), getFirst(), getLast());
    }
}

