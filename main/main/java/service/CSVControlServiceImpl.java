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
	public final int MAX_COUNT = 100;
	//メッセージ
	public final String MSG_W_FILENAME_ERROR = "ファイル名がありません";
	public final String MSG_W_FILE_NOT_FOUND = "ファイルが見つかりません";
	public final String MSG_W_FILE_MAXCOUNT_OVER = "最大件数超過、最大件数まで読み込みます";
	public final String MSG_W_INDATA_NOT_NUMERIC = "入力データに数値型以外が含まれます";
	public final String MSG_W_INDATA_ISNULL = "入力データがNULLです";

	//コンストラクタ
	public CSVControlServiceImpl() {
	}

	/* 
	 * @param String csv_name
	 * @return List<MenuCSV>
	 */
	public List<MenuCSV> readCSV(String infilename) 
			throws Exception{
		BufferedReader buffReader = null;
		String infile_path = CSV_PATH + infilename;
		MenuCSV in_csv = new MenuCSV();
		List<MenuCSV> retList = new ArrayList<MenuCSV>();
		
		// 入力チェック
		if(commonCheck(infilename) == false) {
			return null;
		}

		try {
			// sample1.csvファイルを読み込みます
			FileInputStream fileInput = new FileInputStream(infile_path); // ※1
			// バイトストリームをテキスト形式に変換
			InputStreamReader inputStream = new InputStreamReader(fileInput); // ※2
			// テキスト形式のファイルを読み込む
			buffReader = new BufferedReader(inputStream); // ※3
			String currentContent;
			int row = 0;
			while ((currentContent = buffReader.readLine()) != null) { // ※4

				// 最大件数超過
				if(row == MAX_COUNT) {
					System.out.println(MSG_W_FILE_MAXCOUNT_OVER);
					break;
				}
				
				//初期化
				in_csv = new MenuCSV();

				if (row == 0) { 
					// ※5 header
				} else {
					// カラムを分割
					String[] arrayColumnData = currentContent.split(","); // ※6
					//戻り値を追加
					in_csv.setAllColumns(arrayColumnData);
					retList.add(in_csv);
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(MSG_W_FILE_NOT_FOUND);
			System.out.println("infile_path：" + infile_path);
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
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
	 * @return List<MenuCSV>
	 */
	public List<MenuCSV> writeCSV(MenuCSV rec, String outfilename) 
			throws Exception{

		BufferedReader buffReader = null;
		BufferedWriter buffWriter = null;
//		String outfile_path = CSV_PATH + "\\output\\" + outfilename;
		String outfile_path = CSV_PATH + outfilename;
		List<MenuCSV> retList = new ArrayList<MenuCSV>();
		
		//入力チェック
		if(commonCheck(outfilename) == false || 
				formatCheck(rec) == false) {
			return null;
		}
		
		try {
			File f = new File(outfile_path);
			// 第二引数trueで、追加書き
			FileWriter fw = new FileWriter(f,true);		
			buffWriter = new BufferedWriter(fw); // ※3
			String temp_rec = rec.returnJoinedString();
			
			if (f.exists() == false) {
				//ファイル未存在
				//空ファイルなのでヘッダ書込
				buffWriter.write(MenuCSV.csv_HEADER);
				buffWriter.newLine();
			}else {
				//ヘッダ読込
				FileInputStream fileInput = new FileInputStream(outfile_path); // ※1
				InputStreamReader inputStream = new InputStreamReader(fileInput); // ※2
				buffReader = new BufferedReader(inputStream); // ※3
				
				String top_rec =  buffReader.readLine();
				buffReader.close();
				
				//読込一行がヘッダーと一致
				if (MenuCSV.csv_HEADER.equals(top_rec)) {
					//ヘッダ存在
				}else {
					//ヘッダ未存在
					//空ファイルなのでヘッダ書込
					buffWriter.write(MenuCSV.csv_HEADER);
					buffWriter.newLine();
				}
			}
			//データ部書込
			buffWriter.write(temp_rec);
		} catch (FileNotFoundException e) {
			System.out.println(MSG_W_FILE_NOT_FOUND);
			System.out.println("outfile_path：" + outfile_path);
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			//close処理
			try {
				buffWriter.close(); //※9
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		//再読み込み
		retList = readCSV(outfilename);
		return retList;
	}
	
	public boolean commonCheck(String filename) {
		//チェック
		if(filename == null || filename.isEmpty()) {
			System.out.println(MSG_W_FILENAME_ERROR);
			System.out.println("filename:" + filename);
			return false;
		}
		return true;
	}

	public boolean formatCheck(MenuCSV rec) {
		//チェック
		if(rec == null) {
			System.out.println(MSG_W_INDATA_ISNULL);
			return false;
		}
		//チェック
		try {
			Integer.parseInt(rec.getPrice());
			Integer.parseInt(rec.getTotal());
		}catch(NumberFormatException e){
			System.out.println(MSG_W_INDATA_NOT_NUMERIC);
			System.out.println("price：" + rec.getPrice() +
					"total:" + rec.getTotal());
			return false;
		}
		return true;
	}

}
