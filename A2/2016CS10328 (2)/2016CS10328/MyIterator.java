public class MyIterator<E> {

  private Node<E> current;

  public MyIterator(LinkedList<E> l) {
    current = l.getFirst();
  }

  public boolean hasNext() {
    return current != null;
  }

  public E next() {
    if (hasNext()) {
      E temp = current.getElement();
      current = current.getNext();
      return temp;
    }
    return null;
  }

  public E peek() {
    if (hasNext()) {
      E temp = current.getElement();
      return temp;
    }
    return null;
  }

  public void replaceNextAndMove(E data) {
    if (hasNext()) {
      current.setElement(data);
      current = current.getNext();
    }
  }

  public void replace(E data) {
    if (hasNext()) {
      current.setElement(data);
      // current=current.getNext();
    }
  }
}
