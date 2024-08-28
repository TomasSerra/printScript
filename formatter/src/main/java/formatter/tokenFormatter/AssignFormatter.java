package formatter.tokenFormatter;

import formatter.rules.AssignRule;
import formatter.rules.FormatterTokenVisitor;
import formatter.rules.Rule;
import token.Token;

import java.util.List;

public class AssignFormatter implements TokenFormatter {
    @Override
    public List<Token> formatToken(List<Token> tokens, List<Rule> rules) {
      if (rules.isEmpty()) return tokens;
      FormatterTokenVisitor visitor = new FormatterTokenVisitor();
      List<Token> result = List.copyOf(tokens);
      for (Rule rule : rules) {
        if (rule instanceof AssignRule) {
          result = rule.accept(visitor, result);
        }
      }
      return result;
    }
}
