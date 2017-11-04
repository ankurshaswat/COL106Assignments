public class ArrayDequeue implements DequeInterface {

private int N=1,first_pos=0,last_pos=0;
private Object[] arr=new Object[1];
private boolean full=false;

public void insertFirst(Object o){
        if(full==true) {
                Object[] temp_arr=new Object[2*N];
                int j=0;
                for (int i=first_pos; j<N; i=(i+1)%N,j++ ) {
                        temp_arr[j]=arr[i];
                }
                N=2*N;
                last_pos=j%N;
                temp_arr[N-1]=o;
                first_pos=(N-1);
                arr=temp_arr;
                full=(first_pos==last_pos);
        }else{
                first_pos=(first_pos-1+N)%N;
                arr[first_pos]=o;
                full=(first_pos==last_pos);
        }
}

public void insertLast(Object o){
        if(full==true) {
                Object[] temp_arr=new Object[2*N];
                int j=0;
                for (int i=first_pos; j<N; i=(i+1)%N,j++ ) {
                        temp_arr[j]=arr[i];
                }
                N=2*N;
                temp_arr[j%N]=o;
                last_pos=(j+1)%N;
                first_pos=0;
                arr=temp_arr;
                full=(first_pos==last_pos);
        }else{
                arr[last_pos]=o;
                last_pos=(last_pos+1)%N;
                full=(first_pos==last_pos);
        }
}

public Object removeFirst() throws EmptyDequeException {
        if(isEmpty()) {
                throw new EmptyDequeException("Deque Is Empty");
        }else{
                first_pos=(first_pos+1)%N;
                full=false;
                return arr[(first_pos+N-1)%N];
        }
}

public Object removeLast() throws EmptyDequeException {
        if(isEmpty()) {
                throw new EmptyDequeException("Deque Is Empty");
        }else{
                last_pos=(last_pos+N-1)%N;
                full=false;
                return arr[last_pos];
        }
}
public Object first() throws EmptyDequeException {
        if(isEmpty()) {
                throw new EmptyDequeException("Deque Is Empty");
        }else{
                return arr[first_pos];
        }
}

public Object last() throws EmptyDequeException {
        if(isEmpty()) {
                throw new EmptyDequeException("Deque Is Empty");
        }else{
                return arr[(last_pos+N-1)%N];
        }
}

public int size(){
        if(full==true) {
                return N;
        }
        return((last_pos-first_pos+N)%N);
}

public boolean isEmpty(){
        return((first_pos==last_pos) && !full);
}

public String toString(){
        StringBuilder sb=new StringBuilder("[");
        if(full==true) {
                for (int i=first_pos,j=0; j<N; i=(i+1)%N,j++ ) {
                        if(i==first_pos) {
                                sb.append(arr[i]);
                        }else{
                                sb.append(", "+arr[i]);
                        }
                }
        }
        else{
                for (int i=first_pos; i!=last_pos; i=(i+1)%N ) {
                        if(i==first_pos) {
                                sb.append(arr[i]);
                        }else{
                                sb.append(", "+arr[i]);
                        }
                }
        }
        sb.append("]");
        return(sb.toString());
}


public static void main(String[] args){
        int N = 10;
        DequeInterface myDeque = new ArrayDequeue();
        for(int i = 0; i < N; i++) {
                myDeque.insertFirst(i);
                myDeque.insertLast(-1*i);
        }

        int size1 = myDeque.size();
        System.out.println("Size: " + size1);
        System.out.println(myDeque.toString());

        if(size1 != 2*N) {
                System.err.println("Incorrect size of the queue.");
        }

//Test first() operation
        try{
                int first = (int)myDeque.first();
                int size2 = myDeque.size(); //Should be same as size1
                if(size1 != size2) {
                        System.err.println("Error. Size modified after first()");
                }
        }
        catch (EmptyDequeException e) {
                System.out.println("Empty queue");
        }

//Remove first N elements
        for(int i = 0; i < N; i++) {
                try{
                        int first = (Integer)myDeque.removeFirst();
                }
                catch (EmptyDequeException e) {
                        System.out.println("Cant remove from empty queue");
                }

        }


        int size3 = myDeque.size();
        System.out.println("Size: " + myDeque.size());
        System.out.println(myDeque.toString());

        if(size3 != N) {
                System.err.println("Incorrect size of the queue.");
        }

        try{
                int last = (int)myDeque.last();
                int size4 = myDeque.size(); //Should be same as size3
                if(size3 != size4) {
                        System.err.println("Error. Size modified after last()");
                }
        }
        catch (EmptyDequeException e) {
                System.out.println("Empty queue");
        }

//empty the queue  - test removeLast() operation as well
        while(!myDeque.isEmpty()) {
                try{
                        int last = (int)myDeque.removeLast();
                }
                catch (EmptyDequeException e) {
                        System.out.println("Cant remove from empty queue");
                }
        }

        int size5 = myDeque.size();
        if(size5 != 0) {
                System.err.println("Incorrect size of the queue.");
        }


}

}
