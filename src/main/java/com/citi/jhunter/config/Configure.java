package com.citi.jhunter.config;

import com.citi.jhunter.util.JHunterReflectUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Modifier.isStatic;

public class Configure {
    public static final long DEFAULT_SESSION_TIMEOUT_SECONDS = 30*60*1000/1000;
    private String ip;
    private int telnetPort;
    private int httpPort;
    private long javaPid;
    private String jhunterCore;
    private String jhunterAgent;

    private String tunnelServer;
    private String agentId;

    /**
     * report executed command
     */
    private String statUrl;

    /**
     * session timeout seconds
     */
    private long sessionTimeout = DEFAULT_SESSION_TIMEOUT_SECONDS;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTelnetPort() {
        return telnetPort;
    }

    public void setTelnetPort(int telnetPort) {
        this.telnetPort = telnetPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public long getJavaPid() {
        return javaPid;
    }

    public void setJavaPid(long javaPid) {
        this.javaPid = javaPid;
    }

    public String getJhunterAgent() {
        return jhunterAgent;
    }

    public void setJhunterAgent(String jhunterAgent) {
        this.jhunterAgent = jhunterAgent;
    }

    public String getJhunterCore() {
        return jhunterCore;
    }

    public void setJhunterCore(String jhunterCore) {
        this.jhunterCore = jhunterCore;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getTunnelServer() {
        return tunnelServer;
    }

    public void setTunnelServer(String tunnelServer) {
        this.tunnelServer = tunnelServer;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getStatUrl() {
        return statUrl;
    }

    public void setStatUrl(String statUrl) {
        this.statUrl = statUrl;
    }

    /**
     * 序列化成字符串
     *
     * @return 序列化字符串
     */
    @Override
    public String toString() {

        final Map<String, String> map = new HashMap<String, String>();
        for (Field field : JHunterReflectUtil.getFields(Configure.class)) {

            // 过滤掉静态类
            if (isStatic(field.getModifiers())) {
                continue;
            }
            final boolean isAccessible = field.isAccessible();
            // 非静态的才需要纳入非序列化过程
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(this);
                if (fieldValue != null) {
                    map.put(field.getName(), String.valueOf(fieldValue));
                }
            } catch (Throwable t) {
                //
            }finally {
                field.setAccessible(isAccessible);
            }

        }

        return FeatureCodec.DEFAULT_COMMANDLINE_CODEC.toString(map);
    }

    /**
     * 反序列化字符串成对象
     *
     * @param toString 序列化字符串
     * @return 反序列化的对象
     */
//    public static Configure toConfigure(String toString) throws IllegalAccessException {
//        final Configure configure = new Configure();
//        final Map<String, String> map = FeatureCodec.DEFAULT_COMMANDLINE_CODEC.toMap(toString);
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            final Field field = ArthasReflectUtils.getField(Configure.class, entry.getKey());
//            if (null != field && !isStatic(field.getModifiers())) {
//                ArthasReflectUtils.set(field, ArthasReflectUtils.valueOf(field.getType(), entry.getValue()), configure);
//            }
//        }
//        return configure;
//    }
}
