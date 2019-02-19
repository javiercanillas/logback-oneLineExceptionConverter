# logback-oneLineExceptionConverter

This is an example on how to make your own converter to put all Exception information on the same line. This will be quite helpful on production environments where you can grep over logs and find the whole entry for a given error, instead of searching around for the whole stacktrace and causes.

For example, a normal log of an Exception would be something like this>

```dtd
2019-02-19 18:09:35,725 [ERROR] [m.j.l.ExceptionLoggingTest] [main] ExpectedError
java.lang.RuntimeException: This is a test
	at me.java.logging.ExceptionLoggingTest.testLoggingException(ExceptionLoggingTest.java:16)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
```

With OneLineExceptionConverter it would be something like this:

```dtd
2019-02-19 18:04:46,938 [ERROR] [m.j.l.ExceptionLoggingTest] [main] ExpectedError java.lang.RuntimeException: This is a test|	at me.java.logging.ExceptionLoggingTest.testLoggingException(ExceptionLoggingTest.java:16)|	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)|	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)|	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)|	at java.lang.reflect.Method.invoke(Method.java:498)|	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)|	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)|	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)|	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)|	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)|	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)|	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)|	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)|	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)|	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)|	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)|	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)|	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)|	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)|	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)|	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)|	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)|	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)| 
```

Hope this example helps you out!