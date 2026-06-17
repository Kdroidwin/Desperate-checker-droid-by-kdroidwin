package com.sauzask.hissi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* JADX INFO: loaded from: classes.dex */
public class HissiActivity extends Activity {
    public static final int MENU_SELECT_A = 0;
    public static final int MENU_SELECT_B = 2;
    public static final int MENU_SELECT_D = 1;
    private ArrayAdapter<String> adapterless;
    private ArrayAdapter<String> adaptername;
    private ArrayAdapter<String> adapterthread;
    private List<String> copylist;

    /* JADX INFO: renamed from: dm */
    private DisplayMetrics f0dm;
    private Float fonts;
    private String[] lessinfo;
    private String[] lesslist;
    private String[] lessname;
    private String[] lessnumber;
    private String[] lessurl;
    private ListView lessview;
    private Typeface mona;
    private SimpleAdapter myAdapterless;
    private SimpleAdapter myAdaptername;
    private SimpleAdapter myAdapterthread;
    private String[] namelist;
    private ListView nameview;
    private String[] threadlist;
    private String[] threadurl;
    private ListView threadview;
    private ArrayList<HashMap<String, Object>> outputArraythread = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> outputArrayless = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> outputArrayname = new ArrayList<>();
    private HashMap<Integer, Float> fontsize = new HashMap<>();
    private ProgressDialog dlg = null;
    private ProgressDialog dlg1 = null;
    private String meta = "";
    private String rank = "";
    private String lesssu = "";
    private String userid = "";
    private String threadlistmoto = "";
    private String namelistmoto = "";
    private String searchid = "";
    private int error = 0;
    private int page = 0;
    private int lessnum = 0;
    private int listviewp = 0;
    private int listviewpY = 0;
    private int ivw = 0;
    private Boolean loadnow = false;
    private Handler handler = new HandlerC00001();
    private Handler lessaddafter = new HandlerC00052();
    private Handler handler2 = new Handler() { // from class: com.sauzask.hissi.HissiActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (HissiActivity.this.error != 1) {
                if (HissiActivity.this.error != 2) {
                    if (HissiActivity.this.error != 3) {
                        if (HissiActivity.this.error == 4) {
                            Toast.makeText(HissiActivity.this, "クリップボードに全てのレスの内容をコピーしました", 0).show();
                            HissiActivity.this.dlg1.dismiss();
                            return;
                        } else {
                            Toast.makeText(HissiActivity.this, "不明なエラーです", 0).show();
                            HissiActivity.this.finish();
                            return;
                        }
                    }
                    Toast.makeText(HissiActivity.this, "レス情報の取得に失敗しました", 0).show();
                    if (HissiActivity.this.loadnow.booleanValue()) {
                        HissiActivity.this.dlg.dismiss();
                        return;
                    }
                    return;
                }
                Toast.makeText(HissiActivity.this, "必死チェッカーにデータがありません", 0).show();
                HissiActivity.this.finish();
                return;
            }
            Toast.makeText(HissiActivity.this, "IDの検索に失敗しました", 0).show();
            HissiActivity.this.finish();
        }
    };
    private View mFooter = null;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        ThemeHelper.apply(this);
        super.onCreate(savedInstanceState);
        setContentView(C0018R.layout.main);
        this.mona = Typeface.createFromAsset(getAssets(), "MonaLite.ttf");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.fonts = Float.valueOf(14.0f * dm.scaledDensity);
        this.copylist = new ArrayList();
        ((Button) findViewById(C0018R.id.lessb)).setOnClickListener(new View.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                HissiActivity.this.selecttab(0);
            }
        });
        ((Button) findViewById(C0018R.id.thre)).setOnClickListener(new View.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                HissiActivity.this.selecttab(1);
            }
        });
        ((Button) findViewById(C0018R.id.name)).setOnClickListener(new View.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                HissiActivity.this.selecttab(2);
            }
        });
        selecttab(0);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        this.ivw = display.getWidth();
        this.myAdapterthread = new SimpleAdapter(this, this.outputArraythread, C0018R.layout.list_layout_thread, new String[]{"list"}, new int[]{C0018R.id.kashi});
        this.myAdapterless = new SimpleAdapter(this, this.outputArrayless, C0018R.layout.list_layout_less, new String[]{"list", "name", "info", "url", "nusi"}, new int[]{C0018R.id.less, C0018R.id.textView1, C0018R.id.textView2, C0018R.id.textView3, C0018R.id.textView4}) { // from class: com.sauzask.hissi.HissiActivity.7
            @Override // android.widget.SimpleAdapter, android.widget.Adapter
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Float a = (Float) HissiActivity.this.fontsize.get(Integer.valueOf(position));
                if (a != null) {
                    ((TextView) view.findViewById(C0018R.id.less)).getPaint().setSubpixelText(false);
                    ((TextView) view.findViewById(C0018R.id.less)).setTextSize(a.floatValue());
                    ((TextView) view.findViewById(C0018R.id.less)).setTypeface(HissiActivity.this.mona);
                } else {
                    ((TextView) view.findViewById(C0018R.id.less)).getPaint().setSubpixelText(true);
                    ((TextView) view.findViewById(C0018R.id.less)).setTextSize(0, HissiActivity.this.fonts.floatValue());
                    ((TextView) view.findViewById(C0018R.id.less)).setTypeface(Typeface.create(Typeface.DEFAULT, 0));
                }
                return view;
            }
        };
        this.myAdaptername = new SimpleAdapter(this, this.outputArrayname, C0018R.layout.list_layout_thread, new String[]{"list"}, new int[]{C0018R.id.kashi});
        this.lessview = (ListView) findViewById(C0018R.id.listView2);
        this.lessview.setFastScrollEnabled(true);
        Intent intent = getIntent();
        this.searchid = intent.getDataString();
        if (this.searchid == null) {
            Bundle extras = intent.getExtras();
            CharSequence ext = extras.getCharSequence("android.intent.extra.TEXT");
            this.searchid = (String) ext;
            if (this.searchid == null) {
                this.searchid = intent.getStringExtra("data");
            }
        }
        this.dlg = new ProgressDialog(this);
        this.dlg.setMessage("取得中");
        this.dlg.setCancelable(false);
        this.dlg.setProgressStyle(0);
        this.dlg.show();
        new Thread(new Runnable() { // from class: com.sauzask.hissi.HissiActivity.8
            @Override // java.lang.Runnable
            public void run() {
                HissiActivity.this.search(HissiActivity.this.searchid);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search(String r) {
        String date;
        try {
            if (!Pattern.compile("^http://.+?\\.html$", 32).matcher(r).find()) {
                Pattern pattern = Pattern.compile("read.(php|cgi)/(.+?)/", 32);
                Matcher matcher = pattern.matcher(r);
                Pattern pattern1 = Pattern.compile("\n(.+?)\\s([0-9]+?)/([0-9]+?)/([0-9]+?)\\((.+?)ID\\:(.+?)(\n|\\s)", 32);
                Matcher matcher1 = pattern1.matcher(r);
                String surid = "";
                if (matcher.find()) {
                    surid = matcher.group(2);
                }
                if (matcher1.find()) {
                    this.userid = matcher1.group(6);
                    date = String.valueOf(matcher1.group(2)) + matcher1.group(3) + matcher1.group(4);
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    date = simpleDateFormat.format((Date) new java.sql.Date(System.currentTimeMillis()));
                }
                HttpClient httpclient = new DefaultHttpClient();
                httpclient.getParams().setParameter("http.protocol.expect-continue", false);
                HttpPost httppost = new HttpPost("http://hissi.org/read.php/" + surid + "/search/");
                List<NameValuePair> nameValuePair = new ArrayList<>(1);
                nameValuePair.add(new BasicNameValuePair("date", date));
                nameValuePair.add(new BasicNameValuePair("ID", this.userid));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                HttpResponse response = httpclient.execute(httppost);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                response.getEntity().writeTo(byteArrayOutputStream);
                this.meta = byteArrayOutputStream.toString();
                this.meta = "http://hissi.org/read.php/" + surid + this.meta.replaceAll("(.+?)url\\=\\.\\.(.+?)\"(.+?)", "$2") + "?thread=all&name=all";
            } else {
                this.meta = String.valueOf(r) + "?thread=all&name=all";
            }
            httprequest(this.meta);
        } catch (Exception e) {
            this.error = 1;
            this.handler2.sendEmptyMessage(0);
        }
    }

    private void httprequest(String r) {
        HttpClient c = new DefaultHttpClient();
        HttpGet g = new HttpGet(r);
        byte[] result = (byte[]) null;
        try {
            HttpResponse response = c.execute(g);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                result = EntityUtils.toByteArray(response.getEntity());
            }
            this.rank = "";
            this.lesssu = "";
            String moto = new String(result, "SJIS").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"");
            Pattern pattern = Pattern.compile("<font size=5 color=#FF6347>(.+?)</td><td width=130 bgcolor=#80FFFF>(.+?)<td width=40 bgcolor=(.+?)>(.+?)</td></tr></table></td></tr></table>", 32);
            Matcher matcher = pattern.matcher(moto);
            if (matcher.find()) {
                this.rank = matcher.group(1);
                this.lesssu = matcher.group(4);
                this.lessnum = Integer.parseInt(matcher.group(4));
            }
            Pattern pattern1 = Pattern.compile("書き込んだスレッド一覧</th></tr><tr bgcolor=#E5FFFF><td>(.+?)<br></td><td>(.+?)書き込みレス一覧(.+?)</table></td></tr></table>", 32);
            Matcher matcher1 = pattern1.matcher(moto);
            if (matcher1.find()) {
                this.namelistmoto = matcher1.group(1);
                this.threadlistmoto = matcher1.group(2);
            }
            Pattern pattern3 = Pattern.compile("<a href=[^>]+>([^<]+)</a></h3><h4>書き込み順位＆時間帯一覧</h4>", 32);
            Matcher matcher3 = pattern3.matcher(moto);
            if (matcher3.find()) {
                this.userid = matcher3.group(1);
            }
            this.rank = this.rank.replaceAll("<(.+?)>", "");
            this.rank = this.rank.replaceAll("\\/", " / ");
            this.threadlist = this.threadlistmoto.split("a>");
            this.threadurl = this.threadlistmoto.split("a>");
            this.namelist = this.namelistmoto.split("<br>");
            for (int i = 0; i < this.threadlist.length - 1; i++) {
                Pattern pattern2 = Pattern.compile("href=(.+?)\\s(.+?)>(.+?)</", 32);
                Matcher matcher2 = pattern2.matcher(this.threadlist[i]);
                while (matcher2.find()) {
                    this.threadlist[i] = matcher2.group(3);
                    this.threadurl[i] = matcher2.group(1).replaceAll("l50$", "");
                }
            }
            this.threadlistmoto = this.threadlistmoto.replaceAll("<br(\\/|\\s\\/|)>", "\n");
            lessadd(this.meta);
            this.handler.sendEmptyMessage(0);
        } catch (Exception e) {
            this.error = 2;
            this.handler2.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: renamed from: com.sauzask.hissi.HissiActivity$1 */
    class HandlerC00001 extends Handler {
        HandlerC00001() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            HissiActivity.this.setTitle("ID:" + HissiActivity.this.userid + " - 必死ﾁｪｯｶｰdroid");
            ((TextView) HissiActivity.this.findViewById(C0018R.id.textView1)).setText(HissiActivity.this.rank);
            ((TextView) HissiActivity.this.findViewById(C0018R.id.textView2)).setText("レス:" + HissiActivity.this.lesssu);
            ((Button) HissiActivity.this.findViewById(C0018R.id.lessb)).setText("書込んだレス\n(" + HissiActivity.this.lesssu + ")");
            ((Button) HissiActivity.this.findViewById(C0018R.id.thre)).setText("書込んだスレ\n(" + (HissiActivity.this.threadlist.length - 1) + ")");
            ((Button) HissiActivity.this.findViewById(C0018R.id.name)).setText("使用した名前\n(" + HissiActivity.this.namelist.length + ")");
            for (int i = 0; i < HissiActivity.this.threadlist.length - 1; i++) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("list", HissiActivity.this.threadlist[i]);
                HissiActivity.this.outputArraythread.add(item);
            }
            HissiActivity.this.threadview = (ListView) HissiActivity.this.findViewById(C0018R.id.listView1);
            HissiActivity.this.threadview.setAdapter((ListAdapter) HissiActivity.this.myAdapterthread);
            HissiActivity.this.threadview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sauzask.hissi.HissiActivity.1.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    String[] str_items = {"リンク", "アプリを終了しリンク", "コピー", "追加コピー", "閉じる"};
                    new AlertDialog.Builder(HissiActivity.this).setItems(str_items, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.1.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Uri uri = Uri.parse(HissiActivity.this.threadurl[position]);
                                Intent i2 = new Intent("android.intent.action.VIEW", uri);
                                HissiActivity.this.startActivity(i2);
                                return;
                            }
                            if (which == 1) {
                                Uri uri2 = Uri.parse(HissiActivity.this.threadurl[position]);
                                Intent i3 = new Intent("android.intent.action.VIEW", uri2);
                                HissiActivity.this.startActivity(i3);
                                HissiActivity.this.finish();
                                return;
                            }
                            if (which == 2) {
                                ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(HissiActivity.this.threadlist[position] + "\n" + HissiActivity.this.threadurl[position] + "\n\n");
                                Toast.makeText(HissiActivity.this, "クリップボードに内容をコピーしました", 0).show();
                            } else if (which == 3) {
                                ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(((Object) ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).getText()) + HissiActivity.this.threadlist[position] + "\n" + HissiActivity.this.threadurl[position] + "\n\n");
                                Toast.makeText(HissiActivity.this, "クリップボードに内容を追加コピーしました", 0).show();
                            } else if (which == 4) {
                                dialog.dismiss();
                            }
                        }
                    }).show();
                }
            });
            for (int i2 = 0; i2 < HissiActivity.this.namelist.length; i2++) {
                HashMap<String, Object> item2 = new HashMap<>();
                item2.put("list", HissiActivity.this.namelist[i2]);
                HissiActivity.this.outputArrayname.add(item2);
            }
            HissiActivity.this.nameview = (ListView) HissiActivity.this.findViewById(C0018R.id.listView3);
            HissiActivity.this.nameview.setAdapter((ListAdapter) HissiActivity.this.myAdaptername);
            HissiActivity.this.nameview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sauzask.hissi.HissiActivity.1.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    String[] str_items = {"コピー", "追加コピー", "閉じる"};
                    new AlertDialog.Builder(HissiActivity.this).setItems(str_items, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.1.2.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(HissiActivity.this.namelist[position]);
                                Toast.makeText(HissiActivity.this, "クリップボードに内容をコピーしました", 0).show();
                            } else if (which == 1) {
                                ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(((Object) ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).getText()) + HissiActivity.this.namelist[position] + "\n");
                                Toast.makeText(HissiActivity.this, "クリップボードに内容を追加コピーしました", 0).show();
                            } else if (which == 2) {
                                dialog.dismiss();
                            }
                        }
                    }).show();
                }
            });
            HissiActivity.this.dlg.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lessadd(String r) {
        HttpClient c = new DefaultHttpClient();
        HttpGet g = new HttpGet(r);
        byte[] result = (byte[]) null;
        try {
            HttpResponse response = c.execute(g);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                result = EntityUtils.toByteArray(response.getEntity());
            }
            String moto = new String(result, "SJIS");
            String lesslistmoto = "";
            Pattern pattern1 = Pattern.compile("書き込んだスレッド一覧(.+?)<br></td><td>(.+?)書き込みレス一覧(.+?)</table></td></tr></table>", 32);
            Matcher matcher1 = pattern1.matcher(moto);
            if (matcher1.find()) {
                lesslistmoto = matcher1.group(3);
            }
            this.lesslist = lesslistmoto.split("<br></dl> </td></tr> <tr");
            this.lessurl = lesslistmoto.split("<br></dl> </td></tr> <tr");
            this.lessname = lesslistmoto.split("<br></dl> </td></tr> <tr");
            this.lessinfo = lesslistmoto.split("<br></dl> </td></tr> <tr");
            this.lessnumber = lesslistmoto.split("<br></dl> </td></tr> <tr");
            for (int i = 0; i < this.lesslist.length; i++) {
                Pattern pattern2 = Pattern.compile("<a href=http(.+?) target=\"_blank\">(.+?)</a><br>([0-9]+?) ：<b>(.+?)<dd>(.+?)$", 32);
                Matcher matcher2 = pattern2.matcher(this.lesslist[i]);
                while (matcher2.find()) {
                    String mai = String.valueOf(Html.fromHtml(matcher2.group(5)));
                    this.lesslist[i] = mai;
                    Boolean.valueOf(false);
                    int mail = mai.length();
                    int numb = mai.replaceAll("[一-龠ぁ-んァ-ヴa-zA-Z0-9\n]+", "").length();
                    int s = (int) ((numb / mail) * 100.0f);
                    if (s > 75) {
                        String[] list = mai.split("\n");
                        float max = 0.0f;
                        for (String a : list) {
                            float twobite = a.getBytes().length - a.length();
                            float onebite = ((float) (((double) a.getBytes().length) * 1.2d)) - (2.0f * twobite);
                            float sa = twobite + (onebite / 2.0f);
                            if (max < sa) {
                                max = sa;
                            }
                        }
                        float siz = this.ivw / max;
                        if (siz > this.fonts.floatValue()) {
                            siz = this.fonts.floatValue();
                        }
                        this.fontsize.put(Integer.valueOf(this.outputArrayless.size() + i), Float.valueOf(siz));
                    }
                    this.lessurl[i] = "http" + matcher2.group(1).replaceAll("<(.+?)>|\n$", "");
                    this.lessname[i] = String.valueOf(Html.fromHtml(matcher2.group(2)));
                    this.lessinfo[i] = String.valueOf(matcher2.group(3)) + "：" + matcher2.group(4).replaceAll("<(.+?)>|\n$", "");
                    this.lessnumber[i] = matcher2.group(3);
                }
            }
            this.lessaddafter.sendEmptyMessage(0);
        } catch (Exception e) {
            this.error = 3;
            this.handler2.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: renamed from: com.sauzask.hissi.HissiActivity$2 */
    class HandlerC00052 extends Handler {
        HandlerC00052() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (HissiActivity.this.loadnow.booleanValue()) {
                HissiActivity.this.listviewp = HissiActivity.this.lessview.getFirstVisiblePosition();
                HissiActivity.this.listviewpY = HissiActivity.this.lessview.getChildAt(0).getTop();
            }
            for (int i = 0; i < HissiActivity.this.lesslist.length; i++) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("list", HissiActivity.this.lesslist[i]);
                item.put("name", HissiActivity.this.lessname[i]);
                item.put("info", HissiActivity.this.lessinfo[i]);
                if (Integer.parseInt(HissiActivity.this.lessnumber[i]) == 1) {
                    item.put("nusi", "[ >>1 ] ");
                }
                item.put("url", HissiActivity.this.lessurl[i]);
                HissiActivity.this.outputArrayless.add(item);
                HissiActivity.this.copylist.add(String.valueOf(HissiActivity.this.lessname[i]) + "\n" + HissiActivity.this.lessinfo[i] + "\n" + HissiActivity.this.lesslist[i] + "\n\n");
            }
            if (!HissiActivity.this.loadnow.booleanValue() && ((double) HissiActivity.this.lessnum) / 50.0d > ((double) HissiActivity.this.page) + 1.0d) {
                HissiActivity.this.lessview.addFooterView(HissiActivity.this.getFooter());
            }
            HissiActivity.this.lessview.setAdapter((ListAdapter) HissiActivity.this.myAdapterless);
            HissiActivity.this.lessview.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sauzask.hissi.HissiActivity.2.1
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (totalItemCount == firstVisibleItem + visibleItemCount && !HissiActivity.this.loadnow.booleanValue() && ((double) HissiActivity.this.lessnum) / 50.0d > ((double) HissiActivity.this.page) + 1.0d) {
                        HissiActivity.this.page++;
                        HissiActivity.this.loadnow = true;
                        new Thread(new Runnable() { // from class: com.sauzask.hissi.HissiActivity.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                HissiActivity.this.lessadd(String.valueOf(HissiActivity.this.meta) + "&p=" + HissiActivity.this.page);
                            }
                        }).start();
                    }
                }
            });
            HissiActivity.this.lessview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sauzask.hissi.HissiActivity.2.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    if (view != HissiActivity.this.mFooter) {
                        String[] str_items = {"リンク", "アプリを終了しリンク", "コピー", "追加コピー", "閉じる"};
                        new AlertDialog.Builder(HissiActivity.this).setItems(str_items, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.2.2.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Uri uri = Uri.parse((String) ((TextView) view.findViewById(C0018R.id.textView3)).getText());
                                    Intent i2 = new Intent("android.intent.action.VIEW", uri);
                                    HissiActivity.this.startActivity(i2);
                                    return;
                                }
                                if (which == 1) {
                                    Uri uri2 = Uri.parse((String) ((TextView) view.findViewById(C0018R.id.textView3)).getText());
                                    Intent i3 = new Intent("android.intent.action.VIEW", uri2);
                                    HissiActivity.this.startActivity(i3);
                                    HissiActivity.this.finish();
                                    return;
                                }
                                if (which == 2) {
                                    ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(((String) ((TextView) view.findViewById(C0018R.id.textView1)).getText()) + "\n" + ((String) ((TextView) view.findViewById(C0018R.id.textView2)).getText()) + "\n" + ((String) ((TextView) view.findViewById(C0018R.id.less)).getText()) + "\n\n");
                                    Toast.makeText(HissiActivity.this, "クリップボードに内容をコピーしました", 0).show();
                                } else if (which == 3) {
                                    ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(((Object) ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).getText()) + ((String) ((TextView) view.findViewById(C0018R.id.textView1)).getText()) + "\n" + ((String) ((TextView) view.findViewById(C0018R.id.textView2)).getText()) + "\n" + ((String) ((TextView) view.findViewById(C0018R.id.less)).getText()) + "\n\n");
                                    Toast.makeText(HissiActivity.this, "クリップボードに内容を追加コピーしました", 0).show();
                                } else if (which == 4) {
                                    dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                }
            });
            if (HissiActivity.this.loadnow.booleanValue()) {
                HissiActivity.this.loadnow = false;
                HissiActivity.this.lessview.setSelectionFromTop(HissiActivity.this.listviewp, HissiActivity.this.listviewpY);
                if (((double) HissiActivity.this.lessnum) / 50.0d > ((double) HissiActivity.this.page) + 1.0d) {
                    return;
                }
                HissiActivity.this.lessview.removeFooterView(HissiActivity.this.getFooter());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selecttab(int nm) {
        if (nm == 0) {
            ((Button) findViewById(C0018R.id.lessb)).setEnabled(false);
            ((Button) findViewById(C0018R.id.lessb)).setBackgroundResource(C0018R.drawable.bottombutton2);
            ((Button) findViewById(C0018R.id.thre)).setEnabled(true);
            ((Button) findViewById(C0018R.id.thre)).setBackgroundResource(C0018R.layout.button_selector);
            ((Button) findViewById(C0018R.id.name)).setEnabled(true);
            ((Button) findViewById(C0018R.id.name)).setBackgroundResource(C0018R.layout.button_selector);
            ((ListView) findViewById(C0018R.id.listView2)).setVisibility(0);
            ((ListView) findViewById(C0018R.id.listView1)).setVisibility(8);
            ((ListView) findViewById(C0018R.id.listView3)).setVisibility(8);
            return;
        }
        if (nm == 1) {
            ((Button) findViewById(C0018R.id.lessb)).setEnabled(true);
            ((Button) findViewById(C0018R.id.lessb)).setBackgroundResource(C0018R.layout.button_selector);
            ((Button) findViewById(C0018R.id.thre)).setEnabled(false);
            ((Button) findViewById(C0018R.id.thre)).setBackgroundResource(C0018R.drawable.bottombutton2);
            ((Button) findViewById(C0018R.id.name)).setEnabled(true);
            ((Button) findViewById(C0018R.id.name)).setBackgroundResource(C0018R.layout.button_selector);
            ((ListView) findViewById(C0018R.id.listView1)).setVisibility(0);
            ((ListView) findViewById(C0018R.id.listView1)).setAdapter((ListAdapter) this.myAdapterthread);
            this.myAdapterthread.notifyDataSetChanged();
            ((ListView) findViewById(C0018R.id.listView2)).setVisibility(8);
            ((ListView) findViewById(C0018R.id.listView3)).setVisibility(8);
            return;
        }
        if (nm == 2) {
            ((Button) findViewById(C0018R.id.lessb)).setEnabled(true);
            ((Button) findViewById(C0018R.id.lessb)).setBackgroundResource(C0018R.layout.button_selector);
            ((Button) findViewById(C0018R.id.thre)).setEnabled(true);
            ((Button) findViewById(C0018R.id.thre)).setBackgroundResource(C0018R.layout.button_selector);
            ((Button) findViewById(C0018R.id.name)).setEnabled(false);
            ((Button) findViewById(C0018R.id.name)).setBackgroundResource(C0018R.drawable.bottombutton2);
            ((ListView) findViewById(C0018R.id.listView3)).setVisibility(0);
            ((ListView) findViewById(C0018R.id.listView3)).setAdapter((ListAdapter) this.myAdaptername);
            this.myAdaptername.notifyDataSetChanged();
            ((ListView) findViewById(C0018R.id.listView1)).setVisibility(8);
            ((ListView) findViewById(C0018R.id.listView2)).setVisibility(8);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "全てのレスをコピー").setIcon(C0018R.drawable.copy);
        menu.add(0, 0, 0, "必死ﾁｪｯｶｰへのリンクをコピー").setIcon(C0018R.drawable.copy);
        menu.add(0, 2, 0, "クリップボード確認").setIcon(C0018R.drawable.ic_menu_attachment);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SELECT_A /* 0 */:
                ((ClipboardManager) getSystemService("clipboard")).setText(this.meta.replaceAll("\\?thread\\=all\\&name\\=all", ""));
                Toast.makeText(this, "クリップボードにURLをコピーしました", 0).show();
                return true;
            case 1:
                if (this.lessview.getCount() < this.lessnum) {
                    new AlertDialog.Builder(this).setMessage("レスが全て表示されていません\n全てのレスをコピーする場合全て表示させてください\n現在表示されているレスのみコピーしますか？").setPositiveButton("はい", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.9
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int whichButton) {
                            HissiActivity.this.allcopy();
                        }
                    }).setNegativeButton("いいえ", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.10
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    }).show();
                } else {
                    new AlertDialog.Builder(this).setMessage("全てのレスをコピーしますか？").setPositiveButton("はい", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.11
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int whichButton) {
                            HissiActivity.this.allcopy();
                        }
                    }).setNegativeButton("いいえ", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiActivity.12
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    }).show();
                }
                return true;
            case MENU_SELECT_B /* 2 */:
                TextView text = new TextView(this);
                text.setText(((ClipboardManager) getSystemService("clipboard")).getText());
                text.setTextSize(12.0f);
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(1);
                layout.addView(text);
                LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(-1, -1);
                l.leftMargin = 10;
                l.rightMargin = 10;
                text.setLayoutParams(l);
                ScrollView scrollView = new ScrollView(this);
                scrollView.addView(layout);
                new AlertDialog.Builder(this).setView(scrollView).show();
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getFooter() {
        if (this.mFooter == null) {
            this.mFooter = getLayoutInflater().inflate(C0018R.layout.list_layout_footer, (ViewGroup) null);
        }
        return this.mFooter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void allcopy() {
        ((ClipboardManager) getSystemService("clipboard")).setText("");
        this.dlg1 = new ProgressDialog(this);
        this.dlg1.setTitle("コピー中");
        this.dlg1.setProgressStyle(1);
        this.dlg1.setMax(this.lessview.getCount());
        this.dlg1.setCancelable(false);
        this.dlg1.show();
        new Thread(new Runnable() { // from class: com.sauzask.hissi.HissiActivity.13
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                String aa = "";
                for (String s : HissiActivity.this.copylist) {
                    i++;
                    aa = String.valueOf(aa) + s;
                    HissiActivity.this.dlg1.setProgress(i);
                }
                ((ClipboardManager) HissiActivity.this.getSystemService("clipboard")).setText(aa);
                HissiActivity.this.error = 4;
                HissiActivity.this.handler2.sendEmptyMessage(0);
            }
        }).start();
    }
}
