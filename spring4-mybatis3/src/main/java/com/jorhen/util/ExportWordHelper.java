package com.jorhen.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.jorhen.domain.Team;

public class ExportWordHelper {
	
	//导出表格到word
    /**
     * 
     * @param statisticalGridman  中山区网格员上报案件排名 
     * @param totalGridman 有多少人没上报过案件
     * @return
     * @throws Exception
     */
    public void ExportWord(List<Team> mylist,
    		List<Map<String, String>> lzlist1,XWPFDocument document,String dateStr)throws Exception{
    	/*
		Map<String, String> params1 = new HashMap<String, String>();
		
		params1.put("JD", "ff1");
		params1.put("SQ", "ff2");
		params1.put("NAME", "f3f");
		params1.put("CODE", "ff4");
		params1.put("AJZS1", "f5f");
		params1.put("XCDWS1", "f6f");
		params1.put("XCCS1", "ff7");
		
		Map<String, String> params2 = new HashMap<String, String>();
		
		params1.put("JD", "ff31");
		params1.put("SQ", "ff2");
		params1.put("NAME", "f33f");
		params1.put("CODE", "f3f4");
		params1.put("AJZS1", "f35f");
		params1.put("XCDWS1", "f36f");
		params1.put("XCCS1", "ff37");
		
		Map<String, String> params3 = new HashMap<String, String>();
		
		params1.put("JD", "f5f1");
		params1.put("SQ", "ff52");
		params1.put("NAME", "f53f");
		params1.put("CODE", "f5f4");
		params1.put("AJZS1", "f55f");
		params1.put("XCDWS1", "f56f");
		params1.put("XCCS1", "f5f7");
		*/
		//wgylist1.add(params1);
		//wgylist1.add(params2);
		//wgylist1.add(params3);
	
        //两个表格之间加个换行  
        //XWPFParagraph paragraph = document.createParagraph();  
       // XWPFRun paragraphRun = paragraph.createRun();  
        //paragraphRun.setText("\r");  
        /*
        //添加标题，
        XWPFParagraph titleParagraph = document.createParagraph();  
        //设置段落居中  
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);  
  
        XWPFRun titleParagraphRun = titleParagraph.createRun();  
        titleParagraphRun.setText(dateStr+"此处为表格的标题部分");  
        titleParagraphRun.setColor("000000");  
        titleParagraphRun.setFontSize(24); 
        titleParagraphRun.setFontFamily("宋体");
        
        //换行  
        XWPFParagraph paragraph1 = document.createParagraph();  
        XWPFRun paragraphRun1 = paragraph1.createRun();  
        paragraphRun1.setText("\r");
        */  
        //表格部分
        XWPFTable ComTable = document.createTable();  
        //列宽自动分割  
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();  
        comTableWidth.setType(STTblWidth.DXA);  
        comTableWidth.setW(BigInteger.valueOf(9072));   
        //表格第一行  
        XWPFTableRow comTableRowOne = ComTable.getRow(0);  
        comTableRowOne.getCell(0).setText("姓名");  
        comTableRowOne.addNewTableCell().setText("職稱");  
        comTableRowOne.addNewTableCell().setText("職責");  
        comTableRowOne.addNewTableCell().setText("學歷科系");  
        comTableRowOne.addNewTableCell().setText("HACCP專業訓練及經驗");  
        //comTableRowOne.addNewTableCell().setText("测试");  
        //comTableRowOne.addNewTableCell().setText("测试");  
        //comTableRowOne.addNewTableCell().setText("测试");
        
        for(int i=0;i<mylist.size();i++){
            Team team = mylist.get(i);
            XWPFTableRow comTableRow = ComTable.createRow(); 
            comTableRow.getCell(0).setText((String) team.getMber());
            comTableRow.getCell(1).setText((String) team.getPos());
            comTableRow.getCell(2).setText((String) team.getDuty());
            comTableRow.getCell(3).setText((String) team.getBg());
            comTableRow.getCell(4).setText((String) team.getSkill());
            //comTableRow.getCell(5).setText(map.get("AJZS1"));
           // comTableRow.getCell(6).setText(map.get("XCDWS1"));
            //comTableRow.getCell(7).setText(map.get("XCCS1"));
        } 
        
        //换行  
        XWPFParagraph paragraph2 = document.createParagraph();  
        XWPFRun paragraphRun2 = paragraph2.createRun();  
        paragraphRun2.setText("\r");
    }
    
    /**
     * 设置字体样式
     * @param cell
     * @param text 
     * @throws Exception
     */
    public  void setCellText(XWPFTableCell cell,String text) throws Exception{  
        XWPFParagraph cellP=cell.getParagraphs().get(0);  
        XWPFRun cellR = cellP.createRun();  
        cellR.setText(text);  
        cellR.setFontFamily("宋体");
        cellR.setBold(true);
        cellR.setFontSize(9);
    }
    
    /**
     * 跨列合并   表格的下标是从0开始的
     * @param table 操作的是哪个表格
     * @param col  在第几行合并 
     * @param fromRow 在哪个列上开始合并
     * @param toRow  在哪个列上结束合并
     */
     public  void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {  
            for (int i = fromRow; i <= toRow; i++) {  
                XWPFTableCell cell = table.getRow(i).getCell(col);
                if ( i == fromRow ) {  
                    cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);  
                } else {  
                    cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);  
                }  
            }  
        }
     
    /**
     * 跨行合并   表格的下标是从0开始的
     * @param table 操作的是哪个表格
     * @param row 在第几列合并 
     * @param fromCell 在哪个行上开始合并
     * @param toCell  在哪个行上结束合并
     */
     
    public  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {  
        for (int i = fromCell; i <= toCell; i++) {  
            XWPFTableCell cell = table.getRow(row).getCell(i);  
            if ( i == fromCell ) {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);  
            } else {  
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);  
            }  
        }  
    } 

}
