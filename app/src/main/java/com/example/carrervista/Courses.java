package com.example.carrervista;

import java.util.List;

public class Courses {

    String courseId,title,duration,rating,instructor,useremail,coursename;

    public Courses()
    {

    }

    public Courses(String courseId, String title, String duration, String rating, String instructor, String useremail) {
        this.courseId = courseId;
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.instructor = instructor;
        this.useremail = useremail;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", rating='" + rating + '\'' +
                ", instructor='" + instructor + '\'' +
                ", useremail='" + useremail + '\'' +
                ", coursename='" + coursename + '\'' +
                '}';
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }



}
