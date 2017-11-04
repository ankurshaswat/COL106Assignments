package col106.a3;

import java.util.ArrayList;
import java.util.List;

public class BTree<Key extends Comparable<Key>, Value> implements DuplicateBTree<Key, Value> {

  private int b, t, size, height;
  private TreeNode<Key, Value> root;

  public BTree(int b) throws bNotEvenException {
    /* Initializes an empty b-tree. Assume b is even. */

    if (b % 2 != 0 || b <= 3) {
      throw new bNotEvenException();
    }

    this.b = b;
    this.t = b / 2;
    size = 0;
    height = -1;
  }

  @Override
  public boolean isEmpty() {
    return (height == -1);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public List<Value> search(Key key) {

    List<Value> list;
            if (root == null) {
    //            throw new IllegalKeyException();
          return new ArrayList<>() ;

            }

    list = search(root, key);

    //    if (list.size() == 0) {
    //      throw new IllegalKeyException();
    //    }

    return list;
  }

  private List<Value> search(TreeNode<Key, Value> node, Key in_key) {

    List<Value> list_new = new ArrayList<>();
    if (node.hasChildren()) {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getChildren());
      TreeNode<Key, Value> child;
      Pair<Key, Value> pair;
      Key key;
      do {
        child = children.getNext();
        pair = values.getNext();
        key = pair.getKey();
        if (in_key.compareTo(key) == 0) {
          list_new.addAll(search(child, in_key));
          list_new.add(pair.getVal());
        } else if (in_key.compareTo(key) < 0) {
          list_new.addAll(search(child, in_key));
          break;
        }
      } while ((values.hasNext() && children.hasNext()));

      if (in_key.compareTo(key) >= 0) {
        list_new.addAll(search(children.getNext(), in_key));
      }
    } else {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      Pair<Key, Value> pair;
      Key key;
      pair = values.getNext();
      key = pair.getKey();
      if (in_key.compareTo(key) == 0) {
        list_new.add(pair.getVal());
      }
      while (values.hasNext()) {
        pair = values.getNext();
        key = pair.getKey();
        if (in_key.compareTo(key) == 0) {
          list_new.add(pair.getVal());
        } else if (in_key.compareTo(key) < 0) {
          break;
        }
      }
    }
    return list_new;
  }

  @Override
  public void insert(Key key, Value val) {
    if (root == null) {
      Pair<Key, Value> pair = new Pair<>(key, val);

      //            //System.out.println(toString());
      insert(pair);
      size++;
      height++;
    } else {
      insert(root, key, val, null, null, null);
    }
    //    //System.out.println(checkConsistency(root) + "check");
    //    //System.out.println(root.toString());
  }

  private void insert(Pair<Key, Value> pair) {
    root = new TreeNode<>(b, pair);
  }

