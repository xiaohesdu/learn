package com.learn.clone.cloneBySerial;

import java.io.*;

/**
 * @author gonghe.hogan
 */

public class CloneUtils {

    public static <T extends Serializable> T cloneBySerial(T obj){
        T cloneObj = null;

//        1. （将对象）写出字节流(利用了ObjectOutputStream的writeObject方法将对象导成流，中间还借入了字节流)
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

 //        2. 读入字节流
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);

//          3. 基于对象流的readObject读入对象
            final Object object = ois.readObject();
            cloneObj = (T) object;
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }

        return cloneObj;

    }
}
