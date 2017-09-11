package com.anbang.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class importExcelTools{
	public static List<Map<String,Object>> loadExcel(String filepath,int count){  
        //创建Excel工作簿文件的引用  
        XSSFWorkbook wookbook=null;  
        try{  
            wookbook = new XSSFWorkbook(new FileInputStream(filepath));//根据路劲创建引用  
        }catch(FileNotFoundException e){  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }catch(IOException e){  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        //在excel文档中，第一个工作表的缺省索引是0  
        XSSFSheet sheet=wookbook.getSheetAt(count);  
        //获取到excel文件中的所有行数  
        int rows = sheet.getPhysicalNumberOfRows();  
        int shengchanstart=0;  
        List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();  
        boolean boo =false;  
        for(int i=1;i<rows;i++){  
            XSSFRow row = sheet.getRow(i);  
             
            if(row!=null){  
                //获取文件中的所有列  
                int cells = row.getPhysicalNumberOfCells();  
                String value="";  
                //遍历列  
                if(!boo){  
                   aa: for(int j=0;j<cells;j++){  
                         
                        XSSFCell cell = row.getCell((short)j);  
                          
                        if(cell!=null){  
                            switch(cell.getCellType()){  
                                case XSSFCell.CELL_TYPE_FORMULA:  
                                    System.out.println(cell.getCellFormula() + "=====================");;  
                                    break;  
                                   case XSSFCell.CELL_TYPE_NUMERIC:  
                                    value += cell.getNumericCellValue() + ",";  
                                    break;  
                                   case XSSFCell.CELL_TYPE_STRING:  
                                       if("表名".equals(cell.getStringCellValue())){  
                                           boo=true;  
                                           break aa;  
                                       }  
                                    value += cell.getStringCellValue() + ",";  
                                   default:  
                                    value += "";  
                                    break;  
                            }  
                        }  
                       }  
                    }else{  
                        XSSFCell cell = row.getCell((short)1);  
                        Map<String,Object> map = new HashMap<String, Object>();  
                        if(!"".equals(cell.getStringCellValue())){  
                            String tablename=getMergedRegionValue(sheet,i);  
                            map.put("table",tablename);  
                            map.put("fieldname",row.getCell((short)(shengchanstart+2)).getStringCellValue());  
                            map.put("desc",row.getCell((short)(shengchanstart+1)).getStringCellValue());  
                            System.out.println(tablename+"**********");  
                            li.add(map);  
                        }  
                        //System.out.println(i+"**********");  
                    }  
                }  
        }  
        System.out.println(li);  
        return li;  
    }  
    /**    
    * 获取合并单元格的值    
    * @param sheet    
    * @param row    
    * @param column    
    * @return    
    */      
    public static String getMergedRegionValue(Sheet sheet ,int row ){      
        int sheetMergeCount = sheet.getNumMergedRegions();      
              
        for(int i = 0 ; i < sheetMergeCount ; i++){      
            org.apache.poi.ss.util.CellRangeAddress ca =  sheet.getMergedRegion(i);      
            int firstColumn = ca.getFirstColumn();      
            int lastColumn = ca.getLastColumn();      
            int firstRow = ca.getFirstRow();      
            int lastRow = ca.getLastRow();      
            if(row >= firstRow && row <= lastRow){      
                    Row fRow = sheet.getRow(firstRow);      
                    Cell fCell = fRow.getCell(firstColumn);      
                    return getCellValue(fCell) ;      
            }      
        }      
        return null ;      
    }      
    /**    
    * 获取单元格的值    
    * @param cell    
    * @return    
    */      
    public static String getCellValue(Cell cell){      
              
        if(cell == null) return "";      
              
        if(cell.getCellType() == Cell.CELL_TYPE_STRING){      
                  
            return cell.getStringCellValue();      
                  
        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){      
                  
            return String.valueOf(cell.getBooleanCellValue());      
                  
        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){      
                  
            return cell.getCellFormula() ;      
                  
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){      
                  
            return String.valueOf(cell.getNumericCellValue());      
                  
        }      
        return "";      
    }    
    

}