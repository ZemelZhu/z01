package com.zemel.tool.testBean;

import com.zemel.framework.annotation.TerminalMessage;
import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zemel
 * @Date: 2020/12/12 21:21
 */
@Data
public class TestABean extends TestCBean implements TerminalMessage {
    private int id;
    private long aId;
    public int bid;
    private String name;
    private List<String> names;
    private List<TestBBean> bBean;
    private Set<TestBBean> bbbBean;
    public TestABean()
    {
        this.id = 1;
        this.bid = 1;
        this.aId = 4;
        this.name = "sd";
        names = Arrays.asList("43","s");
        bBean= Arrays.asList(new TestBBean(),new TestBBean());

        bbbBean = new HashSet<>();
        bbbBean.add(new TestBBean() );
        setHah(Arrays.asList(1,2));
    }

    @Override
    public String toString() {
        return "TestABean{" +
                "id=" + id +
                ", aId=" + aId +
                ", bid=" + bid +
                ", name='" + name + '\'' +
                ", names=" + names +
                ", bBean=" + bBean +
                ", bbbBean=" + bbbBean +
                '}'+super.toString();
    }
}
