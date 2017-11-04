//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Arrays;
//import java.util.EmptyStackException;
import java.util.Vector;

//import java.lang.reflect.Array;
//import java.util.Objects;

public class Anagram {

    private class Node<E, T> {
        private E key;
        private T value;
        private boolean isComplete;
        private Node<E, T> next;

        public Node(E key, T value, Node<E, T> next, boolean isComplete) {
            this.key = key;
            this.value=value;
            this.isComplete = isComplete;
            this.next = next;
        }

//public E getElement(){
//        return data;
//}
//public void setElement(E data){
//        this.data=data;
//}


//    public void setKey(E key) {
//        this.key = key;
//    }


//    public void setValue(T value1) {
//        this.value = value1;
//    }



//    public void setComplete(boolean complete) {
//        isComplete = complete;
//    }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete() {
            isComplete = true;
        }

        public E getKey() {
            return key;
        }

//        public void setKey(E key) {
//                this.key = key;
//        }

        public T getValue() {

            return value;
        }

//        public void setValue(T value) {
//                this.value = value;
//        }

        public Node<E, T> getNext() {
            return next;
        }

        public void setNext(Node<E, T> next) {
            this.next = next;
        }
    }


    private Node<String, Vector<String>>[][] store;
//    private Vector<String>[] singleWords, doubleWords;

    public Anagram() {


        store = new Node[13700][361];
//        store = new Node[13700][12961];
//        store = new Node[13700][466561];
//        singleWords= new Vector[10];
//        doubleWords = new Vector[10];
//        for (int i=0;i<36370;i++){

//        }

    }

    private int primaryHash(String sorted) {
//        System.out.println("sorted = [" + sorted + "], len = [" + len + "]");
        int char1 = convert2int(sorted.charAt(0)), char2 = convert2int(sorted.charAt(1));

//        if (char1 < 0 || char2 < 0) {
//            return -1;
//        }

        return (char1 * 37 + char2) * 10 + sorted.length() - 3;
    }

    private int secondaryHash(String s) {
        int sum = 0;
//        int converted;
        for (int i = 2; i < s.length(); i++) {
//            converted = convert2int(s.charAt(i));
//            if (converted < 0) {
//                return -1;
//            } else {
                sum += convert2int(s.charAt(i));
//                sum =(sum + convert2int(s.charAt(i))*convert2int(s.charAt(i)))%359;
//                Math.pow(convert2int(s.charAt(i)),2);
//                sum =(sum + (int) Math.pow(convert2int(s.charAt(i)),2));
//            }
        }
        return sum;
    }
//    private int secondaryHash(String sorted,int len){

