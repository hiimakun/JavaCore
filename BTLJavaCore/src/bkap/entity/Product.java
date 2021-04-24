package bkap.entity;

import java.io.Serializable;
import java.util.Scanner;

import bkap.IProduct;

public class Product implements IProduct, Serializable {
	private String productId;
	private String productName;
	private String title;
	private float importPrice;
	private float exportPrice;
	private float profit;
	private String descriptions;
	private boolean productStatus;
	private Catagories catalog;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
			float profit, String descriptions, boolean productStatus, Catagories catalog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.descriptions = descriptions;
		this.productStatus = productStatus;
		this.catalog = catalog;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public float getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Catagories getCatalog() {
		return catalog;
	}

	public void setCatalog(Catagories catalog) {
		this.catalog = catalog;
	}

	@Override
	public void inputData() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap tieu de san pham:");
		do {
			this.title = sc.nextLine();
			if (this.title.length() >= 6 && this.title.length() <= 30) {
				break;
			} else {
				System.err.println("Tieu de phai co so ky tu 6-30. Moi nhap lai:");
			}
		} while (true);
		System.out.println("Nhap gia nhap san pham:");
		do {
			try {
				this.importPrice = Float.parseFloat(sc.nextLine());
				if (this.importPrice > 0) {
					break;
				} else {
					System.err.println("Gia nhap phai lon hon 0. Moi nhap lai:");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Gia nhap phai la so thuc. Moi nhap lai:");
			}

		} while (true);
		System.out.println("Nhap gia ban san pham:");
		do {
			try {
				this.exportPrice = Float.parseFloat(sc.nextLine());
				if (this.exportPrice > (this.importPrice * (1 + MIN_INTEREST_RATE))) {
					break;
				} else {
					System.err.printf("Gia ban phai lon hon gia nhap it nhat la %f lan. Moi nhap lai:",
							MIN_INTEREST_RATE);
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Gia ban phai la so thuc. Moi nhap lai:");
			}

		} while (true);
		System.out.println("Nhap mo ta san pham:");
		do {
			this.descriptions = sc.nextLine();
			if (this.descriptions.length() != 0) {
				break;
			} else {
				System.err.println("Khong duoc de trong mo ta. Moi nhap lai:");
			}
		} while (true);
		System.out.println("Nhap trang thai san pham:");
		do {
			String str = sc.nextLine();
			if (str.equals("true") || str.equals("false")) {
				this.productStatus = Boolean.parseBoolean(str);
				break;
			} else {
				System.err.println("Chi nhan gia tri true hoac false khi nhap. Moi nhap lai:");
			}
		} while (true);
	}

	@Override
	public void displayData() {
		// TODO Auto-generated method stub
		System.out.println("---Ma san pham = " + productId + " - Ten san pham = " + productName + " - Tieu de = " + title);
		System.out.println("Gia nhap = " + importPrice + " - Gia ban = " + exportPrice + " - Loi nhuan = " + profit);
		String status = this.productStatus ? "Hoat dong" : "Khong hoat dong";
		System.out.println("Mo ta = " + descriptions + " - Trang thai = " + status);
		System.out.println("Catagories = " + catalog.getCatalogName());
	}

	@Override
	public void calProfit() {
		// TODO Auto-generated method stub
		profit = exportPrice - importPrice;

	}

}
