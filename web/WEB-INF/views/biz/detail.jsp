<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="../../../css/header.css" rel="stylesheet" type="text/css"/>
    <link href="../../../css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="https://vjs.zencdn.net/7.4.1/video-js.css" rel="stylesheet">
    <script src='https://vjs.zencdn.net/7.4.1/video.js'></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-contrib-hls/5.15.0/videojs-contrib-hls.min.js" type="text/javascript"></script>

    <script>
        // videojs 简单使用
        var myVideo = videojs('myVideo', {
            bigPlayButton: true,
            textTrackDisplay: false,
            posterImage: false,
            errorDisplay: false,
        })
        myVideo.play()
        var changeVideo = function (vdoSrc) {
            if (/\.m3u8$/.test(vdoSrc)) {
                myVideo.src({
                    src: vdoSrc,
                    type: 'application/x-mpegURL'
                })
            } else {
                myVideo.src(vdoSrc)
            }
            myVideo.load();
            myVideo.play();
        }
        var src = 'http://1252093142.vod2.myqcloud.com/4704461fvodcq1252093142/f865d8a05285890787810776469/playlist.f3.m3u8';
        document.querySelector('.qiehuan').addEventListener('click', function () {
            changeVideo(src);
        })
    </script>
    <style>
        .video-js .vjs-tech {position: relative !important;}
    </style>
</head>

<body>

    <c:import url="header.jsp"></c:import>

    <div class="body">
    <c:set var="movie" value="${movie}"></c:set>
   <div class="movie-name">
        <h2>${movie.movieName}</h2>
    </div>
    <div class="info">
        <div class="info-img">
            <img src="${movie.movieImgUrl}"style="float:left;width:270px;height:400px;border-radius: 8px;" alt="">
        </div>
        <div class="info-list">
            <p>导演：${movie.movieDirector}</p>
            <p>主演：${movie.movieStar}</p>
            <p>类型：${movie.movieType}</p>
            <p>制片国家/地区：${movie.movieName}</p>
            <p>语言：${movie.movieArea}</p>
            <p>年份：${movie.movieYear}</p>
            <p>评分：${movie.movieScore}</p>
        </div>
    </div>
    <div class="intro">
        <p class="title">剧情简介</p>
        <p>&nbsp;${movie.movieSummary}</p>
    </div>

    <div class="play">在线播放</div>
        <div class="vedio">
            <video id="myVideo" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="auto" data-setup='{}' style='width: 100%;height: auto'>
                <source id="source" src="${movie.moviePlayUrl}" type="application/x-mpegURL"></source>
            </video>
        </div>

        <div class="Comment">
            <div class="comment-title">影片评论</div>
            <form action="/comment.do?movieId=${movie.movieId}" method="post">
                <textarea rows="" cols="" name="input-comment"></textarea>
                <input type="submit" value="发表影评">
            </form>
        </div>


        <c:forEach items="${comments}" var="comment">
        <div class="conmentUnit">
                <div class="commentHeader">
                    <sapn class="userName">${comment.userName}</sapn>
                    <span>&nbsp;&nbsp;看过&nbsp;&nbsp;</span>
                    <spqn class="createTime">${comment.commentDataTime}</spqn>
                </div>
                <div class="content">
                        ${comment.comment}
                </div>
        </div>
        </c:forEach>
        <p>><span class="more">更多短评</span></p>
        <div class="bottom"></div>



    </div>
</body>
</html>
