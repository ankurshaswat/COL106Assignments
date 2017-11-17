//package com.company;

import java.io.*;
import java.util.*;

public class Puzzle {

    private MinHeap minHeap;
    private HashMap<String, LinkedList<String>> adjList1, adjList2;
    private HashMap<String, Integer> visitedCloud;
    private HashMap<String,String> previous;
    private HashSet<String> added;
    private int[] weights;
    private String startState;

    private void addNewNode(String newS, HashMap<String, LinkedList<String>> adjList, LinkedList<String> list, Stack<String> stack) {
        if (adjList.get(newS) == null) {
            adjList.put(newS, new LinkedList<>());
            list.add(newS);
            stack.push(newS);
        } else {
            list.add(newS);
        }
    }

    private HashMap<String, LinkedList<String>> constructGraph(String state) {
        Stack<String> stack = new Stack<>();
        HashMap<String, LinkedList<String>> adjList = new HashMap<>();
        adjList.put(state, new LinkedList<>());
        stack.push(state);
        while (!stack.empty()) {
            String node = stack.pop();
            LinkedList<String> list = adjList.get(node);
            int pos = node.indexOf('G');
            if (pos - 3 >= 0) {
                String newS = node.substring(0, pos - 3) + 'G' + node.substring(pos - 2, pos) + node.charAt(pos - 3) + node.substring(pos + 1);
                addNewNode(newS, adjList, list, stack);
            }
            if (pos + 3 < 9) {
                String newS = node.substring(0, pos) + node.charAt(pos + 3) + node.substring(pos + 1, pos + 3) + 'G' + node.substring(pos + 4);
                addNewNode(newS, adjList, list, stack);
            }
            switch (pos % 3) {
                case 0:
                    String newS = node.substring(0, pos) + node.charAt(pos + 1) + 'G' + node.substring(pos + 2);
                    addNewNode(newS, adjList, list, stack);
                    break;
                case 1:
                    newS = node.substring(0, pos) + node.charAt(pos + 1) + 'G' + node.substring(pos + 2);
                    addNewNode(newS, adjList, list, stack);
                    newS = node.substring(0, pos - 1) + 'G' + node.charAt(pos - 1) + node.substring(pos + 1);
                    addNewNode(newS, adjList, list, stack);
                    break;
                case 2:
                    newS = node.substring(0, pos - 1) + 'G' + node.charAt(pos - 1) + node.substring(pos + 1);
                    addNewNode(newS, adjList, list, stack);
                    break;
            }
        }
        return adjList;
    }

    private void initialize(String startState,int[] weights) {
        boolean equal=true;

        if(!startState.equals(this.startState)){
            equal=false;
        }else{
            if(this.weights!=null){
                for (int i=0;i<8;i++){
                    if(weights[i]!=this.weights[i]){
                        equal=false;
                        break;
                    }
                }
            }else{
                equal=false;
            }
        }
        if(!equal){
            this.startState=startState;
            this.weights=weights;
            minHeap = new MinHeap();
            minHeap.insert(startState,0,0);
            visitedCloud = new HashMap<>();
            previous=new HashMap<>();
            added=new HashSet<>();
            added.add(startState);
        }
    }

    private String findPath(String startState, String stopState, int[] weights) {
        HashMap<String, LinkedList<String>> adjList;
        if(adjList1==null){
            adjList1=constructGraph(startState);
            adjList=adjList1;
        }else{
            if(adjList1.get(startState)==null){
                if(adjList1.get(stopState)!=null){
                    return ("-1 -1\n\n");
                }
                if(adjList2==null){
                    adjList2=constructGraph(startState);
                }
                adjList=adjList2;
            }else{
                adjList=adjList1;
            }
        }
        if (adjList.get(stopState)==null) {
            return ("-1 -1\n\n");
        } else {
            if (adjList1.get(startState) != null) {
                adjList = adjList1;
            } else {
                adjList = adjList2;
            }
            initialize(startState,weights);
            while (minHeap.size() != 0 && visitedCloud.get(stopState)==null) {
                Pair temp=minHeap.getMin();
                String name=temp.getData();
                int index=name.indexOf('G');
                int dist=temp.getDistance();
                int pathLen=temp.getPathLen();
                visitedCloud.put(name, dist);
                if (name.equals(stopState)) {
                    break;
                }
                for (String x:adjList.get(name)){
                    if(!added.contains(x)){
                        minHeap.insert(x,Integer.MAX_VALUE,Integer.MAX_VALUE);
                        added.add(x);
                    }
                }
                for (String node : adjList.get(name)) {
                    if(minHeap.checkExistence(node)){
                        int init_dist=minHeap.getDistance(node);
                        int init_pathLen=minHeap.getPathLen(node);
                        int new_dist = dist + edge_weight(index, node);
                        if (new_dist < init_dist || (new_dist==init_dist && pathLen+1<init_pathLen)) {
                            minHeap.decreaseValueTo(node,new_dist,pathLen+1);
                            previous.put(node,name);
                        }
                    }
                }
            }
            StringBuilder sb=new StringBuilder("");
            int i=0;
            String state=""+stopState;
            if(!state.equals(startState)){
                sb.insert(0, edge_movement(previous.get(state),state));
                i++;
                state=previous.get(state);
            }
            while(!state.equals(startState)){
                sb.insert(0, edge_movement(previous.get(state),state)+" ");
                i++;
                state=previous.get(state);
            }
            sb.append('\n');
            sb.insert(0,'\n');
            sb.insert(0,visitedCloud.get(stopState));
            sb.insert(0," ");
            sb.insert(0,i);
            return sb.toString();
        }
    }

    private int edge_weight(int index, String node) {
        return weights[Integer.parseInt(node.charAt(index) + "") - 1];
    }

    private String edge_movement(String first,String second){
        int a=first.indexOf('G'),b=second.indexOf('G');
        if(a==b+1){
            return second.charAt(a)+"R";
        }else if(a==b-1){
            return second.charAt(a)+"L";
        }else if(a==b-3){
            return second.charAt(a)+"U";
        }else{
            return second.charAt(a)+"D";
        }
    }

    public static void main(String[] args) {
  //      long time=System.currentTimeMillis();
        Scanner sc = null;
        Puzzle graph = new Puzzle();
        try {
            FileWriter writer = new FileWriter(new File(args[1]));
            sc = new Scanner(new FileReader(args[0]));
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                String[] data = sc.nextLine().split(" ");
                int j = 0;
                int[] num = new int[8];
                for (String data_num : sc.nextLine().split(" ")) {
                    num[j] = Integer.parseInt(data_num);
                    j++;
                }
                writer.write(graph.findPath(data[0], data[1], num));
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file " + args[0] + " not found");
            System.exit(1);
        }catch (IOException e){
            System.out.println("IO Exception");
            System.exit(1);
        }finally {
            if (sc != null) {
                sc.close();
            }
        }
//        System.out.println(System.currentTimeMillis()-time+" ms");
    }
}
