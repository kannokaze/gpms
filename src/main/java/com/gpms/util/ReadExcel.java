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
	// @�������Ƿ���2003��excel������true��2003 
	public static boolean isExcel2003(String filePath)  {  
		return filePath.matches("^.+\\.(?i)(xls)$");  
	}  
	//@�������Ƿ���2007��excel������true��2007 
	public static boolean isExcel2007(String filePath)  {  
		return filePath.matches("^.+\\.(?i)(xlsx)$");  
	} 
	//������
	private int totalRows = 0;
	//������
	private int totalCells = 0;
	//������Ϣ������
	private String errorMsg; 
	//���췽��
	public ReadExcel(){} 

	/**
	 * 
	 * @param file
	 * @return
	 */
	public List<Teacher> getExcelInfoTeacher(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();//��ȡ�ļ���
		System.out.println("�ļ���"+fileName);
		List<Teacher> teaList = null;
		try {
			if (!validateExcel(fileName)) {// ��֤�ļ����Ƿ�ϸ�
				return null;
			}
			boolean isExcel2003 = true;// �����ļ����ж��ļ���2003�汾����2007�汾
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
	 * ��EXCEL�ļ�����ȡѧ����Ϣ����
	 * @param fielName
	 * @return
	 */
	public List<Student> getExcelInfoStudent(MultipartFile mFile) {
		String fileName = mFile.getOriginalFilename();//��ȡ�ļ���
		System.out.println("�ļ���"+fileName);
		List<Student> stuList = null;
		try {
			if (!validateExcel(fileName)) {// ��֤�ļ����Ƿ�ϸ�
				return null;
			}
			boolean isExcel2003 = true;// �����ļ����ж��ļ���2003�汾����2007�汾
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
	 * ����excel��������ݶ�ȡ�ͻ���Ϣ
	 * @param is ������
	 * @param isExcel2003 excel��2003����2007�汾
	 * @return
	 * @throws IOException
	 */
	public List<Student> createExcelStudent(InputStream is, boolean isExcel2003) {
		List<Student> stuList = null;
		try{
			Workbook wb = null;
			if (isExcel2003) {// ��excel��2003ʱ,����excel2003
				wb = new HSSFWorkbook(is);
			} else {// ��excel��2007ʱ,����excel2007
				wb = new XSSFWorkbook(is);
			}
			stuList = readExcelValueStudent(wb);// ��ȡExcel����ͻ�����Ϣ
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
			if (isExcel2003) {// ��excel��2003ʱ,����excel2003
				wb = new HSSFWorkbook(is);
			} else {// ��excel��2007ʱ,����excel2007
				wb = new XSSFWorkbook(is);
			}
			teaList = readExcelValueTeacher(wb);// ��ȡExcel����ͻ�����Ϣ
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teaList;
	}


	//��ȡ������Ϣ
	public String getErrorInfo() { return errorMsg; }

	/**
	 * ��ȡExcel��ʵ����
	 * @param wb
	 * @return
	 */
	public int getExcelRealRow(Sheet sheet) {
		boolean flag = false;
		for (int i = 1; i <= sheet.getLastRowNum(); ) {
			Row r = sheet.getRow(i);
			if (r == null) {
				// ����ǿ��У���û���κ����ݡ���ʽ����ֱ�Ӱ������µ����������ƶ�
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
				// ����ǿհ��У�������û�����ݣ�������һ����ʽ��
				if (i == sheet.getLastRowNum())// ����������һ�У�ֱ�ӽ���һ��remove��
					sheet.removeRow(r);
				else//�����û�����һ�У�������������һ��
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
			}
		}
		return sheet.getLastRowNum();
	}
	//��ȡ������
	public int getTotalCells() {  return totalCells;}



	//��ȡ������
	public int getTotalRows()  { return totalRows;}

	private List<Teacher> readExcelValueTeacher(Workbook wb) {
		// �õ���һ��shell
		Sheet sheet = wb.getSheetAt(0);
		// �õ�Excel������
		this.totalRows = getExcelRealRow(sheet);
		// �õ�Excel������(ǰ����������)
		if (totalRows > 0 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Teacher> teaList = new ArrayList<Teacher>();

		// ѭ��Excel����
		for (int r = 1; r < totalRows+1; r++) {
			Row row = sheet.getRow(r);
			if (row == null){
				continue;
			}
			Teacher teacher = new Teacher();
			// ѭ��Excel����
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {

					if (c == 0) {
						//����Ǵ�����,������д����25,cell.getNumericCellValue()�����25.0,ͨ����ȡ�ַ���ȥ��.0���25
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							//�����������ò���
						}
					} else if (c == 1) {
						//����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaNo(cell.getStringCellValue());
						} else {
							teacher.setTeaNo(String.valueOf(cell.getNumericCellValue()));
						}

					}else if (c == 2){
						//ѧ������
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaName(cell.getStringCellValue());
						}
					}
					else if (c == 3){  
						//����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaPassword(DigestUtils.md5Hex(cell.getStringCellValue()));
						}
					}
					else if (c == 4){   						
						//�ֻ�����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaPhone(cell.getStringCellValue());
						}
					}
					else if (c == 5){
						//����
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							teacher.setTeaEmail(cell.getStringCellValue());
						}
					}
					else if (c == 6){
						//�˻�״̬ 0:������1:����ˣ�2:�����3:���ڣ�4:ע��
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("����")) {
								teacher.setTeaState("0");
							}else if (cell.getStringCellValue().equals("�����")) {
								teacher.setTeaState("1");
							}else if (cell.getStringCellValue().equals("���")) {
								teacher.setTeaState("2");
							}else if (cell.getStringCellValue().equals("����")) {
								teacher.setTeaState("3");
							}else if (cell.getStringCellValue().equals("ע��")) {
								teacher.setTeaState("4");
							}else{
								teacher.setTeaState("0");
							}
						}else{
							teacher.setTeaState("0");
						}						
					}else if (c == 7) {
						//רҵ��
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							teacher.setTeaMajCode(cell.getStringCellValue());
						}

					}else if (c == 8) {
						// �����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							teacher.setTeaDirCode(cell.getStringCellValue());
						}
					}
					else if (c == 9) {
						//ѧУ��
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							teacher.setTeaSchId((int) cell.getNumericCellValue());
						}
					}
				}
			}
			// ��ӵ�list
			teaList.add(teacher);
			System.out.println(teaList);
		}

		return teaList;
	}
	/**
	 * ��ȡExcel����ѧ������Ϣ
	 * @param wb
	 * @return
	 */
	private List<Student> readExcelValueStudent(Workbook wb) {
		// �õ���һ��shell
		Sheet sheet = wb.getSheetAt(0);
		// �õ�Excel������
		this.totalRows = getExcelRealRow(sheet);
		// �õ�Excel������(ǰ����������)
		if (totalRows > 0 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Student> stuList = new ArrayList<Student>();

		// ѭ��Excel����
		for (int r = 1; r < totalRows+1; r++) {
			Row row = sheet.getRow(r);
			if (row == null){
				continue;
			}
			Student student = new Student();
			// ѭ��Excel����
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {

					if (c == 0) {
						//����Ǵ�����,������д����25,cell.getNumericCellValue()�����25.0,ͨ����ȡ�ַ���ȥ��.0���25
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							//�����������ò���
						}
					} else if (c == 1) {
						//ѧ��
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuNo(cell.getStringCellValue());
						} else {
							student.setStuNo(String.valueOf(cell.getNumericCellValue()));
						}

					}else if (c == 2){
						//ѧ������
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuName(cell.getStringCellValue());
						}
					}
					else if (c == 3){  
						//����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuPassword(DigestUtils.md5Hex(cell.getStringCellValue()));
						}
					}
					else if (c == 4){   						
						//�ֻ�����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuPhone(cell.getStringCellValue());
						}
					}
					else if (c == 5){
						//����
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuEmail(cell.getStringCellValue());
						}
					}
					else if (c == 6){  
						//�Ա�
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("��")) {
								student.setStuGender(0);
							}else{
								student.setStuGender(1);
							}
						}else{
							student.setStuGender((int) cell.getNumericCellValue());
						}
					}
					else if (c == 7){
						//��סַ
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuAddress(cell.getStringCellValue());
						}
					}
					else if (c == 8){
						// ����
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuStage(cell.getStringCellValue());
						}
					}
					else if (c == 9){
						//�˻�״̬ 0:������1:����ˣ�2:�����3:���ڣ�4:ע��
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							if (cell.getStringCellValue().equals("����")) {
								student.setStuState("0");
							}else if (cell.getStringCellValue().equals("�����")) {
								student.setStuState("1");
							}else if (cell.getStringCellValue().equals("���")) {
								student.setStuState("2");
							}else if (cell.getStringCellValue().equals("����")) {
								student.setStuState("3");
							}else if (cell.getStringCellValue().equals("ע��")) {
								student.setStuState("4");
							}else{
								student.setStuState("1");
							}
						}else{
							student.setStuState("1");
						}						
					}else if(c == 10){
						//�༶��
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuClassNo(cell.getStringCellValue());
						}
					}else if (c == 11) {
						//��ʦ��
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							student.setStuTeaNo(cell.getStringCellValue());
						}
					}else if (c == 12) {
						//רҵ��
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							student.setStuMajCode(cell.getStringCellValue());
						}

					}else if (c == 13) {
						// �����
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							student.setStuDirCode(cell.getStringCellValue());
						}
					}
					else if (c == 14) {
						//ѧУ��
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							student.setStuSchId((int) cell.getNumericCellValue());
						}
					}
				}
			}
			// ��ӵ�list
			stuList.add(student);
			System.out.println(stuList);
		}

		return stuList;
	}  
	/**
	 * ��֤EXCEL�ļ�
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "�ļ�������excel��ʽ";
			return false;
		}
		return true;
	}

}