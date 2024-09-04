package parser.syntax;

import parser.syntax.provider.ProviderType;
import parser.syntax.provider.SyntaxParserProvider;
import token.Token;
import token.TokenType;

import java.util.EnumSet;

public class SyntaxParserFactory {
  private final EnumSet<? extends ProviderType> providerTypes;

  public SyntaxParserFactory(EnumSet<ProviderType> providerTypes) {
    this.providerTypes = providerTypes;
  }

  public SyntaxParser getSyntaxParser(TokenStream tokens) {
    if (!tokens.hasNext()) {
      throw new IllegalArgumentException("Empty token list");
    }

    while (tokens.hasNext()) {
      Token firstToken = tokens.getCurrentToken();
      if (firstToken.getType() != TokenType.LINE_BREAK && firstToken.getType() != TokenType.WHITESPACE) {
        break;
      }
      else {
        tokens.advance();
      }
    }

    for (ProviderType type : providerTypes) {
      SyntaxParserProvider provider = type.getProvider();
      if (provider.supports(tokens)) {
        return provider.createParser();
      }
    }

    throw new IllegalArgumentException("No suitable parser found");
  }
}