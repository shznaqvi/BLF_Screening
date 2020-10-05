package edu.aku.hassannaqvi.blf_screening.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.blf_screening.contracts.FormsContract.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "blf";
    private String _ID = "";
    private String _UID = "";
    private String sysdate = "";
    private String a01 = ""; // Date
    private String a02 = ""; // Time
    private String a03 = ""; // Interviewer
    private String username = ""; // Interviewer
    private String a04 = ""; // Province
    private String a05 = ""; // District
    private String a05code = ""; // District Code
    private String refno = ""; // Reference Number
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String sInfo = "";
    private String sB = "";
    private String sC = "";
    private String sD = "";
    public String sl2 = "";
    public String sl301 = "";


    private String a05a = "";
    private String a05b = "";
    // private String a06= "";
    private String a07 = "";
    private String a08 = "";
    private String a09 = "";
    private String a10 = "";
    private String a11 = "";
    private String a12 = "";
    private String a13dd = "";
    private String a13mm = "";
    private String a13yy = "";
    private String a14mm = "";
    private String a14yy = "";
    private String a15 = "";
    private String a16 = "";
    private String a17 = "";
    private String a18 = "";
    private String a19 = "";
    private String a20 = "";
    private String a21 = "";
    private String a22pos = "";
    private String a22org = "";
    private String a23 = "";
    private String b01 = "";
    private String b02 = "";
    private String b03 = "";
    private String b04 = "";
    private String b05 = "";
    private String b06 = "";
    private String b06ax = "";
    private String b07 = "";
    private String b0801 = "";
    private String b0802 = "";
    private String b0803 = "";
    private String b0804 = "";
    private String b0805 = "";
    private String b0806 = "";
    private String b0807 = "";
    private String b9 = "";
    private String b10 = "";
    private String b11 = "";
    //   private String b11096;
    private String b11096x = "";
    private String b11mc = "";
    //   private String b11ws;
    private String b12 = "";
    private String b1301 = "";
    private String b1302 = "";
    private String b1303 = "";
    private String b1304 = "";
    private String b1305 = "";
    private String b1306 = "";
    private String b1307 = "";
    private String b1308 = "";
    private String b1309 = "";
    private String b13096 = "";
    private String b14 = "";
    private String b1501 = "";
    private String b1502 = "";
    private String b1503 = "";
    private String b1504 = "";
    private String b1505 = "";
    private String b15096 = "";
    private String b16 = "";
    private String b17 = "";
    private String c01 = "";
    private String c0201 = "";
    private String c0202 = "";
    private String c03 = "";
    private String c04 = "";
    private String c05 = "";
    private String c06 = "";
    private String c07 = "";
    private String c08 = "";
    private String d01 = "";
    private String d02 = "";
    private String d03 = "";
    private String d04 = "";
    private String d05 = "";

    public String sf101 = "";
    public String sf102 = "";
    public String sf103 = "";
    public String sf104 = "";
    public String sf105 = "";
    public String sf2 = "";
    public String sf3 = "";
    public String sf4 = "";
    public String sf5 = "";
    public String sf6 = "";
    public String sf701 = "";
    public String sf702 = "";
    public String sf8 = "";
    public String sf9 = "";
    public String sf10 = "";
    public String sf11 = "";
    public String sf12 = "";
    public String sf1296x = "";
    public String sf1301 = "";
    public String sf1302 = "";
    public String sf1303 = "";
    public String sf1304 = "";
    public String sf1305 = "";
    public String sf1306 = "";
    public String sf1307 = "";
    public String sf1308 = "";
    public String sf1309 = "";
    public String sf1396x = "";
    public String sf14 = "";
    public String sf1403x = "";
    public String sf1501 = "";
    public String sf1502 = "";
    public String sf1503 = "";
    public String sf1504 = "";
    public String sf1505 = "";
    public String sf1506 = "";
    public String sf1507 = "";
    public String sf1508 = "";
    public String sf1509 = "";
    public String sf16 = "";
    public String sf17 = "";
    public String sf18 = "";
    public String sf1901 = "";
    public String sf1902 = "";
    public String sf20 = "";
    public String sl302 = "";
    public String sl303 = "";
    public String sl4 = "";
    public String sl5 = "";
    public String sl601 = "";
    public String sl602 = "";
    public String sl701 = "";
    public String sl702 = "";
    public String sl703 = "";
    public String sl8 = "";
    public String sl9 = "";
    public String sl10 = "";
    public String sl11 = "";
    private String sF = ""; //SectionSF
    private String sL = ""; //SectionSL
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String tagid = "";

    //For section selection
    private SectionSelection secSelection;


    public Form() {
    }

    public SectionSelection getSecSelection() {
        return secSelection;
    }

    public void setSecSelection(SectionSelection secSelection) {
        this.secSelection = secSelection;
    }

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getA04() {
        return a04;
    }

    public void setA04(String a04) {
        this.a04 = a04;
    }

    public String getA05code() {
        return a05code;
    }

    public void setA05code(String a05code) {
        this.a05code = a05code;
    }

