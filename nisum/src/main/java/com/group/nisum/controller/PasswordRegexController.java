package com.group.nisum.controller;

import com.group.nisum.DTO.RegexDTO;
import com.group.nisum.utils.PasswordRegexValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/passwordRegex")
public class PasswordRegexController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setNewRegex(@Valid @RequestBody RegexDTO regexDTO, BindingResult result){
        // Valid the fields
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errors.put("message", "The field " + err.getField()+ " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        PasswordRegexValidator.getInstance().setPasswordPattern(regexDTO.getRegex());
        return ResponseEntity.noContent().build();
    }
}
