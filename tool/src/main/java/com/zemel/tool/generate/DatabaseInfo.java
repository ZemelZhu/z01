package com.zemel.tool.generate;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/14 21:41
 */
@Data
public class DatabaseInfo {
    public String url;
    public String name;
    public String password;
    public String driver;
    private String packageName;
    private String generatePath;
    private String commonPackageName;
    private String commonGeneratePath;
    private String projectPath;
    public DatabaseInfo()
    {
        url = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        name = "root";
        password = "1234";
        driver = "com.mysql.jdbc.Driver";
    }
    public DatabaseInfo(String projectName)
    {
        super();
        this.url = "jdbc:mysql://127.0.0.1:3306/"+projectName+"?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.name = "root";
        this.password = "1234";
        this.driver = "com.mysql.jdbc.Driver";
        this.packageName ="com.zemel."+projectName+".entiy";
        String projectPath = System.getProperty("user.dir");
        this.projectPath = projectPath+ "/"+projectName+"/src/main/java/com/zemel/"+projectName;
        this.generatePath = this.projectPath+"/entiy/";
        this.commonGeneratePath = projectPath+ "/data/src/main/java/com/zemel/data/entiy/";
        this.commonPackageName = "com.zemel.data.entiy";
    }
}
