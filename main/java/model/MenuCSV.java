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

  /*
   * constructor
   * with param
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
    String flg = arrayColumnData[3];
    if ("○".equals(flg)) {
      // 単品 → OK
    } else {
      // セット品 → 空欄であるべき
      if (!isEmpty(flg)) {
        throw new IllegalArgumentException("単品フラグが不正です（セット品は空欄）: " + flg);
      }
    }

    // 5. 正常ならフィールドにセット
    this.no = arrayColumnData[0];
    this.type = arrayColumnData[1];
    this.restaurantName = arrayColumnData[2];
    this.singlemenuFlg = arrayColumnData[3];
    this.menu = arrayColumnData[4];
    this.price = Integer.parseInt(arrayColumnData[5]);
    this.tax = arrayColumnData[6];
    this.total = Integer.parseInt(arrayColumnData[7]);
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
