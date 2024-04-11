package com.example

import org.springframework.context.annotation.*
import org.springframework.stereotype.Component
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.beans.factory.config.ConfigurableBeanFactory

@Configuration
@ComponentScan("com.example")
class AppConfig

// 1. Creating a bean using @Component
@Component
class MyComponent {
    fun sayHello() {
        println("Hello from MyComponent!")
    }
}

// 2. Creating beans using @Configuration and @Bean
@Configuration
class MyConfiguration {
    @Bean
    fun myBean(): MyBean {
        return MyBean()
    }
}

class MyBean {
    fun sayHello() {
        println("Hello from MyBean!")
    }
}

// 3. Using @Conditional to conditionally create a bean
@Conditional(MyCondition::class)
@Component
class ConditionalComponent {
    fun sayHello() {
        println("Hello from ConditionalComponent!")
    }
}

@Component
class MyCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return true // Example: always create the bean
    }
}

// Non-singleton bean
@Scope("prototype")
@Component
class NonSingletonBean {
    fun sayHello() {
        println("Hello from NonSingletonBean!")
    }
}

fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)

    val myComponent = context.getBean(MyComponent::class.java)
    myComponent.sayHello()

    val myBean = context.getBean(MyBean::class.java)
    myBean.sayHello()

    val conditionalComponent = context.getBean(ConditionalComponent::class.java)
    conditionalComponent.sayHello()

    val nonSingletonBean1 = context.getBean(NonSingletonBean::class.java)
    nonSingletonBean1.sayHello()

    val nonSingletonBean2 = context.getBean(NonSingletonBean::class.java)
    nonSingletonBean2.sayHello()
}