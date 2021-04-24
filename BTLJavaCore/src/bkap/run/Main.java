package bkap.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bkap.entity.Catagories;
import bkap.entity.Product;

public class Main {
	static List<Catagories> listCatalog = new ArrayList<Catagories>();
	static List<Product> listProduct = new ArrayList<Product>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Main m = new Main();
		m.showMenu(sc, m);
	}

	// MENU chinh - Done
	public void showMenu(Scanner sc, Main m) {
		do {
			System.out.println("********************MENU********************");
			System.out.println("1. Quan ly danh muc.");
			System.out.println("2. Quan ly san pham.");
			System.out.println("3. Thoat.");
			System.out.println("Moi ban lua chon 1-3: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.showMenuCatalogManagement(sc, m);
				break;
			case 2:
				m.showMenuProductManagement(sc, m);
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-3: ");
				break;
			}
		} while (true);
	}

	// MENU Danh Muc
	public void showMenuCatalogManagement(Scanner sc, Main m) {
		boolean returnFlag = true;
		do {
			System.out.println("********************QUAN LY DANH MUC********************");
			System.out.println("1. Danh sach danh muc.");
			System.out.println("2. Them danh muc.");
			System.out.println("3. Xoa danh muc.");
			System.out.println("4. Tim kiem danh muc.");
			System.out.println("5. Quay lai.");
			System.out.println("Moi ban lua chon 1-5: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.showMenuListCatalog(sc, m);
				break;
			case 2:
				m.inputListCatalog(sc);
				break;
			case 3:
				m.deleteListCatalog(sc);
				break;
			case 4:
				m.searchListCatalog(sc);
				break;
			case 5:
				returnFlag = false;
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-5: ");
				break;
			}
		} while (returnFlag);
	}

	// 1.2- Done
	public void inputListCatalog(Scanner sc) {
		System.out.println("***Nhap so danh muc can them:");
		int n;
		do {
			try {
				n = Integer.parseInt(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("So danh muc phai la so nguyen. Moi nhap lai:");
			}
		} while (true);
		for (int i = 0; i < n; i++) {
			Catagories catagories = new Catagories();
			inputListCatalogId(sc, catagories);
			catagories.inputData();
			inputListCatalogPrId(sc, catagories);
			listCatalog.add(catagories);
			Main.writeToFileCatagories(listCatalog);
		}
		System.out.println("Them danh muc thanh cong!");
	}

	// Nhap ma danh muc
	public void inputListCatalogId(Scanner sc, Catagories catagories) {
		System.out.println("--Nhap ma danh muc:");
		do {
			try {
				catagories.setCatalogId(Integer.parseInt(sc.nextLine()));
				if (catagories.getCatalogId() > 0) {
					boolean checkCatalogId = false;
					for (Catagories catagories2 : listCatalog) {
						if (catagories2.getCatalogId() == catagories.getCatalogId()) {
							checkCatalogId = true;
							break;
						}
					}
					if (checkCatalogId) {
						System.err.println("Ma danh muc da ton tai. Moi nhap lai:");
					} else {
						break;
					}
				} else {
					System.err.println("Ma danh muc phai lon hon 0. Moi nhap lai:");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Ma danh muc phai la so nguyen. Moi nhap lai:");
			}

		} while (true);
	}

	// Nhap parentID
	public void inputListCatalogPrId(Scanner sc, Catagories catagories) {
		System.out.println("Nhap ma danh muc cha:");
		do {
			try {
				catagories.setParentId(Integer.parseInt(sc.nextLine()));
				int cnt = 0;
				int cnt2 = 0;
				if (catagories.getParentId() != catagories.getCatalogId()) {
					for (Product product : listProduct) {
						if (catagories.getParentId() == product.getCatalog().getCatalogId()) {
							cnt++;
							break;
						}
					}
					for (Catagories catagories2 : listCatalog) {
						if (catagories.getParentId() == catagories2.getCatalogId() && (listCatalog.size() != 0)) {
							cnt2++;
							break;
						}
					}
					if (cnt == 0) {
						if (cnt2 != 0 || (catagories.getParentId() == 0)) {
							break;
						} else {
							System.err.println("Khong ton tai ma danh muc. Moi nhap lai:");
						}
					} else {
						System.err.println("Danh muc nay da co san pham. Moi nhap lai:");
					}
				} else {
					System.err.println("Danh muc khong the nhan ban than lam danh muc cha. Moi nhap lai:");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
			}
		} while (true);
	}

	// 1.3-done
	public void deleteListCatalog(Scanner sc) {
		System.out.println("Nhap ma danh muc muon xoa:");
		do {
			try {
				int deleteCatalogId = Integer.parseInt(sc.nextLine());
				boolean checkDeleteCatalogId = false;
				for (int i = 0; i < listCatalog.size(); i++) {
					if (listCatalog.get(i).getCatalogId() == deleteCatalogId) {
						checkDeleteCatalogId = true;
						int cnt = 0;
						for (Catagories catagories : listCatalog) {
							if (deleteCatalogId == catagories.getParentId()) {
								cnt++;
								break;
							}
						}
						for (Product product : listProduct) {
							if (deleteCatalogId == product.getCatalog().getCatalogId()) {
								cnt++;
								break;
							}
						}
						if (cnt == 0) {
							listCatalog.remove(i);
							Main.writeToFileCatagories(listCatalog);
							System.out.println("Xoa danh muc thanh cong!");
						} else {
							System.err.println("Danh muc co chua danh muc con hoac san pham. Khong the xoa!");
						}
						break;
					}
				}
				if (!checkDeleteCatalogId) {
					System.err.println("Ma danh muc khong ton tai!");
				}
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
			}

		} while (true);
	}

	// 1.4-done
	public void searchListCatalog(Scanner sc) {
		System.out.println("***Nhap ten danh muc muon tim kiem:");
		String searchCatalogName = sc.nextLine();
		int cnt = 0;
		for (Catagories catagories : listCatalog) {
			if (catagories.getCatalogName().equals(searchCatalogName)) {
				System.out.println("Thong tin danh muc muon tim kiem la:");
				catagories.displayData();
				cnt++;
			}
		}
		if (cnt == 0) {
			System.err.println("Ten danh muc khong ton tai!");
		}
	}

	// 1.1 done
	public void showMenuListCatalog(Scanner sc, Main m) {
		boolean returnFlag = true;
		do {
			System.out.println("********************DANH SACH DANH MUC********************");
			System.out.println("1. Danh muc cay danh muc.");
			System.out.println("2. Thong tin chi tiet danh muc.");
			System.out.println("3. Quay lai.");
			System.out.println("Moi ban lua chon 1-3: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.displayTreeCatalog();
				break;
			case 2:
				m.displayListCatalog(sc);
				break;
			case 3:
				returnFlag = false;
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-3: ");
				break;
			}
		} while (returnFlag);
	}

	// 1.1.1-done
	public void displayTreeCatalog() {
		System.out.println("***Danh sach cay danh muc:");
		int i = 0;
		for (Catagories catagories : listCatalog) {
			if (catagories.getParentId() == 0) {
				i++;
				System.out.printf("%d.%s\n", i, catagories.getCatalogName());
				int j = 0;
				for (Catagories catagories2 : listCatalog) {
					if (catagories2.getParentId() == catagories.getCatalogId()) {
						j++;
						System.out.printf("\t%d.%d.%s\n", i, j, catagories2.getCatalogName());
						int k = 0;
						for (Catagories catagories3 : listCatalog) {
							if (catagories3.getParentId() == catagories2.getCatalogId()) {
								k++;
								System.out.printf("\t\t%d.%d.%d.%s\n", i, j, k, catagories3.getCatalogName());
							}
						}
					}
				}
			}
		}
	}

	// 1.1.2-Done
	public void displayListCatalog(Scanner sc) {
		System.out.println("***Hien thi thong tin chi tiet danh muc:");
		System.out.println("Nhap vao ten danh muc can xem thong tin:");
		String checkCatalogName = sc.nextLine();
		int cnt = 0;
		for (Catagories catagories : listCatalog) {
			if (catagories.getCatalogName().equals(checkCatalogName)) {
				cnt++;
				catagories.displayData();
				for (Catagories catagories2 : listCatalog) {
					if (catagories2.getParentId() == catagories.getCatalogId()) {
						catagories2.displayData();
						for (Catagories catagories3 : listCatalog) {
							if (catagories3.getParentId() == catagories2.getCatalogId()) {
								catagories3.displayData();

							}
						}
					}
				}
			}
		}
		if (cnt == 0) {
			System.out.println("Danh muc khong ton tai!");
		}
	}

	// MENU san pham
	public void showMenuProductManagement(Scanner sc, Main m) {
		boolean returnFlag = true;
		do {
			System.out.println("********************QUAN LY SAN PHAM********************");
			System.out.println("1. Them san pham moi.");
			System.out.println("2. Tinh loi nhuan san pham.");
			System.out.println("3. Hien thi thong tin san pham.");
			System.out.println("4. Sap xep san pham.");
			System.out.println("5. Cap nhat thong tin san pham.");
			System.out.println("6. Cap nhat trang thai san pham.");
			System.out.println("7. Quay lai.");
			System.out.println("Moi ban lua chon 1-7: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.inputListProduct(sc);
				break;
			case 2:
				m.calListProductProfit();
				break;
			case 3:
				m.showMenuProductInfomation(sc, m);
				break;
			case 4:
				m.showMenuProductSort(sc, m);
				break;
			case 5:
				m.updateListProductInfo(sc);
				break;
			case 6:
				m.updateListProductStt(sc);
				break;
			case 7:
				returnFlag = false;
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-7: ");
				break;
			}
		} while (returnFlag);
	}

	// 2.1-done
	public void inputListProduct(Scanner sc) {
		System.out.println("***Nhap so san pham muon them:");
		int n;
		do {
			try {
				n = Integer.parseInt(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("So danh muc phai la so nguyen. Moi nhap lai:");
			}
		} while (true);
		for (int i = 0; i < n; i++) {
			Product product = new Product();
			inputListProductIdAndName(sc, product);
			product.inputData();
			inputListProductCatalog(sc, product);
			listProduct.add(product);
			Main.writeToFileProduct(listProduct);
		}
		System.out.println("Them san pham thanh cong!");
	}

	// Nhap ten va Id san pham
	public void inputListProductIdAndName(Scanner sc, Product product) {
		System.out.println("--Nhap ma san pham:");
		do {
			product.setProductId(sc.nextLine());
			if (product.getProductId().length() == 4) {
				if (product.getProductId().startsWith("C")) {
					boolean checkProductId = false;
					for (Product pd : listProduct) {
						if (pd.getProductId().equals(product.getProductId())) {
							checkProductId = true;
							break;
						}
					}
					if (checkProductId) {
						System.err.println("Ma san pham da ton tai. Moi nhap lai:");
					} else {
						break;
					}
				} else {
					System.err.println("Ma san pham phai bat dau la ky tu C. Moi nhap lai:");
				}
			} else {
				System.err.println("Ma san pham phai co 4 ky tu. Moi nhap lai:");
			}
		} while (true);
		System.out.println("Nhap ten san pham:");
		do {
			product.setProductName(sc.nextLine());
			if (product.getProductName().length() >= 6 && product.getProductName().length() <= 50) {
				boolean checkProductName = false;
				for (Product pr : listProduct) {
					if (pr.getProductName().equals(product.getProductName())) {
						checkProductName = true;
						break;
					}
				}
				if (checkProductName) {
					System.err.println("Ten san pham da ton tai. Moi nhap lai:");
				} else {
					break;
				}
			} else {
				System.err.println("Ten san pham chua 6-50 ky tu. Moi nhap lai:");
			}
		} while (true);
	}

	// Nhap danh muc cho san pham:
	public void inputListProductCatalog(Scanner sc, Product product) {
		System.out.println("Nhap ma danh muc cua san pham:");
		do {
			try {
				int cataId = Integer.parseInt(sc.nextLine());
				if (cataId > 0) {
					boolean checkCatalogId = false;
					for (Catagories catagories : listCatalog) {
						if (catagories.getCatalogId() == cataId) {
							product.setCatalog(catagories);
							checkCatalogId = true;
							break;
						}
					}
					if (checkCatalogId) {
						break;
					} else {
						System.err.println("Khong ton tai danh muc co ma danh muc da nhap. Moi nhap lai:");
					}
				} else {
					System.err.println("Ma danh muc phai lon hon 0. Moi nhap lai:");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Ma danh muc phai la so nguyen. Moi nhap lai:");
			}

		} while (true);
	}

	// 2.2 Done
	public void calListProductProfit() {
		for (Product product : listProduct) {
			product.calProfit();
		}
		System.out.println("***Tinh loi nhuan san pham thanh cong$$");
	}

	// 2.5-done
	public void updateListProductInfo(Scanner sc) {
		System.out.println("***Nhap ma san pham muon cap nhat:");
		String productIdNew = sc.nextLine();
		int cnt = 0;
		Product product = new Product();
		for (int i = 0; i < listProduct.size(); i++) {
			if (listProduct.get(i).getProductId().equals(productIdNew)) {
				System.out.println("Cap nhat thong tin san pham:");
				inputListProductIdAndName(sc, product);
				product.inputData();
				inputListProductCatalog(sc, product);
				listProduct.set(i, product);
				Main.writeToFileProduct(listProduct);
				System.out.println("Cap nhat thong tin thanh cong");
				cnt++;
			}

		}
		if (cnt == 0) {
			System.err.println("Khong co san pham muon tim!");
		}
	}

	// 2.6-done
	public void updateListProductStt(Scanner sc) {
		System.out.println("***Nhap ma san pham muon cap nhat:");
		String productIdNew = sc.nextLine();
		int cnt = 0;
		Product product = new Product();
		for (int i = 0; i < listProduct.size(); i++) {
			if (listProduct.get(i).getProductId().equals(productIdNew)) {
				System.out.println("Cap nhat trang thai san pham:");
				do {
					String str = sc.nextLine();
					if (str.equals("true") || str.equals("false")) {
						listProduct.get(i).setProductStatus(Boolean.parseBoolean(str));
						Main.writeToFileProduct(listProduct);
						break;
					} else {
						System.err.println("Chi nhan gia tri true hoac false khi nhap. Moi nhap lai:");
					}
				} while (true);
				System.out.println("Cap nhat trang thai thanh cong!");
				cnt++;
			}

		}
		if (cnt == 0) {
			System.err.println("Khong co san pham muon tim!");
		}
	}

	// 2.3 done
	public void showMenuProductInfomation(Scanner sc, Main m) {
		boolean returnFlag = true;
		do {
			System.out.println("********************THONG TIN SAN PHAM********************");
			System.out.println("1. Hien thi san pham theo danh muc.");
			System.out.println("2. Hien thi chi tiet san pham.");
			System.out.println("3. Quay lai.");
			System.out.println("Moi ban lua chon 1-3: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.diaplayListProductByCatalog();
				break;
			case 2:
				m.displayListProductByName(sc);
				break;
			case 3:
				returnFlag = false;
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-3: ");
				break;
			}
		} while (returnFlag);
	}

	// 2.3.1 done
	public void diaplayListProductByCatalog() {
		System.out.println("Hien thi san pham theo danh muc:");
		for (Product product : listProduct) {
			product.getCatalog().displayData();
		}
		System.out.println("Hien thi thong tin thanh cong!");
	}

	// 2.3.2-done
	public void displayListProductByName(Scanner sc) {
		System.out.println("***Thong tin chi tiet san pham");
		System.out.println("Nhap ten san pham tim kiem:");
		String proName = sc.nextLine();
		int cnt = 0;
		for (Product product : listProduct) {
			if (product.getProductName().equals(proName)) {
				cnt++;
				product.displayData();
//				Main.readObjectFromFilePro();
				break;
			}
		}
		if (cnt == 0) {
			System.out.println("Khong ton tai ten san pham da nhap!");
		} else {
			System.out.println("Hien thi thong tin thanh cong!");
		}
	}

	// 2.4-done
	public void showMenuProductSort(Scanner sc, Main m) {
		boolean returnFlag = true;
		do {
			System.out.println("********************SAP XEP SAN PHAM********************");
			System.out.println("1. Sap xep san pham theo gia suat tang dan.");
			System.out.println("2. Sap xep san pham theo loi nhuan giam dan.");
			System.out.println("3. Quay lai.");
			System.out.println("Moi ban lua chon 1-3: ");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Nhap sai kieu du lieu. Moi nhap lai:");
				}
			} while (true);
			switch (choice) {
			case 1:
				m.sortListProductByEP();
				break;
			case 2:
				m.sortListProductByProfit();
				break;
			case 3:
				returnFlag = false;
				break;
			default:
				System.err.println("Moi ban lua chon lai tu 1-3: ");
				break;
			}
		} while (returnFlag);
	}

	// 2.4.1-done
	public void sortListProductByEP() {
		for (int i = 0; i < listProduct.size() - 1; i++) {
			for (int j = i + 1; j < listProduct.size(); j++) {
				if (listProduct.get(i).getExportPrice() > listProduct.get(j).getExportPrice()) {
					Product productTemp = listProduct.get(i);
					listProduct.set(i, listProduct.get(j));
					listProduct.set(j, productTemp);
				}
			}
		}
		Main.writeToFileProduct(listProduct);
		System.out.println("Sap xep thanh cong!");
	}

	// 2.4.2-done
	public void sortListProductByProfit() {
		for (int i = 0; i < listProduct.size() - 1; i++) {
			for (int j = i + 1; j < listProduct.size(); j++) {
				if (listProduct.get(i).getProfit() < listProduct.get(j).getProfit()) {
					Product productTemp = listProduct.get(i);
					listProduct.set(i, listProduct.get(j));
					listProduct.set(j, productTemp);
				}
			}
		}
		Main.writeToFileProduct(listProduct);
		System.out.println("Sap xep thanh cong!");
	}

	// Ghi file Cata
	public static void writeToFileCatagories(List<Catagories> listCt) {
		try {
			File fileCatagories = new File("D:/eclipse-workspace/categories.txt");
			FileOutputStream fos = new FileOutputStream(fileCatagories);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listCt);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Xuat file Cata
	public static void readObjectFromFileCata() {
		try {
			File fileCatagories = new File("D:/eclipse-workspace/categories.txt");
			FileInputStream fis = new FileInputStream(fileCatagories);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Catagories> listCataRead = (List<Catagories>) ois.readObject();
			ois.close();
			fis.close();
			for (Catagories catagories : listCataRead) {
				System.out.println(catagories.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Ghi file Product
	public static void writeToFileProduct(List<Product> listPr) {
		try {
			File fileProduct = new File("D:/eclipse-workspace/product.txt");
			FileOutputStream fos = new FileOutputStream(fileProduct);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listPr);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Xuat file Pro
	public static void readObjectFromFilePro() {
		try {
			File fileProduct = new File("D:/eclipse-workspace/product.txt");
			FileInputStream fis = new FileInputStream(fileProduct);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Product> listProRead = (List<Product>) ois.readObject();
			ois.close();
			fis.close();
			for (Product product : listProRead) {
				System.out.println(product.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
