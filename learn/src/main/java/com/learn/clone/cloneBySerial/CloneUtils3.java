package com.learn.clone.cloneBySerial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author gonghe.hogan
 */

public class CloneUtils3 {

    private static final Logger logger = LoggerFactory.getLogger(CloneUtils3.class);

    /**
     *有一个注意点，通常来说，对于指向硬盘（文件）的流，它是需要显式的调用close方法来关闭的。
     * 而对于内存中操作的流，比如ByteArrayInputStream或ByteArrayOutputStream，不需要去关闭流。但是他们需要考虑Java GC问题，
     * 所以它们的处理是在finally里面将其指向null即可。
     * 所以我们看到了如下的写法：
     * https://blog.csdn.net/wangzuojia001/article/details/68945568
     *
     *
     * 但是上述的方式还是太麻烦，java提供了一个try(定义流的语句)，它自身可以保证try(流定义)中的语句可以在完成执行后自动关闭
     * （实际上是要求每个流实现Closeable接口的close()方法，java会保证在try语句结束时调用close方法关闭所有资源
     * 我看到的流已经都是实现了Closeable接口，所以应该可以放心
     * https://blog.csdn.net/longzuyuan/article/details/17434995
     *
     *
     *
     */
    public static <T extends Serializable> T cloneBySerial(T obj){

//        0. 准备流（后续关闭）(注意Object流是用来写入写出对象的，而ByteArray流则是用来建立输出和输入流之间的桥梁)
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
 //      1. （将对象）写出字节流(利用了ObjectOutputStream的writeObject方法将对象导成流，对象流本身持有了字节流，这样对象也会被序列化到字节流中)
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

//       2. 将流读入（通过byteArray流,它可以将流转换为字节数组，也可以基于字节数组来建立流）
//      byteArrayInputStream要拿上一步的结果，所以不能直接在try中定义
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return  (T)objectInputStream.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            byteArrayInputStream = null;
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}
