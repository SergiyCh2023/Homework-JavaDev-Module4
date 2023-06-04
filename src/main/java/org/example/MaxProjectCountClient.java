package org.example;

public class MaxProjectCountClient {
    public int getProjectCount() {
        return projectCount;
    }

    private String name;
    private int projectCount;


    public MaxProjectCountClient( String name, int projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }


    @Override
    public String toString() {
        return "MaxProjectCountClient{" +
                "name='" + name + '\'' +
                ", projectCount=" + projectCount +
                '}';
    }
}