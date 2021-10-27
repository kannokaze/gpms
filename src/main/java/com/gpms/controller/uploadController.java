package com.gpms.controller;

import com.gpms.po.Files;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import com.gpms.service.uploadServiceImpl;
import com.gpms.util.JsonUtils;
import com.gpms.util.ListObject;
import com.gpms.util.ResponseUtils;
import com.gpms.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;



@Controller
public class uploadController {

	/**
	 * ���ѧ���ķ���  excel ��صĲ���   �ܹ������ݲ��뵽���ݿ� 
	 */
	@Autowired
	uploadServiceImpl uploadServiceImpl;
	
	
	
	/**
	 * �ϴ�ѧ����
	 * @param file
	 * @param request
	 * @return
	 */
	//@ResponseBody
	@RequestMapping(value="api/upload/uploadExcelStu",method= {RequestMethod.POST,RequestMethod.OPTIONS})
	public void DoExcel1(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		List<Student> readResult =null;
		
		try {
			readResult = uploadServiceImpl.readStudentExcelFile(file);
		} catch (Exception e) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_ERROR);
			listObject.setMsg("ϵͳ�޷���ȡ�����ļ�����ʹ���ļ�ģ�嵼��!");
			listObject.setData(readResult);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("��ȡ�ɹ�!");
		listObject.setData(readResult);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	/**
	 * �ϴ���ʦ��
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="api/upload/uploadExcelTea",method= {RequestMethod.POST,RequestMethod.OPTIONS})
	public void DoExcel2(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,HttpServletResponse response
			) {
		List<Teacher> readResult =null;
		try {
			readResult = uploadServiceImpl.readTeacherExcelFile(file);
			System.out.println("canshu :"+readResult);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("upload falure");
		}
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("��ӳɹ�!");
		listObject.setData(readResult);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
	
	
	//�ϴ��ĵ�
	@RequestMapping(value="/api/upload/uploadFiles")
	public void uploadFile() {
		// TODO Auto-generated method stub
		
	}
	
    @RequestMapping(value ="/api/upload/uploadFile", method = RequestMethod.POST)
    public void uploadFile(MultipartHttpServletRequest request,HttpServletResponse response,String title,String sign) {
    	System.out.println(request.getAttribute("title")+":"+request.getAttribute("sign"));
    	System.out.println(title+":"+sign);
    	
    	String teaNo = (String) ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();

        if (request.getMultiFileMap().get("file").get(0).getSize()/1024 > 102400) {
    		ListObject listObject = new ListObject();
    		listObject.setCode(StatusCode.CODE_ERROR);
    		listObject.setMsg("�ļ�����100M!");
//    		listObject.setData();
    		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
    		return;
		}
        
        List<Files> result = uploadServiceImpl.uploadFileByTeaNo(request.getMultiFileMap().get("file"),title,sign,teaNo);
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("���ʳɹ�!");
		listObject.setData(result);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));	

    }
    
    
    @RequestMapping(value="/api/tea/upload/delete")
    public void deleteFile(HttpServletRequest request,HttpServletResponse response,String fileId) {
    	String teaNo = (String) ((Teacher) request.getSession().getAttribute("onlineUser")).getTeaNo();
//    	String teaNo = "1644";
		if (uploadServiceImpl.deleteFileByTeaNo(fileId, teaNo)) {
			ListObject listObject = new ListObject();
			listObject.setCode(StatusCode.CODE_SUCCESS);
			listObject.setMsg("ɾ���ɹ�!");
//			listObject.setData(result);
			ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
			return;
		}
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("ɾ��ʧ��!");
//		listObject.setData(result);
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}
    
    
    @RequestMapping(value ="/api/stu/upload/uploadAccletter", method = RequestMethod.POST)
    public void uploadAccletter(MultipartHttpServletRequest request,HttpServletResponse response) {
    	System.out.println(request.getSession().getAttribute("onlineUser"));
    	Student onlineUser = (Student) request.getSession().getAttribute("onlineUser");
        
        if (request.getMultiFileMap().get("file").get(0).getSize()/1024 > 102400) {
    		ListObject listObject = new ListObject();
    		listObject.setCode(StatusCode.CODE_ERROR);
    		listObject.setMsg("�ļ�����100M!");
//    		listObject.setData();
    		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
    		return;
		}
        
        Boolean result = uploadServiceImpl.uploadAccltterByStuNo(request.getMultiFileMap().get("file"),onlineUser.getStuNo());
        
        if (result) {
    		ListObject listObject = new ListObject();
    		listObject.setCode(StatusCode.CODE_SUCCESS);
    		listObject.setMsg("�ϴ��ɹ�!");
    		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
    		return;
		}
	
		ListObject listObject = new ListObject();
		listObject.setCode(StatusCode.CODE_ERROR);
		listObject.setMsg("�ϴ�ʧ��!");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
    }

}
