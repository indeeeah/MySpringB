package com.kh.soomc.common.servic;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogPointcut {
	@Pointcut("execution(* com.kh.soomc..*Impl.*(..))")
	public void allPointcut() {
		
	}

	@Pointcut("execution(* com.kh.soomc..*Impl.get*(..))")
	public void getPointcut() {
		
	}
}
