-----AOP实现

--模块
    --aop_tools
        aop实现的工具库，现有功能：
            1.通过注解实现主线程和子线程的切换
            2.日志注解，完成方法的说明

    --app
        测试模块

--aop实现方式：
    使用aspectJ和rxjava结合

--在项目中引入：
引入：
官方引入：
Step 1: Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
  	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.hanlonglinandroidstudys:AopHelper:v1.0'
	}

此外，还需添加：
    1.在项目root下的gradle中添加：
    
         dependencies {
                ....
                classpath 'org.aspectj:aspectjtools:1.9.2'
                ....
            }

    2.在自己的moudle中的gradle中最外层添加：
    
        import org.aspectj.tools.ajc.Main
        project.android.applicationVariants.all { variant ->
            //在编译后 增加行为
            JavaCompile javaCompile = variant.javaCompile
            javaCompile.doLast {
                String[] args = ["-showWeaveInfo",
                                 "-1.8",
                                 "-inpath", javaCompile.destinationDir.toString(),
                                 "-aspectpath", javaCompile.classpath.asPath,
                                 "-d", javaCompile.destinationDir.toString(),
                                 "-classpath", javaCompile.classpath.asPath,
                                 "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
                new Main().runMain(args, false);
            }
        }

--使用例子：
         //主线程方法
         @Main
         private void doMain() {
             Log.e("TAG", "doMain(),线程：" + Thread.currentThread().getName());
         }
         //异步线程方法
         @Async
         private void doAsync() {
             Log.e("TAG", "doAsync(),线程：" + Thread.currentThread().getName());
         }
         //打印日志
         @Logger(Log.ERROR)
         private String dolog(String msg, int count) {
             Log.e("TAG", "dolog()");
             return "结束";
         }