  private void insert(
      TreeNode<Key, Value> node,
      Key in_key,
      Value val,
      Node<TreeNode<Key, Value>> childNode,
      Node<Pair<Key, Value>> pairNode,
      TreeNode<Key, Value> treeNode) {

    boolean inserted = false;

    if (node.full()) {
      //            //System.out.println(node.toString());
      //            //System.out.println(node.getSize());
      if (node == root) {
        height++;
        root = node.split2();
        //                //System.out.println(root.toString());
        //                //System.out.println("here234555");
        insert(root, in_key, val, null, null, null);
        //                insert()
      } else {
        //                //System.out.println("hi");
        TreeNode<Key, Value> searchNode = node.split(in_key, childNode, pairNode, treeNode);
        //                //System.out.println(toString());
        //          height
        insert(searchNode, in_key, val, null, null, null);
      }
    } else if (node.hasChildren()) {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getChildren());

      TreeNode<Key, Value> child;
      Pair<Key, Value> pair;
      Key key;
      child = children.getNext();
      pair = values.getNext();

      key = pair.getKey();

      if (in_key.compareTo(key) < 0) {
        //                //System.out.println("here1bvasv");

        insert(child, in_key, val, children.getNode(), values.getPrevNode(), node);
        inserted = true;

        //              break;
      }
      while (values.hasNext() && children.hasNext() && !inserted) {

        child = children.getNext();
        pair = values.getNext();

        key = pair.getKey();

        if (in_key.compareTo(key) < 0) {
          //                    //System.out.println("here1aa");

          insert(child, in_key, val, children.getNode(), values.getPrevNode(), node);
          inserted = true;
          break;
        }
      }
      if (in_key.compareTo(key) >= 0 && !inserted) {
        //                //System.out.println("here1");
        child = children.getNext();
        insert(child, in_key, val, children.getNode(), values.getNode(), node);
        //                inserted = true;
      }
    } else {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      //          MyIterator<TreeNode<Key,Value>> children=new
      // MyIterator<TreeNode<Key,Value>>(TreeNode.getChildren());

      //          TreeNode<Key,Value> child;
      Pair<Key, Value> pair;
      Key key;
      //          child=children.getNext();
      pair = values.getNext();

      key = pair.getKey();

      if (in_key.compareTo(key) < 0) {
        //              insert(child, in_key, val);
        //                //System.out.println("this??");
        //                //System.out.println("here");
        //                //System.out.println(node.toString());
        values.insertBefore(new Pair<>(in_key, val), node.getValues());
        //                //System.out.println(node.toString());
        inserted = true;
        //              break;
      }
      while (values.hasNext() && !inserted) {

        //              child=children.getNext();
        pair = values.getNext();

        key = pair.getKey();

        if (in_key.compareTo(key) < 0) {
          //                  insert(child, in_key, val);
          //                    //System.out.println("this123");
          values.insertBefore(new Pair<>(in_key, val), node.getValues());
          inserted = true;
          break;
        }
      }

      if (in_key.compareTo(key) >= 0 && !inserted) {
        //                //System.out.println(key.compareTo(in_key));
        //              child=children.getNext();
        //              insert(child, in_key, val);
        //                //System.out.println("this1234");
        //                //System.out.println("inserting"+in_key+"against"+key);
        values.insertAfter(new Pair<>(in_key, val), node.getValues());
        //                inserted = true;
      }
      size++;
      node.increaseSize();
      //            //System.out.println(node.getSize());
    }
  }

  @Override
  public void delete(Key key) throws IllegalKeyException {

    //        int prev_size = size;
    // while()
    if (root == null) {
      throw new IllegalKeyException();
    } else {

      int initial_size = this.size();
      int size_loop;
      while (true) {
        //               if(height==1 || height==0) System.out.println(root.getSize());
        size_loop = this.size();
        if (root == null) {
          break;
        }

        if (root.hasChildren()) {
          //                //System.out.println("Delete 1");
          // System.out.println("BTree.delete 1");
          deleteInternal(new Node<>(root, null, null), key, null);
        } else {
          //                //System.out.println("Delete 2");
          // System.out.println("BTree.delete 2");
          if (root.getSize() == 1 && root.getValues().getFirst().getElement().getKey() == key) {
            root = null;
            //                        root.decreaseSize();
            height = -1;
            size = 0;
          } else {
            deleteAtLeaf(new Node<>(root, null, null), key, null, null, null);
          }
        }
        if (this.size() == size_loop) {
          break;
        }
      }

      if (this.size() == initial_size) {
        throw new IllegalKeyException();
      }
    }

    if (root == null || root.getSize() == 0) {
      root = null;
      height = -1;
      size = 0;
    }

    //        //System.out.println(checkConsistency(root) + "delete check");

    //        if (prev_size == size) {
    //            throw new IllegalKeyException();
    //        }
    //       throw new RuntimeException("Not Implemented");
    // System.out.println(toString() + " after deletion");
  }

  private void delete(Node<TreeNode<Key, Value>> node, Node<Pair<Key, Value>> pair) {
    if (root == null || node == null) {
      return;
    }
    // System.out.println("BTree.delete pairwise");
    // System.out.println("to delete " + pair.toString());
    if (node.getElement().hasChildren()) {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getElement().getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getElement().getChildren());

      //        boolean deleted=false;

      TreeNode<Key, Value> child;
      Pair<Key, Value> pair2;
      //            Key k;
      do {

        child = children.getNext();
        pair2 = values.getNext();
        //                k = pair.getKey();

        if (pair2 == pair.getElement()) {
          pair = values.getNode();
          // System.out.println("found pair");
          //          if (node.getElement().hasChildren()) {
          Node<TreeNode<Key, Value>> leftChild = children.getNode();
          Node<TreeNode<Key, Value>> rightChild = leftChild.getRight();
          //                //System.out.println(leftChild.toString() + "abc");
          //                //System.out.println(rightChild.toString() + "cde");
          if (leftChild != null && leftChild.getElement().getSize() > t - 1) {
            // System.out.println("BTree.delete x");
            values.getNode().setElement(deleteLast(leftChild, values.getPrevNode(), node));
            return;

          } else if (rightChild != null && rightChild.getElement().getSize() > t - 1) {
            // System.out.println("BTree.delete x1");

            values.getNode().setElement(deleteFirst(rightChild, values.getNode(), node));
            return;

          } else if (leftChild != null && rightChild != null) {
            //                if (leftParentPair == root.getValues().getFirst() && root.getSize()
            // == 1)
            // {
            //                    height--;
            //                }
            // node.getleft is leftSibling
            //                    Node<Pair<Key, Value>> pair = nod;
            // System.out.println("BTree.delete x2");

            Node<TreeNode<Key, Value>> node2 = merge(leftChild, values.getNode(), rightChild, node);
            delete(node2, pair);
            return;

            //
            //                {
          }

          //                    TreeNode<Key, Value> leftSibling = null, rightSibling = null;
          ////                    if (node.getLeft() != null) {
          //
          //                        leftSibling = children.getNode().getElement();
          ////                    }
          ////                    if (node.getRight() != null) {
          //
          //                        rightSibling = children.getNode().getRight().getElement();
          ////                    }
          //
          //                    Node<Pair<Key, Value>>
          // leftParentPair=values.getNode(),rightParentPair=leftParentPair;
          //                    if (leftSibling != null && leftSibling.getSize() > t - 1) {
          //                        rightRotate(leftSibling, leftParentPair,
          // children.getNode().getRight());
          //                        delete(node, pair);
          //                        //                        return;
          //                    } else if (rightSibling != null && rightSibling.getSize() > t - 1)
          // {
          //                        leftRotate(node, rightParentPair, rightSibling);
          //                        deleteAtLeaf(node, key, leftParentPair, rightParentPair,
          // parent);
          //                    } else if (leftSibling != null && leftSibling.getSize() == t - 1)
          // {
          //                        if (leftParentPair == root.getValues().getFirst() &&
          // root.getSize() == 1) {
          //                            height--;
          //                        }
          //                        // node.getleft is leftSibling
          //                        node = merge(node.getLeft(), leftParentPair, node, parent);
          //                        if (rightParentPair != null) {
          //                            deleteAtLeaf(node, key, rightParentPair.getLeft(),
          // rightParentPair, parent);
          //                        } else {
          //                            deleteAtLeaf(node, key, null, null, parent);
          //                        }
          //                    } else if (rightSibling != null && rightSibling.getSize() == t -
          // 1) {
          //                        if (rightParentPair == root.getValues().getFirst() &&
          // root.getSize() == 1) {
          //                            height--;
          //                        }
          //                        node = merge(node, rightParentPair, node.getRight(), parent);
          //                        if (leftParentPair != null) {
          //
          //                            deleteAtLeaf(node, key, leftParentPair,
          // leftParentPair.getRight(), parent);
          //                        } else {
          //                            deleteAtLeaf(node, key, null, null, parent);
          //                        }
          //                    } else {
          //                        MyIterator<Pair<Key, Value>> values = new
          // MyIterator<>(node.getElement().getValues());
          //                        Pair<Key, Value> pair;
          //                        Key k;
          //                        //                    boolean deleted=false;
          //                        do {
          //                            pair = values.getNext();
          //                            k = pair.getKey();
          //                            if (key.compareTo(k) == 0) {
          //                                //System.out.println("Deleting in root" + " Before:- " +
          // toString());
          //                                values.delete(node.getElement().getValues());
          //                                size--;
          //                                if (size == 0) {
          //                                    height = -1;
          //                                    root = null;
          //                                }
          //                                node.getElement().decreaseSize();
          //                                //System.out.println("Deleting in root" + " After:- " +
          // toString());
          //
          //                                //
          // delete(node,key,parentPair);
          //                                //                            return true;
          //                                //                            deleted=true;
          //                            }
          //                        } while (values.hasNext());
          //                        //                    return deleted;
          //                    }
          //                }

          //                throw new NotImplementedException();
          //                        deleteInternal(child, key);
          //          } else {
          //                        dokro
          //            if (pair.getPrev() != null) {
          //              pair.getPrev().setNext(pair.getNext());
          //            } else {
          //              node.getElement().getValues().setHead(pair.getNext(), null);
          //            }
          //            if (pair.getNext() != null) {
          //              pair.getNext().setPrev(pair.getPrev());
          //            } else {
          //              node.getElement().getValues().setTail(null, pair.getPrev());
          //            }
          //            size--;
          //                throw new NotImplementedException();
          //            node.getElement().decreaseSize();

          //                        deleteAtLeaf(children.getNode(), key,
          // values.getNode().getLeft(), values.getNode().getNext());
          //          }

          //
          //     break;
        }
        //    }

      } while (values.hasNext() && children.hasNext());

      //            if (key.compareTo(k) > 0) {
      //                child = children.getNext();
      //                if (child.hasChildren()) {
      //                    deleteInternal(child, key);
      //                } else {
      //                    deleteAtLeaf(children.getNode(), key, values.getNode(), null);
      //                }
      //            }

    } else {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getElement().getValues());
      //        MyIterator<TreeNode<Key, Value>> children = new
      // MyIterator<>(node.getElement().getChildren());

      //        boolean deleted=false;

      TreeNode<Key, Value> child;
      Pair<Key, Value> pair2;
      //            Key k;
      do {

        //            child = children.getNext();
        pair2 = values.getNext();
        //                k = pair.getKey();

        if (pair2 == pair.getElement()) {
          pair = values.getNode();
          // System.out.println("found aa");
          //      if (pair.getPrev() != null) {
          //        //System.out.println(pair.getPrev().toString() + "prev");
          //        pair.getPrev().setNext(pair.getNext());
          //      }
          //
          //      if (pair.getNext() != null) {
          //        //System.out.println(pair.getNext().toString() + "next");
          //        pair.getNext().setPrev(pair.getPrev());
          //      }
          //      node.getElement().decreaseSize();
          //      size--;
          // System.out.println(node.toString() + " before deleting pairwise");

          // System.out.println("BTree.delete  no children");
          ;
          if (pair.getPrev() != null) {
            // System.out.println("1");
            pair.getPrev().setNext(pair.getNext());
            // System.out.println(pair.getPrev().getNext().toString() + " next of prev");
          } else {
            // System.out.println("2");
            node.getElement().getValues().setHead(pair.getNext(), null);
          }
          if (pair.getNext() != null) {
            // System.out.println("3");
            pair.getNext().setPrev(pair.getPrev());
          } else {
            // System.out.println("4");
            node.getElement().getValues().setTail(null, pair.getPrev());
          }
          size--;
          //                throw new NotImplementedException();
          node.getElement().decreaseSize();
          // System.out.println(node.toString() + " after deleting pairwise");
          //                    break;
          return;
        }
      } while (values.hasNext());
    }
  }

  private void deleteInternal(
      Node<TreeNode<Key, Value>> node, Key key, Node<TreeNode<Key, Value>> parent) {

    //    if(!node.getElement().hasChildren()){

    //    }
    // System.out.println("BTree.deleteInternal");
    if (root == null || node == null) {
      return;
    }

    //        MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
    MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getElement().getValues());
    MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getElement().getChildren());
    //        if(node.getElement().getChildren()==null){
    //            deleteAtLeaf(new Node<>(root, null, null), key, null, null,null);
    //            return;
    //               delete(node,key);
    //        }

    //        boolean deleted=false;

    TreeNode<Key, Value> child;
    Pair<Key, Value> pair;
    Key k;
    do {

      child = children.getNext();
      pair = values.getNext();
      k = pair.getKey();

      if (key.compareTo(k) < 0) {

        if (child.getSize() > t - 1) {

          if (child.hasChildren()) {
            // System.out.println("BTree.deleteInternal 1");
            deleteInternal(children.getNode(), key, node);
            return;
          } else {
            // System.out.println("BTree.deleteInternal 2");
            deleteAtLeaf(
                children.getNode(), key, values.getNode().getLeft(), values.getNode(), node);
            return;
          }
        } else {

          if (child.hasChildren()) {

            if (children.getNode().getNext().getElement().getSize() > t - 1) {
              // System.out.println("BTree.deleteInternal x");
              leftRotate(
                  children.getNode(), values.getNode(), children.getNode().getRight().getElement());
              deleteInternal(children.getNode(), key, node);
              return;
            } else if (children.getNode().getPrev() != null
                && children.getNode().getPrev().getElement().getSize() > t - 1) {
              // System.out.println("BTree.deleteInternal xx");

              //                                if
              // (children.getNode().getPrev().getElement().getSize() > t - 1) {
              rightRotate(children.getNode().getPrev().getElement(), values.getPrevNode(), node);
              deleteInternal(children.getNode(), key, node);
              //
              return;
              // }
            } else {
              // System.out.println("BTree.deleteInternal xxx");

              deleteInternal(
                  merge(children.getNode(), values.getNode(), children.getNode().getRight(), node),
                  key,
                  node);
              return;
            }
            //                        }else{
            //                        }
            //                            deleteInternal(
            // merge(children.getNode(),values.getNode(),children.getNode().getNext(),node),key,node);

          } else {
            //            {

            if (children.getNode().getRight() != null
                && children.getNode().getRight().getElement().getSize() > t - 1) {
              // System.out.println("BTree.deleteInternal y");

              leftRotate(
                  children.getNode(), values.getNode(), children.getNode().getRight().getElement());
              deleteAtLeaf(
                  children.getNode(), key, values.getNode().getPrev(), values.getNode(), node);
              return;
            } else if (children.getNode().getPrev() != null
                && children.getNode().getPrev().getElement().getSize() > t - 1) {
              //                                if
              // (children.getNode().getPrev().getElement().getSize() > t - 1) {
              // System.out.println("BTree.deleteInternal yy");

              rightRotate(
                  children.getNode().getPrev().getElement(),
                  values.getPrevNode(),
                  children.getNode());
              deleteAtLeaf(
                  children.getNode(), key, values.getNode().getPrev(), values.getNode(), node);
              //
              return;
              //         }
            } else {
              // System.out.println("BTree.deleteInternal yyy");

              Node<Pair<Key, Value>> leftParentPair = values.getNode().getLeft();
              Node<Pair<Key, Value>> rightParentPair = values.getNode().getRight();
              deleteAtLeaf(
                  merge(children.getNode(), values.getNode(), children.getNode().getRight(), node),
                  key,
                  leftParentPair,
                  rightParentPair,
                  node);
              return;
            }
            //                        }else{
          }
          //                            deleteInternal(
          // merge(children.getNode(),values.getNode(),children.getNode().getNext(),node),key,node);

          //            }
        }
        //                }
        //            }
        //                return;
        //                break;
      } else if (key.compareTo(k) == 0) {
        //                    //System.out.println("here1aa");
        if (child.getSize() > t - 1) {
          // System.out.println("BTree.deleteInternal 3");

          values.set(deleteLast(children.getNode(), values.getPrevNode(), node));
          return;
          //                    deleteInternal(node, key,parent);
          //                    try {
          //                        delete(key);
          //                    } catch (IllegalKeyException e) {
          //
          //                    }
          //                    return true;
        } else if (children.getNode().getRight().getElement().getSize() > t - 1) {
          // System.out.println("BTree.deleteInternal 4");

          values.set(deleteFirst(children.getNode().getRight(), values.getNode(), node));
          return;
          //                    deleteInternal(node, key,parent);
          //                    try {
          //                        delete(key);
          //                    } catch (IllegalKeyException e) {
          //
          //                    }
          //                    return true;
        } else {
          // System.out.println("BTree.deleteInternal 5a");

          Node<Pair<Key, Value>> prev = values.getPrevNode();
          Node<Pair<Key, Value>> next = values.getNode().getRight();
          Node<TreeNode<Key, Value>> node2 =
              merge(children.getNode(), values.getNode(), children.getNode().getRight(), node);
          if (node2 == null) {
            return;
          }
          if (node2.getElement().hasChildren()) {
            // System.out.println("BTree.deleteInternal 5");

            deleteInternal(node2, key, node);
            return;
          } else {
            // System.out.println("BTree.deleteInternal 6");

            deleteAtLeaf(node2, key, prev, next, node);
            return;
          }
        }
        //                if(checkPresence(node.getElement(), key)){

        //                    try{

        //                        delete( key);
        //                    }catch (IllegalKeyException e){

        //                    }
        //                }
        //                return;
        //                break;
        //                deleted=true;
        //                insert(child, in_key, val, children.getNode(), values.getPrevNode(),
        // node);
        //                inserted = true;
        //                break;
      }

    } while (values.hasNext() && children.hasNext());

    if (key.compareTo(k) > 0) {
      child = children.getNext();
      if (child.getSize() > t - 1) {

        if (child.hasChildren()) {
          // System.out.println("BTree.deleteInternal 7");

          deleteInternal(children.getNode(), key, node);
          return;
        } else {
          // System.out.println("BTree.deleteInternal 8");

          deleteAtLeaf(children.getNode(), key, values.getNode(), null, node);
        }
      } else {
        //                if (children.getNode().getNext().getElement().getSize() > t - 1) {
        //                    leftRotate(node, values.getNode(),
        // children.getNode().getNext().getElement());
        //                    deleteInternal(children.getNode(), key, node);
        //                 else {
        //                    if (children.getNode().getPrev() != null) {
        if (child.hasChildren()) {

          if (children.getNode().getPrev().getElement().getSize() > t - 1) {
            // System.out.println("BTree.deleteInternal y1");

            rightRotate(
                children.getNode().getPrev().getElement(),
                values.getPrevNode(),
                children.getNode());
            deleteInternal(children.getNode(), key, node);
            return;
            //                        }
          } else {
            // System.out.println("BTree.deleteInternal y2");

            deleteInternal(
                merge(children.getNode().getLeft(), values.getNode(), children.getNode(), node),
                key,
                node);
            return;
          }
        } else {
          deleteAtLeaf(children.getNode(), key, values.getNode(), null, node);
          return;
        }
      }
    }

    //    return deleted;
  }

  private void deleteAtLeaf(
      Node<TreeNode<Key, Value>> node,
      Key key,
      Node<Pair<Key, Value>> leftParentPair,
      Node<Pair<Key, Value>> rightParentPair,
      Node<TreeNode<Key, Value>> parent) {
    // System.out.println("BTree.deleteAtLeaf");
    if (root == null || node == null) {
      return;
    }
    //        boolean deleted=false;

    //        if(node.hasChildren()){

    //        }else{
    if (node.getElement().getSize() > t - 1) {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getElement().getValues());
      Pair<Key, Value> pair;
      Key k;
      do {
        pair = values.getNext();
        k = pair.getKey();
        if (key.compareTo(k) == 0) {
          // System.out.println(toString() + "before deleting at leaf");

          values.delete(node.getElement().getValues());
          size--;
          // System.out.println(toString() + "after deleting at leaf");

          //                    //System.out.println("deleting in leaf");

          node.getElement().decreaseSize();
          try {

            delete(key);
          } catch (IllegalKeyException e) {

          }
          //                    deleteAtLeaf(node, key, leftParentPair, rightParentPair,parent);
          //                    break;
          return;
        }
      } while (values.hasNext());
    } else if (search(node.getElement(), key).size() > 0) {

      TreeNode<Key, Value> leftSibling = null, rightSibling = null;
      if (node.getLeft() != null) {

        leftSibling = node.getLeft().getElement();
      }
      if (node.getRight() != null) {

        rightSibling = node.getRight().getElement();
      }

      if (leftSibling != null && leftSibling.getSize() > t - 1) {
        // System.out.println("BTree.deleteAtLeaf 22");
        rightRotate(leftSibling, leftParentPair, node);
        deleteAtLeaf(node, key, leftParentPair, rightParentPair, parent);
        //                        return;
      } else if (rightSibling != null && rightSibling.getSize() > t - 1) {
        // System.out.println("BTree.deleteAtLeaf 2233");

        leftRotate(node, rightParentPair, rightSibling);
        deleteAtLeaf(node, key, leftParentPair, rightParentPair, parent);
      } else if (leftSibling != null && leftSibling.getSize() == t - 1) {
        // System.out.println("BTree.deleteAtLeaf 22xxxxx");

        //                if (leftParentPair == root.getValues().getFirst() && root.getSize() == 1)
        // {
        //                    height--;
        //                }
        // node.getleft is leftSibling
        node = merge(node.getLeft(), leftParentPair, node, parent);
        if (rightParentPair != null) {
          // System.out.println("BTree.deleteAtLeaf 223344");
          deleteAtLeaf(node, key, rightParentPair.getLeft(), rightParentPair, parent);
        } else {
          // System.out.println("BTree.deleteAtLeaf 22324");

          deleteAtLeaf(node, key, null, null, parent);
        }
      } else if (rightSibling != null && rightSibling.getSize() == t - 1) {
        // System.out.println("BTree.deleteAtLeaf xxxx");

        //                if (rightParentPair == root.getValues().getFirst() && root.getSize() == 1)
        // {
        //                    height--;
        //                }
        node = merge(node, rightParentPair, node.getRight(), parent);
        if (leftParentPair != null) {
          // System.out.println("BTree.deleteAtLeaf 22aa");

          deleteAtLeaf(node, key, leftParentPair, leftParentPair.getRight(), parent);
        } else {
          // System.out.println("BTree.deleteAtLeaf 22bb");

          deleteAtLeaf(node, key, null, null, parent);
        }
      } else {
        MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getElement().getValues());
        Pair<Key, Value> pair;
        Key k;
        //                    boolean deleted=false;
        do {
          pair = values.getNext();
          k = pair.getKey();
          if (key.compareTo(k) == 0) {
            // System.out.println("Deleting in root" + " Before:- " + toString());
            if (size == 1) {
              root = null;
            } else {

              values.delete(node.getElement().getValues());
              node.getElement().decreaseSize();
            }
            size--;
            //                        if (size == 0) {
            //                            height = -1;
            //                            root = null;
            //                        }
            // System.out.println("Deleting in root" + " After:- " + toString());

            //                            delete(node,key,parentPair);
            //                            return true;
            //                            deleted=true;
          }
        } while (values.hasNext());
        //                    return deleted;
      }
    }
    //        }
    //            return false;
  }

  private Pair<Key, Value> deleteFirst(
      Node<TreeNode<Key, Value>> node,
      Node<Pair<Key, Value>> rightParentPair,
      Node<TreeNode<Key, Value>> parent) {
    if (root == null || node == null) {
      return null;
    }
    Pair<Key, Value> pair_to_return = node.getElement().getValues().getFirst().getElement();

    TreeNode<Key, Value> treeNode = node.getElement();

    if (treeNode.hasChildren()) {
      Node<TreeNode<Key, Value>> leftChild = treeNode.getChildren().getFirst();
      Node<TreeNode<Key, Value>> rightChild = leftChild.getRight();
      if (rightChild != null && rightChild.getElement().getSize() > t - 1) {
        // System.out.println("BTree.deleteFirst 1");
        treeNode
            .getValues()
            .getFirst()
            .setElement(deleteFirst(rightChild, treeNode.getValues().getFirst().getNext(), node));
        return pair_to_return;

      } else if (leftChild != null && leftChild.getElement().getSize() > t - 1) {
        // System.out.println("BTree.deleteFirst 2");

        treeNode.getValues().getFirst().setElement(deleteLast(leftChild, null, node));
        return pair_to_return;

      } else if (leftChild != null && rightChild != null) {
        //                if (leftParentPair == root.getValues().getFirst() && root.getSize() ==1) {
        //                    height--;
        //                }
        // System.out.println("BTree.deleteFirst 3");

        // node.getleft is leftSibling
        Node<Pair<Key, Value>> pair = treeNode.getValues().getFirst();
        Node<TreeNode<Key, Value>> node2 =
            merge(leftChild, treeNode.getValues().getFirst(), rightChild, node);
        delete(node2, pair);
        return pair_to_return;

        //                deleteAtLeaf(node, key, rightParentPair.getLeft(), rightParentPair);
        //            } else if (rightSibling != null && rightSibling.getSize() == t - 1) {
        //                if (rightParentPair == root.getValues().getFirst() && root.getSize() == 1)
        // {
        //                    height--;
        //                }
        //                node = merge(node, rightParentPair, rightSibling);
        //                deleteAtLeaf(node, key, leftParentPair, leftParentPair.getRight());
      } else {
        // System.out.println("BTree.deleteFirst 4");

        treeNode.decreaseSize();
        size--;
        treeNode.getValues().removeFirst();
        return pair_to_return;

        //                MyIterator<Pair<Key, Value>> values = new
        // MyIterator<>(node.getElement().getValues());
        //                Pair<Key, Value> pair;
        //                Key k;
        //                    boolean deleted=false;
        //                do {
        //                    pair = values.getNext();
        //                    k = pair.getKey();
        //                    if (key.compareTo(k) == 0) {
        //                        values.delete(node.getElement().getValues());
        //                        size--;
        //                        if (size == 0) {
        //                            height = -1;
        //                            root = null;
        //                        }
        //                        node.getElement().decreaseSize();
        //                            delete(node,key,parentPair);
        //                            return true;
        //                            deleted=true;
        //                    }
        //                } while (values.hasNext());
        //                    return deleted;
      }
      //        }

    } else {
      if (treeNode.getSize() > t - 1) {
        // System.out.println("BTree.deleteFirst 5");
        treeNode.decreaseSize();

        treeNode.getValues().removeFirst();
        size--;
        return pair_to_return;

      } else {
        TreeNode<Key, Value> rightSibling;
        rightSibling = node.getRight().getElement();
        if (rightSibling != null && rightSibling.getSize() > t - 1) {
          // System.out.println("BTree.deleteFirst 6");

          leftRotate(node, rightParentPair, rightSibling);
          deleteFirst(node, rightParentPair, parent);
          return pair_to_return;

          //                     size--;
        } else if (rightSibling != null && rightSibling.getSize() == t - 1) {
          //                    if (rightParentPair == root.getValues().getLast() && root.getSize()
          // == 1) {
          //                        height--;
          //                    }
          // System.out.println("BTree.deleteFirst 7");

          Node<Pair<Key, Value>> rightParent = rightParentPair.getRight();

          Node<TreeNode<Key, Value>> node2 = merge(node, rightParentPair, node.getRight(), parent);
          deleteFirst(node2, rightParent, parent);
          return pair_to_return;

        } else {
          // System.out.println("BTree.deleteFirst 8");

          treeNode.decreaseSize();
          treeNode.getValues().removeFirst();
          size--;
          return pair_to_return;
        }
      }
    }
  }

  private Pair<Key, Value> deleteLast(
      Node<TreeNode<Key, Value>> node,
      Node<Pair<Key, Value>> leftParentPair,
      Node<TreeNode<Key, Value>> parent) {
    if (root == null || node == null) {
      return null;
    }
    Pair<Key, Value> pair_to_return = node.getElement().getValues().getLast().getElement();
    // System.out.println("BTree.deleteLast");
    TreeNode<Key, Value> treeNode = node.getElement();

    if (treeNode.hasChildren()) {
      Node<TreeNode<Key, Value>> rightChild = treeNode.getChildren().getLast();
      Node<TreeNode<Key, Value>> leftChild = rightChild.getPrev();
      // System.out.println(leftChild.toString() + "abc");
      // System.out.println(rightChild.toString() + "cde");
      if (leftChild != null && leftChild.getElement().getSize() > t - 1) {
        // System.out.println("BTree.deleteLast x");
        treeNode
            .getValues()
            .getLast()
            .setElement(deleteLast(leftChild, treeNode.getValues().getLast().getPrev(), node));
        return pair_to_return;

      } else if (rightChild != null && rightChild.getElement().getSize() > t - 1) {
        // System.out.println("BTree.deleteLast x2");

        treeNode.getValues().getLast().setElement(deleteFirst(rightChild, null, node));
        return pair_to_return;

      } else if (leftChild != null && rightChild != null) {
        //                if (leftParentPair == root.getValues().getFirst() && root.getSize() == 1)
        // {
        //                    height--;
        //                }
        // node.getleft is leftSibling
        // System.out.println("BTree.deleteLast x3");

        Node<Pair<Key, Value>> pair = treeNode.getValues().getLast();
        Node<TreeNode<Key, Value>> node2 =
            merge(leftChild, treeNode.getValues().getLast(), rightChild, node);
        delete(node2, pair);
        return pair_to_return;

        //                deleteAtLeaf(node, key, rightParentPair.getLeft(), rightParentPair);
        //            } else if (rightSibling != null && rightSibling.getSize() == t - 1) {
        //                if (rightParentPair == root.getValues().getFirst() && root.getSize() == 1)
        // {
        //                    height--;
        //                }
        //                node = merge(node, rightParentPair, rightSibling);
        //                deleteAtLeaf(node, key, leftParentPair, leftParentPair.getRight());
      } else {
        // System.out.println("BTree.deleteLast x33");

        treeNode.decreaseSize();
        treeNode.getValues().removeLast();
        size--;
        return pair_to_return;

        //                MyIterator<Pair<Key, Value>> values = new
        // MyIterator<>(node.getElement().getValues());
        //                Pair<Key, Value> pair;
        //                Key k;
        //                    boolean deleted=false;
        //                do {
        //                    pair = values.getNext();
        //                    k = pair.getKey();
        //                    if (key.compareTo(k) == 0) {
        //                        values.delete(node.getElement().getValues());
        //                        size--;
        //                        if (size == 0) {
        //                            height = -1;
        //                            root = null;
        //                        }
        //                        node.getElement().decreaseSize();
        //                            delete(node,key,parentPair);
        //                            return true;
        //                            deleted=true;
        //                    }
        //                } while (values.hasNext());
        //                    return deleted;
      }
      //        }

    } else {
      if (treeNode.getSize() > t - 1) {
        // System.out.println("BTree.deleteLast x44");

        treeNode.decreaseSize();
        treeNode.getValues().removeLast();
        size--;
        return pair_to_return;

      } else {
        // System.out.println("BTree.deleteLast x55");

        TreeNode<Key, Value> leftSibling;
        leftSibling = node.getLeft().getElement();
        if (leftSibling != null && leftSibling.getSize() > t - 1) {
          // System.out.println("BTree.deleteLast x41");

          rightRotate(leftSibling, leftParentPair, node);
          deleteLast(node, leftParentPair, parent);
          return pair_to_return;

        } else if (leftSibling != null && leftSibling.getSize() == t - 1) {
          // System.out.println("BTree.deleteLast x56");

          //                    if (leftParentPair == root.getValues().getFirst() && root.getSize()
          // == 1) {
          //                        height--;
          //                    }

          Node<Pair<Key, Value>> leftParent = leftParentPair.getLeft();

          Node<TreeNode<Key, Value>> node2 = merge(node.getLeft(), leftParentPair, node, parent);
          deleteLast(node2, leftParent, parent);
          return pair_to_return;

        } else {
          // System.out.println("BTree.deleteLast xyy");

          treeNode.decreaseSize();
          treeNode.getValues().removeLast();
          size--;
          return pair_to_return;
        }
      }
    }
  }

  private void rightRotate(
      TreeNode<Key, Value> leftSibling,
      Node<Pair<Key, Value>> leftParentPair,
      Node<TreeNode<Key, Value>> node) {
    // System.out.println(root.toString() + " before rightrotate");
    if (root == null) {
      return;
    }
    // System.out.println(node.getElement() + "element");
    leftSibling.decreaseSize();
    node.getElement().increaseSize();

    if (node.getElement().hasChildren()) {
      Node<Pair<Key, Value>> leftLastValue = leftSibling.getValues().getLast();
      // System.out.println(leftSibling.toString() + "left sibling");
      Node<TreeNode<Key, Value>> leftLastChild = leftSibling.getChildren().getLast();
      leftSibling.getChildren().setTail(null, leftLastChild.getPrev());
      leftSibling.getValues().setTail(null, leftLastValue.getPrev());
      leftLastChild.setNext(null);
      leftLastChild.setPrev(null);
      Pair<Key, Value> pair = leftParentPair.getElement();
      leftParentPair.setElement(leftLastValue.getElement());
      node.getElement().addAtStart(pair, leftLastChild);
    } else {
      Node<Pair<Key, Value>> leftLastValue = leftSibling.getValues().getLast();
      //            Node<TreeNode<Key, Value>> leftLastChild = leftSibling.getChildren().getLast();
      //            leftSibling.getChildren().setTail(null, leftLastChild.getPrev());
      leftSibling.getValues().setTail(null, leftLastValue.getPrev());
      //            leftLastChild.setNext(null);
      //            leftLastChild.setPrev(null);
      Pair<Key, Value> pair = leftParentPair.getElement();
      leftParentPair.setElement(leftLastValue.getElement());
      node.getElement().addAtStart(pair, null);
    }

    // System.out.println(root.toString() + " after rightrotate");
  }

  private void leftRotate(
      Node<TreeNode<Key, Value>> node,
      Node<Pair<Key, Value>> rightParentPair,
      TreeNode<Key, Value> rightSibling) {
    // System.out.println(root.toString() + " before leftRotate");
    if (root == null) {
      return;
    }
    // System.out.println("node size earlier " + node.getElement().getSize());
    node.getElement().increaseSize();
    rightSibling.decreaseSize();

    if (node.getElement().hasChildren()) {

      Node<Pair<Key, Value>> rightFirstValue = rightSibling.getValues().getFirst();
      Node<TreeNode<Key, Value>> rightFirstChild = rightSibling.getChildren().getFirst();

      rightSibling.getChildren().setHead(rightFirstChild.getNext(), null);
      rightSibling.getValues().setHead(rightFirstValue.getNext(), null);

      rightFirstChild.setNext(null);
      rightFirstChild.setPrev(null);

      Pair<Key, Value> pair = rightParentPair.getElement();
      rightParentPair.setElement(rightFirstValue.getElement());

      node.getElement().addAtEnd2(pair, rightFirstChild);
    } else {
      Node<Pair<Key, Value>> rightFirstValue = rightSibling.getValues().getFirst();
      //            Node<TreeNode<Key, Value>> rightFirstChild =
      // rightSibling.getChildren().getFirst();

      //            rightSibling.getChildren().setHead(rightFirstChild.getNext(), null);
      rightSibling.getValues().setHead(rightFirstValue.getNext(), null);

      //            rightFirstChild.setNext(null);
      //            rightFirstChild.setPrev(null);

      Pair<Key, Value> pair = rightParentPair.getElement();
      rightParentPair.setElement(rightFirstValue.getElement());

      node.getElement().addAtEnd2(pair, null);
    }
    // System.out.println(root.toString() + " after leftRotate");
  }

  private Node<TreeNode<Key, Value>> merge(
      Node<TreeNode<Key, Value>> leftChild,
      Node<Pair<Key, Value>> pairNode,
      Node<TreeNode<Key, Value>> rightChild,
      Node<TreeNode<Key, Value>> node) {
    //        prev=pairNode.getPrev();
    if (root == null) {
      return null;
    }
    // System.out.println(
    //                root.toString()
    //                        + " before merge "
    //                        + leftChild.getElement().hasChildren()
    //                        + node.getElement().hasChildren()
    //                        + rightChild.getElement().hasChildren());
    // System.out.println(leftChild.toString() + "leftchild before");
    // System.out.println(rightChild.toString() + "rightchild before");
    //        next=pairNode.getNext();
    // System.out.println(pairNode.toString() + "pairnode");
    //        Pair<Key, Value> pair = pairNode.getElement();
    leftChild
        .getElement()
        .setsize(leftChild.getElement().getSize() + 1 + rightChild.getElement().getSize());
    // System.out.println(node.getElement().getSize() + "size");
    node.getElement().decreaseSize();
    // System.out.println(node.getElement().getSize() + "size");

    if (pairNode.getPrev() != null) {
      // System.out.println(pairNode.getPrev().toString() + " pair node get prev");
      if (node.getElement().getValues().getLast() == pairNode) {
        node.getElement().getValues().removeLast();
      } else {

        pairNode.getPrev().setNext(pairNode.getNext());
      }
    } else {
      node.getElement().getChildren().setHead(leftChild, null);
    }

    if (pairNode.getNext() != null) {
      if (node.getElement().getValues().getFirst() == pairNode) {
        node.getElement().getValues().removeFirst();
      } else {
        pairNode.getNext().setPrev(pairNode.getPrev());
      }
      // System.out.println(pairNode.getNext().toString() + " pair node get next");
      //            if(pairNode.getNext().getElement()
    } else {
      node.getElement().getChildren().setTail(null, leftChild);
    }

    leftChild.getElement().addAtEnd(pairNode, rightChild.getElement());
    leftChild.setNext(rightChild.getNext());
    if (rightChild.getNext() != null) {
      rightChild.getNext().setPrev(leftChild);
    }

    leftChild.setNext(rightChild.getNext());
    // System.out.println(node.getElement().getSize() + "size");

    if (node.getElement().getSize() == 0) {
      root = leftChild.getElement();
      node = null;
      height--;
      // System.out.println("Resetting root");
      //            Scanner s=new Scanner(//System.in);
      //            if(s.next().equals("1")){
      //                throw new NotImplementedException();
      //            }
      //            root.setHasChildren(false);
    }
    //        {
    // if(leftChild.getElement().has)
    //        }
    // System.out.println(leftChild.getElement().toString());
    //        if(leftChild.getElement().hasChildren()){
    //
    //            //System.out.println(leftChild.getElement().getChildren().toString());
    //        }

    if (node != null) {
      // System.out.println(node.getElement().toString() + "for node");
      //            if(node.getElement().hasChildren()){
      //                //System.out.println(node.getElement().getChildren().toString()+"for node");
      //
      //            }
    }

    // System.out.println(
    //                leftChild.getElement().hasChildren() + "" +
    // rightChild.getElement().hasChildren());
    //        //System.out.println(leftChild.toString());
    //        //System.out.println(node.toString());
    // System.out.println(root.toString() + " after merge ");

    rightChild = leftChild;

    return leftChild;
  }

  @Override
  public String toString() {
    if (root != null) {

      return root.toString();
    }
    return "[]";
    //        throw new RuntimeException("Not Implemented");

  }

  private boolean checkPresence(TreeNode<Key, Value> node, Key in_key) {
    if (root == null || node == null) {
      return false;
    }
    //        List<Value> list_new = new ArrayList<>();
    if (node.hasChildren()) {
      //            //System.out.println("BTree.checkPresence");
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getChildren());
      TreeNode<Key, Value> child;
      Pair<Key, Value> pair;
      Key key;
      do {
        child = children.getNext();
        pair = values.getNext();
        key = pair.getKey();
        if (in_key.compareTo(key) == 0) {
          return true;
          //                    list_new.addAll(search(child, in_key));
          //                    list_new.add(pair.getVal());
        } else if (in_key.compareTo(key) < 0) {

          if (checkPresence(child, in_key)) {
            return true;
          }
          break;
        }
      } while ((values.hasNext() && children.hasNext()));

      if (in_key.compareTo(key) > 0) {
        return checkPresence(children.getNext(), in_key);
        //                list_new.addAll(search(children.getNext(), in_key));
      }
    } else {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      Pair<Key, Value> pair;
      Key key;
      pair = values.getNext();
      key = pair.getKey();
      if (in_key.compareTo(key) == 0) {
        //                list_new.add(pair.getVal());
        return true;
      }
      while (values.hasNext()) {
        pair = values.getNext();
        key = pair.getKey();
        if (in_key.compareTo(key) == 0) {
          return true;
          //                    list_new.add(pair.getVal());
        } else if (in_key.compareTo(key) < 0) {
          break;
        }
      }
    }
    return false;
  }

  private boolean checkConsistency(TreeNode<Key, Value> node) {
    if (root == null || node == null) {
      return true;
    }
    //        List<Value> list_new = new ArrayList<>();
    if (node.hasChildren()) {
      //            //System.out.println("BTree.checkPresence");
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      MyIterator<TreeNode<Key, Value>> children = new MyIterator<>(node.getChildren());
      TreeNode<Key, Value> child;
      Pair<Key, Value> pair;
      Key key;
      do {
        child = children.getNext();
        pair = values.getNext();
        //                key = pair.getKey();
        //                if (in_key.compareTo(key) == 0) {
        //                    return true;
        //                    list_new.addAll(search(child, in_key));
        //                    list_new.add(pair.getVal());
        //                } else if (in_key.compareTo(key) < 0) {

        if (!checkConsistency(child)) {
          return false;
        }

        if (children.getNode().getNext().getPrev() != children.getNode()) {
          return false;
        }
        if (values.hasNext() && values.getNode().getNext().getPrev() != values.getNode()) {
          return false;
        }
        //                    break;

      } while ((values.hasNext() && children.hasNext()));

      if (children.getNode().getNext().getNext() != null) {
        return false;
      }

      //            if (in_key.compareTo(key) > 0) {
      //                return checkPresence(children.getNext(),in_key);
      ////                list_new.addAll(search(children.getNext(), in_key));
      //            }
    } else {
      MyIterator<Pair<Key, Value>> values = new MyIterator<>(node.getValues());
      Pair<Key, Value> pair;
      Key key;
      pair = values.getNext();
      key = pair.getKey();
      //            if (in_key.compareTo(key) == 0) {
      //                list_new.add(pair.getVal());
      //                return true;
      //            }
      do {
        //                child = children.getNext();
        pair = values.getNext();
        //                key = pair.getKey();
        //                if (in_key.compareTo(key) == 0) {
        //                    return true;
        //                    list_new.addAll(search(child, in_key));
        //                    list_new.add(pair.getVal());
        //                } else if (in_key.compareTo(key) < 0) {

        //                if(!checkConsistency(child)) {
        //                    return false;
        //                }

        //                if(children.getNode().getNext().getPrev()!=children.getNode()) {
        //                    return false;
        //                }
        if (values.hasNext() && values.getNode().getNext().getPrev() != values.getNode()) {
          return false;
        }
        //                    break;

      } while (values.hasNext());
    }
    return true;
  }
}

