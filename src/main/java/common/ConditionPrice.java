package common;

/**
 * ConditionTotal
 * 項目「合計」
 * 条件をまとめたENUMクラスなど.
 * ---
 * 以上
 * 以下
 * 等しい
 * 未満
 *
 */
public enum ConditionPrice {

   BLANK {
   @Override
      public boolean match(int price, int total) {
      return price >= total;
    }
   },
   以上 {
    @Override
      public boolean match(int price, int total) {
      return price >= total;
    }
   },
   以下 {
    @Override
      public boolean match(int price, int total) {
      return price <= total;
    }
   },
   等しい {
    @Override
      public boolean match(int price, int total) {
      return price == total;
    }
   },
   未満 {
    @Override
      public boolean match(int price, int total) {
      return price < total;
    }
   };

  /**
   * コード値変換.
   *
   * @param code 合計（コード値）
   * @return ConditionTotal
   */
  public static ConditionPrice fromCode(String code) {
    return switch (code) {
      case "" -> BLANK;
      case "1" -> 以上;
      case "2" -> 以下;
      case "3" -> 等しい;
      case "4" -> 未満;
      default -> throw new IllegalArgumentException("不正な条件コード");
    };
  }

  public abstract boolean match(int price, int total);
}