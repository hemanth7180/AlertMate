package com.project.public_safety_app.util;

import com.project.public_safety_app.dto.*;
import com.project.public_safety_app.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtil {
    public static UserDto convertToDTO(User user) {

        UserDto userDto=new UserDto(user.getUserId(),user.getUserName(),user.getDOB(),user.getEmail()
                ,user.getUserPhoneNumber(),user.getContacts(),user.getPassword(),user.getDiscussions()
        ,user.getSafetyCheckIns(),user.getSafetyReports(),user.getQuizzes());

        return userDto;
    }

    public static User convertToEntity(UserDto userDto){
        User user=new User(userDto.getUserId(), userDto.getUserName(),userDto.getDOB(),userDto.getEmail()
                ,userDto.getUserPhoneNumber(),userDto.getContacts(),userDto.getPassword(),userDto.getDiscussions()
                ,userDto.getSafetyCheckIns(),userDto.getSafetyReports(),userDto.getQuizzes());
        return user;
    }


    public static EmergencyContactDto convertECToDTO(EmergencyContacts contact) {
        EmergencyContactDto dto = new EmergencyContactDto();
        dto.setEmergency_id(contact.getEmergency_id());
        dto.setContactName(contact.getContactName());
        dto.setContactPhoneNumber(contact.getContactPhoneNumber());
        return dto;
    }

    public static EmergencyContacts convertSTOToECT(EmergencyContactDto contactDto){
        EmergencyContacts emergencyContacts=new EmergencyContacts(contactDto.getEmergency_id(),
                contactDto.getUser(),contactDto.getContactName(), contactDto.getContactPhoneNumber());
        return emergencyContacts;
    }



}

