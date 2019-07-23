package project.core;

import project.models.Driver;

import java.util.HashMap;
import java.util.Map;

public class RunTimeDataStorage {

    public static class DriverStorage {

        static Map dMap;

        public static void initializeDMap() {
            dMap = new HashMap<Long, Driver>();
        }

        public static void setD(Long threadId, Driver d) {
            dMap.put(threadId, d);
        }

        public static Driver getD(Long threadId) {
            return (Driver) dMap.get(threadId);
        }
    }

}