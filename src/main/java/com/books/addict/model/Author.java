package com.books.addict.model;


import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idauthor")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "age")
    private Integer age;


    @NotNull
    @Pattern(regexp ="^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(min=5)
    @Column(name = "password")
    private String password;



    public Author(@NotNull String name, @NotNull Integer age, @NotNull String username, @NotNull String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) &&
                Objects.equals(getName(), author.getName()) &&
                Objects.equals(getAge(), author.getAge()) &&
                Objects.equals(getUsername(), author.getUsername()) &&
                Objects.equals(getPassword(), author.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getUsername(), getPassword());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
