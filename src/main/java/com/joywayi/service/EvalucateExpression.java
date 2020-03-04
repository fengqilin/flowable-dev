package com.joywayi.service;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class EvalucateExpression {


    @Autowired
    BeanFactory beanFactory;

    public void evalcate() {

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("@holidayService.test(\"abcd\")");
        Object value = expression.getValue(context, Object.class);
        System.out.println(  "--------------->" + value);


    }
}
