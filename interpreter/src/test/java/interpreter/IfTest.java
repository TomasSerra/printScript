package interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import AST.nodes.ASTNode;
import AST.nodes.BlockNode;
import AST.nodes.IfNode;
import AST.nodes.LiteralNode;
import AST.nodes.PrintNode;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.printprovider.TestPrintProvider;
import token.TokenType;
import token.ValueToken;

public class IfTest {

  @Test
  public void simpleIfTest() {

    // if (true) {
    //   print("if statement working correctly");
    // }

    TestPrintProvider printProvider = new TestPrintProvider();
    List<ASTNode> astNodes =
        List.of(
            new IfNode(
                new LiteralNode(new ValueToken(TokenType.BOOLEAN, "true", 8, 1)),
                new BlockNode(
                    List.of(
                        new PrintNode(
                            new LiteralNode(
                                new ValueToken(
                                    TokenType.STRING, "if statement working correctly", 8, 1)),
                            1,
                            1))),
                new BlockNode(List.of()),
                1,
                1));

    Interpreter interpreter = new Interpreter(printProvider);
    interpreter.interpret(astNodes.iterator());
    Iterator<String> messages = printProvider.getMessages();
    assertEquals("if statement working correctly\n", messages.next());
  }
}
