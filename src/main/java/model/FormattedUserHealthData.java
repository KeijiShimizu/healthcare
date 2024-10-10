package model;

import com.example.healthcare.entity.UserHealthData;

import lombok.Data;

@Data
public class FormattedUserHealthData {
	private UserHealthData userHealthData;
    private String formattedDate;
    
    public FormattedUserHealthData(UserHealthData userHealthData, String formattedDate) {
        this.userHealthData = userHealthData;
        this.formattedDate = formattedDate;
    }
}
