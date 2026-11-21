package model;

/**
 * GlobalConst.
 *
 */
public class GlobalConst {

  //メッセージ
  // public static final String MSG_W_FILENAME_ERROR = "ファイル名がありません";
  public static final String MSG_W_FILE_NOT_FOUND = "ファイルが見つかりません";
  public static final String MSG_W_FILE_MAXCOUNT_OVER = "最大件数超過、最大件数まで読み込みます";
  public static final String MSG_W_INDATA_NOT_NUMERIC = "入力データに数値型以外が含まれます";
  public static final String MSG_W_INDATA_ISNULL = "入力データがNULLです";
  public static final String CsvName = "menu.csv";
  public static final String CSV_FOLDER_PATH =
      "C:\\pleiades\\workspace\\Menuweb\\resource\\";  //絶対パスで指定
  public static final String EXTENSION_CSV = ".csv";
  public static final int NUM_CSV検索 = 99;
  public static final int NUM_CSV新規登録 = 99;

  //URL
  /*
   * Index
   */
  public static final String MenuIndexUrl = "./index.html";
  // JSP
  public static final String WebDir = "./WEB-INF/jsp/";
  public static final String Login = WebDir + "login.jsp";
  public static final String CsvSearch = WebDir + "csv_search.jsp";
  public static final String CsvWrite = WebDir + "csv_write.jsp";
  public static final String CsvConfirm = WebDir + "csv_confirm.jsp";
  public static final String CsvWriteFinish = WebDir + "csv_write_finish.jsp";
  public static final String MenuSearch = WebDir + "menu_search.jsp";
  public static final String MenuRegist = WebDir + "menu_regist.jsp";
  public static final String MenuConfirm = WebDir + "menu_confirm.jsp";
  public static final String MenuRegistFinish = WebDir + "menu_regist_finish.jsp";
  public static final String RestaurantSearch = WebDir + "restaurant_search.jsp";
  public static final String RestaurantRegist = WebDir + "restaurant_regist.jsp";
  public static final String RestaurantConfirm = WebDir + "restaurant_confirm.jsp";
  public static final String RestaurantRegistFinish = WebDir + "restaurant_regist_finish.jsp";
  public static final String Error = WebDir + "error.jsp";

}
