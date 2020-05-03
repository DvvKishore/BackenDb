package com.example.backenddb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backenddb.pojo.StudentPojo;
import com.example.backenddb.service.StudentService;
@RestController
@CrossOrigin
@RequestMapping("excel")
public class ExcelCreate {
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value="/download",method=RequestMethod.GET)		
	public ResponseEntity<ByteArrayResource> DataBaseTowriteinexcelAndDownload() throws IOException {
		List<StudentPojo> students=studentService.getallrecordsDownload();
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet= workbook.createSheet();
		XSSFRow row=sheet.createRow(0);
		
		XSSFCell cell=row.createCell(0);
		 cell.setCellValue("ID");
		 XSSFCell cell1=row.createCell(1);
		 cell1.setCellValue("Name");
		 XSSFCell cell2=row.createCell(2);
		 cell2.setCellValue("Age");
		 XSSFCell cell3=row.createCell(3);
		 cell3.setCellValue("Qual");
		 XSSFCell cell4=row.createCell(4);
		 cell4.setCellValue("PhoneNo");
		  
		
		 int rownum = 1;
		
		for (StudentPojo studentPojo : students) {
			
			XSSFRow row1=sheet.createRow(rownum);
			
			XSSFCell id=row1.createCell(0);
			 id.setCellValue(studentPojo.getId());
			 XSSFCell name=row1.createCell(1);
			 name.setCellValue(studentPojo.getName());
			 XSSFCell age=row1.createCell(2);
			 age.setCellValue(studentPojo.getAge());
			 XSSFCell qual=row1.createCell(3);
			 qual.setCellValue(studentPojo.getQual());
			 XSSFCell phone=row1.createCell(4);
			 phone.setCellValue(studentPojo.getPhoneno());

	           rownum++;    
	            }
		 try {
				FileOutputStream fos=new FileOutputStream("F:\\FileUploads\\Excels\\student.xlsx");
				workbook.write(fos);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 Path path=Paths.get("F:\\FileUploads\\Excels\\student.xlsx");
			byte[] bytes=Files.readAllBytes(path);
			ByteArrayResource bar=new ByteArrayResource(bytes);
			return ResponseEntity.ok()
					.header("content-disposition", "attachment;filename=student.xlsx")
					.body(bar);
	        
		
	}
	
	public void createnewexcel()  {
		XSSFWorkbook workbook=new XSSFWorkbook();
		
		XSSFSheet sheet=workbook.createSheet();
		
		 XSSFRow row=sheet.createRow(0);
		 
		  XSSFCell cell=row.createCell(0);
		 // cell.setCellValue("hello");
		  
		  try {
			FileOutputStream fos=new FileOutputStream("F:\\FileUploads\\Excels\\java.xlsx");
			workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void fileUploadAndReadExcel(MultipartFile file) throws IllegalStateException, IOException
	{

	    	System.out.println(file.getOriginalFilename());
			String filename=file.getOriginalFilename();
			File rfile= new File("F:\\FileUploads\\Excels\\"+filename);
			file.transferTo(rfile);

			
		
    	FileInputStream filetoread=new FileInputStream("F:\\FileUploads\\Excels\\"+filename);
    	XSSFWorkbook wb=new XSSFWorkbook(filetoread);
    	XSSFSheet sheet=wb.getSheetAt(0);
    	
    	
    	 int rowno=sheet.getPhysicalNumberOfRows();
    	
       for(int j=1;j<=rowno;j++)
        {
    		StudentPojo std=new StudentPojo();	
    	   XSSFRow row=sheet.getRow(j);
    	   XSSFCell cell;
            int i=0;
           if(row!=null)
           {
            Iterator cells=row.cellIterator();
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();   
                if(i==0)
    				 std.setName(cell.getStringCellValue());
    			else if(i==1)
    				 std.setAge(Integer.parseInt(cell.getRawValue()));
    			else if(i==2)
                    std.setQual(cell.getStringCellValue());
    			else if(i==3)
                    std.setPhoneno(cell.getRawValue()+"");
                i++;
            }
            studentService.studentsave(std); 
            }
        }
	}
    	}
    
    	
	
	


