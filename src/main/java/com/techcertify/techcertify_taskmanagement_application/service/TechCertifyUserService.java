package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.data.model.User;
import com.techcertify.techcertify_taskmanagement_application.data.repository.UserRepository;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.EmailRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.LoginRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.RegisterUserRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.AuthenticateUserResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.EmailAlreadyExistException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.security.service.JwtServices;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.TechCertifyMailService;
import com.techcertify.techcertify_taskmanagement_application.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechCertifyUserService implements UserService{
    private final UserRepository userRepository;
    private final TechCertifyMailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServices jwtServices;
    private final ModelMapper modelMapper;
    @Override
    public AuthenticateUserResponse register(RegisterUserRequest registerUserRequest) throws EmailAlreadyExistException {
        validateDuplicateEmail(registerUserRequest);
        User user = modelMapper.map(registerUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        EmailRequest emailRequest = Mapper.getEmailRequest(user);
        mailService.sendMail(emailRequest);
        var savedUser = userRepository.save(user);
        return AuthenticateUserResponse.builder()
                .id(savedUser.getId())
                .build();
    }

    private void validateDuplicateEmail(RegisterUserRequest registerUserRequest) throws EmailAlreadyExistException {
        boolean isEmailDuplicate =  userRepository.existsByEmail(registerUserRequest.getEmail());
        if (isEmailDuplicate) {
            throw new EmailAlreadyExistException("Email already exists");
        }
    }

    @Override
    public AuthenticateUserResponse authenticate(LoginRequest loginRequest) throws UserNotFoundException {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
                )
        );
        var foundUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new UserNotFoundException("user not found"));
        var jwtToken= jwtServices.generateToken(foundUser);
        return AuthenticateUserResponse.builder()
                .id(foundUser.getId())
                .token(jwtToken)
                .build();
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow( ()-> new UserNotFoundException(" user not found"));
    }


}
