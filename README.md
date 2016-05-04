这是一个用[gank.io](http://gank.io) api写的app

# 这个用到的新知识
- [RxJava](http://gank.io/post/560e15be2dca930e00da1083)
- [Retrofit](https://github.com/square/retrofit)
- MVP模式

# 应用介绍
- 每日推荐</br>
这个类似与One，每天推荐一张妹子图，一段视频，一些Android，iOS，前端的博客或者开源库，总的来说还挺不错的。</br>
![](https://github.com/fallblank/Gank.io/blob/master/ScreenShots/1.JPG)

- 分类导航
我还集中的抓取了以往发布的技术推荐，然后进行分类展示</br>
![](https://github.com/fallblank/Gank.io/blob/master/ScreenShots/3.JPG)

- 福利
如图，不解释（捂脸::>_<::）</br>
![](https://github.com/fallblank/Gank.io/blob/master/ScreenShots/4.JPG)

# 目前存在的问题
1. 图片的放大查看没实现完全（预想中应该是类似知乎的透明背景）
2. 加载时的进度显示没处理好，没有失败提醒
3. fragment每次都得重建，没有保存实例的解决方案，这也可能是我的MVP模式实现不太好
4. 第三方登录进行收藏等操作没实现，这需要在我的服务器上写一个Python用户管理系统，但现在不想写python...
5. 界面没动画，看起来死板。
6. 其他很多

# update -01 2016-05-04
- 优化图片查看，实现类似知乎的图片查看效果, 
[实现思路](http://www.cnblogs.com/fallblank/p/5459336.html)</br>

<p>
	<img style="margin: 5px" src="https://github.com/fallblank/Gank.io/blob/master/ScreenShots/5.png" width="281" height="500">
</p>

