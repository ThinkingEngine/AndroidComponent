package s.cala.androidcompent.protocol.updates;

/**
 * package name:s.cala.androidcompent.protocol.updates
 * create:cala
 * date:2019/1/21
 * commits:apk升级的信息体，封装apkd升级信息。
 */
public class ApkUpdateData {

    private String version_name;
    private int version_code;
    private String apk_name;
    private int code;
    private DateBean data;
    private boolean hasScreen;

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getApk_name() {
        return apk_name;
    }

    public void setApk_name(String apk_name) {
        this.apk_name = apk_name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DateBean getData() {
        return data;
    }

    public void setData(DateBean data) {
        this.data = data;
    }

    public boolean isHasScreen() {
        return hasScreen;
    }

    public void setHasScreen(boolean hasScreen) {
        this.hasScreen = hasScreen;
    }

    public static class DateBean {

        private String url;
        private String desc;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
