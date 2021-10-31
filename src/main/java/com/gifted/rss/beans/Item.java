package com.gifted.rss.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String title;
    @Column(length = 5000)
    private String description;
    private Date pubDate;

    public Item() {}

    public Item(String title, String description, Date pubDate) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setNewValues(String description, Date pubDate) {
        this.description = description;
        this.pubDate = pubDate;
    }

}
