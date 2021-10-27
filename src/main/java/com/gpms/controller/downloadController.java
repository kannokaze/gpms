package com.gpms.controller;

import com.gpms.po.Company;
import com.gpms.po.Files;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import com.gpms.service.downloadServiceImpl;
import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class downloadController {

	@Autowired
	downloadServiceImpl downloadServiceImpl;

	/**
	 * 通知浏览器以attachment(下载方式)打开
	 * @param file
	 * @param httpServletRequest
	 * @return
	 * @throws IOException
	 */
	private ResponseEntity<byte[]> outStudent(String file,HttpServletRequest httpServletRequest,Student student) throws IOException {
		// 创建请求头对象
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputAllStudent(httpServletRequest,student)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	private ResponseEntity<byte[]> outCompany(String file,HttpServletRequest httpServletRequest,Company company) throws IOException {
		// 创建请求头对象
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputAllCompany(httpServletRequest,company)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	private ResponseEntity<byte[]> outTeacher(String file,HttpServletRequest httpServletRequest,Teacher teacher) throws IOException {
		// 创建请求头对象
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputAllTeacher(httpServletRequest,teacher)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	private ResponseEntity<byte[]> outInternshipTable(String stuNo,String file,HttpServletRequest httpServletRequest) throws IOException {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputInternshipTable(httpServletRequest, stuNo)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	@RequestMapping(value="/api/download/outputStudentExcel")
	public ResponseEntity<byte[]> downloadStudentInfoExcel(Student student,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFileName = new String("信息导出表.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// 通知浏览器以attachment(下载方式)打开
		return outStudent(downloadFileName, httpServletRequest, student);
	}

	@RequestMapping(value="/api/download/outputTeacherExcel")
	public ResponseEntity<byte[]> downloadTeacherInfoExcel(Teacher teacher,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFileName = new String("信息导出表.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// 通知浏览器以attachment(下载方式)打开
		return outTeacher(downloadFileName, httpServletRequest, teacher);
	}
	@RequestMapping(value="/api/download/outputCompanyExcel")
	public ResponseEntity<byte[]> downloadCompanyInfoExcel(Company company,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFileName = new String("信息导出表.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// 通知浏览器以attachment(下载方式)打开
		return outCompany(downloadFileName, httpServletRequest, company);
	}
	@RequestMapping(value="/api/stu/download/filesList")
	public void downloadList1(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request.getSession().getId());
		Student onlineUser = (Student) request.getSession().getAttribute("onlineUser");
		List<Files> filesList = null;
		if (null != onlineUser) {
			filesList = downloadServiceImpl.getDownloadList(onlineUser.getStuTeaNo());
			System.out.println(filesList);
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("访问成功");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}else{
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
			listObject.setMsg("您未登录，没有访问权限！");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

	}

	@RequestMapping(value="/api/tea/download/filesList")
	public void downloadList2(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request.getSession().getId());
		Teacher onlineUser = (Teacher) request.getSession().getAttribute("onlineUser");
		List<Files> filesList = null;
		if (null != onlineUser) {
			filesList = downloadServiceImpl.getDownloadList(onlineUser.getTeaNo());
			System.out.println(filesList);
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("访问成功");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}else{
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
			listObject.setMsg("您未登录，没有访问权限！");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

	}

	@RequestMapping(value="/api/download/StudentInternshipTable")
	public ResponseEntity<byte[]> downInternshipTable(String stuNo,HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 下载显示的文件名，解决中文名称乱码问题
		String downloadFileName = new String("实习鉴定表.docx".getBytes("UTF-8"), "iso-8859-1");
		// 通知浏览器以attachment(下载方式)打开
		return outInternshipTable(stuNo,downloadFileName, request);
		
		
	}

}
