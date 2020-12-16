package com.kh.soomc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyTestComponent {
	
	public void testFunc() {
		System.out.println("테스트 하는 메소드 입니다. 무엇을 위한~? 컴포넌트 스캔, 어노테이션, DI");
	}
}
