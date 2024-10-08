package runner;

import formatter.FormatterIterator;
import formatter.rules.Rule;
import formatter.rulesmap.RulesMap;
import formatter.rulesmap.RulesMapFactory;
import formatter.rulesmap.RulesReader;
import iterator.FileReaderIterator;
import iterator.TokenIterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import parser.iterator.AstIterator;
import providers.outputprovider.OutputProvider;

public class FormatterRunner {

  public void format(
      InputStream inputStream,
      InputStream configRules,
      OutputProvider outputProvider,
      String version)
      throws IOException {
    FileReaderIterator fileIterator = new FileReaderIterator(inputStream);
    TokenIterator tokens = new TokenIterator(fileIterator, version);
    AstIterator nodes = new AstIterator(tokens, version);
    outputProvider.write(new FormatterIterator(nodes, getRules(configRules, version)));
  }

  private List<Rule> getRules(InputStream configFile, String version) throws IOException {
    RulesMap rulesMap = new RulesMapFactory().createRuleMap(version);
    RulesReader rulesReader = new RulesReader();
    List<Rule> rules = rulesReader.loadRulesFromJson(configFile, rulesMap);
    return rules;
  }
}
