package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
