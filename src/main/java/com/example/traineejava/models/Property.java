package com.example.traineejava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 255, message = "Длина должна быть от 5 до 255")
    @NotNull(message = "Название не может быть пустым")
    private String title;

    @Size(min = 50, max = 1000, message = "Длина должна быть от 50 до 1000")
    @NotNull(message = "Описание не может быть пустым")
    @Column(columnDefinition = "varchar(1000)")
    private String description;
    @Positive(message = "Значение должно быть больше или равно 0")
    private long price;

    @Size(min = 15, max = 255, message = "Длина должна быть от 15 до 255")
    @NotNull(message = "Картинка не может быть пустой")
    private String linkPhoto;

    @Size(min = 5, max = 255, message = "Длина должна быть от 5 до 255")
    @NotNull(message = "Адрес не может быть пустым")
    private String address;

    private Date date;

    @Size(min = 5, max = 255, message = "Длина должна быть от 5 до 255")
    private String type;

    private boolean deleted = false;

    @ManyToOne
    private User user;


    public Property() {

    }
    public Property(long id, String title, String address, long price, String type, String linkPhoto, String description, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.linkPhoto = linkPhoto;
        this.address = address;
        this.date = date;
        this.type = type;

    }

    public Property(String title, String address, long price, String type, String linkPhoto, String description, Date date) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.linkPhoto = linkPhoto;
        this.address = address;
        this.date = date;
        this.type = type;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDateString() {
        int year = date.getYear() + 1900; // Получение года (добавляем 1900, так как метод getYear() возвращает год смещенный на 1900)
        int month = date.getMonth() + 1; // Получение месяца (добавляем 1, так как метод getMonth() возвращает месяц смещенный относительно января)
        int day = date.getDate(); // Получение числа (дня месяца)

        return day + "." + month + "." + year;
    }
}
