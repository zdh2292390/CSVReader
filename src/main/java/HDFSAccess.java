/**
 * Created by zhangdenghui on 16/10/13.
 */
import java.io.IOException;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class HDFSAccess {
    private static Configuration conf = new Configuration();
    private static FileSystem fs;
    private static String nameNode = "hdfs://nobida122:8020";
    private static String modulePath = "/BDA/Modules";
    static{
        conf.set("fs.defaultFS", nameNode);
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("ipc.client.connect.max.retries.on.timeouts", "5");

        try {
            fs = FileSystem.get(URI.create(nameNode), conf, "root");
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void uploadfile(String src, String dstPath) throws IOException {
        Path path = new Path(dstPath);
        fs.copyFromLocalFile(false, new Path(src), path);

    }
    public static void mkdirs(String uri) throws IOException {
        Path path = new Path(nameNode + "/" + uri);
        fs.mkdirs(path);
    }

    public static void delete(String filePath) throws IOException {
        Path path = new Path(nameNode + "/" + filePath);
        fs.delete(path, true);

    }
    public static ArrayList<String> readHDFSFile(String dst,int num_lines) throws Exception
    {

        // check if the file exists
        Path path = new Path(dst);
        if ( fs.exists(path) )
        {
            FSDataInputStream is = fs.open(path);
            // get the file info to create the buffer
            FileStatus stat = fs.getFileStatus(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ArrayList<String> content=new ArrayList<String>();

            for (int i=0;i<num_lines;i++){
                content.add(br.readLine());
            }

            // create the buffer
//            byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
//            is.readFully(0, buffer);
            br.close();
            is.close();
            fs.close();
            return content;
        }
        else
        {
            throw new Exception("the file is not found .");
        }
    }
    public static void copy(String uri, String touri){
        try {
            FileUtil.copy(fs, new Path(uri), fs, new Path(touri), false,
                    conf);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
