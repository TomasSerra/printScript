package parser.syntax;

import AST.nodes.ASTNode;
import AST.nodes.AssignationNode;
import AST.nodes.DeclarationNode;
import AST.nodes.EmptyNode;
import parser.syntax.result.SyntaxErrorResult;
import parser.syntax.result.SyntaxResult;
import parser.syntax.result.SyntaxSuccessResult;
import token.Token;
import token.TokenType;

public class AssignationSyntaxParser implements SyntaxParser {

  @Override
  public SyntaxResult syntaxParse(TokenStream tokens) {
    ASTNode result = parseAssignation(tokens);
    if (tokens.getErrorMessages().isEmpty()) {
      return new SyntaxSuccessResult(result);
    } else {
      return new SyntaxErrorResult(tokens.getErrorMessages());
    }
  }

  private ASTNode parseAssignation(TokenStream tokenStream) {
    ASTNode expressionNode;
    tokenStream.expect(TokenType.LET_KEYWORD, "Expected 'let'");
    DeclarationNode declarationNode = parseDeclaration(tokenStream);

    if (tokenStream.getCurrentToken().getType() == TokenType.SEMICOLON) {
      TokenType type = resolveEmptyType(declarationNode.getTypeToken().getType());

      expressionNode = new EmptyNode(type);
    }
    else {
      tokenStream.expect(TokenType.ASSIGN, "Expected '='");
      expressionNode = ExpressionFactory.createExpression(tokenStream);
      tokenStream.expect(TokenType.SEMICOLON, "Expected ';'");
    }

    return new AssignationNode(
        declarationNode, expressionNode, declarationNode.getLine(), declarationNode.getColumn());
  }

  private DeclarationNode parseDeclaration(TokenStream tokenStream) {
    Token nameToken = tokenStream.getCurrentToken();
    tokenStream.expect(TokenType.IDENTIFIER, "Expected identifier");
    tokenStream.expect(TokenType.COLON, "Expected ':'");
    Token typeToken = tokenStream.getCurrentToken();
    if (typeToken.getType() != TokenType.STRING_TYPE
        && typeToken.getType() != TokenType.NUMBER_TYPE) {
      tokenStream.getErrorMessages().add("Expected type to be 'string' or 'number'");
    } else {
      tokenStream.advance();
    }
    return new DeclarationNode(typeToken, nameToken, nameToken.getLine(), nameToken.getColumn());
  }

  private TokenType resolveEmptyType(TokenType type) {
    if (type == TokenType.STRING_TYPE) {
      return TokenType.STRING;
    } else if (type == TokenType.NUMBER_TYPE) {
      return TokenType.NUMBER;
    }
    return TokenType.EMPTY;
  }
}