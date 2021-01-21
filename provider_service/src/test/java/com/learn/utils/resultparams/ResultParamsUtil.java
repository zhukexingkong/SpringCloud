package com.learn.utils.resultparams;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WBZ
 */
public class ResultParamsUtil {

    public static Builder getInstance(){
        return new MapResultParamBuilder();
    }


    /**
     * 根据传入的实体类构建resultParam
     * @param clazz
     * @return
     */
    public static List<?> toResultParam(Class<?> clazz){
        return toResultParam(clazz,null);
    }

    private static List<?> toResultParam(Class<?> clazz,Builder builder){
        boolean isRoot = false;
        if(builder == null){
            builder = getInstance();
            isRoot = true;
        }
        List<Field> fields = getAllFields(clazz);
        for(Field field:fields){
            String filedName = field.getName();
            if(isBaseType(field.getType())){
                builder.field(filedName);
            }else if(field.getType().equals(List.class)){
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType type = (ParameterizedType) genericType;
                    Class<?> actualTypeArgument = (Class<?>)type.getActualTypeArguments()[0];
                    if(isBaseType(actualTypeArgument)){
                        builder.field(filedName);
                    }else{
                        toResultParam(actualTypeArgument,builder.list(filedName));
                    }
                }
            }else{
                toResultParam(field.getType(),builder.map(filedName));
            }
        }
        if(!isRoot){
            return null;
        }
        return toResultParam(builder.root());
    }

    /**
     * 根据传入的builder构建resultparam
     * @param builder
     * @return
     */
    public static List<?> toResultParam(Builder builder){
        Builder root = builder.rePreLevel();
        if(root != null){
            throw new RuntimeException("传入的构造器有误，请传入根节点");
        }

        Map<String,Builder> infos = builder.getInfos();

        List<String> rootParam = filedFind(infos);
        if(rootParam.size() == infos.size()){
            return rootParam;
        }
        List<Object> resultList = new ArrayList<>();
        for(Map.Entry<String,Builder> entry:infos.entrySet()){
            if(rootParam.contains(entry.getKey())){
                continue;
            }
            resultList.add(substrateParam(entry.getKey(),entry.getValue()));
        }
        resultList.addAll(rootParam);
        return resultList;
    }

    private static Map<String,?> substrateParam(String name,Builder builder){

        Map<String,Builder> infos = builder.getInfos();

        List<Object> bList = new ArrayList<>();

        for(Map.Entry<String,Builder> entry:infos.entrySet()){
            if(entry.getValue() == null){
                bList.add(entry.getKey());
                continue;
            }
            bList.add(substrateParam(entry.getKey(),entry.getValue()));
        }

        return Collections.singletonMap(name,bList);
    }

    private static List<String> filedFind(Map<String,Builder> infos){
        return infos.entrySet()
                .stream().filter(entry -> entry.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(toResultParam(ResultParamsUtil.getInstance().list("data")
                .field("uuid").field("name")
                .map("startPort").field("")
                .rePreLevel()
                .field("")
                .map("").field("").root()));
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 判断对象属性是否是基本数据类型,包括是否包括string
     * @param className 字节码对象
     * @return
     */
    private static boolean isBaseType(Class className) {
        return className.equals(String.class) ||
                className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(boolean.class);
    }
}
