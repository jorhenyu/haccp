package com.jorhen.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.jorhen.util.XwpfTUtil;

public class PoiWordTest {
	
	public static void main(String[] args) throws Exception {
		//需要进行文本替换的信息
		  XWPFDocument doc = null;
	        InputStream is=null;
	        //OutputStream os = null;
	        XwpfTUtil xwpfTUtil = new XwpfTUtil();
	        FileOutputStream os = null;
	        try{
	            Map<String, Object> params = new HashMap<String, Object>();
	           // params.put("${name}", "张三");
	            
	            params.put("${rdate}", "2018-03-06");
	            params.put("${puse}",  "11");
	            params.put("${sspot}", "22");
	    
	                String fileNameInResource="/template/pc.docx";//word模版的名字
	                    
	            //is = getClass().getClassLoader().getResourceAsStream(fileNameInResource);//会在跟路径下面查看temp.doc文件。(web项目中为classes文件下面)
	            doc = new XWPFDocument(is);
	            xwpfTUtil.replaceInPara(doc, params);
	            //替换表格里面的变量
	            xwpfTUtil.replaceInTable(doc, params);

	            //os = response.getOutputStream();
	           // response.setContentType("application/vnd.ms-excel");
	           // response.setHeader("Content-disposition","attachment;filename=export-Word-name.docx");//filename为导出的word的名字
	             os = new FileOutputStream("E:/22.docx");
	            doc.write(os);

	        }catch (Exception e){
	            e.getMessage();
	        }finally {
	            xwpfTUtil.close(os);
	            xwpfTUtil.close(is);
	            try {
	                os.flush();
	                os.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	        }

	}

}
