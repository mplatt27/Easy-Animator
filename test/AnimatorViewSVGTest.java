
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimatorViewSVG;
import cs5004.model.AnimatorModelImpl;

import static org.junit.Assert.assertEquals;


/**
 * a JUnit test for the AnimatorViewSVG class.
 */
public class AnimatorViewSVGTest {
  private AnimatorViewSVG test1;

  @Test
  public void svgOutputBuildings() throws IOException {
    FileReader inputFile = new FileReader("buildings.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "out.svg");
    BufferedReader br = new BufferedReader(new FileReader("test.txt"));
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
  public void svgOutputBigBangBigCrunch() throws IOException {
    FileReader inputFile = new FileReader("big-bang-big-crunch.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "myfile.svg");
    BufferedReader br = new BufferedReader(new FileReader("bigbangtest.txt"));
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
  public void svgOutputHanoi() throws IOException {
    FileReader inputFile = new FileReader("hanoi.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "myfile2.svg");
    BufferedReader br = new BufferedReader(new FileReader("hanoitest.txt"));
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
  public void svgOutputToh3() throws IOException {
    FileReader inputFile = new FileReader("toh-3.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "testtoh3.svg");
    BufferedReader br = new BufferedReader(new FileReader("myfile3.txt"));
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
  public void svgOutputToh5() throws IOException {
    FileReader inputFile = new FileReader("toh-5.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "testtoh5.svg");
    BufferedReader br = new BufferedReader(new FileReader("myfile4.txt"));
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
  public void svgOutputToh8() throws IOException {
    FileReader inputFile = new FileReader("toh-8.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "testtoh8.svg");
    BufferedReader br = new BufferedReader(new FileReader("myfile8.txt"));
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
  public void svgOutputToh12() throws IOException {
    FileReader inputFile = new FileReader("toh-12.txt");
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader.parseFile(inputFile, builder);
    test1 = new AnimatorViewSVG(model, 4, "testtoh12.svg");
    BufferedReader br = new BufferedReader(new FileReader("myfile12.txt"));
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