/*    public String getA06() {
        return a06;
    }

    public Form setA06(String a06) {
        this.a06 = a06;
        return this;
    }*/

    public String getUsername() {
        return username;
    }

    public Form setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getA07() {
        return a07;
    }

    public Form setA07(String a07) {
        this.a07 = a07;
        return this;
    }

    public String getA08() {
        return a08;
    }

    public Form setA08(String a08) {
        this.a08 = a08;
        return this;
    }

    public String getA09() {
        return a09;
    }

    public Form setA09(String a09) {
        this.a09 = a09;
        return this;
    }

    public String getA10() {
        return a10;
    }

    public Form setA10(String a10) {
        this.a10 = a10;
        return this;
    }

    public String getA11() {
        return a11;
    }

    public Form setA11(String a11) {
        this.a11 = a11;
        return this;
    }

    public String getA12() {
        return a12;
    }

    public Form setA12(String a12) {
        this.a12 = a12;
        return this;
    }

    public String getA13dd() {
        return a13dd;
    }

    public Form setA13dd(String a13dd) {
        this.a13dd = a13dd;
        return this;
    }

    public String getA13mm() {
        return a13mm;
    }

    public Form setA13mm(String a13mm) {
        this.a13mm = a13mm;
        return this;
    }

    public String getA13yy() {
        return a13yy;
    }

    public Form setA13yy(String a13yy) {
        this.a13yy = a13yy;
        return this;
    }

    public String getA14mm() {
        return a14mm;
    }

    public Form setA14mm(String a14mm) {
        this.a14mm = a14mm;
        return this;
    }

    public String getA14yy() {
        return a14yy;
    }

    public Form setA14yy(String a14yy) {
        this.a14yy = a14yy;
        return this;
    }

    public String getA15() {
        return a15;
    }

    public Form setA15(String a15) {
        this.a15 = a15;
        return this;
    }

    public String getA16() {
        return a16;
    }

    public Form setA16(String a16) {
        this.a16 = a16;
        return this;
    }

    public String getA17() {
        return a17;
    }

    public Form setA17(String a17) {
        this.a17 = a17;
        return this;
    }

    public String getA18() {
        return a18;
    }

    public Form setA18(String a18) {
        this.a18 = a18;
        return this;
    }

    public String getA19() {
        return a19;
    }

    public Form setA19(String a19) {
        this.a19 = a19;
        return this;
    }

    public String getA20() {
        return a20;
    }

    public Form setA20(String a20) {
        this.a20 = a20;
        return this;
    }

    public String getA21() {
        return a21;
    }

    public Form setA21(String a21) {
        this.a21 = a21;
        return this;
    }

    public String getA22pos() {
        return a22pos;
    }

    public Form setA22pos(String a22pos) {
        this.a22pos = a22pos;
        return this;
    }

    public String getA22org() {
        return a22org;
    }

    public Form setA22org(String a22org) {
        this.a22org = a22org;
        return this;
    }

    public String getA23() {
        return a23;
    }

    public Form setA23(String a23) {
        this.a23 = a23;
        return this;
    }

    public String getB01() {
        return b01;
    }

    public Form setB01(String b01) {
        this.b01 = b01;
        return this;
    }

    public String getB02() {
        return b02;
    }

    public Form setB02(String b02) {
        this.b02 = b02;
        return this;
    }

    public String getB03() {
        return b03;
    }

    public Form setB03(String b03) {
        this.b03 = b03;
        return this;
    }

    public String getB04() {
        return b04;
    }

    public Form setB04(String b04) {
        this.b04 = b04;
        return this;
    }

    public String getB05() {
        return b05;
    }

    public Form setB05(String b05) {
        this.b05 = b05;
        return this;
    }

    public String getB06() {
        return b06;
    }

    public Form setB06(String b06) {
        this.b06 = b06;
        return this;
    }

    public String getB06ax() {
        return b06ax;
    }

    public Form setB06ax(String b06ax) {
        this.b06ax = b06ax;
        return this;
    }

    public String getB07() {
        return b07;
    }

    public Form setB07(String b07) {
        this.b07 = b07;
        return this;
    }

    public String getB0801() {
        return b0801;
    }

    public Form setB0801(String b0801) {
        this.b0801 = b0801;
        return this;
    }

    public String getB0802() {
        return b0802;
    }

    public Form setB0802(String b0802) {
        this.b0802 = b0802;
        return this;
    }

    public String getB0803() {
        return b0803;
    }

    public Form setB0803(String b0803) {
        this.b0803 = b0803;
        return this;
    }

    public String getB0804() {
        return b0804;
    }

    public Form setB0804(String b0804) {
        this.b0804 = b0804;
        return this;
    }

    public String getB0805() {
        return b0805;
    }

    public Form setB0805(String b0805) {
        this.b0805 = b0805;
        return this;
    }

    public String getB0806() {
        return b0806;
    }

    public Form setB0806(String b0806) {
        this.b0806 = b0806;
        return this;
    }

    public String getB0807() {
        return b0807;
    }

    public Form setB0807(String b0807) {
        this.b0807 = b0807;
        return this;
    }

    public String getB9() {
        return b9;
    }

    public Form setB9(String b9) {
        this.b9 = b9;
        return this;
    }

    public String getB10() {
        return b10;
    }

    public Form setB10(String b10) {
        this.b10 = b10;
        return this;
    }

    public String getB11() {
        return b11;
    }

    public Form setB11(String b11) {
        this.b11 = b11;
        return this;
    }

    /*public String getB11096() {
        return b11096;
    }

    public Form setB11096(String b11096) {
        this.b11096 = b11096;
        return this;
    }*/

    public String getB11096x() {
        return b11096x;
    }

    public Form setB11096x(String b11096x) {
        this.b11096x = b11096x;
        return this;
    }

    public String getB11mc() {
        return b11mc;
    }

    public Form setB11mc(String b11mc) {
        this.b11mc = b11mc;
        return this;
    }

   /* public String getB11ws() {
        return b11ws;
    }

    public Form setB11ws(String b11ws) {
        this.b11ws = b11ws;
        return this;
    }*/

    public String getB12() {
        return b12;
    }

    public Form setB12(String b12) {
        this.b12 = b12;
        return this;
    }

    public String getB1301() {
        return b1301;
    }

    public Form setB1301(String b1301) {
        this.b1301 = b1301;
        return this;
    }

    public String getB1302() {
        return b1302;
    }

    public Form setB1302(String b1302) {
        this.b1302 = b1302;
        return this;
    }

    public String getB1303() {
        return b1303;
    }

    public Form setB1303(String b1303) {
        this.b1303 = b1303;
        return this;
    }

    public String getB1304() {
        return b1304;
    }

    public Form setB1304(String b1304) {
        this.b1304 = b1304;
        return this;
    }

    public String getB1305() {
        return b1305;
    }

    public Form setB1305(String b1305) {
        this.b1305 = b1305;
        return this;
    }

    public String getB1306() {
        return b1306;
    }

    public Form setB1306(String b1306) {
        this.b1306 = b1306;
        return this;
    }

    public String getB1307() {
        return b1307;
    }

    public Form setB1307(String b1307) {
        this.b1307 = b1307;
        return this;
    }

    public String getB1308() {
        return b1308;
    }

    public Form setB1308(String b1308) {
        this.b1308 = b1308;
        return this;
    }

    public String getB1309() {
        return b1309;
    }

    public Form setB1309(String b1309) {
        this.b1309 = b1309;
        return this;
    }

    public String getB13096() {
        return b13096;
    }

    public Form setB13096(String b13096) {
        this.b13096 = b13096;
        return this;
    }

    public String getB14() {
        return b14;
    }

    public Form setB14(String b14) {
        this.b14 = b14;
        return this;
    }

    public String getB1501() {
        return b1501;
    }

    public Form setB1501(String b1501) {
        this.b1501 = b1501;
        return this;
    }

    public String getB1502() {
        return b1502;
    }

    public Form setB1502(String b1502) {
        this.b1502 = b1502;
        return this;
    }

    public String getB1503() {
        return b1503;
    }

    public Form setB1503(String b1503) {
        this.b1503 = b1503;
        return this;
    }

    public String getB1504() {
        return b1504;
    }

    public Form setB1504(String b1504) {
        this.b1504 = b1504;
        return this;
    }

    public String getB1505() {
        return b1505;
    }

    public Form setB1505(String b1505) {
        this.b1505 = b1505;
        return this;
    }

    public String getB15096() {
        return b15096;
    }

    public Form setB15096(String b15096) {
        this.b15096 = b15096;
        return this;
    }

    public String getB16() {
        return b16;
    }

    public Form setB16(String b16) {
        this.b16 = b16;
        return this;
    }

    public String getB17() {
        return b17;
    }

    public Form setB17(String b17) {
        this.b17 = b17;
        return this;
    }

    public String getC01() {
        return c01;
    }

    public Form setC01(String c01) {
        this.c01 = c01;
        return this;
    }

    public String getC0201() {
        return c0201;
    }

    public Form setC0201(String c0201) {
        this.c0201 = c0201;
        return this;
    }

    public String getC0202() {
        return c0202;
    }

    public Form setC0202(String c0202) {
        this.c0202 = c0202;
        return this;
    }

    public String getC03() {
        return c03;
    }

    public Form setC03(String c03) {
        this.c03 = c03;
        return this;
    }

    public String getC04() {
        return c04;
    }

    public Form setC04(String c04) {
        this.c04 = c04;
        return this;
    }

    public String getC05() {
        return c05;
    }

    public Form setC05(String c05) {
        this.c05 = c05;
        return this;
    }

    public String getC06() {
        return c06;
    }

    public Form setC06(String c06) {
        this.c06 = c06;
        return this;
    }

    public String getC07() {
        return c07;
    }

    public Form setC07(String c07) {
        this.c07 = c07;
        return this;
    }

    public String getC08() {
        return c08;
    }

    public Form setC08(String c08) {
        this.c08 = c08;
        return this;
    }

    public String getD01() {
        return d01;
    }

    public Form setD01(String d01) {
        this.d01 = d01;
        return this;
    }

    public String getD02() {
        return d02;
    }

    public Form setD02(String d02) {
        this.d02 = d02;
        return this;
    }

    public String getD03() {
        return d03;
    }

    public Form setD03(String d03) {
        this.d03 = d03;
        return this;
    }

    public String getD04() {
        return d04;
    }

    public Form setD04(String d04) {
        this.d04 = d04;
        return this;
    }

    public String getD05() {
        return d05;
    }

    public Form setD05(String d05) {
        this.d05 = d05;
        return this;
    }

    public String getSf101() {
        return sf101;
    }

    public Form setSf101(String sf101) {
        this.sf101 = sf101;
        return this;
    }

    public String getSf102() {
        return sf102;
    }

    public Form setSf102(String sf102) {
        this.sf102 = sf102;
        return this;
    }

    public String getSf103() {
        return sf103;
    }

    public Form setSf103(String sf103) {
        this.sf103 = sf103;
        return this;
    }

    public String getSf104() {
        return sf104;
    }

    public Form setSf104(String sf104) {
        this.sf104 = sf104;
        return this;
    }

    public String getSf105() {
        return sf105;
    }

    public Form setSf105(String sf105) {
        this.sf105 = sf105;
        return this;
    }

    public String getSf2() {
        return sf2;
    }

    public Form setSf2(String sf2) {
        this.sf2 = sf2;
        return this;
    }

    public String getSf3() {
        return sf3;
    }

    public Form setSf3(String sf3) {
        this.sf3 = sf3;
        return this;
    }

    public String getSf4() {
        return sf4;
    }

    public Form setSf4(String sf4) {
        this.sf4 = sf4;
        return this;
    }

    public String getSf5() {
        return sf5;
    }

    public Form setSf5(String sf5) {
        this.sf5 = sf5;
        return this;
    }

    public String getSf6() {
        return sf6;
    }

    public Form setSf6(String sf6) {
        this.sf6 = sf6;
        return this;
    }

    public String getSf701() {
        return sf701;
    }

    public Form setSf701(String sf701) {
        this.sf701 = sf701;
        return this;
    }

    public String getSf702() {
        return sf702;
    }

    public Form setSf702(String sf702) {
        this.sf702 = sf702;
        return this;
    }

    public String getSf8() {
        return sf8;
    }

    public Form setSf8(String sf8) {
        this.sf8 = sf8;
        return this;
    }

    public String getSf9() {
        return sf9;
    }

    public Form setSf9(String sf9) {
        this.sf9 = sf9;
        return this;
    }

    public String getSf10() {
        return sf10;
    }

    public Form setSf10(String sf10) {
        this.sf10 = sf10;
        return this;
    }

    public String getSf11() {
        return sf11;
    }

    public Form setSf11(String sf11) {
        this.sf11 = sf11;
        return this;
    }

    public String getSf12() {
        return sf12;
    }

    public Form setSf12(String sf12) {
        this.sf12 = sf12;
        return this;
    }

    public String getSf1296x() {
        return sf1296x;
    }

    public Form setSf1296x(String sf1296x) {
        this.sf1296x = sf1296x;
        return this;
    }

    public String getSf1301() {
        return sf1301;
    }

    public Form setSf1301(String sf1301) {
        this.sf1301 = sf1301;
        return this;
    }

    public String getSf1302() {
        return sf1302;
    }

    public Form setSf1302(String sf1302) {
        this.sf1302 = sf1302;
        return this;
    }

    public String getSf1303() {
        return sf1303;
    }

    public Form setSf1303(String sf1303) {
        this.sf1303 = sf1303;
        return this;
    }

    public String getSf1304() {
        return sf1304;
    }

    public Form setSf1304(String sf1304) {
        this.sf1304 = sf1304;
        return this;
    }

    public String getSf1305() {
        return sf1305;
    }

    public Form setSf1305(String sf1305) {
        this.sf1305 = sf1305;
        return this;
    }

    public String getSf1306() {
        return sf1306;
    }

    public Form setSf1306(String sf1306) {
        this.sf1306 = sf1306;
        return this;
    }

    public String getSf1307() {
        return sf1307;
    }

    public Form setSf1307(String sf1307) {
        this.sf1307 = sf1307;
        return this;
    }

    public String getSf1308() {
        return sf1308;
    }

    public Form setSf1308(String sf1308) {
        this.sf1308 = sf1308;
        return this;
    }

    public String getSf1309() {
        return sf1309;
    }

    public Form setSf1309(String sf1309) {
        this.sf1309 = sf1309;
        return this;
    }

    public String getSf1396x() {
        return sf1396x;
    }

    public Form setSf1396x(String sf1396x) {
        this.sf1396x = sf1396x;
        return this;
    }

    public String getSf14() {
        return sf14;
    }

    public Form setSf14(String sf14) {
        this.sf14 = sf14;
        return this;
    }

    public String getSf1403x() {
        return sf1403x;
    }

    public Form setSf1403x(String sf1403x) {
        this.sf1403x = sf1403x;
        return this;
    }

    public String getSf1501() {
        return sf1501;
    }

    public Form setSf1501(String sf1501) {
        this.sf1501 = sf1501;
        return this;
    }

    public String getSf1502() {
        return sf1502;
    }

    public Form setSf1502(String sf1502) {
        this.sf1502 = sf1502;
        return this;
    }

    public String getSf1503() {
        return sf1503;
    }

    public Form setSf1503(String sf1503) {
        this.sf1503 = sf1503;
        return this;
    }

    public String getSf1504() {
        return sf1504;
    }

    public Form setSf1504(String sf1504) {
        this.sf1504 = sf1504;
        return this;
    }

    public String getSf1505() {
        return sf1505;
    }

    public Form setSf1505(String sf1505) {
        this.sf1505 = sf1505;
        return this;
    }

    public String getSf1506() {
        return sf1506;
    }

    public Form setSf1506(String sf1506) {
        this.sf1506 = sf1506;
        return this;
    }

    public String getSf1507() {
        return sf1507;
    }

    public Form setSf1507(String sf1507) {
        this.sf1507 = sf1507;
        return this;
    }

    public String getSf1508() {
        return sf1508;
    }

    public Form setSf1508(String sf1508) {
        this.sf1508 = sf1508;
        return this;
    }

    public String getSf1509() {
        return sf1509;
    }

    public Form setSf1509(String sf1509) {
        this.sf1509 = sf1509;
        return this;
    }

    public String getSf16() {
        return sf16;
    }

    public Form setSf16(String sf16) {
        this.sf16 = sf16;
        return this;
    }

    public String getSf17() {
        return sf17;
    }

    public Form setSf17(String sf17) {
        this.sf17 = sf17;
        return this;
    }

    public String getSf18() {
        return sf18;
    }

    public Form setSf18(String sf18) {
        this.sf18 = sf18;
        return this;
    }

    public String getSf1901() {
        return sf1901;
    }

    public Form setSf1901(String sf1901) {
        this.sf1901 = sf1901;
        return this;
    }

    public String getSf1902() {
        return sf1902;
    }

    public Form setSf1902(String sf1902) {
        this.sf1902 = sf1902;
        return this;
    }

    public String getSf20() {
        return sf20;
    }

    public Form setSf20(String sf17) {
        this.sf20 = sf20;
        return this;
    }

    public String getSl2() {
        return sl2;
    }

    public Form setSl2(String sl2) {
        this.sl2 = sl2;
        return this;
    }

    public String getSl301() {
        return sl301;
    }

    public Form setSl301(String sl301) {
        this.sl301 = sl301;
        return this;
    }

    public String getSl302() {
        return sl302;
    }

    public Form setSl302(String sl302) {
        this.sl302 = sl302;
        return this;
    }

    public String getSl303() {
        return sl303;
    }

    public Form setSl303(String sl303) {
        this.sl303 = sl303;
        return this;
    }

    public String getSl4() {
        return sl4;
    }

    public Form setSl4(String sl4) {
        this.sl4 = sl4;
        return this;
    }

    public String getSl5() {
        return sl5;
    }

    public Form setSl5(String sl5) {
        this.sl5 = sl5;
        return this;
    }

    public String getSl601() {
        return sl601;
    }

    public Form setSl601(String sl601) {
        this.sl601 = sl601;
        return this;
    }

    public String getSl602() {
        return sl602;
    }

    public Form setSl602(String sl602) {
        this.sl602 = sl602;
        return this;
    }

    public String getSl701() {
        return sl701;
    }

    public Form setSl701(String sl701) {
        this.sl701 = sl701;
        return this;
    }

    public String getSl702() {
        return sl702;
    }

    public Form setSl702(String sl702) {
        this.sl702 = sl702;
        return this;
    }

    public String getSl703() {
        return sl703;
    }

    public Form setSl703(String sl703) {
        this.sl703 = sl703;
        return this;
    }

    public String getSl8() {
        return sl8;
    }

    public Form setSl8(String sl8) {
        this.sl8 = sl8;
        return this;
    }

    public String getSl9() {
        return sl9;
    }

    public Form setSl9(String sl9) {
        this.sl9 = sl9;
        return this;
    }

    public String getSl10() {
        return sl10;
    }

    public Form setSl10(String sl10) {
        this.sl10 = sl10;
        return this;
    }

    public String getSl11() {
        return sl11;
    }

    public Form setSl11(String sl11) {
        this.sl11 = sl11;
        return this;
    }

    public String getGpslat() {
        return gpslat;
    }

    public Form setGpslat(String gpslat) {
        this.gpslat = gpslat;
        return this;
    }

    public String getGpslng() {
        return gpslng;
    }

    public Form setGpslng(String gpslng) {
        this.gpslng = gpslng;
        return this;
    }

    public String getGpsdate() {
        return gpsdate;
    }

    public Form setGpsdate(String gpsdate) {
        this.gpsdate = gpsdate;
        return this;
    }

    public String getGpsacc() {
        return gpsacc;
    }

    public Form setGpsacc(String gpsacc) {
        this.gpsacc = gpsacc;
        return this;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public Form setDeviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public String getTagid() {
        return tagid;
    }

    public Form setTagid(String tagid) {
        this.tagid = tagid;
        return this;
    }

    public String getA05() {
        return a05;
    }

    public void setA05(String a05) {
        this.a05 = a05;
    }

    public String getA05a() {
        return a05a;
    }

    public Form setA05a(String a05a) {
        this.a05a = a05a;
        return this;
    }

    public String getA05b() {
        return a05b;
    }

    public Form setA05b(String a05b) {
        this.a05b = a05b;
        return this;
    }
//=====================


    //====================

    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }


    public String getsD() {
        return sD;
    }

    public void setsD(String sD) {
        this.sD = sD;
    }


    public String getsL() {
        return sL;
    }

    public void setsL(String sL) {
        this.sL = sL;
    }


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getA01() {
        return a01;
    }

    public void setA01(String a01) {
        this.a01 = a01;
    }

    public String getA02() {
        return a02;
    }

    public void setA02(String a02) {
        this.a02 = a02;
    }

    public String getA03() {
        return a03;
    }

    public void setA03(String a03) {
        this.a03 = a03;
    }


    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }


    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }


    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }


    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }


    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }


    public Form Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.sysdate = jsonObject.getString(FormsTable.COLUMN_SYSDATE);
        this.a01 = jsonObject.getString(FormsTable.COLUMN_A01);
        this.a02 = jsonObject.getString(FormsTable.COLUMN_A02);
        this.a03 = jsonObject.getString(FormsTable.COLUMN_A03);
        this.a04 = jsonObject.getString(FormsTable.COLUMN_A04);
        this.a05 = jsonObject.getString(FormsTable.COLUMN_A05);
        this.a05code = jsonObject.getString(FormsTable.COLUMN_A05CODE);
        this.a08 = jsonObject.getString(FormsTable.COLUMN_A08);
        this.refno = jsonObject.getString(FormsTable.COLUMN_REFNO);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.sInfo = jsonObject.getString(FormsTable.COLUMN_SINFO);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);
        this.sC = jsonObject.getString(FormsTable.COLUMN_SC);
        this.sD = jsonObject.getString(FormsTable.COLUMN_SD);
        this.sF = jsonObject.getString(FormsTable.COLUMN_SF);
        this.sL = jsonObject.getString(FormsTable.COLUMN_SL);

        return this;

    }

    public Form Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYSDATE));
        this.a01 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A01));
        this.a02 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A02));
        this.a03 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A03));
        this.a04 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A04));
        this.a05 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A05));
        this.a05code = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A05CODE));
        this.a08 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_A08));
        this.refno = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_REFNO));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
