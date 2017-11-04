package col106.a3;

public class TreeNode<Key extends Comparable<Key>, Value> {

  private LinkedList<TreeNode<Key, Value>> children;
  private LinkedList<Pair<Key, Value>> values;
  private int curr_size, node_max_size;
  private boolean hasChildren;

  //  private int ;
  // private Pair<Key,Value> k;
  public TreeNode(int b) {
    node_max_size = b;
    curr_size = 0;
    hasChildren = false;
    // main_vec=new Vector();

  }

  public TreeNode(
      int b,
      TreeNode<Key, Value> leftChild,
      Pair<Key, Value> pair,
      TreeNode<Key, Value> rightChild) {
    this(b);
    if ((rightChild != null) && (leftChild != null)) {
      hasChildren = true;
    }
    children = new LinkedList<>();
    values = new LinkedList<>();
    children.insertLast(leftChild);
    children.insertLast(rightChild);
    values.insertFirst(pair);

    this.setsize(1);
    //        values=new LinkedList<>();
    //        values.insertFirst(pair);
  }

  public TreeNode(int b, Pair<Key, Value> pair) {
    this(b);
    values = new LinkedList<>();
    values.insertFirst(pair);
    this.setsize(1);
  }

  public TreeNode(
      int b, LinkedList<TreeNode<Key, Value>> children, LinkedList<Pair<Key, Value>> values) {
    this(b);
    if (children != null) {
      this.children = children;
      hasChildren = true;
    } else {
      hasChildren = false;
    }
    this.values = values;
    //        this.setsize(1);
  }

  public void setHasChildren(boolean hasChildren) {
    this.hasChildren = hasChildren;
  }
  //  public Key getKey(int i) {
  //    return main_vec.get(i).getKey();
  //  }

  //  public Value getValue(int i) {
  //    return main_vec.get(i).getVal();
  //  }

  //  public Node<Key, Value> getPointer(int i) {
  //    return pointers.get(i);
  //  }

  public int getSize() {
    return curr_size;
  }

  public boolean full() {
    return curr_size == (node_max_size - 1);
  }

  public LinkedList<Pair<Key, Value>> getValues() {
    return values;
  }

