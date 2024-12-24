package sadiva.mpi.platformbackend.service;

import jakarta.validation.constraints.NotNull;
import jooq.sadiva.mpi.platformbackend.tables.pojos.PlatformUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.auth.AuthReq;
import sadiva.mpi.platformbackend.dto.auth.AuthRes;
import sadiva.mpi.platformbackend.dto.auth.LoginReq;
import sadiva.mpi.platformbackend.dto.user.RolesReq;
import sadiva.mpi.platformbackend.dto.user.UserCreateReq;
import sadiva.mpi.platformbackend.dto.user.UserRes;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PlatformUserEntity;
import sadiva.mpi.platformbackend.mapper.UserMapper;
import sadiva.mpi.platformbackend.repo.RoleRepo;
import sadiva.mpi.platformbackend.repo.UserRepo;
import sadiva.mpi.platformbackend.security.JwtUtil;
import sadiva.mpi.platformbackend.service.exception.ConflictException;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;


    public AuthRes createAuthenticationToken(@RequestBody LoginReq authReq) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.username(), authReq.password()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.username());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthRes(jwt, userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    @Transactional
    public void authorize(@RequestBody AuthReq authReq) {
        if (userRepo.isExistByUsername(authReq.username())) {
            throw new ConflictException("User already exists");
        }

        PlatformUser user = userRepo.create(authReq.username(), passwordEncoder.encode(authReq.password()));
        userRepo.assignRoles(user.getUserId(), authReq.roles());
    }

    public PageResponseDto<UserRes> getPaginated(Pageable pageable) {
        PageEntity<PlatformUserEntity> res = userRepo.getPaginated(pageable);
        return new PageResponseDto<>(
                userMapper.entitiesToDto(res.content()),
                res.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    @Transactional
    public void save(UserCreateReq userCreateReq) {
        PlatformUser user = userRepo.create(userCreateReq.username(), passwordEncoder.encode(userCreateReq.password()));
        roleRepo.batchSave(user.getUserId(), userCreateReq.roles());
    }

    @Transactional
    public void updateRoles(UUID userId, RolesReq rolesReq) {
        roleRepo.deleteByUserId(userId);
        roleRepo.batchSave(userId, rolesReq.roles());
    }

    public void delete(UUID userId) {
        userRepo.delete(userId);
    }

    public boolean updatePassword(@NotNull String username, String password) {
        return userRepo.updatePassword(username, passwordEncoder.encode(password)) == 1;
    }
}
