# tiny-tomcat-2
这是一个关于<how tomcat works>的一个演化项目


- 2018-3-20日 完成第三章部分

- 2018-3-20日 完成第四章

1.Connector是一个守护线程
2.Connector维护了一个 minProcessors 到 maxProcessors数量之间的一个processors栈,processors栈中保存了所有空闲的processor
3.LifeCycle接口规定了一系列生命周期的行为，包括start(),stop()以及添加查询和删除监听器
4.Connector将自己收到的Socket发送给HttpProcessor处理
5.HttpProcessor内部利用wait和notifyall实现了异步互相通知（主要涉及的函数为await和assign）
