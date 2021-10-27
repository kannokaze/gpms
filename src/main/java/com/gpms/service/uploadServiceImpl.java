package com.gpms.service;

import com.gpms.mapper.AccletterMapper;
import com.gpms.mapper.FilesMapper;
import com.gpms.mapper.StudentMapper;
import com.gpms.mapper.TeacherMapper;
import com.gpms.po.Accletter;
import com.gpms.po.Files;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import com.gpms.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class uploadServiceImpl{
	@Autowired
	StudentMapper studentMapper; 
	@Autowired
	FilesMapper filesMapper;
	@Autowired
	AccletterMapper accletterMapper;
	@Autowired
	TeacherMapper teacherMapper;

	private final String Path = "C://uploadServer/UserUpData";
	/**
	 * �ϴ���ʦ�ĵ�
	 * @param file
	 * @return
	 */
	public List<Teacher> readTeacherExcelFile(MultipartFile file) {
		//��������EXCEL����
		ReadExcel readExcel=new ReadExcel();
		//����excel����ȡ�ϴ����¼���
		List<Teacher> teaList = null;
		List<Teacher> sucessList =  new ArrayList<Teacher>();
		List<Teacher> failList = new ArrayList<Teacher>();
		try {
			teaList = readExcel.getExcelInfoTeacher(file);
			//�����Ѿ���excel�е�����ת����list������,�������Ϳ��Բ���list,���Խ��б��浽���ݿ�,������������,
			//�������ҵ���й�,���ﲻ�������ʾ��
			//���ݿ����	,���ݱ��Ѿ����ڲ���������
			for(Teacher s :teaList) {
				try {
					teacherMapper.insertSelective(s);
					sucessList.add(s);
				} catch (DuplicateKeyException e) {
					failList.add(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("����excel����е�����ʧ�ܣ�����");
		}		
		return failList;
	}
	/**
	 * �ϴ�ѧ���ĵ�
	 * @param file
	 * @return
	 */
	public List<Student> readStudentExcelFile(MultipartFile file) {
		//��������EXCEL����
		ReadExcel readExcel=new ReadExcel();
		//����excel����ȡ�ϴ����¼���
		List<Student> stuList = null;
		List<Student> sucessList =  new ArrayList<Student>();
		List<Student> failList = new ArrayList<Student>();
		stuList = readExcel.getExcelInfoStudent(file);
		//�����Ѿ���excel�е�����ת����list������,�������Ϳ��Բ���list,���Խ��б��浽���ݿ�,������������,
		//�������ҵ���й�,���ﲻ�������ʾ��
		//���ݿ����	,���ݱ��Ѿ����ڲ���������
		for(Student s :stuList) {
			try {
				studentMapper.insertSelective(s);
				sucessList.add(s);
			} catch (DuplicateKeyException e) {
				failList.add(s);
			}
		}

		return failList;
	}

	public List<Files> uploadFileByTeaNo(List<MultipartFile> mpfList,String title,String sign,String upper) {
		List<Files> files = new ArrayList<Files>();
		Files filesInfo = null;
		//		String path = "C://uploadServer/";  // �ļ��洢��Ŀ¼
		// ��ȡ���·������file_conf������·��
		String relativePath =  "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

		// ��֤�������洢·���Ƿ���ڣ��������ڣ����½��ļ���
		File serFile = new File(Path + relativePath);
		if (!serFile.exists()) {
			serFile.mkdirs();
		}


		// ѭ���ϴ��ļ�
		for (MultipartFile mpf : mpfList) {
			String originalFileName = mpf.getOriginalFilename(); // ��ȡԴ�ļ���
			// �������ļ���
			String newFileName = "F" + UUID.randomUUID().toString().replace("-", "").toUpperCase()
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			// ��װ����
			filesInfo = new Files();
			filesInfo.setFileTitle(title);
			filesInfo.setFileOriginalName(originalFileName);
			filesInfo.setFileSize(String.valueOf(mpf.getSize())); // ��λ��b��
			filesInfo.setFileType(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")+1, mpf.getOriginalFilename().length()));     // �ļ�����
			filesInfo.setFileNewName(newFileName);                        // �ļ�������
			filesInfo.setFileRelativePath("/UserUpData/"+relativePath + newFileName);    // �ļ����·��
			filesInfo.setFilePath(Path + relativePath + newFileName); // �ļ�����·��
			filesInfo.setFileSign(sign);
			filesInfo.setFileDelFlag(false);
			filesInfo.setFileUpper(upper);
			filesInfo.setFileUpdateTime(new Date());	
			// �洢�ļ�����¼�����ݿ�
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filesInfo.getFilePath()));
				filesMapper.uploadFilesByTeaNo(filesInfo); 
			} catch (IOException e) {
				return null;
			} finally{
			}
			files.add(filesInfo);
		}
		return files;
	}


	public Boolean deleteFileByTeaNo(String file_id,String teaNo) {
		boolean flag = false;
		if (filesMapper.changeFileFlag(file_id,teaNo) >= 1) {
			flag = true;
		}
		return flag;
	}

	public Boolean uploadAccltterByStuNo(List<MultipartFile> mpfList,String upper) {
		Boolean flag = false;

		Accletter accletter = null;

		//		String path = "C://uploadServer/upData";  // �ļ��洢��Ŀ¼
		// ��ȡ���·������file_conf������·��
		String relativePath =  "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

		// ��֤�������洢·���Ƿ���ڣ��������ڣ����½��ļ���
		File serFile = new File(Path + relativePath);
		if (!serFile.exists()) {
			serFile.mkdirs();
		}


		// ѭ���ϴ��ļ�
		for (MultipartFile mpf : mpfList) {
			String originalFileName = mpf.getOriginalFilename(); // ��ȡԴ�ļ���
			// �������ļ���
			String newFileName = "P" + UUID.randomUUID().toString().replace("-", "").toUpperCase()
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			// ��װ����
			accletter = new Accletter();
			accletter.setLalOriginalName(originalFileName);
			accletter.setLalSize(String.valueOf(mpf.getSize())); // ��λ��b��
			accletter.setLalType(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")+1, mpf.getOriginalFilename().length()));     // �ļ�����
			accletter.setLalNewName(newFileName);                        // �ļ�������
			accletter.setLalRelativePath("/UserUpData/"+relativePath + newFileName);    // �ļ����·��
			accletter.setLalPath(Path + relativePath + newFileName); // �ļ�����·��
			accletter.setLalDelFlag(false);
			accletter.setLalStuNo(upper);
			accletter.setLalUpdatetime(new Date());	
			// �洢�ļ�����¼�����ݿ�
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(accletter.getLalPath()));
				accletterMapper.insertAccltter(accletter); 
				flag = true;
			} catch (IOException e) {

			} finally{
			}
		}
		return flag;
	}



}