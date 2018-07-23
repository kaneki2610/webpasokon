package com.example.dell.myapplication14.Model.Object;

public class Evaluate {
    private String evaluate_id, title, content, device_name, evaluate_date;
    private int number_star, product_id;

    public Evaluate(String evaluate_id, String title, String content, String device_name, String evaluate_date, int number_star, int product_id) {

        this.evaluate_id = evaluate_id;
        this.title = title;
        this.content = content;
        this.device_name = device_name;
        this.evaluate_date = evaluate_date;
        this.number_star = number_star;
        this.product_id = product_id;
    }
    public Evaluate(){}
    public String getEvaluate_id() {
        return evaluate_id;
    }

    public void setEvaluate_id(String evaluate_id) {
        this.evaluate_id = evaluate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getEvaluate_date() {
        return evaluate_date;
    }

    public void setEvaluate_date(String evaluate_date) {
        this.evaluate_date = evaluate_date;
    }

    public int getNumber_star() {
        return number_star;
    }

    public void setNumber_star(int number_star) {
        this.number_star = number_star;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
