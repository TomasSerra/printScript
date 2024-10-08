package lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import token.Token;
import token.TokenType;

public class LexerTokenTypeTest {

  @Test
  public void tokenizeOneOperationTest() throws IOException {
    // 5 + 3;
    String filePath = "src/test/resources/operation.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.0");

    assertEquals(tokenIterator.current().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeMultipleOperationTest() throws IOException {
    // 5 + 3;
    // 10 / 2;
    String filePath = "src/test/resources/multiple_operations.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.0");

    assertEquals(tokenIterator.current().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertFalse(tokenIterator.hasNext());
  }

  @Test
  public void tokenizeCompleteCode() throws IOException {
    // let a: string = "Hello ";
    // let b: string = "World!";
    // println("Result: "+a+b);

    String filePath = "src/test/resources/complete.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.0");
    assertEquals(tokenIterator.current().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.PRINT_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.OPERATOR);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void tokenizeReassignmentCompleteCode() throws IOException {
    // let name: string = "John";
    // name = "Doe";

    String filePath = "src/test/resources/reassignment.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.0");
    assertEquals(tokenIterator.current().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
    assertEquals(tokenIterator.next().getType(), TokenType.LINE_BREAK);

    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void tokenizeBooleanAssignmentCompleteCode() throws IOException {
    // let a: boolean = true;
    String filePath = "src/test/resources/boolean_declaration.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");
    assertEquals(tokenIterator.current().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.BOOLEAN_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.BOOLEAN);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void tokenizeConstAssignment() throws IOException {
    // const a: number = 5;
    String filePath = "src/test/resources/const_declaration.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");
    assertEquals(tokenIterator.current().getType(), TokenType.CONST_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.NUMBER);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void tokenizeReadInput() throws IOException {
    // readInput("Test");
    String filePath = "src/test/resources/readInput.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    assertEquals(tokenIterator.current().getType(), TokenType.READ_INPUT);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void consecutiveLineBreakTest() throws IOException {
    // GIVEN
    // 1+1;
    //
    //
    // 2+2;

    String filePath = "src/test/resources/consecutive_linebreaks.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    jumpLines(tokenIterator, 3);
    jumpLines(tokenIterator, 1);

    Token token = tokenIterator.current();
    assertEquals(token.getType(), TokenType.LINE_BREAK);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.LINE_BREAK);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.LINE_BREAK);

    token = tokenIterator.next();
    assertEquals(token.getType(), TokenType.NUMBER);
  }

  @Test
  public void tokenizeReadEnv() throws IOException {
    // readEnv("TEST");
    String filePath = "src/test/resources/readEnv.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");

    assertEquals(tokenIterator.current().getType(), TokenType.READ_ENV);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  @Test
  public void tokenizeAssignationReadEnv() throws IOException {
    // let a: string = readEnv("TEST");
    String filePath = "src/test/resources/readEnvAssignation.txt";
    FileReaderIterator fileReaderIterator = new FileReaderIterator(new FileInputStream(filePath));
    TokenIterator tokenIterator = new TokenIterator(fileReaderIterator, "1.1");
    assertEquals(tokenIterator.current().getType(), TokenType.LET_KEYWORD);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.IDENTIFIER);
    assertEquals(tokenIterator.next().getType(), TokenType.COLON);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING_TYPE);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.ASSIGN);
    assertEquals(tokenIterator.next().getType(), TokenType.WHITESPACE);
    assertEquals(tokenIterator.next().getType(), TokenType.READ_ENV);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_OPEN);
    assertEquals(tokenIterator.next().getType(), TokenType.STRING);
    assertEquals(tokenIterator.next().getType(), TokenType.PARENTHESIS_CLOSE);
    assertEquals(tokenIterator.next().getType(), TokenType.SEMICOLON);
  }

  private void jumpLines(TokenIterator tokenIterator, int n) {
    for (int i = 0; i < n; i++) {
      tokenIterator.next();
    }
  }
}
