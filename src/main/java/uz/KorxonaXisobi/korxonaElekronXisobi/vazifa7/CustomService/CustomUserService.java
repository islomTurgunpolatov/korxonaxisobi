package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.CustomService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserEntity;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserPositionRole;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Repositories.UserEntityRepository;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public CustomUserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user = userEntityRepository.findUserEntitiesByUsername(username);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                String userPositionRole = user.getPositionRole().getName();

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userPositionRole);

                return Collections.singletonList(authority);
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
