package factory;

import java.util.HashMap;
import java.util.Map;
import lexer.MapReader;
import lexer.MapReader1_0;
import lexer.MapReader1_1;

public class LexerMapFactory {
  Map<String, MapReader> mapReaders = new HashMap<>();

  public LexerMapFactory() {
    mapReaders.put("1.0", new MapReader1_0());
    mapReaders.put("1.1", new MapReader1_1());
  }

  public MapReader getLexerMap(String version) {
    if (mapReaders.containsKey(version)) {
      return mapReaders.get(version);
    }
    throw new IllegalArgumentException("Invalid version: " + version);
  }
}
