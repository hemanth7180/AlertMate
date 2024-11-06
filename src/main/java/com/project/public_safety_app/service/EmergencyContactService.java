package com.project.public_safety_app.service;

import com.project.public_safety_app.dto.EmergencyContactDto;
import com.project.public_safety_app.model.User;

import java.util.List;

public interface EmergencyContactService {
    EmergencyContactDto saveContacts(EmergencyContactDto contacts);
    void deleteContacts(EmergencyContactDto contactDto);
    List<EmergencyContactDto> getAllContacts();
    EmergencyContactDto updateContact(EmergencyContactDto contactDto);
    List<EmergencyContactDto> findByUser(User user);
}
