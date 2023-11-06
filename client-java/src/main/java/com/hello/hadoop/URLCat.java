package com.hello.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
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
            URL url = new URL("hdfs://localhost:9900/user/root/foo.txt");
            System.out.println("open stream");
            in = url.openStream();
            System.out.println("read all bytes");
            byte[] bytes = in.readAllBytes();
            System.out.println("read task done");
            System.out.println(new String(bytes));
            IOUtils.copyBytes(in, System.out, 1, false); }
        finally {
            IOUtils.closeStream(in);
        }
        /*
        2023-10-24 18:36:02,642 WARN impl.BlockReaderFactory: I/O error constructing remote block reader.
org.apache.hadoop.net.ConnectTimeoutException: 60000 millis timeout while waiting for channel to be ready for connect. ch : java.nio.channels.SocketChannel[connection-pending remote=/172.19.0.2:9866]
        at org.apache.hadoop.net.NetUtils.connect(NetUtils.java:603)
        at org.apache.hadoop.hdfs.DFSClient.newConnectedPeer(DFSClient.java:3033)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.nextTcpPeer(BlockReaderFactory.java:829)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.getRemoteBlockReaderFromTcp(BlockReaderFactory.java:754)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.build(BlockReaderFactory.java:381)
        at org.apache.hadoop.hdfs.DFSInputStream.getBlockReader(DFSInputStream.java:715)
        at org.apache.hadoop.hdfs.DFSInputStream.blockSeekTo(DFSInputStream.java:645)
        at org.apache.hadoop.hdfs.DFSInputStream.readWithStrategy(DFSInputStream.java:845)
        at org.apache.hadoop.hdfs.DFSInputStream.read(DFSInputStream.java:918)
        at java.base/java.io.DataInputStream.read(DataInputStream.java:102)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:96)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:69)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:133)
        at org.apache.hadoop.fs.shell.Display$Cat.printToStdout(Display.java:101)
        at org.apache.hadoop.fs.shell.Display$Cat.processPath(Display.java:96)
        at org.apache.hadoop.fs.shell.Command.processPathInternal(Command.java:382)
        at org.apache.hadoop.fs.shell.Command.processPaths(Command.java:345)
        at org.apache.hadoop.fs.shell.Command.processPathArgument(Command.java:318)
        at org.apache.hadoop.fs.shell.Command.processArgument(Command.java:300)
        at org.apache.hadoop.fs.shell.Command.processArguments(Command.java:284)
        at org.apache.hadoop.fs.shell.FsCommand.processRawArguments(FsCommand.java:121)
        at org.apache.hadoop.fs.shell.Command.run(Command.java:191)
        at org.apache.hadoop.fs.FsShell.run(FsShell.java:327)
        at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:82)
        at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:97)
        at org.apache.hadoop.fs.FsShell.main(FsShell.java:390)
2023-10-24 18:36:02,705 WARN hdfs.DFSClient: Failed to connect to /172.19.0.2:9866 for file /user/root/foo.txt for block BP-1872787820-172.19.0.2-1698102830448:blk_1073741825_1001, add to deadNodes and continue.
org.apache.hadoop.net.ConnectTimeoutException: 60000 millis timeout while waiting for channel to be ready for connect. ch : java.nio.channels.SocketChannel[connection-pending remote=/172.19.0.2:9866]
        at org.apache.hadoop.net.NetUtils.connect(NetUtils.java:603)
        at org.apache.hadoop.hdfs.DFSClient.newConnectedPeer(DFSClient.java:3033)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.nextTcpPeer(BlockReaderFactory.java:829)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.getRemoteBlockReaderFromTcp(BlockReaderFactory.java:754)
        at org.apache.hadoop.hdfs.client.impl.BlockReaderFactory.build(BlockReaderFactory.java:381)
        at org.apache.hadoop.hdfs.DFSInputStream.getBlockReader(DFSInputStream.java:715)
v        at org.apache.hadoop.hdfs.DFSInputStream.blockSeekTo(DFSInputStream.java:645)
        at org.apache.hadoop.hdfs.DFSInputStream.readWithStrategy(DFSInputStream.java:845)
        at org.apache.hadoop.hdfs.DFSInputStream.read(DFSInputStream.java:918)
        at java.base/java.io.DataInputStream.read(DataInputStream.java:102)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:96)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:69)
        at org.apache.hadoop.io.IOUtils.copyBytes(IOUtils.java:133)
        at org.apache.hadoop.fs.shell.Display$Cat.printToStdout(Display.java:101)
        at org.apache.hadoop.fs.shell.Display$Cat.processPath(Display.java:96)
        at org.apache.hadoop.fs.shell.Command.processPathInternal(Command.java:382)
        at org.apache.hadoop.fs.shell.Command.processPaths(Command.java:345)
        at org.apache.hadoop.fs.shell.Command.processPathArgument(Command.java:318)
        at org.apache.hadoop.fs.shell.Command.processArgument(Command.java:300)
        at org.apache.hadoop.fs.shell.Command.processArguments(Command.java:284)
        at org.apache.hadoop.fs.shell.FsCommand.processRawArguments(FsCommand.java:121)
        at org.apache.hadoop.fs.shell.Command.run(Command.java:191)
        at org.apache.hadoop.fs.FsShell.run(FsShell.java:327)
        at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:82)
        at org.apache.hadoop.util.ToolRunner.run(ToolRunner.java:97)
        at org.apache.hadoop.fs.FsShell.main(FsShell.java:390)
2023-10-24 18:36:02,724 WARN hdfs.DFSClient: No live nodes contain block BP-1872787820-172.19.0.2-1698102830448:blk_1073741825_1001 after checking nodes = [DatanodeInfoWithStorage[172.19.0.2:9866,DS-79282adf-261b-41f4-9881-c5dea5e5758c,DISK]], ignoredNodes = null
2023-10-24 18:36:02,724 INFO hdfs.DFSClient: Could not obtain BP-1872787820-172.19.0.2-1698102830448:blk_1073741825_1001 from any node:  No live nodes contain current block Block locations: DatanodeInfoWithStorage[172.19.0.2:9866,DS-79282adf-261b-41f4-9881-c5dea5e5758c,DISK] Dead nodes:  DatanodeInfoWithStorage[172.19.0.2:9866,DS-79282adf-261b-41f4-9881-c5dea5e5758c,DISK]. Will get new block locations from namenode and retry...
2023-10-24 18:36:02,724 WARN hdfs.DFSClient: DFS chooseDataNode: got # 1 IOException, will wait for 690.7511848954223 msec.


         */
    }
}
