package lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;

public class LexerLineAndColumnTest {

  @Test
  public void singleLineTest() throws IOException {
    // GIVEN
    // 5 + 3;
    // 10 / 2
    String filePath = "src/test/resources/multiple_operations.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.NUMBER);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 1);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.WHITESPACE);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 2);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.OPERATOR);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 3);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.WHITESPACE);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 4);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.NUMBER);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 5);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.SEMICOLON);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 6);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.LINE_BREAK);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 7);
  }

  @Test
  public void multiLineTest() throws IOException {
    // GIVEN
    // 5 + 3;
    // 10 / 2;

    String filePath = "src/test/resources/multiple_operations.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.NUMBER);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 1);

    jumpLines(tokenIterator, 5);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.LINE_BREAK);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 7);
  }

  @Test
  public void readInputTest() throws IOException {
    // GIVEN
    // readInput("Test");

    String filePath = "src/test/resources/readInput.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.READ_INPUT);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 1);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 10);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.STRING);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 11);
    assertEquals(token.getValue(), "Test");

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 17);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.SEMICOLON);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 18);
  }

  @Test
  public void readInputWithExpressionTest() throws IOException {
    // GIVEN
    // readInput("Test"+"Test2");

    String filePath = "src/test/resources/readInput_with_expression.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.READ_INPUT);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 1);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 10);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.STRING);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 11);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.OPERATOR);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 17);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.STRING);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 18);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 25);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.SEMICOLON);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 26);
  }

  @Test
  public void readInputAssignationWithExpressionTest() throws IOException {
    // GIVEN
    // let a: string = readInput("Test"+"Test");

    String filePath = "src/test/resources/readInputAssignationWithExpressionTest.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    jumpLines(tokenIterator, 9);

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.READ_INPUT);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 17);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 26);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.STRING);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 27);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.OPERATOR);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 33);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.STRING);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 34);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 40);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.SEMICOLON);
    assertEquals(token.getLine(), 1);
    assertEquals(token.getColumn(), 41);
  }

  private void jumpLines(TokenIterator tokenIterator, int n) {
    for (int i = 0; i < n; i++) {
      tokenIterator.next();
    }
  }
}
