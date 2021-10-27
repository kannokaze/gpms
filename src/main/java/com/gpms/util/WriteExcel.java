package com.gpms.util;

import com.gpms.po.Company;
import com.gpms.po.Student;
import com.gpms.po.Teacher;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WriteExcel {
	public int ALL = 0;
	public int STUDENT_ALL = 1;
	public int TEACHER_ALL = 2;
	public int COMPANY_ALL = 3;
	public Student student;
	public XSSFWorkbook workbook;
	
	/**
	 * 添加数据有效性检查.
	 * @param sheet 要添加此检查的Sheet
	 * @param firstRow 开始行
	 * @param lastRow 结束行
	 * @param firstCol 开始列
	 * @param lastCol 结束列
	 * @param explicitListValues 有效性检查的下拉列表
	 * @throws IllegalArgumentException 如果传入的行或者列小于0(< 0)或者结束行/列比开始行/列小
	 */
	public static void setValidationData(Sheet sheet, int firstRow,  int lastRow,
	        int firstCol,  int lastCol,String[] explicitListValues) throws IllegalArgumentException{
		if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol) {
			throw new IllegalArgumentException("Wrong Row or Column index : " + firstRow+":"+lastRow+":"+firstCol+":" +lastCol);
		}
		if (sheet instanceof XSSFSheet) {
			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheet);
			XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
					.createExplicitListConstraint(explicitListValues);
			CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
			XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			validation.createPromptBox("请选择列表中的值", "[正常,待审核,封禁,过期,注销]");  
			sheet.addValidationData(validation);
		} else if(sheet instanceof HSSFSheet){
			CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
			DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(explicitListValues);
			DataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			validation.createPromptBox("请选择列表中的值", "[正常,待审核,封禁,过期,注销]");  
			sheet.addValidationData(validation);
		}
	}

	/**
	 * 创建表头
	 * @param sheet
	 * @param titles
	 */
	private void createRowOne(XSSFSheet sheet,List<String> titles) {
		// TODO Auto-generated method stub
		XSSFRow fristRow = sheet.createRow(0);
		int cellnum = 0;
		for(String title:titles){
			XSSFCell cell = fristRow.createCell(cellnum);
			cell.setCellValue(title);
			cell.setCellStyle(setStyle(HSSFColor.SEA_GREEN.index,HSSFCellStyle.ALIGN_RIGHT,(short)11,IndexedColors.WHITE.getIndex()));
			cellnum++;
		}
	}
	/**
	 * 设置字体样式
	 * @return 
	 * @return 
	 */
	private XSSFFont setFont(short size,short color) {
		XSSFFont fontStyle = workbook.createFont();
		fontStyle.setFontName("等线"); //字体
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //粗体
		fontStyle.setFontHeightInPoints(size);//设置excel数据字体大
		fontStyle.setColor(color);//设置excel数据字体颜色
		return fontStyle;
	}

	/**
	 * 设置表格单元格样式
	 * @return 
	 */
	private CellStyle setStyle(short bgcolor,short align,short size,short color) {
		CellStyle style = workbook.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		style.setFillForegroundColor(bgcolor);
		style.setAlignment(align);
		style.setFont(setFont(size, color));
		return style;
	}
	/**
	 * 输入excel表
	 * @param studentList
	 * @param sheetName
	 * @param path
	 * @param fileName
	 * @return
	 */
	public File outputStudentExcel(List<Student> dataList,String sheetName,String path,String fileName,int model){

		File tempPath = new File(path);
		if (!tempPath.exists()) {
			tempPath.mkdirs();
		}
		File tfile = new File(tempPath.getPath()+ "\\" + fileName);

		switch (model) {
		case 0:

			break;
		case 1:
			writeStudentListExcel(dataList, sheetName);
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		default:

			break;
		}
		//保存excel文件
		try {
			workbook.write(new FileOutputStream(tfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//处理后返回一个文件
		return tfile;
	}
	/**
	 * 输入excel表
	 * @param studentList
	 * @param sheetName
	 * @param path
	 * @param fileName
	 * @return
	 */
	public File outputTeacherExcel(List<Teacher> dataList,String sheetName,String path,String fileName,int model){

		File tempPath = new File(path);
		if (!tempPath.exists()) {
			tempPath.mkdirs();
		}
		File tfile = new File(tempPath.getPath()+ "\\" + fileName);

		switch (model) {
		case 0:

			break;
		case 1:
			writeTeacherListExcel(dataList, sheetName);
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		default:

			break;
		}
		//保存excel文件
		try {
			workbook.write(new FileOutputStream(tfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//处理后返回一个文件
		return tfile;
	}


	public File outputCompanyExcel(List<Company> dataList,String sheetName,String path,String fileName,int model) {

		File tempPath = new File(path);
		if (!tempPath.exists()) {
			tempPath.mkdirs();
		}
		File tfile = new File(tempPath.getPath()+ "\\" + fileName);

		switch (model) {
		case 0:

			break;
		case 1:
			writeCompanyListExcel(dataList, sheetName);
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		default:

			break;
		}
		//保存excel文件
		try {
			workbook.write(new FileOutputStream(tfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//处理后返回一个文件
		return tfile;
	}


	/**
	 * 
	 * @param studentList
	 * @param sheetName
	 */
	public void writeStudentListExcel(List<Student> studentList,String sheetName){

		workbook=new XSSFWorkbook();
		//2、新建工作表
		XSSFSheet sheet=workbook.createSheet(sheetName);

		//创建表头列表
		List<String> tiList = new ArrayList<String>();
		tiList.add(0, "序号");
		tiList.add(1, "学号");
		tiList.add(2,"姓名");
		tiList.add(3,"登录密码");
		tiList.add(4,"手机号");
		tiList.add(5,"邮箱");
		tiList.add(6,"性别");
		tiList.add(7,"现住址");
		tiList.add(8,"届期");
		tiList.add(9,"账户状态");
		tiList.add(10,"班级号");
		tiList.add(11,"导师号");
		tiList.add(12,"专业号");
		tiList.add(13,"方向号");
		tiList.add(14,"学校号");
		//创建表头
		createRowOne(sheet, tiList);
		String[] dataVailStrings = {"正常","待审核","封禁","过期","注销"};
		setValidationData(sheet, 1, 1048570, 9, 9, dataVailStrings);
		//写入数据
		int rownum = 1;
		for (Student student:studentList) {
			XSSFRow row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(student.getStuId());
			row.createCell(1).setCellValue(student.getStuNo());
			row.createCell(2).setCellValue(student.getStuName());
			row.createCell(3).setCellValue(student.getStuPassword());
			row.createCell(4).setCellValue(student.getStuPhone());
			row.createCell(5).setCellValue(student.getStuEmail());
			if (student.getStuGender().equals(0)) {
				row.createCell(6).setCellValue("男");
			}else{
				row.createCell(6).setCellValue("女");
			}
			row.createCell(7).setCellValue(student.getStuAddress());
			row.createCell(8).setCellValue(student.getStuStage());
			if (student.getStuState().equals("0")) {
				row.createCell(9).setCellValue("正常");
			}else if (student.getStuState().equals("1")) {
				row.createCell(9).setCellValue("待审核");
			}else if (student.getStuState().equals("2")) {
				row.createCell(9).setCellValue("封禁");
			}else if (student.getStuState().equals("3")) {
				row.createCell(9).setCellValue("过期");
			}else if (student.getStuState().equals("4")) {
				row.createCell(9).setCellValue("注销");
			}else{
				row.createCell(9).setCellValue(student.getStuState());
			}			
			row.createCell(10).setCellValue(student.getStuClassNo());
			row.createCell(11).setCellValue(student.getStuTeaNo());
			row.createCell(12).setCellValue(student.getStuMajCode());
			row.createCell(13).setCellValue(student.getStuDirCode());
			row.createCell(14).setCellValue(student.getStuSchId());
			rownum++;
		}

	}  
	
	public void writeTeacherListExcel(List<Teacher> teacherList, String sheetName) {

		workbook=new XSSFWorkbook();
		//2、新建工作表
		XSSFSheet sheet=workbook.createSheet(sheetName);

		//创建表头列表
		List<String> tiList = new ArrayList<String>();
		tiList.add(0, "序号");
		tiList.add(1, "工号");
		tiList.add(2,"姓名");
		tiList.add(3,"登录密码");
		tiList.add(4,"手机号");
		tiList.add(5,"邮箱");
		tiList.add(6,"账户状态");
		tiList.add(7,"专业号");
		tiList.add(8,"方向号");
		tiList.add(9,"学校号");
		//创建表头
		createRowOne(sheet, tiList);
		String[] dataVailStrings = {"正常","待审核","封禁","过期","注销"};
		setValidationData(sheet, 1, 1048570, 9, 9, dataVailStrings);
		//写入数据
		int rownum = 1;
		for (Teacher teacher:teacherList) {
			XSSFRow row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(teacher.getTeaId());
			row.createCell(1).setCellValue(teacher.getTeaNo());
			row.createCell(2).setCellValue(teacher.getTeaName());
			row.createCell(3).setCellValue(teacher.getTeaPassword());
			row.createCell(4).setCellValue(teacher.getTeaPhone());
			row.createCell(5).setCellValue(teacher.getTeaEmail());
			if (teacher.getTeaState().equals("0")) {
				row.createCell(6).setCellValue("正常");
			}else if (teacher.getTeaState().equals("1")) {
				row.createCell(6).setCellValue("待审核");
			}else if (teacher.getTeaState().equals("2")) {
				row.createCell(6).setCellValue("封禁");
			}else if (teacher.getTeaState().equals("3")) {
				row.createCell(6).setCellValue("过期");
			}else if (teacher.getTeaState().equals("4")) {
				row.createCell(6).setCellValue("注销");
			}else{
				row.createCell(6).setCellValue(teacher.getTeaState());
			}			
			row.createCell(7).setCellValue(teacher.getTeaMajCode());
			row.createCell(8).setCellValue(teacher.getTeaDirCode());
			row.createCell(9).setCellValue(teacher.getTeaSchId());
			rownum++;
		}
		
	}
	public void writeCompanyListExcel(List<Company> companiesList, String sheetName) {
		workbook=new XSSFWorkbook();
		//2、新建工作表
		XSSFSheet sheet=workbook.createSheet(sheetName);

		//创建表头列表
		List<String> tiList = new ArrayList<String>();
		tiList.add(0, "序号");
		tiList.add(1, "企业号");
		tiList.add(2,"企业名");
		tiList.add(3,"登录密码");
		tiList.add(4,"联系电话");
		tiList.add(5,"邮箱");
		tiList.add(6,"负责人");
		tiList.add(7,"账户转态");
		tiList.add(8,"行业");
		tiList.add(9,"注册时间");
		//创建表头
		createRowOne(sheet, tiList);
		String[] dataVailStrings = {"正常","未激活","封禁"};
		setValidationData(sheet, 1, 1048570, 7, 7, dataVailStrings);
		//写入数据
		int rownum = 1;
		for (Company company:companiesList) {
			XSSFRow row = sheet.createRow(rownum);
			row.createCell(0).setCellValue(company.getComId());
			row.createCell(1).setCellValue(company.getComAccount());
			row.createCell(2).setCellValue(company.getComName());
			row.createCell(3).setCellValue(company.getComPassword());
			row.createCell(4).setCellValue(company.getComPhone());
			row.createCell(5).setCellValue(company.getComEmail());
			row.createCell(6).setCellValue(company.getComManager());
			if (company.getComState().equals("0")) {
				row.createCell(7).setCellValue("正常");
			}else if (company.getComState().equals("1")) {
				row.createCell(7).setCellValue("待审核");
			}else if (company.getComState().equals("2")) {
				row.createCell(7).setCellValue("封禁");
			}else{
				row.createCell(7).setCellValue(company.getComState());
			}			
			row.createCell(8).setCellValue(company.getComTrade());
			row.createCell(9).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( company.getComRegistime()));
			
			rownum++;
		}
		
	}

}
