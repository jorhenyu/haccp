package com.jorhen.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jorhen.domain.Pc;
import com.jorhen.service.PcServiceI;
import com.jorhen.util.JfreeUtil;

@Controller
@RequestMapping("/fchart")
public class ChartController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(ChartController.class);

	// @Autowired
	// private CityBiz cityBiz;

	// 處理業務邏輯的pcService
	@Autowired
	private PcServiceI pcService;

	List<Pc> lsts = null;
	String rder = null;

	public PcServiceI getPc() {
		return pcService;
	}

	public void setPc(PcServiceI pc) {
		pcService = pc;
	}

	//@RequestMapping("/index")
	//public String resultmap() {
	//	return "/index";
	//}
	
	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		return "fchart/index";
	}

	// 显示折线图
	@RequestMapping(value = "/getColumnLine")
	public String getColumnLine(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		// 1. 获得数据集合
		CategoryDataset dataset = getDataSetLine();
		log.info("==获得数据集合==");
		// 2. 创建柱状图
		
	    JFreeChart chart = ChartFactory.createLineChart("用户统计报表（所属单位）", // 主标题的名称
                "所属单位名称", // X轴的标签
                "数量", // Y轴的标签
                dataset, // 图标显示的数据集合
                PlotOrientation.VERTICAL, // 图像的显示形式（水平或者垂直）
                true, // 是否显示子标题
                true, // 是否生成提示的标签
                true); // 是否生成URL链接
        
       
		log.info("==创建柱状图==");
		// 3. 设置整个柱状图的颜色和文字（char对象的设置是针对整个图形的设置）
		chart.setBackgroundPaint(ChartColor.WHITE); // 设置总的背景颜色
		log.info("==设置整个柱状图的颜色和文字==");
		// 4. 获得图形对象，并通过此对象对图形的颜色文字进行设置
		CategoryPlot p = chart.getCategoryPlot();// 获得图表对象
		p.setBackgroundPaint(ChartColor.lightGray);// 图形背景颜色
		p.setRangeGridlinePaint(ChartColor.WHITE);// 图形表格颜色

		JfreeUtil.setJfreeLine(chart);

		// 6. 将图形转换为图片，传到前台
		String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String chartURL = basePath + "chart?filename=" + fileName;
		//modelMap.put("chartURL", chartURL);
		log.info("==chartURL=="+chartURL);
		modelMap.addAttribute("chartURL", chartURL);
		return "fchart/index";
	}
	
	   // 获取折线图数据集
    private DefaultCategoryDataset getDataSetLine() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
        /*
        dataset.addValue(20, "上限", "1月");
        dataset.addValue(20, "上限", "2月");
        dataset.addValue(20, "上限", "3月");
        dataset.addValue(20, "上限", "4月");
        dataset.addValue(20, "上限", "5月");
        dataset.addValue(20, "上限", "6月");
        dataset.addValue(20, "上限", "7月");
        dataset.addValue(20, "上限", "8月");
        dataset.addValue(20, "上限", "9月");
        dataset.addValue(20, "上限", "10月");
        dataset.addValue(20, "上限", "11月");
        dataset.addValue(20, "上限", "12月");
        dataset.addValue(5, "下限", "1月");
        dataset.addValue(5, "下限", "2月");
        dataset.addValue(5, "下限", "3月");
        dataset.addValue(5, "下限", "4月");
        dataset.addValue(5, "下限", "5月");
        dataset.addValue(5, "下限", "6月");
        dataset.addValue(5, "下限", "7月");
        dataset.addValue(5, "下限", "8月");
        dataset.addValue(5, "下限", "9月");
        dataset.addValue(5, "下限", "10月");
        dataset.addValue(5, "下限", "11月");
        dataset.addValue(5, "下限", "12月");
        dataset.addValue(10, "平均", "1月");
        dataset.addValue(10, "平均", "2月");
        dataset.addValue(10, "平均", "3月");
        dataset.addValue(10, "平均", "4月");
        dataset.addValue(10, "平均", "5月");
        dataset.addValue(10, "平均", "6月");
        dataset.addValue(10, "平均", "7月");
        dataset.addValue(10, "平均", "8月");
        dataset.addValue(10, "平均", "9月");
        dataset.addValue(10, "平均", "10月");
        dataset.addValue(10, "平均", "11月");
        dataset.addValue(10, "平均", "12月");
        
        dataset.addValue(2, "2011", "1月");
        dataset.addValue(4, "2011", "2月");
        dataset.addValue(5, "2011", "3月");
        dataset.addValue(12, "2011", "4月");
        dataset.addValue(6, "2011", "5月");
        dataset.addValue(7, "2011", "6月");
        dataset.addValue(1, "2011", "7月");
        dataset.addValue(16, "2011", "8月");
        dataset.addValue(18, "2011", "9月");
        dataset.addValue(20, "2011", "10月");
        dataset.addValue(21, "2011", "11月");
        dataset.addValue(24, "2011", "12月");
        
        dataset.addValue(22, "2022", "1月");
        dataset.addValue(5, "2022", "2月");
        dataset.addValue(7, "2022", "3月");
        dataset.addValue(12, "2022", "4月");
        dataset.addValue(9, "2022", "5月");
        dataset.addValue(3, "2022", "6月");
        dataset.addValue(17, "2022", "7月");
        dataset.addValue(16, "2022", "8月");
        dataset.addValue(18, "2022", "9月");
        dataset.addValue(12, "2022", "10月");
        dataset.addValue(24, "2022", "11月");
        dataset.addValue(16, "2022", "12月");
        */
        
        dataset.addValue(20, "上限", "0801");
        dataset.addValue(20, "上限", "0802");
        dataset.addValue(20, "上限", "0803");       
        dataset.addValue(20, "上限", "0804");
        dataset.addValue(20, "上限", "0805");
        dataset.addValue(20, "上限", "0806");
        dataset.addValue(20, "上限", "0807");
        dataset.addValue(20, "上限", "0808");
        dataset.addValue(20, "上限", "0809");
        dataset.addValue(20, "上限", "0810");
        dataset.addValue(20, "上限", "0811");
        dataset.addValue(20, "上限", "0812");

    
        dataset.addValue(5, "下限", "0801");  
        dataset.addValue(5, "下限", "0802");  
        dataset.addValue(5, "下限", "0803");     
        dataset.addValue(5, "下限", "0804"); 
        dataset.addValue(5, "下限", "0805");  
        dataset.addValue(5, "下限", "0806");  
        dataset.addValue(5, "下限", "0807");  
        dataset.addValue(5, "下限", "0808");  
        dataset.addValue(5, "下限", "0809");  
        dataset.addValue(5, "下限", "0810");  
        dataset.addValue(5, "下限", "0811");  
        dataset.addValue(5, "下限", "0812");  
                                              
        dataset.addValue(10, "平均", "0801");  
        dataset.addValue(10, "平均", "0802");  
        dataset.addValue(10, "平均", "0803");        
        dataset.addValue(10, "平均", "0804");  
        dataset.addValue(10, "平均", "0805");  
        dataset.addValue(10, "平均", "0806");  
        dataset.addValue(10, "平均", "0807");  
        dataset.addValue(10, "平均", "0808");  
        dataset.addValue(10, "平均", "0809");  
        dataset.addValue(10, "平均", "0810");  
        dataset.addValue(10, "平均", "0811");  
        dataset.addValue(10, "平均", "0812");  
                                               
        
        dataset.addValue(2, "2011",  "0801");  
        dataset.addValue(4, "2011",  "0802");  
        dataset.addValue(5, "2011",  "0803");  
        dataset.addValue(12, "2011", "0804");  
        dataset.addValue(6, "2011",  "0805");  
        dataset.addValue(7, "2011",  "0806");  
        dataset.addValue(1, "2011",  "0807");  
        dataset.addValue(16, "2011", "0808");  
        dataset.addValue(18, "2011", "0809");  
        dataset.addValue(20, "2011", "0810");  
        dataset.addValue(21, "2011", "0811");  
        dataset.addValue(24, "2011", "0812"); 
        
        return dataset;
    }
    

  //定位到上传单个文件界面 /hello/upload    
  		@RequestMapping(value="/upload")
  		public String showUploadPage(){	
  			return "fchart/uploadFile";		 //view文件夹下的上传单个文件的页面
  		}
  		

/**
	 * 上传单个文件操作
	 * @param RequestParam 括号中的参数名file和表单的input节点的name属性值一致
	 * @return
	 */
	@RequestMapping(value="/doUpload")
	public String doUploadFile(@RequestParam("file") MultipartFile file){
 
		if(!file.isEmpty()){
			try {
				
				//这里将上传得到的文件保存至 d:\\temp\\file 目录
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("e:\\temp\\file\\", 
						System.currentTimeMillis()+ file.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
		return "success";  //上传成功则跳转至此success.jsp页面
	}


	

}
