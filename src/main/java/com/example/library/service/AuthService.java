package com.example.library.service;

import com.example.library.RolesEnum;
import com.example.library.config.CustomUserDetails;
import com.example.library.dto.LoginRequestDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterRequestDto;
import com.example.library.entity.AppUser;
import com.example.library.entity.Roles;
import com.example.library.repository.AppUserRepository;
import com.example.library.repository.RolesRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {


    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, RolesRepository rolesRepository, AuthenticationManager authenticationManager, JwtService jwtService) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public void register(RegisterRequestDto registerRequestDto) {

        if (appUserRepository.existsAppUserByEmail(registerRequestDto.email())) {

            throw new RuntimeException("Użytkownik istnieje");

        }

        AppUser appUser = new AppUser();

        appUser.setEmail(registerRequestDto.email());
        appUser.setFirstName(registerRequestDto.firstName());
        appUser.setLastName(registerRequestDto.lastName());

        appUser.setPassword(passwordEncoder.encode(registerRequestDto.password()));

        appUser.setEnabled(false);


        Roles defaultRole = rolesRepository
                .findByRole(RolesEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Rola domyślna nie istnieje"));

        appUser.getRoles().add(defaultRole);

        appUserRepository.save(appUser);


    }


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        List<String> rolesList = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return new LoginResponseDto(token, userDetails.getUsername(), rolesList);

    }

}
