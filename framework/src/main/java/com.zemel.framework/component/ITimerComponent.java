package com.zemel.framework.component;

import com.zemel.framework.timer.IDailyTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/2/10 11:45
 */
public class ITimerComponent implements IComponent {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private List<IDailyTimer> dailyTimerList;

    public void addDelayJob(String jobName, Class<? extends Job> job, long delay) {
        addDelayJob(jobName, job, delay, 0, 0L);
    }

    public void addDefaultDelayJob(Class<? extends Job> job, long millis) {
        addDelayJob(job.getName(), job, 2 * 1000, -1, millis);
    }

    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
                            long delay) {
        addDelayJob(dataMap, jobName, job, delay, 0, 0L);
    }

    public void addDelayJob(JobDataMap dataMap, String jobName, Class<? extends Job> job,
                            long delay, int repeat, long millis) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").setJobData(
                    dataMap).build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").startAt(
                    new Date(new Date().getTime() + delay)).withSchedule(
                    SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(millis).withRepeatCount(
                            repeat)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    public void addJob(String jobName, Class<? extends Job> job, String time) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").withSchedule(
                    CronScheduleBuilder.cronSchedule(time)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    /**
     * 增加延迟delay任务，延迟毫秒单位执行，可指定重复执行次数和间隔时间
     *
     * @param jobName
     * @param job
     * @param delay   延迟执行时间 毫秒
     * @param repeat  重复次数，-1表示一直重复；1表示重复一次，总共执行两次
     * @param millis  重复间隔时间 毫秒
     */
    public void addDelayJob(String jobName, Class<? extends Job> job, long delay, int repeat,
                            long millis) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, "quartzGroup").build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "quartzGroup").startAt(
                    new Date(new Date().getTime() + delay)).withSchedule(
                    SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(millis).withRepeatCount(
                            repeat)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error("添加定时器任务异常，", e);
        }
    }

    public void deleteJob(String jobName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));// 停止触发器
            JobKey key = new JobKey(jobName, "quartzGroup");
            scheduler.deleteJob(key);// 删除任务
            LOGGER.error("删除定时任务: " + jobName);
        } catch (SchedulerException e) {
            LOGGER.error("删除定时器任务异常，", e);
        }
    }

    public boolean isJobExist(String jobName) {
        try {
            return scheduler.checkExists(JobKey.jobKey(jobName, "quartzGroup"));
        } catch (SchedulerException e) {
            LOGGER.error("检查定时器任务存在异常, ", e);
        }
        return false;
    }

    public void pauseJob(String jobName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, "quartzGroup"));
        } catch (SchedulerException e) {
            LOGGER.error("暂停定时器任务异常, ", e);
        }
    }

    public void resumeJob(String jobName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, "quartzGroup"));
        } catch (SchedulerException e) {
            LOGGER.error("恢复定时器任务异常, ", e);
        }
    }

    public Trigger.TriggerState getJobState(String jobName) {
        try {
            return scheduler.getTriggerState(TriggerKey.triggerKey(jobName, "quartzGroup"));
        } catch (SchedulerException e) {
            LOGGER.error("获取定时器任务状态异常, ", e);
        }
        return Trigger.TriggerState.NONE;
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        if (dailyTimerList != null && dailyTimerList.size() > 0) {
            addJob("DayResetJob", defaultDayJob.class, "0 0 0 * * ?");
            LOGGER.error("添加每天定时器,数量:" + dailyTimerList.size());
        }
        return true;
    }

    private class defaultDayJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            for (IDailyTimer dailyTimer : dailyTimerList) {
                try {
                    dailyTimer.dailyJob();
                    LOGGER.error(dailyTimer.getClass().getName() + "每日定时器执行完毕");
                } catch (Exception e) {
                    String message = e.getMessage();
                    LOGGER.error(dailyTimer.getClass().getName() + " error " + message);
                }
            }
        }
    }

    @Override
    public void stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean reload() {
        return true;
    }
}
