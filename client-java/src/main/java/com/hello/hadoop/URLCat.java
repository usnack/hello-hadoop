package com.hello.hadoop;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            URL url = new URL("hdfs://localhost:9900/user/root/myfile2");
            in = url.openStream();
            IOUtils.copyBytes(in, System.out, 4096, false); } finally {
            IOUtils.closeStream(in);
        }
    }
}
