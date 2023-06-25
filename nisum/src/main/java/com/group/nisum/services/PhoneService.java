package com.group.nisum.services;

import com.group.nisum.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<Phone> getAll();
    Phone savePhone(Phone phone);
    List<Phone> savePhones(List<Phone> phones);
    Optional<Phone> getPhoneById(Long id);
    void deletePhoneById(Long id);
}
