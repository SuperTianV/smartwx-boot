package com.wxmp.config;

import com.wxmp.wxapi.process.WxMemoryCacheClient;
import com.wxmp.wxcms.domain.Account;
import com.wxmp.wxcms.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class WxAccountInitRunner implements ApplicationRunner {
    private Logger logger = LogManager.getLogger();

    @Autowired
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //设置登陆者默认公众号
        Account account = accountService.getSingleAccount();
        if (account != null) {
            WxMemoryCacheClient.setAccount(account.getAccount());
            System.out.println(account.toString());
        }
        logger.info("WxAccountInitRunner方法 执行完成");
    }
}
