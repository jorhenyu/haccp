package com.jorhen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Ccp;
import com.jorhen.service.CcpServiceI;

@Controller
@RequestMapping("/ccp")
public class CcpController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(CcpController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private CcpServiceI ccpService;
	
	List<Ccp> lsts = null;
	String rder = null;		

	public CcpServiceI getCcp() {
		return ccpService;
	}

	public void setCcp(CcpServiceI ccp) {
		ccpService = ccp;
	}


	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = ccpService.getMyCcp(user.getuName());
		model.addAttribute("lsts", lsts);				
		log.info("=plan=uName==" + user.getuName());
		return "ccp/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "ccpId", required = false) String ccpId) {
		Ccp ccp = ccpService.selectCcpById(ccpId);
		model.addAttribute("ccp", ccp);				
		return "ccp/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Ccp ccp) {
		
		ccp.setMder(user.getuName());
		int aa = ccpService.updateByPrimaryKeySelective(ccp);
		log.info("==aa==" + aa);
		return "redirect:/ccp/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model) {	
		String jt = request.getParameter("jt");
		System.out.println("=jt=="+jt);
		if(jt.equals("3")) {
			return "ccp/add3t";
		}else {
			return "ccp/add4t";
		}
		
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, Ccp ccp) {
		ccp.setRder(user.getuName());
		ccpService.insert(ccp);
		return "redirect:/ccp/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "ccpId", required = false) String ccpId) {
		System.out.println("id=" + ccpId);
		ccpService.deleteByPrimaryKey(ccpId);
		return "redirect:/ccp/index.do";
	}
	


}
