package com.zemel.tool.generate;

import com.zemel.tool.config.GenerateConfig;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: zemel
 * @Date: 2020/1/16 19:40
 */
public class DbJdbcConnectionHelp extends AbstractDbConnectionHelp {

    public DbJdbcConnectionHelp(DatabaseInfo databaseInfo, GenerateConfig generateConfig) {
        super(databaseInfo,generateConfig);
    }


    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(info.getDriver());
            conn = DriverManager.getConnection(info.getUrl(), info.getName(), info.getPassword());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return conn;
    }
}
