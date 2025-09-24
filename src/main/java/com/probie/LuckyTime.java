package com.probie;

public class LuckyTime {

    private volatile static String pluginName = "LuckyTime";
    private volatile static String pluginVersion = "1.0.0";
    private volatile static String pluginMCVersion = "1.21";
    private volatile static String pluginCoreType = "SpigotPlugin";
    private volatile static String pluginAuthor = "probie";
    private volatile static String pluginGithub = "https://github.com/BProbie";

    private volatile static String messageHead = getPluginCoreType()+"-"+getPluginName()+"-"+getPluginVersion()+":"+" ";
    private volatile static String simpleMessageHead = getPluginName()+":"+" ";

    private volatile static String keyLuckyTime = "luckytime";

    private volatile static String filePath = System.getProperty("user.dir")+"\\"+ LuckyTime.getPluginName()+".config";

    private volatile static int luckyTime = 60 * 10; // 秒
    private volatile static int minCount = 1;
    private volatile static int maxCount = 3;

    public static String colorWord(String word, String color) {
        return color+word+"\u00a7r";
    }

    public static String colorPlayerName(String playerName) {
        return colorWord(playerName, "\u00a7a");
    }

    public static void setPluginName(String pluginName) {
        LuckyTime.pluginName = pluginName;
    }

    public static String getPluginName() {
        return pluginName;
    }

    public static void setPluginVersion(String pluginVersion) {
        LuckyTime.pluginVersion = pluginVersion;
    }

    public static String getPluginVersion() {
        return pluginVersion;
    }

    public static void setPluginMCVersion(String pluginMCVersion) {
        LuckyTime.pluginMCVersion = pluginMCVersion;
    }

    public static String getPluginMCVersion() {
        return pluginMCVersion;
    }

    public static void setPluginCoreType(String pluginCoreType) {
        LuckyTime.pluginCoreType = pluginCoreType;
    }

    public static String getPluginCoreType() {
        return pluginCoreType;
    }

    public static void setPluginAuthor(String pluginAuthor) {
        LuckyTime.pluginAuthor = pluginAuthor;
    }

    public static String getPluginAuthor() {
        return pluginAuthor;
    }

    public static void setPluginGithub(String pluginGithub) {
        LuckyTime.pluginGithub = pluginGithub;
    }

    public static String getPluginGithub() {
        return pluginGithub;
    }

    public static void setMessageHead(String messageHead) {
        LuckyTime.messageHead = messageHead;
    }

    public static String getMessageHead() {
        return messageHead;
    }

    public static void setSimpleMessageHead(String simpleMessageHead) {
        LuckyTime.simpleMessageHead = simpleMessageHead;
    }

    public static String getSimpleMessageHead() {
        return simpleMessageHead;
    }

    public static void setKeyLuckyTime(String keyLuckyTime) {
        LuckyTime.keyLuckyTime = keyLuckyTime;
    }

    public static String getKeyLuckyTime() {
        return keyLuckyTime;
    }

    public static void setFilePath(String filePath) {
        LuckyTime.filePath = filePath;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setLuckyTime(int luckyTime) {
        LuckyTime.luckyTime = luckyTime;
    }

    public static int getLuckyTime() {
        return luckyTime;
    }

    public static void setMinCount(int minCount) {
        LuckyTime.minCount = minCount;
    }

    public static int getMinCount() {
        return minCount;
    }

    public static void setMaxCount(int maxCount) {
        LuckyTime.maxCount = maxCount;
    }

    public static int getMaxCount() {
        return maxCount;
    }

}