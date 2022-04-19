package com.changgou.util;

/**
 * Created by Jinmunan
 * 2022/1/14
 * 10:04
 */

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传
 * 文件下载
 * 文件删除
 * 文件信息获取
 * Storage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {

    /**
     * 抽取:获取TrackerServer
     *
     * @return
     * @throws Exception
     */
    public static TrackerServer getTrackerServer() throws Exception {
        //创建tracker客户端访问对象trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient去访问trackerServer服务,获取连接信息
        return trackerClient.getConnection();
    }

    /**
     * 抽取:获取StorageClient
     *
     * @return
     * @throws Exception
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer) throws Exception {
        //通过trackerServer去获取Storage信息,通过创建StorageClient对象存储Storage连接信息
        return new StorageClient(trackerServer, null);
    }


    /**
     * 加载Tracker信息
     */
    static {
        //查找classpath下的文件路径
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            //加载tracker信息
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传:上传的文件信息的封装
     */
    public static String[] upload(FastDFSFile file) {
        try {
            TrackerServer trackerServer = getTrackerServer();
            StorageClient storageClient = getStorageClient(trackerServer);
            //元数据
            NameValuePair[] meta_list = new NameValuePair[]{new NameValuePair(file.getAuthor()), new NameValuePair(file.getName())};
            //通过StorageClient访问Storage,实现文件上传,并获取文件上传后的存储信息
            return storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = getStorageClient(trackerServer);
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     */
    public static InputStream downLoadFile(String groupName, String remoteFileName) throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = getStorageClient(trackerServer);
        byte[] buf = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buf);
    }

    /**
     * 文件删除
     */
    public static Integer deleteFile(String groupName, String remoteFileName) throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = getStorageClient(trackerServer);
        return storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取storage信息
     *
     * @return
     * @throws Exception
     */
    public static StorageServer getStorage() throws Exception {
        //创建tracker客户端访问对象trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient去访问trackerServer服务,获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 获取tracker的ip和端口的信息
     * http://192.168.211.132:8080
     *
     * @return
     */
    public static String getTrackerUrl() {
        try {
            TrackerServer trackerServer = getTrackerServer();
            //tracker 的ip的信息
            String hostString = trackerServer.getInetSocketAddress().getHostString();
            int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostString + ":" + g_tracker_http_port;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
