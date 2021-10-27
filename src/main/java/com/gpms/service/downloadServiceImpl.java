package com.gpms.service;

import com.gpms.mapper.*;
import com.gpms.mapper.complex.InternshipByContentMapper;
import com.gpms.po.*;
import com.gpms.po.complex.InternshipByContent;
import com.gpms.util.CustomXWPFDocument;
import com.gpms.util.WordUtil;
import com.gpms.util.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class downloadServiceImpl {

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	TeacherMapper teacherMapper;

	@Autowired
	FilesMapper filesMapper;

	@Autowired
	InternshipByContentMapper internshipByContentMapper;

	@Autowired
	JournalMapper journalMapper;

	@Autowired
	AchievementMapper achievementMapper;

	@Autowired
	RelationStuComMapper relationStuComMapper;

	@Autowired
	AccletterMapper accletterMapper;

	// ��ȡģ���ַ��������ַ
	private final String TemplatePath = "C:\\uploadServer\\Template";
	//��ȡ�ļ���������ʱ�ļ���ַ
	private final String tempPath = "C:\\uploadServer\\temp";

	public downloadServiceImpl() {
		File tempPaths = new File(tempPath);
		if (!tempPaths.exists()) {
			tempPaths.mkdirs();
		}
	}


	public List<Files> getDownloadList(String fileUpper) {
		return filesMapper.selectByUpper(fileUpper);	
	}


	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputAllStudent(HttpServletRequest request,Student student) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectStudentInfo1(student);	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}


	public File outputAllCompany(HttpServletRequest httpServletRequest,
			Company company) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		List<Company> allCom = companyMapper.selectAllByKey(company);
		return writeExcel.outputCompanyExcel(allCom, "��ҵ��Ϣ", tempPath, "��ҵ��Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}


	public File outputAllTeacher(HttpServletRequest httpServletRequest,
			Teacher teacher) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		List<Teacher> allTea = teacherMapper.selectAllByKey(teacher);
		return writeExcel.outputTeacherExcel(allTea, "��ʦ��Ϣ", tempPath, "��ʦ��Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputStudent(HttpServletRequest request) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectAllStudentInfoForExcel();	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "������"+request + "��ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}


	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputStudentByKey(HttpServletRequest request) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectAllStudentInfoForExcel();	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "������"+request + "��ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputAllTeacher(HttpServletRequest request) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectAllStudentInfoForExcel();	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "ȫ��ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputTeacher(HttpServletRequest request) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectAllStudentInfoForExcel();	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "������"+request + "��ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}


	/**
	 * 
	 * @param request
	 * @return
	 */
	public File outputTeacherByKey(HttpServletRequest request) {
		//��������EXCEL����
		WriteExcel writeExcel=new WriteExcel();
		//��ȡ����ѧ���б�
		List<Student> allStu = studentMapper.selectAllStudentInfoForExcel();	
		//����excel
		return writeExcel.outputStudentExcel(allStu, "ѧ����Ϣ", tempPath, "������"+request + "��ѧ����Ϣ.xlsx", writeExcel.STUDENT_ALL);
	}

	/**
	 * ���ѧ��ʵϰ������
	 * @param httpServletRequest 
	 */
	public File outputInternshipTable(HttpServletRequest httpServletRequest,String stuNo) {

		File file = null;

		// ���ϲ�ѯ
		List<Student> student = studentMapper.selectStudentByStuNo(stuNo);
		List<Journal>journals  = journalMapper.searchJournalByStuNo(stuNo);
		List<Company> relationStuComs = relationStuComMapper.selectAllForStudentByStuNo(stuNo);
		List<InternshipByContent>internshipByContents = internshipByContentMapper.selectInternshipByStuNo(stuNo);
		List<Accletter> accletters = accletterMapper.selectByStuNo(stuNo);
		List<Achievement> achievements = achievementMapper.selectByStuNo(stuNo);

		Map<String, Object> param = new HashMap<String, Object>();

		System.err.println(student+"____________________________________");
		System.err.println(relationStuComs+"____________________________________");
		System.err.println(journals+"____________________________________");
		System.err.println(internshipByContents+"____________________________________");
		System.err.println(accletters+"____________________________________");
		System.err.println(achievements+"____________________________________");

		// ����
		param.put("@major@", student.get(0).getMajName().toString() + "ϵ");
		param.put("@majorM@",student.get(0).getMajName().toString());
		param.put("@class@", student.get(0).getClassName().toString());
		param.put("@name@", student.get(0).getStuName().toString());
		param.put("@no@",student.get(0).getStuNo().toString());


		if (relationStuComs.size() > 0) {
			param.put("@company@", relationStuComs.get(0).getComName().toString());
		}else{
			param.put("@company@", "");
		}

		// ������
		if (internshipByContents.size()>0) {
			param.put("@selfcomment@", internshipByContents.get(0).getLcSelfcomment().toString());
		}else{
			param.put("@selfcomment@", "");
		}

		if (achievements.size() > 0) {
			param.put("@comComment@", achievements.get(0).getAchComComment().toString());
			param.put("@comScore@", achievements.get(0).getAchComScore().toString());
			param.put("@teaComment@", achievements.get(0).getAchTeaComment().toString());
			param.put("@teaScore@", achievements.get(0).getAchTeaScore().toString());
			param.put("@teaName@", student.get(0).getTeaName().toString());
			param.put("@AdminComment@", "ʵϰ�����������");
			param.put("@AdminName@", "ʵϰС���鳤");
			param.put("@systemScore@", achievements.get(0).getAchLastScore().toString());
			
			if (achievements.get(0).getAchLastScore().intValue()>= 90) {
				param.put("@sss@", "��");
			}else if (achievements.get(0).getAchLastScore().intValue()>= 80) {
				param.put("@sss@", "��");
			}else if (achievements.get(0).getAchLastScore().intValue()>= 70) {
				param.put("@sss@", "��");
			}else if(achievements.get(0).getAchLastScore().intValue()>= 60){
				param.put("@sss@", "����");
			}else{
				param.put("@sss@", "������");
			}
		}else {
			param.put("@comComment@", "");
			param.put("@comScore@", "");
			param.put("@teaComment@", "");
			param.put("@teaScore@", "");
			param.put("@teaName@", student.get(0).getTeaName().toString());
			param.put("@AdminComment@", "");
			param.put("@AdminName@", "ʵϰС���鳤");
			param.put("@systemScore@", "");
			param.put("@sss@", "");
		}

		

		// ��ƪʵϰ��־
		param.put("@direction@", student.get(0).getDirName().toString());
		if (journals.size() > 0) {
			param.put("@journalone@", journals.get(0).getJourContent().toString());
			param.put("@journaltwo@", journals.get(1).getJourContent().toString());
			param.put("@journalthree@", journals.get(2).getJourContent().toString());
			param.put("@journalfour@", journals.get(3).getJourContent().toString());
		}else{
			param.put("@journalone@", "");
			param.put("@journaltwo@", "");
			param.put("@journalthree@", "");
			param.put("@journalfour@", "");
		}


		// ʵϰ����
		if (internshipByContents.size()>0) {
			//			param.put("@teamMember@", internshipByContents.get(0).get);
			String proString = "";
			for (int i = 0; i < internshipByContents.get(0).getProjectList().size(); i++) {
				proString += internshipByContents.get(0).getProjectList().get(i).getProName() + "��";
			}
			param.put("@project@", proString);
			param.put("@content@", internshipByContents.get(0).getLcContent().toString());
			param.put("@teamMember@", internshipByContents.get(0).getProjectList().get(0).getTeam().getTeamMemberList().get(0).getTmName().toString());

		}else{
			//			param.put("@teamMember@", "");
			param.put("@project@", "");
			param.put("@content@", "");
			param.put("@teamMember@", "");

		}

		if (relationStuComs.size() > 0) {
			param.put("@comManager@", relationStuComs.get(0).getComManager().toString());
		}else{
			param.put("@comManager@", "��");
		}
		//		param.put("@5@", new Date().toString());



		// ʵϰ���ܺ�����
		if (accletters.size() > 0) {
			FileInputStream accletter = null;
			try {
				accletter = new FileInputStream(accletters.get(0).getLalPath());
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			BufferedImage sourceImg = null;
			try {
				sourceImg = ImageIO.read(accletter);
			} catch (IOException e2) {
				e2.printStackTrace();
			}finally{

				//				try {
				//					accletter.close();
				//				} catch (IOException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}
			}
			float p = sourceImg.getWidth()/sourceImg.getHeight();
			Map<String, Object> header = new HashMap<String, Object>();
			header.put("width", 570);
			header.put("height",(int) ((int) 570*p));
			header.put("type", "jpg");
			try {
				header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream("C:\\uploadServer\\UserUpData\\2020-03-13\\P6101C9C23C8F468A94411D104F19015A.png"), true));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			param.put("@accletter@", header);
		}else{
			param.put("@accletter@", "δ�ҵ�ʵϰ���ܺ�������");
		}




		CustomXWPFDocument doc = WordUtil.generateWord(param, TemplatePath + "\\internship_appraisal_form.docx");

		FileOutputStream fopts;
		try {
			fopts = new FileOutputStream(tempPath+"\\temp2.docx");
			doc.write(fopts);
			fopts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = new File(tempPath+"\\temp2.docx");
		return file;

	}



}
