import java.io.IOException;

/**
 * This class is for testing IOException when the view is given an InvalidAppendable.
 * All the methods in this class should result in an IOException.
 */
public class InvalidAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Testing IOException for Appendable");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Testing IOException for Appendable");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Testing IOException for Appendable");
  }
}
