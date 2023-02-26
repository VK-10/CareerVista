package com.example.carrervista;

public class CourseModel {

    String Name, Duration, Instructor, Rating, Image;

    public CourseModel() {}

    public CourseModel(String name, String duration, String instructor, String rating, String image) {
        this.Name = name;
        this.Duration = duration;
        this.Instructor = instructor;
        this.Rating = rating;
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
