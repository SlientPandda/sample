package com.wuhao.tips.mock.service.Impl;

import com.wuhao.tips.mock.dao.SalesDao;
import com.wuhao.tips.mock.dao.UserDao;
import com.wuhao.tips.mock.entity.User;
import com.wuhao.tips.mock.exception.DAOException;
import com.wuhao.tips.mock.exception.ValidationException;
import com.wuhao.tips.mock.util.FindUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @InjectMocks 注解会将@mock和@spy注解的对象注入到此注解修饰的对象中
 */
class RegistrationServiceImplTest {

    @InjectMocks
    @Spy
    private RegistrationServiceImpl registrationService;
    @Mock
    private UserDao userDao;
    @Mock
    private SalesDao salesDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() throws Exception {
        String name = null;
        String phone = "15071271412";
        try {
            registrationService.register(name, phone);
            Assertions.fail("这里会挂掉");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof ValidationException);
        }

        name = "一直游到海水变蓝";
        phone = null;
        try {
            registrationService.register(name, phone);
            Assertions.fail("这里会挂掉");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof ValidationException);
        }

        phone = "15071271412";
        MockedStatic<FindUtils> staticService = Mockito.mockStatic(FindUtils.class);
        staticService.when(() -> FindUtils.getAreaCode("15071271412")).thenReturn("a");
        staticService.when(() -> FindUtils.getOperatorCode("15071271412")).thenReturn("b");
        // 1.数据库操作正常
        Mockito.when(salesDao.findRep("a", "b")).thenCallRealMethod();
        Mockito.when(userDao.save(name, phone, "Echo")).thenCallRealMethod();
        User user = registrationService.register(name, phone);
        Assertions.assertEquals("Echo", user.getRepId());

        // 2.数据库操作异常
        Mockito.when(userDao.save(name, phone, "Echo")).thenThrow(new SQLException());
        try {
            registrationService.register(name, phone);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof DAOException);
        }
    }
}