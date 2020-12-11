package com.zemel.tool.generate;

import com.zemel.framework.until.FormatUtil;
import com.zemel.tool.config.GenerateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author: zemel
 * @Date: 2020/1/16 18:56
 */
public abstract class AbstractDbConnectionHelp {
    static final Logger LOGGER = LoggerFactory.getLogger(AbstractDbConnectionHelp.class);
   private  GenerateConfig generateConfig;
    protected DatabaseInfo info;
    public AbstractDbConnectionHelp(DatabaseInfo databaseInfo, GenerateConfig generateConfig) {
        this.info = databaseInfo;
        this.generateConfig = generateConfig;
    }

    /**
     * 获取数据中所有表名
     *
     * @return
     */
    public List<TableInfo> getTableInfos() {
        List<TableInfo> tableNames = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = getConnection();
            // 获取数据库的元数据
            DatabaseMetaData db = connection.getMetaData();
            String url = db.getURL();
            String dataBaseName = url.substring(28, url.indexOf("?"));
            stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            // 从元数据中获取到所有的表名
            rs = db.getTables(dataBaseName, null, null, null);
            while (rs.next()) {
                String tableName=rs.getString("TABLE_NAME");
//                System.out.println(tt+"   "+tp);
//                String tableName = rs.getString(3);
//                LOGGER.error(tableName+"   tableName ");
                TableInfo tableInfo = getTableInfo(tableName,stmt);
                if(tableInfo==null)
                {
                    LOGGER.error("generate table error  tableName:"+tableName);
                    continue;
                }
                tableNames.add(tableInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }
    public TableInfo getTableInfo(String tableName)
    {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            stmt = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            // 从元数据中获取到所有的表名
            TableInfo tableInfo = getTableInfo(tableName,stmt);
            return tableInfo;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    private TableInfo getTableInfo(String tableName, Statement stmt) {
        try {
            String showTableSql = "show full columns from " + tableName;
            // 获取表中字段注释
            ResultSet rs = stmt.executeQuery(showTableSql);
            int columnCount = 0;
            TableInfo tableInfo = new TableInfo(tableName);
            while (rs.next()) {
                String columnName = FormatUtil.toUppercase4FirstLetter(rs.getString("Field"));
                String columnType = SqlFormatUtil.typeConversion(rs.getString("Type"));
                String columnComment = rs.getString("Comment");
                tableInfo.analize(columnName, columnType, columnComment);
                tableInfo.getColumnNames().add(columnName);
                tableInfo.getColumnTypes().add(columnType);
                tableInfo.getColumnComments().add(columnComment);
                ResultSetMetaData metaData = rs.getMetaData();
                if(metaData!=null)
                {
                    boolean autoIncrement = metaData.isAutoIncrement(1);
                    if(autoIncrement||columnComment.contains("自增"))
                        tableInfo.setAutoIncrease(true);
                }
                // System.out.println(columnName + " " + columnType + " " +
                // typeConversion + " " + columnComment);
                columnCount++;
            }
            if(columnCount==0)
                return null;
            tableInfo.setColumnCount(columnCount);
            tableInfo.initPath(generateConfig,info);
            return tableInfo;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取数据库连接
     *
     * @return
     */
    public abstract Connection getConnection();
}
