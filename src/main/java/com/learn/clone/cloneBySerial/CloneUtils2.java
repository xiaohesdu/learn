package com.learn.clone.cloneBySerial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author gonghe.hogan
 */

public class CloneUtils2 {

    private static final Logger logger = LoggerFactory.getLogger(CloneUtils2.class);

    /**
     *有一个注意点，通常来说，对于指向硬盘（文件）的流，它是需要显式的调用close方法来关闭的。
     * 而对于内存中操作的流，比如ByteArrayInputStream或ByteArrayOutputStream，不需要去关闭流。但是他们需要考虑Java GC问题，
     * 所以它们的处理是在finally里面将其指向null即可。
     * 所以我们看到了如下的写法：
     * https://blog.csdn.net/wangzuojia001/article/details/68945568
     *
     *
     *
     */
    public static <T extends Serializable> T cloneBySerial(T obj){
        T cloneObj = null;

//        0. 准备流（后续关闭）(注意Object流是用来写入写出对象的，而ByteArray流则是用来建立输出和输入流之间的桥梁)
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;


        try {
 //      1. （将对象）写出字节流(利用了ObjectOutputStream的writeObject方法将对象导成流，对象流本身持有了字节流，这样对象也会被序列化到字节流中)
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);

//       2. 将流读入（通过byteArray流,它可以将流转换为字节数组，也可以基于字节数组来建立流）
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cloneObj = (T)objectInputStream.readObject();
            return cloneObj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            纯内存操作的只需要考虑GC
            byteArrayInputStream = null;
            byteArrayOutputStream = null;

            try{
//            非纯内存的则需要显式的调用close(但是close方法可能会抛出异常，还要再补货一次)
                if (objectOutputStream != null){
                    objectOutputStream.close();
                }if (objectInputStream != null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return cloneObj;

    }
}
