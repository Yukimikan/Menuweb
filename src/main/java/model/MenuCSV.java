package model;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * MenuCSV.
 *
 */
@Data
@AllArgsConstructor
public class MenuCSV {

  public static final String CSV_HEADER = "No,種類,店名,単品,メニュー,価格,税,金額";
  public static final String CNST_セット_FLG0 = "0";
  public static final String CNST_単品_FLG1 = "1";
  public static final String CNST_単品マル = "○";

  //No,種類,店名,単品,メニュー,価格,税,金額
  //1,和食,松屋,○,牛丼,500,なし,500
  private String no;
  private String type;
  private String restaurantName;
  private String singlemenuFlg;
  private String menu;
  private int price;
  private String tax;
  private int total;

  /*
   * constructor
   */
  public MenuCSV() {}

  @Override
  public String toString() {
    return returnJoinedString();
  }

  /**
   * 書き込み時：0/1 → ○/空欄 に変換.
   */
  public static String convertSingleMenuFlg(String flg) {
    if (!flg.equals(CNST_セット_FLG0) && !flg.equals(CNST_単品_FLG1)) {
      throw new IllegalArgumentException("単品フラグが不正です: " + flg);
    }
    return flg.equals(CNST_単品_FLG1) ? CNST_単品マル : "";
  }

  /**
   * 読み込み時：○/空欄 → 1/0 に変換.
   */
  public static String normalizeSingleMenuFlg(String flg) {
    return CNST_単品マル.equals(flg) ? CNST_単品_FLG1 : CNST_セット_FLG0;
  }

  /**
   * constructor
   * with param.
   */
  public MenuCSV(String[] arrayColumnData) throws IllegalArgumentException {

    // 1. カラム数チェック
    if (arrayColumnData.length != 8) {
      throw new IllegalArgumentException("CSVカラム数が不正です: " + Arrays.toString(arrayColumnData));
    }

    // 2. 必須項目チェック
    if (isEmpty(arrayColumnData[0]) ||  // No
        isEmpty(arrayColumnData[1]) ||  // 種類
        isEmpty(arrayColumnData[2]) ||  // 店名
        isEmpty(arrayColumnData[4]) ||  // メニュー
        isEmpty(arrayColumnData[5]) ||  // 価格
        isEmpty(arrayColumnData[7])) {  // 金額
      throw new IllegalArgumentException("必須項目が未設定です: " + Arrays.toString(arrayColumnData));
    }

    // 3. 型チェック（価格・金額）
    if (!isNumeric(arrayColumnData[5])) {
      throw new IllegalArgumentException("価格が数値ではありません: " + arrayColumnData[5]);
    }
    if (!isNumeric(arrayColumnData[7])) {
      throw new IllegalArgumentException("金額が数値ではありません: " + arrayColumnData[7]);
    }
    // 4. ビジネスルールチェック（単品フラグ）
    // ★ 読み込み時は normalizeSingleMenuFlg を使う
    String normalizedFlg = normalizeSingleMenuFlg(arrayColumnData[3]);

    // ★ 内部表現に変換（○ / 空欄）
    this.singlemenuFlg = convertSingleMenuFlg(normalizedFlg);

    // 5. 正常ならフィールドにセット
    this.no = arrayColumnData[0];
    this.type = arrayColumnData[1];
    this.restaurantName = arrayColumnData[2];
    // this.singlemenuFlg = arrayColumnData[3];
    this.menu = arrayColumnData[4];
    this.price = Integer.parseInt(arrayColumnData[5]);
    this.tax = arrayColumnData[6];
    this.total = Integer.parseInt(arrayColumnData[7]);
  }

  /**
   * ★★★ 書き込み用コンストラクタ（Servlet → Model）
   * normalizeSingleMenuFlg() は絶対に使わない.
   */
  public static MenuCSV fromInput(String[] cols) {

    MenuCSV rec = new MenuCSV();
    rec.no = cols[0];
    rec.type = cols[1];
    rec.restaurantName = cols[2];

    // ★ 書き込み時は 0/1 → ○/空欄 に変換するだけ
    rec.singlemenuFlg = convertSingleMenuFlg(cols[3]);

    rec.menu = cols[4];
    rec.price = Integer.parseInt(cols[5]);
    rec.tax = cols[6];
    rec.total = Integer.parseInt(cols[7]);

    return rec;
  }

  private boolean isEmpty(String s) {
    return s == null || s.isEmpty();
  }

  private boolean isNumeric(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public String returnJoinedString() {
    return String.join(
       ",",
       no,
       type,
       restaurantName,
       singlemenuFlg,
       menu,
       String.valueOf(price),
       tax,
       String.valueOf(total)
       );
  }

}
