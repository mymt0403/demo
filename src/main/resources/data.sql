INSERT INTO prefecture (number, prefecture_name, default_latitude, default_longitude) VALUES
  (40, '福岡県', 33.549371780410496, 130.6919457831582),
  (41, '佐賀県', 33.28573301243255, 130.17833494873358),
  (42, '長崎県', 32.95218070016451, 129.87895750903715),
  (43, '熊本県', 32.63818323138118, 130.67271970792515),
  (44, '大分県', 33.15246412812109, 131.50768063783255),
  (45, '宮崎県', 32.25342332692016, 131.33739255247465),
  (46, '鹿児島県', 31.661528437683334, 130.65074704982047),
  (47, '沖縄県', 26.518107423323457, 127.9729267663165);

INSERT INTO garbage_type (type_id, type_name) VALUES
  (1, '可燃物'),
  (2, '不燃物'),
  (3, '可燃・不燃物');

INSERT INTO bulky_garbage_facilities (
  latitude, longitude, prefecture_no, facility_name, address, map_url, garbage_type_id
) VALUES
  (33.64388661895616, 130.48743279315005, 40, '東部工場', '福岡県福岡市東区蒲田5丁目11-2', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (33.63770692226995, 130.41921675329377, 40, '臨海工場', '福岡県福岡市東区箱崎ふ頭4丁目13-42', 'https://maps.app.goo.gl/qMVv1Y5hdUuWGDu66', 1),
  (33.50736402044899, 130.45890032464226, 40, 'クリーン・エネ・パーク南部', '福岡県春日市大字下白水104-5', 'https://maps.app.goo.gl/BsvCDeAb84d2rxub6', 1),
  (33.64544275253747, 130.4875796532938, 40, '東部資源化センター', '福岡県福岡市東区蒲田5丁目11-1', 'https://maps.app.goo.gl/4cx5X8DrpqQXXmM37', 2),
  (33.660271380165085, 130.47664301771164, 40, '東部（伏谷）埋立場', '福岡県糟屋郡久山町大字山田1431-1', 'https://maps.app.goo.gl/e2vgdCso4ZHGmzRM7', 2),
  (33.618247604736986, 130.22959808887595, 40, '西部（中田）埋立場', '福岡県福岡市西区大字今津4439', 'https://maps.app.goo.gl/GKRTxBpiZ2c9AwWc6', 2),
  (33.56316973571027, 130.2955613133586, 40, '西部工場', '福岡県福岡市西区大字拾六町1191', 'https://maps.app.goo.gl/g7BEzsVdLHXXnfmL9', 3),
  (33.856077044396415, 130.99393809666745, 40, '北九州市環境局新門司工場', '福岡県北九州市門司区新門司3丁目79', 'https://maps.app.goo.gl/h3fAqfGmZbHr5f3B9', 1),
  (33.912844365875394, 130.8704305678336, 40, '北九州市環境局日明工場', '福岡県北九州市小倉北区西港町96−2', 'https://maps.app.goo.gl/UgVTMWBqoAE54VAN7', 1),
  (33.912410495495195, 130.87051140893482, 40, '日明工場不燃粗大仮置場', '福岡県北九州市小倉北区西港町96−2', 'https://maps.app.goo.gl/68DhDqWXWx9z9r9DA', 2),
  (33.87122284913156, 130.7428238966681, 40, '北九州市環境局皇后崎工場', '福岡県北九州市八幡西区夕原町2−1', 'https://maps.app.goo.gl/6CwE9uXMRcjcuYuy9', 1),
  (33.94188656421211, 130.7676053120747, 40, '北九州市響灘西地区廃棄物処分場', '福岡県北九州市若松区響町3丁目', 'https://maps.app.goo.gl/mvyrzCou4YDFMbCSA', 2),
  (31.310577, 130.857811, 46, '鹿児島施設1', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (31.421219, 130.143112, 46, '鹿児島施設2', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (31.555377, 130.239634, 46, '鹿児島施設3', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (31.123102, 130.262788, 46, '鹿児島施設4', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (32.499602, 131.856627, 45, '宮崎施設1', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (31.725448, 131.535201, 45, '宮崎施設2', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (32.348430, 131.017515, 45, '宮崎施設3', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (33.174217, 131.044714, 44, '大分施設1', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (33.346065, 131.078543, 44, '大分施設2', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1),
  (32.419470, 130.329591, 43, '熊本施設1', '福岡県福岡市XXX区XXX1丁目1-1', 'https://maps.app.goo.gl/XWEnx8WRLu9L6GnQ9', 1);
