### Build Variant 是什么

* Build Variant意味着一个完整apk的构成(源码,资源文件,编译类型,版本配置等)

* Build Variant = Build Type + Product Flavor 

* Build Type 编译类型一般分为两种 debug/release

* Product Flavor 应用版本根据项目需要配置(可以n个) 人人车默认test/multi/bbs/mipush四个版本

### Build Variant 能做什么

* Build Variant 生成满足各种业务需求的apk 即eclipse时代所说的多渠道版本配置

### Build Variant 怎么用? (google对资源(java源文件&res)合并支持到什么程度)

**Build Variant实际上就是一套完整的资源选取和合并机制，主要从以下三个方面进行灵活配置。**

* java源文件

* res&assets目录资源文件

* AndroidManifest中申明的android资源

#####java源文件

**根据Build Type & Product Flavor在project中创建对应的资源目录 一般创建目录布局如下：**
	
标准简单版本  
	
![标准简单版本](http://ww2.sinaimg.cn/mw690/780f2ae4jw1f46k549jssj20id0d7dgl.jpg)
	
标准复杂版本  
![标准复杂版本](http://ww2.sinaimg.cn/mw690/780f2ae4jw1f46k5aohc4j20dw05hq3e.jpg)
	
	1. 生成一个apk时，所有Build Variants资源目录的java文件都会被自动选取并进行代码合并，编译。
	
	2. src/main中一般放置Build Variants公用的java文件
	
	3. 不同Build Variants中可放置包名和类名完全相同的java文件，但是允许各自java文件有不一样的实现。此种类型的java文件不允许在main有实现版本。
	
	4. 上面描述的java文件也包括android Activity类文件。
	
	5. [demo演示](https://github.com/tangjie1989/ProductFlavorsTest.git)

######res&assets目录资源文件

	1. 所有res&assets目录资源文件采用覆盖式方法，最终选取一套app所使用的资源。

	2. 目录资源文件覆盖优先级 Build Type > Product Flavor > main sourceSet

######AndroidManifest中android资源

	1. 生成一个apk时，Build Variants资源目录的AndroidManifest文件都会被自动选取并进行合并最终生成一个AndroidManifest文件. 允许Product Flavors/Build Types所对应的资源目录中的AndroidManifest文件有各自不同的实现(components and/or permissions)

	2. AndroidManifest中的标签合并有一套合并原则简称Manifest Merger。详情参见[Manifest Merger Guide](http://tools.android.com/tech-docs/new-build-system/user-guide/manifest-merger)

####使用BuildConfig进行参数配置

生成编译预置数据，同一份代码可根据BuildConfig类中配置的数据做不同的界面展示或其他的业务需求。BuildConfig可以和Build Variants一起配合使用。

####当前app中已用到的Build Variants

**人人车bbs版本 需求是根据人人车主app最新代码生成一个用bbs论坛页替换卖车页的版本。实现步骤如下：**

	1. 建立mipush flavor
	
	2. 不同flavor使用BuildConfig 配置一个是否显示bbs的变量
	
	3. 代码中使用BuildConfig配置的变量做判断，控制显示内容。
	
	**理论上应该是用flavor书写卖车页位置不同的页面展现，不应该依赖BuildConfig变量在同一份代码中做配置。但是需求是这个替换只是临时的测试，之后版本中卖车页位置只存在一种展示，所以采取了这种改动变化最小的方式。**
	
**人人车mipush测试包 需求是根据人人车主app最新代码生成一个特殊mipush渠道的版本。实现步骤如下：**

	1. 建立mipush flavor
	
	2. 不同flavor使用BuildConfig 配置不同的mipushId和mipushKey 以及应用包名
	
	3. mipush资源目录下的strings.xml自动覆盖main目录下的strings.xml中的相同标签
	
	3. 不同flavor运用Manifest Merger对AndroidManifest文件中 mipush相关资源进行合并

####Note：激活Build Variants

	1. 当进行多Build Variants开发时，必须手动选择对应的Build Variants，才能对该Build Variants下的资源目录和文件进行适当的更新。

	2. studio 左下角有个Build Variants panel栏目，选择一个Build Variants后，相应的Build Variants资源目录会自动激活

	3. 只有当前选中的Build Variants 所对应的资源目录(src/java)是蓝色的。其他的src/java目录都是默认土色，除了main目录下的src/java。

####官方指导文章

[Gradle Plugin User Guide](http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Build-Variants)

[Manifest Merger](http://tools.android.com/tech-docs/new-build-system/user-guide/manifest-merger)

[Configure Your Build](https://developer.android.com/studio/build/index.html)

######其他参考文章
[英文](http://blog.robustastudio.com/mobile-development/android/building-multiple-editions-of-android-app-gradle/)

[中文](http://ghui.me/post/2015/03/create-several-variants/)


