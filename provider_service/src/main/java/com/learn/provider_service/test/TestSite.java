package com.learn.provider_service.test;

/**
 * @Author HeBiao
 * @Date 2021/12/10 11:05
 * @Description 测试函数
 */
public class TestSite {
    public static void main(String[] args) {
//        String strHex = Integer.toHexString(456);
//        System.out.println(strHex);
//        int valueTen2 = Integer.parseInt(strHex,16);
//        System.out.println(valueTen2);
        String message = "03068300a104c36a6f44047eeee2a5211001016dbb294a03b0001d2000481f0e1450791c0fa020010100020c200300200500200600200700200800200c003a200d00200e00000000200f0154201100002013000000002014015e20160058201900201d010e010e010e012c00fa00fa00fa00fa201e000000000000000020220020240000000020250000202701202800202a02202e000020300020480000204900";

//        System.out.println(builder);
        System.out.println(message.indexOf("010e010e010e012c00fa00fa00fa00fa"));
//        System.out.println(addZeroForStr("12e",4));
        return;
    }

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
}
