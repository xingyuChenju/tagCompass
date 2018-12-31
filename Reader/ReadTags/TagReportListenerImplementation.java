/**
 * Created by XiaoCong on 2017/1/13.
 * 示例：标签中包含的信息
 */

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TagReportListenerImplementation implements TagReportListener {
    String record="";
    long init=0;
    long interval=0;
    private String textname;
    DecimalFormat df1, df2;
    Boolean flag=false;
//    public static String Root = Work.path;
    //一次启动只生成一次实例
    public TagReportListenerImplementation(String textname) {
        System.out.println("AllTagReportListener");
        df1 = new DecimalFormat("#0.0");
        df2 = new DecimalFormat("#0.00000");
        this.textname = textname;
    }


    //异步模式下一次Report一个标签
    public void onTagReported(ImpinjReader reader, TagReport report) {
        List<Tag> tags = report.getTags();

        for (Tag t : tags) {
            System.out.print(" EPC: " + t.getEpc().toString());
            

            if (reader.getName() != null) {
                System.out.print(" Reader_name: " + reader.getName());
            } else {
                System.out.print(" Reader_ip: " + reader.getAddress());
            }

            if (t.isPcBitsPresent()) {
                System.out.print(" PcBits: " + t.getPcBits());

            }

            if (t.isAntennaPortNumberPresent()) {
                System.out.print(" antenna: " + t.getAntennaPortNumber());
            }

            if (t.isFirstSeenTimePresent()) {
                System.out.print(" first: " + t.getFirstSeenTime().ToString());
            }

            if (t.isLastSeenTimePresent()) {
                System.out.print(" last: " + t.getLastSeenTime().ToString());
            }

            if (t.isSeenCountPresent()) {
                System.out.print(" count: " + t.getTagSeenCount());
            }

            if (t.isRfDopplerFrequencyPresent()) {
                System.out.print(" doppler: " + t.getRfDopplerFrequency());
            }

            if (t.isPeakRssiInDbmPresent()) {
                System.out.print(" peak_rssi: " + t.getPeakRssiInDbm());
            }

            if (t.isChannelInMhzPresent()) {
                System.out.print(" chan_MHz: " + t.getChannelInMhz());
            }

            if (t.isFastIdPresent()) {
                System.out.print("\n     fast_id: " + t.getTid().toHexString());

                System.out.print(" model: " +
                        t.getModelDetails().getModelName());

                System.out.print(" epcsize: " +
                        t.getModelDetails().getEpcSizeBits());

                System.out.print(" usermemsize: " +
                        t.getModelDetails().getUserMemorySizeBits());

               synchronized (this) {
                   if (!flag) {
                       init = t.getFirstSeenTime().getLocalDateTime().getTime();
                       flag = true;
                   }
               }
                interval = t.getFirstSeenTime().getLocalDateTime().getTime()-init;
               //EPC  RSSI  Phase  interval
//                record=df2.format(t.getPhaseAngleInRadians())+" "+interval;
//                record= t.getEpc().toString().replace(" ","").charAt(7)+" "+
//                        t.getChannelInMhz()+" "+
//                        df2.format(t.getPhaseAngleInRadians())+" "+
//                        interval+" "+
//                        t.getRfDopplerFrequency()+" "+
//                        df1.format(t.getPeakRssiInDbm())+" "+
//                        t.getAntennaPortNumber()
                record= t.getEpc().toString().replace(" ","").charAt(11)+" "+
                        df2.format(t.getPhaseAngleInRadians())+" "+
                        df1.format(t.getPeakRssiInDbm())
                ;
                //record="1"+" "+"1"+" "+df1.format(t.getPeakRssiInDbm())+" "+df2.format(t.getPhaseAngleInRadians())+" "+interval;
            }

            System.out.println("");
            writeFile(record+'\n');
//            ReadTags.writer.println(record);
        }
    }
    public void writeFile(String str) {
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        try {
            FileOutputStream in = new FileOutputStream(ReadTags.path+textname+".txt", true);
            try {
                in.write(bt, 0, bt.length);
                in.flush();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String str,String filename) {
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        try {
            FileOutputStream in = new FileOutputStream(ReadTags.path+filename, true);
            try {
                in.write(bt, 0, bt.length);
                in.flush();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

