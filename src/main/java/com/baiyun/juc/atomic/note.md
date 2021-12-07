### 总结
`版本：`jdk1.5之后提供了java.util.concurrent.atomic包

`作用：`方便在多线程环境、无锁的情况下保证原子操作

`内部核心：`atomic包中的内部实现并不是简单的Synchronized，而是使用了更为高效的CAS（compare and swap）+ volatile和native方法（同步的工作更多的交给了硬件），从而避免了使用Synchronized的高开销，提高执行效率

`问题：`CAS操作中的ABA问题（通过版本号解决）

https://vimsky.com/examples/list/java-class-page-1.html