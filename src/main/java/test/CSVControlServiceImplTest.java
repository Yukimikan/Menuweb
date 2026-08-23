package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

import model.MenuCSV;
import service.CSVControlServiceImpl;

public class CSVControlServiceImplTest {

	/*
	 * 準正常系
	 * 引数：null
	 * ①０件：ループなし
	 * 正常終了:
	 * @return null
	 */
	@Test
	public void SemiNormal01_readCSV() {

		System.out.println("SemiNormal01_readCSV");
		try {
			//OK
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV(null);
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

	/*
	 * 異常系
	 * 引数：空白
	 * 正常終了
	 */
	@Test
	public void SemiNormal02_readCSV() {

		System.out.println("SemiNormal02_readCSV");
		try {
			//OK
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV("");
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

	/*
	 * 異常系
	 * 引数：エラー
	 * 異常終了: FileNotFoundException
	 */
	@Test
	public void AbNormal01_readCSV() {

		System.out.println("case3");
		List<MenuCSV> retList = null;
		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			retList = service.readCSV("error.csv");
			fail("異常確認失敗");
		} 	catch (FileNotFoundException e) {
			//OK
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

	/*
	 * 正常系 No01-06
	 * *
	 */
	@Test
	public void Normal_readCSV() {

		 /*
		 * 引数：ファイル名
		 * ①0件：ループなし
		 * @return 0件
		 */
		 normal_readCSVExec("Normal01","empty.csv", 0);

		 /*
		 * 引数：ファイル名
		 * ①1件：ループ
		 * @return 0件
		 */		 
		 normal_readCSVExec("Normal02","menu_test1.csv",0);

		 /*
		 * 引数：ファイル名
		 * ①2件：ループ
		 * @return 1件
		 */		 
		 normal_readCSVExec("Normal03","menu_test2.csv",1);

		 /*
		 * 引数：ファイル名
		 * ①3件：ループ
		 * @return 2件
		 */		 
		 normal_readCSVExec("Normal04","menu_test3.csv",2);

		 /*
		 * 引数：ファイル名
		 * ①100件：ループ
		 * 正常終了:
		 * @return 99件
		 */		 
		 normal_readCSVExec("Normal05","menu_testmax.csv",99);

		 /*
		 * 引数：ファイル名
		 * ①100件：ループ
		 * 正常終了:
		 * @return 99件
		 */		 
		 normal_readCSVExec("Normal06","menu_testmaxover.csv",99);
	 }

	/*
	 * 正常系実行
	 */
	private void normal_readCSVExec(String caseid, String filename,int expectedCnt) {

		System.out.println(caseid);
		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV(filename);
			//戻り値チェック
			assertEquals(expectedCnt, retList.size());
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}
	
	/*
	 * 準正常系
	 * @param CSV名：null
	 * @param：入力データ：あり
	 * ①０件：ループなし
	 * 正常終了:
	 * @return null
	 */
	@Test
	public void SemiNormal01_writeCSV() {

		System.out.println("SemiNormal01_writeCSV");
		try {
			//OK
			// データ移送
			MenuCSV rec = new MenuCSV();

			// service
			rec.setNo("99");
			rec.setType("和食");
			rec.setRestaurant_name("松屋");
			rec.setSinglemenu_flg("○");
			rec.setMenu("チーズ牛丼");
			rec.setPrice("550");
			rec.setTax("なし");
			rec.setTotal("550");
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(rec,null);
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

	/*
	 * 準正常系
	 * @param CSV名：空白
	 * @param：入力データ：あり
	 * 正常終了
	 * @return null
	 */
	@Test
	public void SemiNormal02_writeCSV() {

		System.out.println("SemiNormal02_writeCSV");
		try {
			//OK
			// データ移送
			MenuCSV rec = new MenuCSV();

			// service
			rec.setNo("99");
			rec.setType("和食");
			rec.setRestaurant_name("松屋");
			rec.setSinglemenu_flg("○");
			rec.setMenu("チーズ牛丼");
			rec.setPrice("550");
			rec.setTax("なし");
			rec.setTotal("550");
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(rec,"");
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}
	/*
	 * 異常系
	 * @param CSV名：あり
	 * @param：入力データ：null
	 * 正常終了:
	 * @return null
	 */
	@Test
	public void AbNormal01_writeCSV() {

		System.out.println("AbNormal01_writeCSV");
		try {
			//OK
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(null,"menu_test.csv");
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}
	

	/*
	 * 正常系 No01-04
	 * *
	 */
	@Test
	public void Normal_writeCSV() {
		
        // 現在日時を取得
        LocalDateTime nowDate = LocalDateTime.now();
		// 表示形式を指定
        DateTimeFormatter dtf3 =
            DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                String formatNowDate = dtf3.format(nowDate);
        String fname = "menu" + formatNowDate + ".csv";
		// データ移送
		MenuCSV rec = new MenuCSV();

        rec.setNo("99");
		rec.setType("和食");
		rec.setRestaurant_name("松屋");
		rec.setSinglemenu_flg("○");
		rec.setMenu("チーズ牛丼");
		rec.setPrice("550");
		rec.setTax("なし");
		rec.setTotal("550");

		/*
		 * @param CSV名：未存在
		 * @param：入力データ：あり
		 * 入力ファイル新規作成
		 * @return retList 1件
		 */
		normal_writeCSVexec("Normal01_writeCSV",rec, fname, 1);
		
		/*
		 * @param CSV名：存在
		 * @param：入力データ：あり
		 * 入力ファイル：追加書き(空ファイル)
		 * @return retList 1件
		 */
		normal_writeCSVexec("Normal02_writeCSV",rec, "menu_outempty.csv", 1);
		
		/*
		 * @param CSV名：存在
		 * @param：入力データ：あり
		 * 入力ファイル追加書き(ヘッダのみ)
		 * @return retList 1件
		 */
		normal_writeCSVexec("Normal03_writeCSV",rec, "menu_out1.csv", 1);

		/*
		 * @param CSV名：存在
		 * @param：入力データ：あり
		 * 入力ファイル追加書き(ヘッダ＋１件)
		 * @return retList 2件
		 */
		normal_writeCSVexec("Normal04_writeCSV",rec, "menu_out2.csv", 2);
	}
	
	/*
	 * 正常系
	 */
	private void normal_writeCSVexec(String caseid, MenuCSV rec, String filename,int expectedCnt) {

		System.out.println(caseid);
		List<MenuCSV> retList = null;
		try {
			//OK
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
	        retList = service.writeCSV(rec,filename);
			//戻り値チェック
	        assertEquals(expectedCnt,retList.size());
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}
	

	/*
	 * 準正常系
	 * @param CSV名：あり
	 * @param：入力データ：数値型以外エラー
	 * 正常終了
	 * @return null
	 */
	@Test
	public void SemiNormal03_writeCSV() {

		System.out.println("SemiNormal03_writeCSV");
		try {
			//OK
			// データ移送
			MenuCSV rec = new MenuCSV();

			// service
			rec.setNo("99");
			rec.setType("和食");
			rec.setRestaurant_name("松屋");
			rec.setSinglemenu_flg("○");
			rec.setMenu("チーズ牛丼");
			rec.setPrice("error");	//数値以外
			rec.setTax("なし");
			rec.setTotal("550");
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(rec,"menu_test.csv");
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

	/*
	 * 準正常系
	 * @param CSV名：あり
	 * @param：入力データ：数値型以外エラー
	 * 正常終了
	 * @return null
	 */
	@Test
	public void SemiNormal04_writeCSV() {

		System.out.println("SemiNormal04_writeCSV");
		try {
			//OK
			// データ移送
			MenuCSV rec = new MenuCSV();

			// service
			rec.setNo("99");
			rec.setType("和食");
			rec.setRestaurant_name("松屋");
			rec.setSinglemenu_flg("○");
			rec.setMenu("チーズ牛丼");
			rec.setPrice("550");
			rec.setTax("なし");
			rec.setTotal("error");	//数値以外
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(rec,"menu_test.csv");
			//戻り値チェック
			assertTrue(retList == null);
		} 	catch (Exception e) {
			fail("異常発生");
		}		
	}

}
