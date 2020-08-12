package com.jorhen.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jorhen.domain.Ccp;
import com.jorhen.domain.File;
import com.jorhen.domain.Ha;
import com.jorhen.domain.Haccp;
import com.jorhen.domain.Pc;
import com.jorhen.domain.Plan;
import com.jorhen.domain.Ps;
import com.jorhen.domain.Query;
import com.jorhen.domain.Team;
import com.jorhen.service.CcpServiceI;
import com.jorhen.service.FileServiceI;
import com.jorhen.service.HaServiceI;
import com.jorhen.service.HaccpServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.service.PcServiceI;
import com.jorhen.service.PlanServiceI;
import com.jorhen.service.PsServiceI;
import com.jorhen.service.TeamServiceI;
import com.jorhen.service.UserServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class PlanCopyTest {
	
	// 設置log
	private static Logger log = Logger.getLogger(PlanCopyTest.class); 
	// 處理業務邏輯的planService
	@Autowired
	private PlanServiceI planService;
	@Autowired
	OptionService optionService;

	@Autowired
	private UserServiceI userService;

	@Autowired
	private TeamServiceI teamService;

	@Autowired
	private PsServiceI psService;

	@Autowired
	private PcServiceI pcService;

	@Autowired
	private HaServiceI haService;
	
	@Autowired
	private FileServiceI fileService;

	@Autowired
	private CcpServiceI ccpService;
	
	@Autowired
	private HaccpServiceI haccpService;
	
	List<Plan> lsts = null;
	
	@Test	    
	public  void doCopy() {
		Plan plan = new Plan();
		//String planId = plan.getpId();
		String planId = "550c523c91614eeeacdc7937cccf6e2a";//old
		log.info("1==planId==" + planId);

		// 1.取得要複製plan專案表單
		plan = planService.getById(planId);
		log.info("=2=getpName==" + plan.getpName());
		
		//String newPlanId = "66908277ad404b2a8a4e37c287b72f91";
		
		plan.setRder("test");
		// plan.setpName("複製_"+plan.getpName());
		// 將舊planid放到專案名稱中
		plan.setpName(planId);
		// 2.產生新專案
		planService.add(plan);

		// 使用舊planId找新專案ID
		Query query = new Query();
		query.setqPname(planId);
		String newPlanId = null;
		lsts = planService.getMyPlanByQuery(query);
		// 應該只有一筆
		for (Plan p : lsts) {
			newPlanId = p.getpId();  //new
			log.info("==newPlanId==" + newPlanId);
		}
		

		// 先copy team表單
		List<Team> lstTeam = teamService.selectTeamByPlanId(planId);
		// 會有多筆
		for (Team t : lstTeam) {
			t.setPlanId(newPlanId); // 只換planId,其他都一樣
			teamService.addTeam(t);
		}

		// copy ps表單

		Ps ps = psService.getMyPsBypLd(planId);
		ps.setPlanId(newPlanId);
		psService.insert(ps);

		// copy pc表單
		Pc pc = pcService.getMyPcBypLd(planId);
		pc.setPlanId(newPlanId);
		pcService.insert(pc);
		
		// copy file表單
		File file = fileService.getMyFileBypLd(planId);
        file.setPlanId(newPlanId);
        fileService.insert(file);
	
		
		// copy ha表單 多筆
		List<Ha> lstHa = haService.selectHaByPlanId(planId);

		// 新增 Ha, 會有多筆
		for (Ha h : lstHa) {
			h.setPlanId(newPlanId); // 只換planId,其他都一樣，會產生新haId
			haService.insert(h);
		}
		
	
		
	
		
		
		
		// copy ccp表單 多筆，取舊的CCP資料
		List<Ccp> lstCcp = ccpService.selectCcpByPlanId(planId);
		
		// 先插入新的CCP,更換planId
		for (Ccp c : lstCcp) {
			c.setPlanId(newPlanId); // 只換planId,其他都一樣
			log.info("=s=HaId==" +c.getHaId());
			ccpService.insert(c);
		}
		
		
		

		// 新的 Ha，取其 haId
		List<Ha> lstHaNew = haService.selectHaByPlanId(newPlanId);
		// 新的 CCP，置換 新haId
		List<Ccp> lstCcpNew = ccpService.selectCcpByPlanId(newPlanId);

		for (int i = 0; i < lstHaNew.size(); i++) {
			Ha newHa = lstHaNew.get(i);
			for (int j = 0; j < lstCcpNew.size(); j++) {
				if (lstHaNew.get(i).getProcStep().equals(lstCcpNew.get(j).getHa().getProcStep())) {
					Ccp newCcp = lstCcpNew.get(j);
					newCcp.setHaId(newHa.getHaId());
					ccpService.updateByPrimaryKeySelective(newCcp);
				}

			}
		}
		
		
		// copy haccp表單 多筆，取舊的haccp資料
		List<Haccp> lstHaccp = haccpService.selectHaccpByPlanId(planId);
		
		// 先插入新的haccp,更換planId
		for (Haccp h : lstHaccp) {
			h.setPlanId(newPlanId); // 只換planId,其他都一樣
			log.info("=s=HaccpId==" +h.getHaccpId());
			haccpService.insert(h);
		}
		
		// copy haccp表單 多筆

		// 新的 Ha，取其 hacId
		//List<Ha> lstHaNew = haService.selectHaByPlanId(newPlanId);
		// 新的 haccp，置換 新haId
		List<Haccp> lstHaccpNew = haccpService.selectHaccpByPlanId(newPlanId);

		for (int i = 0; i < lstHaNew.size(); i++) {
			Ha newHa = lstHaNew.get(i);
			for (int j = 0; j < lstHaccpNew.size(); j++) {
				if (lstHaNew.get(i).getProcStep().equals(lstHaccpNew.get(j).getHa().getProcStep())) {
					Haccp newHaccp = lstHaccpNew.get(j);
					newHaccp.setHaId(newHa.getHaId());
					haccpService.updateByPrimaryKeySelective(newHaccp);
				}

			}
		}
		
		 
		//return "plan/index";
	}



}
