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
	 * 上传导师文档
	 * @param file
	 * @return
	 */
	public List<Teacher> readTeacherExcelFile(MultipartFile file) {
		//创建处理EXCEL的类
		ReadExcel readExcel=new ReadExcel();
		//解析excel，获取上传的事件单
		List<Teacher> teaList = null;
		List<Teacher> sucessList =  new ArrayList<Teacher>();
		List<Teacher> failList = new ArrayList<Teacher>();
		try {
			teaList = readExcel.getExcelInfoTeacher(file);
			//至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
			//和你具体业务有关,这里不做具体的示范
			//数据库插入	,数据表已经存在部分则跳过
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
			System.err.println("接受excel表格中的数据失败！！！");
		}		
		return failList;
	}
	/**
	 * 上传学生文档
	 * @param file
	 * @return
	 */
	public List<Student> readStudentExcelFile(MultipartFile file) {
		//创建处理EXCEL的类
		ReadExcel readExcel=new ReadExcel();
		//解析excel，获取上传的事件单
		List<Student> stuList = null;
		List<Student> sucessList =  new ArrayList<Student>();
		List<Student> failList = new ArrayList<Student>();
		stuList = readExcel.getExcelInfoStudent(file);
		//至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		//和你具体业务有关,这里不做具体的示范
		//数据库插入	,数据表已经存在部分则跳过
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
		//		String path = "C://uploadServer/";  // 文件存储的目录
		// 获取相对路径，由file_conf、额外路径
		String relativePath =  "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

		// 验证服务器存储路径是否存在，若不存在，则新建文件夹
		File serFile = new File(Path + relativePath);
		if (!serFile.exists()) {
			serFile.mkdirs();
		}


		// 循环上传文件
		for (MultipartFile mpf : mpfList) {
			String originalFileName = mpf.getOriginalFilename(); // 获取源文件名
			// 生成新文件名
			String newFileName = "F" + UUID.randomUUID().toString().replace("-", "").toUpperCase()
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			// 组装数据
			filesInfo = new Files();
			filesInfo.setFileTitle(title);
			filesInfo.setFileOriginalName(originalFileName);
			filesInfo.setFileSize(String.valueOf(mpf.getSize())); // 单位（b）
			filesInfo.setFileType(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")+1, mpf.getOriginalFilename().length()));     // 文件类型
			filesInfo.setFileNewName(newFileName);                        // 文件新名字
			filesInfo.setFileRelativePath("/UserUpData/"+relativePath + newFileName);    // 文件相对路径
			filesInfo.setFilePath(Path + relativePath + newFileName); // 文件物理路径
			filesInfo.setFileSign(sign);
			filesInfo.setFileDelFlag(false);
			filesInfo.setFileUpper(upper);
			filesInfo.setFileUpdateTime(new Date());	
			// 存储文件并记录到数据库
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

		//		String path = "C://uploadServer/upData";  // 文件存储的目录
		// 获取相对路径，由file_conf、额外路径
		String relativePath =  "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

		// 验证服务器存储路径是否存在，若不存在，则新建文件夹
		File serFile = new File(Path + relativePath);
		if (!serFile.exists()) {
			serFile.mkdirs();
		}


		// 循环上传文件
		for (MultipartFile mpf : mpfList) {
			String originalFileName = mpf.getOriginalFilename(); // 获取源文件名
			// 生成新文件名
			String newFileName = "P" + UUID.randomUUID().toString().replace("-", "").toUpperCase()
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
			// 组装数据
			accletter = new Accletter();
			accletter.setLalOriginalName(originalFileName);
			accletter.setLalSize(String.valueOf(mpf.getSize())); // 单位（b）
			accletter.setLalType(mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")+1, mpf.getOriginalFilename().length()));     // 文件类型
			accletter.setLalNewName(newFileName);                        // 文件新名字
			accletter.setLalRelativePath("/UserUpData/"+relativePath + newFileName);    // 文件相对路径
			accletter.setLalPath(Path + relativePath + newFileName); // 文件物理路径
			accletter.setLalDelFlag(false);
			accletter.setLalStuNo(upper);
			accletter.setLalUpdatetime(new Date());	
			// 存储文件并记录到数据库
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