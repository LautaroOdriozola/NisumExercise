package com.group.nisum.controller;

import com.group.nisum.DTO.AuthRequest;
import com.group.nisum.DTO.AuthResponse;
import com.group.nisum.DTO.PhoneDTO;
import com.group.nisum.DTO.UserDTO;
import com.group.nisum.entity.Phone;
import com.group.nisum.entity.User;
import com.group.nisum.jwt.JwtTokenUtil;
import com.group.nisum.services.PhoneService;
import com.group.nisum.services.UserService;
import com.group.nisum.utils.PasswordRegexValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO, BindingResult result){
        // Valid the fields
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errors.put("message", "The field " + err.getField()+ " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        // Valid the password by the actual regex
        if(!PasswordRegexValidator.getInstance().isValid(userDTO.getPassword())){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "The password does not meet the requirements.");
            return ResponseEntity.badRequest().body(errors);
        }

        // Valid if the user exists
        Optional<User> userFound = userService.getUserByEmail(userDTO.getEmail());
        if(userFound.isPresent()){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "There is already a registered user with that email.");
            return ResponseEntity.badRequest().body(errors);
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setActive(true);
        String accessToken = jwtUtil.generateAccessToken(user);
        user = userService.updateTokenAndLastLogin(user, accessToken);

        User userCreated = userService.saveUser(user);

        // Create phones
        List<Phone> phones = userDTO.getPhones().stream()
                .map(phoneDTO -> modelMapper.map(phoneDTO,Phone.class))
                .collect(Collectors.toList());
        phones.forEach(phone -> phone.setUser(userCreated));
        phones = phoneService.savePhones(phones);
        userCreated.setPhones(phones);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(userCreated, UserDTO.class));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            userService.updateTokenAndLastLogin(user, accessToken);

            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/desactivate/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> desactivate(@PathVariable Long id){
        Optional<User> optionalUser = userService.getUserById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);
            user = userService.saveUser(user);
            return ResponseEntity.noContent().build();
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "The indicated User does not exist in the database.");
            return ResponseEntity.badRequest().body(errors);
        }
    }
}
