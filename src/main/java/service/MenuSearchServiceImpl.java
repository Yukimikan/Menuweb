package service;

import java.util.Collections;
import java.util.List;

import common.ConditionTotal;
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

    ConditionTotal cond = ConditionTotal.fromCode(form.getTotalCondition());

    int total = this.convertTotal(form.getTotal());

    MenuEntity entity = new MenuEntity(
            GlobalConst.NUM_CSV検索, // 検索
            form.getType(),
            form.getRestaurantName(),
            form.getSingleMenuFlg(),
            form.getMenu(),
            Integer.parseInt(form.getPrice()),
            Integer.parseInt(form.getTax()),
            this.convertTotal(form.getTotal()),
            userName);

    try {

      List<MenuEntity> tmpList = menuDao.select(entity, userName);
      List<MenuEntity> menuList = tmpList.stream()
          .filter(ret ->
                cond.match(ret.getTotal(), total)
                && isMatch(form.getType(), ret.getType())
                && isMatch(form.getRestaurantName(), ret.getRestaurantName())
                )
              .toList();
      if (tmpList.size() == 0) {
        // 結果0件
        result.setSuccess(true);
        result.setMessage("0件");
        result.setMenuList(Collections.emptyList());
        System.out.print(result.getMessage());
      } else {
        result.setSuccess(true);
        result.setMenuList(menuList);
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

  private boolean isMatch(String condition, String value) {
    return condition == null
            || condition.isEmpty()
            || condition.equals(value);
  }

  /**
   * 合計金額を返す.
   *
   * @return total 合計金額 or 0
   */
  public int convertTotal(String total) {
    // ★ int で返す(NULLチェック後)
    if (total == null || total.isBlank()) {
      return 0;
    } else {
      return Integer.parseInt(total);
    }

  }
}
