package com.jorhen.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Monit;
import com.jorhen.domain.Query;
import com.jorhen.service.MonitServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.util.DateUtils;
import com.jorhen.util.JfreeUtil;

@Controller
@RequestMapping("/monit")
public class MonitController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(MonitController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private MonitServiceI monitService;
	
    @Autowired
    OptionService optionService;
	
	List<Monit> lsts = null;
	String rder = null;		

	public MonitServiceI getMonit() {
		return monitService;
	}

	public void setCcp(MonitServiceI monit) {
		monitService = monit;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = monitService.getMyMonit(user.getuName());
		model.addAttribute("lsts", lsts);				
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		return "monit/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "monitId", required = false) String monitId) {
		Monit monit = monitService.selectMonitById(monitId);
		model.addAttribute("monit", monit);				
		return "monit/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Monit monit) {
		
		monit.setMder(user.getuName());
		int aa = monitService.updateByPrimaryKeySelective(monit);
		log.info("==aa==" + aa);
		return "redirect:/monit/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {		
			return "monit/add";		
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Monit monit) {
		monit.setRder(user.getuName());
		monitService.insert(monit);
		return "redirect:/monit/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "monitId", required = false) String monitId) {
		System.out.println("id=" + monitId);
		monitService.deleteByPrimaryKey(monitId);
		return "redirect:/monit/index.do";
	}
	
	// 显示折线图
	@RequestMapping(value = "/getColumnLine")
	public String getColumnLine(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Query query)
			throws Exception {
		
		log.info("==querystart=="+query.getrDateStart());
		log.info("==getrDateEnd=="+query.getrDateEnd());
		log.info("==formatDateTommdd=="+DateUtils.formatDateTommdd(query.getrDateStart()));
		log.info("==formatDateTommdd=="+DateUtils.formatDateTommdd(query.getrDateEnd()));
		//只會查到自己的
		query.setqRder(user.getuName());
		
		
		// 1. 获得数据集合
		CategoryDataset dataset = getDataSetLine(query);
		log.info("==获得数据集合==");
		// 2. 创建柱状图
		
	    JFreeChart chart = ChartFactory.createLineChart("CCP監控统计报表", // 主标题的名称
                "日期", // X轴的标签
                "數量", // Y轴的标签
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
		return "monit/resultChart";
	}
	
	   // 获取折线图数据集
    private DefaultCategoryDataset getDataSetLine(Query query) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
		lsts = monitService.getMyMonitByQuery(query);
		
        Map<String, String> myMonit = new HashMap<String, String>();
        //phone.put("Apple", 7299);
        //phone.put("SAMSUNG", 6000);
        //phone.put("Meizu", 2698);
        //phone.put("Xiaomi", 2400);
        //key-sort

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
		
		for(Monit m : lsts) {			
			if(m.getTypeReg().equals("ul")) {

				list1.add(m.getUcl()+"_上限_"+DateUtils.formatDateTommdd(m.getrDate()));
				list2.add(m.getmVal()+"_"+ m.getHa().getProcStep()+"_"+DateUtils.formatDateTommdd(m.getrDate()));
			}else if(m.getTypeReg().equals("ll")) {				 
					list1.add(m.getLcl()+"_下限_"+DateUtils.formatDateTommdd(m.getrDate()));
					list2.add(m.getmVal()+"_"+ m.getHa().getProcStep()+"_"+DateUtils.formatDateTommdd(m.getrDate()));
				
			}else {				 
					list1.add(m.getUcl()+"_上限_"+DateUtils.formatDateTommdd(m.getrDate()));
					list2.add(m.getLcl()+"_下限_"+DateUtils.formatDateTommdd(m.getrDate()));
					list3.add(m.getmVal()+"_"+ m.getHa().getProcStep()+"_"+DateUtils.formatDateTommdd(m.getrDate()));
			}			
		}
		
		if(!list2.isEmpty()) {
			list1.addAll(list2);
		}
		
		if(!list3.isEmpty()) {
			list1.addAll(list3);
		}	
		
		System.out.println("====System.out.println(list); ===="+list1); 
		
		for(String i : list1) {	
			String[] tokens = i.split("_"); 			
			dataset.addValue(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);	
		}
 
		/*
		 * 
		 *        Set<String> set = myMonit.keySet();
        Object[] arr = set.toArray();
        Arrays.sort(arr);
        for (Object key : arr) {
            System.out.println(key + ": " + myMonit.get(key));
        }
		
		for(Monit m : lsts) {			
            if(m.getTypeReg().equals("ll")) {				
				//dataset.addValue(Integer.parseInt(m.getLcl()), "下限", DateUtils.formatDateTommdd(m.getrDate()));				
			}
		}
		
		for(Monit m : lsts) {			
            if(m.getTypeReg().equals("ulAndll")) {				
				//dataset.addValue(Integer.parseInt(m.getLcl()), "下限", DateUtils.formatDateTommdd(m.getrDate()));	
				//dataset.addValue(Integer.parseInt(m.getUcl()), "上限", DateUtils.formatDateTommdd(m.getrDate()));
				//dataset.addValue(Integer.parseInt(m.getLcl()), "下限", DateUtils.formatDateTommdd(m.getrDate()));
				//dataset.addValue(Integer.parseInt(m.getmVal()), m.getHa().getProcStep(),  DateUtils.formatDateTommdd(m.getrDate()));
			}
		}
		*/

		
 
        /*
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
        */
        
        return dataset;
    }
    
	//查詢	
	@RequestMapping("/queryChart")
	public String queryChart(HttpServletRequest request,Model model) {
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		return "monit/queryChart";		
	}
	@RequestMapping("/queryByparm")
	public String queryByparm(ModelMap model, Query query) {
		
		query.setqRder(user.getuName());

		// 其他狀態就撈公開跟協作資料
		lsts = monitService.getMyMonitByQuery(query);
		
		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		// 其他狀態就根據狀態處理
		return "monit/data";

	}
	

}