//    }

    private int convert2int(char c) {

        /* Mapping the characters to lower numbers to reduce space required **/

        if (Character.isLetter(c)) {
//            System.out.println();
            return Character.getNumericValue(c) - Character.getNumericValue('a');
        } else if (Character.isDigit(c)) {
            return Character.getNumericValue(c) - Character.getNumericValue('0') + 26;
//        } else if (c == 39) {

        }else{
            return 36;
//        } else {
//            System.out.println(c+" this is char");
//            return -1;
//            throw new RuntimeException("Not Implemented");
        }

    }

    private String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }


    private Vector<String> findAnagrams(String word) {
//        if(word.equals("waxen")){
//            System.out.println("hola");
//        }

        String sorted = sortString(word);
//        int len = sorted.length();
//        if(len>12){
//            return;
//        }
//        System.out.println(sorted+"=sorted");
        int pos = primaryHash(sorted);
        int sum = secondaryHash(sorted);

//        System.out.println(pos+"=pos,"+sum+"=sum");

        if (store[pos][sum] != null) {
//            return;
//        } else {
            Node<String, Vector<String>> x = store[pos][sum];
            while (x != null) {
//                if(word.equals("waxen")){
//                    System.out.println("hola");
//                }
                if (x.getKey().compareTo(sorted) == 0) {

                    return x.getValue();
//                    Vector<String> list = x.getValue();
//                    for (String aList : list) {
//                        System.out.println(aList);
//                    }
//                    return new Vector<>();
                } else if (x.getKey().compareTo(sorted) > 0) {
//                    if(word.equals("waxen")){
//                        System.out.println("hola2");
//                    }
                    return new Vector<>();
                } else {
//                    if(word.equals("waxen")){
//                        System.out.println("hola3");
//                    }
                    x = x.getNext();
                }
//                if (x.getNext() == null) {
//                    break;
//                } else {
//                    x = x.getNext();
//                }

            }

//            if (x.getKey().equals(sorted)) {

//                x.getValue().add(vocabWord);
//            } else {
//                return;
//            }
        }

        return new Vector<>();
    }

    private void add2vocab(String vocabWord, String sorted) {

//        if(vocabWord.equals("geld heir that")){
//            System.out.println("hola");
//        }
//        if(vocabWord.equals("that geld heir")){
//            System.out.println("hola2");
//        }
        /* This function adds the word to vocabulary and tells if the word has been successfully inserted or not**/


//        System.out.println(convert2int('e'));
//        System.out.println(vocabWord+" adding this to vocab");

//        if (sorted == null) {
//            sorted = sortString(vocabWord);
//        }
//        int len = sorted.length();
//        if (len > 12) {

//            System.out.println(
//                    "hi"
//            );
//            return;
//        }
//        System.out.println(sorted+"=sorted , len="+len);
        int pos = primaryHash(sorted);
        int sum = secondaryHash(sorted);

//        if (sum < 0 || pos < 0) {
//            System.out.println("returning");
//            return;
//        }

//        System.out.println(pos+"=pos,"+sum+"=sum");

        if (store[pos][sum] == null) {

            Vector<String> x = new Vector<>();
            x.add(vocabWord);
//            System.out.println("in");
            store[pos][sum] = new Node<>(sorted, x, null, false);
        } else {

            Node<String, Vector<String>> x = store[pos][sum];
            while (x != null) {
//                System.out.println("java");
                if (x.getKey().compareTo(sorted) == 0) {

                    Vector<String> vec = x.getValue();

//                    if(isComplete){
//                        x.setComplete();
//                    }
                    //add without order and without checking duplicates
//                    vec.add(vocabWord);
//                    System.out.println(vocabWord);
//                    return true;

                    // add here in lexicographic order
                    int insert_position = searchPos(vec, vocabWord);
                    if (insert_position >= 0) {
//                        System.out.println("in");
                        vec.insertElementAt(vocabWord, insert_position);
//                        System.out.println(vocabWord);
                        return;
                    } else {

                        return;
                    }
//                    if(vec.size()>2 ) {


//                        System.out.println(vec.size()+" heryyyyyyyyyyyyyy "+vocabWord);
//                        System.out.println(vec.toString());
//                    }

//                    return;
                } else if (x.getKey().compareTo(sorted) < 0) {

                    if (x.getNext() == null || x.getNext().getKey().compareTo(sorted) > 0) {
                        Vector<String> vec = new Vector<>();
                        vec.add(vocabWord);
//                        System.out.println("collision "+pos+" "+sum);
//                        System.out.println("in");
                        x.setNext(new Node<>(sorted, vec, x.getNext(), false));
//                        System.out.println(vocabWord);
                        return;
                    }
                    x = x.getNext();
                } else {
//                    if(vocabWord.equals("waxen")){
//                        System.out.println("hola11");
////                System.out.println(x.getValue());
//                    }
                    Vector<String> vec = new Vector<>();
                    vec.add(vocabWord);
//                    System.out.println("collision "+pos+" "+sum);
//                    System.out.println("in");
                    store[pos][sum] = new Node<>(sorted, vec, x, false);
//                    System.out.println(vocabWord);
                    return;
                }
//                else{
//                    x=x.getNext();
//                }

//                if(x.getNext()==null){
//                    break;
//                }else{
//                    x=x.getNext();
//                }

            }

//            if(x.getKey().equals(sorted)){
//
//            }else{
//                Vector<String> y = new Vector<>();
//                y.add(vocabWord);
//                x.setNext(new Node<>(sorted, y, null));
//            }
//            if(x==null){
//            }
//            System.out.println("Program shouldn't be here");
        }
    }

    private int searchPos(Vector<String> vec, String string) {

//        Scanner s=new Scanner(System.in);

        switch (vec.size()) {
            case 0:
                return 0;
            case 1:
                int comp = vec.get(0).compareTo(string);
                if (comp > 0) {
                    return 0;
                } else if (comp < 0) {
                    return 1;
                } else {
//                    System.out.println("dup" +string);

//                    if(spaces(string)>0){

//                        throw new EmptyStackException();
//                    }
                    return -1;
                }
            default:

                int start = 0;
                int end = vec.size() - 1;
                int mid;


                while (true) {

                    mid = (end + start) / 2;

                    int comparison1 = string.compareTo(vec.get(start)), comparison2 = string.compareTo(vec.get(end));

                    if (comparison1 < 0) {
                        return start;
                    } else if (comparison1 == 0) {

//                        System.out.println("dup "+string);

//                        if(spaces(string)>0)
//                        {
//                            throw new EmptyStackException();
//                        }

                        return -1;
                    } else if (comparison2 == 0) {
//                        System.out.println("dup "+string);

//                        if(spaces(string)>0)
//                        {
//                            throw new EmptyStackException();
//                        }

                        return -1;
                    } else if (comparison2 > 0) {
                        return end + 1;
                    } else {


                        if (string.compareTo(vec.get(mid)) == 0) {
//                            System.out.println("dup "+string);

//                            if(spaces(string)>0)
//                            {
//                                throw new EmptyStackException();
//                            }

                            return -1;
                        } else if (string.compareTo(vec.get(mid)) > 0) {
                            start = mid + 1;
                        } else {
                            end = mid - 1;
                        }
                    }
                }


        }


//        if(vec.size()==0){
//            return 0;
//        }else{
//
//        }
    }

    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();

