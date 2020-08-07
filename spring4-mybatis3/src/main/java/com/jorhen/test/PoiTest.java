package com.jorhen.test;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


public class PoiTest {

	public static void main(String[] args) throws Exception {
		//需要进行文本替换的信息
		Map<String, Object> data = new HashMap<String, Object>();
		//data.put("${date}", "2018-03-06");
		//data.put("${name}", "中文反體");
		//data.put("${address}", "上海黄浦江附近11");
		//data.put("${communityValue}", "我是中文");
		
		
		data.put("${rdate}", "2018-03-06");
	    data.put("${puse}",  "11");
		data.put("${sspot}", "22");
		//data.put("${cobj}", "55");
		//data.put("${notes}", "33");
		/*
		data.put("${communityValue}", "");
		data.put("${safetyCode}", "东方社区");
		data.put("${picture2}", "");
		data.put("${picture3}", "");
		data.put("${buildingValue2}", "漫展提示");
		data.put("${patrolPhoto1}", "");
		data.put("${patrolPhoto2}", "");
		data.put("${buildingValue3}", "中国标语");
		
		//图片，如果是多个图片，就新建多个map
		Map<String,Object> picture1 = new HashMap<String, Object>();
		picture1.put("width", 100);
		picture1.put("height", 150);
		picture1.put("type", "jpg");
		picture1.put("content", WorderToNewWordUtils.inputStream2ByteArray(new FileInputStream("template/c1.jpg"), true));
		data.put("${picture1}",picture1);
		
		//需要进行动态生成的信息
		Map<String,Object> mapList = new HashMap<String, Object>();
		
		//第一个动态生成的数据列表
		List<String[]> list01 = new ArrayList<String[]>();
		list01.add(new String[]{"A","美女好看"});
		list01.add(new String[]{"A","美女好多"});
		list01.add(new String[]{"B","漫展人太多"});
		list01.add(new String[]{"C","妹子穿的很清凉"});
		
		//第二个动态生成的数据列表
		List<String> list02 = new ArrayList<String>();
		list02.add("1、民主");
		list02.add("2、富强");
		list02.add("3、文明");
		list02.add("4、和谐");
 
		mapList.put("list01", list01);
		mapList.put("list02", list02);
		
 */
		Map<String,Object> mapList = new HashMap<String, Object>();
		//CustomXWPFDocument doc = WorderToNewWordUtils.changWord("/template/pc.docx",data,mapList);
		FileOutputStream fopts = new FileOutputStream("E:/22.docx");
		//doc.write(fopts);
		fopts.close();
		

	}

}
