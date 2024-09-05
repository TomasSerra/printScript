package runner;

import AST.nodes.ASTNode;
import fileReader.FileReaderIterator;
import interpreter.Interpreter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import providers.printProvider.PrintProvider;
import iterator.TokenIterator;
import parser.iterator.ASTIterator;
import token.Token;

public class Runner {
  //When you use printProvider
  public void run(String filePath, String version, PrintProvider printProvider) throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(new File(filePath));
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    Iterator<ASTNode> ASTNodes = new ASTIterator(tokens, version);
    new Interpreter(printProvider).interpret(ASTNodes);
  }
}