//        System.out.println();

//        File vocab = new File(args[0]), input = new File(args[1]);
        try {
            Vector<String> x;
            BufferedReader scanner=new BufferedReader(new FileReader(args[0]));
            OutputStream out = new BufferedOutputStream( System.out );

            //            Scanner scanner = new Scanner(new File(args[0]));
//            Scanner scanner2=new Scanner(System.in);
            Anagram hasher = new Anagram();
//            System.out.println(hasher.convert2int('e'));
//            throw new RuntimeException("s");
            int n = Integer.parseInt( scanner.readLine());
            String sorted;
//            int len;
            String vocabWord, word;
            for (int i = 0; i < n; i++) {
                vocabWord = scanner.readLine();
//                len=vocabWord.length();
                sorted = hasher.sortString(vocabWord);
//                int pos = hasher.primaryHash(sorted, len);
//                int sum = hasher.secondaryHash(sorted, len);
                hasher.add2vocab(vocabWord, sorted);
            }
//            long time3=System.currentTimeMillis()-startTime;
//            System.out.println("time for vocabulary creation : "+time3+" millis");
//            System.exit(0);
//            scanner = new Scanner(new File(args[1]));
            scanner = new BufferedReader(new FileReader(args[1]));
//            n = scanner.nextInt();
            n = Integer.parseInt( scanner.readLine());

//            Vector<String> anagrams;
//            long startTime3=System.currentTimeMillis();

            for (int i = 0; i < n; i++) {
//                long startTime2=System.currentTimeMillis();
//                scanner2.next();
//                anagrams=new Vector<>();
                word = scanner.readLine();

//                int len = word.length();

//                if (len > 12) {
//                    continue;
//                }
//                System.out.println("Word = " + word);


//                try{
                 x = hasher.getPermutations(word);
                if(x!=null){

                        for (String aX : x) {
                            out.write((aX+"\n").getBytes());
//            System.out.println(aX);
                        }
                    }
                out.write(("-1\n").getBytes());
                out.flush();


//                print( hasher.findAnagrams(word));
                //                anagrams.addAll(hasher.findAnagrams(word));
//                anagrams.sort(String::compareToIgnoreCase);

//                System.out.println(anagrams.toString());


//                long time2=System.currentTimeMillis()-startTime2;
//                System.out.println("time to search: "+time2+" millis");
//                System.out.println("-1");

            }

//            long time4=System.currentTimeMillis()-startTime3;

//            System.out.println("total time to search: "+time4+" millis");
        } catch (Exception e) {
//            System.out.println("FILE NOT FOUND!!!!");
//        } catch (IOException e){
//            System.out.println("IO exception");
        }
