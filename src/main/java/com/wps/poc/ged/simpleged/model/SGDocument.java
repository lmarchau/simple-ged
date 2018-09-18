package com.wps.poc.ged.simpleged.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Document
public class SGDocument {

    @Id
    private String id;

    @NotNull
    private String name;

    private LocalDateTime craetedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCraetedAt() {
        return craetedAt;
    }

    public void setCraetedAt(LocalDateTime craetedAt) {
        this.craetedAt = craetedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SGDocument that = (SGDocument) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(craetedAt, that.craetedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, craetedAt);
    }

    @Override
    public String toString() {
        return "SGDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", craetedAt=" + craetedAt +
                '}';
    }
}
