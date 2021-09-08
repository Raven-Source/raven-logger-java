//package org.raven.logger;
//
//import org.raven.commons.data.ValueType;
//
//public enum DomainType implements ValueType<Integer> {
//    log(1, "log"),
//
//    //client
//    session(2, "session"),
//    event(3, "event"),
//    pageView(4, "pageView"),
//    launch(5, "launch"),
//    rpc(6, "rpc"),
//    fileLog(7, "filelog"),
//    performance(8, "performance"),
//    rpcHeader(9, "rpcheader"),
//    duration(10, "duration"),
//
//    //service side
//    serverEvent(100, "serverEvent");
//
//    private int value;
//    private String desc;
//
//    DomainType(int value, String desc) {
//        this.desc = desc;
//        this.value = value;
//    }
//
//    /**
//     * 枚举值
//     *
//     * @return
//     */
//    @Override
//    public int getValue() {
//        return value;
//    }
//
//    /**
//     * 枚举描述
//     *
//     * @return
//     */
//    @Override
//    public String getDesc() {
//        return desc;
//    }
//}
