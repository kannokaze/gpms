package com.gpms.util;

import com.gpms.po.Student;
import com.gpms.po.Teacher;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class ReadExcel {
	// @描述：是否是2003的excel，返回true是2003 
	public static boolean isExcel2003(String filePath)  {  
		return filePath.matches("^.+\\.(?i)(xls)$");  
	}  
	//@描述：是否是2007的excel，返回true是2007 
	public static boolean isExcel2007(String filePath)  {  
		return filePath.matches("^.+\\.(?i)(xlsx)$");  
	} 
	//总行数
	private int totalRows = 0;
	//总条数
	private int totalCells = 0;
	//错误信息接收器
	private String errorMsg; 
	//构造方法
	public ReadExcel(){} 

	/**
	 * 
	 * @param file
	 * @return
	 */
	public List<Teacher> getExcelInfoTeacher(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();//获取文件名
		System.out.println("文件名"+fileName);
		List<Teacher> teaList = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			teaList = createExcelTeacher(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teaList;
	}


	/**
	 * 读EXCEL文件，获取学生信息集合
	 * @param fielName
	 * @return
	 */
	public List<Student> getExcelInfoStudent(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();//获取文件名
		System.out.println("文件名"+fileName);
		List<Student> stuList = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			stuList = createExcelStudent(mFile.getInputStream(), isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stuList;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * @param is 输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<Student> createExcelStudent(InputStream is, boolean isExcel2003) {
		List<Student> stuList = null;
		try{
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			stuList = readExcelValueStudent(wb);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stuList;
	}
	/**
	 * 
	 * @param inputStream
	 * @param isExcel2003
	 * @return
	 */
	public List<Teacher> createExcelTeacher(InputStream is, boolean isExcel2003){
		List<Teacher> teaList = null;
		try{
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			teaList = readExcelValueTeacher(wb);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teaList;
	}


	//获取错误信息
	public String getErrorInfo() { return errorMsg; }

	/**
	 * 读取Excel真实行数
	 * @param wb
	 * @return
	 */
	public int getExcelRealRow(Sheet sheet) {
		boolean flag = false;
		for (int i = 1; i <= sheet.getLastRowNum(); ) {
			Row r = sheet.getRow(i);
			if (r == null) {
				// 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
				sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				continue;
			}
			flag = false;
			for (Cell c : r) {
				if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
					flag = true;
					break;
				}
			}
			if (flag) {
				i++;
				continue;
			} else {
				// 如果是空白行（即可能没有数据，但是有一定格式）
				if (i == sheet.getLastRowNum())// 如果到了最后一行，直接将那一行remove掉
					sheet.removeRow(r);
				else//如果还没到最后一行，则数据往上移一行
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
			}
		}
		return sheet.getLastRowNum();
	}
	//获取总列数
	public int getTotalCells() {  return totalCells;}



	//获取总行数
	public int getTotalRows()  { return totalRows;}

	private List<Teacher> readExcelValueTeacher(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = getExcelRealRow(sheet);
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 0 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Teacher> teaList = new ArrayList<Teacher>();

		// 循环Excel行数
		for (int r = 1; r < totalRows+1; r++) {
			Row row = sheet.getRow(r);
			if (row == null){
				continue;
			}
			Teacher teacher = new Teacher();
			// 循环Excel的列
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {

					if (c == 0) {
						//如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							//序列自增不用操作
						}
					} else if (c == 1) {
						//工号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaNo(cell.getStringCellValue());
						} else {
							teacher.setTeaNo(String.valueOf(cell.getNumericCellValue()));
						}

					}else if (c == 2){
						//学生姓名
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaName(cell.getStringCellValue());
						}
					}
					else if (c == 3){  
						//密码
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaPassword(DigestUtils.md5Hex(cell.getStringCellValue()));
						}
					}
					else if (c == 4){   						
						//手机号码
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaPhone(cell.getStringCellValue());
						}
					}
					else if (c == 5){
						//邮箱
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaEmail(cell.getStringCellValue());
						}
					}
					else if (c == 6){
						//账户状态 0:正常，1:待审核，2:封禁，3:过期，4:注销
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("正常")) {
								teacher.setTeaState("0");
							}else if (cell.getStringCellValue().equals("待审核")) {
								teacher.setTeaState("1");
							}else if (cell.getStringCellValue().equals("封禁")) {
								teacher.setTeaState("2");
							}else if (cell.getStringCellValue().equals("过期")) {
								teacher.setTeaState("3");
							}else if (cell.getStringCellValue().equals("注销")) {
								teacher.setTeaState("4");
							}else{
								teacher.setTeaState("0");
							}
						}else{
							teacher.setTeaState("0");
						}						
					}else if (c == 7) {
						//专业号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							teacher.setTeaMajCode(cell.getStringCellValue());
						}

					}else if (c == 8) {
						// 方向号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							teacher.setTeaDirCode(cell.getStringCellValue());
						}
					}
					else if (c == 9) {
						//学校号
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							teacher.setTeaSchId((int) cell.getNumericCellValue());
						}
					}
				}
			}
			// 添加到list
			teaList.add(teacher);
			System.out.println(teaList);
		}

		return teaList;
	}
	/**
	 * 读取Excel里面学生的信息
	 * @param wb
	 * @return
	 */
	private List<Student> readExcelValueStudent(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = getExcelRealRow(sheet);
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 0 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Student> stuList = new ArrayList<Student>();

		// 循环Excel行数
		for (int r = 1; r < totalRows+1; r++) {
			Row row = sheet.getRow(r);
			if (row == null){
				continue;
			}
			Student student = new Student();
			// 循环Excel的列
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {

					if (c == 0) {
						//如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							//序列自增不用操作
						}
					} else if (c == 1) {
						//学号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuNo(cell.getStringCellValue());
						} else {
							student.setStuNo(String.valueOf(cell.getNumericCellValue()));
						}

					}else if (c == 2){
						//学生姓名
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuName(cell.getStringCellValue());
						}
					}
					else if (c == 3){  
						//密码
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuPassword(DigestUtils.md5Hex(cell.getStringCellValue()));
						}
					}
					else if (c == 4){   						
						//手机号码
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuPhone(cell.getStringCellValue());
						}
					}
					else if (c == 5){
						//邮箱
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuEmail(cell.getStringCellValue());
						}
					}
					else if (c == 6){  
						//性别
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("男")) {
								student.setStuGender(0);
							}else{
								student.setStuGender(1);
							}
						}else{
							student.setStuGender((int) cell.getNumericCellValue());
						}
					}
					else if (c == 7){
						//现住址
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuAddress(cell.getStringCellValue());
						}
					}
					else if (c == 8){
						// 届期
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuStage(cell.getStringCellValue());
						}
					}
					else if (c == 9){
						//账户状态 0:正常，1:待审核，2:封禁，3:过期，4:注销
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("正常")) {
								student.setStuState("0");
							}else if (cell.getStringCellValue().equals("待审核")) {
								student.setStuState("1");
							}else if (cell.getStringCellValue().equals("封禁")) {
								student.setStuState("2");
							}else if (cell.getStringCellValue().equals("过期")) {
								student.setStuState("3");
							}else if (cell.getStringCellValue().equals("注销")) {
								student.setStuState("4");
							}else{
								student.setStuState("1");
							}
						}else{
							student.setStuState("1");
						}						
					}else if(c == 10){
						//班级号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuClassNo(cell.getStringCellValue());
						}
					}else if (c == 11) {
						//导师号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuTeaNo(cell.getStringCellValue());
						}
					}else if (c == 12) {
						//专业号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							student.setStuMajCode(cell.getStringCellValue());
						}

					}else if (c == 13) {
						// 方向号
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							student.setStuDirCode(cell.getStringCellValue());
						}
					}
					else if (c == 14) {
						//学校号
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							student.setStuSchId((int) cell.getNumericCellValue());
						}
					}
				}
			}
			// 添加到list
			stuList.add(student);
			System.out.println(stuList);
		}

		return stuList;
	}  
	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}

}