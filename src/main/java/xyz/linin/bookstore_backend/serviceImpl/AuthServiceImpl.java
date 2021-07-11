package xyz.linin.bookstore_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.linin.bookstore_backend.constants.Role;
import xyz.linin.bookstore_backend.constants.SecurityConstants;
import xyz.linin.bookstore_backend.dao.UserDao;
import xyz.linin.bookstore_backend.dto.AuthResult;
import xyz.linin.bookstore_backend.dto.LoginCredentials;
import xyz.linin.bookstore_backend.entity.AuthUser;
import xyz.linin.bookstore_backend.entity.User;
import xyz.linin.bookstore_backend.exception.BusinessLogicException;
import xyz.linin.bookstore_backend.service.AuthService;
import xyz.linin.bookstore_backend.service.UserService;
import xyz.linin.bookstore_backend.utils.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    public AuthResult login(LoginCredentials loginCredentials) {
        if (!userService.checkPassword(loginCredentials.getPassword(), loginCredentials.getName())) {
            throw new BadCredentialsException("学工号或密码错误");
        }
        User user = userDao.findByName(loginCredentials.getName());
        if (user.getRole() == Role.disable) throw new BusinessLogicException("你已被禁用");
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
        String idNo = authentication.getPrincipal().toString();
        return userDao.findByName(idNo);
    }
}
