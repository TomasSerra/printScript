package formatter;

import fileReader.FileReaderIterator;
import fileWriter.OutputProvider;
import formatter.rules.Rule;
import formatter.rulesMap.RulesMap;
import formatter.rulesMap.RulesMapFactory;
import formatter.rulesMap.RulesReader;
import iterator.TokenIterator;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class FormatterRunner {

  public void format(InputStream inputStream, InputStream configRules, OutputProvider outputProvider, String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    Iterator<Token> tokens = new TokenIterator(fileIterator, version);
    outputProvider.write(new FormatterIterator(tokens, getRules(configRules, version)));
  }

  private List<Rule> getRules(InputStream configFile, String version) throws IOException {
    RulesMap rulesMap = new RulesMapFactory().createRuleMap(version);
    RulesReader rulesReader = new RulesReader();
    List<Rule> rules = rulesReader.loadRulesFromJson(configFile, rulesMap);
    return rules;
  }
}
