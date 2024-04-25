package sadiva.mpi.platformbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sadiva.mpi.platformbackend.entity.PlatformUserEntity;
import sadiva.mpi.platformbackend.repo.UserRepo;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlatformUserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.username(), user.password(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(PlatformUserEntity user) {
        return user.roles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
