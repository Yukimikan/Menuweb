package model;

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
  private String price;
  private String tax;
  private String total;

  /*
   * constructor
   */
  public MenuCSV() {}

  /**
   * 全列指定.
   * arrayColumnData 分割済みのデータ.
   *
   * @param  arrayColumnData
   * @throw ArrayIndexOutOfBoundsException
   */
  public void setAllColumns(String[] arrayColumnData) {

    try {
      this.setNo(arrayColumnData[0]);
      this.setType(arrayColumnData[1]);
      this.setRestaurantName(arrayColumnData[2]);
      this.setSinglemenuFlg(arrayColumnData[3]);
      this.setMenu(arrayColumnData[4]);
      this.setPrice(arrayColumnData[5]);
      this.setTax(arrayColumnData[6]);
      this.setTotal(arrayColumnData[7]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("添字オーバー");
    }
  }

  /**
   * 関数.
   */
  public String returnJoinedString() {
    String[] arrayColumnData = new String[8];
    arrayColumnData[0] = no;
    arrayColumnData[1] = type;
    arrayColumnData[2] = restaurantName;
    arrayColumnData[3] = singlemenuFlg;
    arrayColumnData[4] = menu;
    arrayColumnData[5] = price;
    arrayColumnData[6] = tax;
    arrayColumnData[7] = total;
    //Java8から
    String sj = String.join(",", arrayColumnData);
    return sj;
  }

}
