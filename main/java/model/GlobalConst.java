package model;

/*
 * GlobalConst
 *
 */
public class GlobalConst {

  //メッセージ
  /*
   * CsvUtilServiceImpl
   */
  public static final String MSG_W_FILENAME_ERROR = "ファイル名がありません";
  public static final String MSG_W_FILE_NOT_FOUND = "ファイルが見つかりません";
  public static final String MSG_W_FILE_MAXCOUNT_OVER = "最大件数超過、最大件数まで読み込みます";
  public static final String MSG_W_INDATA_NOT_NUMERIC = "入力データに数値型以外が含まれます";
  public static final String MSG_W_INDATA_ISNULL = "入力データがNULLです";
  /*
   * CsvInputServlet
   */
  // フロント側でチェック
  //  public static final String MSG_W_DATE_ERROR = "日付を指定して下さい";
  //  public static final String MSG_W_CSVNAME_ERROR = "CSV名を指定して下さい";

  //URL
  /*
   * SearchMenuServlet
   */
  public static final String JspMenuResultUrl = "./jsp/menu_result.jsp";
  /*
   * CsvInputServlet
   */
  public static final String JspInputUrl = "./jsp/menu_csv_input.jsp";
  public static final String JspResultUrl = "./jsp/menu_csv_result.jsp";

}
