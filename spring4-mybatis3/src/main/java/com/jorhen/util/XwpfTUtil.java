package com.jorhen.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class XwpfTUtil {

	/*
	 * String filePath = "/sta.docx"; InputStream is; XWPFDocument doc; Map<String,
	 * Object> params = new HashMap<String, Object>(); { params.put("${name}",
	 * "xxx"); params.put("${sex}", "男"); params.put("${political}", "共青團員");
	 * params.put("${place}", "sssss"); params.put("${classes}", "3102");
	 * params.put("${id}", "213123123"); params.put("${qq}", "213123");
	 * params.put("${tel}", "312313213"); params.put("${oldJob}", "sadasd");
	 * params.put("${swap}", "是"); params.put("${first}", "asdasd");
	 * params.put("${second}", "綜合事務部"); params.put("${award}", "asda");
	 * params.put("${achievement}", "完成科協網站的開發"); params.put("${advice}", "沒有建議");
	 * params.put("${attach}", "無"); try { is = new FileInputStream(filePath); doc =
	 * new XWPFDocument(is); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
	 */

	/**
	 * 用一個docx文檔作為範本，然後替換其中的內容，再寫入目的文件中。
	 *
	 * @throws Exception
	 */
	/*
	 * @Test public void testTemplateWrite() throws Exception { //替換段落裡面的變數
	 * this.replaceInPara(doc, params); //替換表格裡面的變數 this.replaceInTable(doc,
	 * params); OutputStream os = new FileOutputStream("D:\\sta1.docx");
	 * doc.write(os); this.close(os); this.close(is); }
	 */

	/*
	 * @Test public void myTest1() throws Exception {
	 *//*
		 * Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator(); XWPFParagraph
		 * para; while (iterator.hasNext()) { para = iterator.next(); List<XWPFRun> runs
		 * = para.getRuns(); para.removeRun(0); para.insertNewRun(0).setText("hello"); }
		 * OutputStream os = new FileOutputStream("D:\\sta1.docx"); doc.write(os);
		 * this.close(os); this.close(is);
		 *//*
			 * System.out.println(this.matcher("報告日期：${reportDate}").find()); }
			 */

	/*
	 * @Test public void myReplaceInPara() { // Iterator<XWPFParagraph> iterator =
	 * doc.getParagraphsIterator(); // XWPFParagraph para; // while
	 * (iterator.hasNext()) { // para = iterator.next(); // List<XWPFRun> runs =
	 * para.getRuns(); // // // } System.out.println('{'=='{'); }
	 */
	/*
	 * 
	 * XWPFParagraph：代表一个段落。
	 * 
	 * XWPFRun：代表具有相同属性的一段文本。
	 * 
	 * XWPFTable：代表一个表格。
	 * 
	 * XWPFTableRow：表格的一行。
	 * 
	 * XWPFTableCell：表格对应的一个单元格。
	 */

	/**
	 * 替換段落裡面的變數
	 *
	 * @param doc
	 *            要替換的文檔
	 * @param params
	 *            參數
	 */
	public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
		XWPFParagraph para;
		while (iterator.hasNext()) {
			para = iterator.next();
			this.replaceInPara(para, params);
		}
	}

	/**
	 * 替換段落裡面的變數
	 *
	 * @param para
	 *            要替換的段落
	 * @param params
	 *            參數
	 */
	public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
		List<XWPFRun> runs;
		Matcher matcher;
		// 獲取每個paragraph中的文字資訊
		if (this.matcher(para.getParagraphText()).find()) {
			runs = para.getRuns();

			int start = -1;
			int end = -1;
			String str = "";
			for (int i = 0; i < runs.size(); i++) {
				XWPFRun run = runs.get(i);
				String runText = run.toString();
				System.out.println("------>>>>>>>>>" + runText);
				if ('$' == runText.charAt(0) && '{' == runText.charAt(1)) {
					start = i;
				}
				if ((start != -1)) {
					str += runText;
				}
				if ('}' == runText.charAt(runText.length() - 1)) {
					if (start != -1) {
						end = i;
						break;
					}
				}
			}
			System.out.println("start--->" + start);
			System.out.println("end--->" + end);

			System.out.println("str---->>>" + str);
			// 直接調用XWPFRun的setText()方法設置文本時，在底層會重新創建一個XWPFRun，把文本附加在當前文本後面，
			// 所以我們不能直接設值，需要先刪除當前run,然後再自己手動插入一個新的run。

			for (int i = start; i <= end; i++) {
				para.removeRun(i);
				i--;
				end--;
				System.out.println("remove i=" + i);
			}

			for (String key : params.keySet()) {
				if (str.equals(key)) {
					para.createRun().setText((String) params.get(key));
					System.out.println("=======替換段落裡面的變數=runText======="+para.getText());
					break;
				}
			}

		}
	}

	/**
	 * 替換表格裡面的變數
	 *
	 * @param doc
	 *            要替換的文檔
	 * @param params
	 *            參數
	 */
	public void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		List<XWPFParagraph> paras;
		while (iterator.hasNext()) {
			table = iterator.next();
			rows = table.getRows();
			for (XWPFTableRow row : rows) {
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					paras = cell.getParagraphs();
					for (XWPFParagraph para : paras) {
						this.replaceInPara(para, params);
						System.out.println("=======替換段落裡面的變數=params======="+params);
					}
				}
			}
		}
	}

	/**
	 * 正則匹配字串
	 *
	 * @param str
	 * @return
	 */
	private Matcher matcher(String str) {
		//啟用不區分大小寫的匹配。
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

	/**
	 * 關閉輸入流
	 *
	 * @param is
	 */
	public void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 關閉輸出流
	 *
	 * @param os
	 */
	public void close(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
