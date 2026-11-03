package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * ファイル読込用サービス.
 * */
public class CsvFileListServiceImpl implements CsvFileListService {

  List<String> csvFiles = new ArrayList<>();
  //絶対パスで指定
  public static final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";


  /**
   * execute.
   */
  public List<String> execute() throws IOException {

    try (Stream<Path> s = Files.list(Paths.get(CSV_PATH))) {
      return s
      .filter(Files::isRegularFile)
      .filter(p -> p.toString().endsWith(".csv"))
      .map(p -> p.getFileName().toString())
      .toList();
    }
  }

}
