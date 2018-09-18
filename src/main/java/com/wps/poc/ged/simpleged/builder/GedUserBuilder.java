package com.wps.poc.ged.simpleged.builder;

import com.wps.poc.ged.simpleged.model.GedUser;

public class GedUserBuilder {

    private String id;
    private String username;
    private String password;

    public GedUserBuilder() {
    }

    public GedUserBuilder id(final String id) {
        this.id = id;
        return this;
    }

    public GedUserBuilder username(final String username) {
        this.username = username;
        return this;
    }

    public GedUserBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public GedUser build() {
        GedUser gedUser = new GedUser();
        gedUser.setId(id);
        gedUser.setPassword(password);
        gedUser.setUsername(username);
        return gedUser;
    }

    public String toString() {
        return "GedUser.GedUserBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ")";
    }
}