/*        this.sInfo = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SINFO));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB));
        this.sD = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SD));
        this.sE = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SE));
        this.sF = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SF));
        this.sG = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SG));
        this.sH = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SH));
        this.sI = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SI));
        this.sJ = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SJ));
        this.sK = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SK));
        this.sL = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SL));*/
        sInfoHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SINFO)));
        sBHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB)));
        sCHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SC)));
        sDHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SD)));
        sFHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SF)));
        sLHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SL)));


        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String sInfotoString() {
        JSONObject json = new JSONObject();

        try {
            json
                    .put("a05a", a05a)
                    .put("a05b", a05b)
                    /*                    .put("a05code", a05code)
                                    .put("refno", refno)
                                       .put("istatus", istatus)
                                       .put("istatus96x", istatus96x)
                                       .put("endingdatetime", endingdatetime)*/
                    //      .put("a06", a06)
                    .put("username", username)
                    .put("a07", a07)
                    //.put("a08", a08)
                    .put("a09", a09)
                    .put("a10", a10)
                    .put("a11", a11)
                    .put("a12", a12)
                    .put("a13dd", a13dd)
                    .put("a13mm", a13mm)
                    .put("a13yy", a13yy)
                    .put("a14mm", a14mm)
                    .put("a14yy", a14yy)
                    .put("a15", a15)
                    .put("a16", a16)
                    .put("a17", a17)
                    .put("a18", a18)
                    .put("a19", a19)
                    .put("a20", a20)
                    .put("a21", a21)
                    .put("a22pos", a22pos)
                    .put("a22org", a22org)
                    .put("a23", a23);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    public String sBtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("b01", b01)
                    .put("b02", b02)
                    .put("b03", b03)
                    .put("b04", b04)
                    .put("b05", b05)
                    .put("b06", b06)
                    .put("b06ax", b06ax)
                    .put("b07", b07)
                    .put("b0801", b0801)
                    .put("b0802", b0802)
                    .put("b0803", b0803)
                    .put("b0804", b0804)
                    .put("b0805", b0805)
                    .put("b0806", b0806)
                    .put("b0807", b0807)
                    .put("b9", b9)
                    .put("b10", b10)
                    .put("b11", b11)
                    .put("b11mc", b11mc)
                    //        .put("b11096", b11096)
                    .put("b11096x", b11096x)
                    .put("b11mc", b11mc)
                    //        .put("b11ws", b11ws)
                    .put("b12", b12)
                    .put("b1301", b1301)
                    .put("b1302", b1302)
                    .put("b1303", b1303)
                    .put("b1304", b1304)
                    .put("b1305", b1305)
                    .put("b1306", b1306)
                    .put("b1307", b1307)
                    .put("b1308", b1308)
                    .put("b1309", b1309)
                    .put("b13096", b13096)
                    .put("b14", b14)
                    .put("b1501", b1501)
                    .put("b1502", b1502)
                    .put("b1503", b1503)
                    .put("b1504", b1504)
                    .put("b1505", b1505)
                    .put("b15096", b15096)
                    .put("b16", b16)
                    .put("b17", b17);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public String sCtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("c01", c01)
                    .put("c0201", c0201)
                    .put("c0202", c0202)
                    .put("c03", c03)
                    .put("c04", c04)
                    .put("c05", c05)
                    .put("c06", c06)
                    .put("c07", c07)
                    .put("c08", c08);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    public String sDtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("d01", d01)
                    .put("d02", d02)
                    .put("d03", d03)
                    .put("d04", d04)
                    .put("d05", d05);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    public String sFtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("sf101", sf101)
                    .put("sf102", sf102)
                    .put("sf103", sf103)
                    .put("sf104", sf104)
                    .put("sf105", sf105)
                    .put("sf2", sf2)
                    .put("sf3 ", sf3)
                    .put("sf4 ", sf4)
                    .put("sf5 ", sf5)
                    .put("sf6 ", sf6)
                    .put("sf701", sf701)
                    .put("sf702", sf702)
                    .put("sf8", sf8)
                    .put("sf9", sf9)
                    .put("sf10", sf10)
                    .put("sf11", sf11)
                    .put("sf12", sf12)
                    .put("sf1296x", sf1296x)
                    .put("sf1301", sf1301)
                    .put("sf1302", sf1302)
                    .put("sf1303", sf1303)
                    .put("sf1304", sf1304)
                    .put("sf1305", sf1305)
                    .put("sf1306", sf1306)
                    .put("sf1307", sf1307)
                    .put("sf1308", sf1308)
                    .put("sf1309", sf1309)
                    .put("sf1396x", sf1396x)
                    .put("sf14", sf14)
                    .put("sf1403x", sf1403x)
                    .put("sf1501", sf1501)
                    .put("sf1502", sf1502)
                    .put("sf1503", sf1503)
                    .put("sf1504", sf1504)
                    .put("sf1505", sf1505)
                    .put("sf1506", sf1506)
                    .put("sf1507", sf1507)
                    .put("sf1508", sf1508)
                    .put("sf1509", sf1509)
                    .put("sf16", sf16)
                    .put("sf17", sf17)
                    .put("sf18", sf18)
                    .put("sf1901", sf1901)
                    .put("sf1902", sf1902)
                    .put("sf20", sf20);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    public String sLtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("sl2", sl2)
                    .put("sl301", sl301)
                    .put("sl302", sl302)
                    .put("sl303", sl303)
                    .put("sl4", sl4)
                    .put("sl5", sl5)
                    .put("sl601", sl601)
                    .put("sl602", sl602)
                    .put("sl701", sl701)
                    .put("sl702", sl702)
                    .put("sl703", sl703)
                    .put("sl8", sl8)
                    .put("sl9", sl9)
                    .put("sl10", sl10)
                    .put("sl11", sl11)
                    .put("gpslat", gpslat)
                    .put("gpslng", gpslng)
                    .put("gpsdate", gpsdate)
                    .put("gpsacc", gpsacc)
                    .put("deviceid", deviceid)
                    .put("tagid", tagid)
                    .put("appversion", appversion);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\":, \"" + e.getMessage() + "\"";

        }
        return json.toString();
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);

            json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(FormsTable.COLUMN_A01, this.a01 == null ? JSONObject.NULL : this.a01);
            json.put(FormsTable.COLUMN_A02, this.a02 == null ? JSONObject.NULL : this.a02);
            json.put(FormsTable.COLUMN_A03, this.a03 == null ? JSONObject.NULL : this.a03);
            json.put(FormsTable.COLUMN_A04, this.a04 == null ? JSONObject.NULL : this.a04);
            json.put(FormsTable.COLUMN_A05, this.a05 == null ? JSONObject.NULL : this.a05);
            json.put(FormsTable.COLUMN_A05CODE, this.a05code == null ? JSONObject.NULL : this.a05code);
            json.put(FormsTable.COLUMN_A08, this.a08 == null ? JSONObject.NULL : this.a08);
            json.put(FormsTable.COLUMN_REFNO, this.refno == null ? JSONObject.NULL : this.refno);
            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);

         /*   if (this.sInfo != null && !this.sInfo.equals("")) {
                json.put(FormsTable.COLUMN_SINFO, new JSONObject(this.sInfo));
            }

            if (this.sB != null && !this.sB.equals("")) {
                json.put(FormsTable.COLUMN_SB, new JSONObject(this.sB));
            }

            if (this.sC != null && !this.sC.equals("")) {
                json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
            }

            if (this.sD != null && !this.sD.equals("")) {
                json.put(FormsTable.COLUMN_SD, new JSONObject(this.sD));
            }

            if (this.sE != null && !this.sE.equals("")) {
                json.put(FormsTable.COLUMN_SE, new JSONObject(this.sE));
            }

            if (this.sF != null && !this.sF.equals("")) {
                json.put(FormsTable.COLUMN_SF, new JSONObject(this.sF));
            }

            if (this.sG != null && !this.sG.equals("")) {
                json.put(FormsTable.COLUMN_SG, new JSONObject(this.sG));
            }

            if (this.sH != null && !this.sH.equals("")) {
                json.put(FormsTable.COLUMN_SH, new JSONObject(this.sH));
            }

            if (this.sI != null && !this.sI.equals("")) {
                json.put(FormsTable.COLUMN_SI, new JSONObject(this.sI));
            }

            if (this.sJ != null && !this.sJ.equals("")) {
                json.put(FormsTable.COLUMN_SJ, new JSONObject(this.sJ));
            }

            if (this.sK != null && !this.sK.equals("")) {
                json.put(FormsTable.COLUMN_SK, new JSONObject(this.sK));
            }

            if (this.sL != null && !this.sL.equals("")) {
                json.put(FormsTable.COLUMN_SL, new JSONObject(this.sL));
            }*/


            json.put(FormsTable.COLUMN_SINFO, new JSONObject(sInfotoString()));
            json.put(FormsTable.COLUMN_SB, new JSONObject(sBtoString()));
            json.put(FormsTable.COLUMN_SC, new JSONObject(sCtoString()));
            json.put(FormsTable.COLUMN_SD, new JSONObject(sDtoString()));
            json.put(FormsTable.COLUMN_SF, new JSONObject(sFtoString()));
            json.put(FormsTable.COLUMN_SL, new JSONObject(sLtoString()));


            if (this.sC != null && !this.sC.equals("")) {
                json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
            }

            if (this.sD != null && !this.sD.equals("")) {
                json.put(FormsTable.COLUMN_SD, new JSONObject(this.sD));
            }

            if (this.sF != null && !this.sF.equals("")) {
                json.put(FormsTable.COLUMN_SF, new JSONObject(this.sF));
            }

            if (this.sL != null && !this.sL.equals("")) {
                json.put(FormsTable.COLUMN_SL, new JSONObject(this.sL));
            }

            json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
            json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
            json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
            json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
            json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sInfoHydrate(String string) {


        try {
            JSONObject json = null;
            json = new JSONObject(string);

            this.a05a = json.getString("a05a");
            this.a05b = json.getString("a05b");
            //this.a05code = json.getString("a05code");
//            this.a06 = json.getString("a06");
            this.username = json.getString("username");
            this.a07 = json.getString("a07");
            //this.a08 = json.getString("a08");
            this.a09 = json.getString("a09");
            this.a10 = json.getString("a10");
            this.a11 = json.getString("a11");
            this.a12 = json.getString("a12");
            this.a13dd = json.getString("a13dd");
            this.a13mm = json.getString("a13mm");
            this.a13yy = json.getString("a13yy");
            this.a14mm = json.getString("a14mm");
            this.a14yy = json.getString("a14yy");
            this.a15 = json.getString("a15");
            this.a16 = json.getString("a16");
            this.a17 = json.getString("a17");
            this.a18 = json.getString("a18");
            this.a19 = json.getString("a19");
            this.a20 = json.getString("a20");
            this.a21 = json.getString("a21");
            this.a22pos = json.getString("a22pos");
            this.a22org = json.getString("a22org");
            this.a23 = json.getString("a23");

        } catch (JSONException e) {
            e.printStackTrace();
            this.a05a = "ERROR: " + e.getMessage();
        }
    }

    private void sBHydrate(String string) {

        if (string != null) {
            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.b01 = json.getString("b01");
                this.b02 = json.getString("b02");
                this.b03 = json.getString("b03");
                this.b04 = json.getString("b04");
                this.b05 = json.getString("b05");
                this.b06 = json.getString("b06");
                this.b06ax = json.getString("b06ax");
                this.b07 = json.getString("b07");
                this.b0801 = json.getString("b0801");
                this.b0802 = json.getString("b0802");
                this.b0803 = json.getString("b0803");
                this.b0804 = json.getString("b0804");
                this.b0805 = json.getString("b0805");
                this.b0806 = json.getString("b0806");
                this.b0807 = json.getString("b0807");
                this.b9 = json.getString("b9");
                this.b10 = json.getString("b10");
                this.b11 = json.getString("b11");
                //            this.b11096 = json.getString("b11096");
                this.b11096x = json.getString("b11096x");
                this.b11mc = json.getString("b11mc");
                //            this.b11ws = json.getString("b11ws");
                this.b12 = json.getString("b12");
                this.b1301 = json.getString("b1301");
                this.b1302 = json.getString("b1302");
                this.b1303 = json.getString("b1303");
                this.b1304 = json.getString("b1304");
                this.b1305 = json.getString("b1305");
                this.b1306 = json.getString("b1306");
                this.b1307 = json.getString("b1307");
                this.b1308 = json.getString("b1308");
                this.b1309 = json.getString("b1309");
                this.b13096 = json.getString("b13096");
                this.b14 = json.getString("b14");
                this.b1501 = json.getString("b1501");
                this.b1502 = json.getString("b1502");
                this.b1503 = json.getString("b1503");
                this.b1504 = json.getString("b1504");
                this.b1505 = json.getString("b1505");
                this.b15096 = json.getString("b15096");
                this.b16 = json.getString("b16");
                this.b17 = json.getString("b17");

            } catch (JSONException e) {
                e.printStackTrace();
                this.b01 = "ERROR: " + e.getMessage();
            }
        }
    }

    private void sCHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.c01 = json.getString("c01");
                this.c0201 = json.getString("c0201");
                this.c0202 = json.getString("c0202");
                this.c03 = json.getString("c03");
                this.c04 = json.getString("c04");
                this.c05 = json.getString("c05");
                this.c06 = json.getString("c06");
                this.c07 = json.getString("c07");
                this.c08 = json.getString("c08");

            } catch (JSONException e) {
                e.printStackTrace();
                this.c01 = "ERROR: " + e.getMessage();
            }
        }
    }

    private void sDHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.d01 = json.getString("d01");
                this.d02 = json.getString("d02");
                this.d03 = json.getString("d03");
                this.d04 = json.getString("d04");
                this.d05 = json.getString("d05");

            } catch (JSONException e) {
                e.printStackTrace();
                this.d01 = "ERROR: " + e.getMessage();
            }
        }
    }

    private void sFHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.sf101 = json.getString("sf101");
                this.sf102 = json.getString("sf102");
                this.sf103 = json.getString("sf103");
                this.sf104 = json.getString("sf104");
                this.sf105 = json.getString("sf105");
                this.sf2 = json.getString("sf2");
                this.sf3 = json.getString("sf3 ");
                this.sf4 = json.getString("sf4 ");
                this.sf5 = json.getString("sf5 ");
                this.sf6 = json.getString("sf6 ");
                this.sf701 = json.getString("sf701");
                this.sf702 = json.getString("sf702");
                this.sf8 = json.getString("sf8");
                this.sf9 = json.getString("sf9");
                this.sf10 = json.getString("sf10");
                this.sf11 = json.getString("sf11");
                this.sf12 = json.getString("sf12");
                this.sf1296x = json.getString("sf1296x");
                this.sf1301 = json.getString("sf1301");
                this.sf1302 = json.getString("sf1302");
                this.sf1303 = json.getString("sf1303");
                this.sf1304 = json.getString("sf1304");
                this.sf1305 = json.getString("sf1305");
                this.sf1306 = json.getString("sf1306");
                this.sf1307 = json.getString("sf1307");
                this.sf1308 = json.getString("sf1308");
                this.sf1309 = json.getString("sf1309");
                this.sf1396x = json.getString("sf1396x");
                this.sf14 = json.getString("sf14");
                this.sf1403x = json.getString("sf1403x");
                this.sf1501 = json.getString("sf1501");
                this.sf1502 = json.getString("sf1502");
                this.sf1503 = json.getString("sf1503");
                this.sf1504 = json.getString("sf1504");
                this.sf1505 = json.getString("sf1505");
                this.sf1506 = json.getString("sf1506");
                this.sf1507 = json.getString("sf1507");
                this.sf1508 = json.getString("sf1508");
                this.sf1509 = json.getString("sf1509");
                this.sf16 = json.getString("sf16");
                this.sf17 = json.getString("sf17");
                this.sf18 = json.getString("sf18");
                this.sf1901 = json.getString("sf1901");
                this.sf1902 = json.getString("sf1902");
                this.sf20 = json.getString("sf20");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void sLHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                this.sl2 = json.getString("sl2");
                this.sl301 = json.getString("sl301");
                this.sl302 = json.getString("sl302");
                this.sl303 = json.getString("sl303");
                this.sl4 = json.getString("sl4");
                this.sl5 = json.getString("sl5");
                this.sl601 = json.getString("sl601");
                this.sl602 = json.getString("sl602");
                this.sl701 = json.getString("sl701");
                this.sl702 = json.getString("sl702");
                this.sl703 = json.getString("sl703");
                this.sl8 = json.getString("sl8");
                this.sl9 = json.getString("sl9");
                this.sl10 = json.getString("sl10");
                this.sl11 = json.getString("sl11");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
