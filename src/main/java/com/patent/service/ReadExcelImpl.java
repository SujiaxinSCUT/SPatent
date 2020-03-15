package com.patent.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patent.bean.Patent;




public class ReadExcelImpl implements IReadExcelService{

	private ArrayList<Patent> patents;
	private String filename;
	Logger logger = LoggerFactory.getLogger(ReadExcelImpl.class);
	public ReadExcelImpl() {
		super();
		// TODO Auto-generated constructor stub
		patents=new ArrayList<>();
	}
	
	public Workbook getWorkbook(String path) throws Exception{
		Workbook workbook=null;
		File file=new File(path);
		
		if(path.endsWith("xls"))
		{
			workbook=WorkbookFactory.create(file);
			
		}
		else if(path.endsWith("xlsx"))
		{
			
			workbook=WorkbookFactory.create(file);
			
			
		}
		else {
			throw new Exception("请上传Excel表单"); 
		}
		return workbook;
	}

	@Override
	public ArrayList<Patent> ReadExcel(String path,int num) throws Exception {
		
			// TODO Auto-generated method stub
			filename=path.substring(path.lastIndexOf(File.separator)+14);
			Workbook workbook=getWorkbook(path);
			  int sheetCount=workbook.getNumberOfSheets();
		        for(int i=0;i<sheetCount;i++)
		        {
		        	Sheet sheet=workbook.getSheetAt(i);
		        	int rows=sheet.getLastRowNum()+1;
		        	Row tmp=sheet.getRow(0);
		        	if(tmp==null)
		        	{
		        		continue;
		        	}
		        	int cols=tmp.getPhysicalNumberOfCells();
		        	for(int row=2;row<rows;row++)
		        	{
		        		Row r=sheet.getRow(row);
		        		Patent patent=new Patent();
		        		
		        		patent.setUpload_num(num);
		        		patents.add(patent);
		        		
		        		for(int col=0;col<cols;col++)
		        		{
		        			
		        			read(col,r,patent);
		        			

		        		}
		        		
		        	}
		        }
		        
			return patents;
		
	} 
	
	public String readColumn(int col , Row r )
	{
		String value=null;
		switch(r.getCell(col).getCellType())
		{
		case NUMERIC:
			value=String.valueOf(r.getCell(col).getNumericCellValue());break;
		case STRING:value=r.getCell(col).getStringCellValue();break;
		case BLANK:value=null;logger.info("单元格为空");;break;
		case ERROR:value=null;logger.info("解析单元格出错");;break;
		}
		return value;
	}
	
	public void read(int col,Row r,Patent patent) throws Exception
	{
		
		switch(col)
		{
		case 0:
			try {
				patent.setFAN((int)Double.parseDouble(this.readColumn(col, r)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}

			break;
		case 1:
			try {
				patent.setXPN(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
			break;
		case 2:
			try {
				patent.setIsPCT(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}		
			break;
		case 3:
			try {
				patent.setIsGD(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
		
			break;
		case 4:
			try {
				patent.setTitle(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
		
			break;
		case 5:
			
			try {
				patent.setAbstruction(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
			
			break;
		case 6:
			try {
				patent.setPerson(this.readColumn(col, r));
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e2.getMessage());
			}
		
			break;
		case 7:
			
				
			try {
				patent.setArea(this.readColumn(col, r));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e1.getMessage());
			}
						
		    break;
		case 8:
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(r.getCell(col))) {
				java.util.Date date =r.getCell(col).getDateCellValue();
				SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date sdate = Date.valueOf(dff.format(date));
				patent.setPublic_date(sdate);
			}else
			{
				throw new Exception("Error in "+filename+" row:"+(r.getRowNum()+1)+" col:"+(col)+"日期格式:  yyyy-mm-dd");
			}
		
			break;
		case 9:
			patent.setPublic_area(this.readColumn(col, r));
	
			break;
		case 10:if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(r.getCell(col))) {
			java.util.Date date = r.getCell(col).getDateCellValue();
			SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
			String datestr=dff.format(date);
			java.sql.Date sdate = Date.valueOf(datestr);
			patent.setApply_date(sdate);
			String year=datestr.substring(0, 4);
			patent.setApply_year(Integer.parseInt(year));
		}else
		{
			throw new Exception("Error in "+filename+" row:"+(r.getRowNum()+1)+" col:"+(col)+"日期格式: yyyy-mm-dd");
		}
	
		break;
//		case 11:
//			try {
//				patent.setApply_year((int)Double.parseDouble(this.readColumn(col, r)));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				
//				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
//			}
//	
//			break;
		case 12:
			
			
				

			try {
				patent.setIPC(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}

					
					
		break;
		case 13:
			try {
				patent.setNPN((int)Double.parseDouble(this.readColumn(col, r)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
		
			break;
		case 14:
			try {
				patent.setMajor(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
	
			break;
		case 15:
			try {
				patent.setSecondary(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
	
			break;
		case 16:
			try {
				patent.setThird(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
			
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
	
			}
			break;
		case 17:
			try {
				patent.setEffect(this.readColumn(col, r));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
		
			break;
		case 18:{
			
			try {
				if(!(this.readColumn(col, r).equals("NULL")||this.readColumn(col, r).equals("")||this.readColumn(col, r)==null))
					patent.setScore( Double.parseDouble(this.readColumn(col, r)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(filename+": row"+(r.getRowNum()+1)+" col"+col+"   "+e.getMessage());
			}
			
			
		}break;
		
		
		
			
		
		}
			
	}

}
