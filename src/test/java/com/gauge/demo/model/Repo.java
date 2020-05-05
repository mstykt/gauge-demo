package com.gauge.demo.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Repo {

    private Long id;
    private String name;
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Repo repo = (Repo) obj;
        return Objects.equals(id, repo.id) &&
                Objects.equals(name, repo.name) &&
                Objects.equals(owner, repo.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Repo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("owner=" + owner)
                .toString();
    }
}
