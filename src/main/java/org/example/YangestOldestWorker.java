package org.example;

import org.h2.store.Data;

import java.sql.Date;

public class YangestOldestWorker {
    private String category;
    private String name;
    private Date date;


    public YangestOldestWorker(String category, String name, Date date) {
        this.category = category;
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "YangestOldestWorker{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", data=" + date +
                '}';
    }





}
