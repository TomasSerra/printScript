package ast;

import ast.nodes.AssignationNode;
import ast.nodes.BlockNode;
import ast.nodes.DeclarationNode;
import ast.nodes.EmptyNode;
import ast.nodes.IfNode;
import ast.nodes.LiteralNode;
import ast.nodes.OperatorNode;
import ast.nodes.PrintNode;
import ast.nodes.ReadEnvNode;
import ast.nodes.ReadInputNode;
import ast.nodes.ReassignmentNode;
import ast.nodes.VariableNode;
import ast.tokens.AstTokenType;

public class ExpressionTypeVisitor implements AstVisitor<AstTokenType> {

  @Override
  public AstTokenType visit(DeclarationNode node) {
    return node.getTypeToken().getType();
  }

  @Override
  public AstTokenType visit(LiteralNode node) {
    return node.getType();
  }

  @Override
  public AstTokenType visit(PrintNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public AstTokenType visit(AssignationNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public AstTokenType visit(OperatorNode node) {
    if (node.getLeftNode().accept(this) == AstTokenType.BOOLEAN
        || node.getRightNode().accept(this) == AstTokenType.BOOLEAN) {
      return AstTokenType.BOOLEAN;
    }
    if (node.getLeftNode().accept(this) == AstTokenType.NUMBER
        && node.getRightNode().accept(this) == AstTokenType.NUMBER) {
      return AstTokenType.NUMBER;
    } else if (node.getLeftNode().accept(this) == AstTokenType.IDENTIFIER
        || node.getRightNode().accept(this) == AstTokenType.IDENTIFIER) {
      return AstTokenType.IDENTIFIER;
    }
    return AstTokenType.STRING;
  }

  @Override
  public AstTokenType visit(VariableNode node) {
    return AstTokenType.IDENTIFIER;
  }

  @Override
  public AstTokenType visit(ReassignmentNode node) {
    return node.getExpression().accept(this);
  }

  @Override
  public AstTokenType visit(EmptyNode node) {
    return AstTokenType.EMPTY;
  }

  @Override
  public AstTokenType visit(ReadInputNode node) {
    return AstTokenType.READ_INPUT;
  }

  @Override
  public AstTokenType visit(ReadEnvNode readEnvNode) {
    return AstTokenType.STRING;
  }

  @Override
  public AstTokenType visit(IfNode ifNode) {
    return null;
  }

  @Override
  public AstTokenType visit(BlockNode blockNode) {
    return null;
  }
}
