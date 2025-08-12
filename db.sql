-- alter table country modify column phoneCode varchar (5000) not null;

insert into `country` (id, name, countryCode, countryCode3, phoneCode, region, subRegion, flag, currency, language,
                       active)
values (1, 'Togo', 'TG', 'TGO', '{root=+2, suffixes=[28]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/tg.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (2, 'Mayotte', 'YT', 'MYT', '{root=+2, suffixes=[62]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/yt.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (3, 'Georgia', 'GE', 'GEO', '{root=+9, suffixes=[95]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/ge.png', '{GEL={name=lari, symbol=₾}}', '{kat=Georgian}', 1),
       (4, 'Vanuatu', 'VU', 'VUT', '{root=+6, suffixes=[78]}', 'Oceania', 'Melanesia',
        'https://flagcdn.com/w320/vu.png', '{VUV={name=Vanuatu vatu, symbol=Vt}}',
        '{bis=Bislama, eng=English, fra=French}', 1),
       (5, 'Kyrgyzstan', 'KG', 'KGZ', '{root=+9, suffixes=[96]}', 'Asia', 'Central Asia',
        'https://flagcdn.com/w320/kg.png', '{KGS={name=Kyrgyzstani som, symbol=с}}', '{kir=Kyrgyz, rus=Russian}', 1),
       (6, 'Niger', 'NE', 'NER', '{root=+2, suffixes=[27]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/ne.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (7, 'China', 'CN', 'CHN', '{root=+8, suffixes=[6]}', 'Asia', 'Eastern Asia', 'https://flagcdn.com/w320/cn.png',
        '{CNY={name=Chinese yuan, symbol=¥}}', '{zho=Chinese}', 1),
       (8, 'Tuvalu', 'TV', 'TUV', '{root=+6, suffixes=[88]}', 'Oceania', 'Polynesia', 'https://flagcdn.com/w320/tv.png',
        '{AUD={name=Australian dollar, symbol=$}, TVD={name=Tuvaluan dollar, symbol=$}}', '{eng=English, tvl=Tuvaluan}',
        1),
       (9, 'Comoros', 'KM', 'COM', '{root=+2, suffixes=[69]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/km.png', '{KMF={name=Comorian franc, symbol=Fr}}',
        '{ara=Arabic, fra=French, zdj=Comorian}', 1),
       (10, 'Bosnia and Herzegovina', 'BA', 'BIH', '{root=+3, suffixes=[87]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/ba.png', '{BAM={name=Bosnia and Herzegovina convertible mark, symbol=KM}}',
        '{bos=Bosnian, hrv=Croatian, srp=Serbian}', 1),
       (11, 'Bahrain', 'BH', 'BHR', '{root=+9, suffixes=[73]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/bh.png', '{BHD={name=Bahraini dinar, symbol=.د.ب}}', '{ara=Arabic}', 1),
       (12, 'Somalia', 'SO', 'SOM', '{root=+2, suffixes=[52]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/so.png', '{SOS={name=Somali shilling, symbol=Sh}}', '{ara=Arabic, som=Somali}', 1),
       (13, 'Saint Barthélemy', 'BL', 'BLM', '{root=+5, suffixes=[90]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/bl.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (14, 'Latvia', 'LV', 'LVA', '{root=+3, suffixes=[71]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/lv.png', '{EUR={name=Euro, symbol=€}}', '{lav=Latvian}', 1),
       (15, 'Cayman Islands', 'KY', 'CYM', '{root=+1, suffixes=[345]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/ky.png', '{KYD={name=Cayman Islands dollar, symbol=$}}', '{eng=English}', 1),
       (16, 'Netherlands', 'NL', 'NLD', '{root=+3, suffixes=[1]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/nl.png', '{EUR={name=Euro, symbol=€}}', '{nld=Dutch}', 1),
       (17, 'Lesotho', 'LS', 'LSO', '{root=+2, suffixes=[66]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/ls.png',
        '{LSL={name=Lesotho loti, symbol=L}, ZAR={name=South African rand, symbol=R}}', '{eng=English, sot=Sotho}', 1),
       (18, 'Venezuela', 'VE', 'VEN', '{root=+5, suffixes=[8]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/ve.png', '{VES={name=Venezuelan bolívar soberano, symbol=Bs.S.}}', '{spa=Spanish}',
        1),
       (19, 'Kenya', 'KE', 'KEN', '{root=+2, suffixes=[54]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/ke.png', '{KES={name=Kenyan shilling, symbol=Sh}}', '{eng=English, swa=Swahili}', 1),
       (20, 'Turkey', 'TR', 'TUR', '{root=+9, suffixes=[0]}', 'Asia', 'Western Asia', 'https://flagcdn.com/w320/tr.png',
        '{TRY={name=Turkish lira, symbol=₺}}', '{tur=Turkish}', 1),
       (21, 'Fiji', 'FJ', 'FJI', '{root=+6, suffixes=[79]}', 'Oceania', 'Melanesia', 'https://flagcdn.com/w320/fj.png',
        '{FJD={name=Fijian dollar, symbol=$}}', '{eng=English, fij=Fijian, hif=Fiji Hindi}', 1),
       (22, 'Trinidad and Tobago', 'TT', 'TTO', '{root=+1, suffixes=[868]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/tt.png', '{TTD={name=Trinidad and Tobago dollar, symbol=$}}', '{eng=English}', 1),
       (23, 'Honduras', 'HN', 'HND', '{root=+5, suffixes=[04]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/hn.png', '{HNL={name=Honduran lempira, symbol=L}}', '{spa=Spanish}', 1),
       (24, 'Jersey', 'JE', 'JEY', '{root=+4, suffixes=[4]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/je.png', '{GBP={name=British pound, symbol=£}, JEP={name=Jersey pound, symbol=£}}',
        '{eng=English, fra=French, nrf=Jèrriais}', 1),
       (25, 'Djibouti', 'DJ', 'DJI', '{root=+2, suffixes=[53]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/dj.png', '{DJF={name=Djiboutian franc, symbol=Fr}}', '{ara=Arabic, fra=French}', 1),
       (26, 'Réunion', 'RE', 'REU', '{root=+2, suffixes=[62]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/re.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (27, 'Eswatini', 'SZ', 'SWZ', '{root=+2, suffixes=[68]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/sz.png',
        '{SZL={name=Swazi lilangeni, symbol=L}, ZAR={name=South African rand, symbol=R}}', '{eng=English, ssw=Swazi}',
        1),
       (28, 'Tajikistan', 'TJ', 'TJK', '{root=+9, suffixes=[92]}', 'Asia', 'Central Asia',
        'https://flagcdn.com/w320/tj.png', '{TJS={name=Tajikistani somoni, symbol=ЅМ}}', '{rus=Russian, tgk=Tajik}', 1),
       (29, 'Saudi Arabia', 'SA', 'SAU', '{root=+9, suffixes=[66]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/sa.png', '{SAR={name=Saudi riyal, symbol=ر.س}}', '{ara=Arabic}', 1),
       (30, 'Bermuda', 'BM', 'BMU', '{root=+1, suffixes=[441]}', 'Americas', 'North America',
        'https://flagcdn.com/w320/bm.png', '{BMD={name=Bermudian dollar, symbol=$}}', '{eng=English}', 1),
       (31, 'New Zealand', 'NZ', 'NZL', '{root=+6, suffixes=[4]}', 'Oceania', 'Australia and New Zealand',
        'https://flagcdn.com/w320/nz.png', '{NZD={name=New Zealand dollar, symbol=$}}',
        '{eng=English, mri=Māori, nzs=New Zealand Sign Language}', 1),
       (32, 'Belarus', 'BY', 'BLR', '{root=+3, suffixes=[75]}', 'Europe', 'Eastern Europe',
        'https://flagcdn.com/w320/by.png', '{BYN={name=Belarusian ruble, symbol=Br}}', '{bel=Belarusian, rus=Russian}',
        1),
       (33, 'Christmas Island', 'CX', 'CXR', '{root=+6, suffixes=[1]}', 'Oceania', 'Australia and New Zealand',
        'https://flagcdn.com/w320/cx.png', '{AUD={name=Australian dollar, symbol=$}}', '{eng=English}', 1),
       (34, 'Gambia', 'GM', 'GMB', '{root=+2, suffixes=[20]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/gm.png', '{GMD={name=dalasi, symbol=D}}', '{eng=English}', 1),
       (35, 'French Polynesia', 'PF', 'PYF', '{root=+6, suffixes=[89]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/pf.png', '{XPF={name=CFP franc, symbol=₣}}', '{fra=French}', 1),
       (36, 'DR Congo', 'CD', 'COD', '{root=+2, suffixes=[43]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/cd.png', '{CDF={name=Congolese franc, symbol=FC}}',
        '{fra=French, kon=Kikongo, lin=Lingala, lua=Tshiluba, swa=Swahili}', 1),
       (37, 'Costa Rica', 'CR', 'CRI', '{root=+5, suffixes=[06]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/cr.png', '{CRC={name=Costa Rican colón, symbol=₡}}', '{spa=Spanish}', 1),
       (38, 'Malawi', 'MW', 'MWI', '{root=+2, suffixes=[65]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/mw.png', '{MWK={name=Malawian kwacha, symbol=MK}}', '{eng=English, nya=Chewa}', 1),
       (39, 'Republic of the Congo', 'CG', 'COG', '{root=+2, suffixes=[42]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/cg.png', '{XAF={name=Central African CFA franc, symbol=Fr}}',
        '{fra=French, kon=Kikongo, lin=Lingala}', 1),
       (40, 'Oman', 'OM', 'OMN', '{root=+9, suffixes=[68]}', 'Asia', 'Western Asia', 'https://flagcdn.com/w320/om.png',
        '{OMR={name=Omani rial, symbol=ر.ع.}}', '{ara=Arabic}', 1),
       (41, 'Iraq', 'IQ', 'IRQ', '{root=+9, suffixes=[64]}', 'Asia', 'Western Asia', 'https://flagcdn.com/w320/iq.png',
        '{IQD={name=Iraqi dinar, symbol=ع.د}}', '{ara=Arabic, arc=Aramaic, ckb=Sorani}', 1),
       (42, 'Niue', 'NU', 'NIU', '{root=+6, suffixes=[83]}', 'Oceania', 'Polynesia', 'https://flagcdn.com/w320/nu.png',
        '{NZD={name=New Zealand dollar, symbol=$}}', '{eng=English, niu=Niuean}', 1),
       (43, 'Senegal', 'SN', 'SEN', '{root=+2, suffixes=[21]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/sn.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (44, 'Lebanon', 'LB', 'LBN', '{root=+9, suffixes=[61]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/lb.png', '{LBP={name=Lebanese pound, symbol=ل.ل}}', '{ara=Arabic, fra=French}', 1),
       (45, 'Angola', 'AO', 'AGO', '{root=+2, suffixes=[44]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/ao.png', '{AOA={name=Angolan kwanza, symbol=Kz}}', '{por=Portuguese}', 1),
       (46, 'Iran', 'IR', 'IRN', '{root=+9, suffixes=[8]}', 'Asia', 'Southern Asia', 'https://flagcdn.com/w320/ir.png',
        '{IRR={name=Iranian rial, symbol=﷼}}', '{fas=Persian (Farsi)}', 1),
       (47, 'Ecuador', 'EC', 'ECU', '{root=+5, suffixes=[93]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/ec.png', '{USD={name=United States dollar, symbol=$}}', '{spa=Spanish}', 1),
       (48, 'Laos', 'LA', 'LAO', '{root=+8, suffixes=[56]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/la.png', '{LAK={name=Lao kip, symbol=₭}}', '{lao=Lao}', 1),
       (49, 'Sri Lanka', 'LK', 'LKA', '{root=+9, suffixes=[4]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/lk.png', '{LKR={name=Sri Lankan rupee, symbol=Rs  රු}}', '{sin=Sinhala, tam=Tamil}',
        1),
       (50, 'Aruba', 'AW', 'ABW', '{root=+2, suffixes=[97]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/aw.png', '{AWG={name=Aruban florin, symbol=ƒ}}', '{nld=Dutch, pap=Papiamento}', 1),
       (51, 'São Tomé and Príncipe', 'ST', 'STP', '{root=+2, suffixes=[39]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/st.png', '{STN={name=São Tomé and Príncipe dobra, symbol=Db}}', '{por=Portuguese}',
        1),
       (52, 'Grenada', 'GD', 'GRD', '{root=+1, suffixes=[473]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/gd.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (53, 'Montserrat', 'MS', 'MSR', '{root=+1, suffixes=[664]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/ms.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (54, 'Western Sahara', 'EH', 'ESH', '{root=+2, suffixes=[125288, 125289]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/eh.png',
        '{DZD={name=Algerian dinar, symbol=دج}, MAD={name=Moroccan dirham, symbol=DH}, MRU={name=Mauritanian ouguiya, symbol=UM}}',
        '{ber=Berber, mey=Hassaniya, spa=Spanish}', 1),
       (55, 'Guinea', 'GN', 'GIN', '{root=+2, suffixes=[24]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/gn.png', '{GNF={name=Guinean franc, symbol=Fr}}', '{fra=French}', 1),
       (56, 'British Virgin Islands', 'VG', 'VGB', '{root=+1, suffixes=[284]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/vg.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (57, 'Panama', 'PA', 'PAN', '{root=+5, suffixes=[07]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/pa.png',
        '{PAB={name=Panamanian balboa, symbol=B/.}, USD={name=United States dollar, symbol=$}}', '{spa=Spanish}', 1),
       (58, 'Yemen', 'YE', 'YEM', '{root=+9, suffixes=[67]}', 'Asia', 'Western Asia', 'https://flagcdn.com/w320/ye.png',
        '{YER={name=Yemeni rial, symbol=﷼}}', '{ara=Arabic}', 1),
       (59, 'Estonia', 'EE', 'EST', '{root=+3, suffixes=[72]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/ee.png', '{EUR={name=Euro, symbol=€}}', '{est=Estonian}', 1),
       (60, 'Guyana', 'GY', 'GUY', '{root=+5, suffixes=[92]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/gy.png', '{GYD={name=Guyanese dollar, symbol=$}}', '{eng=English}', 1),
       (61, 'Luxembourg', 'LU', 'LUX', '{root=+3, suffixes=[52]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/lu.png', '{EUR={name=Euro, symbol=€}}', '{deu=German, fra=French, ltz=Luxembourgish}',
        1),
       (62, 'Namibia', 'NA', 'NAM', '{root=+2, suffixes=[64]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/na.png',
        '{NAD={name=Namibian dollar, symbol=$}, ZAR={name=South African rand, symbol=R}}',
        '{afr=Afrikaans, deu=German, eng=English, her=Herero, hgm=Khoekhoe, kwn=Kwangali, loz=Lozi, ndo=Ndonga, tsn=Tswana}',
        1),
       (63, 'Kosovo', 'XK', 'UNK', '{root=+3, suffixes=[83]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/xk.png', '{EUR={name=Euro, symbol=€}}', '{sqi=Albanian, srp=Serbian}', 1),
       (64, 'New Caledonia', 'NC', 'NCL', '{root=+6, suffixes=[87]}', 'Oceania', 'Melanesia',
        'https://flagcdn.com/w320/nc.png', '{XPF={name=CFP franc, symbol=₣}}', '{fra=French}', 1),
       (65, 'Saint Pierre and Miquelon', 'PM', 'SPM', '{root=+5, suffixes=[08]}', 'Americas', 'North America',
        'https://flagcdn.com/w320/pm.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (66, 'Libya', 'LY', 'LBY', '{root=+2, suffixes=[18]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/ly.png', '{LYD={name=Libyan dinar, symbol=ل.د}}', '{ara=Arabic}', 1),
       (67, 'Myanmar', 'MM', 'MMR', '{root=+9, suffixes=[5]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/mm.png', '{MMK={name=Burmese kyat, symbol=Ks}}', '{mya=Burmese}', 1),
       (68, 'Israel', 'IL', 'ISR', '{root=+9, suffixes=[72]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/il.png', '{ILS={name=Israeli new shekel, symbol=₪}}', '{ara=Arabic, heb=Hebrew}', 1),
       (69, 'Mali', 'ML', 'MLI', '{root=+2, suffixes=[23]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/ml.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (70, 'Argentina', 'AR', 'ARG', '{root=+5, suffixes=[4]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/ar.png', '{ARS={name=Argentine peso, symbol=$}}', '{grn=Guaraní, spa=Spanish}', 1),
       (71, 'French Guiana', 'GF', 'GUF', '{root=+5, suffixes=[94]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/gf.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (72, 'Uganda', 'UG', 'UGA', '{root=+2, suffixes=[56]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/ug.png', '{UGX={name=Ugandan shilling, symbol=Sh}}', '{eng=English, swa=Swahili}', 1),
       (73, 'Malaysia', 'MY', 'MYS', '{root=+6, suffixes=[0]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/my.png', '{MYR={name=Malaysian ringgit, symbol=RM}}', '{eng=English, msa=Malay}', 1),
       (74, 'Zambia', 'ZM', 'ZMB', '{root=+2, suffixes=[60]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/zm.png', '{ZMW={name=Zambian kwacha, symbol=ZK}}', '{eng=English}', 1),
       (75, 'Micronesia', 'FM', 'FSM', '{root=+6, suffixes=[91]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/fm.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (76, 'Slovakia', 'SK', 'SVK', '{root=+4, suffixes=[21]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/sk.png', '{EUR={name=Euro, symbol=€}}', '{slk=Slovak}', 1),
       (77, 'Burundi', 'BI', 'BDI', '{root=+2, suffixes=[57]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/bi.png', '{BIF={name=Burundian franc, symbol=Fr}}', '{fra=French, run=Kirundi}', 1),
       (78, 'Serbia', 'RS', 'SRB', '{root=+3, suffixes=[81]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/rs.png', '{RSD={name=Serbian dinar, symbol=дин.}}', '{srp=Serbian}', 1),
       (79, 'Turkmenistan', 'TM', 'TKM', '{root=+9, suffixes=[93]}', 'Asia', 'Central Asia',
        'https://flagcdn.com/w320/tm.png', '{TMT={name=Turkmenistan manat, symbol=m}}', '{rus=Russian, tuk=Turkmen}',
        1),
       (80, 'South Africa', 'ZA', 'ZAF', '{root=+2, suffixes=[7]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/za.png', '{ZAR={name=South African rand, symbol=R}}',
        '{afr=Afrikaans, eng=English, nbl=Southern Ndebele, nso=Northern Sotho, sot=Southern Sotho, ssw=Swazi, tsn=Tswana, tso=Tsonga, ven=Venda, xho=Xhosa, zul=Zulu}',
        1),
       (81, 'Kazakhstan', 'KZ', 'KAZ', '{root=+7, suffixes=[6, 7]}', 'Asia', 'Central Asia',
        'https://flagcdn.com/w320/kz.png', '{KZT={name=Kazakhstani tenge, symbol=₸}}', '{kaz=Kazakh, rus=Russian}', 1),
       (82, 'Canada', 'CA', 'CAN', '{root=+1, suffixes=[]}', 'Americas', 'North America',
        'https://flagcdn.com/w320/ca.png', '{CAD={name=Canadian dollar, symbol=$}}', '{eng=English, fra=French}', 1),
       (83, 'Poland', 'PL', 'POL', '{root=+4, suffixes=[8]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/pl.png', '{PLN={name=Polish złoty, symbol=zł}}', '{pol=Polish}', 1),
       (84, 'Puerto Rico', 'PR', 'PRI', '{root=+1, suffixes=[787, 939]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/pr.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English, spa=Spanish}',
        1),
       (85, 'Cocos (Keeling) Islands', 'CC', 'CCK', '{root=+6, suffixes=[1]}', 'Oceania', 'Australia and New Zealand',
        'https://flagcdn.com/w320/cc.png', '{AUD={name=Australian dollar, symbol=$}}', '{eng=English}', 1),
       (86, 'San Marino', 'SM', 'SMR', '{root=+3, suffixes=[78]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/sm.png', '{EUR={name=Euro, symbol=€}}', '{ita=Italian}', 1),
       (87, 'American Samoa', 'AS', 'ASM', '{root=+1, suffixes=[684]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/as.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English, smo=Samoan}',
        1),
       (88, 'Qatar', 'QA', 'QAT', '{root=+9, suffixes=[74]}', 'Asia', 'Western Asia', 'https://flagcdn.com/w320/qa.png',
        '{QAR={name=Qatari riyal, symbol=ر.ق}}', '{ara=Arabic}', 1),
       (89, 'Gibraltar', 'GI', 'GIB', '{root=+3, suffixes=[50]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/gi.png', '{GIP={name=Gibraltar pound, symbol=£}}', '{eng=English}', 1),
       (90, 'Croatia', 'HR', 'HRV', '{root=+3, suffixes=[85]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/hr.png', '{EUR={name=Euro, symbol=€}}', '{hrv=Croatian}', 1),
       (91, 'Svalbard and Jan Mayen', 'SJ', 'SJM', '{root=+4, suffixes=[779]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/sj.png', '{NOK={name=krone, symbol=kr}}', '{nor=Norwegian}', 1),
       (92, 'Isle of Man', 'IM', 'IMN', '{root=+4, suffixes=[4]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/im.png', '{GBP={name=British pound, symbol=£}, IMP={name=Manx pound, symbol=£}}',
        '{eng=English, glv=Manx}', 1),
       (93, 'Palestine', 'PS', 'PSE', '{root=+9, suffixes=[70]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/ps.png',
        '{EGP={name=Egyptian pound, symbol=E£}, ILS={name=Israeli new shekel, symbol=₪}, JOD={name=Jordanian dinar, symbol=JD}}',
        '{ara=Arabic}', 1),
       (94, 'Mauritius', 'MU', 'MUS', '{root=+2, suffixes=[30]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/mu.png', '{MUR={name=Mauritian rupee, symbol=₨}}',
        '{eng=English, fra=French, mfe=Mauritian Creole}', 1),
       (95, 'Saint Martin', 'MF', 'MAF', '{root=+5, suffixes=[90]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/mf.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (96, 'Sierra Leone', 'SL', 'SLE', '{root=+2, suffixes=[32]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/sl.png', '{SLE={name=Leone, symbol=Le}}', '{eng=English}', 1),
       (97, 'Saint Kitts and Nevis', 'KN', 'KNA', '{root=+1, suffixes=[869]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/kn.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (98, 'Iceland', 'IS', 'ISL', '{root=+3, suffixes=[54]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/is.png', '{ISK={name=Icelandic króna, symbol=kr}}', '{isl=Icelandic}', 1),
       (99, 'Heard Island and McDonald Islands', 'HM', 'HMD', '{root=, suffixes=[]}', 'Antarctic', '',
        'https://flagcdn.com/w320/hm.png', '{}', '{eng=English}', 1),
       (100, 'Kuwait', 'KW', 'KWT', '{root=+9, suffixes=[65]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/kw.png', '{KWD={name=Kuwaiti dinar, symbol=د.ك}}', '{ara=Arabic}', 1),
       (101, 'Taiwan', 'TW', 'TWN', '{root=+8, suffixes=[86]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/tw.png', '{TWD={name=New Taiwan dollar, symbol=$}}', '{zho=Chinese}', 1),
       (102, 'Cameroon', 'CM', 'CMR', '{root=+2, suffixes=[37]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/cm.png', '{XAF={name=Central African CFA franc, symbol=Fr}}',
        '{eng=English, fra=French}', 1),
       (103, 'El Salvador', 'SV', 'SLV', '{root=+5, suffixes=[03]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/sv.png', '{USD={name=United States dollar, symbol=$}}', '{spa=Spanish}', 1),
       (104, 'Macau', 'MO', 'MAC', '{root=+8, suffixes=[53]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/mo.png', '{MOP={name=Macanese pataca, symbol=P}}', '{por=Portuguese, zho=Chinese}',
        1);
insert into `country` (id, name, countryCode, countryCode3, phoneCode, region, subRegion, flag, currency, language,
                       active)
values (105, 'Cuba', 'CU', 'CUB', '{root=+5, suffixes=[3]}', 'Americas', 'Caribbean', 'https://flagcdn.com/w320/cu.png',
        '{CUC={name=Cuban convertible peso, symbol=$}, CUP={name=Cuban peso, symbol=$}}', '{spa=Spanish}', 1),
       (106, 'Brazil', 'BR', 'BRA', '{root=+5, suffixes=[5]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/br.png', '{BRL={name=Brazilian real, symbol=R$}}', '{por=Portuguese}', 1),
       (107, 'Solomon Islands', 'SB', 'SLB', '{root=+6, suffixes=[77]}', 'Oceania', 'Melanesia',
        'https://flagcdn.com/w320/sb.png', '{SBD={name=Solomon Islands dollar, symbol=$}}', '{eng=English}', 1),
       (108, 'Greece', 'GR', 'GRC', '{root=+3, suffixes=[0]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/gr.png', '{EUR={name=Euro, symbol=€}}', '{ell=Greek}', 1),
       (109, 'Norway', 'NO', 'NOR', '{root=+4, suffixes=[7]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/no.png', '{NOK={name=Norwegian krone, symbol=kr}}',
        '{nno=Norwegian Nynorsk, nob=Norwegian Bokmål, smi=Sami}', 1),
       (110, 'Jamaica', 'JM', 'JAM', '{root=+1, suffixes=[876, 658]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/jm.png', '{JMD={name=Jamaican dollar, symbol=$}}',
        '{eng=English, jam=Jamaican Patois}', 1),
       (111, 'Tanzania', 'TZ', 'TZA', '{root=+2, suffixes=[55]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/tz.png', '{TZS={name=Tanzanian shilling, symbol=Sh}}', '{eng=English, swa=Swahili}',
        1),
       (112, 'Bolivia', 'BO', 'BOL', '{root=+5, suffixes=[91]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/bo.png', '{BOB={name=Bolivian boliviano, symbol=Bs.}}',
        '{aym=Aymara, grn=Guaraní, que=Quechua, spa=Spanish}', 1),
       (113, 'Cyprus', 'CY', 'CYP', '{root=+3, suffixes=[57]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/cy.png', '{EUR={name=Euro, symbol=€}}', '{ell=Greek, tur=Turkish}', 1),
       (114, 'Mongolia', 'MN', 'MNG', '{root=+9, suffixes=[76]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/mn.png', '{MNT={name=Mongolian tögrög, symbol=₮}}', '{mon=Mongolian}', 1),
       (115, 'Monaco', 'MC', 'MCO', '{root=+3, suffixes=[77]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/mc.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (116, 'Tunisia', 'TN', 'TUN', '{root=+2, suffixes=[16]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/tn.png', '{TND={name=Tunisian dinar, symbol=د.ت}}', '{ara=Arabic}', 1),
       (117, 'French Southern and Antarctic Lands', 'TF', 'ATF', '{root=+2, suffixes=[62]}', 'Antarctic', '',
        'https://flagcdn.com/w320/tf.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (118, 'Syria', 'SY', 'SYR', '{root=+9, suffixes=[63]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/sy.png', '{SYP={name=Syrian pound, symbol=£}}', '{ara=Arabic}', 1),
       (119, 'Chile', 'CL', 'CHL', '{root=+5, suffixes=[6]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/cl.png', '{CLP={name=Chilean peso, symbol=$}}', '{spa=Spanish}', 1),
       (120, 'Azerbaijan', 'AZ', 'AZE', '{root=+9, suffixes=[94]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/az.png', '{AZN={name=Azerbaijani manat, symbol=₼}}', '{aze=Azerbaijani}', 1),
       (121, 'France', 'FR', 'FRA', '{root=+3, suffixes=[3]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/fr.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (122, 'South Korea', 'KR', 'KOR', '{root=+8, suffixes=[2]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/kr.png', '{KRW={name=South Korean won, symbol=₩}}', '{kor=Korean}', 1),
       (123, 'Ghana', 'GH', 'GHA', '{root=+2, suffixes=[33]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/gh.png', '{GHS={name=Ghanaian cedi, symbol=₵}}', '{eng=English}', 1),
       (124, 'Denmark', 'DK', 'DNK', '{root=+4, suffixes=[5]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/dk.png', '{DKK={name=Danish krone, symbol=kr}}', '{dan=Danish}', 1),
       (125, 'Sint Maarten', 'SX', 'SXM', '{root=+1, suffixes=[721]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/sx.png', '{ANG={name=Netherlands Antillean guilder, symbol=ƒ}}',
        '{eng=English, fra=French, nld=Dutch}', 1),
       (126, 'Andorra', 'AD', 'AND', '{root=+3, suffixes=[76]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/ad.png', '{EUR={name=Euro, symbol=€}}', '{cat=Catalan}', 1),
       (127, 'Curaçao', 'CW', 'CUW', '{root=+5, suffixes=[99]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/cw.png', '{ANG={name=Netherlands Antillean guilder, symbol=ƒ}}',
        '{eng=English, nld=Dutch, pap=Papiamento}', 1),
       (128, 'Switzerland', 'CH', 'CHE', '{root=+4, suffixes=[1]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/ch.png', '{CHF={name=Swiss franc, symbol=Fr.}}',
        '{fra=French, gsw=Swiss German, ita=Italian, roh=Romansh}', 1),
       (129, 'North Macedonia', 'MK', 'MKD', '{root=+3, suffixes=[89]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/mk.png', '{MKD={name=denar, symbol=den}}', '{mkd=Macedonian}', 1),
       (130, 'South Sudan', 'SS', 'SSD', '{root=+2, suffixes=[11]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/ss.png', '{SSP={name=South Sudanese pound, symbol=£}}', '{eng=English}', 1),
       (131, 'Haiti', 'HT', 'HTI', '{root=+5, suffixes=[09]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/ht.png', '{HTG={name=Haitian gourde, symbol=G}}', '{fra=French, hat=Haitian Creole}',
        1),
       (132, 'Peru', 'PE', 'PER', '{root=+5, suffixes=[1]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/pe.png', '{PEN={name=Peruvian sol, symbol=S/ }}',
        '{aym=Aymara, que=Quechua, spa=Spanish}', 1),
       (133, 'Guinea-Bissau', 'GW', 'GNB', '{root=+2, suffixes=[45]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/gw.png', '{XOF={name=West African CFA franc, symbol=Fr}}',
        '{por=Portuguese, pov=Upper Guinea Creole}', 1),
       (134, 'Czechia', 'CZ', 'CZE', '{root=+4, suffixes=[20]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/cz.png', '{CZK={name=Czech koruna, symbol=Kč}}', '{ces=Czech, slk=Slovak}', 1),
       (135, 'Portugal', 'PT', 'PRT', '{root=+3, suffixes=[51]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/pt.png', '{EUR={name=Euro, symbol=€}}', '{por=Portuguese}', 1),
       (136, 'Gabon', 'GA', 'GAB', '{root=+2, suffixes=[41]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/ga.png', '{XAF={name=Central African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (137, 'Mexico', 'MX', 'MEX', '{root=+5, suffixes=[2]}', 'Americas', 'North America',
        'https://flagcdn.com/w320/mx.png', '{MXN={name=Mexican peso, symbol=$}}', '{spa=Spanish}', 1),
       (138, 'Italy', 'IT', 'ITA', '{root=+3, suffixes=[9]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/it.png', '{EUR={name=Euro, symbol=€}}', '{ita=Italian, cat=Catalan}', 1),
       (139, 'Finland', 'FI', 'FIN', '{root=+3, suffixes=[58]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/fi.png', '{EUR={name=Euro, symbol=€}}', '{fin=Finnish, swe=Swedish}', 1),
       (140, 'Zimbabwe', 'ZW', 'ZWE', '{root=+2, suffixes=[63]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/zw.png', '{ZWL={name=Zimbabwean dollar, symbol=$}}',
        '{bwg=Chibarwe, eng=English, kck=Kalanga, khi=Khoisan, ndc=Ndau, nde=Northern Ndebele, nya=Chewa, sna=Shona, sot=Sotho, toi=Tonga, tsn=Tswana, tso=Tsonga, ven=Venda, xho=Xhosa, zib=Zimbabwean Sign Language}',
        1),
       (141, 'Spain', 'ES', 'ESP', '{root=+3, suffixes=[4]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/es.png', '{EUR={name=Euro, symbol=€}}',
        '{spa=Spanish, cat=Catalan, eus=Basque, glc=Galician}', 1),
       (142, 'Japan', 'JP', 'JPN', '{root=+8, suffixes=[1]}', 'Asia', 'Eastern Asia', 'https://flagcdn.com/w320/jp.png',
        '{JPY={name=Japanese yen, symbol=¥}}', '{jpn=Japanese}', 1),
       (143, 'Seychelles', 'SC', 'SYC', '{root=+2, suffixes=[48]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/sc.png', '{SCR={name=Seychellois rupee, symbol=₨}}',
        '{crs=Seychellois Creole, eng=English, fra=French}', 1),
       (144, 'Albania', 'AL', 'ALB', '{root=+3, suffixes=[55]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/al.png', '{ALL={name=Albanian lek, symbol=L}}', '{sqi=Albanian}', 1),
       (145, 'Moldova', 'MD', 'MDA', '{root=+3, suffixes=[73]}', 'Europe', 'Eastern Europe',
        'https://flagcdn.com/w320/md.png', '{MDL={name=Moldovan leu, symbol=L}}', '{ron=Romanian}', 1),
       (146, 'Guatemala', 'GT', 'GTM', '{root=+5, suffixes=[02]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/gt.png', '{GTQ={name=Guatemalan quetzal, symbol=Q}}', '{spa=Spanish}', 1),
       (147, 'Germany', 'DE', 'DEU', '{root=+4, suffixes=[9]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/de.png', '{EUR={name=Euro, symbol=€}}', '{deu=German}', 1),
       (148, 'Guadeloupe', 'GP', 'GLP', '{root=+5, suffixes=[90]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/gp.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (149, 'Bulgaria', 'BG', 'BGR', '{root=+3, suffixes=[59]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/bg.png', '{BGN={name=Bulgarian lev, symbol=лв}}', '{bul=Bulgarian}', 1),
       (150, 'Paraguay', 'PY', 'PRY', '{root=+5, suffixes=[95]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/py.png', '{PYG={name=Paraguayan guaraní, symbol=₲}}', '{grn=Guaraní, spa=Spanish}',
        1),
       (151, 'Eritrea', 'ER', 'ERI', '{root=+2, suffixes=[91]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/er.png', '{ERN={name=Eritrean nakfa, symbol=Nfk}}',
        '{ara=Arabic, eng=English, tir=Tigrinya}', 1),
       (152, 'Sudan', 'SD', 'SDN', '{root=+2, suffixes=[49]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/sd.png', '{SDG={name=Sudanese pound, symbol=ج.س}}', '{ara=Arabic, eng=English}', 1),
       (153, 'Equatorial Guinea', 'GQ', 'GNQ', '{root=+2, suffixes=[40]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/gq.png', '{XAF={name=Central African CFA franc, symbol=Fr}}',
        '{fra=French, por=Portuguese, spa=Spanish}', 1),
       (154, 'Colombia', 'CO', 'COL', '{root=+5, suffixes=[7]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/co.png', '{COP={name=Colombian peso, symbol=$}}', '{spa=Spanish}', 1),
       (155, 'Nigeria', 'NG', 'NGA', '{root=+2, suffixes=[34]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/ng.png', '{NGN={name=Nigerian naira, symbol=₦}}', '{eng=English}', 1),
       (156, 'Montenegro', 'ME', 'MNE', '{root=+3, suffixes=[82]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/me.png', '{EUR={name=Euro, symbol=€}}', '{cnr=Montenegrin}', 1),
       (157, 'Nepal', 'NP', 'NPL', '{root=+9, suffixes=[77]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/np.png', '{NPR={name=Nepalese rupee, symbol=₨}}', '{nep=Nepali}', 1),
       (158, 'Brunei', 'BN', 'BRN', '{root=+6, suffixes=[73]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/bn.png',
        '{BND={name=Brunei dollar, symbol=$}, SGD={name=Singapore dollar, symbol=$}}', '{msa=Malay}', 1),
       (159, 'Austria', 'AT', 'AUT', '{root=+4, suffixes=[3]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/at.png', '{EUR={name=Euro, symbol=€}}', '{deu=German}', 1),
       (160, 'Turks and Caicos Islands', 'TC', 'TCA', '{root=+1, suffixes=[649]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/tc.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (161, 'Bahamas', 'BS', 'BHS', '{root=+1, suffixes=[242]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/bs.png',
        '{BSD={name=Bahamian dollar, symbol=$}, USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (162, 'United Kingdom', 'GB', 'GBR', '{root=+4, suffixes=[4]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/gb.png', '{GBP={name=British pound, symbol=£}}', '{eng=English}', 1),
       (163, 'British Indian Ocean Territory', 'IO', 'IOT', '{root=+2, suffixes=[46]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/io.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (164, 'Liberia', 'LR', 'LBR', '{root=+2, suffixes=[31]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/lr.png', '{LRD={name=Liberian dollar, symbol=$}}', '{eng=English}', 1),
       (165, 'Anguilla', 'AI', 'AIA', '{root=+1, suffixes=[264]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/ai.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (166, 'Central African Republic', 'CF', 'CAF', '{root=+2, suffixes=[36]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/cf.png', '{XAF={name=Central African CFA franc, symbol=Fr}}',
        '{fra=French, sag=Sango}', 1),
       (167, 'Algeria', 'DZ', 'DZA', '{root=+2, suffixes=[13]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/dz.png', '{DZD={name=Algerian dinar, symbol=د.ج}}', '{ara=Arabic}', 1),
       (168, 'Saint Lucia', 'LC', 'LCA', '{root=+1, suffixes=[758]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/lc.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (169, 'Romania', 'RO', 'ROU', '{root=+4, suffixes=[0]}', 'Europe', 'Southeast Europe',
        'https://flagcdn.com/w320/ro.png', '{RON={name=Romanian leu, symbol=lei}}', '{ron=Romanian}', 1),
       (170, 'United Arab Emirates', 'AE', 'ARE', '{root=+9, suffixes=[71]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/ae.png', '{AED={name=United Arab Emirates dirham, symbol=د.إ}}', '{ara=Arabic}', 1),
       (171, 'Morocco', 'MA', 'MAR', '{root=+2, suffixes=[12]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/ma.png', '{MAD={name=Moroccan dirham, symbol=د.م.}}', '{ara=Arabic, ber=Berber}', 1),
       (172, 'Northern Mariana Islands', 'MP', 'MNP', '{root=+1, suffixes=[670]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/mp.png', '{USD={name=United States dollar, symbol=$}}',
        '{cal=Carolinian, cha=Chamorro, eng=English}', 1),
       (173, 'United States Minor Outlying Islands', 'UM', 'UMI', '{root=+2, suffixes=[68]}', 'Americas',
        'North America', 'https://flagcdn.com/w320/um.png', '{USD={name=United States dollar, symbol=$}}',
        '{eng=English}', 1),
       (174, 'Botswana', 'BW', 'BWA', '{root=+2, suffixes=[67]}', 'Africa', 'Southern Africa',
        'https://flagcdn.com/w320/bw.png', '{BWP={name=Botswana pula, symbol=P}}', '{eng=English, tsn=Tswana}', 1),
       (175, 'Lithuania', 'LT', 'LTU', '{root=+3, suffixes=[70]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/lt.png', '{EUR={name=Euro, symbol=€}}', '{lit=Lithuanian}', 1),
       (176, 'Caribbean Netherlands', 'BQ', 'BES', '{root=+5, suffixes=[99]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/bq.png', '{USD={name=United States dollar, symbol=$}}',
        '{eng=English, nld=Dutch, pap=Papiamento}', 1),
       (177, 'Cape Verde', 'CV', 'CPV', '{root=+2, suffixes=[38]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/cv.png', '{CVE={name=Cape Verdean escudo, symbol=Esc}}', '{por=Portuguese}', 1),
       (178, 'United States', 'US', 'USA',
        '{root=+1, suffixes=[201, 202, 203, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 224, 225, 227, 228, 229, 231, 234, 239, 240, 248, 251, 252, 253, 254, 256, 260, 262, 267, 269, 270, 272, 274, 276, 281, 283, 301, 302, 303, 304, 305, 307, 308, 309, 310, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 323, 325, 327, 330, 331, 334, 336, 337, 339, 346, 347, 351, 352, 360, 361, 364, 380, 385, 386, 401, 402, 404, 405, 406, 407, 408, 409, 410, 412, 413, 414, 415, 417, 419, 423, 424, 425, 430, 432, 434, 435, 440, 442, 443, 447, 458, 463, 464, 469, 470, 475, 478, 479, 480, 484, 501, 502, 503, 504, 505, 507, 508, 509, 510, 512, 513, 515, 516, 517, 518, 520, 530, 531, 534, 539, 540, 541, 551, 559, 561, 562, 563, 564, 567, 570, 571, 573, 574, 575, 580, 585, 586, 601, 602, 603, 605, 606, 607, 608, 609, 610, 612, 614, 615, 616, 617, 618, 619, 620, 623, 626, 628, 629, 630, 631, 636, 641, 646, 650, 651, 657, 660, 661, 662, 667, 669, 678, 681, 682, 701, 702, 703, 704, 706, 707, 708, 712, 713, 714, 715, 716, 717, 718, 719, 720, 724, 725, 727, 730, 731, 732, 734, 737, 740, 743, 747, 754, 757, 760, 762, 763, 765, 769, 770, 772, 773, 774, 775, 779, 781, 785, 786, 801, 802, 803, 804, 805, 806, 808, 810, 812, 813, 814, 815, 816, 817, 818, 828, 830, 831, 832, 843, 845, 847, 848, 850, 854, 856, 857, 858, 859, 860, 862, 863, 864, 865, 870, 872, 878, 901, 903, 904, 906, 907, 908, 909, 910, 912, 913, 914, 915, 916, 917, 918, 919, 920, 925, 928, 929, 930, 931, 934, 936, 937, 938, 940, 941, 947, 949, 951, 952, 954, 956, 959, 970, 971, 972, 973, 975, 978, 979, 980, 984, 985, 989]}',
        'Americas', 'North America', 'https://flagcdn.com/w320/us.png', '{USD={name=United States dollar, symbol=$}}',
        '{eng=English}', 1),
       (179, 'Australia', 'AU', 'AUS', '{root=+6, suffixes=[1]}', 'Oceania', 'Australia and New Zealand',
        'https://flagcdn.com/w320/au.png', '{AUD={name=Australian dollar, symbol=$}}', '{eng=English}', 1),
       (180, 'Guam', 'GU', 'GUM', '{root=+1, suffixes=[671]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/gu.png', '{USD={name=United States dollar, symbol=$}}',
        '{cha=Chamorro, eng=English, spa=Spanish}', 1),
       (181, 'Tonga', 'TO', 'TON', '{root=+6, suffixes=[76]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/to.png', '{TOP={name=Tongan paʻanga, symbol=T$}}', '{eng=English, ton=Tongan}', 1),
       (182, 'Afghanistan', 'AF', 'AFG', '{root=+9, suffixes=[3]}', 'Asia', 'Southern Asia',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_the_Taliban.svg/320px-Flag_of_the_Taliban.svg.png',
        '{AFN={name=Afghan afghani, symbol=؋}}', '{prs=Dari, pus=Pashto, tuk=Turkmen}', 1),
       (183, 'Falkland Islands', 'FK', 'FLK', '{root=+5, suffixes=[00]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/fk.png', '{FKP={name=Falkland Islands pound, symbol=£}}', '{eng=English}', 1),
       (184, 'Nauru', 'NR', 'NRU', '{root=+6, suffixes=[74]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/nr.png', '{AUD={name=Australian dollar, symbol=$}}', '{eng=English, nau=Nauru}', 1),
       (185, 'Cook Islands', 'CK', 'COK', '{root=+6, suffixes=[82]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/ck.png',
        '{CKD={name=Cook Islands dollar, symbol=$}, NZD={name=New Zealand dollar, symbol=$}}',
        '{eng=English, rar=Cook Islands Māori}', 1),
       (186, 'Hungary', 'HU', 'HUN', '{root=+3, suffixes=[6]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/hu.png', '{HUF={name=Hungarian forint, symbol=Ft}}', '{hun=Hungarian}', 1),
       (187, 'Mauritania', 'MR', 'MRT', '{root=+2, suffixes=[22]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/mr.png', '{MRU={name=Mauritanian ouguiya, symbol=UM}}', '{ara=Arabic}', 1),
       (188, 'Slovenia', 'SI', 'SVN', '{root=+3, suffixes=[86]}', 'Europe', 'Central Europe',
        'https://flagcdn.com/w320/si.png', '{EUR={name=Euro, symbol=€}}', '{slv=Slovene}', 1),
       (189, 'Chad', 'TD', 'TCD', '{root=+2, suffixes=[35]}', 'Africa', 'Middle Africa',
        'https://flagcdn.com/w320/td.png', '{XAF={name=Central African CFA franc, symbol=Fr}}',
        '{ara=Arabic, fra=French}', 1),
       (190, 'Vatican City', 'VA', 'VAT', '{root=+3, suffixes=[906698, 79]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/va.png', '{EUR={name=Euro, symbol=€}}', '{ita=Italian, lat=Latin}', 1),
       (191, 'Benin', 'BJ', 'BEN', '{root=+2, suffixes=[29]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/bj.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (192, 'Singapore', 'SG', 'SGP', '{root=+6, suffixes=[5]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/sg.png', '{SGD={name=Singapore dollar, symbol=$}}',
        '{eng=English, zho=Chinese, msa=Malay, tam=Tamil}', 1),
       (193, 'Malta', 'MT', 'MLT', '{root=+3, suffixes=[56]}', 'Europe', 'Southern Europe',
        'https://flagcdn.com/w320/mt.png', '{EUR={name=Euro, symbol=€}}', '{eng=English, mlt=Maltese}', 1),
       (194, 'Saint Vincent and the Grenadines', 'VC', 'VCT', '{root=+1, suffixes=[784]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/vc.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (195, 'Marshall Islands', 'MH', 'MHL', '{root=+6, suffixes=[92]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/mh.png', '{USD={name=United States dollar, symbol=$}}',
        '{eng=English, mah=Marshallese}', 1),
       (196, 'Thailand', 'TH', 'THA', '{root=+6, suffixes=[6]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/th.png', '{THB={name=Thai baht, symbol=฿}}', '{tha=Thai}', 1),
       (197, 'Liechtenstein', 'LI', 'LIE', '{root=+4, suffixes=[23]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/li.png', '{CHF={name=Swiss franc, symbol=Fr}}', '{deu=German}', 1),
       (198, 'Ireland', 'IE', 'IRL', '{root=+3, suffixes=[53]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/ie.png', '{EUR={name=Euro, symbol=€}}', '{eng=English, gle=Irish}', 1),
       (199, 'Maldives', 'MV', 'MDV', '{root=+9, suffixes=[60]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/mv.png', '{MVR={name=Maldivian rufiyaa, symbol=.ރ}}', '{div=Maldivian}', 1),
       (200, 'Vietnam', 'VN', 'VNM', '{root=+8, suffixes=[4]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/vn.png', '{VND={name=Vietnamese đồng, symbol=₫}}', '{vie=Vietnamese}', 1);
insert into `country` (id, name, countryCode, countryCode3, phoneCode, region, subRegion, flag, currency, language,
                       active)
values (201, 'Armenia', 'AM', 'ARM', '{root=+3, suffixes=[74]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/am.png', '{AMD={name=Armenian dram, symbol=֏}}', '{hye=Armenian}', 1),
       (202, 'Pakistan', 'PK', 'PAK', '{root=+9, suffixes=[2]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/pk.png', '{PKR={name=Pakistani rupee, symbol=₨}}', '{eng=English, urd=Urdu}', 1),
       (203, 'Ivory Coast', 'CI', 'CIV', '{root=+2, suffixes=[25]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/ci.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (204, 'Russia', 'RU', 'RUS', '{root=+7, suffixes=[3, 4, 5, 8, 9]}', 'Europe', 'Eastern Europe',
        'https://flagcdn.com/w320/ru.png', '{RUB={name=Russian ruble, symbol=₽}}', '{rus=Russian}', 1),
       (205, 'Martinique', 'MQ', 'MTQ', '{root=+5, suffixes=[96]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/mq.png', '{EUR={name=Euro, symbol=€}}', '{fra=French}', 1),
       (206, 'Bouvet Island', 'BV', 'BVT', '{root=+4, suffixes=[7]}', 'Antarctic', '',
        'https://flagcdn.com/w320/bv.png', '{}', '{nor=Norwegian}', 1),
       (207, 'Timor-Leste', 'TL', 'TLS', '{root=+6, suffixes=[70]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/tl.png', '{USD={name=United States dollar, symbol=$}}', '{por=Portuguese, tet=Tetum}',
        1),
       (208, 'Nicaragua', 'NI', 'NIC', '{root=+5, suffixes=[05]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/ni.png', '{NIO={name=Nicaraguan córdoba, symbol=C$}}', '{spa=Spanish}', 1),
       (209, 'Papua New Guinea', 'PG', 'PNG', '{root=+6, suffixes=[75]}', 'Oceania', 'Melanesia',
        'https://flagcdn.com/w320/pg.png', '{PGK={name=Papua New Guinean kina, symbol=K}}',
        '{eng=English, hmo=Hiri Motu, tpi=Tok Pisin}', 1),
       (210, 'Egypt', 'EG', 'EGY', '{root=+2, suffixes=[0]}', 'Africa', 'Northern Africa',
        'https://flagcdn.com/w320/eg.png', '{EGP={name=Egyptian pound, symbol=£}}', '{ara=Arabic}', 1),
       (211, 'Jordan', 'JO', 'JOR', '{root=+9, suffixes=[62]}', 'Asia', 'Western Asia',
        'https://flagcdn.com/w320/jo.png', '{JOD={name=Jordanian dinar, symbol=د.ا}}', '{ara=Arabic}', 1),
       (212, 'Uruguay', 'UY', 'URY', '{root=+5, suffixes=[98]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/uy.png', '{UYU={name=Uruguayan peso, symbol=$}}', '{spa=Spanish}', 1),
       (213, 'Burkina Faso', 'BF', 'BFA', '{root=+2, suffixes=[26]}', 'Africa', 'Western Africa',
        'https://flagcdn.com/w320/bf.png', '{XOF={name=West African CFA franc, symbol=Fr}}', '{fra=French}', 1),
       (214, 'Cambodia', 'KH', 'KHM', '{root=+8, suffixes=[55]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/kh.png',
        '{KHR={name=Cambodian riel, symbol=៛}, USD={name=United States dollar, symbol=$}}', '{khm=Khmer}', 1),
       (215, 'Ethiopia', 'ET', 'ETH', '{root=+2, suffixes=[51]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/et.png', '{ETB={name=Ethiopian birr, symbol=Br}}', '{amh=Amharic}', 1),
       (216, 'Kiribati', 'KI', 'KIR', '{root=+6, suffixes=[86]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/ki.png',
        '{AUD={name=Australian dollar, symbol=$}, KID={name=Kiribati dollar, symbol=$}}',
        '{eng=English, gil=Gilbertese}', 1),
       (217, 'Bhutan', 'BT', 'BTN', '{root=+9, suffixes=[75]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/bt.png',
        '{BTN={name=Bhutanese ngultrum, symbol=Nu.}, INR={name=Indian rupee, symbol=₹}}', '{dzo=Dzongkha}', 1),
       (218, 'Dominican Republic', 'DO', 'DOM', '{root=+1, suffixes=[809, 829, 849]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/do.png', '{DOP={name=Dominican peso, symbol=$}}', '{spa=Spanish}', 1),
       (219, 'Suriname', 'SR', 'SUR', '{root=+5, suffixes=[97]}', 'Americas', 'South America',
        'https://flagcdn.com/w320/sr.png', '{SRD={name=Surinamese dollar, symbol=$}}', '{nld=Dutch}', 1),
       (220, 'Bangladesh', 'BD', 'BGD', '{root=+8, suffixes=[80]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/bd.png', '{BDT={name=Bangladeshi taka, symbol=৳}}', '{ben=Bengali}', 1),
       (221, 'Barbados', 'BB', 'BRB', '{root=+1, suffixes=[246]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/bb.png', '{BBD={name=Barbadian dollar, symbol=$}}', '{eng=English}', 1),
       (222, 'North Korea', 'KP', 'PRK', '{root=+8, suffixes=[50]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/kp.png', '{KPW={name=North Korean won, symbol=₩}}', '{kor=Korean}', 1),
       (223, 'Greenland', 'GL', 'GRL', '{root=+2, suffixes=[99]}', 'Americas', 'North America',
        'https://flagcdn.com/w320/gl.png', '{DKK={name=krone, symbol=kr.}}', '{kal=Greenlandic}', 1),
       (224, 'Belgium', 'BE', 'BEL', '{root=+3, suffixes=[2]}', 'Europe', 'Western Europe',
        'https://flagcdn.com/w320/be.png', '{EUR={name=Euro, symbol=€}}', '{deu=German, fra=French, nld=Dutch}', 1),
       (225, 'Palau', 'PW', 'PLW', '{root=+6, suffixes=[80]}', 'Oceania', 'Micronesia',
        'https://flagcdn.com/w320/pw.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English, pau=Palauan}',
        1),
       (226, 'Norfolk Island', 'NF', 'NFK', '{root=+6, suffixes=[72]}', 'Oceania', 'Australia and New Zealand',
        'https://flagcdn.com/w320/nf.png', '{AUD={name=Australian dollar, symbol=$}}', '{eng=English, pih=Norfuk}', 1),
       (227, 'South Georgia', 'GS', 'SGS', '{root=+5, suffixes=[00]}', 'Antarctic', '',
        'https://flagcdn.com/w320/gs.png', '{GBP={name=British pound, symbol=£}}', '{eng=English}', 1),
       (228, 'Philippines', 'PH', 'PHL', '{root=+6, suffixes=[3]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/ph.png', '{PHP={name=Philippine peso, symbol=₱}}', '{eng=English, fil=Filipino}', 1),
       (229, 'India', 'IN', 'IND', '{root=+9, suffixes=[1]}', 'Asia', 'Southern Asia',
        'https://flagcdn.com/w320/in.png', '{INR={name=Indian rupee, symbol=₹}}', '{eng=English, hin=Hindi, tam=Tamil}',
        1),
       (230, 'Indonesia', 'ID', 'IDN', '{root=+6, suffixes=[2]}', 'Asia', 'South-Eastern Asia',
        'https://flagcdn.com/w320/id.png', '{IDR={name=Indonesian rupiah, symbol=Rp}}', '{ind=Indonesian}', 1),
       (231, 'United States Virgin Islands', 'VI', 'VIR', '{root=+1, suffixes=[340]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/vi.png', '{USD={name=United States dollar, symbol=$}}', '{eng=English}', 1),
       (232, 'Dominica', 'DM', 'DMA', '{root=+1, suffixes=[767]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/dm.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (233, 'Rwanda', 'RW', 'RWA', '{root=+2, suffixes=[50]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/rw.png', '{RWF={name=Rwandan franc, symbol=Fr}}',
        '{eng=English, fra=French, kin=Kinyarwanda}', 1),
       (234, 'Madagascar', 'MG', 'MDG', '{root=+2, suffixes=[61]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/mg.png', '{MGA={name=Malagasy ariary, symbol=Ar}}', '{fra=French, mlg=Malagasy}', 1),
       (235, 'Hong Kong', 'HK', 'HKG', '{root=+8, suffixes=[52]}', 'Asia', 'Eastern Asia',
        'https://flagcdn.com/w320/hk.png', '{HKD={name=Hong Kong dollar, symbol=$}}', '{eng=English, zho=Chinese}', 1),
       (236, 'Samoa', 'WS', 'WSM', '{root=+6, suffixes=[85]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/ws.png', '{WST={name=Samoan tālā, symbol=T}}', '{eng=English, smo=Samoan}', 1),
       (237, 'Faroe Islands', 'FO', 'FRO', '{root=+2, suffixes=[98]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/fo.png', '{DKK={name=Danish krone, symbol=kr}, FOK={name=Faroese króna, symbol=kr}}',
        '{dan=Danish, fao=Faroese}', 1),
       (238, 'Pitcairn Islands', 'PN', 'PCN', '{root=+6, suffixes=[4]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/pn.png', '{NZD={name=New Zealand dollar, symbol=$}}', '{eng=English}', 1),
       (239, 'Antigua and Barbuda', 'AG', 'ATG', '{root=+1, suffixes=[268]}', 'Americas', 'Caribbean',
        'https://flagcdn.com/w320/ag.png', '{XCD={name=Eastern Caribbean dollar, symbol=$}}', '{eng=English}', 1),
       (240, 'Åland Islands', 'AX', 'ALA', '{root=+3, suffixes=[5818]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/ax.png', '{EUR={name=Euro, symbol=€}}', '{swe=Swedish}', 1),
       (241, 'Sweden', 'SE', 'SWE', '{root=+4, suffixes=[6]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/se.png', '{SEK={name=Swedish krona, symbol=kr}}', '{swe=Swedish}', 1),
       (242, 'Mozambique', 'MZ', 'MOZ', '{root=+2, suffixes=[58]}', 'Africa', 'Eastern Africa',
        'https://flagcdn.com/w320/mz.png', '{MZN={name=Mozambican metical, symbol=MT}}', '{por=Portuguese}', 1),
       (243, 'Uzbekistan', 'UZ', 'UZB', '{root=+9, suffixes=[98]}', 'Asia', 'Central Asia',
        'https://flagcdn.com/w320/uz.png', '{UZS={name=Uzbekistani soʻm, symbol=so''m}}', '{rus=Russian, uzb=Uzbek}',
        1),
       (244, 'Antarctica', 'AQ', 'ATA', '{root=, suffixes=[]}', 'Antarctic', '', 'https://flagcdn.com/w320/aq.png',
        '{}', '{}', 1),
       (245, 'Wallis and Futuna', 'WF', 'WLF', '{root=+6, suffixes=[81]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/wf.png', '{XPF={name=CFP franc, symbol=₣}}', '{fra=French}', 1),
       (246, 'Ukraine', 'UA', 'UKR', '{root=+3, suffixes=[80]}', 'Europe', 'Eastern Europe',
        'https://flagcdn.com/w320/ua.png', '{UAH={name=Ukrainian hryvnia, symbol=₴}}', '{ukr=Ukrainian}', 1),
       (247, 'Belize', 'BZ', 'BLZ', '{root=+5, suffixes=[01]}', 'Americas', 'Central America',
        'https://flagcdn.com/w320/bz.png', '{BZD={name=Belize dollar, symbol=$}}',
        '{bjz=Belizean Creole, eng=English, spa=Spanish}', 1),
       (248, 'Saint Helena, Ascension and Tristan da Cunha', 'SH', 'SHN', '{root=+2, suffixes=[90, 47]}', 'Africa',
        'Western Africa', 'https://flagcdn.com/w320/sh.png',
        '{GBP={name=Pound sterling, symbol=£}, SHP={name=Saint Helena pound, symbol=£}}', '{eng=English}', 1),
       (249, 'Guernsey', 'GG', 'GGY', '{root=+4, suffixes=[4]}', 'Europe', 'Northern Europe',
        'https://flagcdn.com/w320/gg.png', '{GBP={name=British pound, symbol=£}, GGP={name=Guernsey pound, symbol=£}}',
        '{eng=English, fra=French, nfr=Guernésiais}', 1),
       (250, 'Tokelau', 'TK', 'TKL', '{root=+6, suffixes=[90]}', 'Oceania', 'Polynesia',
        'https://flagcdn.com/w320/tk.png', '{NZD={name=New Zealand dollar, symbol=$}}',
        '{eng=English, smo=Samoan, tkl=Tokelauan}', 1);

-- IT Product Based Sample Company
set
@companyId:=1;
insert into `company` (id, name, code, sample, countryId, active)
values (@companyId, 'Product based IT Services', '981101', true, 170, 1);

set
@Id:=@companyId*1000+1;

insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Product Manager', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Account Administrator', 'ADMIN', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'HR', 'HR', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Scrum Master', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Business Analyst', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Solution Architect', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Backend Developer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'UI/UX Designer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'QA Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Data Scientist', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Fullstack Developer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'DevOps Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Frontend Developer', 'TR', @companyId, 0, 0, 1);

insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Blocker', @companyId, 1, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Critical', @companyId, 2, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Highest', @companyId, 3, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'High', @companyId, 4, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Medium', @companyId, 5, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Low', @companyId, 6, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SATURDAY', @companyId, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SUNDAY', @companyId, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 480, 'Daily working minutes', 'DEFAULT', @companyId, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 420, 'Friday working minutes', 'WEEK_DAY', @companyId, null, 'FRIDAY', null, null, 1, 1);


-- IT Product Based Sample Company
set
@companyId:=2;
insert into `company` (id, name, code, sample, countryId, active)
values (@companyId, 'Project based IT Services', '981102', true, 170, 1);

set
@Id:=@companyId*1000+1;
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Project Manager', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Account Administrator', 'ADMIN', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'HR', 'HR', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Scrum Master', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Business Analyst', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Solution Architect', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Backend Developer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'UI/UX Designer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'QA Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Data Scientist', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Fullstack Developer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'DevOps Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Frontend Developer', 'TR', @companyId, 0, 0, 1);

insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Blocker', @companyId, 1, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Critical', @companyId, 2, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Highest', @companyId, 3, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'High', @companyId, 4, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Medium', @companyId, 5, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Low', @companyId, 6, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SATURDAY', @companyId, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SUNDAY', @companyId, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 480, 'Daily working minutes', 'DEFAULT', @companyId, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 420, 'Friday working minutes', 'WEEK_DAY', @companyId, null, 'FRIDAY', null, null, 1, 1);


-- Manufacturing Product Based Sample Company
set
@companyId:=3;
insert into `company` (id, name, code, sample, countryId, active)
values (@companyId, 'Manufacturing Industries', '981103', true, 170, 1);

set
@Id:=@companyId*1000+1;
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Project Manager', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Account Administrator', 'ADMIN', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'HR', 'HR', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Auditor', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Marketing Manager', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Business Analyst', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Supervisor', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Worker Type 1', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Worker Type 2', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Quality Checker', 'TR', @companyId, 0, 0, 1);

insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Blocker', @companyId, 1, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Critical', @companyId, 2, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Highest', @companyId, 3, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'High', @companyId, 4, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Medium', @companyId, 5, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Low', @companyId, 6, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SATURDAY', @companyId, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SUNDAY', @companyId, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 480, 'Daily working minutes', 'DEFAULT', @companyId, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 420, 'Friday working minutes', 'WEEK_DAY', @companyId, null, 'FRIDAY', null, null, 1, 1);


-- Manufacturing Product Based Sample Company
set
@companyId:=4;
insert into `company` (id, name, code, sample, countryId, active)
values (@companyId, 'Others', '981104', true, 170, 1);

set
set @Id:=@companyId*100+1;
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Project Manager', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Account Administrator', 'ADMIN', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'HR', 'HR', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Auditor', 'PM', @companyId, 0, 1, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Marketing Manager', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Business Analyst', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Engineer', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Supervisor', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Worker Type 1', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Worker Type 2', 'TR', @companyId, 0, 0, 1);
insert into `role` (id, name, code, companyId, required, systemRole, active)
values (@Id:=@Id+1, 'Quality Checker', 'TR', @companyId, 0, 0, 1);

insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Blocker', @companyId, 1, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Critical', @companyId, 2, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Highest', @companyId, 3, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'High', @companyId, 4, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Medium', @companyId, 5, 1);
insert into `priority` (id, name, companyId, priorityLevel, active)
values (@Id:=@Id+1, 'Low', @companyId, 6, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SATURDAY', @companyId, 1);

insert into `companyweekend` (id, day, companyId, active)
values (@Id:=@Id+1, 'SUNDAY', @companyId, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 480, 'Daily working minutes', 'DEFAULT', @companyId, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (@Id:=@Id+1, 420, 'Friday working minutes', 'WEEK_DAY', @companyId, null, 'FRIDAY', null, null, 1, 1);



update priority_seq
set next_val=10000;

update `companycalendar_seq`
set next_val=10000;

update companyweekend_seq
set next_val=10000;

update country_seq
set next_val=10000;

update company_seq
set next_val=1000;
update role_seq
set next_val=10000;