//        long time = System.currentTimeMillis() - startTime;
//        System.out.println("total time: " + time + " millis");
    }

    private Node<String, Vector<String>> getNode(int pos, int sum, String sorted) {

//        System.out.println(convert2int('e'));
//        System.out.println(vocabWord+" adding this to vocab");

//        if (sorted == null) {
//            sorted = sortString(vocabWord);
//        }
//        int len = sorted.length();
//        if (len > 12) {
//            return null;
//        }
//        System.out.println(sorted+"=sorted , len="+len);
//        int pos = primaryHash(sorted, len);
//        int sum = secondaryHash(sorted, len);

//        if (sum < 0 || pos < 0) {
//            System.out.println("returning");
//            return null;
//        }

//        System.out.println(pos+"=pos,"+sum+"=sum");

        if (store[pos][sum] == null) {

            Vector<String> x = new Vector<>();
//            x.add(vocabWord);
//            System.out.println("in");
            store[pos][sum] = new Node<>(sorted, x, null, false);
        } else {

            Node<String, Vector<String>> x = store[pos][sum];
            while (x != null) {
//                System.out.println("java");
                if (x.getKey().compareTo(sorted) == 0) {
                    return x;
//                    Vector<String> vec = x.getValue();

//                    if(isComplete){
//                        x.setComplete();
//                    }
                    //add without order and without checking duplicates
//                    vec.add(vocabWord);
//                    System.out.println(vocabWord);
//                    return true;

                    // add here in lexicographic order
//                    int insert_position = searchPos(vec, vocabWord);
//                    if (insert_position >= 0) {
//                        System.out.println("in");
//                        vec.insertElementAt(vocabWord, insert_position);
//                        System.out.println(vocabWord);
//                        return;
//                    } else {
//                        return;
//                    }
//                    if(vec.size()>2 ) {


//                        System.out.println(vec.size()+" heryyyyyyyyyyyyyy "+vocabWord);
//                        System.out.println(vec.toString());
//                    }

//                    return;
                } else if (x.getKey().compareTo(sorted) < 0) {

                    if (x.getNext() == null || x.getNext().getKey().compareTo(sorted) > 0) {
                        Vector<String> vec = new Vector<>();
//                        vec.add(vocabWord);
//                        System.out.println("collision "+pos+" "+sum);
//                        System.out.println("in");
                        x.setNext(new Node<>(sorted, vec, x.getNext(), false));
//                        System.out.println(vocabWord);
                        return x.getNext();
                    }
                    x = x.getNext();
                } else {
//                    if(vocabWord.equals("waxen")){
//                        System.out.println("hola11");
////                System.out.println(x.getValue());
//                    }
                    Vector<String> vec = new Vector<>();
//                    vec.add(vocabWord);
//                    System.out.println("collision "+pos+" "+sum);
//                    System.out.println("in");
                    Node<String, Vector<String>> a = new Node<>(sorted, vec, x, false);
                    store[pos][sum] =a;
//                    System.out.println(vocabWord);
                    return a;
                }
//                else{
//                    x=x.getNext();
//                }

//                if(x.getNext()==null){
//                    break;
//                }else{
//                    x=x.getNext();
//                }

            }

//            if(x.getKey().equals(sorted)){
//
//            }else{
//                Vector<String> y = new Vector<>();
//                y.add(vocabWord);
//                x.setNext(new Node<>(sorted, y, null));
//            }
//            if(x==null){
//            }
//            System.out.println("Program shouldn't be here");
        }



//        if (store[pos][sum] != null) {
//            Node<String, Vector<String>> x = store[pos][sum];
//            while (x != null) {
//                if (x.getKey().compareTo(sorted) == 0) {
//                    return x;
//                } else if (x.getKey().compareTo(sorted) > 0) {
//                    return null;
//                } else {
//                    x = x.getNext();
//                }
//            }
//        }else{
//
//        }
        return null;
    }

    private Vector<String> getPermutations(String word) {

        String sorted = sortString(word);
        int pos = primaryHash(sorted);
        int sum = secondaryHash(sorted);
//        if (sum < 0 || pos < 0) {
//            System.out.println("returning");
//            return null;
//        }
//        System.out.println(p
        Node<String, Vector<String>> node = this.getNode(pos, sum, sorted);

//        if(node!=null && node.isComplete()){
//            System.out.println("used");
//        }

        if (node == null || !node.isComplete()) {
            this.findPermutations_withSplit( sorted);
//        }else{
//            System.out.println("remembered");

        }
        if (node == null) {
            node = this.getNode(pos, sum, sorted);
        }
        if (node != null) {
//            print(node.getValue());

            return node.getValue();
        }else{
            return null;
//            print(null);
        }

//        return null;
    }

    private boolean checkNodeComplete(String sorted) {
//        String sorted = sortString(word);
        int pos = primaryHash(sorted);
        int sum = secondaryHash(sorted);

        Node<String, Vector<String>> node = this.getNode(pos, sum, sorted);

        return node != null && node.isComplete();
    }


    private void findPermutations_withSplit( String sorted) {
        if (sorted.length() >= 6) {


//                        for (int i = 3; i <= len / 2; i++) {// this line skips some cases. Next line fixed it
            for (int i = sorted.length()/2; i>=3; i--) {
                multiAnagrams("", "", i, sorted);
            }
        }
    }

