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

import com.jorhen.domain.Cat;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.domain.Team;
import com.jorhen.service.CatServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.service.PsServiceI;
import com.jorhen.util.XwpfTUtil;

@Controller
@RequestMapping("/ps")
public class PsController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(PsController.class);

	// 處理業務邏輯的psService
	@Autowired
	private PsServiceI psService;
    @Autowired
    OptionService optionService;
    @Autowired
    CatServiceI catService;
	
	List<Ps> lsts = null;
	String rder = null;		

	public PsServiceI getPs() {
		return psService;
	}

	public void setPs(PsServiceI ps) {
		psService = ps;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = psService.getMyPs(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		
		return "ps/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "psId", required = false) String psId) {
		Ps ps = psService.selectPsById(psId);
		model.addAttribute("ps", ps);
		model.addAttribute("options",this.optionService.catsTypeOption(null));		
		return "ps/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Ps ps) {
		
		ps.setMder(user.getuName());
		int aa = psService.updateByPrimaryKeySelective(ps);
		log.info("==aa==" + aa);
		return "redirect:/ps/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("options",this.optionService.catsTypeOption(null));		
		return "ps/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Ps ps) {
		ps.setRder(user.getuName());
		psService.insert(ps);
		return "redirect:/ps/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "psId", required = false) String psId) {
		System.out.println("id=" + psId);
		psService.deleteByPrimaryKey(psId);
		return "redirect:/ps/index.do";
	}
	
	@RequestMapping("/queryPro")
	public String queryPro(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "psId", required = false) String psId) {

		lsts = psService.getMyPs(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options",this.optionService.catsTypeOption(null));
		return "ps/datapro";

	}

	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Ps ps) {

		log.info("==pcId==" + ps.getPsId());		
		
		Ps ps1 = psService.selectPsById(ps.getPsId());
		// 上传路径
		String path = request.getServletContext().getRealPath("/template/");
		// 上传文件名
		// String filename = file.getOriginalFilename();
		log.info("==ps1.getrDate()==" + ps1.getrDate());
		
		Cat cat = catService.getCatById(ps1.getcId());	

		Map<String, Object> params = new HashMap<String, Object>();	
		params.put("${rDate}", ps1.getrDate());
		params.put("${cId}", cat.getcName());
		params.put("${pName}", ps1.getpName());
		params.put("${matrlM}", ps1.getMatrlM());
		params.put("${matrlO}", ps1.getMatrlO());
		params.put("${fdAdd}", ps1.getFdAdd());
		
		params.put("${prcsAids}", ps1.getPrcsAids());
		params.put("${matrl}", ps1.getMatrl());
		params.put("${pdtFt}", ps1.getPdtFt());
		params.put("${pdtMd}", ps1.getPdtMd());
		params.put("${pmDesc}", ps1.getPmDesc());
		
		params.put("${stMd}", ps1.getStMd());
		params.put("${sLife}", ps1.getsLife());
		params.put("${notes}", ps1.getNotes());
		
		

		XwpfTUtil xwpfTUtil = new XwpfTUtil();	

		String fileName = "CharacterStorage_tranMethods.docx";

		// 响应到客户端
		try {

			String filePath = path + "ps.docx";

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

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			lsts = psService.getMyPs(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = psService.getMyPsByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "ps/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "ps/data";
		}
	}
	
	@RequestMapping(value = "/cowork")
	public String cowork(ModelMap model, Ps ps) {

		ps = psService.selectPsById(ps.getPsId());
		model.addAttribute("ps", ps);
		return "ps/coworkpw";
	}



}
