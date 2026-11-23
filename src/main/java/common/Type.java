package common;

import java.util.Arrays;

/**
 * Type
 * 項目「種類」
 */
public enum Type {

  TEISYOKU("定食屋"),
  FASTFOOD("ファストフード"),
  WAFU("和風料理"),
  YOFU("洋風料理"),
  CHUKA("中華料理"),
  KISSA("喫茶店"),
  BUFFET("バイキング"),
  DESSERT("デザート");

  private final String value;

  Type(String value) {
      this.value = value;
  }

  public String getValue() {
      return value;
  }

  public static boolean isValid(String value) {
    //enum に存在しない場合エラー
    return Arrays.stream(values())
          .anyMatch(type -> type.getValue().equals(value));
  }

}
