package com.learn.utils.resultparams;

import java.util.Map;

/**
 * @author WBZ
 */
public interface Builder {

    /**
     * 获取信息
     *
     * @return 返回当前builder节点下信息
     */
    Map<String, Builder> getInfos();

    /**
     * 返回上一级
     *
     * @return 返回上一级builder
     */
    Builder rePreLevel();

    /**
     * 根路径
     * @return  根节点的Builder
     */
    Builder root();

    /**
     * 放入字段
     * @param name 字段名
     * @return 返回当前builder
     */
    Builder field(String name);

    /**
     * 放入一个map
     * @param name 字段名
     * @return 返回构造map的下一级builder
     */
    Builder map(String name);

    /**
     * 放入一个list
     * @param name 字段名
     * @return 返回构造list的下一级builder
     */
    Builder list(String name);
}
