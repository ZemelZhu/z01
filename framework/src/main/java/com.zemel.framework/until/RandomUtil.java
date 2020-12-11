package com.zemel.framework.until;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: zemel
 * @Date: 2020/4/5 17:41
 */
public class RandomUtil<T> {
    public static int rand(int maxValue) {
        return ThreadLocalRandom.current().nextInt(maxValue);
    }

    public static <T> List<T> rand(List<T> commodityMasks, int limit) {
        if (limit > commodityMasks.size())
            limit = commodityMasks.size();
        int[] randomUnrepeatArray = getRandomUnrepeatArray(0, commodityMasks.size(), limit);
        ArrayList<T> objects = new ArrayList<>();
        for (int i = 0; i < randomUnrepeatArray.length; i++) {
            objects.add(commodityMasks.get(i));
        }
        return objects;
    }

    /**
     * 产生不重复数组
     *
     * @param minValue 起始值，包含
     * @param maxValue 最大值，不包含
     * @param count    数量
     * @return
     */
    public static int[] getRandomUnrepeatArray(int minValue, int maxValue, int count) {
        if (minValue >= maxValue) {
            return new int[0];
        }
        // 首先创建一个临时数组，数据为传入的数值
        int[] tempArray = new int[maxValue - minValue];
        for (int i = minValue; i < maxValue; i++) {
            tempArray[i - minValue] = i;
        }
        if (count >= tempArray.length) {
            return tempArray;
        }
        int[] resultRound = new int[count];
        for (int i = 0; i < count; i++) {
            int rand = ThreadLocalRandom.current().nextInt(tempArray.length - i);
            int tempVar = tempArray[rand];
            // 将原有数组随机出的下标所代表的值赋值给新数组
            resultRound[i] = tempVar;
            // 将临时数组的最后一个数与随机出来的数进行交换，保证数组的唯一性
            tempArray[rand] = tempArray[tempArray.length - 1 - i];
            tempArray[tempArray.length - 1 - i] = tempVar;
        }
        return resultRound;
    }

}
