package com.ecampus.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ecampus.repository.*;
import com.ecampus.service.*;

@Controller
@RequestMapping("/admin/addDropDownload")
public class AddDropDownloadController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TermCourseAvailableForRepository trmCrsAvailableForRepo;

    @Autowired
    private ExportToExcel exportToExcel;
    
    @GetMapping
    public void download(HttpServletResponse response) throws IOException {

        List<Object[]> addDropPref = userRepo.getForAddDrop();
        List<Object[]> seats = trmCrsAvailableForRepo.getForAddDrop();

        // 1. Set the content type for Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        
        // 2. Set the header to trigger the download dialog
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=AddDrop_Preferences.xlsx";
        response.setHeader(headerKey, headerValue);

        // 3. Pass the response output stream to the service
        exportToExcel.saveAddDrop(addDropPref, seats, response.getOutputStream());

    }

}
