package com.zemel.tool.generate;

import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/8/1 11:03
 */
@Service
public class GenerateMybatisMapper implements IGenerate{
    public String generate(TableInfo tableInfo) {
        StringBuilder sb = new StringBuilder();
        // 包
        sb.append("package " + tableInfo.getGeneratePackage() + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        addImport(sb,tableInfo);
        // 实体部分
        sb.append("public interface " + tableInfo.getJavaClassName() + "Mapper extends BaseMapper<").append(tableInfo.getJavaClassName()).append("> {\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }
    protected void addImport(StringBuilder sb, TableInfo tableInfo) {
            sb.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\r\n");
            sb.append("import ").append(tableInfo.getGeneratePackage()).append(";\r\n");
    }
}
