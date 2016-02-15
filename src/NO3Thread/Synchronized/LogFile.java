package NO3Thread.Synchronized;

import java.io.*;
import java.util.Date;

/**
 * Usage:
 * Description:
 * Created by zhongyinghao on 2/15/16.
 */
public class LogFile {

    private Writer out;

    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter( fw );
    }

    //获取当前日期及时间 并写入到out中
    public synchronized void writerEntry(String message) throws IOException {
        Date d = new Date();
        out.write( d.toString() );
        out.write( '\t' );
        out.write( message );
        out.write( "\r\n" );
    }

    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
