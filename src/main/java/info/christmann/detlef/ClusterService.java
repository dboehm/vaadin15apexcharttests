package info.christmann.detlef;

import java.io.Serializable;
import java.sql.Time;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import info.christmann.recs.model.TimestampValueBean;
import info.christmann.recs.tor_master.webinterface.utils.Helper;

@Service
@Scope("singleton")
public class ClusterService implements Serializable {
    private static ClusterService mClusterService = null;
    private static Logger mLog = LogManager.getLogger();

//    public ClusterService getInstance() {
//        if (mClusterService == null) {
//            return new ClusterService();
//        }
//        else return mClusterService;
//    }
//
//    private ClusterService() {
//
//    }

    double mNextDouble = 0.0;
    private List<TimestampValueBean> timestampValueBeanList = new LinkedList<>();

        public List<TimestampValueBean> simulateTimeStampValues(boolean makeNew) {

            if (makeNew) {
                mLog.info("START: Number of timestamValueBeans: {} from ClusterService {}", timestampValueBeanList.size(), this);
                mNextDouble = Helper.getRandomDoubleBetweenRange(0.5,1.0);
                TimestampValueBean timestampValueBean = new TimestampValueBean(Instant.now().toEpochMilli(), mNextDouble);
                if (timestampValueBeanList.size() <= 60)
                    ; // timestampValueBeanList.add(timestampValueBean);
                else {
                    timestampValueBeanList.remove(0);
                }
                int counter = 0;
                mLog.info("New {} from ClusterService {}", timestampValueBean, this);
                timestampValueBeanList.add(timestampValueBean);
                for (TimestampValueBean t : timestampValueBeanList) {
                    mLog.info("No. {} ->  {} ", ++counter, t);
                }

                mLog.info("END: Number of timestamValueBeans: {} from ClusterService {}", timestampValueBeanList.size(), this);
            }

        return timestampValueBeanList;
    }
}
