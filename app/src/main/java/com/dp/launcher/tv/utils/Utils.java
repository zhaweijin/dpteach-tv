package com.dp.launcher.tv.utils;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static android.content.Context.WIFI_SERVICE;

public class Utils {


    private static final String TAG = "util";


    public static void print(String tag, String value) {
        Log.v(tag, value);
    }

    public static String getActiveNetworkName(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                return info.getTypeName();
            }
        }
        return "";
    }


    public static String getFirmwareMac(Context context) {
        String mac = getMacFromHardware("eth0");
        if (mac == null || mac.equals("")) {
            mac = getMacFromHardware("wlan0");
        }
        return mac;
    }


    /**
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String str = "";
        String macSerial = "";
        Utils.print(TAG, "active type name=" + getActiveNetworkName(context));
        if (getActiveNetworkName(context).toUpperCase().equals("WIFI")) {
            try {
                Process pp = Runtime.getRuntime().exec(
                        "cat /sys/class/net/wlan0/address");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                for (; null != str; ) {
                    str = input.readLine();
                    if (str != null) {
                        macSerial = str.trim();// 去空格
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                macSerial = loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("----->" + "NetInfoManager", "getMacAddress:" + e.toString());
            }
        }
        Utils.print(TAG, "macSerial=" + macSerial);

        if (macSerial == null || "".equals(macSerial)) {
            try {
                Process pp = Runtime.getRuntime().exec(
                        "cat /sys/class/net/wlan0/address");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                for (; null != str; ) {
                    str = input.readLine();
                    if (str != null) {
                        macSerial = str.trim();// 去空格
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return macSerial.toUpperCase();
    }


    public static String getMacFromHardware(String type) {
        Utils.print(TAG,"getMacFromHardware type="+type);
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase(type)) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();

    }

    public static String getFirmwareSn() {
        return Build.SERIAL;
    }



    /**
     * @author zhaweijin
     * @fucntion 删除目录文件
     */
    /**
     * @author carter
     * @fucntion 删除目录文件
     */
    public static boolean deleteFileDir(File f) {
        boolean result = false;
        try {
            if (f.exists()) {
                File[] files = f.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            if (deleteFileDir(file))
                                result = false;
                        } else {
                            deleteFile(file);
                        }
                    }
                    f.delete();
                    result = true;
                } else {
                    f.delete();
                    return false;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            return result;
        }
        return result;
    }

    /**
     * @author zhaweijin
     * @fucntion 删除文件，根据文件对象
     */
    public static boolean deleteFile(File f) {
        boolean result = false;
        try {
            if (f.exists()) {
                Utils.print(TAG, "file=" + f.getAbsolutePath());
                f.delete();
                result = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return result;
        }
        return result;
    }


    /**
     * 获取屏幕宽带
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    /**
     * 获取屏幕高度
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }


    /**
     * 根据包名，类型启动
     *
     * @param context
     * @param packageName
     * @param activityName
     */
    public static void startAppByClassPath(Context context, String packageName, String activityName) {
        Utils.print(TAG, "startApp");
        Intent mIntent = new Intent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        ComponentName comp = new ComponentName(packageName, activityName);
        mIntent.setComponent(comp);

        context.startActivity(mIntent);
    }


    /**
     * 根据包名启动
     *
     * @param context
     * @param packageName
     */
    public static void startAppByPackage(Context context, String packageName) {
        try {
            Utils.print(TAG, "startAppByPackage");
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            // 这里如果intent为空，就说名没有安装要跳转的应用嘛
            if (intent != null) {
                Utils.print(TAG, "packageName=" + packageName);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据action启动
     *
     * @param context
     * @param action
     */
    public static void startAppByAction(Context context, String action) {
        try {
            Utils.print(TAG, "startAppByAction "+action);
            Intent intent = new Intent();
            intent.setAction(action);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 根据url启动
     *
     * @param context
     * @param mUri
     */
    public static void startAppByUrl(Context context, String mUri) {
        try {
            Utils.print(TAG, "startAppByUrl "+mUri);
            Intent intent = new Intent();
            intent.setData(Uri.parse(mUri));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 检测app 是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkPackageIsExist(Context context, String packageName) {
        try {
            PackageManager mPm = context.getPackageManager();
            ApplicationInfo mAppInfo = mPm.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Utils.print("TAG", "Exception when retrieving package: " + packageName);
            // show dialog
            return false;
        }
    }




    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context, String packageName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }




    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkNetworkIsActive(Context context) {
        boolean mIsNetworkUp = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info != null) {
            mIsNetworkUp = info.isAvailable();
        }
        return mIsNetworkUp;
    }


    /**
     * 获取WiFi信号强度
     *
     * @param context
     * @return
     */
    public static int getWiFiLevel(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        int result = 0;
        if (info.getBSSID() != null) {
            // 链接信号强度
            result = WifiManager.calculateSignalLevel(info.getRssi(), 5);
        }
        return result;

    }




    public static String getFirmwareVersion() {
        Object systemProperties = null;
        try {
            systemProperties = Class.forName("android.os.SystemProperties").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (systemProperties == null)
            return "";

        String version = (String) ReflectUtils.invokeMethod(systemProperties, "get", new Class[]{String.class}, new Object[]{"ro.product.firmware"});
        version = version.trim();
        return version;
    }





    public static String getFormatTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        return sdf.format(date);
    }


    public static String getFormatTime2() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    public static String getTime() {
        Date date = new Date();
        return date.getTime() + "";
    }

    public static long getLongTime() {
        Date date = new Date();
        return date.getTime();
    }


    public static int convertY(Context mContext,int value,int col){
        return (int)((value*col/1080f)*Utils.getScreenHeight(mContext));
    }

    public static int convertX(Context mContext,int value,int row){
        return (int)((value*row/1920f)*Utils.getScreenWidth(mContext));
    }

}
