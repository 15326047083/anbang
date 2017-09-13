package com.anbang.tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { java.lang.annotation.ElementType.FIELD })
public @interface ExcelVOAttribute {

	/**
	 * ������Excel�е�����.
	 */
	public abstract String name();

	/**
	 * �����е����,��ӦA,B,C,D....
	 */
	public abstract String column();

	/**
	 * ��ʾ��Ϣ
	 */
	public abstract String prompt() default "";

	/**
	 * ����ֻ��ѡ���������������.
	 */
	public abstract String[] combo() default {};

	/**
	 * �Ƿ񵼳����,Ӧ������:��ʱ������Ҫ����һ��ģ��,���Ǳ�����Ҫ��������Ҫ�û��ֹ���д.
	 */
	public abstract boolean isExport() default true;

}
