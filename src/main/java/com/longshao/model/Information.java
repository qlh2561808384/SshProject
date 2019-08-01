package com.longshao.model;

import javax.persistence.*;

@Entity
public class Information {
    private int id;
    private int idUsers;
    private String sex;
    private String address;
    private String hobby;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_users")
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "hobby")
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Information that = (Information) o;

        if (id != that.id) return false;
        if (idUsers != that.idUsers) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (hobby != null ? !hobby.equals(that.hobby) : that.hobby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idUsers;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (hobby != null ? hobby.hashCode() : 0);
        return result;
    }
}
