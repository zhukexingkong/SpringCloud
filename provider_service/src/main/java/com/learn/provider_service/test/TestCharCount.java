package com.learn.provider_service.test;

/**
 * @Author HeBiao
 * @Date 2021/12/8 11:36
 * @Description
 */
public class TestCharCount {
    public static String addZeroForStr(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

    public static void main(String[] args) {
//        String strHex = Integer.toHexString(456);
//        System.out.println(strHex);
//        int valueTen2 = Integer.parseInt(strHex,16);
//        System.out.println(valueTen2);
        String message = "03068300a104c36a6f44047eeee2a5211001016dbb294a03b0001d2000481f0e1450791c0fa020010100020c200300200500200600200700200800200c003a200d00200e00000000200f0154201100002013000000002014015e20160058201900201d010e010e010e012c00fa00fa00fa00fa201e000000000000000020220020240000000020250000202701202800202a02202e000020300020480000204900";

//        System.out.println(builder);
//        System.out.println(message.indexOf("200C"));
        StringBuilder builder = new StringBuilder(message);


        // 经度
        int longitudeDegree = 121;
        int longitudeMinute = 40;
        int longitudeSecond = 25;
        // 纬度
        int latitudeDegree = 31;
        int latitudeMinute = 8;
        int latitudeSecond = 51;
        // 车速
        int speed = 0;
        // 胎压
        int leftUp = 100;
        int leftDown = 100;
        int rightUp = 100;
        int rightDown = 100;
        // 油量
        int oil = 60;
        // 续航里程
        int enduranceMileage = 350;


        int latitudeDegreeSite = 60;
        builder.replace(latitudeDegreeSite,latitudeDegreeSite + 2, addZeroForStr(Integer.toHexString(latitudeDegree),2)); // 左包右不包

        int latitudeMinuteSite = 62;
        builder.replace(latitudeMinuteSite,latitudeMinuteSite + 2, addZeroForStr(Integer.toHexString(latitudeMinute),2)); // 左包右不包

        int latitudeSecondSite = 64; //4
        builder.replace(latitudeSecondSite,latitudeSecondSite + 4, addZeroForStr(Integer.toHexString(latitudeSecond * 100), 4)); // 左包右不包

        int longitudeDegreeSite = 68;
        builder.replace(longitudeDegreeSite,longitudeDegreeSite + 2, addZeroForStr(Integer.toHexString(longitudeDegree),2)); // 左包右不包

        int longitudeMinuteSite = 70;
        builder.replace(longitudeMinuteSite,longitudeMinuteSite + 2, addZeroForStr(Integer.toHexString(longitudeMinute),2)); // 左包右不包

        int longitudeSecondSite = 72; //4
        builder.replace(longitudeSecondSite,longitudeSecondSite + 4, addZeroForStr(Integer.toHexString(longitudeSecond * 100), 4)); // 左包右不包

        int speedSite = 184;    //4
        builder.replace(speedSite,speedSite + 4, addZeroForStr(Integer.toHexString(speed),4)); // 左包右不包

        int leftUpSite = 198;   //4
        builder.replace(leftUpSite,leftUpSite + 4, addZeroForStr(Integer.toHexString(leftUp),4)); // 左包右不包

        int leftDownSite = 202;
        builder.replace(leftDownSite,leftDownSite + 4, addZeroForStr(Integer.toHexString(leftDown),4)); // 左包右不包

        int rightUpSite = 206;
        builder.replace(rightUpSite,rightUpSite + 4, addZeroForStr(Integer.toHexString(rightUp),4)); // 左包右不包

        int rightDownSite = 210;
        builder.replace(rightDownSite,rightDownSite + 4, addZeroForStr(Integer.toHexString(rightDown), 4)); // 左包右不包

        int oilSite = 122;
        builder.replace(oilSite,oilSite + 4, addZeroForStr(Integer.toHexString(oil), 4)); // 左包右不包

        int enduranceMileageSite = 148;
        builder.replace(enduranceMileageSite,enduranceMileageSite + 4, addZeroForStr(Integer.toHexString(enduranceMileage), 4)); // 左包右不包

        System.out.println(builder);
        System.out.println();

    }
}
