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
	 * ֪ͨ�������attachment(���ط�ʽ)��
	 * @param file
	 * @param httpServletRequest
	 * @return
	 * @throws IOException
	 */
	private ResponseEntity<byte[]> outStudent(String file,HttpServletRequest httpServletRequest,Student student) throws IOException {
		// ��������ͷ����
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:�����������ݣ�������ļ����أ�
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputAllStudent(httpServletRequest,student)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	private ResponseEntity<byte[]> outCompany(String file,HttpServletRequest httpServletRequest,Company company) throws IOException {
		// ��������ͷ����
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:�����������ݣ�������ļ����أ�
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputAllCompany(httpServletRequest,company)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	private ResponseEntity<byte[]> outTeacher(String file,HttpServletRequest httpServletRequest,Teacher teacher) throws IOException {
		// ��������ͷ����
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file);
		// application/octet-stream:�����������ݣ�������ļ����أ�
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
		// application/octet-stream:�����������ݣ�������ļ����أ�
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(downloadServiceImpl.outputInternshipTable(httpServletRequest, stuNo)),
				headers, HttpStatus.CREATED);

		return responseEntity;
	}

	@RequestMapping(value="/api/download/outputStudentExcel")
	public ResponseEntity<byte[]> downloadStudentInfoExcel(Student student,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// ������ʾ���ļ������������������������
		String downloadFileName = new String("��Ϣ������.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// ֪ͨ�������attachment(���ط�ʽ)��
		return outStudent(downloadFileName, httpServletRequest, student);
	}

	@RequestMapping(value="/api/download/outputTeacherExcel")
	public ResponseEntity<byte[]> downloadTeacherInfoExcel(Teacher teacher,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// ������ʾ���ļ������������������������
		String downloadFileName = new String("��Ϣ������.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// ֪ͨ�������attachment(���ط�ʽ)��
		return outTeacher(downloadFileName, httpServletRequest, teacher);
	}
	@RequestMapping(value="/api/download/outputCompanyExcel")
	public ResponseEntity<byte[]> downloadCompanyInfoExcel(Company company,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException{
		// ������ʾ���ļ������������������������
		String downloadFileName = new String("��Ϣ������.xlsx".getBytes("UTF-8"), "iso-8859-1");
		// ֪ͨ�������attachment(���ط�ʽ)��
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
			listObject.setMsg("���ʳɹ�");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}else{
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
			listObject.setMsg("��δ��¼��û�з���Ȩ�ޣ�");
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
			listObject.setMsg("���ʳɹ�");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}else{
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_ERROR_NO_LOGIN_OR_TIMEOUT);
			listObject.setMsg("��δ��¼��û�з���Ȩ�ޣ�");
			listObject.setData(filesList);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
		}

	}

	@RequestMapping(value="/api/download/StudentInternshipTable")
	public ResponseEntity<byte[]> downInternshipTable(String stuNo,HttpServletRequest request,HttpServletResponse response) throws IOException {
		// ������ʾ���ļ������������������������
		String downloadFileName = new String("ʵϰ������.docx".getBytes("UTF-8"), "iso-8859-1");
		// ֪ͨ�������attachment(���ط�ʽ)��
		return outInternshipTable(stuNo,downloadFileName, request);
		
		
	}

}
