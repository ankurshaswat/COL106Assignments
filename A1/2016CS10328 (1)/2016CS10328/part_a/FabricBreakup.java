import java.util.Scanner;
import java.util.EmptyStackException;
import java.io.File;
import java.io.FileNotFoundException;

public class FabricBreakup {

private int top_fav,counter;
private MyStack<Integer> num_to_topple,favourites;

public FabricBreakup(){
        num_to_topple=new MyStack<Integer>();
        favourites=new MyStack<Integer>();
        top_fav=counter=-1;
}

public void move_in(int score){
        if(top_fav==-1) {
                top_fav=score;
                counter=0;
        }
        else if(score>=top_fav) {
                favourites.push(top_fav);
                num_to_topple.push(counter);
                top_fav=score;
                counter=0;
        }else{
                counter++;
        }
}

public int party(){
        int temp=counter;
        if(favourites.size()==0) {
                if(top_fav==-1) {
                        return -1;
                }else{
                        top_fav=counter=-1;
                }
        }else{
                counter=num_to_topple.pop();
                top_fav=favourites.pop();
        }
        return temp;
}

class MyStack<E>{

private Node<E> head=null,tail=null;
private int size=0;

public void push(E e){
        if(head==null) {
                head=tail=new Node<E>(e,null);
        }else{
                tail.setNext(new Node<E>(e,null));
                tail=tail.getNext();
        }
        size++;
}
public E pop() throws EmptyStackException {
        if(isEmpty()) {
                throw new EmptyStackException();
        }
        E temp=head.getElement();
        head=head.getNext();
        size--;
        return temp;
}

public boolean isEmpty(){
        return(size==0);
}

public int size(){
        return size;
}
class Node<E>{
private E data;
private Node<E> next;

public Node (E data,Node<E> next){
        this.data=data;
        this.next=next;
}

public E getElement(){
        return data;
}
public void setElement(E data){
        this.data=data;
}
public Node<E> getNext(){
        return next;
}
public void setNext(Node<E> next){
        this.next=next;
}

}

}
public static void main(String[] args){


        File file = new File(args[0]);
        try{
                Scanner sc = new Scanner(file);
                // Scanner sc=new Scanner(System.in);
                FabricBreakup pile=new FabricBreakup();
                int n=sc.nextInt(),j=0,id;

                for (int i=1; i<=n; i++) {
                        j=sc.nextInt();
                        if(i!=j) {
                                System.out.println("Error in line number of input");
                        }else{
                                id=sc.nextInt();
                                switch(id) {
                                case 1:
                                        pile.move_in(sc.nextInt());
                                        break;
                                case 2:
                                        System.out.println(i+" "+pile.party());
                                        break;
                                default:
                                        System.out.println("Error in id number.");
                                }

                        }
                }
        }catch(FileNotFoundException f) {
                System.out.println("FILE NOT FOUND!!!!");
        }


}

}
