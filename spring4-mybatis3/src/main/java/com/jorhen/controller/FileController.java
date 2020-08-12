package com.jorhen.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jorhen.domain.File;
import com.jorhen.domain.FileInfo;
import com.jorhen.domain.Query;
import com.jorhen.service.FileServiceI;
import com.jorhen.service.OptionService;
import com.jorhen.util.DateUtils;

@Controller
@RequestMapping("/fchart")
public class FileController extends BaseController {

	// 設置log
	private static Logger log = Logger.getLogger(FileController.class);

	// 處理業務邏輯的pcService
	@Autowired
	private FileServiceI fileService;
	@Autowired
	OptionService optionService;

	List<File> lsts = null;
	String rder = null;

	public FileServiceI getFile() {
		return fileService;
	}

	public void setPc(FileServiceI file) {
		fileService = file;
	}

	// 管理介面
	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		lsts = fileService.getMyFile(user.getuName());
		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());
		log.info("=plan=uName==" + user.getuName());
		return "fchart/mydata";
	}

	// 更新介面
	@RequestMapping("/update")
	public String update(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "fileId", required = false) String fileId) {
		File file = fileService.selectFileById(fileId);
		model.addAttribute("file", file);
		return "fchart/update";
	}

	// 更新執行
	@RequestMapping("/doUpdate")
	public String doUpdate(HttpServletRequest request, ModelMap modelMap, File file) {

		file.setMder(user.getuName());
		int aa = fileService.updateByPrimaryKeySelective(file);
		log.info("==aa==" + aa);
		return "redirect:/fchart/index.do";

	}

	// 新增導引
	@RequestMapping("/add")
	public String add(Model model) {
		return "fchart/add";
	}

	// 新增執行，可以無限新增
	@RequestMapping("/doAdd")
	public String doAdd(HttpServletRequest request, ModelMap modelMap, File file) {
		file.setRder(user.getuName());
		fileService.insert(file);
		return "redirect:/fchart/index.do";

	}

	// 刪除
	@RequestMapping("/del")
	public String del(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "fileId", required = false) String fileId) {
		System.out.println("id=" + fileId);
		fileService.deleteByPrimaryKey(fileId);
		return "redirect:/fchart/index.do";
	}

	@RequestMapping("/queryPro")
	public String queryPro(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "fileId", required = false) String fileId) {

		lsts = fileService.getMyFile(user.getuName());
		model.addAttribute("lsts", lsts);
		log.info("=plan=uName==" + user.getuName());
		return "fchart/datapro";

	}

	// 參數查詢
	@RequestMapping(value = "/queryByparm")
	public String queryByparm(ModelMap model, Query query) {

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人，就自己資料全撈
			lsts = fileService.getMyFile(user.getuName());
		} else {
			// 其他狀態就撈公開跟協作資料
			lsts = fileService.getMyFileByQuery(query);
		}

		model.addAttribute("lsts", lsts);
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (query.getqPstatus().equals("fprivate")) {
			// 如果私人就進可以修刪功能頁面
			return "fchart/mydata";
		} else {
			// 其他狀態就根據狀態處理
			return "fchart/data";
		}
	}

	// 定位到上传单个文件界面 /hello/upload
	@RequestMapping(value = "/upload")
	public String showUploadPage() {
		return "fchart/uploadFile"; // view文件夹下的上传单个文件的页面
	}

	/**
	 * 上传单个文件操作
	 * 
	 * @param RequestParam
	 *            括号中的参数名file和表单的input节点的name属性值一致
	 * @return
	 */
	@RequestMapping(value = "/doUpload")
	public String doUploadFile(HttpServletRequest request, @ModelAttribute FileInfo fileinfo, ModelMap model,
			File file) {
		
		model.addAttribute("options", this.optionService.catsTypeOption(null));
		model.addAttribute("planStatus", this.optionService.planStatusOption());

		if (fileinfo.getFile().isEmpty()) {
			return "fchart/data";
		} else {
			// 上传路径
			// String path = request.getServletContext().getRealPath("/images/");
			log.info("=FileId==" + file.getFileId());
			file = fileService.selectFileById(file.getFileId());
			String path = "d:\\temp\\file\\";

			log.info("=path==" + path);
			// 上传文件名
			// String filename = fileinfo.getFile().getOriginalFilename();
			String filename = DateUtils.formatDateToyyyymmdd(file.getrDate()) + "_" + file.getFileId() + "_"
					+fileinfo.getFile().getOriginalFilename();
			log.info("=filename==" + filename);
			java.io.File filepath = new java.io.File(path, filename);
			if (!filepath.getParentFile().exists())
				filepath.getParentFile().mkdirs();
			try {

				// 将文件保存到一个目标文件中
				fileinfo.getFile().transferTo(new java.io.File(path + java.io.File.separator + filename));
				// 将上传的文件信息添加进 model
				file.setDownload(filename);
				fileService.updateByPrimaryKeySelective(file);
				file = fileService.selectFileById(file.getFileId());
				
				model.addAttribute("file", file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			// 跳转至下载页面
			return "fchart/mydata";
		}

		// return "fchart/mydata"; // 上传成功则跳转至此success.jsp页面
	}

	// 文件下载
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename)
			throws Exception {
		// 下载路径
		// String path = request.getServletContext().getRealPath("/images/");
		String path = "d:\\temp\\file\\";
		java.io.File file = new java.io.File(path + java.io.File.separator + filename);
		HttpHeaders headers = new HttpHeaders();
		// 解决中文乱码
		String downloadfile = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		// 以下载方式打开文件
		headers.setContentDispositionFormData("attachment", downloadfile);
		// 二进制流
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

	}

}
