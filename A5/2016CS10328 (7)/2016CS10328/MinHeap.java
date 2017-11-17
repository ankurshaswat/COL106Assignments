//package com.company;

import java.util.HashMap;
import java.util.Vector;

public class MinHeap {

    private Vector<Pair> heap;
    private HashMap<String,Integer> arrayPoints,distances,pathLengths;

    public MinHeap() {
        heap=new Vector<>();
        arrayPoints=new HashMap<>();
        distances=new HashMap<>();
        pathLengths=new HashMap<>();
        //Dummy Data
        heap.add(new Pair("dummy",0,0));
    }

    public Pair getMin(){
        if(heap.size()==2){
            return heap.remove(1);
        }else{

            Pair changed=heap.remove(heap.size()-1);
            Pair ret=heap.get(1);
            heap.set(1,changed);
            arrayPoints.put(changed.getData(),1);
            percolateDown(1);
            arrayPoints.remove(ret.getData());
            distances.remove(ret.getData());
            pathLengths.remove(ret.getData());
            return ret;
        }
    }

    public void insert(String string,int data,int pathLen){
        arrayPoints.put(string,heap.size());
        distances.put(string,data);
        pathLengths.put(string,pathLen);
        heap.add(new Pair(string,data,pathLen));
        bubbleUp(heap.size()-1);
    }

    public boolean checkExistence(String s){
        return arrayPoints.get(s)!=null;
    }

    public int getDistance(String s){
        return distances.get(s);
    }

    private void bubbleUp(int i){
        int distance=heap.get(i).getDistance();
        int pathLen=heap.get(i).getPathLen();
        while (i>1) {
            if(i/2<1){
                break;
            }else{
                if(distance<heap.get(i/2).getDistance() || (distance==heap.get(i/2).getDistance() && pathLen<heap.get(i/2).getPathLen())){
                    exchange(i,i/2);
                    i=i/2;
                }else{
                    break;
                }
            }

        }
    }

    private void percolateDown(int i){
        int distance=heap.get(i).getDistance();
        int pathLen=heap.get(i).getPathLen();
        int n=heap.size()-1;
        while (i<heap.size()) {
            if(2*i>n){
                break;
            }else if(2*i == n){
                if(distance>heap.get(2*i).getDistance() || (distance==heap.get(2*i).getDistance() && pathLen>heap.get(2*i).getPathLen())){
                    exchange(i,2*i);
                    i=2*i;
                }else{
                    break;
                }
            }else{
                int j;
                if(heap.get(2*i).getDistance()<heap.get(2*i+1).getDistance()){
                    j=2*i;
                }else if(heap.get(2*i).getDistance()>heap.get(2*i+1).getDistance()){
                    j=2*i+1;
                }else{
                    if(heap.get(2*i).getPathLen()<heap.get(2*i+1).getPathLen()){
                        j=2*i;
                    }else{
                        j=2*i+1;
                    }
                }
                if(heap.get(j).getDistance()<distance || (distance==heap.get(j).getDistance() && heap.get(j).getPathLen()<pathLen)){
                    exchange(j,i);
                    i=j;
                }else{
                    break;
                }
            }
        }
    }

    public void decreaseValueTo(String s,int newValue,int pathLen){
        int pos=arrayPoints.get(s);
        heap.get(pos).setDistance(newValue);
        heap.get(pos).setPathLen(pathLen);
        distances.put(s,newValue);
        pathLengths.put(s,pathLen);
        bubbleUp(pos);
    }

    private void exchange(int a,int b){
        Pair aa=heap.get(a);
        arrayPoints.put(aa.getData(),b);
        arrayPoints.put(heap.get(b).getData(),a);
        heap.set(a,heap.get(b));
        heap.set(b,aa);
    }

    public int size(){
        return heap.size()-1;
    }

    public int getPathLen(String node) {
        return pathLengths.get(node);
    }
}
