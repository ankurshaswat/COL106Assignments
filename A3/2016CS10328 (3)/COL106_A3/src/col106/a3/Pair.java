package col106.a3;

public class Pair<Key extends Comparable<Key>, Value> {
  private Key k;
  private Value val;

  public Pair(Key key, Value val) {
    this.k = key;
    this.val = val;
  }

  public Value getVal() {
    return val;
  }

  public Key getKey() {
    return k;
  }

  public String toString() {
    return k + "=" + val;
  }
}
