# Static Or Not

Through this example repository, I am trying to express possible ways to create util classes, especially as a part of
the SpringBoot and OOP world.

## Build

- [jdk17](https://adoptium.net/)
- [maven](https://maven.apache.org/)

```shell
mvn clean install
```

## Project

### common-company-library

Standard java library is represented by the [common-company-library](./common-company-library) which every java company
project should use. This library doesn't use DI container/framework. Our example contains three util classes.

- [ScDf](./common-company-library/src/main/java/sk/janobono/ccl/ScDf.java) represents widely used a classic util class
  with static methods.
- [ImageUtil](./common-company-library/src/main/java/sk/janobono/ccl/ImageUtil.java) represents widely used util class
  without static methods. Can be realized via Singleton pattern (first call private static object initialization).
- [RandomString](./common-company-library/src/main/java/sk/janobono/ccl/RandomString.java) represents widely used util
  class without static methods.

### one-of-company-projects

Represents any company project. In this case SpringBoot framework is used.

#### project-common-library

Project related library used by all services.

- [CodeFormatter](./one-of-company-projects/project-common-library/src/main/java/sk/janobono/oocp/common/CodeFormatter.java)
  will be used in every service so here is @Component annotation used.
- [LocalStorage](./one-of-company-projects/project-common-library/src/main/java/sk/janobono/oocp/common/LocalStorage.java)
  will be used just by specific services, so it's not registered as a bean automatically.
- [CCLImageUtil](./one-of-company-projects/project-common-library/src/main/java/sk/janobono/oocp/common/CCLImageUtil.java)
  improved [ImageUtil](./common-company-library/src/main/java/sk/janobono/ccl/ImageUtil.java) polymorphism used.

#### project-service-one

- [PsoLocalStorage](./one-of-company-projects/project-service-one/src/main/java/sk/janobono/oocp/pso/component/PsoLocalStorage.java)
  is local version
  of [LocalStorage](./one-of-company-projects/project-common-library/src/main/java/sk/janobono/oocp/common/LocalStorage.java)
  .
- [PsoUtilConfig](./one-of-company-projects/project-service-one/src/main/java/sk/janobono/oocp/pso/config/PsoUtilConfig.java)
  is config which
  registers [CCLImageUtil](./one-of-company-projects/project-common-library/src/main/java/sk/janobono/oocp/common/CCLImageUtil.java)
  as a singleton bean.
- [PsoService](./one-of-company-projects/project-service-one/src/main/java/sk/janobono/oocp/pso/service/PsoService.java)
  service uses utils.

#### project-service-two

- [PstDataUtil](./one-of-company-projects/project-service-two/src/main/java/sk/janobono/oocp/pst/component/PstDataUtil.java)
  is current service local util.
- [PstUtilConfig](./one-of-company-projects/project-service-two/src/main/java/sk/janobono/oocp/pst/config/PstUtilConfig.java)
  is config which registers [RandomString](./common-company-library/src/main/java/sk/janobono/ccl/RandomString.java) as
  a singleton bean.
- [PstService](./one-of-company-projects/project-service-two/src/main/java/sk/janobono/oocp/pst/service/PstService.java)
  service uses utils.

## Theory

### Why statics are important?

First, statics provide us a powerful mechanism for initializing data only once it has been loaded by the class loader
and for all. Here, we talk about the optimization of data initialization. The naive singleton pattern implementation
contains a static instance, which can be initialized at the time of its first call.

Second, statics are very useful to create clear functions that are working with the value classes and doing routine
utility tasks. This point, actually, is very controversial, especially for the fans of pure object-oriented design, by
which each operation should be wrapped in a separate object.

Nevertheless, a lot of existing libraries and frameworks actively use statics for constants and immutable entities, and,
of course, for the creation of useful utility classes.

### Why statics are bad for OO code?

The first point of OO design is the idea of messaging. It means that a typical program following object-oriented
principles consists of a network of self-contained objects which are communicating with each other by sending messages (
instead of accessing each other data directly, it is also called incapsulation). In the case of most popular OO
languages messaging is implemented as invocations of object methods with parameters.

The second important point is dynamic dispatch also called polymorphism. The idea of polymorphism is selecting of which
implementation of the class should be used on the runtime.

The concept of static members and methods violates both of these principles.

The messaging principle seems to be violated by statics because static methods aren’t associated with any object. They
aren’t methods at all, according to the usual definition. They are just procedures. There’s pretty much no difference
between a Java static method Foo.bar() and a basic subroutine FOO_BAR() from any procedural not-OO language. This
statement is also fair for static members and constants from procedural languages.

As for non-OOP principles — static methods are easy to test, but it is pretty hard to mock. For mocking static methods
you’ll probably have to use some reflection libraries like
[PowerMockito](https://github.com/powermock/powermock/wiki/Mockito) on the top of the standard testing utility
kits (like testing starter from spring-boot). If you organize your utility class as a regular OO class — there will be
no problem with, for example, Mockito library to easily mock your functionality.

### What about the best practices of using statics?

Use static members, when:

- You need to use some data as constant. A static member is always acceptable with the addition of the final keyword,
  which means that this is a constant value, declared only once in class, and it can’t change after initialization.
- You need to share some data between instances and this data can be changed. This is not a very good approach,
  especially, when you deal with multithreading applications. But I think it is OK, if you’re sure, that this shared
  state will change predictably and safely for the execution of your program and the consistency of your data.

Use static methods, when:

- You need to separate extremely reusable code, which is tightly linked with usage of some toolkit or set of classes.
- You need to create a functionality that is tightly coupled with the implementation, but it isn’t linked with any
  instance. For example data-creation methods or enum converting methods.

**If you’re using a DI container/framework, there isn’t so much need of using static methods, any utility class could be
registered as a singleton bean or context object and injected in the place of usage as regular OO component.**

## Conclusion

In conclusion, I would like to say that statements like “violation of OO design principles” and “making the design of
program as not pure OO” don’t make your code bad, and, of course, don’t make the entire usage of statics as bad
practice. OO design is not a panacea for all problems. Each approach should be justified and should also be applied
wisely.
