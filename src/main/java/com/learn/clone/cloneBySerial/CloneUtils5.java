package com.learn.clone.cloneBySerial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author gonghe.hogan
 */

public class CloneUtils5 {

    private static final Logger logger = LoggerFactory.getLogger(CloneUtils5.class);

    /**
     *
     * 还可以考虑通过反射，反射获取clone()方法，来浅克隆一个对象，且对象必须实现Cloneable接口，并重写clone()方法
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneBySerial(T obj){
//        0. 准备流（后续关闭）(注意Object流是用来写入写出对象的，而ByteArray流则是用来建立输出和输入流之间的桥梁)

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
 //      1. （将对象）写出字节流(利用了ObjectOutputStream的writeObject方法将对象导成流，对象流本身持有了字节流，这样对象也会被序列化到字节流中)
            objectOutputStream.writeObject(obj);

//       2. 将流读入（通过byteArray流,它可以将流转换为字节数组，也可以基于字节数组来建立流）
            return  (T)objectInputStream.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
