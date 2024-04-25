package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import sadiva.mpi.platformbackend.dto.auth.AuthReq;
import sadiva.mpi.platformbackend.dto.auth.AuthRes;
import sadiva.mpi.platformbackend.repo.UserRepo;
import sadiva.mpi.platformbackend.security.JwtUtil;
import sadiva.mpi.platformbackend.service.exception.ConflictException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public AuthRes createAuthenticationToken(@RequestBody AuthReq authReq) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.username(), authReq.password()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.username());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthRes(jwt);
    }

    @Transactional
    public void authorize(@RequestBody AuthReq authReq) {
        if (userRepo.isExistByUsername(authReq.username())) {
            throw new ConflictException("User already exists");
        };
        userRepo.create(authReq.username(), passwordEncoder.encode(authReq.password()));
    }
}
