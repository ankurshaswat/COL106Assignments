public class Node<E> {
  private E data;
  private Node<E> next;

  public Node(E data, Node<E> next) {
    this.data = data;
    this.next = next;
  }

  public E getElement() {
    return data;
  }

  public void setElement(E data) {
    this.data = data;
  }

  public Node<E> getNext() {
    return next;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }
}
