package service;

import java.util.Collections;
import java.util.List;

import common.ConditionTotal;
import dao.MenuDao;
import dao.MenuDaoImpl;
import form.MenuSearchForm;
import model.GlobalConst;
import model.MenuEntity2;

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

    ConditionTotal eatInPriteCond = ConditionTotal.fromCode(form.getEatInCondition());
    ConditionTotal totalCond = ConditionTotal.fromCode(form.getTotalCondition());

    int eatInPrice = this.convertTotal(form.getEatInPrice());
    int takeoutPrice = this.convertTotal(form.getTakeoutPrice());
    int tax = this.convertTotal(form.getTax());
    int total = this.convertTotal(form.getTotal());

    MenuEntity2 entity = new MenuEntity2(
            GlobalConst.NUM_CSV検索, // 検索
            Integer.parseInt(form.getRestaurantId()),
            form.getRestaurantName(),
            form.getMenuName(),
            form.getCategory(),
            form.getSingleMenuFlg(),
            eatInPrice,
            takeoutPrice,
            tax,
            total,
            userName);

    try {

      List<MenuEntity2> tmpList = menuDao.select(entity, userName);
      List<MenuEntity2> menuList = tmpList.stream()
          .filter(ret ->
                eatInPriteCond.match(ret.getEatInPrice(), eatInPrice)
                && totalCond.match(ret.getTotal(), total)
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
