package com.sauzask.hissi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class HissiTop extends Activity {
    public static final int MENU_SELECT_D = 1;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    private String[] threadlist;
    private String[] threadurl;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        ThemeHelper.apply(this);
        super.onCreate(savedInstanceState);
        setContentView(C0018R.layout.top);
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.editor = this.pref.edit();
        this.threadlist = "namazuplus/>地震速報,eq/>臨時地震,eqplus/>臨時地震+,lifeline/>緊急自然災害,be/>面白ネタnews,nandemo/>なんでも質問,argue/>朝生,bizplus/>ビジネスnews+,newsplus/>ニュース速報+,lifeline/>緊急自然災害,wildplus/>ニュース二軍+,moeplus/>萌えニュース+,mnewsplus/>芸スポ速報+,femnewsplus/>ほのぼのnews+,scienceplus/>科学ニュース+,liveplus/>ニュース実況+,news/>ニュース速報,trafficinfo/>交通情報,musicnews/>芸能音楽速報,gamenews/>ゲーム速報,news2/>ニュース議論,asia/>ニュース極東,editorial/>社説,wikileaks/>WikiLeaks,kokusai/>国際情勢,war/>戦争・国防,news4plus/>東アジアnews+,africa/>アフリカ情勢,europa/>欧州・CIS情勢,news5plus/>ニュース国際+,dejima/>dejima,entrance2/>ﾗｳﾝｼﾞｸﾗｼｯｸ,qa/>初心者の質問,gline/>ガイドライン,event/>イベント企画,2chse/>2ch証券取引所,dataroom/>資料室,vote/>投票所,operate/>2ch運用情報,operatex/>運用情報臨時,sec2chd/>2ch規制議論,sakukb/>削除知恵袋,intro/>自己紹介,offmatrix/>大規模OFF,offreg/>定期OFF,offevent/>突発OFF,aasaloon/>AAサロン,mona/>モナー,nida/>ニダー,aastory/>AA長編,kao/>顔文字,mass/>マスコミ,youth/>少年犯罪,119/>消防救急防災,gender/>男性論女性論,giin/>議員・選挙,manifesto/>政治家語録,court/>裁判・司法,saibanin/>裁判員制度,river/>河川・ダム等,traf/>運輸・交通,way/>道路・高速道路,develop/>都市計画,job/>転職,volunteer/>ボランティア,welfare/>介護・福祉,mayor/>地方自治知事,ftax/>ふるさと納税,nenga/>郵便・郵政,regulate/>通信行政,venture/>ベンチャー,management/>店舗運営,shikaku/>法律勉強相談,haken/>派遣業界,tax/>税金経理会計,hosp/>病院・医者,industry/>製造業界,peko/>食品業界・問題,company/>ちくり裏事情,bouhan/>防犯・詐欺対策,antispam/>架空請求・spam,expo/>万博・地方博,subcal/>サブカル,mitemite/>創作発表,poem/>詩・ポエム,rongo/>名言・格言,movie/>映画一般・8mm,cinema/>映画作品・人,rmovie/>懐かし邦画,kinema/>懐かし洋画,occult/>オカルト,esp/>超能力,sfx/>特撮！,rsfx/>昭和特撮,drama/>演劇・舞台役者,siki/>宝塚・四季,fortune/>占い,uranai/>占術理論実践,ruins/>世界遺産,emperor/>皇室・王侯貴族,kikai/>機械・工学,denki/>電気・電子,robot/>ロボット技術,informatics/>情報学,sky/>天文・気象,galileo/>宇宙,kampo/>東洋医学,future/>未来技術,dialect/>方言,pedagogy/>教育学,economics/>経済学,poetics/>詩文学,history2/>日本近代史,archeology/>考古学,kobun/>古文・漢文,usa/>アメリカ,korea/>ハングル,geo/>地理・人類学,chiri/>地理お国自慢,jurisp/>法学,wm/>ポータブルAV,vcamera/>ビデオカメラ,bakery/>調理家電,toilet/>シャワートイレ,apple2/>Apple,phs/>携帯・ＰＨＳ,keitai/>携帯機種,iPhone/>iPhone,smartphone/>スマートフォン,chakumelo/>携帯コンテンツ,appli/>携帯電話ゲー,dgoods/>デジタルモノ,camera/>カメラ,dcamera/>デジカメ,av/>AV機器,pav/>ピュアAU,battery/>電池等,seiji/>政治,diplomacy/>外交政策,trafficpolicy/>交通政策,stock/>株式,stockb/>株個別銘柄,market/>投資一般,livemarket1/>市況1,livemarket2/>市況2,deal/>先物,koumei/>創価・公明,kova/>ゴーマニズム,cook/>料理,okome/>米・米加工品,yasai/>野菜・果物,kinoko/>きのこ,salt/>調味料,ramen/>ラーメン,nissin/>インスタント麺,jnoodle/>そば・うどん,sushi/>おすし,don/>丼,bread/>パン,pasta/>パスタ・ピザ,kbbq/>焼肉,konamono/>たこ焼き等,toba/>珍味,famires/>ファミレス,jfoods/>Ｂ級グルメ,bento/>弁当・駅弁,wine/>ワイン,drunk/>居酒屋,patissier/>製菓・製パン,kankon/>生活全般,homealone/>一人暮らし,countrylife/>田舎暮らし,debt/>借金生活,inpatient/>入院生活,sportsclub/>スポーツクラブ,bath/>お風呂・銭湯,baby/>育児,hcenter/>家電等量販店,used/>中古リサイクル,rental/>レンタル,trend/>流行,ticketplus/>Walker+,underwear/>下着,shoes/>靴,female/>化粧,mensbeauty/>男の美容・化粧,aroma/>香水芳香消臭,seikei/>美容整形,shapeup/>ダイエット,world/>一般海外生活,northa/>北米海外生活,credit/>クレジット,point/>ポイント・マイル,cafe30/>３０代,cafe50/>５０代以上,cafe60/>６０歳以上,souji/>掃除全般,radiation/>放射能,mascot/>マスコットキャラ,senji/>戦時,ex/>カップル,gaysaloon/>同性愛サロン,dame/>無職・だめ,loser/>負け組,mental/>メンヘルサロン,sfe/>独身女性限定,wmotenai/>もてない女,ms/>既婚女性,alone/>孤独な男性,campus/>大学生活,575/>しりとり,hidari/>左利き,livesaturn/>なんでも実況S,livevenus/>なんでも実況V,livejupiter/>なんでも実況J,liveuranus/>なんでも実況U,endless/>実況ch,weekly/>番組ch,livewkwest/>番組ch(西日本）,livenhk/>番組ch(NHK）,liveetv/>番組ch(教育）,liventv/>番組ch(NTV）,livetbs/>番組ch(TBS）,livecx/>番組ch(フジ）,liveanb/>番組ch(朝日）,livetx/>番組ch(TX）,livebs/>BS実況(NHK）,livewowow/>BS有料民放実況,livebs2/>BS無料民放実況,liveskyp/>スカパー実況,liveradio/>ラジオ実況,liveanime/>アニメ特撮実況,kokkai/>議会選挙実況,dome/>スポーツch,livebase/>野球ch,livefoot/>サッカーch,oonna/>五輪実況(女）,ootoko/>五輪実況(男）,dancesite/>芸能ch,festival/>お祭りch,liveplus/>ニュース実況+,livemarket1/>市況1,livemarket2/>市況2,edu/>教育・先生,jsaloon/>大学受験サロン,kouri/>大学受験,juku/>学習塾・予備校,ojyuken/>お受験,musicology/>音楽系学校,govexam/>公務員試験,magic/>手品・曲芸,card/>トランプ,puzzle/>パズル,toy/>おもちゃ,smoking/>煙草銘柄・器具,knife/>刃物,engei/>園芸,dog/>犬猫大好き,pet/>ペット大好き,aquarium/>アクアリウム,goldenfish/>日本の淡水魚,insect/>昆虫・節足動物,cat/>生き物苦手,bike/>バイク,car/>車,kcar/>軽自動車,auto/>車種・メーカー,usedcar/>中古車,truck/>大型・特殊車両,train/>鉄道総合,rail/>鉄道路線・車両,jnr/>鉄道懐かし,ice/>鉄道(海外）,gage/>鉄道模型,bus/>バス・バス路線,airline/>エアライン,mokei/>模型・プラモ,radiocontrol/>RC（ラジコン）,gun/>サバゲー,fireworks/>花火,chinahero/>中国英雄,sengoku/>戦国時代,photo/>写真撮影,sposaloon/>スポーツサロン,sports/>スポーツ,rsports/>懐かしスポーツ,stadium/>スポーツ施設,gymnastics/>体操・新体操,muscle/>ウエイトトレ,noroma/>運動音痴,wsports/>冬スポーツ,skate/>スケート,swim/>水泳,boat/>船スポーツ,equestrian/>乗馬・馬術,f1/>ﾓｰﾀｰｽﾎﾟｰﾂ,bullseye/>的スポーツ,parksports/>公園スポーツ,amespo/>アメスポ,cheerleading/>チア,base/>プロ野球,npb/>球界改革議論,meikyu/>野球殿堂,mlb/>野球総合,hsb/>高校野球,soccer/>国内サッカー,eleven/>日本代表蹴球,wc/>ワールドカップ,football/>海外サッカー,tennis/>テニス,volley/>バレーボール,ovalball/>ラグビー,pingpong/>卓球,gutter/>ボウリング,billiards/>ビリヤード,ballgame/>その他球技,k1/>格闘技,wres/>プロレス,budou/>武道・武芸,jyudo/>伝統武術,oversea/>海外旅行,21oversea/>危ない海外,travel/>国内旅行,hotel/>ホテル･旅館,localfoods/>土産物・特産物,tropical/>トロピカル,onsen/>温泉,park/>遊園地,zoo/>動物園・水族館,museum/>博物館・美術館,tv/>テレビ番組,tvd/>テレビドラマ,nhkdrama/>大河ドラマ,natsudora/>懐かしドラマ,am/>ラジオ番組,rradio/>懐かしラジオ,hanryu/>韓流,nhk/>NHK,geino/>芸能,4sama/>アジアエンタメ,kyon2/>懐かし芸能人,actor/>男性俳優,actress/>女優,geinoj/>U-15タレント,ana/>アナウンサー,ainotane/>モ娘（羊）,mendol/>男性アイドル,idol/>女性アイドル,akb/>地下アイドル,ske/>ローカルアイドル,jan/>ジャニーズ,smap/>スマップ,jr/>ジャニーズ２,pachi/>パチンコサロン,pachij/>パチンコ店情報,pachik/>パチンコ機種等,slot/>スロットサロン,slotj/>スロット店情報,slotk/>スロット機種,keiba/>競馬,uma/>競馬２,keirin/>競輪,kyotei/>競艇,autorace/>オートレース,gamble/>ギャンブル,loto/>宝くじ,gsaloon/>ゲームサロン,gamenews/>ゲーム速報,gameover/>家ゲー攻略,goveract/>家ゲACT攻略,goverrpg/>家ゲRPG攻略,gamerpg/>家ゲーRPG,ff/>FF・ドラクエ,gamesrpg/>家ゲーSRPG,gamerobo/>ロボットゲー,gal/>ギャルゲー,gamespo/>スポーツ・RACE,gamehis/>歴史ゲーム,otoge/>音ゲー,gamefight/>格闘ゲーム,gamestg/>シューティング,gamef/>PCアクション,famicom/>家庭用ゲーム,zgame/>家ゲーZ区分,retro2/>家ゲーレトロ,game90/>レトロ32bit以上,arc/>アーケード,rarc/>アケゲーレトロ,amusement/>メダル・プライズ,gecen/>ゲーセン,game/>PCゲーム,gameama/>同人ゲーム,gameswf/>ブラウザゲーム,tcg/>TCG,bgame/>将棋・チェス,gamestones/>囲碁・オセロ,ghard/>ハード・業界,gameurawaza/>裏技・改造,gamechara/>ゲームキャラ,gamemusic/>ゲーム音楽,handygame/>携帯ゲーソフト,handygover/>携帯ゲー攻略,handygrpg/>携帯ゲーRPG,wifi/>Wi-Fi,rhandyg/>携帯ゲーレトロ,pokechara/>携帯ゲーキャラ,mmonews/>ネトゲ速報,mmoqa/>ネトゲ質問,ogame/>ネトゲ実況,ogame2/>ネトゲ実況2,ogame3/>ネトゲ実況3,netgame/>ネットゲーム,mmo/>大規模MMO,mmominor/>小規模MMO,anime4vip/>アニメサロンex,anime/>アニメ,anime2/>アニメ２,anime3/>アニメ新作情報,animovie/>アニメ映画,anichara/>アニキャラ総合,anichara2/>アニキャラ個別,cosp/>コスプレ,voice/>声優総合,voiceactor/>声優個人,doujin/>同人,comic/>漫画,ymag/>少年漫画,wcomic/>週刊少年漫画,cchara/>漫画キャラ,sakura/>CCさくら,cartoon/>海外アニメ漫画,bookall/>文芸書籍サロン,magazin/>ライトノベル,mystery/>ミステリー,litechara/>文芸キャラ,ebooks/>電子書籍,juvenile/>児童書,illustrator/>イラストレーター,musicnews/>芸能音楽速報,musicj/>邦楽,musicjm/>邦楽男性ソロ,musicjf/>邦楽女性ソロ,musicjg/>邦楽グループ,enka/>演歌,musice/>洋楽,natsumeloe/>懐メロ洋楽,visual/>ヴィジュサロン,visualb/>ヴィジュバンド,disco/>ディスコ,randb/>R&B・SOUL,hrhm/>HR・HM,progre/>プログレ,healmusic/>ヒーリング音楽,reggae/>レゲエ,fusion/>フュージョン,classical/>クラシック,contemporary/>現代音楽,nika/>エレクトロニカ,suisou/>吹奏楽,chorus/>合唱,doyo/>童謡・唱歌,soundtrack/>サントラ,karaok/>カラオケ,band/>バンド,compose/>楽器・作曲,piano/>鍵盤楽器,healing/>癒し,jinsei/>人生相談,psy/>心と宗教,body/>身体・健康,stretch/>マッサージ等,handicap/>ハンディキャップ,cancer/>癌・腫瘍,nanbyou/>難病,infection/>新型感染症,hiv/>HIVサロン,atopi/>アトピー,allergy/>アレルギー,hage/>ハゲ・ズラ,pure/>純情恋愛,gay/>同性愛,utu/>メンタルヘルス,win/>Windows,mac/>新・mac,desktop/>デスクトップ,notepc/>ノートPC,jisaku/>自作PC,printer/>プリンタ,hard/>ハードウェア,cdr/>CD-R,DVD,software/>ソフトウェア,linux/>Linux,cg/>ＣＧ,dtm/>DTM,avi/>DTV,swf/>FLASH,gamedev/>ゲ製作技術,internet/>インターネット,download/>Download,affiliate/>Web収入,ipv6/>IPv6,isp/>プロバイダー,netspot/>ネットカフェ,google/>Google,streaming/>YouTube,mstreaming/>携帯動画,mdis/>音楽配信,netradio/>ネットラジオ等,blog/>ブログ,sns/>ソーシャルネット,net/>ネットwatch,yahoo/>オークション,lobby/>ロビー,tubo/>最悪,joke/>学歴,rights/>人権問題,accuse/>2ch批判要望,ranking/>格付け,record/>新記録・珍記録,news4vip/>ニュー速VIP,news4viptasu/>ニュー速VIP+,heaven4vip/>天国,neet4vip/>ニー速,aniki/>ガチホモ,sureh/>スレH・エロ会話,erolive/>大人の実況,hneta/>えっちねた,pinkcafe/>PINKのおいらロ,erobbs/>pink秘密基地,housekeeping/>PINK削除依頼,ccc/>PINK規制議論,hgame/>エロゲー,hgame2/>エロゲー作品別,erog/>エロゲネタ,leaf/>Leaf・key,adultsite/>アダルトサイト,avideo/>AV総合,avideo2/>AV女優,erocomic/>エロ漫画小説,erodoujin/>エロ同人,natuero/>懐かしエロ,eroacademy/>PINKな学問,kageki/>過激な恋愛,kageki2/>大人の恋愛,adultgoods/>アダルトグッズ,sm/>ＳＭ,feti/>フェチ,okama/>おかま・おなべ,gaypink/>大人の同性愛,lesbian/>レズ・百合萌え,eroaa/>エロAA,erochara2/>オリキャラ,801/>８０１,erocg/>お絵描き・創作,eroparo/>エロパロ,ascii/>半角文字列,ascii2d/>半角二次元,ascii2kana/>半角かな,girls/>アイドル画像,club/>風俗全般,pub/>お水出会い系,host/>ホストクラブ,nuki/>へるす,soap/>ソープ,neet4pink/>ニー速(pink）,megami/>女神".split(",");
        this.threadurl = "namazuplus/>地震速報,eq/>臨時地震,eqplus/>臨時地震+,lifeline/>緊急自然災害,be/>面白ネタnews,nandemo/>なんでも質問,argue/>朝生,bizplus/>ビジネスnews+,newsplus/>ニュース速報+,lifeline/>緊急自然災害,wildplus/>ニュース二軍+,moeplus/>萌えニュース+,mnewsplus/>芸スポ速報+,femnewsplus/>ほのぼのnews+,scienceplus/>科学ニュース+,liveplus/>ニュース実況+,news/>ニュース速報,trafficinfo/>交通情報,musicnews/>芸能音楽速報,gamenews/>ゲーム速報,news2/>ニュース議論,asia/>ニュース極東,editorial/>社説,wikileaks/>WikiLeaks,kokusai/>国際情勢,war/>戦争・国防,news4plus/>東アジアnews+,africa/>アフリカ情勢,europa/>欧州・CIS情勢,news5plus/>ニュース国際+,dejima/>dejima,entrance2/>ﾗｳﾝｼﾞｸﾗｼｯｸ,qa/>初心者の質問,gline/>ガイドライン,event/>イベント企画,2chse/>2ch証券取引所,dataroom/>資料室,vote/>投票所,operate/>2ch運用情報,operatex/>運用情報臨時,sec2chd/>2ch規制議論,sakukb/>削除知恵袋,intro/>自己紹介,offmatrix/>大規模OFF,offreg/>定期OFF,offevent/>突発OFF,aasaloon/>AAサロン,mona/>モナー,nida/>ニダー,aastory/>AA長編,kao/>顔文字,mass/>マスコミ,youth/>少年犯罪,119/>消防救急防災,gender/>男性論女性論,giin/>議員・選挙,manifesto/>政治家語録,court/>裁判・司法,saibanin/>裁判員制度,river/>河川・ダム等,traf/>運輸・交通,way/>道路・高速道路,develop/>都市計画,job/>転職,volunteer/>ボランティア,welfare/>介護・福祉,mayor/>地方自治知事,ftax/>ふるさと納税,nenga/>郵便・郵政,regulate/>通信行政,venture/>ベンチャー,management/>店舗運営,shikaku/>法律勉強相談,haken/>派遣業界,tax/>税金経理会計,hosp/>病院・医者,industry/>製造業界,peko/>食品業界・問題,company/>ちくり裏事情,bouhan/>防犯・詐欺対策,antispam/>架空請求・spam,expo/>万博・地方博,subcal/>サブカル,mitemite/>創作発表,poem/>詩・ポエム,rongo/>名言・格言,movie/>映画一般・8mm,cinema/>映画作品・人,rmovie/>懐かし邦画,kinema/>懐かし洋画,occult/>オカルト,esp/>超能力,sfx/>特撮！,rsfx/>昭和特撮,drama/>演劇・舞台役者,siki/>宝塚・四季,fortune/>占い,uranai/>占術理論実践,ruins/>世界遺産,emperor/>皇室・王侯貴族,kikai/>機械・工学,denki/>電気・電子,robot/>ロボット技術,informatics/>情報学,sky/>天文・気象,galileo/>宇宙,kampo/>東洋医学,future/>未来技術,dialect/>方言,pedagogy/>教育学,economics/>経済学,poetics/>詩文学,history2/>日本近代史,archeology/>考古学,kobun/>古文・漢文,usa/>アメリカ,korea/>ハングル,geo/>地理・人類学,chiri/>地理お国自慢,jurisp/>法学,wm/>ポータブルAV,vcamera/>ビデオカメラ,bakery/>調理家電,toilet/>シャワートイレ,apple2/>Apple,phs/>携帯・ＰＨＳ,keitai/>携帯機種,iPhone/>iPhone,smartphone/>スマートフォン,chakumelo/>携帯コンテンツ,appli/>携帯電話ゲー,dgoods/>デジタルモノ,camera/>カメラ,dcamera/>デジカメ,av/>AV機器,pav/>ピュアAU,battery/>電池等,seiji/>政治,diplomacy/>外交政策,trafficpolicy/>交通政策,stock/>株式,stockb/>株個別銘柄,market/>投資一般,livemarket1/>市況1,livemarket2/>市況2,deal/>先物,koumei/>創価・公明,kova/>ゴーマニズム,cook/>料理,okome/>米・米加工品,yasai/>野菜・果物,kinoko/>きのこ,salt/>調味料,ramen/>ラーメン,nissin/>インスタント麺,jnoodle/>そば・うどん,sushi/>おすし,don/>丼,bread/>パン,pasta/>パスタ・ピザ,kbbq/>焼肉,konamono/>たこ焼き等,toba/>珍味,famires/>ファミレス,jfoods/>Ｂ級グルメ,bento/>弁当・駅弁,wine/>ワイン,drunk/>居酒屋,patissier/>製菓・製パン,kankon/>生活全般,homealone/>一人暮らし,countrylife/>田舎暮らし,debt/>借金生活,inpatient/>入院生活,sportsclub/>スポーツクラブ,bath/>お風呂・銭湯,baby/>育児,hcenter/>家電等量販店,used/>中古リサイクル,rental/>レンタル,trend/>流行,ticketplus/>Walker+,underwear/>下着,shoes/>靴,female/>化粧,mensbeauty/>男の美容・化粧,aroma/>香水芳香消臭,seikei/>美容整形,shapeup/>ダイエット,world/>一般海外生活,northa/>北米海外生活,credit/>クレジット,point/>ポイント・マイル,cafe30/>３０代,cafe50/>５０代以上,cafe60/>６０歳以上,souji/>掃除全般,radiation/>放射能,mascot/>マスコットキャラ,senji/>戦時,ex/>カップル,gaysaloon/>同性愛サロン,dame/>無職・だめ,loser/>負け組,mental/>メンヘルサロン,sfe/>独身女性限定,wmotenai/>もてない女,ms/>既婚女性,alone/>孤独な男性,campus/>大学生活,575/>しりとり,hidari/>左利き,livesaturn/>なんでも実況S,livevenus/>なんでも実況V,livejupiter/>なんでも実況J,liveuranus/>なんでも実況U,endless/>実況ch,weekly/>番組ch,livewkwest/>番組ch(西日本）,livenhk/>番組ch(NHK）,liveetv/>番組ch(教育）,liventv/>番組ch(NTV）,livetbs/>番組ch(TBS）,livecx/>番組ch(フジ）,liveanb/>番組ch(朝日）,livetx/>番組ch(TX）,livebs/>BS実況(NHK）,livewowow/>BS有料民放実況,livebs2/>BS無料民放実況,liveskyp/>スカパー実況,liveradio/>ラジオ実況,liveanime/>アニメ特撮実況,kokkai/>議会選挙実況,dome/>スポーツch,livebase/>野球ch,livefoot/>サッカーch,oonna/>五輪実況(女）,ootoko/>五輪実況(男）,dancesite/>芸能ch,festival/>お祭りch,liveplus/>ニュース実況+,livemarket1/>市況1,livemarket2/>市況2,edu/>教育・先生,jsaloon/>大学受験サロン,kouri/>大学受験,juku/>学習塾・予備校,ojyuken/>お受験,musicology/>音楽系学校,govexam/>公務員試験,magic/>手品・曲芸,card/>トランプ,puzzle/>パズル,toy/>おもちゃ,smoking/>煙草銘柄・器具,knife/>刃物,engei/>園芸,dog/>犬猫大好き,pet/>ペット大好き,aquarium/>アクアリウム,goldenfish/>日本の淡水魚,insect/>昆虫・節足動物,cat/>生き物苦手,bike/>バイク,car/>車,kcar/>軽自動車,auto/>車種・メーカー,usedcar/>中古車,truck/>大型・特殊車両,train/>鉄道総合,rail/>鉄道路線・車両,jnr/>鉄道懐かし,ice/>鉄道(海外）,gage/>鉄道模型,bus/>バス・バス路線,airline/>エアライン,mokei/>模型・プラモ,radiocontrol/>RC（ラジコン）,gun/>サバゲー,fireworks/>花火,chinahero/>中国英雄,sengoku/>戦国時代,photo/>写真撮影,sposaloon/>スポーツサロン,sports/>スポーツ,rsports/>懐かしスポーツ,stadium/>スポーツ施設,gymnastics/>体操・新体操,muscle/>ウエイトトレ,noroma/>運動音痴,wsports/>冬スポーツ,skate/>スケート,swim/>水泳,boat/>船スポーツ,equestrian/>乗馬・馬術,f1/>ﾓｰﾀｰｽﾎﾟｰﾂ,bullseye/>的スポーツ,parksports/>公園スポーツ,amespo/>アメスポ,cheerleading/>チア,base/>プロ野球,npb/>球界改革議論,meikyu/>野球殿堂,mlb/>野球総合,hsb/>高校野球,soccer/>国内サッカー,eleven/>日本代表蹴球,wc/>ワールドカップ,football/>海外サッカー,tennis/>テニス,volley/>バレーボール,ovalball/>ラグビー,pingpong/>卓球,gutter/>ボウリング,billiards/>ビリヤード,ballgame/>その他球技,k1/>格闘技,wres/>プロレス,budou/>武道・武芸,jyudo/>伝統武術,oversea/>海外旅行,21oversea/>危ない海外,travel/>国内旅行,hotel/>ホテル･旅館,localfoods/>土産物・特産物,tropical/>トロピカル,onsen/>温泉,park/>遊園地,zoo/>動物園・水族館,museum/>博物館・美術館,tv/>テレビ番組,tvd/>テレビドラマ,nhkdrama/>大河ドラマ,natsudora/>懐かしドラマ,am/>ラジオ番組,rradio/>懐かしラジオ,hanryu/>韓流,nhk/>NHK,geino/>芸能,4sama/>アジアエンタメ,kyon2/>懐かし芸能人,actor/>男性俳優,actress/>女優,geinoj/>U-15タレント,ana/>アナウンサー,ainotane/>モ娘（羊）,mendol/>男性アイドル,idol/>女性アイドル,akb/>地下アイドル,ske/>ローカルアイドル,jan/>ジャニーズ,smap/>スマップ,jr/>ジャニーズ２,pachi/>パチンコサロン,pachij/>パチンコ店情報,pachik/>パチンコ機種等,slot/>スロットサロン,slotj/>スロット店情報,slotk/>スロット機種,keiba/>競馬,uma/>競馬２,keirin/>競輪,kyotei/>競艇,autorace/>オートレース,gamble/>ギャンブル,loto/>宝くじ,gsaloon/>ゲームサロン,gamenews/>ゲーム速報,gameover/>家ゲー攻略,goveract/>家ゲACT攻略,goverrpg/>家ゲRPG攻略,gamerpg/>家ゲーRPG,ff/>FF・ドラクエ,gamesrpg/>家ゲーSRPG,gamerobo/>ロボットゲー,gal/>ギャルゲー,gamespo/>スポーツ・RACE,gamehis/>歴史ゲーム,otoge/>音ゲー,gamefight/>格闘ゲーム,gamestg/>シューティング,gamef/>PCアクション,famicom/>家庭用ゲーム,zgame/>家ゲーZ区分,retro2/>家ゲーレトロ,game90/>レトロ32bit以上,arc/>アーケード,rarc/>アケゲーレトロ,amusement/>メダル・プライズ,gecen/>ゲーセン,game/>PCゲーム,gameama/>同人ゲーム,gameswf/>ブラウザゲーム,tcg/>TCG,bgame/>将棋・チェス,gamestones/>囲碁・オセロ,ghard/>ハード・業界,gameurawaza/>裏技・改造,gamechara/>ゲームキャラ,gamemusic/>ゲーム音楽,handygame/>携帯ゲーソフト,handygover/>携帯ゲー攻略,handygrpg/>携帯ゲーRPG,wifi/>Wi-Fi,rhandyg/>携帯ゲーレトロ,pokechara/>携帯ゲーキャラ,mmonews/>ネトゲ速報,mmoqa/>ネトゲ質問,ogame/>ネトゲ実況,ogame2/>ネトゲ実況2,ogame3/>ネトゲ実況3,netgame/>ネットゲーム,mmo/>大規模MMO,mmominor/>小規模MMO,anime4vip/>アニメサロンex,anime/>アニメ,anime2/>アニメ２,anime3/>アニメ新作情報,animovie/>アニメ映画,anichara/>アニキャラ総合,anichara2/>アニキャラ個別,cosp/>コスプレ,voice/>声優総合,voiceactor/>声優個人,doujin/>同人,comic/>漫画,ymag/>少年漫画,wcomic/>週刊少年漫画,cchara/>漫画キャラ,sakura/>CCさくら,cartoon/>海外アニメ漫画,bookall/>文芸書籍サロン,magazin/>ライトノベル,mystery/>ミステリー,litechara/>文芸キャラ,ebooks/>電子書籍,juvenile/>児童書,illustrator/>イラストレーター,musicnews/>芸能音楽速報,musicj/>邦楽,musicjm/>邦楽男性ソロ,musicjf/>邦楽女性ソロ,musicjg/>邦楽グループ,enka/>演歌,musice/>洋楽,natsumeloe/>懐メロ洋楽,visual/>ヴィジュサロン,visualb/>ヴィジュバンド,disco/>ディスコ,randb/>R&B・SOUL,hrhm/>HR・HM,progre/>プログレ,healmusic/>ヒーリング音楽,reggae/>レゲエ,fusion/>フュージョン,classical/>クラシック,contemporary/>現代音楽,nika/>エレクトロニカ,suisou/>吹奏楽,chorus/>合唱,doyo/>童謡・唱歌,soundtrack/>サントラ,karaok/>カラオケ,band/>バンド,compose/>楽器・作曲,piano/>鍵盤楽器,healing/>癒し,jinsei/>人生相談,psy/>心と宗教,body/>身体・健康,stretch/>マッサージ等,handicap/>ハンディキャップ,cancer/>癌・腫瘍,nanbyou/>難病,infection/>新型感染症,hiv/>HIVサロン,atopi/>アトピー,allergy/>アレルギー,hage/>ハゲ・ズラ,pure/>純情恋愛,gay/>同性愛,utu/>メンタルヘルス,win/>Windows,mac/>新・mac,desktop/>デスクトップ,notepc/>ノートPC,jisaku/>自作PC,printer/>プリンタ,hard/>ハードウェア,cdr/>CD-R,DVD,software/>ソフトウェア,linux/>Linux,cg/>ＣＧ,dtm/>DTM,avi/>DTV,swf/>FLASH,gamedev/>ゲ製作技術,internet/>インターネット,download/>Download,affiliate/>Web収入,ipv6/>IPv6,isp/>プロバイダー,netspot/>ネットカフェ,google/>Google,streaming/>YouTube,mstreaming/>携帯動画,mdis/>音楽配信,netradio/>ネットラジオ等,blog/>ブログ,sns/>ソーシャルネット,net/>ネットwatch,yahoo/>オークション,lobby/>ロビー,tubo/>最悪,joke/>学歴,rights/>人権問題,accuse/>2ch批判要望,ranking/>格付け,record/>新記録・珍記録,news4vip/>ニュー速VIP,news4viptasu/>ニュー速VIP+,heaven4vip/>天国,neet4vip/>ニー速,aniki/>ガチホモ,sureh/>スレH・エロ会話,erolive/>大人の実況,hneta/>えっちねた,pinkcafe/>PINKのおいらロ,erobbs/>pink秘密基地,housekeeping/>PINK削除依頼,ccc/>PINK規制議論,hgame/>エロゲー,hgame2/>エロゲー作品別,erog/>エロゲネタ,leaf/>Leaf・key,adultsite/>アダルトサイト,avideo/>AV総合,avideo2/>AV女優,erocomic/>エロ漫画小説,erodoujin/>エロ同人,natuero/>懐かしエロ,eroacademy/>PINKな学問,kageki/>過激な恋愛,kageki2/>大人の恋愛,adultgoods/>アダルトグッズ,sm/>ＳＭ,feti/>フェチ,okama/>おかま・おなべ,gaypink/>大人の同性愛,lesbian/>レズ・百合萌え,eroaa/>エロAA,erochara2/>オリキャラ,801/>８０１,erocg/>お絵描き・創作,eroparo/>エロパロ,ascii/>半角文字列,ascii2d/>半角二次元,ascii2kana/>半角かな,girls/>アイドル画像,club/>風俗全般,pub/>お水出会い系,host/>ホストクラブ,nuki/>へるす,soap/>ソープ,neet4pink/>ニー速(pink）,megami/>女神".split(",");
        for (int i = 0; i < this.threadlist.length; i++) {
            Pattern pattern2 = Pattern.compile("^(.+?)/>(.+?)$", 32);
            Matcher matcher2 = pattern2.matcher(this.threadlist[i]);
            while (matcher2.find()) {
                this.threadlist[i] = matcher2.group(2);
                this.threadurl[i] = matcher2.group(1);
            }
        }
        ((EditText) findViewById(C0018R.id.editText1)).setText(this.pref.getString("TH", ""));
        ((EditText) findViewById(C0018R.id.editText2)).setText(this.pref.getString("ID", ""));
        ((TextView) findViewById(C0018R.id.textView1)).setText("検索板(例：news4vip、ghard)");
        ((TextView) findViewById(C0018R.id.textView2)).setText("検索対象日時");
        ((TextView) findViewById(C0018R.id.textView3)).setText("検索ID");
        ((Button) findViewById(C0018R.id.button1)).setOnClickListener(new View.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.1
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                DatePicker datePicker1 = (DatePicker) HissiTop.this.findViewById(C0018R.id.datePicker1);
                int year = datePicker1.getYear();
                int month = datePicker1.getMonth();
                int day = datePicker1.getDayOfMonth();
                String data = "read.cgi/" + ((EditText) HissiTop.this.findViewById(C0018R.id.editText1)).getText().toString() + "/\n  " + String.format("%d/%2$02d/%3$02d", Integer.valueOf(year), Integer.valueOf(month + 1), Integer.valueOf(day)) + "() ID:" + ((EditText) HissiTop.this.findViewById(C0018R.id.editText2)).getText().toString().replaceAll("^(ID|id|Id|iD):", "") + "\n";
                Intent intent2 = new Intent(HissiTop.this, (Class<?>) HissiActivity.class);
                intent2.putExtra("data", data);
                HissiTop.this.startActivity(intent2);
                HissiTop.this.editor.putString("ID", ((EditText) HissiTop.this.findViewById(C0018R.id.editText2)).getText().toString());
                HissiTop.this.editor.putString("TH", ((EditText) HissiTop.this.findViewById(C0018R.id.editText1)).getText().toString());
                HissiTop.this.editor.commit();
            }
        });
        ((Button) findViewById(C0018R.id.button3)).setOnClickListener(new View.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.2
            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                String[] listname = HissiTop.this.pref.getString("Favorite", "").split("\n");
                final String[] listurl = HissiTop.this.pref.getString("Favorite", "").split("\n");
                for (int i2 = 0; i2 < listurl.length; i2++) {
                    Pattern pattern22 = Pattern.compile("^(.+?)\\,(.+?)$", 32);
                    Matcher matcher22 = pattern22.matcher(listurl[i2]);
                    while (matcher22.find()) {
                        listname[i2] = matcher22.group(2);
                        listurl[i2] = matcher22.group(1);
                    }
                }
                if (listname[0] != "") {
                    new AlertDialog.Builder(HissiTop.this).setItems(listname, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.2.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            ((EditText) HissiTop.this.findViewById(C0018R.id.editText1)).setText(listurl[which]);
                        }
                    }).show();
                } else {
                    Toast.makeText(HissiTop.this, "お気に入りがありません", 0).show();
                }
            }
        });
        ((Button) findViewById(C0018R.id.button2)).setOnClickListener(new ViewOnClickListenerC00153());
    }

    /* JADX INFO: renamed from: com.sauzask.hissi.HissiTop$3 */
    class ViewOnClickListenerC00153 implements View.OnClickListener {
        ViewOnClickListenerC00153() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            new AlertDialog.Builder(HissiTop.this).setItems(HissiTop.this.threadlist, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.3.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, final int which) {
                    String[] str_items = {"選択", "お気に入りに登録"};
                    new AlertDialog.Builder(HissiTop.this).setTitle(HissiTop.this.threadlist[which]).setItems(str_items, new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.3.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog2, int which1) {
                            if (which1 == 0) {
                                ((EditText) HissiTop.this.findViewById(C0018R.id.editText1)).setText(HissiTop.this.threadurl[which]);
                            } else if (which1 == 1) {
                                HissiTop.this.editor.putString("Favorite", String.valueOf(HissiTop.this.threadurl[which]) + "," + HissiTop.this.threadlist[which] + "\n" + HissiTop.this.pref.getString("Favorite", ""));
                                HissiTop.this.editor.commit();
                                Toast.makeText(HissiTop.this, String.valueOf(HissiTop.this.threadlist[which]) + "\nをお気に入りに登録しました", 0).show();
                            }
                        }
                    }).show();
                }
            }).show();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "お気に入りの初期化");
        menu.add(0, 2, 1, "Theme");
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                new AlertDialog.Builder(this).setTitle("確認").setMessage("お気に入りがすべて削除されます\nよろしいですか？").setPositiveButton("はい", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int whichButton) {
                        HissiTop.this.editor.putString("Favorite", "");
                        HissiTop.this.editor.commit();
                        Toast.makeText(HissiTop.this, "お気に入りを初期化しました", 0).show();
                    }
                }).setNegativeButton("いいえ", new DialogInterface.OnClickListener() { // from class: com.sauzask.hissi.HissiTop.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
                return true;
            case 2:
                ThemeHelper.showThemeDialog(this);
                return true;
            default:
                return false;
        }
    }
}
