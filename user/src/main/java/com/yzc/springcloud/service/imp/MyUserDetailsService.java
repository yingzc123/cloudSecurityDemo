package com.yzc.springcloud.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{


    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String cal) throws UsernameNotFoundException {
        com.yzc.springcloud.entity.User user = userService.getOne
                (new QueryWrapper<com.yzc.springcloud.entity.User>().eq("cal", cal));
        if(null == user) {
            throw new UsernameNotFoundException("用户为空");
        }
        List<GrantedAuthority> auth= AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User(user.getCal(),new BCryptPasswordEncoder().encode(user.getLoginPwd()),auth);
    }
}
