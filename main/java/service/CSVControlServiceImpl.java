package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.MenuCSV;

public class CSVControlServiceImpl {

	/*
	 * 参考サイト
	 * https://style.potepan.com/articles/18230.html
	 * */

	//絶対パスで指定
	public final String CSV_PATH = "C:\\pleiades\\workspace\\Menuweb\\resource\\";
	public final String csv_name = "menu.csv";

	//コンストラクタ
	public CSVControlServiceImpl() {
	}

	/* 
	 * @param String csv_name
	 * @return List<MenuCSV>
	 */
	public List<MenuCSV> readCSV() {

		BufferedReader buffReader = null;
		String infile_path = CSV_PATH + csv_name;
		MenuCSV in_csv = new MenuCSV();
		List<MenuCSV> retList = new ArrayList<MenuCSV>();

		try {
			// sample1.csvファイルを読み込みます
			FileInputStream fileInput = new FileInputStream(infile_path); // ※1
			// バイトストリームをテキスト形式に変換
			InputStreamReader inputStream = new InputStreamReader(fileInput); // ※2
			// テキスト形式のファイルを読み込む
			buffReader = new BufferedReader(inputStream); // ※3
			String currentContent;
			int row = 0;
			String[] arrayColumnName = null;
			while ((currentContent = buffReader.readLine()) != null) { // ※4
				//初期化
				in_csv = new MenuCSV();

				if (row == 0) { // ※5 header
					arrayColumnName = currentContent.split(",");
				} else {
					// カラムを分割
					String[] arrayColumnData = currentContent.split(","); // ※6
					/*	
					int arrayNumber = 0;
					for (String columnName : arrayColumnName) { //※7
						System.out.println(columnName + " = " + arrayColumnData[arrayNumber]); //※8
						arrayNumber++;
					}
					*/
					//戻り値を追加
					in_csv.setAllColumns(arrayColumnData);
					retList.add(in_csv);
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("ファイル見つからない：" + infile_path);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//close処理
			try {
				buffReader.close(); //※9
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return retList;
	}
	
	/* 
	 * @param String csv_name
	 */
	public List<MenuCSV> writeCSV(MenuCSV rec) {

		BufferedWriter buffWriter = null;
		String outfile_path = CSV_PATH + "\\output\\" + csv_name;
		List<MenuCSV> retList = new ArrayList<MenuCSV>();

		try {
			File f = new File(outfile_path);
			// 第二引数trueで、追加書き
			FileWriter fw = new FileWriter(f,true);		
			buffWriter = new BufferedWriter(fw); // ※3
			String temp_rec = rec.returnJoinedString();
			//
			buffWriter.write(temp_rec);

		} catch (FileNotFoundException e) {
			System.out.println("ファイル見つからない：" + outfile_path);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//close処理
			try {
				buffWriter.close(); //※9
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		//再読み込み
		retList = readCSV();
		return retList;
	}
}
