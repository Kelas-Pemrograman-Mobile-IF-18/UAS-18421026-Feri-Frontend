package com.feri.utspenjualankopibubuk.server;

public class BaseURL {
    public static String baseURL = "http://192.168.43.248:5050/";

    public static String login = baseURL + "user/login";
    public static String register = baseURL + "user/registrasi";

    //kopi
    public static String dataKopi = baseURL + "kopi/datakopi";

    public static String editDataKopi = baseURL + "kopi/ubah/";
    public static String hapusDataKopi = baseURL + "kopi/hapus/";
    public static String tambahDataKopi = baseURL + "kopi/input";
}
