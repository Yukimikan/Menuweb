package service;

import java.util.Collections;
import java.util.List;

import common.ConditionPrice;
import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuSearchForm;
import model.MenuEntity;

/**
 * MenuSearchService
 * メニュー検索条件入力.
 */
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
    String userName = form.getUserName();

    ConditionPrice eatInPriceCond = ConditionPrice.fromCode(form.getEatInCondition());
    ConditionPrice takeoutPriceCond = ConditionPrice.fromCode(form.getTakeoutCondition());

    int eatInPrice = this.convertTotal(form.getEatInPrice());
    int takeoutPrice = this.convertTotal(form.getTakeoutPrice());

    try {

      List<MenuEntity> tmpList =
          menuDao.select(
              form.getRestaurantName(),
              form.getMenuName(),
              form.getCategory(),
              form.getSingleMenuFlg());
      List<MenuEntity> menuList = tmpList.stream()
          .filter(ret ->
                eatInPriceCond.match(ret.getEatInPrice(), eatInPrice)
                && takeoutPriceCond.match(ret.getTakeoutPrice(), takeoutPrice)
                && isMatch(form.getRestaurantName(), ret.getRestaurantName())
                && isMatch(form.getMenuName(), ret.getMenuName())
                && isMatch(form.getCategory(), ret.getCategory())
                && isMatch(form.getSingleMenuFlg(), ret.getSingleMenuFlg())
                )
              .toList();
      if (menuList.size() == 0) {
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
