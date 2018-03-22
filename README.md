# tiny-tomcat-2
这是一个关于<how tomcat works>的一个演化项目


- 2018-3-20日 完成第三章

- 2018-3-20日 完成第四章

1.Connector是一个守护线程，一个connector和container是一一绑定的

2.Connector维护了一个 minProcessors 到 maxProcessors数量之间的一个processors栈,processors栈中保存了所有空闲的processor

3.LifeCycle接口规定了一系列生命周期的行为，包括start(),stop()以及添加查询和删除监听器

4.Connector将自己收到的Socket发送给HttpProcessor处理

5.HttpProcessor内部利用wait和notifyAll实现了异步互相通知（主要涉及的函数为await和assign）

- 2018-3-21 完成第五章

1.Container和Connector是一对一的，在tomcat中一共有四种类型的container：engine，host，context，wrapper

     * engine：表示tomcat的整个servlet引擎；
     * host：表示包含有一个或多个context的虚拟机；
     * context：表示一个web应用。一个context中可以有多个wrapper；
     * wrapper：表示一个独立的servlet。

2.一个container可以有0个或多个低层级的子container

3.pipeline为container执行invoke的调用，pipeline中的valve表示的是这个pipeline需要执行的任务，类似filter，当一个valve结束的时候，pipeline就调用下一个valve
来处理，basic valve总是最后被调用的

4.pipeline通过创建valveContext来保证valve被调用一次，valveContext中最重要的函数是invokeNext

5.valve处理请求，通过invoke方法

6.mapper在各个container之中用来映射每一级container和下一级的container的映射关系
   
- 2018-3-21 完成第六章

Lifecycle接口，规定了生命周期，包括start、stop函数和对应的六个事件，通过实现这个接口，完成对组件的生命周期进行控制
    
    1. 一个请求进来了，空闲的processor会监听到这个socket,processor会回调注册在connector中的container来处理
    2. 在container中，会使用pipeline来逐个调用value
    3. container的请求交给其valve来实现，比如SimpleContextValve和SimpleWrapperValve
    4. simplePipeline的内部类实现了invoke的内部逻辑，首先按照配置的普通valve逐个执行，最后用basicValve来进入下一个container的首个valve
    5. 对于父container，mapper辅助其找到子需要的container
    6. wrapper的allocate方法只会初始化一次Servlet