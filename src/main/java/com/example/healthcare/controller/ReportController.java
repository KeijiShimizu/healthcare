package com.example.healthcare.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.healthcare.constant.UrlConst;
import com.example.healthcare.entity.UserHealthData;
import com.example.healthcare.repository.UserHealthDataRepository;

@Controller
public class ReportController {

    @Autowired
    private UserHealthDataRepository userHealthDataRepository;

    @GetMapping(UrlConst.REPORT)
    public String showReport(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        
        // 日付データのフォーマット
        LocalDateTime entryDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = entryDate.format(formatter);
        model.addAttribute("formattedDate", formattedDate);

        // ユーザーの履歴データを取得
        List<UserHealthData> healthDataList = userHealthDataRepository.findByUserId(userid);
        model.addAttribute("healthDataList", healthDataList);

        return "report";
    }
}
