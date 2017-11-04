import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinkedListImage implements CompressedImageInterface {
  LinkedList<Integer>[] masterArray;
  int width, height;
  boolean black_computed = false;
  // int[] num_black;
  // see if storing is better or not.....
  public LinkedList<Integer> getRow(int i) {
    return masterArray[i];
  }

  public void setRow(int i, LinkedList<Integer> l) {
    masterArray[i] = l;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public LinkedListImage(String filename) {
    File file = new File(filename);
    try {
      Scanner sc = new Scanner(file);
      // masterList = new LinkedList<LinkedList<Integer> >();
      LinkedList<Integer> temp;
      this.width = sc.nextInt();
      this.height = sc.nextInt();

      // masterArray =(LinkedList<Integer>[]) new Object[height];

      // @SuppressWarnings("unchecked")
      masterArray = new LinkedList[height];

      // num_black=new int[height];
      int val = 1, prev_val = 1;
      for (int i = 0; i < height; i++) {
        prev_val = 1;
        val = 1;
        temp = new LinkedList<Integer>();
        // prev_val = sc.nextInt();

        // if (prev_val == 0) {
        //         // num_black[i]++;
        //         temp.insertLast(0);
        // }
        for (int j = 0; j < width; j++) {
          val = sc.nextInt();
          if (val == 0 && prev_val == 1) {
            // num_black[i]++;
            // if (prev_val == 1) {
            temp.insertLast(j);
            // }
          } else if (val == 1 && prev_val == 0) {
            temp.insertLast(j - 1);
          }
          prev_val = val;
        }
        if (val == 0) {
          temp.insertLast(width - 1);
        }
        // masterList.insertLast(temp);
        masterArray[i] = temp;
      }
    } catch (FileNotFoundException f) {
      System.out.println("FILE NOT FOUND!!!!");
    }
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public LinkedListImage(boolean[][] grid, int width, int height) {
    this.width = width;
    this.height = height;
    boolean val = true, prev_val = true;

    // @SuppressWarnings("unchecked")
    masterArray = new LinkedList[height];
    // int count=0;
    // num_black=new int[height];
    // masterList = new LinkedList<LinkedList<Integer> >();
    LinkedList<Integer> temp;
    for (int i = 0; i < height; i++) {
      prev_val = true;
      val = true;
      temp = new LinkedList<Integer>();
      // prev_val = grid[i][0];
      // count=0;
      // if (prev_val == false) {
      //   temp.insertLast(0);
      //   // num_black[i]++;
      // }

      for (int j = 0; j < width; j++) {
        val = grid[i][j];
        if (val == false && prev_val == true) {
          // num_black[i]++;
          // if (prev_val == true) {
          temp.insertLast(j);
          // count++;// }
        } else if (val == true && prev_val == false) {
          temp.insertLast(j - 1);
          // count++;
        }
        prev_val = val;
      }

      if (val == false) {
        temp.insertLast(width - 1);
        // count++;
      }
      // masterList.insertLast(temp);
      // System.out.println(count);
      // if(count%2!=0) {
      // System.out.println("Here");
      // temp.printer();
      // }
      masterArray[i] = temp;
      // temp.printer();
      // masterArray[i].printer();

      // System.out.println(count);
      // inverted=false;
    }
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public boolean getPixelValue(int x, int y) throws PixelOutOfBoundException {
    int start, end;
    if (x >= height || x < 0 || y >= width || y < 0) {
      throw new PixelOutOfBoundException("Pixel out of bound");
    }

    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    // for(int i=0; i<x; i++) {
    // masterIterator.next();
    // }

    secondaryIterator = new MyIterator<Integer>(masterArray[x]);
    while (secondaryIterator.hasNext()) {
      start = secondaryIterator.next();
      end = secondaryIterator.next();

      if (y >= start && y <= end) {
        return false;
      } else if (y < start) {
        return true;
      }
    }
    return true;
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public void setPixelValue(int x, int y, boolean val) throws PixelOutOfBoundException {
    // System.out.println(this.toStringCompressed());
    boolean inserted = false;
    int start = -1, end = -1, prev_start = -1, prev_end = -1;
    if (x >= height || x < 0 || y >= width || y < 0) {
      throw new PixelOutOfBoundException("Pixel out of bound");
    }

    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    LinkedList<Integer> temp = new LinkedList<Integer>();

    // for(int i=0; i<x; i++) {
    // masterIterator.next();
    // }

    secondaryIterator = new MyIterator<Integer>(masterArray[x]);
    if (secondaryIterator.hasNext()) {

      if (val == false) {

        //////							//complete this part
        prev_start = secondaryIterator.next();
        prev_end = secondaryIterator.next();

        if (y <= prev_start) {

          if (y >= prev_start - 1) {
            temp.insertLast(y);
            temp.insertLast(prev_end);
          } else {
            temp.insertLast(y);
            temp.insertLast(y);
            temp.insertLast(prev_start);
            temp.insertLast(prev_end);
          }

          inserted = true;
        } else if (y <= prev_end) {
          temp.insertLast(prev_start);
          temp.insertLast(prev_end);
          inserted = true;
        }

        if (secondaryIterator.hasNext()) {

          while (secondaryIterator.hasNext()) {
            start = secondaryIterator.next();
            end = secondaryIterator.next();

            if (inserted == true) {
              temp.insertLast(start);
              temp.insertLast(end);
              continue;
            }
            if (y >= start && y <= end) {
              temp.insertLast(prev_start);
              temp.insertLast(prev_end);
              temp.insertLast(start);
              temp.insertLast(end);
              inserted = true;
              continue;
              // break;
              // if(val==false) {
              //         break;
              // }else{
              //         temp.insertLast(start);
              //         temp.insertLast(y-1);
              //         if(y<15) {
              //                 temp.insertLast(y+1);
              //                 temp.insertLast(end);
              //         }
              // }
              // // return false;
            } else if (y < start) {
              // if(y>=prev_start && y<=prev_end){
              // inserted=true;
              // continue;
              // }

              if (prev_end + 1 == y) {
                if (y + 1 == start) {
                  temp.insertLast(prev_start);
                  temp.insertLast(end);
                  // inserted=true;

                } else {
                  temp.insertLast(prev_start);
                  temp.insertLast(y);
                  temp.insertLast(start);
                  temp.insertLast(end);
                  // inserted=true;

                }
              } else if (start - 1 == y) {
                temp.insertLast(prev_start);
                temp.insertLast(prev_end);
                temp.insertLast(y);
                temp.insertLast(end);
                // inserted=true;

              } else {
                temp.insertLast(prev_start);
                temp.insertLast(prev_end);
                temp.insertLast(y);
                temp.insertLast(y);
                temp.insertLast(start);
                temp.insertLast(end);
                // inserted=true;

              }
              inserted = true;
              continue;
              // return true;
            } else {
              temp.insertLast(prev_start);
              temp.insertLast(prev_end);
              prev_start = start;
              prev_end = end;
            }
          }
          if (inserted == false) {
            if (y == end + 1) {
              temp.insertLast(start);
              temp.insertLast(end + 1);

            } else if (y > end) {
              temp.insertLast(start);
              temp.insertLast(end);
              temp.insertLast(y);
              temp.insertLast(y);
            }
          }
        } else {
          // if (y >= prev_start && y <= prev_end) {
          //
          // } else if (y == prev_start - 1) {
          //   temp.insertLast(prev_start - 1);
          //   temp.insertLast(prev_end);
          //
          // } else
          if (y == prev_end + 1) {
            temp.insertLast(prev_start);
            temp.insertLast(prev_end + 1);
          }
          // else if (y < prev_start) {
          //   temp.insertLast(y);
          //   temp.insertLast(y);
          //   temp.insertLast(prev_start);
          //   temp.insertLast(prev_end);
          // }
          else if (y > prev_end) {
            temp.insertLast(prev_start);
            temp.insertLast(prev_end);
            temp.insertLast(y);
            temp.insertLast(y);
          }
          // temp.insertLast(y);
          // temp.insertLast(y);
          // inserted
        }

        // return true;
      } else {
        while (secondaryIterator.hasNext()) {
          start = secondaryIterator.next();
          end = secondaryIterator.next();

          if (y == start) {
            if (start != end) {
              temp.insertLast(start + 1);
              temp.insertLast(end);
              // break;
            }
          } else if (y == end) {
            if (start != end) {
              temp.insertLast(start);
              temp.insertLast(end - 1);
              // break;
            }
          } else if (y > start && y < end) {

            temp.insertLast(start);
            temp.insertLast(y - 1);
            if (y < width - 1) {
              temp.insertLast(y + 1);
              temp.insertLast(end);
            }
            // break;
          } else {
            temp.insertLast(start);
            temp.insertLast(end);
          }
        }
      }
    } else if (val == false) {
      temp.insertLast(y);
      temp.insertLast(y);
    }

    masterArray[x] = temp;
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    // System.out.println(this.toStringCompressed());

    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public int[] numberOfBlackPixels() {
    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    // LinkedList<Integer> temp;
    int num_black[] = new int[height];
    for (int i = 0; i < height; i++) {
      // temp=new LinkedList<Integer>();

      // secondaryIterator = new MyIterator<Integer>(masterIterator.next());
      secondaryIterator = new MyIterator<Integer>(masterArray[i]);

      if (!secondaryIterator.hasNext()) {
        num_black[i] = 0;
      } else {
        int start, end;
        while (secondaryIterator.hasNext()) {
          start = secondaryIterator.next();
          end = secondaryIterator.next();
          num_black[i] += end - start + 1;
        }
      }

      // masterIterator.replaceNextAndMove(temp);

    }
    return num_black;
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public void invert() {
    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    LinkedList<Integer> temp;
    int start, end;
    for (int i = 0; i < height; i++) {
      temp = new LinkedList<Integer>();
      // masterArray[i].printer();

      // secondaryIterator = new MyIterator<Integer>(masterIterator.next());
      secondaryIterator = new MyIterator<Integer>(masterArray[i]);

      if (!secondaryIterator.hasNext()) {
        temp.insertLast(0);
        temp.insertLast(width - 1);
      } else {
        start = secondaryIterator.next();
        end = secondaryIterator.next();

        if (start == 0) {
          if (end < width - 1) {
            temp.insertLast(end + 1);
          }
        } else {
          temp.insertLast(0);
          temp.insertLast(start - 1);
          if (end < width - 1) {
            temp.insertLast(end + 1);
          }
        }
        // if (start != 0) {
        // } if(start>=0) {

        // }
        // if (end < width - 1) {
        // }
        while (secondaryIterator.hasNext()) {
          start = secondaryIterator.next();
          // if(!secondaryIterator.hasNext()){
          // }
          end = secondaryIterator.next();

          temp.insertLast(start - 1);
          if (end < width - 1) {
            temp.insertLast(end + 1);
          }
        }

        if (end < width - 1) {
          temp.insertLast(width - 1);
        }
      }

      masterArray[i] = temp;
    }
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public int jump(int j, int start1, int start2, int end1, int end2, int count) {
    // System.out.println(j);
    // System.out.println(j+" "+start1+" "+end1+" "+start2+" "+end2+" "+count);
    // int passed=j;
    int minima = Math.min(start1, start2);

    // if(Math.min(j,minima)==j && j!=minima) {
    // return minima;
    // }
    // System.out.println("Here2");

    switch (count) {
      case 0:
        if (j < minima) {

          return minima;
        } else {
          if (j < start1 || j < start2) {

            return Math.max(start1, start2);
          } else {

            return width;
          }
        }
        // break;
      case 2:
        // System.out.println(end1);

        return Math.min(end1, end2) + 1;
        // break;
      case 1:
        if (j >= start1 && j <= end1) {

          if (start2 >= start1 && start2 <= end1) {
            return start2;
          }
          // System.out.println("Here");
          else if (end1 == width) {
            return end1;
          }
          return end1 + 1;
        } else if (j >= start2 && j <= end2) {
          // System.out.println("Here");
          if (start1 >= start2 && start1 <= end2) {
            return start1;
          } else if (end2 == width) {
            return end2;
          }
          return end2 + 1;
        }

        // break;
    }
    return j + 1;
  }

  public void performAnd(CompressedImageInterface img) throws BoundsMismatchException {

    LinkedListImage l = (LinkedListImage) img;
    if (height != l.getHeight() || width != l.getWidth()) {
      throw new BoundsMismatchException("");
    }

    // System.out.println(l.toStringCompressed());
    // System.out.println(this.toStringCompressed());

    // System.out.println(l.toStringUnCompressed());
    // System.out.println(masterArray[1]);

    int count = -1, prev_count, temper;
    int start1, start2, end1, end2;
    LinkedList<Integer> temp, list1, list2;
    MyIterator<Integer> l1, l2;
    for (int i = 0; i < height; i++) {
      list1 = getRow(i);
      list2 = l.getRow(i);
      l1 = new MyIterator<Integer>(list1);
      l2 = new MyIterator<Integer>(list2);
      temp = new LinkedList<Integer>();

      // count=0;
      if (l1.hasNext() && l2.hasNext()) {
        // System.out.println("Hi a");

        start1 = l1.next();
        start2 = l2.next();
        end1 = l1.next();
        end2 = l2.next();
        prev_count = 0;

        for (int j = 0; j < width; j++) {
          if (j > end1) {
            if (l1.hasNext()) {
              start1 = l1.next();
              end1 = l1.next();
            }
          }
          if (j > end2) {
            if (l2.hasNext()) {
              start2 = l2.next();
              end2 = l2.next();
            }
          }
          count = 0;
          if (j >= start1 && j <= end1) {
            count++;
          }
          if (j >= start2 && j <= end2) {
            count++;
          }

          switch (count) {
            case 2: // for getting 0
            case 1:
              if (prev_count == 0) {
                temp.insertLast(j);
                // System.out.println(j-1);
              }

              break;
              // case 1: //both these cases are for getting 1
            case 0:
              if (prev_count != 0) {
                temp.insertLast(j - 1);
              }
              // break;
          }

          prev_count = count;

          temper = jump(j, start1, start2, end1, end2, count) - 1;
          if (temper >= j) {
            j = temper;
          }
        }
        if (count != 0) {
          temp.insertLast(width - 1);
        }
        // switch(count) {
        // // case 1:
        //         // if(prev_count%2==0) {
        //                 // temp.insertLast(j-1);
        //                 // System.out.println(j-1);
        //         // }
        //
        //         // break;
        // case 0:
        // case 2:
        //         // if(prev_count==1) {
        //                 temp.insertLast(j);
        //         // }
        //         // break;
        // }

      } else if (l1.hasNext()) {
        temp = list1;
      } else if (l2.hasNext()) {
        temp = list2;
      } else {

      }
      // temp.insertLast(0);
      // temp.insertLast(width);
      // System.out.println("Hi");

      // continue;

      masterArray[i] = temp;
      // System.out.println(temp.getFirst().gett());
      // System.out.println(masterArray);

    }
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public void performOr(CompressedImageInterface img) throws BoundsMismatchException {
    LinkedListImage l = (LinkedListImage) img;
    if (height != l.getHeight() || width != l.getWidth()) {
      throw new BoundsMismatchException("");
    }
    // System.out.println(l.toStringCompressed());
    // System.out.println(this.toStringCompressed());

    // System.out.println(l.toStringUnCompressed());
    // System.out.println(masterArray[1]);

    int count = -1, prev_count, temper;
    int start1, start2, end1, end2;
    LinkedList<Integer> temp;
    MyIterator<Integer> l1, l2;
    for (int i = 0; i < height; i++) {
      l1 = new MyIterator<Integer>(getRow(i));
      l2 = new MyIterator<Integer>(l.getRow(i));
      temp = new LinkedList<Integer>();

      // count=0;
      if (l1.hasNext() && l2.hasNext()) {
        // System.out.println("Hi a");

        start1 = l1.next();
        start2 = l2.next();
        end1 = l1.next();
        end2 = l2.next();
        prev_count = 1;

        for (int j = 0; j < width; j++) {

          if (j > end1) {
            if (l1.hasNext()) {
              start1 = l1.next();
              end1 = l1.next();
            }
          }
          if (j > end2) {
            if (l2.hasNext()) {
              start2 = l2.next();
              end2 = l2.next();
            }
          }
          count = 0;
          if (j >= start1 && j <= end1) {
            count++;
          }
          if (j >= start2 && j <= end2) {
            count++;
          }

          switch (count) {
            case 2: // for getting 0
              if (prev_count != 2) {
                temp.insertLast(j);
                // System.out.println(j-1);
              }

              break;
            case 0: // both these cases are for getting 1
            case 1:
              if (prev_count == 2) {
                temp.insertLast(j - 1);
              }
              // break;
          }

          prev_count = count;

          // if(i==15) {
          //
          //                System.out.print(j+" ");
          // }
          // j = jump(j, start1, start2, end1, end2, count) - 1;
          temper = jump(j, start1, start2, end1, end2, count) - 1;
          if (temper >= j) {
            j = temper;
          }
          // if(i==15) {
          //
          //                System.out.println(j+1);
          // }
          // System.out.println(j);

        }
        if (count == 2) {
          temp.insertLast(width - 1);
        }
        // switch(count) {
        // // case 1:
        //         // if(prev_count%2==0) {
        //                 // temp.insertLast(j-1);
        //                 // System.out.println(j-1);
        //         // }
        //
        //         // break;
        // case 0:
        // case 2:
        //         // if(prev_count==1) {
        //                 temp.insertLast(j);
        //         // }
        //         // break;
        // }

      } else {
        // temp.insertLast(0);
        // temp.insertLast(width);
        // System.out.println("Hi");

        // continue;

      }
      masterArray[i] = temp;
      // System.out.println(temp.getFirst().gett());
      // System.out.println(masterArray);

    }
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public void performXor(CompressedImageInterface img) throws BoundsMismatchException {
    LinkedListImage l = (LinkedListImage) img;
    if (height != l.getHeight() || width != l.getWidth()) {
      throw new BoundsMismatchException("");
    }

    // System.out.println(l.toStringCompressed());
    // System.out.println(this.toStringCompressed());

    // System.out.println(l.toStringUnCompressed());
    // System.out.println(masterArray[1]);

    int count = -1, prev_count, temper;
    int start1, start2, end1, end2;
    LinkedList<Integer> temp;
    MyIterator<Integer> l1, l2;
    for (int i = 0; i < height; i++) {
      l1 = new MyIterator<Integer>(getRow(i));
      l2 = new MyIterator<Integer>(l.getRow(i));
      temp = new LinkedList<Integer>();

      // count=0;
      if (l1.hasNext() && l2.hasNext()) {
        // System.out.println("Hi a");

        start1 = l1.next();
        start2 = l2.next();
        end1 = l1.next();
        end2 = l2.next();
        prev_count = 1;

        for (int j = 0; j < width; j++) {
          if (j > end1) {
            if (l1.hasNext()) {
              start1 = l1.next();
              end1 = l1.next();
            }
          }
          if (j > end2) {
            if (l2.hasNext()) {
              start2 = l2.next();
              end2 = l2.next();
            }
          }
          count = 0;
          if (j >= start1 && j <= end1) {
            count++;
          }
          if (j >= start2 && j <= end2) {
            count++;
          }

          switch (count) {
            case 1:
              if (prev_count != 1) {
                temp.insertLast(j - 1);
                // System.out.println(j-1);
              }

              break;
            case 0:
            case 2:
              if (prev_count == 1) {
                temp.insertLast(j);
              }
              // break;
          }

          prev_count = count;

          // if(i==8) {
          //         System.out.print(j);
          // }
          // j = jump(j, start1, start2, end1, end2, count) - 1;
          temper = jump(j, start1, start2, end1, end2, count) - 1;
          if (temper >= j) {
            j = temper;
          }
          // if(i==8) {
          //         System.out.println(j+1);
          // }

        }
        if (count != 1) {
          temp.insertLast(width - 1);
        }
        // switch(count) {
        // // case 1:
        //         // if(prev_count%2==0) {
        //                 // temp.insertLast(j-1);
        //                 // System.out.println(j-1);
        //         // }
        //
        //         // break;
        // case 0:
        // case 2:
        //         // if(prev_count==1) {
        //                 temp.insertLast(j);
        //         // }
        //         // break;
        // }

      } else {
        temp.insertLast(0);
        temp.insertLast(width - 1);
        // System.out.println("Hi");

        // continue;

      }
      masterArray[i] = temp;
      // System.out.println(temp.getFirst().gett());
      // System.out.println(masterArray);

    }
    // System.out.println("Hi");
    // System.out.println(this.toStringUnCompressed());

    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public String toStringUnCompressed() {
    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    StringBuilder sb = new StringBuilder();

    int count, start, end;
    sb.append(width + " " + height + ",");
    for (int i = 0; i < height - 1; i++) {
      // while(masterIterator.hasNext()) {
      // System.out.println(masterIterator.next());
      count = 0;
      secondaryIterator = new MyIterator<Integer>(masterArray[i]);
      while (secondaryIterator.hasNext()) {
        start = secondaryIterator.next();
        end = secondaryIterator.next();
        while (count < start) {
          sb.append(" 1");
          count++;
        }
        while (count <= end) {
          sb.append(" 0");
          count++;
        }
        // System.out.println(secondaryIterator.next()+" ");
        // System.out.println("yo");

      }
      while (count < width) {
        sb.append(" 1");
        count++;
      }
      // System.out.println("yo2");
      // if(i==height-1)
      sb.append(",");
      // masterIterator.next();
    }
    count = 0;
    secondaryIterator = new MyIterator<Integer>(masterArray[height - 1]);
    while (secondaryIterator.hasNext()) {
      start = secondaryIterator.next();
      end = secondaryIterator.next();
      while (count < start) {
        sb.append(" 1");
        count++;
      }
      while (count <= end) {
        sb.append(" 0");
        count++;
      }
      // System.out.println(secondaryIterator.next()+" ");
      // System.out.println("yo");

    }
    while (count < width) {
      sb.append(" 1");
      count++;
    }
    // System.out.println(secondaryIterator.next()+" ");
    // System.out.println("yo");

    // System.out.println("yo2");
    // if(i==height-1)
    // sb.append("-1");
    // System.out.println(sb);
    return sb.toString();
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public String toStringCompressed() {
    // MyIterator<LinkedList<Integer> > masterIterator =interator();
    MyIterator<Integer> secondaryIterator;
    StringBuilder sb = new StringBuilder();

    sb.append(width + " " + height + ", ");
    for (int i = 0; i < height - 1; i++) {
      // while(masterIterator.hasNext()) {
      // System.out.println(masterIterator.next());
      secondaryIterator = new MyIterator<Integer>(masterArray[i]);
      while (secondaryIterator.hasNext()) {
        sb.append(secondaryIterator.next() + " ");
        // System.out.println(secondaryIterator.next()+" ");
        // System.out.println("yo");

      }
      // System.out.println("yo2");
      // if(i==height-1)
      sb.append("-1, ");
      // masterIterator.next();
    }
    secondaryIterator = new MyIterator<Integer>(masterArray[height - 1]);
    while (secondaryIterator.hasNext()) {
      sb.append(secondaryIterator.next() + " ");
      // System.out.println(secondaryIterator.next()+" ");
      // System.out.println("yo");

    }
    // System.out.println("yo2");
    // if(i==height-1)
    sb.append("-1");
    // System.out.println(sb);
    return sb.toString();
    // you need to implement this
    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public static void main(String[] args) {
    // testing all methods here :
    boolean success = true;

    // check constructor from file
    CompressedImageInterface img1 = new LinkedListImage("sampleInputFile.txt");

    // check toStringCompressed
    String img1_compressed = img1.toStringCompressed();
    String img_ans =
        "16 16, -1, 5 7 -1, 3 7 -1, 2 7 -1, 2 2 6 7 -1, 6 7 -1, 6 7 -1, 4 6 -1, 2 4 -1, 2 3 14 15 -1, 2 2 13 15 -1, 11 13 -1, 11 12 -1, 10 11 -1, 9 10 -1, 7 9 -1";
    success = success && (img_ans.equals(img1_compressed));

    if (!success) {
      System.out.println("Constructor (file) or toStringCompressed ERROR");
      return;
    }

    // check getPixelValue
    boolean[][] grid = new boolean[16][16];
    for (int i = 0; i < 16; i++)
      for (int j = 0; j < 16; j++) {
        try {
          grid[i][j] = img1.getPixelValue(i, j);
        } catch (PixelOutOfBoundException e) {
          System.out.println("Errorrrrrrrr");
        }
      }

    // check constructor from grid
    CompressedImageInterface img2 = new LinkedListImage(grid, 16, 16);
    String img2_compressed = img2.toStringCompressed();
    success = success && (img2_compressed.equals(img_ans));

    if (!success) {
      System.out.println("Constructor (array) or toStringCompressed ERROR");
      return;
    }

    // check Xor
    try {
      img1.performXor(img2);
    } catch (BoundsMismatchException e) {
      System.out.println("Errorrrrrrrr");
    }
    for (int i = 0; i < 16; i++)
      for (int j = 0; j < 16; j++) {
        try {
          success = success && (!img1.getPixelValue(i, j));
        } catch (PixelOutOfBoundException e) {
          System.out.println("Errorrrrrrrr");
        }
      }

    if (!success) {
      System.out.println("performXor or getPixelValue ERROR");
      return;
    }

    // check setPixelValue
    for (int i = 0; i < 16; i++) {
      try {
        img1.setPixelValue(i, 0, true);
      } catch (PixelOutOfBoundException e) {
        System.out.println("Errorrrrrrrr");
      }
    }

    // check numberOfBlackPixels
    int[] img1_black = img1.numberOfBlackPixels();
    success = success && (img1_black.length == 16);
    for (int i = 0; i < 16 && success; i++) success = success && (img1_black[i] == 15);
    if (!success) {
      System.out.println("setPixelValue or numberOfBlackPixels ERROR");
      return;
    }

    // check invert
    img1.invert();
    for (int i = 0; i < 16; i++) {
      try {
        success = success && !(img1.getPixelValue(i, 0));
      } catch (PixelOutOfBoundException e) {
        System.out.println("Errorrrrrrrr");
      }
    }
    if (!success) {
      System.out.println("invert or getPixelValue ERROR");
      return;
    }

    // check Or
    try {
      img1.performOr(img2);
    } catch (BoundsMismatchException e) {
      System.out.println("Errorrrrrrrr");
    }
    for (int i = 0; i < 16; i++)
      for (int j = 0; j < 16; j++) {
        try {
          success = success && img1.getPixelValue(i, j);
        } catch (PixelOutOfBoundException e) {
          System.out.println("Errorrrrrrrr");
        }
      }
    if (!success) {
      System.out.println("performOr or getPixelValue ERROR");
      return;
    }

    // check And
    try {
      img1.performAnd(img2);
    } catch (BoundsMismatchException e) {
      System.out.println("Errorrrrrrrr");
    }
    for (int i = 0; i < 16; i++)
      for (int j = 0; j < 16; j++) {
        try {
          success = success && (img1.getPixelValue(i, j) == img2.getPixelValue(i, j));
        } catch (PixelOutOfBoundException e) {
          System.out.println("Errorrrrrrrr");
        }
      }
    if (!success) {
      System.out.println("performAnd or getPixelValue ERROR");
      return;
    }

    // check toStringUnCompressed
    String img_ans_uncomp =
        "16 16, 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1, 1 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1, 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1 1, 1 1 0 0 0 1 1 1 1 1 1 1 1 1 1 1, 1 1 0 0 1 1 1 1 1 1 1 1 1 1 0 0, 1 1 0 1 1 1 1 1 1 1 1 1 1 0 0 0, 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 1, 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1, 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1, 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 1, 1 1 1 1 1 1 1 0 0 0 1 1 1 1 1 1";
    success =
        success
            && (img1.toStringUnCompressed().equals(img_ans_uncomp))
            && (img2.toStringUnCompressed().equals(img_ans_uncomp));

    if (!success) {
      System.out.println("toStringUnCompressed ERROR");
      return;
    } else System.out.println("ALL TESTS SUCCESSFUL! YAYY!");
  }
}
