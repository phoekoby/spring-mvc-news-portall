package ru.dataart.course.newsspring.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "category")
    private String category = "none";

    public News(String title, String body, LocalDateTime date, String category) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.category = category;
    }
    public News(String title, String body, LocalDateTime date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }
}
