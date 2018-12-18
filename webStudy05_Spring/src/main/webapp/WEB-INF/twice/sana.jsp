<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="people_info section people_spc" id="people_info_z"
		data-dss-logarea="x29">
		<div class="section_head">
			<h2>인물 정보</h2>
		</div>
		<div class="cont_noline" style="z-index: 10">
			<div class="profile_wrap">
				<div class="big_thumb">
					<img
						src="https://search.pstatic.net/common?type=a&amp;size=120x150&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201811%2F2018110613312516.jpg"
						alt="가수 사나 이미지" width="120" height="150"
						onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"><a
						nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.edit&amp;i='+'1800000D_0000002AA35E');"
						class="btn_profile_update" href="http://myprofile.naver.com"
						target="_blank"><span class="blind">가수 사나</span>내 프로필 수정</a>
				</div>
				<dl class="detail_profile">
					<dd class="name">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.namemusic&amp;i='+'1800000D_0000002AA35E');"
							href="https://music.naver.com/artist/home.nhn?artistId=460475"
							target="_blank"><strong>사나</strong></a> <span>가수</span>
					</dd>
					<dt>출생</dt>
					<dd>
						<span> 1996년 12월 29일</span>
					</dd>
					<dt>소속그룹</dt>
					<dd>
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.group&amp;i='+'1800000D_0000002AA35E');"
							href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjky&amp;pkid=1&amp;os=2794338&amp;query=TWICE">TWICE</a>
					</dd>
					<dt>소속사</dt>
					<dd>
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.management&amp;i='+'1800000D_0000002AA35E');"
							href="?where=nexearch&amp;sm=tab_etc&amp;query=JYP%EC%97%94%ED%84%B0%ED%85%8C%EC%9D%B8%EB%A8%BC%ED%8A%B8">JYP엔터테인먼트</a>
					</dd>
					<dt>관련정보</dt>
					<dd>
						네이버[폴라] - <a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.relatedinfo&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
							href="http://www.pholar.co/album/908403/77135" target="_blank">사나
							X 진수경 스페셜 폴라 앨범</a>
					</dd>
					<dt>사이트</dt>
					<dd>
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.vlive&amp;i='+'1800000D_0000002AA35E');"
							href="http://www.vlive.tv/channels/EDBF" target="_blank">V
							LIVE</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="go_relate">
			<div class="offer_more2">
				<div class="confirm">
					<em>정보확인 <time datetime="2018.11.07.">2018.11.07.</time></em><a
						nocr="" href="javascript:;" id="more_div_btn"><img
						src="https://ssl.pstatic.net/sstatic/search/img/ico_help.gif"
						width="12" height="12" alt="네이버 인물정보 도움말"></a>
				</div>
				<div style="left: -65px; width: 286px; visibility: hidden;"
					class="layer_tooltip_wrap" id="tooltip_layer">
					<div class="layer_tooltip2">
						<div class="content">
							<p>
								네이버는 당사자 또는 당사자와 관련된 기관<br>에서 공식적으로 제공한 정보 및 언론이나 외부에<br>공개된
								정보를 프로필로 제공하고 있습니다.
							</p>
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.imaginfmy&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								href="https://myprofile.naver.com/Main.nhn" class="request"
								target="_blank">본인 및 소속사의 인물정보 수정요청</a> <a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.imaginfcs&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								href="https://help.naver.com/support/alias/search/people/people_0.naver"
								class="helpdesk" target="_blank">네이버 고객센터</a><a nocr=""
								onclick="fnLayerClose();return false;" href="javascript:;"
								class="closelayer C"><img
								src="https://ssl.pstatic.net/sstatic/search/images11/btn_layer_close.gif"
								width="10" height="9" alt="네이버 인물정보 도움말 닫기"></a> <span
								class="arw detail C"></span>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$Fn(fnLayerOpen).attach($('more_div_btn'), "click");
				function fnLayerOpen(e) {
					$('tooltip_layer').style.visibility = "visible";
					goOtherTCR(this,
							'a=nco_x29*3.imaginf&r=1&i=1800000D_0000002AA35E');
				}
				function fnLayerClose(e) {
					$('tooltip_layer').style.visibility = "hidden";
					$('more_div_btn').focus();
					goOtherTCR(this,
							'a=nco_x29*3.imaginfclose&r=1&i=1800000D_0000002AA35E');
				}
			</script>
			<a nocr=""
				onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.profiledetail&amp;i='+'1800000D_0000002AA35E');"
				href="https://people.search.naver.com/search.naver?where=nexearch&amp;query=%EC%82%AC%EB%82%98&amp;sm=tab_etc&amp;ie=utf8&amp;key=PeopleService&amp;os=2794334"
				class="btn_txt_more" target="_blank"><span class="blind">가수
					사나</span>프로필 더보기</a>
		</div>
		<script type="text/javascript">
			var __sDefaultPeopleTab = "네이버TV";
		</script>
		<div class="people_type" id="tx_ca_people_workall">
			<div class="texttab_type">
				<ul>
					<li id="tx_ca_people_tvcast_tab" class="select"><a nocr="1"
						href="#" class="pg_btn">네이버TV</a></li>
					<li id="tx_ca_people_album_tab"><a nocr="1" href="#"
						class="pg_btn">앨범</a></li>
					<li id="tx_ca_people_onair_tab"><a nocr="1" href="#"
						class="pg_btn">방송</a></li>
					<li id="tx_ca_people_play_tab"><a nocr="1" href="#"
						class="pg_btn">공연</a></li>
				</ul>
			</div>
			<div class="lst_cont tvcast " style="display: block;">
				<h3 class="blind">네이버TV</h3>
				<ul>
					<li>
						<div class="thumb_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4499265"> <img
								src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20181112_202%2FGhaW0_1541989300133EOsFK_JPEG%2F1541989296028.jpg"
								alt="TWICE TV &amp;quot;YES or YES&amp;quot; EP.01" width="128"
								height="72"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <span class="ico_play"></span> <span
								class="runtime"><span class="bg">재생시간</span><em>12분
										04초</em></span> <span class="img_bd"></span>
							</a>
						</div>
						<div class="txt_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4499265"
								class="cast_txt">TWICE TV "YES or YES" E...</a> <span
								class="day_txt">23시간 전</span>
						</div>
					</li>
					<li>
						<div class="thumb_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4494570"> <img
								src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20181111_110%2FWd07t_1541925101548TR74h_JPEG%2FVD44009729_w1280.jpg"
								alt="사랑스러운 답정너 ‘트와이스’의 러블리한 퍼포먼스 ‘YES or YES’" width="128"
								height="72"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <span class="ico_play"></span> <span
								class="runtime"><span class="bg">재생시간</span><em>3분
										59초</em></span> <span class="img_bd"></span>
							</a>
						</div>
						<div class="txt_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4494570"
								class="cast_txt">사랑스러운 답정너 ‘트와이스’의 러블리한 ...</a> <span
								class="day_txt">2018.11.11.</span>
						</div>
					</li>
					<li>
						<div class="thumb_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4494467"> <img
								src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20181111_227%2FTIJoy_1541924210038I1cJb_JPEG%2FVD48284023_w1280.jpg"
								alt="꿈과 사랑을 향해 달려가는 ‘트와이스’의 무대 ‘BDZ’" width="128" height="72"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <span class="ico_play"></span> <span
								class="runtime"><span class="bg">재생시간</span><em>1분
										34초</em></span> <span class="img_bd"></span>
							</a>
						</div>
						<div class="txt_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4494467"
								class="cast_txt">꿈과 사랑을 향해 달려가는 ‘트와이스’의 ...</a> <span
								class="day_txt">2018.11.11.</span>
						</div>
					</li>
					<li>
						<div class="thumb_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4485270"> <img
								src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20181109_267%2F3QHlA_1541768579390ptnNx_PNG%2FhV1Zb532NAsz.png"
								alt="같이 소풍갈까요? Yes Or Yes! 트와이스" width="128" height="72"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <span class="ico_play"></span> <span
								class="runtime"><span class="bg">재생시간</span><em>5분
										21초</em></span> <span class="img_bd"></span>
							</a>
						</div>
						<div class="txt_box">
							<a nocr=""
								onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
								target="_blank" href="https://tv.naver.com/v/4485270"
								class="cast_txt">같이 소풍갈까요? Yes Or Yes! 트...</a> <span
								class="day_txt">2018.11.09.</span>
						</div>
					</li>
				</ul>
				<a nocr=""
					onclick="return goOtherCR(this, 'a=nco_x29*3.tvcmore&amp;r=1&amp;i=1800000D_0000002AA35E&amp;u=' + urlencode(this.href));"
					target="_blank" class="more_view"
					href="http://tv.naver.com/search/clip?query=사나&amp;sort=date"><span
					class="blind">네이버TV </span>더보기</a>
			</div>
			<div class="lst_cont albumlist" id="tx_ca_people_album_content"
				style="display: none;">
				<h3 class="blind">앨범</h3>
				<ul>
					<li>
						<div class="thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumimage&amp;i='+'1800000D_0000002AA35E');"
								href="https://music.naver.com/album/index.nhn?albumId=2581382"
								target="_blank"><img
								src="https://search.pstatic.net/common?type=o&amp;size=78x71&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fmusicmeta-phinf.pstatic.net%2Falbum%2F002%2F581%2F2581382.jpg%3Ftype%3Dr300Fll%26v%3D20181105175903"
								alt="YES or YES" width="78" height="71"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <img height="75" width="84" class="bg_album"
								alt=""
								src="https://ssl.pstatic.net/sstatic/search/images11/blank.gif">
							</a>
						</div>
						<dl>
							<dt>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="https://music.naver.com/album/index.nhn?albumId=2581382"
									target="_blank">YES or YES</a>
							</dt>
							<dd title="TWICE(트와이스)">TWICE(트와이...</dd>
							<dd>2018.11.05</dd>
						</dl>
					</li>
					<li>
						<div class="thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumimage&amp;i='+'1800000D_0000002AA35E');"
								href="https://music.naver.com/album/index.nhn?albumId=2522032"
								target="_blank"><img
								src="https://search.pstatic.net/common?type=o&amp;size=78x71&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fmusicmeta-phinf.pstatic.net%2Falbum%2F002%2F522%2F2522032.jpg%3Ftype%3Dr300Fll%26v%3D20180912175909"
								alt="BDZ" width="78" height="71"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <img height="75" width="84" class="bg_album"
								alt=""
								src="https://ssl.pstatic.net/sstatic/search/images11/blank.gif">
							</a>
						</div>
						<dl>
							<dt>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="https://music.naver.com/album/index.nhn?albumId=2522032"
									target="_blank">BDZ</a>
							</dt>
							<dd title="TWICE(트와이스)">TWICE(트와이...</dd>
							<dd>2018.09.12</dd>
						</dl>
					</li>
					<li>
						<div class="thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumimage&amp;i='+'1800000D_0000002AA35E');"
								href="https://music.naver.com/album/index.nhn?albumId=2480898"
								target="_blank"><img
								src="https://search.pstatic.net/common?type=o&amp;size=78x71&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fmusicmeta-phinf.pstatic.net%2Falbum%2F002%2F480%2F2480898.jpg%3Ftype%3Dr300Fll%26v%3D20180717160329"
								alt="Summer Nights" width="78" height="71"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""> <img height="75" width="84" class="bg_album"
								alt=""
								src="https://ssl.pstatic.net/sstatic/search/images11/blank.gif">
							</a>
						</div>
						<dl>
							<dt>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.albumtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="https://music.naver.com/album/index.nhn?albumId=2480898"
									target="_blank" title="Summer Nights">Summer Ni...</a>
							</dt>
							<dd title="TWICE(트와이스)">TWICE(트와이...</dd>
							<dd>2018.07.09</dd>
						</dl>
					</li>
				</ul>
				<a nocr=""
					onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.wtmusicmore&amp;i='+'1800000D_0000002AA35E');"
					class="more_view"
					href="https://music.naver.com/artist/album.nhn?artistId=460475"
					target="_blank"><span class="blind">앨범 </span>더보기</a>
			</div>
			<div class="lst_cont" id="tx_ca_people_onair_content"
				style="display: none;">
				<h3 class="blind">방송</h3>
				<ul>
					<li>
						<div class="big_thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.dramaimage&amp;i='+'1800000D_0000002AA35E');"
								href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjkw&amp;x_csa=%7B%22pkid%22%3A%2257%22%2C%22os%22%3A%222343346%22%7D&amp;pkid=57&amp;os=2343346&amp;query=%EB%A7%88%EC%9D%B4%EB%A6%AC%ED%8B%80%ED%85%94%EB%A0%88%EB%B9%84%EC%A0%84"><img
								src="https://search.pstatic.net/common?type=o&amp;size=58x82&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F57%2F34%2F33%2F46%2F57_2343346_poster_image_1427777554125.jpg"
								alt="마이 리틀 텔레비전" width="58" height="82"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""></a>
						</div>
						<dl>
							<dd>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.dramatitle&amp;i='+'1800000D_0000002AA35E');"
									href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjkw&amp;x_csa=%7B%22pkid%22%3A%2257%22%2C%22os%22%3A%222343346%22%7D&amp;pkid=57&amp;os=2343346&amp;query=%EB%A7%88%EC%9D%B4%EB%A6%AC%ED%8B%80%ED%85%94%EB%A0%88%EB%B9%84%EC%A0%84">마이
									리틀 텔레비전</a>
							</dd>
							<dd>2015</dd>
						</dl>
					</li>
					<li>
						<div class="big_thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.dramaimage&amp;i='+'1800000D_0000002AA35E');"
								href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjkw&amp;x_csa=%7B%22pkid%22%3A%2257%22%2C%22os%22%3A%222408530%22%7D&amp;pkid=57&amp;os=2408530&amp;query=%EC%8B%9D%EC%8A%A4%ED%8B%B4"><img
								src="https://search.pstatic.net/common?type=o&amp;size=58x82&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F57%2F40%2F85%2F30%2F57_2408530_poster_image_1428640718534.jpg"
								alt="SIXTEEN" width="58" height="82"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""></a>
						</div>
						<dl>
							<dd>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.dramatitle&amp;i='+'1800000D_0000002AA35E');"
									href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjkw&amp;x_csa=%7B%22pkid%22%3A%2257%22%2C%22os%22%3A%222408530%22%7D&amp;pkid=57&amp;os=2408530&amp;query=%EC%8B%9D%EC%8A%A4%ED%8B%B4">SIXTEEN</a>
							</dd>
							<dd>2015</dd>
						</dl>
					</li>
				</ul>
			</div>
			<div class="lst_cont" id="tx_ca_people_play_content"
				style="display: none;">
				<h3 class="blind">공연</h3>
				<ul>
					<li>
						<div class="big_thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playimage&amp;i='+'1800000D_0000002AA35E');"
								href="https://www.facebook.com/JYPETWICE/photos/a.1597898153825293/2250773045204464/?type=3&amp;theater"
								target="_blank"><img
								src="https://search.pstatic.net/common?type=mfullfill&amp;size=66x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F4209_000_1%2F20180928173042187_A5QQBWTTU.jpg%2F%25ED%258A%25B8%25EC%2599%2580.jpg%3Ftype%3Dr160"
								alt="TWICE FANMEETING ONCE HALLOWEEN" width="66" height="80"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""></a>
						</div>
						<dl>
							<dd>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="https://www.facebook.com/JYPETWICE/photos/a.1597898153825293/2250773045204464/?type=3&amp;theater"
									target="_blank" title="TWICE FANMEETING ONCE HALLOWEEN">TWICE
									FAN...</a>
							</dd>
							<dt>연세대학교 노천극장</dt>
							<dd>2018.10.28</dd>
						</dl>
					</li>
					<li>
						<div class="big_thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playimage&amp;i='+'1800000D_0000002AA35E');"
								href="http://www.soribada.com/award/2018/summary"
								target="_blank"><img
								src="https://search.pstatic.net/common?type=mfullfill&amp;size=66x80&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fdbscthumb.phinf.naver.net%2F4209_000_5%2F20180810180936231_WZ199QBFT.jpg%2F%25EC%2586%258C%25EB%25A6%25AC.jpg%3Ftype%3Dr160"
								alt="2018 소리바다 베스트 케이뮤직 어워즈" width="66" height="80"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""></a>
						</div>
						<dl>
							<dd>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="http://www.soribada.com/award/2018/summary"
									target="_blank" title="2018 소리바다 베스트 케이뮤직 어워즈">2018 소리바다...</a>
							</dd>
							<dt>올림픽공원 체조경기장</dt>
							<dd>2018.08.30</dd>
						</dl>
					</li>
					<li class="last">
						<div class="big_thumb">
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playimage&amp;i='+'1800000D_0000002AA35E');"
								href="http://koreamusicfestival.org/kmf2018.php" target="_blank"><img
								src="https://search.pstatic.net/common?type=mfullfill&amp;size=66x80&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fdbscthumb.phinf.naver.net%2F4209_000_4%2F20180716155445547_4WV1III6O.jpg%2F%25EC%25BC%2580%25EC%259D%25B4.jpg%3Ftype%3Dr160"
								alt="케이스타 2018 코리아 뮤직 페스티벌" width="66" height="80"
								onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
								class=""></a>
						</div>
						<dl>
							<dd>
								<a nocr=""
									onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_2794334+'.playtitle&amp;i='+'1800000D_0000002AA35E');"
									class="sh_people_title sh_people_link"
									href="http://koreamusicfestival.org/kmf2018.php"
									target="_blank" title="케이스타 2018 코리아 뮤직 페스티벌">케이스타 2018...</a>
							</dd>
							<dt>고척스카이돔</dt>
							<dd>2018.08.01 ~ 2018.08.02</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			nhn.peopleTabView = function(a) {
				var d = a;
				for (var b = 0, c = d.length; b < c; b++) {
					new nhn.peopleTabView.Tab(d[b]);
				}
			};
			nhn.peopleTabView.Tab = $Class({
				$init : function(b) {
					var a = $$.getSingle(">div.texttab_type>ul", b);
					this.aTab = $$(">li>a", a);
					this.aLayer = $$(">div.lst_cont", b);
					this.htClickArea = {
						"영화" : "nco_x29*3.wtmovie",
						"앨범" : "nco_x29*3.wtmusic",
						"선거이력" : "nco_x29*3.telection",
						"경기성적" : "nco_x29*3.wtsports",
						"공연" : "nco_x29*3.wtplay",
						"주요근황" : "nco_x29*3.wtrecent",
						"방송" : "nco_x29*3.wtonair",
						"도서" : "nco_x29*3.wtbook",
						"웹툰" : "nco_x29*3.wtwebtoon",
						"미술작품" : "nco_x29*3.wtarts",
						"칼럼" : "nco_x29*3.wtcolumn",
						"네이버TV" : "nco_x29*3.tvc",
						"그라폴리오" : "nco_x29*3.gra",
						"아트" : "nco_x29*3.art",
						"명언" : "nco_x29*3.quotation",
						"지식iN" : "nco_x29*3.wtkin",
						"전시" : "nco_x29*3.exh"
					};
					$Fn(this.click, this).attach(a, "click");
					this.initTab();
				},
				click : function(a) {
					a.stop();
					var d = a.element;
					if (d.tagName == "A") {
						for (var c = 0, f = this.aTab.length; c < f; c++) {
							var b = $Element(this.aTab[c]).parent();
							if (this.aTab[c] == d) {
								if (!b.hasClass("select")) {
									b.addClass("select");
								}
								$Element(this.aLayer[c]).show();
								nhn.common.ellipsis(this.aLayer[c]);
							} else {
								b.removeClass("select");
								$Element(this.aLayer[c]).hide();
							}
						}
						try {
							for ( var c in this.htClickArea) {
								if (d.innerHTML.match(new RegExp(c))) {
									goOtherTCR(
											this,
											("a=" + this.htClickArea[c]
													+ "&r=1&i=" + txinfo_people_gdid));
									break;
								}
							}
						} catch (g) {
						}
					}
				},
				initTab : function() {
					try {
						if (__sDefaultPeopleTab && __sDefaultPeopleTab != "") {
							for (var b = 0, c = this.aTab.length; b < c; b++) {
								var a = $Element(this.aTab[b]).parent();
								if ($Element(this.aTab[b]).html().match(
										__sDefaultPeopleTab)) {
									if (!a.hasClass("select")) {
										a.addClass("select");
									}
									$Element(this.aLayer[b]).show();
								} else {
									a.removeClass("select");
									$Element(this.aLayer[b]).hide();
								}
							}
						}
					} catch (d) {
					}
				}
			});
			if ($("tx_ca_people_workall")) {
				var aPeopleType = [ $("tx_ca_people_workall") ];
				nhn.peopleTabView(aPeopleType);
			} else {
				$Fn(function() {
					var a = [ $("tx_ca_people_workall") ];
					nhn.peopleTabView(a);
				}).attach(window, "load");
			}
			var elTxCaPeopleKinContent = $("tx_ca_people_kin_content");
			if (elTxCaPeopleKinContent)
				nhn.common.ellipsis(elTxCaPeopleKinContent);
		</script>
		<script type="text/javascript">
			function tx_noimage_thumb(img, className, noimage) {
				img.onerror = null;
				if (noimage) {
					img.src = noimage;
				} else {
					img.src = "https://ssl.pstatic.net/sstatic/search/images11/blank.gif";
				}
				if (img.alt == '') {
					img.alt = "이미지 준비중";
				} else {
					img.alt = img.alt + " 준비중";
				}
				if (className) {
					img.className = className;
				}
			}
			function tx_set_href(a) {
				if (a.href)
					a.href = a.href.replace(/&query=[^&]*/g, "&query="
							+ headerfooter_query_encoded_utf8);
			}
		</script>
	</div>

</body>
</html>