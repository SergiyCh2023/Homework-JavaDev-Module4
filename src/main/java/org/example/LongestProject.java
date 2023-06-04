package org.example;

public class LongestProject {


    private int id;
    private int lenghtProject;

    public LongestProject(int id, int lenghtProject) {
        this.id = id;
        this.lenghtProject = lenghtProject;
    }


    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", lenghtProject=" + lenghtProject +
                '}';
    }
}
