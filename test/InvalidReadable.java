import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class is for testing IOException when the controller is given an InvalidReadable.
 * All the methods in this class should result in an IOException.
 */
public class InvalidReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Testing IOException for Readable");
  }
}
