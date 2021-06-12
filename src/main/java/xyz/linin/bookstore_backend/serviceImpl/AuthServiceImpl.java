package xyz.linin.bookstore_backend.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.SecurityConstants;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.AuthResult;
import xyz.linin.bookstore_backend.dto.LoginCredentials;
import xyz.linin.bookstore_backend.entity.AuthUser;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import xyz.linin.bookstore_backend.utils.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    public AuthResult login(LoginCredentials loginCredentials) {
        if (!userDao.checkPassword(loginCredentials.getPassword(), loginCredentials.getName())) {
            throw new BadCredentialsException("用户名或密码错误");
        }
        User user = userDao.findByName(loginCredentials.getName());
        AuthUser authUser = new AuthUser(user);
        List<String> authorities = authUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtUtils.createToken(user.getName(), user.getId().toString(), authorities);
        return new AuthResult(user, SecurityConstants.TOKEN_PREFIX + token);
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getPrincipal().toString();
        return userDao.findByName(name);
    }
}
