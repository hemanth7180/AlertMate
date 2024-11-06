package com.project.public_safety_app.service;

import com.project.public_safety_app.dto.EmergencyContactDto;
import com.project.public_safety_app.model.EmergencyContacts;
import com.project.public_safety_app.model.User;
import com.project.public_safety_app.repository.EmergencyContactRepository;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService{

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    public EmergencyContactDto saveContacts(EmergencyContactDto contacts) {
        EmergencyContacts ec= EntityUtil.convertSTOToECT(contacts);
        return EntityUtil.convertECToDTO(emergencyContactRepository.save(ec));
    }

    public void deleteContacts(EmergencyContactDto contactDto) {
        emergencyContactRepository.delete(EntityUtil.convertSTOToECT(contactDto));
    }

    public List<EmergencyContactDto> getAllContacts() {
        return emergencyContactRepository.findAll().stream().map(EntityUtil::convertECToDTO).toList();
    }

    public EmergencyContactDto updateContact(EmergencyContactDto contactDto) {
        return EntityUtil.convertECToDTO(emergencyContactRepository.save(EntityUtil.convertSTOToECT(contactDto)));
    }

    public List<EmergencyContactDto> findByUser(User user) {
        return emergencyContactRepository.findByUser(user).stream().map(EntityUtil::convertECToDTO).toList();
    }

}