  public LinkedList<TreeNode<Key, Value>> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    return hasChildren;
  }

  public void increaseSize() {
    curr_size++;

    //        //System.out.println("Increasing to "+curr_size);
    //        if(curr_size=node_max_size-1){
    //
    //        }
  }

  public void setsize(int size) {
    curr_size = size;
    //        if(curr_size==node_max_size-1){

    //        }
  }

  public TreeNode<Key, Value> split(
      Key k,
      Node<TreeNode<Key, Value>> thisNode,
      Node<Pair<Key, Value>> pairNode,
      TreeNode<Key, Value> treeNode) {
    if (full() && hasChildren()) {
      //            //System.out.println("gsdgs");

      TreeNode<Key, Value> leftChild, rightChild;
      Pair<Key, Value> goingUp;

      Node<Pair<Key, Value>> value;
      Node<TreeNode<Key, Value>> child;

      value = values.getFirst();
      //            child=children.getFirst();

      child = children.getFirst();
      for (int i = 1; i < node_max_size / 2 - 1; i++) {
        value = value.getNext();
        child = child.getNext();
      }
      child = child.getNext();
      goingUp = value.getNext().getElement();

      //            value=value.

      LinkedList<TreeNode<Key, Value>> children2 = new LinkedList<>();
      LinkedList<Pair<Key, Value>> values2 = new LinkedList<>();

      values2.setHead(value.getNext().getNext(), values.getLast());
      //            values2.getHead().setPrev(null);
      //            value.getNext().setNext(null);
      value.setNext(null);
      values.setTail(null, value);

      children2.setHead(child.getNext(), children.getLast());
      //            children2.getHead().setPrev(null);
      child.setNext(null);
      children.setTail(null, child);

      leftChild = new TreeNode<>(node_max_size, children, values);
      //            //System.out.println(leftChild.toString());
      leftChild.setsize(node_max_size / 2 - 1);
      leftChild.setHasChildren(true);

      //            thisNode.setElement(leftChild);

      rightChild = new TreeNode<>(node_max_size, children2, values2);
      //            //System.out.println(rightChild.toString());
      rightChild.setsize(node_max_size / 2 - 1);
      rightChild.setHasChildren(true);

      //            return leftChild,rightChild,
      if (thisNode != null) {
        Node<TreeNode<Key, Value>> x = new Node<>(rightChild, thisNode.getNext(), thisNode);
        if (thisNode.getNext() != null) {
          thisNode.getNext().setPrev(x);
        }
        thisNode.setNext(x);

        if (thisNode.getNext().getNext() == null) {
          treeNode.getChildren().setTail(null, x);
        }
        //            if(thisNode==treeNode.getChildren().getLast()){
        //                treeNode.getChildren().setTail(null,x);
        //            }
        //                treeNode.increaseSize();
        thisNode.setElement(leftChild);
      }
      if (pairNode != null) {
        Node<Pair<Key, Value>> y = new Node<>(goingUp, pairNode.getNext(), pairNode);
        if (pairNode.getNext() != null) {

          pairNode.getNext().setPrev(y);
        }
        pairNode.setNext(y);
        if (pairNode == treeNode.getValues().getLast()) {
          treeNode.getValues().setTail(null, y);
        }
        //                treeNode.increaseSize();
        treeNode.increaseSize();
        //                //System.out.println("in pair2 node");
        //                //System.out.println(treeNode.getSize());

      } else {
        //                Node<Pair<Key, Value>> y=new Node<>(goingUp,
        // treeNode.getValues().getFirst(),null);
        //                treeNode.getValues().getFirst().setPrev(y);
        //                treeNode.getValues().setHead(y);
        treeNode.getValues().insertFirst(goingUp);
        treeNode.increaseSize();
      }
      //            treeNode.increaseSize();
      if (goingUp.getKey().compareTo(k) > 0) {
        return leftChild;
      } else {
        return rightChild;
      }

    } else if (full() && !hasChildren()) {
      //            //System.out.println("gsdgs sdgsd");

      TreeNode<Key, Value> leftChild, rightChild;
      Pair<Key, Value> goingUp;

      Node<Pair<Key, Value>> value;
      //            Node<TreeNode<Key, Value>> child;

      value = values.getFirst();
      //            child=children.getFirst();

      //            child = children.getFirst();
      for (int i = 1; i < node_max_size / 2 - 1; i++) {
        value = value.getNext();
        //                child = child.getNext();
      }

      //            //System.out.println(toString());
      //            //System.out.println(curr_size);
      goingUp = value.getNext().getElement();

      //            value=value.

      //            LinkedList<TreeNode<Key, Value>> children2 = new LinkedList<>();
      LinkedList<Pair<Key, Value>> values2 = new LinkedList<>();

      values2.setHead(value.getNext().getNext(), values.getLast());
      //            values2.getHead().setPrev(null);
      //            value.getNext().setNext(null);
      value.setNext(null);
      values.setTail(null, value);
      //            children2.setHead(child);

      leftChild = new TreeNode<>(node_max_size, null, values);
      leftChild.setsize(node_max_size / 2 - 1);
      leftChild.setHasChildren(false);

      //            thisNode.setElement(leftChild);

      rightChild = new TreeNode<>(node_max_size, null, values2);
      rightChild.setsize(node_max_size / 2 - 1);
      rightChild.setHasChildren(false);

      //            return leftChild,rightChild,

      if (thisNode != null) {
        Node<TreeNode<Key, Value>> x = new Node<>(rightChild, thisNode.getNext(), thisNode);
        if (thisNode.getNext() != null) {
          thisNode.getNext().setPrev(x);
        }
        thisNode.setNext(x);
        //                if(childNode==treeNode.getChildren().getLast()){
        //                    treeNode.getChildren().setTail(null,x);
        //                }
        //                treeNode.increaseSize();

        if (thisNode.getNext().getNext() == null) {
          treeNode.getChildren().setTail(null, x);
        }

        thisNode.setElement(leftChild);
      }

      if (pairNode != null) {
        Node<Pair<Key, Value>> y = new Node<>(goingUp, pairNode.getNext(), pairNode);
        if (pairNode.getNext() != null) {

          pairNode.getNext().setPrev(y);
        }

        pairNode.setNext(y);
        if (pairNode == treeNode.getValues().getLast()) {
          treeNode.getValues().setTail(null, y);
        }
        treeNode.increaseSize();
        //                //System.out.println("in pair node");
        //                //System.out.println(treeNode.getSize());
      } else if (treeNode != null) {
        //                Pair<Key, Value> y=new Node<>(goingUp,
        // treeNode.getValues().getFirst(),null);
        treeNode.getValues().insertFirst(goingUp);
        //                treeNode.getValues().getFirst().setPrev(y);
        //                treeNode.getValues().setHead(y);
        treeNode.increaseSize();
      }

      //            if(treeNode!=null){

      //            }
      if (goingUp.getKey().compareTo(k) > 0) {
        return leftChild;
      } else {
        return rightChild;
      }
    }
    //        //System.out.println("gsdgs sdgsd");

    return null;
  }

  public TreeNode<Key, Value> split2() {

    if (full() && hasChildren()) {
      //            //System.out.println("split 2 1");

      TreeNode<Key, Value> leftChild, rightChild;
      Pair<Key, Value> goingUp;

      Node<Pair<Key, Value>> value;
      Node<TreeNode<Key, Value>> child;

      value = values.getFirst();
      //            child=children.getFirst();

      child = children.getFirst();
      for (int i = 1; i < node_max_size / 2 - 1; i++) {
        value = value.getNext();
        child = child.getNext();
      }
      goingUp = value.getNext().getElement();
      child = child.getNext();
      //            value=value.

      LinkedList<TreeNode<Key, Value>> children2 = new LinkedList<>(),
          children1 = new LinkedList<>();
      LinkedList<Pair<Key, Value>> values2 = new LinkedList<>(), values1 = new LinkedList<>();

      //            //System.out.println(children.getFirst().toString()+" first c");
      //            //System.out.println(children.getLast().toString()+" last c");

      values2.setHead(value.getNext().getNext(), values.getLast());
      //            value.getNext().setNext(null);

      //            value.setNext(null);
      values1.setHead(values.getFirst(), value);

      Node<TreeNode<Key, Value>> right = child.getNext();
      Node<TreeNode<Key, Value>> left = child;
      children2.setHead(right, children.getLast());
      children1.setHead(children.getFirst(), left);
      //            child.setNext(null);
      //            //System.out.println(children1.toString());
      //            //System.out.println(children2.toString());
      //            //System.out.println(values1.toString());
      //            //System.out.println(values2.toString());

      leftChild = new TreeNode<>(node_max_size, children1, values1);
      leftChild.setsize(node_max_size / 2 - 1);
      leftChild.setHasChildren(true);

      rightChild = new TreeNode<>(node_max_size, children2, values2);
      rightChild.setsize(node_max_size / 2 - 1);
      rightChild.setHasChildren(true);

      //            //System.out.println(rightChild.toString());
      //            //System.out.println(leftChild.toString());
      //            return leftChild,rightChild,

      return new TreeNode<>(node_max_size, leftChild, goingUp, rightChild);

    } else if (full() && !hasChildren()) {
      //            //System.out.println("split 2 ds1");

      //            //System.out.println("in this");

      TreeNode<Key, Value> leftChild, rightChild;
      Pair<Key, Value> goingUp;

      Node<Pair<Key, Value>> value;
      //            Node<TreeNode<Key, Value>> child;

      value = values.getFirst();
      //            child=children.getFirst();

      //            child = children.getFirst();
      for (int i = 1; i < node_max_size / 2 - 1; i++) {
        value = value.getNext();
        //                child = child.getNext();
      }

      //            if(value.getNext()!=null){
      //
      //            }
      goingUp = value.getNext().getElement();

      //            value=value.

      //            LinkedList<TreeNode<Key, Value>> children2 = new LinkedList<>();
      LinkedList<Pair<Key, Value>> values2 = new LinkedList<>();

      values2.setHead(value.getNext().getNext(), values.getLast());
      //            value.getNext().setNext(null);
      value.setNext(null);
      values.setTail(null, value);
      //            children2.setHead(child);

      leftChild = new TreeNode<>(node_max_size, null, values);
      leftChild.setsize((node_max_size / 2) - 1);
      leftChild.setHasChildren(false);
      rightChild = new TreeNode<>(node_max_size, null, values2);
      rightChild.setsize((node_max_size / 2) - 1);
      rightChild.setHasChildren(false);

      //            return leftChild,rightChild,
      //            //System.out.println(leftChild.toString());

      //            toString(leftChild)
      //            if(treeNode!=null){

      //            }
      return new TreeNode<>(node_max_size, leftChild, goingUp, rightChild);
    }
    //        //System.out.println("split 2 gggg1");

    return null;
  }

  public String toString() {

    StringBuilder sb = new StringBuilder();
    Pair<Key, Value> pair;

    if (this.hasChildren()) {

      sb
          //                    .append(this.curr_size)
          .append("[");

      MyIterator<Pair<Key, Value>> values = new MyIterator<>(this.getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(this.getChildren());

      TreeNode<Key, Value> child;
      child = children.getNext();
      pair = values.getNext();
      //            //System.out.println(pair.getKey());
      sb.append(child.toString())
          .append(", ")
          //                    .                        append("("+values.getPrevNode()+")")

          .append(pair.toString());
      while (values.hasNext() && children.hasNext()) {

        child = children.getNext();
        pair = values.getNext();
        sb.append(", ").append(child.toString()).append(", ").append(pair.toString());
      }
      child = children.getNext();
      sb.append(", ").append(child.toString()).append("]");

      //            sb.append("(" + (this.getChildren().getLast().getElement() == child) + ")");

    } else {
      sb
          //                    .append(this.curr_size)
          .append("[");
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(this.getValues());

      pair = values.getNext();
      sb
          //                    .append("("+values.getPrevNode()+")")

          .append(pair.getKey())
          .append("=")
          .append(pair.getVal());

      while (values.hasNext()) {
        pair = values.getNext();
        sb.append(", ").append(pair.getKey()).append("=").append(pair.getVal());
      }

      sb.append("]");
    }

    return sb.toString();
  }

  public void decreaseSize() {
    curr_size--;
  }

  public void addAtEnd(Node<Pair<Key, Value>> pairNode, TreeNode<Key, Value> rightChild) {

    // this adds all the nodes of right child into the current node

    //        Node<Pair<Key,Value>> pair;
    //        Node<TreeNode<Key,Value>> child;

    //        MyIterator<Pair<Key, Value>> values = new MyIterator<>(this.getValues());
    //        MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(this.getChildren());

    //        do{
    //            pair=values.getNextNode();
    //            child=children.getNextNode();
    //        }while(values.hasNext() && children.hasNext());
    //
    //        child=children.getNextNode();

    pairNode.setPrev(this.getValues().getLast());

    this.getValues().insertLast(pairNode.getElement());

    if (rightChild != null) {

      pairNode.setNext(rightChild.getValues().getFirst());

      if (rightChild.hasChildren()) {

        Node<Pair<Key, Value>> x = rightChild.getValues().getFirst();
        Node<TreeNode<Key, Value>> y = rightChild.getChildren().getFirst();

        x.setPrev(this.getValues().getLast());
        this.getValues().getLast().setNext(x);
        this.getValues().setTail(null, rightChild.getValues().getLast());

        // System.out.println(this.getChildren().getLast() + "x1");
        // System.out.println(rightChild.getChildren().getLast().getNext() + "x2");

        // System.out.println(this.getChildren().toString() + "aa");
        // System.out.println(this.getChildren().getLast().toString() + "aa");

        // System.out.println(rightChild.getChildren().toString() + "bb");
        // System.out.println(rightChild.getChildren().getFirst().toString() + "bb2");

        this.getChildren().getLast().setNext(rightChild.getChildren().getFirst());

        rightChild.getChildren().getFirst().setPrev(this.getChildren().getLast());
        //                //System.out.println(y.toString());

        this.getChildren().setTail(null, rightChild.getChildren().getLast());

        // System.out.println(this.getChildren().toString() + "cc");
        // System.out.println(rightChild.getChildren().toString() + "dd");
      } else {

        Node<Pair<Key, Value>> x = rightChild.getValues().getFirst();
        //                Node<TreeNode<Key, Value>> y=rightChild.getChildren().getFirst();

        x.setPrev(this.getValues().getLast());
        this.getValues().getLast().setNext(x);
        this.getValues().setTail(null, rightChild.getValues().getLast());

        //                y.setPrev(this.getChildren().getLast());
        //                this.getChildren().getLast().setNext(y);
        //                this.getChildren().setTail(null,rightChild.getChildren().getLast());
      }
    }
  }

  //    public Pair<Key,Value> deleteLast() {
  //        Node<Pair<Key,Value>> pair;
  //        Node<TreeNode<Key,Value>> child;
  //
  //        MyIterator<Pair<Key, Value>> values = new MyIterator<>(this.getValues());
  //        MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(this.getChildren());
  //
  //
  //        do{
  //            pair=values.getNextNode();
  //            child=children.getNextNode();
  //        }while(values.hasNext() && children.hasNext());
  //
  //
  //
  //        not implemented yet
  //        this.getValues().removeLast();
  //        this.getChildren().removeLast();

  //    }

  public void addAtEnd2(Pair<Key, Value> pairNode, Node<TreeNode<Key, Value>> element) {
    //        Node<Pair<Key,Value>> pair;
    //        Node<TreeNode<Key,Value>> child;
    //
    //        MyIterator<Pair<Key, Value>> values = new MyIterator<>(this.getValues());
    //        MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(this.getChildren());
    //
    //
    //        do{
    //            pair=values.getNextNode();
    //            child=children.getNextNode();
    //        }while(values.hasNext() && children.hasNext());
    //
    //        child=children.getNextNode();
    //
    //        pair.setNext(pairNode);
    //        pairNode.setPrev(pair);
    //        pairNode.setNext(null);
    //
    //        child.setNext(element);
    //        element.setNext(null);
    //        element.setPrev(child);

    this.getValues().insertLast(pairNode);
    if (element != null) {
      element.setPrev(this.getChildren().getLast());
      this.getChildren().insertLast(element.getElement());
    }
  }

  public void addAtStart(Pair<Key, Value> pair, Node<TreeNode<Key, Value>> leftLastChild) {

    if (leftLastChild != null) {

      this.getChildren().insertFirst(leftLastChild.getElement());
      //        }else{

    }
    this.getValues().insertFirst(pair);
  }
}
