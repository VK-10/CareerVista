package com.example.carrervista;

public class JobModel {

    String Title, Work, Start, End, Salary;

    public JobModel() {}

    public JobModel(String title, String work, String start, String end, String salary) {
        this.Title = title;
        this.Work = work;
        this.Start = start;
        this.End = end;
        this.Salary = salary;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}