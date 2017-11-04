package col106.a3;

public class MyIterator<E> {

  private Node<E> current, prev;

  public MyIterator(LinkedList<E> l) {
    current = l.getFirst();
  }

  public boolean hasNext() {
    return current != null;
  }

  public E getNext() {

    if (hasNext()) {
      E temp = current.getElement();
      //            prev2prev = prev;
      prev = current;
      current = current.getNext();
      return temp;
    }
    return null;
  }

  public Node<E> getNextNode() {

    if (hasNext()) {
      Node<E> temp = current;
      //            prev2prev = prev;
      prev = current;
      current = current.getNext();
      return temp;
    }
    return null;
  }

  // public E peek(){
  //        if(hasNext()) {
  //                E temp=current.getElement();
  //                return temp;
  //        } return null;
  // }

  // public void replaceNextAndMove(E data){
  //        if(hasNext()) {
  //                current.setElement(data);
  //                current=current.getNext();
  //        }
  //
  // }
  //
  // public void replace(E data){
  //        if(hasNext()) {
  //                current.setElement(data);
  //                // current=current.getNext();
  //        }
  // }

  public Node<E> getNode() {
    return prev;
  }

  public Node<E> getPrevNode() {
    return prev.getPrev();
  }

  //    public void insertNext(){
  //
  //    }

  public void insertBefore(E data, LinkedList<E> l) {

    Node<E> prev2prev = prev.getPrev();
    Node<E> x = new Node<>(data, prev, prev2prev);
    prev.setPrev(x);
    if (prev2prev != null) {
      //            System.out.println("doing thid");
      prev2prev.setNext(x);
      //            System.out.println(prev2prev.getElement().toString()+"yo");
    } else {
      //            System.out.println("or this");
      l.setHead(x, null);
    }

    //        MyIterator<E> a=new MyIterator<>(l);
    //        while(a.hasNext())
    //        System.out.println(a.getNext());
  }

  public void insertAfter(E data, LinkedList<E> l) {

    Node<E> x = new Node<>(data, current, prev);
    prev.setNext(x);
    if (current != null) {
      current.setPrev(x);
    } else {
      l.setTail(null, x);
    }
  }

  public void delete(LinkedList<E> l) {

    Node<E> prev2prev = prev.getPrev();

    if (current != null) {
      //            if(current.getNext()==null){
      //                l.setTail(null,prev);
      //            }
      current.setPrev(prev2prev);
    } else {
      if (prev2prev != null) {
        l.setTail(null, prev2prev);
      }
    }

    if (prev2prev == null) {
      if (current != null) {

        l.setHead(current, null);
      }
    } else {
      prev2prev.setNext(current);
      //            if(current!=null){
      //                current.setPrev(prev2prev);
      //            }
    }
  }

  public void set(E keyValuePair) {

    prev.setElement(keyValuePair);
  }
}
