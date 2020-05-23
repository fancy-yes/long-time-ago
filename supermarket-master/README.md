# new1-maven

### maven项目三个部分

1. java目录：存放源代码 
2.  resources 存放资源文件 （propertise文件，xml文件，static文件（img，css文件，JavaScript文件
3. webapp   存放前端页面的模板 （jsp，html）

### Java目录

1. damain  实体类 可能这个类是跟数据库一张表的属性是对应的 （属性，getset方法）
   Class User{
   private String userName;
   private String password;
   }
2. util    工具包  DateUtils  PageBeanUtils BeanUtils  StringUtils ListUtils
   session 会话，你跟数据库建立一次连接（你跟数据库的一个会话），mybatis怎么构建一个session的呢？
   （1）根据你的mybatis配置文件创建一个SqlSessionFactoryBuilder（建造者模式）
   （2）通过SqlSessionFactoryBuilder来建立一座sqlSessionFactory
   （3）sqlSessionFactory生产session的
   （序列化）对象是存在于内存的，把对象序列化之后，那这个对象就可以存在于硬盘，还可以在网络中传输

3. service层  专门处理业务逻辑的

4. Dao层的对象就是   Data Acess Object  数据访问对象
   应该全部都是接口  接口里面的方法是对应数据库的一次操作

   ```java
   4. InterFace UserMapper{
   
   User  getUser（String userName);
   
   int inserUser(User user);
   
   }
   ```

     

5.mapper就是Dao层接口的实现
UserDao.getUser  这个接口的实现类

```xml
<mapper namespace="com.hut.zc.SuperMarket.mapper.UserMapper" >

<select id="getUser" parameterType="String" resultType=“User”>
	select * from t_user where userName=#{userName}
</select>

<insert id="insertUser" parameterType="User" >
	insert into t_user values(#{userName},#{password} );
</insert>
</mapper>
```


mybatis会根据你的xml的文件去生成相应的实现类去实现你的数据路CRUD操作
这些标签都是语义规范

6.servlet
其实就是 一个控制器

# new2-mybatis

### mybatis配置文件

一共是有两类配置文件的。

1. mybatis的总的配置文件，这个是一些mybatis配置，是用来创建sqlsessionfactorybuilder的xml文件

```xml
	<mapper resource="mapper/user.xml"></mapper>
    <mapper resource="mapper/bill.xml"></mapper>
    <mapper resource="mapper/supplier.xml"></mapper>
    <mapper resource="mapper/admin.xml"></mapper>
```

2. DAO层（mapper层）接口对应的xml文件
   mapper/admin.xm
   mapper/supplier.xml
   mapper/bill.xml
   去生成Dao接口的实现类帮助你维护结果集，创建事务，建立连接



# new3-Servlet

### Servlet
  Tomcat服务器（一个servlet的容器）
   http://127.0.0.1:8080/supermarketing/SuperMarket
   协议名：//ip：port/项目名/路径名

   get     post  delete  http协议
   doGet   doPost
   LoginServlet extends HttpServlet

   get  http://127.0.0.1:8080/supermarketing/SuperMarket?userName=xujia
   post http://127.0.0.1:8080/supermarketing/SuperMarket    数据包（username=xujia) 加密，密文

   HTTP请求-》相应的Servlet的相应方法去执行-》Service-》mapper -》数据库
   http响应

   转账（mapper）2个操作分开的  减钱方法，加钱方法
   （service） （先给我减钱，再给你加钱）事务，要不全部成功要么回滚，就是相应很多mapper操作集合
   servlet根据不同的请求，就是调用service的，servlet里面有一些http请求的参数  根据不同的请求调用service
   MVC（C请求转发交付Service，返回相应的界面——View（jsp，freemarker thymleaf））

   

# new4-Http

Ip  TCP  http协议（请求，响应）

请求-》tomcat服务器8080  TOmcat自动一直监听一个8080端口有没有请求（数据包）发过来
项目部署到Tomcat服务器里面的话

项目必须要有类集成httpServletimplements Servlet接口这个抽象类的

servlet规范   tomcat对调用实现了servlet接口的类

