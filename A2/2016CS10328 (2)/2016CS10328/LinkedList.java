public class LinkedList<E> {
  private Node<E> head = null, tail = null;

  public void insertFirst(E data) {
    if (head == null) {
      head = new Node<E>(data, head);
      tail = head;
    } else {
      head = new Node<E>(data, head);
    }
  }

  public void insertLast(E data) {
    if (tail == null) {
      tail = new Node<E>(data, tail);
      head = tail;
    } else {
      tail.setNext(new Node<E>(data, null));
      tail = tail.getNext();
    }
  }

  public E removeFirst() {
    if (head == null) {
      return null;
    }

    E data = head.getElement();
    head = head.getNext();
    return data;
  }

  public E removeLast() {
    throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public E getAtPos(int pos) {
    throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public Node<E> getFirst() {
    return head;
  }

  public void printer() {
    Node<E> temp = head;
    int i = 0;
    while (temp != null) {

      System.out.print(temp.getElement() + " ");

      i += 1;
      // if(i%2 == 0) {
      //         System.out.println();
      // }
      temp = temp.getNext();
      break;
    }
    System.out.println();
  }
}
