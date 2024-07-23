package com.example.healthcare.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.healthcare.constant.UrlConst;
import com.example.healthcare.entity.User;
import com.example.healthcare.entity.UserHealthData;
import com.example.healthcare.repository.UserHealthDataRepository;
import com.example.healthcare.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HealthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private UserHealthDataRepository userHealthDataRepository;

    @GetMapping("/")
    public String showForm() {
        return "bmiForm";
    }
    
    @PostMapping(UrlConst.GUEST_RESULT)
    public String guestResultHealthMetrics(@RequestParam("weight") double weight,
                                         @RequestParam("height") double height,
                                         @RequestParam("age") int age,
                                         @RequestParam("gender") String gender,
                                         Model model) {
        // BMIの計算
        double bmi = weight / ((height / 100) * (height / 100));

        // 体脂肪率の計算
        double bodyFatPercentage;
        if ("male".equals(gender)) {
            bodyFatPercentage = (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            bodyFatPercentage = (1.20 * bmi) + (0.23 * age) - 5.4;
        }

        model.addAttribute("bmi", bmi);
        model.addAttribute("bodyFatPercentage", bodyFatPercentage);
        model.addAttribute("weight", weight);
        model.addAttribute("height", height);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);

        return "guestResult";
    }
    
    @GetMapping(UrlConst.USER_BMI_FORM)
    public String showUserForm(Model model) {
    	model.addAttribute("user", new User());
    	return "userBmiForm";
    }
    
    @PostMapping(UrlConst.RESULT)
    public String resultHealthMetrics(
    		@RequestParam("weight") double weight,
            @RequestParam("height") double height,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender,
            Model model,
            HttpSession session) {
    	
    	// 認証されたユーザーIDを取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();

        // ユーザーの健康指標を更新
        //userService.updateHealthMetricsByUserid(userid, age, height, weight, gender);
    	
    	// BMIの計算
        double bmi = weight / ((height / 100) * (height / 100));

        // 体脂肪率の計算
        double bodyFatPercentage;
        if ("male".equals(gender)) {
            bodyFatPercentage = (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            bodyFatPercentage = (1.20 * bmi) + (0.23 * age) - 5.4;
        }
        
     // 健康指標データを保存
        UserHealthData userHealthData = new UserHealthData();
        userHealthData.setUserId(userid);
        userHealthData.setWeight(weight);
        userHealthData.setHeight(height);
        userHealthData.setAge(age);
        userHealthData.setGender(gender);
        userHealthData.setBmi(bmi);
        userHealthData.setBodyFatPercentage(bodyFatPercentage);
        userHealthData.setEntryDate(LocalDateTime.now());

        userHealthDataRepository.save(userHealthData);
        
     // 結果をセッションに保存
        session.setAttribute("bmi", bmi);
        session.setAttribute("bodyFatPercentage", bodyFatPercentage);
        session.setAttribute("weight", weight);
        session.setAttribute("height", height);
        session.setAttribute("age", age);
        session.setAttribute("gender", gender);

        model.addAttribute("bmi", bmi);
        model.addAttribute("bodyFatPercentage", bodyFatPercentage);
        model.addAttribute("weight", weight);
        model.addAttribute("height", height);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);

        // 一週間の献立表を生成するロジックを追加します
        String[] mealPlan = generateWeeklyMealPlan();
        model.addAttribute("mealPlan", mealPlan);

        return "result";
    }
    
    @GetMapping("/resultBack")
    public String resultBack(Model model, HttpSession session) {
        model.addAttribute("bmi", session.getAttribute("bmi"));
        model.addAttribute("bodyFatPercentage", session.getAttribute("bodyFatPercentage"));
        model.addAttribute("weight", session.getAttribute("weight"));
        model.addAttribute("height", session.getAttribute("height"));
        model.addAttribute("age", session.getAttribute("age"));
        model.addAttribute("gender", session.getAttribute("gender"));
        model.addAttribute("mealPlan", session.getAttribute("mealPlan"));

        return "result";
    }


    private String[] generateWeeklyMealPlan() {
        // 簡単なサンプル献立表
        return new String[] {
            "1日目: 肉じゃが",
            "2日目: 豆腐ハンバーグ",
            "3日目: 野菜炒め",
            "4日目: 唐揚げ",
            "5日目: そうめん",
            "6日目: オムライス",
            "7日目: サバの味噌煮"
        };
    }
}