//    private void findPermutations_noSplit(int len, String sorted) {
//        if (len >= 6) {
//            for (int i = 3; i <= len / 2; i++) {
//                formAnagrams("", "", i, len, sorted);
//            }
//        }
//    }

    private void multiAnagrams(String word1, String word2, int lenToAdd, String charHeap) {
//        System.out.println("word1 = [" + word1 + "], word2 = [" + word2 + "], lenToAdd = [" + lenToAdd + "], charHeap = [" + charHeap + "]");
//if(sortString("agatha").equals(word1)){
//    sout
//}
        if (lenToAdd == 0) {

            if (charHeap.length() != 0) {
                word2 = word2 + charHeap;
//                charHeap = "";
//                len = 0;
            }
//            int length=word2.length();

            if((word1.length()<6 || word1.equals(word2)) && findAnagrams(word1).size()==0){
//                System.out.println(word1+" "+word2);
                return;
            }

//            if(findAnagrams(word1).size()==0){

//                System.out.println(word1+" "+word2);
//            }

            if (word2.length() >= 6) {

//                int length;
//                String sorted = sortString(word2);
//                int pos2 = primaryHash(sorted, length);
//                int sum2 = secondaryHash(sorted, length);
                if (!checkNodeComplete(word2)) {
                    for (int i = 3; i <= word2.length() / 2; i++) {
                        formAnagrams("", "", i, word2);
                    }
                }
            }

            if(word1.length()==word2.length() && word1.compareTo(word2)<0){
                return;
            }

            formAnagrams(word1, word2, 0, "");
            // NOTE:- add sorted parameter here or later to prevent resorting again and again
        } else {
            for (int j = 0; j < charHeap.length() - (lenToAdd - 1); j++) {

                char c = charHeap.charAt(j);
                if(j>0){
                    char d=charHeap.charAt(j-1);
                    if(d==c){
                        continue;
//                    }else{
//                        multiAnagrams(word1 + c, word2 + charHeap.substring(0, j), lenToAdd - 1,  charHeap.substring(j + 1));
                    }
                }
//                else{
                multiAnagrams(word1 + c, word2 + charHeap.substring(0, j), lenToAdd - 1,  charHeap.substring(j + 1));

//                }
//                if(j< && c==charHeap.charAt(j-1)) {
//                    continue;
//                }

//                if(j<len-(lenToAdd-1)){
//                int k;
//                System.out.println(charHeap);
//                System.out.println(charHeap.length()+" cc");
//                System.out.println(lenToAdd+ "l2a");
//                System.out.println(j+" j");

//                for( k=j+1;k<charHeap.length()-(lenToAdd-1);k++){
//                    if(charHeap.charAt(k)!=c){
//                        k--;
//                        break;
//                    }
//                }


//                String sub=charHeap;
//
//                if(k+1 > charHeap.length()){
//
//                    sub = charHeap.substring(k + 1);
//                }

//                System.out.println(k+1);
//                System.out.println(charHeap.length());

//                for(;j<charHeap.length()-(lenToAdd-1) && j<=k;j++){
//                    if(lenToAdd-(k-j+1) >=0){
//                        System.out.println(word1+charHeap.substring(j,k+1)
//                                +" , "+word2 + charHeap.substring(0, j)
//                                +","+(lenToAdd-(k-j+1))+","+sub);
//                        multiAnagrams(word1+charHeap.substring(j,k+1),word2 + charHeap.substring(0, j),lenToAdd-(k-j+1),sub);
//                    }

//                }
//                if(j==charHeap.length()-(lenToAdd-1)){
//                    break;
//                }else{
//                    j--;
//                }
//                }else{

            }
        }
//    }
    }

    private void formAnagrams(String word1, String word2, int lenToAdd, String charHeap) {



        if (lenToAdd == 0) {
            if (charHeap.length() != 0) {
                word2 = word2 + charHeap;
//                charHeap = "";
//                len = 0;
            }
//        System.out.println("word1 = [" + word1 + "], word2 = [" + word2 + "], lenToAdd = [" + lenToAdd + "], charHeap = [" + charHeap + "]");
//            System.out.println();
            if(word1.length()==word2.length() && word1.compareTo(word2)<0){
                return;
            }

            Vector<String> vec1, vec2;
            vec1 = findAnagrams(word1);
            vec2=findAnagrams(word2);



            String sorted = sortString(word1 + word2);

            int pos=primaryHash(sorted);
            int sum=secondaryHash(sorted);
            Node<String, Vector<String>> node = getNode(pos, sum, sorted);

            if(vec1.size()==0  || vec2.size()==0){
                if (node != null) {
                    node.setComplete();
                }
                return;
            }

            //change this to equality of sorted words
            if (word1.equals(word2)) {
                if (word1.length() == 6) {

                    for (int j = 0; j < vec1.size(); j++) {
                        for (String aVec1 : vec1) {

                            String w1 = vec1.get(j);

                            if ((spaces(w1) + spaces(aVec1)) < 2) {
//                                add2vocab(vec1.get(j) + " " + aVec1, sorted, true);
                                add2node(node,w1 + " " + aVec1);
                            }

                        }
                    }
                } else {
                    for (int j = 0; j < vec1.size(); j++) {
                        for (String aVec1 : vec1) {

//                            if(spaces(vec1.get(j))+spaces(vec1.get(k)) <2){
//                            add2vocab(vec1.get(j) + " " + aVec1, sorted, true);
                            add2node(node,vec1.get(j) + " " + aVec1);
                        }

                    }
//                    }
                }
            } else {

//                System.out.println(word2);
                vec2 = findAnagrams(word2);

//                int word1len = word1.length(),word2len=word2.length();


                if (word1.length() == 6 && word2.length() == 6) {
                    for (String aVec1 : vec1) {
                        for (String aVec2 : vec2) {
                            if ((spaces(aVec1) + spaces(aVec2)) < 2) {
//                            if(spaces(vec1.get(j))<2){
//                                add2vocab(aVec1 + " " + aVec2, sorted, true);
                                add2node(node,aVec1 + " " + aVec2);
//                                add2vocab(aVec2 + " " + aVec1, sorted, true);
                                add2node(node,aVec2 + " " + aVec1);
                            }
                        }
                    }
                } else if (word1.length() == 9 ) {
                    for (String aVec1 : vec1) {
                        for (String aVec2 : vec2) {
                            if (spaces(aVec1) < 2) {
//                                add2vocab(aVec1 + " " + aVec2, sorted, true);
                                add2node(node,aVec1 + " " + aVec2);
//                                add2vocab(aVec2 + " " + aVec1, sorted, true);
                                add2node(node,aVec2 + " " + aVec1);
                            }
                        }
                    }
                } else if (word2.length() == 9) {
                    for (String aVec1 : vec1) {
                        for (String aVec2 : vec2) {
                            if (spaces(aVec2) < 2) {
//                                add2vocab(aVec1 + " " + aVec2, sorted, true);
                                add2node(node,aVec1 + " " + aVec2);
//                                add2vocab(aVec2 + " " + aVec1, sorted, true);
                                add2node(node,aVec2 + " " + aVec1);
                            }
                        }
                    }
                } else {


                    for (String aVec1 : vec1) {
                        for (String aVec2 : vec2) {

//                            if((aVec1+" "+aVec2).equals("geld heir that")){
//                                System.out.println("hola");
//                            }
//
//                            if((aVec1+" "+aVec2).equals("that geld heir")){
//                                System.out.println("hola");
//                            }
//
//                            if((aVec2+" "+aVec1).equals("geld heir that")){
//                                System.out.println("hola");
//                            }
//                            if(spaces(vec2.get(j))<2){
//                            add2vocab(aVec1 + " " + aVec2, sorted, true);
                            add2node(node,aVec1 + " " + aVec2);
//                            add2vocab(aVec2 + " " + aVec1, sorted, true);
                            add2node(node,aVec2 + " " + aVec1);
//                            }
                        }
                    }



                }
            }
        } else {
            for (int j = 0; j < charHeap.length() - (lenToAdd - 1); j++) {
                char c = charHeap.charAt(j);
                if(j>0){
                    char d=charHeap.charAt(j-1);
                    if(d==c){
                        continue;
//                        formAnagrams(word1 + c, word2 + charHeap.substring(0, j), lenToAdd - 1,  charHeap.substring(j + 1));

                    }
//                    else{

//                        System.out.println("word1 = [" + word1 + "], word2 = [" + word2 + "], lenToAdd = [" + lenToAdd + "], charHeap = [" + charHeap + "]");
//                        System.out.println("skipped");

                }
//                    }
//                else{
                formAnagrams(word1 + c, word2 + charHeap.substring(0, j), lenToAdd - 1,  charHeap.substring(j + 1));

//                }


            }
        }
    }

    private void add2node(Node<String, Vector<String>> x, String vocabWord) {
        Vector<String> vec = x.getValue();

//        if(isComplete){
        x.setComplete();
//        }
        //add without order and without checking duplicates
//                    vec.add(vocabWord);
//                    System.out.println(vocabWord);
//                    return true;

        // add here in lexicographic order
        int insert_position = searchPos(vec, vocabWord);
        if (insert_position >= 0) {
//                        System.out.println("in");
            vec.insertElementAt(vocabWord, insert_position);
//                        System.out.println(vocabWord);
        }
    }

    private int spaces(String s) {

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sum++;
            }
        }
        return sum;
    }

