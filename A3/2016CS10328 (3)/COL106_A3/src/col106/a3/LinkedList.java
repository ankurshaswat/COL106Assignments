package col106.a3;

// import com.sun.org.apache.xpath.internal.SourceTree;

public class LinkedList<E> {
  private Node<E> head = null, tail = null;
  //        tail=null;

  public void insertFirst(E data) {
    if (head == null) {
      head = new Node<>(data, null, null);
      tail = head;
    } else {
      head.setPrev(new Node<>(data, head, null));
      head = head.getPrev();
    }
  }

  public void insertLast(E data) {
    if (data != null) {

      if (head == null) {
        insertFirst(data);
      } else {

        //                Node<E> temp = head;
        //                while (temp.getNext() != null) {
        //                    temp = temp.getNext();
        //                }
        tail.setNext(new Node<>(data, null, tail));
        tail = tail.getNext();
      }
    }
  }

  // public void insertLast(E data){
  //        if(head==null) {
  //                head= new Node<E>(data,null);
  ////                head=tail;
  //        }else{
  //                tail.setNext(new Node<E>(data,null));
  //                tail=tail.getNext();
  //        }
  // }

  //    public void insertAfter(Node<E> node, E data) {
  //        if (node != null) {
  //            node.setNext(new Node<E>(data, node.getNext()));
  //        }
  //    }
  //
  //    public E removeFirst() {
  //        if (head == null) {
  //            return null;
  //        }
  //
  //        E data = head.getElement();
  //        head = head.getNext();
  //        return data;
  //    }

  public E removeLast() {
    //        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    E temp;
    temp = tail.getElement();
    tail = tail.getPrev();
    tail.setNext(null);
    return temp;
  }
  //
  //    public E getAtPos(int pos) {
  //        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  //    }

  public Node<E> getFirst() {
    //        if (head.getPrev() == null) {
    return head;
    //        } else {
    //            head = head.getPrev();
    //            return getFirst();
    //        }
  }

  public Node<E> getLast() {

    return tail;

    //        if(tail==null){
    //            return null;
    //        }
    //
    //        if(tail==tail.getNext()){
    //            tail.getNext().setPrev(tail.getPrev());
    //            tail.getPrev().setNext(tail.getNext());
    //            tail=tail.getNext();
    //            return getLast();
    //        }
    //
    //        if(tail==tail.getPrev()){
    //            tail.getPrev().setNext(null);
    //            tail=tail.getPrev();
    //            return getLast();
    //        }
    //
    //        return tail;
    //
    //
    ////        if(tail==tail.getPrev()){
    ////            if(tail.getNext()!=null){
    ////                tail.getNext().setPrev(tail.getPrev());
    ////            }
    ////            tail=tail.getPrev()
    ////            return
    ////        }
    //        if(tail.getNext()==null){
    //            return tail;
    //        }else{
    //            if(tail==tail.getNext()){
    //                tail.setNext(null);
    //                return tail;
    //            }
    //            if(tail==tail.getPrev()){
    //                tail=tail.getPrev();
    //                tail.setNext(null);
    //            }
    //            System.out.println(tail.toString());
    //            tail=tail.getNext();
    //            return getLast();
    //        }
  }

  public void setHead(Node<E> head, Node<E> tail) {

    // if(tail!=null)
    //        System.out.println("head "+head.toString()+" tail "+tail.toString());
    this.head = head;
    head.setPrev(null);

    if (tail != null) {
      this.tail = tail;
      tail.setNext(null);
    }

    //        System.out.println(this.toString()+"yo22");
    //        if(head==tail){

    //        }
    //        System.out.println(node.getElement().toString());
  }

  public void setTail(Node<E> head, Node<E> tail) {
    if (head != null) {
      this.head = head;
      head.setPrev(null);
    }

    this.tail = tail;
    tail.setNext(null);
  }

  @Override
  public String toString() {

    Node<E> temp = head;
    StringBuilder sb = new StringBuilder();
    while (temp != null) {
      sb.append(temp.toString() + ",,");
      temp = temp.getNext();
    }

    return sb.toString();
  }

  public E removeFirst() {
    E temp;
    temp = head.getElement();
    head = head.getNext();
    head.setPrev(null);
    return temp;
  }

  //    public Node<E> getHead() {
  //        return head;
  //    }
}
