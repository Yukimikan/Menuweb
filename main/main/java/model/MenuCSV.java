package model;

public class MenuCSV {

	public final static String csv_HEADER ="No,種類,店名,単品,メニュー,価格,税,金額";
	//No,種類,店名,単品,メニュー,価格,税,金額
	//1,和食,松屋,○,牛丼,500,なし,500
	private String no;
	private String type;
	private String restaurant_name;
	private String singlemenu_flg;
	private String menu;
	private String price;
	private String tax;
	private String total;
	
	/*
	 * constructor
	 */
	public MenuCSV() {};
	
	/*
	 * @param  arrayColumnData 分割済みのデータ
	 * @throw ArrayIndexOutOfBoundsException
	 */
	public void setAllColumns(String[] arrayColumnData){
		
		try {
			this.setNo(arrayColumnData[0]);
			this.setType(arrayColumnData[1]);
			this.setRestaurant_name(arrayColumnData[2]);
			this.setSinglemenu_flg(arrayColumnData[3]);
			this.setMenu(arrayColumnData[4]);
			this.setPrice(arrayColumnData[5]);
			this.setTax(arrayColumnData[6]);
			this.setTotal(arrayColumnData[7]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("添字オーバー");
		}
	}
	
	/*
	 * @param  arrayColumnData 分割済みのデータ
	 * @throw ArrayIndexOutOfBoundsException
	 */
	public String returnJoinedString(){
		String[] arrayColumnData = new String[8];
		arrayColumnData[0] = no;
		arrayColumnData[1] = type;
		arrayColumnData[2] = restaurant_name;
		arrayColumnData[3] = singlemenu_flg;
		arrayColumnData[4] = menu;
		arrayColumnData[5] = price;
		arrayColumnData[6] = tax;
		arrayColumnData[7] = total;
		//Java8から
		String sj = String.join(",", arrayColumnData);
		return sj;		
	}
	
	/*
	 * アクセッサ-
	 * */
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getSinglemenu_flg() {
		return singlemenu_flg;
	}
	public void setSinglemenu_flg(String singlemenu_flg) {
		this.singlemenu_flg = singlemenu_flg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
