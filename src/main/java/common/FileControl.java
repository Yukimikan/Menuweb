package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * FileControl
 * ファイル操作（共通化）.
 */
public class FileControl {

  /**
   * getFileNames
   * ファイルパスからファイル名を取得
   * (csvファイル).
   *
   * @param folderPath // フォルダパス
   * @param extension // 拡張子
   */
  public static List<String> getFileNames(String folderPath, String extension) throws IOException {

    try (Stream<Path> s = Files.list(Paths.get(folderPath))) {
      return s
      .filter(Files::isRegularFile)
      .filter(p -> p.toString().endsWith(extension))
      .map(p -> p.getFileName().toString())
      .toList();
    }
  }

  /**
   * ファイル操作（追加書き）.
   *
   * @param filePath //ファイルパス
   * @param rec      //追加行
   * @throws IOException // ファイル書き込みエラー
   */
  public static void append(String filePath, String rec) throws IOException {

    try (BufferedWriter bw = new BufferedWriter(
        new FileWriter(filePath, true))
      ) { // ★ true = 追記モード
      bw.write(rec);
      bw.newLine();
    } catch (Exception e) {
      throw new IOException("error: " + e.getMessage(), e);
    }
  }
}
