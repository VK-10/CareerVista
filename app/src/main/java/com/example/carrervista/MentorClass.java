package com.example.carrervista;

public class MentorClass {

    String imageview1, textview1, textview2, textview3, textview4;

    public MentorClass(){}

    public MentorClass(String imageview1,String textview1,String textview2,String textview3,String textview4){
        this.imageview1 = imageview1;
        this.textview1 = textview1;
        this.textview2 = textview2;
        this.textview3 = textview3;
        this.textview4 = textview4;
    }

    public String getImageview1() {
        return imageview1;
    }
    public void setTextview1(String textview1){
        this.textview1=textview1;
    }

    public String getTextview1() {
        return textview1;
    }
    public void setTextview2(String textview2){
        this.textview2=textview2;
    }

    public String getTextview2() {
        return textview2;
    }

    public void setTextview3(String textview3){
        this.textview3=textview3;
    }

    public String getTextview3() {
        return textview3;
    }
    public void setTextview4(String textview4){
        this.textview4=textview4;
    }

    public String getTextview4() {
        return textview4;
    }
}