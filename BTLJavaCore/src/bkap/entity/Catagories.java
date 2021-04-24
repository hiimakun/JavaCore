package bkap.entity;

import java.io.Serializable;
import java.util.Scanner;

import bkap.ICatagories;

public class Catagories implements ICatagories,Serializable {
	private int catalogId;
	private String catalogName;
	private String descriptions;
	private boolean catalogStatus;
	private int parentId;

	public Catagories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catagories(int catalogId, String catalogName, String descriptions, boolean catalogStatus, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.catalogStatus = catalogStatus;
		this.parentId = parentId;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean isCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public void inputData() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ten danh muc:");
		do {
			this.catalogName = sc.nextLine();
			if (this.catalogName.length() >= 6 && this.catalogName.length() <= 30) {
				break;
			} else {
				System.err.println("Ten danh muc phai co 6-30 ky tu. Moi nhap lai:");
			}
		} while (true);
		System.out.println("Nhap mo ta danh muc:");
		do {
			this.descriptions = sc.nextLine();
			if (this.descriptions.length() != 0) {
				break;
			} else {
				System.err.println("Khong duoc de trong mo ta. Moi nhap lai:");
			}
		} while (true);
		System.out.println("Nhap trang thai danh muc:");
		do {
			String str = sc.nextLine();
			if (str.equals("true") || str.equals("false")) {
				this.catalogStatus = Boolean.parseBoolean(str);
				break;
			} else {
				System.err.println("Chi nhan gia tri true hoac false khi nhap. Moi nhap lai:");
			}
		} while (true);
	}

	@Override
	public void displayData() {
		// TODO Auto-generated method stub
		System.out.println("---Ma danh muc = " + catalogId + " - Ten danh muc = " + catalogName);
		System.out.println("Mo ta = " + descriptions);
		String status = this.catalogStatus ? "Hoat dong" : "Khong hoat dong";
		System.out.println("parentId = " + parentId + " - Trang Thai = " + status);
	}

}
