package col106.a3;

public class Node<E> {
  private E data;
  private Node<E> next, prev;

  public Node(E data, Node<E> next, Node<E> prev) {
    this.data = data;
    this.next = next;
    this.prev = prev;
  }

  public E getElement() {
    return data;
  }

  public E setElement(E data) {
    this.data = data;
    return data;
  }

  public Node<E> getNext() {
    return next;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }

  public E getData() {
    return data;
  }

  public Node<E> getPrev() {
    return prev;
  }

  public void setPrev(Node<E> prev) {
    this.prev = prev;
  }

  public Node<E> getLeft() {
    return getPrev();
  }

  public Node<E> getRight() {
    return getNext();
  }

  @Override
  public String toString() {
    return data.toString();
  }
}
