package com.learn.test;

import com.learn.utils.resultparams.ResultParamsUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author HeBiao
 * @Date 2021/1/21 14:30
 * @Description
 */
public class ResultParamsTest {
    public static void main(String[] args) {
        // map转list
        List roomResultParams = ResultParamsUtil.toResultParam(ResultParamsUtil.getInstance()
                .field("uuid").field("name").field("classId").field("floorUuid").field("floorPosition").field("area").field("siteUuid")
                .map("site").field("name")
                .root());
        System.out.println(roomResultParams.toString());

        // 类转list
        System.out.println(ResultParamsUtil.toResultParam(OpticalRouteResult.class));
    }

    @Data
    private static class BasicsInfo {
        private String uuid;
        private String name;
        private String classId;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    private static class OpticalRouteResult extends BasicsInfo {
        private Integer opticalNo;
        private BasicsInfo startPort;
        private BasicsInfo endPort;
        private BasicsInfo startPointCableBox;
        private BasicsInfo endPointCableBox;
        private BasicsInfo startPointSite;
        private BasicsInfo endPointSite;
        private BasicsInfo startPointPoi;
        private BasicsInfo endPointPoi;
    }
}
