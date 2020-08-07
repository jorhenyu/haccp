package com.jorhen.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.Team;
import com.jorhen.domain.TeamForm;
import com.jorhen.service.TeamServiceI;
import com.jorhen.util.ExportWordHelper;
import com.jorhen.util.XwpfTUtil;

@Controller
@RequestMapping("/team")
// @SessionAttributes(value={"lstUsers","urlName"},types={List.class,String.class})
public class TeamController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(TeamController.class);

	// 處理業務邏輯的teamService
	@Autowired
	private TeamServiceI teamService;
	List<Team> lstTeams = null;

	public TeamServiceI getTeam() {
		return teamService;
	}

	public void setTeam(TeamServiceI team) {
		teamService = team;
	}

	// 管理介面
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, ModelMap modelMap, Team team) {

		lstTeams = teamService.getAllTeam(user.getuName());
		modelMap.addAttribute("lstTeams", lstTeams);

		return "team/data";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "teamId", required = false) String teamId) {
		Team team = teamService.getTeamById(teamId);
		modelMap.addAttribute("team", team);
		return "team/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, Team team) {
		team.setMder(user.getuName());
		int aa = teamService.updateByPrimaryKeySelective(team);
		log.info("==aa==" + aa);
		return "redirect:/team/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add() {
		return "team/add";
	}

	// 新增執行
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("teamForm") TeamForm teamForm, Team team1) {
		/*
		 * if (StringUtils.isNotBlank(team.getPlanId())) {
		 * team.setRder(user.getuName()); teamService.addTeam(team); return
		 * "redirect:/team/index.do"; } else { // 向BindingResult添加類別已存在的校驗錯誤
		 * bindingResult.rejectValue("planId", "專案ID不存在", "專案ID不存在");
		 * modelMap.addAttribute("team", team); return "team/add"; }
		 */
		System.out.println("===PlanId===="+team1.getPlanId());
		String planId = team1.getPlanId();
		
		List<Team> teams = teamForm.getTeams();

		if (null != teams && teams.size() > 0) {			
			for (Team team : teams) {
				team.setPlanId(planId);
				team.setRder(user.getuName()); 
				teamService.addTeam(team);				
			}
		}		

		return "redirect:/team/index.do";

	}

	// 類別刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "teamId", required = false) String teamId) {
		System.out.println("id=" + teamId);
		teamService.deleteByPrimaryKey(teamId);
		return "redirect:/team/index.do";
	}
	
	@RequestMapping("/queryPro")
	public String queryPro(HttpServletRequest request, ModelMap model) {

		lstTeams = teamService.getTeamByPlanIdDistinct(user.getuName());
		model.addAttribute("lsts", lstTeams);		
		return "team/datapro";

	}

	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Team team) {

		log.info("==getPlanId==" + team.getPlanId());	
		List<Team> wgylist1 = new ArrayList<Team>();
		
		wgylist1 = teamService.selectTeamByPlanId(team.getPlanId());
		// 上传路径
		String path = request.getServletContext().getRealPath("/template/");
		// 上传文件名
		// String filename = file.getOriginalFilename();
		//log.info("==team1.getrDate()==" + team1.getrDate());
		
		
		List<Map<String,String>> lzlist1 = null;
		String dateStr = null;

		Map<String, Object> params = new HashMap<String, Object>();		
		//params.put("${rDate}", team1.getrDate());
		/*
		params.put("${pUse}", pc1.getpUse());
		params.put("${sSpot}", pc1.getsSpot());
		params.put("${cObj}", pc1.getcObj());
		params.put("${notes}", pc1.getNotes());
		*/

		XwpfTUtil xwpfTUtil = new XwpfTUtil();	

		String fileName = "team.docx";

		// 响应到客户端
		try {

			String filePath = path + "team.docx";

			InputStream is = new FileInputStream(filePath);

			XWPFDocument doc = new XWPFDocument(is);

			xwpfTUtil.replaceInPara(doc, params);
			// 替換表格裡面的變數
			xwpfTUtil.replaceInTable(doc, params);
			
			ExportWordHelper ewp =new ExportWordHelper();
            ewp.ExportWord(wgylist1, lzlist1,doc,dateStr); 

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

}
