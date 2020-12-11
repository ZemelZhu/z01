package com.zemel.tool.generate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: zemel
 * @Date: 2020/1/16 19:43
 */

public class DbMybatisDbConnectionHelp extends AbstractDbConnectionHelp {
    private DataSource dataSource;
    private String packageName;
    public DbMybatisDbConnectionHelp(DataSource dataSource,String packageName) {
        super(null, null);
        this.packageName = packageName;
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
