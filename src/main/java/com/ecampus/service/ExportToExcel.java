package com.ecampus.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExportToExcel {
    
    public void save(List<Object[]> studentdetails,
                    List<Object[]> coursedata,
                    List<Object[]> openfor,
                    List<Object[]> studentRequirements, 
                    List<Object[]> coursePreferences, 
                    List<Object[]> slotPreferences,
                    List<Object[]> instRequirements,
                    OutputStream outputStream) {
        
        try (Workbook workbook = new XSSFWorkbook()) {

            // --- SHEET 1: STUDENT DETAILS ---
            Sheet sheet1 = workbook.createSheet("StudentData");
            Row header1 = sheet1.createRow(0);
            header1.createCell(0).setCellValue("StudentID");
            header1.createCell(1).setCellValue("Name");
            header1.createCell(2).setCellValue("Program");
            header1.createCell(3).setCellValue("Semester");

            int rowIdx = 1;
            for (Object[] rowData : studentdetails) {
                Row row = sheet1.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
                row.createCell(3).setCellValue(rowData[3] != null ? rowData[3].toString() : "");
            }

            // --- SHEET 2: COURSE DATA ---
            Sheet sheet2 = workbook.createSheet("CourseData");
            Row header2 = sheet2.createRow(0);
            header2.createCell(0).setCellValue("CourseID");
            header2.createCell(1).setCellValue("CourseName");
            header2.createCell(2).setCellValue("Credits");
            header2.createCell(3).setCellValue("Slot");

            rowIdx = 1;
            for (Object[] rowData : coursedata) {
                Row row = sheet2.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
                row.createCell(3).setCellValue(rowData[3] != null ? rowData[3].toString() : "");
            }

            // --- SHEET 3: OPEN FOR ---
            Sheet sheet3 = workbook.createSheet("OpenFor");
            Row header3 = sheet3.createRow(0);
            header3.createCell(0).setCellValue("CourseID");
            header3.createCell(1).setCellValue("Program");
            header3.createCell(2).setCellValue("Semester");
            header3.createCell(3).setCellValue("Category");
            header3.createCell(4).setCellValue("Seats");

            rowIdx = 1;
            for (Object[] rowData : openfor) {
                Row row = sheet3.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
                row.createCell(3).setCellValue(rowData[3] != null ? rowData[3].toString() : "");
                row.createCell(4).setCellValue(rowData[4] != null ? rowData[4].toString() : "");
            }
            
            // --- SHEET 4: STUDENT REQUIREMENTS ---
            Sheet sheet4 = workbook.createSheet("StudentRequirements");
            Row header4 = sheet4.createRow(0);
            header4.createCell(0).setCellValue("StudentID");
            header4.createCell(1).setCellValue("Category");
            header4.createCell(2).setCellValue("Count");

            rowIdx = 1;
            for (Object[] rowData : studentRequirements) {
                Row row = sheet4.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
            }

            // --- SHEET 5: COURSE PREFERENCES ---
            Sheet sheet5 = workbook.createSheet("CoursePreferences");
            Row header5 = sheet5.createRow(0);
            header5.createCell(0).setCellValue("StudentID");
            header5.createCell(1).setCellValue("Slot");
            header5.createCell(2).setCellValue("CourseID");
            header5.createCell(3).setCellValue("PreferenceIndex");

            rowIdx = 1;
            for (Object[] rowData : coursePreferences) {
                Row row = sheet5.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
                row.createCell(3).setCellValue(rowData[3] != null ? rowData[3].toString() : "");
            }

            // --- SHEET 6: SLOT PREFERENCES ---
            Sheet sheet6 = workbook.createSheet("SlotPreferences");
            Row header6 = sheet6.createRow(0);
            header6.createCell(0).setCellValue("StudentID");
            header6.createCell(1).setCellValue("SlotNo");
            header6.createCell(2).setCellValue("PreferenceIndex");

            rowIdx = 1;
            for (Object[] rowData : slotPreferences) {
                Row row = sheet6.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
            }

            // --- SHEET 7: INSTITUTE REQUIREMENTS ---
            Sheet sheet7 = workbook.createSheet("InstituteRequirements");
            Row header7 = sheet7.createRow(0);
            header7.createCell(0).setCellValue("Program");
            header7.createCell(1).setCellValue("Semester");
            header7.createCell(2).setCellValue("Category");
            header7.createCell(3).setCellValue("Count");

            rowIdx = 1;
            for (Object[] rowData : instRequirements) {
                Row row = sheet7.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
                row.createCell(2).setCellValue(rowData[2] != null ? rowData[2].toString() : "");
                row.createCell(3).setCellValue(rowData[3] != null ? rowData[3].toString() : "");
            }

            // Write directly to the browser's output stream
            workbook.write(outputStream);
            System.out.println("Excel file streamed to browser successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAddDrop(List<Object[]> addDropPref,
                    List<Object[]> seats,
                    OutputStream outputStream) {
        
        try (Workbook workbook = new XSSFWorkbook()) {

            // --- SHEET 1: Add/Drop Preferences ---
            Sheet sheet1 = workbook.createSheet("Add_Drop Preferences");
            Row header1 = sheet1.createRow(0);
            header1.createCell(0).setCellValue("Time Stamp");
            header1.createCell(1).setCellValue("Email");
            header1.createCell(2).setCellValue("Student ID");
            header1.createCell(3).setCellValue("Name");
            header1.createCell(4).setCellValue("Program");
            header1.createCell(5).setCellValue("No. of Courses to Add");
            header1.createCell(6).setCellValue("Add p1");
            header1.createCell(7).setCellValue("Add p2");
            header1.createCell(8).setCellValue("Add p3");
            header1.createCell(9).setCellValue("Add p4");
            header1.createCell(10).setCellValue("Drop 1");
            header1.createCell(11).setCellValue("Drop 1 p1");
            header1.createCell(12).setCellValue("Drop 1 p2");
            header1.createCell(13).setCellValue("Drop 1 p3");
            header1.createCell(14).setCellValue("Drop 2");
            header1.createCell(15).setCellValue("Drop 2 p1");
            header1.createCell(16).setCellValue("Drop 2 p2");
            header1.createCell(17).setCellValue("Drop 2 p3");
            header1.createCell(18).setCellValue("Drop 3");
            header1.createCell(19).setCellValue("Drop 3 p1");
            header1.createCell(20).setCellValue("Drop 3 p2");
            header1.createCell(21).setCellValue("Drop 3 p3");

            int rowIdx = 1;
            for (Object[] rowData : addDropPref) {
                Row row = sheet1.createRow(rowIdx++);
                row.createCell(0).setCellValue(LocalDateTime.now().toString());
                row.createCell(1).setCellValue(rowData[0] != null ? rowData[0].toString() : "null");
                row.createCell(2).setCellValue(rowData[1] != null ? rowData[1].toString() : "null");
                row.createCell(3).setCellValue(rowData[2] != null ? rowData[2].toString() : "null");
                row.createCell(4).setCellValue(rowData[3] != null ? rowData[3].toString() : "null");
                row.createCell(5).setCellValue(rowData[4] != null ? rowData[4].toString() : "null");
                row.createCell(6).setCellValue(rowData[5] != null ? rowData[5].toString() : "null");
                row.createCell(7).setCellValue(rowData[6] != null ? rowData[6].toString() : "null");
                row.createCell(8).setCellValue(rowData[7] != null ? rowData[7].toString() : "null");
                row.createCell(9).setCellValue(rowData[8] != null ? rowData[8].toString() : "null");
                row.createCell(10).setCellValue(rowData[9] != null ? rowData[9].toString() : "null");
                row.createCell(11).setCellValue(rowData[10] != null ? rowData[10].toString() : "null");
                row.createCell(12).setCellValue(rowData[11] != null ? rowData[11].toString() : "null");
                row.createCell(13).setCellValue(rowData[12] != null ? rowData[12].toString() : "null");
                row.createCell(14).setCellValue(rowData[13] != null ? rowData[13].toString() : "null");
                row.createCell(15).setCellValue(rowData[14] != null ? rowData[14].toString() : "null");
                row.createCell(16).setCellValue(rowData[15] != null ? rowData[15].toString() : "null");
                row.createCell(17).setCellValue(rowData[16] != null ? rowData[16].toString() : "null");
                row.createCell(18).setCellValue(rowData[17] != null ? rowData[17].toString() : "null");
                row.createCell(19).setCellValue(rowData[18] != null ? rowData[18].toString() : "null");
                row.createCell(20).setCellValue(rowData[19] != null ? rowData[19].toString() : "null");
                row.createCell(21).setCellValue(rowData[20] != null ? rowData[20].toString() : "null");
            }

            // --- SHEET 2: SEATS ---
            Sheet sheet2 = workbook.createSheet("Seats Left");
            Row header2 = sheet2.createRow(0);
            header2.createCell(0).setCellValue("Course Code");
            header2.createCell(1).setCellValue("Seats Available");

            rowIdx = 1;
            for (Object[] rowData : seats) {
                Row row = sheet2.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowData[0] != null ? rowData[0].toString() : "");
                row.createCell(1).setCellValue(rowData[1] != null ? rowData[1].toString() : "");
            }

            // Write directly to the browser's output stream
            workbook.write(outputStream);
            System.out.println("Excel file streamed to browser successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
