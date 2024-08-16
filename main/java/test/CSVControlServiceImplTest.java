package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import model.MenuCSV;
import service.CSVControlServiceImpl;

public class CSVControlServiceImplTest {

	/*
	 * 異常系
	 * 引数：null
	 * ①０件：ループなし
	 * 異常終了:
	 */
	@Test
	public void Abend01() {
				
		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV(null);
			fail("異常未発生");
		} 	catch (Exception e) {
			//OK
		}
		
	}

	/*
	 * 異常系
	 * 引数：空白
	 * ①０件：ループなし
	 * 異常終了: FileNotFoundException
	 */
	@Test
	public void Abend02() {

		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV("");
			fail("異常発生");
		} 	catch (FileNotFoundException e) {
			//OK
			
		} 	catch (Exception e) {
			fail("異常発生");
		}
		
	}

	
}
