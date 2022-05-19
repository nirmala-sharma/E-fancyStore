package com.example.demo.globalAccess;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.enittiy.product;

public class GlobalData {
	public static List<product> cart;
	static {
		cart=new ArrayList<>();
	}

}
