package com.zemel.tool.generate;

import com.zemel.framework.until.FileUtil;
import com.zemel.framework.until.StringUtil;
import com.zemel.tool.component.ToolComponent;
import com.zemel.tool.config.GenerateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/1/16 18:48
 */
@Service
public class Dbgenerate extends ToolComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dbgenerate.class);
    @Autowired
    private DataSource dataSource;
    @Autowired
    private GenerateConfig generateConfig;
    private GenerateJavaCode generateJavaCode;

    @Override
    protected boolean init() {
        return true;
    }

    @Override
    protected boolean init0() {
        if(!generateConfig.isGenerate())
            return false;
        LOGGER.error("===========autoCreateClass  start==============");
        if (generateConfig.isLombok())
            generateJavaCode = new GenerateJavaCodeLomBok();
        else
            generateJavaCode = new GenerateJavaCodeLomBok();
        generate();
        LOGGER.error("===========autoCreateClass  end==============");
        return true;
    }

    private void generate()
    {
        List<String> databases = generateConfig.getDatabases();
        for(String dbName:databases)
        {
            LOGGER.error(dbName+" generate start");
            generateDb(dbName);
            LOGGER.error(dbName+" generate end");
        }
    }

    private void generateDb(String dbName) {

        DatabaseInfo databaseInfo = new DatabaseInfo(dbName);
        DbJdbcConnectionHelp dbConnectionHelp = new DbJdbcConnectionHelp(databaseInfo,generateConfig);
        List<TableInfo> tableName = dbConnectionHelp.getTableInfos();
        tableName.stream().forEach(str -> {
            str.generate(generateJavaCode);
           String path  =str.getJavaClassPath();
           if(!StringUtil.isNullOrEmpty(path)) {
               LOGGER.error(path);
               FileUtil.writeFile(str.getJavaContent(), path);
           }
        });
    }
}
