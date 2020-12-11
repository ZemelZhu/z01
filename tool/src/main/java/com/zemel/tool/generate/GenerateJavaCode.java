package com.zemel.tool.generate;

import com.zemel.framework.until.StringUtil;
import com.zemel.framework.until.TimeUtil;

/**
 * @Author: zemel
 * @Date: 2020/1/16 18:53
 */
public class GenerateJavaCode {
    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    protected void processAllAttrs(StringBuilder sb, TableInfo tableInfo) {

        for (int i = 0; i < tableInfo.getColumnCount(); i++) {
            String name = tableInfo.getColumnNames().get(i);
            sb.append("\t/** " + tableInfo.getColumnComments().get(i) + " */\n");
            if(name.equals("id")&&tableInfo.isAutoIncrease())
            {
                sb.append("\t@TableId(type= IdType.AUTO)"+ "\r\n");
            }
            sb.append("\tprivate " + tableInfo.getColumnTypes().get(i) + " " + name + ";"
                    + "\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    protected void processAllMethod(StringBuilder sb, TableInfo tableInfo) {

        for (int i = 0; i < tableInfo.getColumnCount(); i++) {
            String columnName = tableInfo.getColumnNames().get(i);
            String columnType = tableInfo.getColumnTypes().get(i);
            String name = StringUtil.initcap(columnName);
            sb.append("\tpublic void set" + name + "(" + columnType + " " + columnName + "){\r\n");
            sb.append("\t\tthis." + columnName + "=" + columnName + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + columnType + " get" + name + "(){\r\n");
            sb.append("\t\treturn " + columnName + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    public String generate(TableInfo tableInfo) {
        StringBuilder sb = new StringBuilder();
        // 包
        sb.append("package " + tableInfo.getGeneratePackage() + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        addImport(sb,tableInfo);
        processComment(sb,tableInfo);
        addAnnotation(sb,tableInfo);
        // 实体部分
        sb.append("public class " + tableInfo.getJavaClassName() + " {\r\n");
        processAllAttrs(sb, tableInfo);// 属性
        processAllMethod(sb, tableInfo);// get set方法
        sb.append("}\r\n");
        return sb.toString();
    }

    protected void addImport(StringBuilder sb, TableInfo tableInfo) {
        if (tableInfo.isImportDate()) {
            sb.append("import java.util.Date;\r\n");
        }
        if (tableInfo.isImportBigDecimal()) {
            sb.append("import java.math.BigDecimal;\r\n");
        }
    }

    protected void addAnnotation(StringBuilder sb,TableInfo tableInfo) {
    }

    protected void processComment(StringBuilder sb,TableInfo tableInfo)
    {
        // 注释部分
        sb.append("/**\r\n");
        sb.append(" * " + tableInfo.getJavaClassName() + " 实体类\r\n");
        sb.append(" * @Date:" + TimeUtil.nowDateString() + "\r\n");
        sb.append(" * @Authod zemel\r\n");
        sb.append("*/\n");
    }
}
