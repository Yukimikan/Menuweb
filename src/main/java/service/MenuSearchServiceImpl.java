package service;

import java.util.Collections;
import java.util.List;

import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuSearchForm;
import model.GlobalConst;
import model.MenuEntity;

/**
 * ファイル読込用サービス.
 * */
public class MenuSearchServiceImpl implements MenuSearchService {

  private MenuDao menuDao;

  public MenuSearchServiceImpl() {
    menuDao = new MenuDaoImpl();
  }


  /**
   * execute.
   */
  public MenuSearchServiceResult execute(MenuSearchForm form, String userName) {

	MenuSearchServiceResult result = new MenuSearchServiceResult();
    MenuEntity entity = new MenuEntity(
            GlobalConst.NUM_CSV検索, // 検索
            form.getType(),
            form.getRestaurantName(),
            form.getSingleMenuFlg(),
            form.getMenu(),
            Integer.parseInt(form.getPrice()),
            Integer.parseInt(form.getTax()),
            form.getTotal(),
            userName);

    try {
      List<MenuEntity> retList = menuDao.select(entity, userName);

      if (retList.size() == 0) {
        // 結果0件
        result.setSuccess(true);
        result.setMessage("0件");
        result.setRetList(Collections.emptyList());
        System.out.print(result.getMessage());
      } else {
        result.setSuccess(true);
        result.setRetList(retList);
      }
      // result
      if (result.isSuccess()) {
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // return
    return result;
  }

}
