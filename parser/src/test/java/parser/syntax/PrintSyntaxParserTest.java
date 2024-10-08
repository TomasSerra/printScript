package parser.syntax;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ast.nodes.AstNode;
import ast.nodes.PrintNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.iterator.AstIterator;
import parser.iterator.TestTokenIterator;
import token.Token;
import token.TokenType;
import token.ValueToken;

public class PrintSyntaxParserTest {

  @Test
  public void testSyntaxParsePrint() {
    // println("Hello");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.PARENTHESIS_OPEN, "(", 8, 1),
            new ValueToken(TokenType.STRING, "Hello", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);

    Iterator<AstNode> nodes = new AstIterator(tokenIterator, "1.0");
    AstNode firstAst = nodes.next();
    assertInstanceOf(PrintNode.class, firstAst);
  }

  @Test
  public void testSyntaxParsePrintError() {
    // println"Hello");
    List<Token> tokens =
        List.of(
            new ValueToken(TokenType.PRINT_KEYWORD, "println", 1, 1),
            new ValueToken(TokenType.STRING, "Hello", 9, 1),
            new ValueToken(TokenType.PARENTHESIS_CLOSE, ")", 13, 1),
            new ValueToken(TokenType.SEMICOLON, ";", 14, 1));

    // WHEN
    TestTokenIterator tokenIterator = new TestTokenIterator(tokens);
    Parser parser = new Parser("1.0");
    assertThrows(RuntimeException.class, () -> parser.parse(tokenIterator));
  }
}
