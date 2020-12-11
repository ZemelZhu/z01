package com.zemel.tool.generate;

/**
 * @Author: zemel
 * @Date: 2020/1/16 19:18
 */
public class GenerateJavaCodeLomBok extends GenerateJavaCode {
    protected void processAllMethod(StringBuilder sb, TableInfo tableInfo)
    {

    }
    @Override
    protected void addAnnotation(StringBuilder sb,TableInfo tableInfo) {
        sb.append("@Data\n");
        sb.append("@TableName(\""+tableInfo.getTableName()+"\")\n");
    }
    @Override
    protected void addImport(StringBuilder sb, TableInfo tableInfo)
    {
        super.addImport(sb,tableInfo);
        if(tableInfo.isAutoIncrease()) {
            sb.append("import com.baomidou.mybatisplus.annotation.IdType;\r\n");
            sb.append("import com.baomidou.mybatisplus.annotation.TableId;\r\n");
        }
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\r\n");
        sb.append("import lombok.Data;\r\n");
    }
}
