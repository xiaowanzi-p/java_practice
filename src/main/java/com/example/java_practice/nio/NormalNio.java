package com.example.java_practice.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NormalNio {

    public static boolean copyFile(String origin, String target) throws IOException {
        FileInputStream inputStream = new FileInputStream(origin);
        FileOutputStream outputStream = new FileOutputStream(target);
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);

        while (true) {
            //读取原始文件
            int read = inChannel.read(buffer);
            buffer.flip();
            //写入新文件
            outChannel.write(buffer);
            buffer.clear();
            if (read == -1) {
                break;
            }
        }

        //关闭资源
        inChannel.close();
        outChannel.close();
        inputStream.close();
        outputStream.close();
        return true;
    }

}
