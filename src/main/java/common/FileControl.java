package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileControl {

  /*
   *
   * getFileNames.
   * @param folderPath
   * @param extension // 拡張子
   */
  public static List<String> getFileNames(String folderPath,String extension) throws IOException {
	// ファイルパスからファイル名を取得
	// (csvファイル)

    try (Stream<Path> s = Files.list(Paths.get(folderPath))) {
      return s
      .filter(Files::isRegularFile)
      .filter(p -> p.toString().endsWith(extension))
      .map(p -> p.getFileName().toString())
      .toList();
    }
  }
}
