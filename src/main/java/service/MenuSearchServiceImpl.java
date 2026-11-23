package service;

import java.util.Collections;
import java.util.List;

import common.ConditionPrice;
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
  @Override
  public MenuSearchServiceResult execute(MenuSearchForm form) {

    MenuSearchServiceResult result = new MenuSearchServiceResult();

    ConditionPrice eatInPriceCond = ConditionPrice.fromCode(form.getEatInCondition());
    ConditionPrice takeoutPriceCond = ConditionPrice.fromCode(form.getTakeoutCondition());

    int eatInPrice = this.convertTotal(form.getEatInPrice());
    int takeoutPrice = this.convertTotal(form.getTakeoutPrice());

    MenuEntity entity = new MenuEntity(
            GlobalConst.NUM_CSV検索, // 検索
            0,
            form.getRestaurantName(),
            form.getMenuName(),
            form.getCategory(),
            form.getSingleMenuFlg(),
            eatInPrice,
            takeoutPrice,
            0,
            0,
            form.getUserName());

    try {

      List<MenuEntity> tmpList = menuDao.select(entity, form.getUserName());
      List<MenuEntity> menuList = tmpList.stream()
          .filter(ret ->
                eatInPriceCond.match(ret.getEatInPrice(), eatInPrice)
                && takeoutPriceCond.match(ret.getTakeoutPrice(), takeoutPrice)
                && isMatch(form.getRestaurantName(), ret.getRestaurantName())
                && isMatch(form.getMenuName(), ret.getMenuName())
                && isMatch(form.getCategory(), ret.getCategory())
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
