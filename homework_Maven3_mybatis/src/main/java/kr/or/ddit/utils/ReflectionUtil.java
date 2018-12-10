package kr.or.ddit.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {
	public static List<Class<?>> getClassesAtBasePackage(String basePackage) {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		URL baseURL=Thread.currentThread().getContextClassLoader().getResource("/"+basePackage.replace(".", "/"));//콸리파이드 네임을 폴더경로로 변ㄱ셩해주 ㅁ
		if(baseURL==null) return classList;
		File baseFolder =new File(baseURL.getFile());//클래스파일을 뽑아오기위한 폴더생성
		String[] classArray= baseFolder.list((dir,name)->{//폴더에서 클래스파일을 뽑아오기위한 메서드
			boolean flag=false;
			flag=name.endsWith(".class");
			return flag;
		});
//		System.out.println(Arrays.toString(classList));
		if(classArray!=null) {
			for (String classFileName : classArray) {
				int lastIndex=classFileName.lastIndexOf(".class");
				
				String qualifiedName= basePackage+"."+classFileName.substring(0,lastIndex);//클래스의 콸리파이드 네임을 찾아옴 ㅎㅎ;
				try {
					Class clz= Class.forName(qualifiedName);//클래스 로딩합니다 ㅎㅎ;
					classList.add(clz);
				} catch (ClassNotFoundException e) {
					continue;
				}//트케 앤드
				
			}//포문옌드
		}//이쁘문에 엔드
		return classList;
	}

	public static List<Class<?>> getClassesAtBasePackages(String... basePackages) {
		List<Class<?>> classList = new ArrayList<>();
		if (basePackages != null) {
			for (String basePackage : basePackages) {
				classList.addAll(getClassesAtBasePackage(basePackage));
			}
		}
		return classList;
	}

	public static List<Class<?>> getClassesWithAnnotationAtBasePackages(Class<? extends Annotation> annotationType,
			String... basePackages) {
		List<Class<?>> classList = getClassesAtBasePackages(basePackages);
		for (int idx = classList.size() - 1; idx >= 0; idx--) {
			Class<?> temp = classList.get(idx);
			if (temp.getAnnotation(annotationType) == null) {
				classList.remove(idx);
			}
		}
		return classList;

	}

	public static List<Method> getmethodsAtClass(Class<?> targetClz, Class<?> returnType, Class<?>... parameterTypes) {
		List<Method> methodList = new ArrayList<>();
		if (targetClz != null)
			methodList.addAll(Arrays.asList(targetClz.getDeclaredMethods()));
		for (int idx = methodList.size() - 1; idx >= 0; idx--) {
			Method temp = methodList.get(idx);
			if ((returnType != null && !returnType.equals(temp.getReturnType())

					|| (parameterTypes != null && !Arrays.deepEquals(parameterTypes, temp.getParameterTypes())))) {
				methodList.remove(idx);

			}

		}
		return methodList;
	}

	public static List<Method> getmethodsWithAnnotationAtClass(Class<?> targetClz, Class<? extends Annotation> annotationType,
			Class<?> returnType, Class<?>... parameterTypes) {
		List<Method> methodList = getmethodsAtClass(targetClz, returnType, parameterTypes);
		for (int idx = methodList.size() - 1; idx >= 0; idx--) {
			Method temp = methodList.get(idx);
			if (temp.getAnnotation(annotationType) == null) {
				methodList.remove(idx);
			}
		}
		return methodList;
	}
}
