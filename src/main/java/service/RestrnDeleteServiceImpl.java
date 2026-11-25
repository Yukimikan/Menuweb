package service;

import dao.RestrnDao;
import dao.RestrnDaoImpl;
import form.RestrnSearchForm;

/**
 * ファイル書込用サービス.
 * */
public class RestrnDeleteServiceImpl implements RestrnDeleteService {

  private RestrnDao restrnDao;

  public RestrnDeleteServiceImpl() {
    restrnDao = new RestrnDaoImpl();
  }

  /**
   * execute.
   */
  @Override
  public RestrnDeleteServiceResult execute(RestrnSearchForm form) {

    // 初期化
    RestrnDeleteServiceResult result = new RestrnDeleteServiceResult();
    int id = Integer.parseInt(form.getSelectedRow());

    // 1. 新規登録
    try {
      int flg = restrnDao.delete(id);
      result.setSuccess(true);
    } catch (Exception e) {
      // 削除失敗
      result.setMessage("削除エラー");
      System.out.println(e.getMessage());
    }
    return result;
  }

}
