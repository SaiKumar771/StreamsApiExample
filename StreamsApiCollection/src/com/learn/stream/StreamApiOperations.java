package com.learn.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamApiOperations {

	public static void main(String[] args) {
		
		List<Integer> nums1=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> nums2=Arrays.asList(11,12,13,14,15);
		List<Integer> nums3=Arrays.asList(21,22,23,24,25,26,27);
		
		List<List<Integer>> numList=new ArrayList<>();
		numList.add(nums3);
		numList.add(nums2);
		numList.add(nums1);
		
		
		System.out.println("-----1.forEach(Consumer)");
		nums1.stream().forEach(s->System.out.print(s+" "));
		
		System.out.println("\n-----2.fiter(Predicate)");
		nums1.stream().filter(s->s%2==0).forEach(s->System.out.print(s+" "));
		
		System.out.println("\n-----3.map(Function)");
		nums1.stream().map(s->s*2).forEach(s->System.out.print(s+" "));
		
		System.out.println("\n-----4.flatMap()");
		nums1.stream().flatMap(e->List.of(e*2,e*3,e*4).stream()).forEach(s->System.out.print(s+" "));
		
		System.out.println("\n-----5.flatMap()");
		numList.stream().flatMap(e->e.stream()).forEach(s->System.out.print(s+" "));
		
		System.out.println("\n-----6.reduce(identity,binaryoperation)");
		int sumResult=nums1.stream().reduce(0,(a,b)->a+b);
		System.out.println(sumResult);
		
		List<Double> nums4=Arrays.asList(1.0,2.0,3.0,4.0,5.0);
		System.out.println("-----7.sum()");
		double sumNums=nums4.stream().mapToDouble(s->s).sum();
		
		System.out.println("sum:"+sumNums);
		
		
	}
}
