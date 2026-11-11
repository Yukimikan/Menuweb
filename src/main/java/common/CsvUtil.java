package common;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.GlobalConst;
import model.MenuCSV;

/**
 * ファイル操作用サービス.
 * */
public final class CsvUtil {

  /*
   * 参考サイト
   * https://style.potepan.com/articles/18230.html
   * */
  //Copilotで修正済み。

  //絶対パスで指定
  public static final String CSV_FOLDER_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
  public static final int MAX_COUNT = 100;
  public static final long MAX_BYTES = 1_000_000; // 1MB
  /* callしない */
  //コンストラクタ
  private CsvUtil() {
  }

  /**
   * 読取CSV.
   *
   * @param infilename
   * @return retList
   * @exception FileNotFoundException
   */
  public static List<MenuCSV> read(String infilename)
            throws IOException {
    String infilePath = CSV_FOLDER_PATH + infilename;
    List<MenuCSV> retList = new ArrayList<MenuCSV>();

    try {
      Path filePath = Paths.get(infilePath);

      // 1. byte 数チェック（品質チェック）
      long fileSize = Files.size(filePath);
      if (fileSize > MAX_BYTES) {
        System.out.println(GlobalConst.MSG_W_FILE_MAXCOUNT_OVER);
        // ただし処理は続行
      }
      // 2. 全行読み込み
      Iterator<String> it = Files.readAllLines(filePath, StandardCharsets.UTF_8)
                                 .iterator();

      // 3. ヘッダー一致チェック


      // 3. ヘッダー読み飛ばし
      if (it.hasNext()) {
        it.next(); // iterator の先頭を捨てる
        // 今はヘッダー内容を使わないので捨てるだけ
      }
      // 4. 本体行の処理
      while (it.hasNext()) {
        String currentContent = it.next();
        String[] arrayColumnData = currentContent.split(",", -1); /// 空欄保持
        try {
          retList.add(new MenuCSV(arrayColumnData));  // ★ 読み込み用コンストラクタ
        } catch (IllegalArgumentException e) {
          System.out.println("警告：不正な行 → " + currentContent);
          System.out.println("理由：" + e.getMessage());
          // 続行
        }
      }
    } catch (NoSuchFileException e) {
      System.out.println(GlobalConst.MSG_W_FILE_NOT_FOUND);
      System.out.println("infile_path：" + infilePath);
      throw new IOException("CSVファイルが存在しません: " + infilePath, e);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IOException("CSV読み込みに失敗しました: " + e.getMessage(), e);
    }
    return retList;
  }

  /**
   * ★ 今は使わないが、将来の編集機能用に残す選択肢
   * CSV 全書き込み（行削除・並び替えなどで使う）
   */
  public static void write(String csvName, List<MenuCSV> list) throws IOException {

    try (BufferedWriter bw = new BufferedWriter(
          new FileWriter(CSV_FOLDER_PATH + csvName, false))) { // ★ false = 上書き

      // ヘッダー書き込み
      bw.write(MenuCSV.CSV_HEADER);
      bw.newLine();

      // 全行書き込み
      for (MenuCSV rec : list) {
        bw.write(rec.returnJoinedString());
        bw.newLine();
      }

    } catch (Exception e) {
      throw new IOException("CSV書き込みに失敗しました: " + e.getMessage(), e);
    }
  }

}
