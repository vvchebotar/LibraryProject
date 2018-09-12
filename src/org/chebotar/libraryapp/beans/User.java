package org.chebotar.libraryapp.beans;

import java.time.DateTimeException;
import java.time.LocalDate;


public class User {

    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";

    private String id;
    private String userName;
    private String password;
    private String administrator;
    private LocalDate registrationDate;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private String gender;
    private String favoriteBooksBdName;

    public User() {
    }

    public User(String userName, String password, String name, String lastName, int day, int month, int year, String gender) throws DateTimeException {
        this.userName = userName;
        this.password = password;
        this.registrationDate = LocalDate.now();
        this.name = name;
        this.lastName = lastName;
        this.birthday = LocalDate.of(year, month, day);
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(long registration_date) {
        this.registrationDate = LocalDate.ofEpochDay(registration_date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = LocalDate.ofEpochDay(birthday);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteBooksBdName() {
        return favoriteBooksBdName;
    }

    public void setFavoriteBooksBdName(String favoriteBooksBdName) {
        this.favoriteBooksBdName = favoriteBooksBdName;
    }
}

