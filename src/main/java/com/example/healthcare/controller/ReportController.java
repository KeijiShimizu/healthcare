package com.example.healthcare.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.healthcare.constant.UrlConst;
import com.example.healthcare.entity.UserHealthData;
import com.example.healthcare.repository.UserHealthDataRepository;
import com.example.healthcare.service.DateFormatterService;

import model.FormattedUserHealthData;

@Controller
public class ReportController {

//    @Autowired
    private UserHealthDataRepository userHealthDataRepository;
    
    private final DateFormatterService dateFormatterService;
    
    public ReportController(UserHealthDataRepository userHealthDataRepository, DateFormatterService dateFormatterService) {
        this.userHealthDataRepository = userHealthDataRepository;
        this.dateFormatterService = dateFormatterService;
    }

    @GetMapping(UrlConst.REPORT)
    public String showReport(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        
     // 現在の日付をフォーマット
//        LocalDateTime entryDate = LocalDateTime.now();
//        String formattedDate = dateFormatterService.format(entryDate);
//        model.addAttribute("formattedDate", formattedDate);

        // ユーザーの履歴データを取得し、フォーマット
        List<UserHealthData> healthDataList = userHealthDataRepository.findByUserId(userid);
        List<FormattedUserHealthData> formattedHealthDataList = healthDataList.stream()
                .map(data -> new FormattedUserHealthData(data, dateFormatterService.format(data.getEntryDate())))
                .collect(Collectors.toList());
        model.addAttribute("healthDataList", formattedHealthDataList);
        
//        // ユーザーの履歴データを取得
//        List<UserHealthData> healthDataList = userHealthDataRepository.findByUserId(userid);
//        model.addAttribute("healthDataList", healthDataList);

        return "report";
    }
}
