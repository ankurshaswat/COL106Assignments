//package com.company;

public class Pair {
    private String data;
    private int distance,pathLen;

    public Pair(String data, int distance, int pathLen) {
        this.data = data;
        this.distance = distance;
        this.pathLen=pathLen;
    }

    public String getData() {
        return data;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPathLen() {
        return pathLen;
    }

    public void setPathLen(int pathLen) {
        this.pathLen = pathLen;
    }
}
