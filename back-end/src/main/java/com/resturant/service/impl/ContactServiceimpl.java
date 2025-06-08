package com.resturant.service.impl;

import com.resturant.dao.ContactRepo;
import com.resturant.dto.ContactInfoDto;
import com.resturant.model.ContactInfo;
import com.resturant.model.security.Users;
import com.resturant.service.ContactService;
import com.resturant.service.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContactServiceimpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;
    @Override
    public String saveMessage(ContactInfoDto contactInfoDto) {
        if(Objects.isNull(contactInfoDto)){
            throw new RuntimeException("invalid.message");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = (Users) authentication.getPrincipal();
        ContactInfo contactInfo = ContactMapper.contact.DtoToEntity(contactInfoDto);
        contactInfo.setUsers(users);
        if(Objects.nonNull(contactInfo.getId())){
            throw new RuntimeException("invalid.id");
        }
        if (contactInfo.getEmail()==null||contactInfo.getMessage()==null||contactInfo.getName()==null||contactInfo.getSubject()==null){
            throw new RuntimeException("invalid.message");
        }


        contactRepo.save(contactInfo);

        return "Thanks for contacting us" ;
    }
}
