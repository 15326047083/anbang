package com.anbang.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddressList;


public class ExcelUtil<T> {
	Class<T> clazz;

	public ExcelUtil(Class<T> clazz) {
		this.clazz = clazz;
	}

	public List<T> importExcel(String sheetName, InputStream input) {
		List<T> list = new ArrayList<T>();
		try {
			Workbook book = Workbook.getWorkbook(input);
			Sheet sheet = null;
			if (!sheetName.trim().equals("")) {
				sheet = book.getSheet(sheetName);// ���ָ��sheet��,��ȡָ��sheet�е�����.
			}
			if (sheet == null) {
				sheet = book.getSheet(0);// ������sheet�������Ĭ��ָ���1��sheet.
			}
			int rows = sheet.getRows();// �õ���ݵ�����
			if (rows > 0) {// �����ʱ�Ŵ���
				Field[] allFields = clazz.getDeclaredFields();// �õ��������field.
				Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// ����һ��map���ڴ���е���ź�field.
				for (Field field : allFields) {
					// ����ע���field��ŵ�map��.
					if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
						ExcelVOAttribute attr = field
								.getAnnotation(ExcelVOAttribute.class);
						int col = getExcelCol(attr.column());// ����к�
						// System.out.println(col + "====" + field.getName());
						field.setAccessible(true);// �������˽���ֶ����Կɷ���.
						fieldsMap.put(col, field);
					}
				}
				for (int i = 1; i < rows; i++) {// �ӵ�2�п�ʼȡ���,Ĭ�ϵ�һ���Ǳ�ͷ.
					Cell[] cells = sheet.getRow(i);// �õ�һ���е����е�Ԫ�����.
					T entity = null;
					for (int j = 0; j < cells.length; j++) {
						String c = cells[j].getContents();// ��Ԫ���е�����.
						//System.out.println(c);
						if (c.equals("")) {
							continue;
						}
						entity = (entity == null ? clazz.newInstance() : entity);// ������ʵ�����½�.
						// System.out.println(cells[j].getContents());
						Field field = fieldsMap.get(j);// ��map�еõ���Ӧ�е�field.
						// ȡ������,����ݶ�����������ֵ.
						Class<?> fieldType = field.getType();
						if ((Integer.TYPE == fieldType)
								|| (Integer.class == fieldType)) {
							field.set(entity, Integer.parseInt(c));
						} else if (String.class == fieldType) {
							field.set(entity, String.valueOf(c));
						} else if ((Long.TYPE == fieldType)
								|| (Long.class == fieldType)) {
							field.set(entity, Long.valueOf(c));
						} else if ((Float.TYPE == fieldType)
								|| (Float.class == fieldType)) {
							field.set(entity, Float.valueOf(c));
						} else if ((Short.TYPE == fieldType)
								|| (Short.class == fieldType)) {
							field.set(entity, Short.valueOf(c));
						} else if ((Double.TYPE == fieldType)
								|| (Double.class == fieldType)) {
							field.set(entity, Double.valueOf(c));
						} else if (Character.TYPE == fieldType) {
							if ((c != null) && (c.length() > 0)) {
								field.set(entity, Character
										.valueOf(c.charAt(0)));
							}
						}

					}
					if (entity != null) {
						list.add(entity);
					}
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ��list���Դ�����������ݵ��뵽excel�?
	 * 
	 * @param sheetName
	 *            ����������
	 * @param sheetSize
	 *            ÿ��sheet����ݵ�����,����ֵ����С��65536
	 * @param output
	 *            java�����
	 */
	public boolean exportExcel(List<T> list, String sheetName, int sheetSize,
			OutputStream output) {

		Field[] allFields = clazz.getDeclaredFields();// �õ����ж����ֶ�
		List<Field> fields = new ArrayList<Field>();
		// �õ�����field����ŵ�һ��list��.
		for (Field field : allFields) {
			if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
				fields.add(field);
			}
		}

		HSSFWorkbook workbook = new HSSFWorkbook();// ������������

		// excel2003��ÿ��sheet�������65536��,Ϊ�������������Լ�����߼�.
		if (sheetSize > 65536 || sheetSize < 1) {
			sheetSize = 65536;
		}
		double sheetNo = Math.ceil(list.size() / sheetSize);// ȡ��һ���ж��ٸ�sheet.
		for (int index = 0; index <= sheetNo; index++) {
			HSSFSheet sheet = workbook.createSheet();// �����������
			workbook.setSheetName(index, sheetName + index);// ���ù���������.
			HSSFRow row;
			HSSFCell cell;// ����Ԫ��

			row = sheet.createRow(0);// ����һ��
			// д������ֶε���ͷ���
			for (int i = 0; i < fields.size(); i++) {
				Field field = fields.get(i);
				ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
				int col = getExcelCol(attr.column());// ����к�
				cell = row.createCell(col);// ������
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);// ��������д������ΪString����
				cell.setCellValue(attr.name());// д������

				// �����������ʾ��Ϣ��������ȥ��ʾ.
				if (!attr.prompt().trim().equals("")) {
					setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);// ����Ĭ������2-101����ʾ.
				}
				// ���������combo��������ֻ��ѡ��������
				if (attr.combo().length > 0) {
					setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);// ����Ĭ������2-101��ֻ��ѡ��������.
				}
			}

			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// д�������¼,ÿ����¼��Ӧexcel���е�һ��
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				T vo = (T) list.get(i); // �õ���������.
				for (int j = 0; j < fields.size(); j++) {
					Field field = fields.get(j);// ���field.
					field.setAccessible(true);// ����ʵ����˽�����Կɷ���
					ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
					try {
						// ���ExcelVOAttribute��������������Ƿ񵼳�,��Щ�����Ҫ����Ϊ��,ϣ���û���д��һ��.
						if (attr.isExport()) {
							cell = row.createCell(getExcelCol(attr.column()));// ����cell
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(field.get(vo) == null ? ""
									: String.valueOf(field.get(vo)));// �����ݴ��ھ�����,����������ո�.
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

		}
		try {
			output.flush();
			workbook.write(output);
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output is closed ");
			return false;
		}

	}

	/**
	 * ��EXCEL��A,B,C,D,E��ӳ���0,1,2,3
	 * 
	 * @param col
	 */
	public static int getExcelCol(String col) {
		col = col.toUpperCase();
		// ��-1��ʼ����,��ĸ��1��ʼ���㡣���������������������ͬ��
		int count = -1;
		char[] cs = col.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
		}
		return count;
	}

	/**
	 * ���õ�Ԫ������ʾ
	 * 
	 * @param sheet
	 *            Ҫ���õ�sheet.
	 * @param promptTitle
	 *            ����
	 * @param promptContent
	 *            ����
	 * @param firstRow
	 *            ��ʼ��
	 * @param endRow
	 *            ������
	 * @param firstCol
	 *            ��ʼ��
	 * @param endCol
	 *            ������
	 * @return ���úõ�sheet.
	 */
	public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,
			String promptContent, int firstRow, int endRow, int firstCol,
			int endCol) {
		// ����constraint����
		DVConstraint constraint = DVConstraint
				.createCustomFormulaConstraint("DD1");
		// �ĸ�����ֱ��ǣ���ʼ�С���ֹ�С���ʼ�С���ֹ��
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// �����Ч�Զ���
		HSSFDataValidation data_validation_view = new HSSFDataValidation(
				regions, constraint);
		data_validation_view.createPromptBox(promptTitle, promptContent);
		sheet.addValidationData(data_validation_view);
		return sheet;
	}

	/**
	 * ����ĳЩ�е�ֵֻ������Ԥ�Ƶ����,��ʾ������.
	 * 
	 * @param sheet
	 *            Ҫ���õ�sheet.
	 * @param textlist
	 *            ��������ʾ������
	 * @param firstRow
	 *            ��ʼ��
	 * @param endRow
	 *            ������
	 * @param firstCol
	 *            ��ʼ��
	 * @param endCol
	 *            ������
	 * @return ���úõ�sheet.
	 */
	public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
			String[] textlist, int firstRow, int endRow, int firstCol,
			int endCol) {
		// ���������б�����
		DVConstraint constraint = DVConstraint
				.createExplicitListConstraint(textlist);
		// ���������Ч�Լ������ĸ���Ԫ����,�ĸ�����ֱ��ǣ���ʼ�С���ֹ�С���ʼ�С���ֹ��
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,
				endRow, firstCol, endCol);
		// �����Ч�Զ���
		HSSFDataValidation data_validation_list = new HSSFDataValidation(
				regions, constraint);
		sheet.addValidationData(data_validation_list);
		return sheet;
	}
}
