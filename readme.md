## 我该怎么编写接口？
接口方法中将不再与JsonResponse相耦合，方法返回值为具体对象，不出现任何与业务无关的框架代码
```
public ObjectVO foo(){
    ObjectVo objectVo = service.get();
    ...
    return objectVo;
}
```

## 我该怎么返回错误信息？
接口方法如果要通知外部调用者此接口出现异常，则使用`throw`关键字抛出`业务异常`，值得注意的是我们抛出的业务异常都是直接或者间接的继承`AbstractBusinessException`类，抛出异常不需要捕获，由框架自行处理并返回错误信息，Service/DAO同理
```
public ObjectVO foo(){
    ObjectVo objectVo = service.get();
    if (objectVo == null) {
        throw new DataNotFoundException();
    }
    ...
    return objectVo;
}
```

## 什么是业务异常
业务异常通常是指由我们业务层面决定是否属于错误行为、无效请求、基础服务异常，例如：
- 请求的参数校验不通过
- 数据库找不到数据
- 非法的服务订单
- 行程状态不合法
- ...

系统异常常规来说是指由框架BUG引发、JDK内部方法抛出，例如
- 数据库挂了
- Redis连不上
- 空指针异常
- 非法获取锁异常
- 某个Bean尚未注册到IOC
- 内存溢出
- ...

## 我该怎么定义属于自己的业务异常
如果内置业务异常不能满足你的需求，很有可能会自行建立一套错误信息处理流程，甚至是不符合团队规范。
定义自己的业务异常有两种方式：

一、创建一个异常类且继承`AbstractBusinessException`，在类中编写此异常响应的状态码、异常提示消息，继承该类可以不必自行创建异常处理器；

二、创建一个异常类且继承`RuntimeException`，然后创建该异常类的异常处理器且实现`BusinessExceptionHandler`。

## 异常处理器是如何工作
在框架中定义了一个全局异常处理器`GlobalExceptionHandler`，此处理器中`handleException`方法将会处理接口中抛出的异常，其会寻找注册在IOC容器中所有`BusinessExceptionHandler`的实例，并会调用每个实例中的`support`方法匹配相应的异常处理器进行处理，所以上面说的继承`AbstractBusinessException`不必额外编写异常处理器就是因为异常处理器链中存在一个`DefaultExceptionHandler`

## 怎么使用Debug功能
当接口出现异常时我们通常只能从前端那边得知请求接口、请求参数，这给我门排查问题带来了不小的难度，在开发环境以及测试环境可以通过在`application.yml`中的配置项`spring.profiles.active`中激活`debug`配置即可使用该功能
```
application.yml
---------------------
server:
    port: 8080
...
spring:
    profiles:
        active: dev, debug
        # active: test, debug
        # active: prod
...

```
Debug模式前端返回数据格式：

业务异常
```
/foo/business_exception
----------------------------
{
  "code": 1400,
  "msg": "无效的参数",
  "data": null,
  "_ex_msg": null,
  "_ex_stack": "com.example.web.controller.FooController.businessException(FooController.java:55)",
  "_cost_time": 1
}
```

系统异常
```
/foo/exception
----------------------------
{
  "code": 1500,
  "msg": "服务器内部错误",
  "data": null,
  "_ex_msg": "flag must not be null",
  "_ex_stack": "
  at com.example.web.controller.FooController.exception(FooController.java:64)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)",
  "_cost_time": 0
}
```

`_ex_msg`: 系统异常提示消息，业务异常可选返回

`_ex_stack`: 系统异常打印完整调用栈，业务异常只返回触发异常的方法栈

`_cost_time`: 接口处理耗时，通过该字段可以大致了解接口性能