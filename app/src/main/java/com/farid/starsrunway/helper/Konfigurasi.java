package com.farid.starsrunway.helper;

public class Konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    //Base URL
    private static final String BASE_URL            = "http://runway.ikc.co.id/android/";

    // ASM
    public static final String URL_ASM              = BASE_URL + "asm/asm.php?mode=getDataAsm";

    // Visit
    public static final String URL_READ_INVISIT     = BASE_URL + "visit/read_in.php";
    public static final String URL_READ_OUTVISIT    = BASE_URL + "visit/read_out.php";
    public static final String URL_INSERT_INVISIT   = BASE_URL + "visit/create_in.php";
    public static final String URL_INSERT_OUTVISIT  = BASE_URL + "visit/create_out.php";

    //Sampling
    public static final String URL_READ_COMESAMPLING   = BASE_URL + "sampling/read_come.php";
    public static final String URL_READ_GOSAMPLING     = BASE_URL + "sampling/read_go.php";
    public static final String URL_INSERT_COMESAMPLING = BASE_URL + "sampling/create_come.php";
    public static final String URL_INSERT_GOSAMPLING   = BASE_URL + "sampling/create_go.php";

    //Pi
    public static final String URL_READ_PI         = BASE_URL + "pi/read_pi.php";
    public static final String URL_READ_RESULT     = BASE_URL + "pi/read_result.php";
    public static final String URL_INSERT_PI       = BASE_URL + "pi/create_pi.php";
    public static final String URL_INSERT_RESULT   = BASE_URL + "pi/create_result.php";

    //Toko
    public static final String URL_READ_TOKO         = BASE_URL + "toko/read_toko.php";
    public static final String URL_INSERT_TOKO       = BASE_URL + "toko/create_toko.php";

    //Login
    public static final String URL_LOGIN            = BASE_URL + "login/login.php?";
    public static final String URL_REGISTER         = BASE_URL + "login/register.php";

    //Maps
    public static final String URL_VIEW_MAPS_INVISIT         = BASE_URL + "maps/read_in.php";

    //Report
    public static final String URL_READ_HITLIST     = BASE_URL + "report/read_hitlist.php?";
    public static final String URL_READ_BESTSELLER  = BASE_URL + "report/read_best_seller.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ID      = "id";
    public static final String KEY_TANGGAL = "tgl";
    public static final String KEY_JAM     = "jam";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_KD_TOKO = "kodetoko";
    public static final String KEY_STATUS  = "kodestatus";
    public static final String KEY_KD_ASM  = "kodeasm";
    public static final String KEY_KET     = "keterangan";
    public static final String KEY_STR_ID    = "str_id";
    public static final String KEY_NAMA      = "nama";
    public static final String KEY_ALAMAT    = "alamat";
    public static final String KEY_KABUPATEN = "kabupaten";
    public static final String KEY_PROVINSI  = "provinsi";
    public static final String KEY_SMS       = "sms";
    public static final String KEY_NAMA_ASM   = "namaasm";
    public static final String KEY_NAMA_TOKO  = "namatoko";
    public static final String KEY_KD_AKSI    = "kodeaksi";
    public static final String KEY_VERIFIKASI = "verifikasi";
    public static final String KEY_HASIL_AKSI = "hasilaksi";
    public static final String KEY_ARTIKEL   = "artikel";
    public static final String KEY_PRJ       = "prj";
    public static final String KEY_RETPRC    = "retprc";
    public static final String KEY_SLS       = "sls";
    public static final String KEY_STDT      = "stdt";
    public static final String KEY_STOK      = "stok";
    public static final String KEY_GR        = "gr";
    public static final String KEY_MRG       = "mrg";
    public static final String KEY_DIS       = "dis";
    public static final String KEY_TG1       = "tg1";
    public static final String KEY_TG2       = "tg1";

    public static final String KEY_TYPE    = "Type";
    public static final String KEY_USER    = "Username";
    public static final String KEY_PASS    = "Password";

    //JSON Tags
    public static final String TAG_SUCCESS   = "success";
    public static final String TAG_VISIT     = "visit";
    public static final String TAG_SAMPLING  = "sampling";
    public static final String TAG_PI        = "pi";
    public static final String TAG_TOKO      = "toko";
    public static final String TAG_HITLIST   = "hitlist";
    public static final String TAG_BESTSELLER= "bestseller";
    public static final String TAG_ID        = "id";
    public static final String TAG_TANGGAL   = "tgl";
    public static final String TAG_JAM       = "jam";
    public static final String TAG_KD_TOKO   = "kodetoko";
    public static final String TAG_LATITUDE  = "latitude";
    public static final String TAG_LONGITUDE = "longitude";
    public static final String TAG_STATUS    = "kodestatus";
    public static final String TAG_KD_ASM    = "kodeasm";
    public static final String TAG_KET       = "keterangan";
    public static final String TAG_STR_ID    = "str_id";
    public static final String TAG_NAMA      = "nama";
    public static final String TAG_ALAMAT    = "alamat";
    public static final String TAG_KABUPATEN = "kabupaten";
    public static final String TAG_PROVINSI  = "provinsi";
    public static final String TAG_SMS       = "sms";
    public static final String TAG_NO        = "no";
    public static final String TAG_NAMA_ASM  = "namaasm";
    public static final String TAG_NAMA_TOKO = "namatoko";
    public static final String TAG_KD_AKSI   = "kodeaksi";
    public static final String TAG_VERIFIKASI= "verifikasi";
    public static final String TAG_HASIL_AKSI= "hasilaksi";
    public static final String TAG_ARTIKEL   = "artikel";
    public static final String TAG_PRJ       = "prj";
    public static final String TAG_RETPRC    = "retprc";
    public static final String TAG_SLS       = "sls";
    public static final String TAG_STDT      = "stdt";
    public static final String TAG_STOK      = "stok";
    public static final String TAG_GR        = "gr";
    public static final String TAG_MRG       = "mrg";
    public static final String TAG_DIS       = "dis";
    public static final String TAG_TG1       = "tg1";
    public static final String TAG_TG2       = "tg1";
}