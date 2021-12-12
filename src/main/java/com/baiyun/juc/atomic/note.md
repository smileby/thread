### 总结
`版本：`jdk1.5之后提供了java.util.concurrent.atomic包

`作用：`方便在多线程环境、无锁的情况下保证原子操作

`内部核心：`atomic包中的内部实现并不是简单的Synchronized，而是使用了更为高效的CAS（compare and swap）+ volatile和native方法（同步的工作更多的交给了硬件），从而避免了使用Synchronized的高开销，提高执行效率

`问题：`CAS操作中的ABA问题（通过版本号解决）

https://vimsky.com/examples/list/java-class-page-1.html

### CAS
cas 全称 `Compare and swap` , 实现比较并替换，是实现并发常用到的技术，在java并发包中有很多类用到了CAS技术，
其核心思想如下

执行函数
 ```$xslt
    CAS(V, E, N)
    C: 内存地址
    E: 预期值
    N: 期望更新的值（期望值）
```
当CAS指令执行时，当内存地址V和预期值E相等时，将内存地址V改为N，否则什么都不做，整个比较和替换的操作是一个原子操作。
 