//    private static void print(Vector<String> x) {
//
//        OutputStream out = new BufferedOutputStream( System.out );
//
//
//        try{
//            if(x!=null){
//
//                for (String aX : x) {
//                    out.write((aX+"\n").getBytes());
////            System.out.println(aX);
//                }
//            }
//            out.write(("-1\n").getBytes());
//            out.flush();
//
//        }
//        catch (IOException e){
////            System.out.println("IO Exception");
//        }
////
////        BufferedWriter out = new BufferedWriter(new OutputStreamWriter( System.out) );
////
////
////        try{
////        for (String aX : x) {
////            out.write((aX+"\n"));
//////            System.out.println(aX);
////        }
////            out.write(("-1\n"));
////            out.flush();
////
////        }
////        catch (IOException e){
////            System.out.println("IO Exception");
////        }
//
//
//
////        System.out.println(x.size());
//    }

//    private void formAnagrams(int i, int i1, String word, int len) {
//        StringBuilder sb=new StringBuilder(word);
//    }

//    private void extendVocab(String vocabWord) {
//
//
//        if(add2vocab(vocabWord,vocabWord,len)){
//
//            if(singleWords[len-3]==null){
//                singleWords[len-3]= new Vector<>();
//            }
//            singleWords[len-3].add(vocabWord);
//
//            addPermutations2Vocab(vocabWord,len);
//        }else{
//            return;
//        }
//
//    }

//    private void addPermutations2Vocab(String vocabWord,int len) {
//
//        Vector<String > words;
//        String word1,word2;
//        for (int i=0;i<9-len;i++){
//            if(singleWords[i]!=null){
//
//                words=singleWords[i];
//
//                for (String word:words){
//
//
//                    word1=vocabWord + " " + word;
//                    System.out.println(word1);
//                    if(add2vocab(vocabWord+word,word1,len + i + 3)){
//                        if(doubleWords[len+i]==null){
//                            doubleWords[len+i]= new Vector<>();
//                        }
//                        doubleWords[len+i].add(word1);
//                    }
//
//                    word2=word + " " + vocabWord;
//                    if(!vocabWord.equals(word) && add2vocab(vocabWord+word,word2,len + i + 3)){
//                        if(doubleWords[len+i]==null){
//                            doubleWords[len+i]= new Vector<>();
//                        }
//                        doubleWords[len+i].add(word2);
//                    }
//
//
//                }
//            }
//        }
//    }
}

