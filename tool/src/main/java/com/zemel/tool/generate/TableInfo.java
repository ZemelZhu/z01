package com.zemel.tool.generate;

import com.zemel.framework.until.FormatUtil;
import com.zemel.framework.until.StringUtil;
import com.zemel.tool.config.GenerateConfig;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/1/16 18:53
 */
@Data
public class TableInfo {
    private List<String> columnNames;
    private List<String> columnTypes;
    private List<String> columnComments;
    private String tableName;
    private String javaClassName;
    /** 系统全路径 */
    private String javaClassPath;
    /** 内容 */
    private String javaContent;
    private String generatePackage;
    private int columnCount;
    /** 是否导入日期 */
    private boolean isImportDate;
    private boolean isImportBigDecimal;
    private boolean commonTable;
    /** 是否IdType */
    private boolean isAutoIncrease;

    public TableInfo(String tableName) {
        this.tableName = tableName;
        columnNames = new ArrayList<String>();
        columnTypes = new ArrayList<String>();
        columnComments = new ArrayList<String>();
        isImportDate = false;
        isImportBigDecimal = false;
        isAutoIncrease =  false;
    }


    public void analize(String columnName, String columnType, String columnComment) throws Exception {
        if (StringUtil.isNullOrEmpty(columnName, columnType, columnComment))
        {
            System.out.println(columnName+"  "+columnType+"  "+columnComment);
            throw new Exception("tableInfo columName not null "+columnName+"  "+tableName);
        }
        if (!isImportDate && columnType.equals("Date"))
            isImportDate = true;
        if (!isImportBigDecimal && columnType.equals("BigDecimal"))
            isImportBigDecimal = true;
    }


    public void generate(GenerateJavaCode generateJavaCode) {

        setJavaContent(generateJavaCode.generate(this));
    }

    public void initPath(GenerateConfig generateConfig, DatabaseInfo info) {
        String javaName = tableName;
        for(Map.Entry<String,String> str:generateConfig.getPrefix().entrySet())
        {
            if(tableName.contains(str.getKey()))
            {
                javaName = tableName.substring(str.getKey().length())+str.getValue();
                String name = FormatUtil.toUppercase4FirstLetter(javaName);
                this.javaClassName =StringUtil.initcap(name);
                generatePackage = info.getPackageName();
                this.javaClassPath = info.getGeneratePath()+javaClassName+".java";
                return ;
            }
        }
        for(Map.Entry<String,String> str:generateConfig.getCommonPrefix().entrySet())
        {
            if(tableName.contains(str.getKey()))
            {
                javaName = tableName.substring(str.getKey().length())+str.getValue();
                String name = FormatUtil.toUppercase4FirstLetter(javaName);
                this.javaClassName =StringUtil.initcap(name);
                generatePackage = info.getCommonPackageName();
                this.javaClassPath = info.getCommonGeneratePath()+javaClassName+".java";
                return ;
            }
        }
    }
}
