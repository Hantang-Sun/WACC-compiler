package compiler;

public class Pair<T, R> {

  private final T fst;
  private final R snd;

  public Pair(T fst, R snd) {
    this.fst = fst;
    this.snd = snd;
  }

  public T getKey() {
    return fst;
  }

  public R getValue() {
    return snd;
  }
}
