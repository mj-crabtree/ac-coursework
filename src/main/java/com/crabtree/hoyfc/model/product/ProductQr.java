package com.crabtree.hoyfc.model.product;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ProductQr {
	// todo: research QR codes
	private BufferedImage productQuickResponseCode;
	//private String basisForProductQr; not even sure if this is a thing?
}