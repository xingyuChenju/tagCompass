/**
 * Created by XiaoCong on 2017/1/13.
 * 当阅读器设置发生改变时运行，重新保存设置
 */

import com.impinj.octane.*;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderSettings {
    public static void main(String[] args) {
        String hostname = "169.254.0.22";
        ImpinjReader reader = new ImpinjReader();
        try {
//            reader.connect(hostname+".local");
            reader.connect(hostname);
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }

        Settings settings = reader.queryDefaultSettings();
//        try {
//            设置频率列表，如果设置过多，变一轮频率要8到9s,美版不需要这个设置
//            FeatureSet features= reader.queryFeatureSet();
//            if (!features.isHoppingRegion()) {
//                 settings fixed frequencies is allowed if its non hopping
//                ArrayList<Double> freqList = new ArrayList<Double>();
//                freqList.add(920.625);
//                freqList.add(921.125);
//                freqList.add(921.625);
//                freqList.add(922.125);
//                freqList.add(922.625);
//                freqList.add(923.125);
//                freqList.add(923.625);
//                freqList.add(924.125);
//                settings.setTxFrequenciesInMhz(freqList);
//            }
//        } catch (OctaneSdkException e) {
//            e.printStackTrace();
//        }
        settings.setReaderMode(ReaderMode.MaxThroughput);
        settings.setSearchMode(SearchMode.DualTarget);
        settings.setSession(0);
        settings.setTagPopulationEstimate(1);

        //设置功率

        ArrayList<AntennaConfig> arrayList = settings.getAntennas().getAntennaConfigs();
        for (AntennaConfig ac : arrayList) {
            ac.setEnabled(false);
            ac.setIsMaxTxPower(false);
            ac.setTxPowerinDbm(32.5);
            ac.setIsMaxRxSensitivity(true);
        }
        AntennaConfig ac = arrayList.get(0);
        ac.setEnabled(true);
        ReportConfig r = settings.getReport();
        r.setIncludeAntennaPortNumber(true);
        r.setIncludeFirstSeenTime(true);
        r.setIncludeLastSeenTime(true);
        r.setIncludeFastId(true);
        r.setIncludePeakRssi(true);
        r.setIncludePcBits(true);
        r.setIncludeChannel(true);
        r.setIncludeDopplerFrequency(true);
        r.setIncludePhaseAngle(true);
        r.setMode(ReportMode.Individual);
        settings.setReport(r);

        TagFilter t1 = settings.getFilters().getTagFilter1();
        t1.setBitCount(24);
        t1.setBitPointer(32);
        //匹配EPC部分
        t1.setMemoryBank(MemoryBank.Epc);
        t1.setFilterOp(TagFilterOp.Match);
        t1.setTagMask("181012");
//        t1.setTagMask("180830");
        settings.getFilters().setMode(TagFilterMode.OnlyFilter1);

        try {
            settings.save("ReadTags/"+hostname+".xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.disconnect();
    }
}
