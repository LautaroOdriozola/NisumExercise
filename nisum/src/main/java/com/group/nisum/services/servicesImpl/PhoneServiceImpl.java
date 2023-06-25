package com.group.nisum.services.servicesImpl;

import com.group.nisum.entity.Phone;
import com.group.nisum.repository.PhoneRepository;
import com.group.nisum.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> getAll() {
        return null;
    }

    @Override
    public Phone savePhone(Phone phone) {
        return null;
    }

    @Override
    public List<Phone> savePhones(List<Phone> phones){
        return phoneRepository.saveAll(phones);
    }

    @Override
    public Optional<Phone> getPhoneById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deletePhoneById(Long id) {

    }
}
