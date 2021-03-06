package com.jorhen.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Plan;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.service.OptionService;
import com.jorhen.service.PcServiceI;
import com.jorhen.util.XwpfTUtil;

@Controller
@RequestMapping("/pc")
public class PcController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(PcController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private PcServiceI pcService;
    @Autowired
    OptionService optionService;

	List<Pc> lsts = null;
	String rder = null;

	public PcServiceI getPc() {
		return pcService;
	}

	public void setPc(PcServiceI pc) {
		pcService = pc;
	}

	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = pcService.getMyPc(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		log.info("=plan=uName==" + user.getuName());
		return "pc/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "pcId", required = false) String pcId) {
		Pc pc = pcService.selectPcById(pcId);
		model.addAttribute("pc", pc);
		return "pc/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Pc pc) {

		pc.setMder(user.getuName());
		int aa = pcService.updateByPrimaryKeySelective(pc);
		log.info("==aa==" + aa);
		return "redirect:/pc/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {
		return "pc/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Pc pc) {
		pc.setRder(user.getuName());
		pcService.insert(pc);
		return "redirect:/pc/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "pcId", required = false) String pcId) {
		System.out.println("id=" + pcId);
		pcService.deleteByPrimaryKey(pcId);
		return "redirect:/pc/index.do";
	}

	@RequestMapping("/queryPro")
	public String queryPro(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "pcId", required = false) String pcId) {

		lsts = pcService.getMyPc(user.getuName());
		model.addAttribute("lsts", lsts);
		log.info("=plan=uName==" + user.getuName());
		return "pc/datapro";

	}

	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Pc pc) {

		log.info("==pcId==" + pc.getPcId());		
		
		Pc pc1 = pcService.selectPcById(pc.getPcId());
		// 上传路径
		String path = request.getServletContext().getRealPath("/template/");
		// 上传文件名
		// String filename = file.getOriginalFilename();
		log.info("==pc1.getrDate()==" + pc1.getrDate());

		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("${rDate}", pc1.getrDate());
		params.put("${pUse}", pc1.getpUse());
		params.put("${sSpot}", pc1.getsSpot());
		params.put("${cObj}", pc1.getcObj());
		params.put("${notes}", pc1.getNotes());

		XwpfTUtil xwpfTUtil = new XwpfTUtil();	

		String fileName = "productUse_consumerObjects.docx";

		// 响应到客户端
		try {

			String filePath = path + "pc.docx";

			InputStream is = new FileInputStream(filePath);

			XWPFDocument doc = new XWPFDocument(is);

			xwpfTUtil.replaceInPara(doc, params);
			// 替換表格裡面的變數
			xwpfTUtil.replaceInTable(doc, params);

			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();

			doc.write(os);
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// 參數查詢
	@RequestMapping(value = "/queryByparm")
	public String queryByparm(ModelMap model, Query query) {
		
		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			query.setqRder(user.getuName());
			query.setqPstatus("");
			// lsts = haService.getMyHa(user.getuName());
			lsts = pcService.getMyPcByQuery(query);
			PageInfo<Pc> pageInfo = new PageInfo<Pc>(lsts);
			// lsts = pageInfo.getList();
			model.addAttribute("lsts", lsts);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("options", this.optionService.catsTypeOption(null));
			model.addAttribute("planStatus", this.optionService.planStatusOption());
			return "pc/mydata";
		} else {
			lsts = pcService.getMyPcByQuery(query);
			PageInfo<Pc> pageInfo = new PageInfo<Pc>(lsts);
			// lsts = pageInfo.getList();
			model.addAttribute("lsts", lsts);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("options", this.optionService.catsTypeOption(null));
			model.addAttribute("planStatus", this.optionService.planStatusOption());
			return "pc/data";
		}

	
	}
	
	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Pc pc) {

		pc = pcService.selectPcById(pc.getPcId());
		model.addAttribute("pc", pc);
		return "pc/coworkpw";
	}

}
