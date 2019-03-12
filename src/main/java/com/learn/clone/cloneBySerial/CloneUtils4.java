package com.learn.clone.cloneBySerial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author gonghe.hogan
 */

public class CloneUtils4 {

    private static final Logger logger = LoggerFactory.getLogger(CloneUtils4.class);

    /**
     *之前的方法是通过java自带的Serializable接口来序列化实现的，
     *
     * 1.要求对象必须实现Serializable接口，否则会抛出java.io.NotSerializableException异常
     * 2.序列化的类型和反序列化的类型的序列化ID必须一致(远程信息交换时)。
     * 3.静态数据不会被序列化，Transient关键字修饰的字段不会被序列化。（因为序列化实际上是只序列化对象在堆中的信息，静态信息是在方法区，没有序列化）
     * https://blog.csdn.net/wangzuojia001/article/details/68945568
     *
     *
     * 建议加一个	@SuppressWarnings("unchecked")注解，因为类型转换时会有异常提示
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
