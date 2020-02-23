package com.ravensoftware.reporting.user.control;

import com.ravensoftware.reporting.config.Constants;
import com.ravensoftware.reporting.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by bilga on 20-02-2020
 */
@Component
public class UserControl {

    private UserRepository userRepository;

    public UserControl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String save(User user) {
        String validation = checkEmailAndUsernameRegex(user);
        if (validation != null){
            return validation;
        }
        userRepository.save(user);
        return user.getEmail();
    }
    private String checkEmailAndUsernameRegex(User user) {
        if(user.getEmail()==null){
            return "Başarısız! Email giriniz!";
        }
        if (!user.getEmail().matches(Constants.EMAIL_REGEX)){
            return "Gecerli bir mail adresi giriniz!";
        }
        return null;
    }
    public User findByEmail(String email){
        User byId = userRepository.findByEmail(email);
        return byId;
    }
}
