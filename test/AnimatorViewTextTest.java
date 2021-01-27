
import org.junit.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimatorView;
import cs5004.animator.view.AnimatorViewText;
import cs5004.model.AnimatorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the AnimatorViewText class.
 */
public class AnimatorViewTextTest {
  private AnimatorView test1;

  @Test
  public void testTextBuildings() throws IOException {
    FileReader inputFile = new FileReader("buildings.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("builingsOut.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextBigBang() throws IOException {
    FileReader inputFile = new FileReader("big-bang-big-crunch.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("bigBangOut.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextHanoi() throws IOException {
    FileReader inputFile = new FileReader("hanoi.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("hanoiOut.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextToh3() throws IOException {
    FileReader inputFile = new FileReader("toh-3.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("toh3Out.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextToh5() throws IOException {
    FileReader inputFile = new FileReader("toh-5.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("toh5Out.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextToh8() throws IOException {
    FileReader inputFile = new FileReader("toh-8.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("toh8Out.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }

  @Test
  public void testTextToh12() throws IOException {
    FileReader inputFile = new FileReader("toh-12.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewText(model, 0, "");
    BufferedReader br = new BufferedReader(new FileReader("toh12Out.txt"));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line).append("\n");
      line = br.readLine();
    }
    String expected = sb.toString();
    assertEquals(expected.trim(), test1.stringOutput());
  }
}

