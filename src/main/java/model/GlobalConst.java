package model;

/*
 * GlobalConst
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
  public static final String CSV_FOLDER_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";  //絶対パスで指定
  public static final String EXTENSION_CSV = ".csv";
  public static final int NUM_CSV新規登録 = 99;

  //URL
  /*
   * Index
   */
  public static final String MenuIndexUrl = "./index.html";
  // JSP
  public static final String JspLoginUrl = "./jsp/login.jsp";
  public static final String JspInputUrl = "./jsp/menu_csv_input.jsp";
  public static final String JspResultUrl = "./jsp/menu_csv_result.jsp";
  public static final String JspConfirmUrl = "./jsp/menu_csv_confirm.jsp";
  public static final String JspRegistUrl = "./jsp/menu_csv_regist.jsp";
  public static final String JspMenuRegistUrl = "./jsp/menu_regist.jsp";
  public static final String JspMenuResultUrl = "./jsp/menu_result.jsp";
  public static final String JspErrorUrl = "./jsp/error.jsp";

}
